grammar TSmm;

@header{
import ast.*;
import ast.expressions.*;
import ast.definitions.*;
import ast.types.*;
}

program returns [Program ast] locals [List<Definition> defs = new ArrayList<>()]:
        (definition{$defs.add($definition.ast);})* {$ast = new Program($defs);} EOF
       ;

statement: 'log' expression (',' expression)*? ';'
         | 'input' expression (',' expression)*? ';'
         | expression '=' expression ';'
         | 'if''('expression')' block ('else' block)?
         | 'while' '('expression')' block
         | 'return' expression ';'
         | ID'('(expression (','expression)*)?')'';'
         ;

block: '{' statement* '}'
     | statement
     ;

expression returns [Expression ast]:
            ID {$ast = new Variable($ID.text, $ID.getLine(), $ID.getCharPositionInLine()+1);}
          | INT_CONSTANT {$ast = new IntLiteral(LexerHelper.lexemeToInt($INT_CONSTANT.text),$INT_CONSTANT.getLine(),
                                $INT_CONSTANT.getCharPositionInLine() + 1);}
          | REAL_CONSTANT {$ast = new DoubleValue(LexerHelper.lexemeToReal($REAL_CONSTANT.text),$REAL_CONSTANT.getLine(),
                                          $REAL_CONSTANT.getCharPositionInLine() + 1);}
          | CHAR_CONSTANT {$ast = new CharLiteral(LexerHelper.lexemeToChar($CHAR_CONSTANT.text),$CHAR_CONSTANT.getLine(),
                                           $CHAR_CONSTANT.getCharPositionInLine() + 1);}
          | '('e1=expression')' {$ast = $e1.ast;}
          | e1=expression '.' ID {$ast = new FieldAccess($e1.ast,$ID.text,
                $e1.ast.getLine(), $e1.ast.getColumn() + 1);}
          | '('expression 'as' simple_type')'
          | ID'('(expression (','expression)*)?')'
          | '-' expression
          | '!' expression
          | expression ('*'|'/'|'%') expression
          | e1=expression OP=('-'|'+') e2=expression {$ast = new ArithmeticOperation($e1.ast, $OP.text, $e2.ast,
                $e1.ast.getLine(), $e1.ast.getColumn() + 1);}
          | expression ('>'|'<'|'>='|'<='|'=='|'!=') expression
          | expression ('&&'|'||') expression
          | expression '[' expression ']'
          ;

definition returns [Definition ast]:
        var_definition
        | function_definition
        ;

function_definition: function_type '{' var_definition* statement* '}'
                    ;

function_type: 'function' ID '(' (ID ':' simple_type (',' ID ':' simple_type)*)?  ')'
             | 'function' ID '('  (ID ':' simple_type (',' ID ':' simple_type)*)?  ')' ':' type
             ;

var_definition returns [List<Definition> ast] locals [List<String> ids = new ArrayList<>()]:
                LET='let' ID1=ID {$ids.add($ID1.text);} (',' ID2=ID{$ids.add($ID2.text);})* ':' type ';'
                {for(String id: $ids)
                    new VarDefinition($type.ast, id, $LET.getLine(), $LET.getCharPositionInLine() + 1);}
                ;

type returns [Type ast] locals [int dim = 1]:
    simple_type {$ast = $simple_type.ast;}
    | 'void'  {$ast = VoidType.getInstance();}
    | '['('let' ID ':' type ';' {})*']' {$ast = }
    | ('['INT_CONSTANT']'{$dim = $dim*LexerHelper.lexemeToInt($INT_CONSTANT.text);})+
        simple_type {$ast = new ArrayType($dim,$simple_type.ast);}
    ;

simple_type returns [Type ast]:
    'int' {$ast = Int.getInstance();}
     | 'char' {$ast = Char.getInstance();}
     | 'number' {$ast = Real.getInstance();}
    ;

// ----------------------------------------------------------

ID: [a-zA-Z_]+[a-zA-Z_0-9]*
         ;

CHAR_CONSTANT: '\'' . '\''
             | '\'' '\\'INT_CONSTANT'\''
             | '\'' '\\'[nt] '\''
             ;
  		 
INT_CONSTANT: [1-9][0-9]*
            | '0'
            ;

REAL_CONSTANT: INT_CONSTANT'.'[0-9]*EXP?
        | '.'[0-9]+EXP?
        | INT_CONSTANT EXP
        ;

fragment EXP: [eE][+-]?INT_CONSTANT
        ;

SINGLE_LINE_COMMENT: '//'.*?('\n'|EOF) -> skip
                    ;

MULTILINE_COMMENT : '/*'.*?'*/' ->skip
                   ;

WHITESPACES: [\t\n\r ] -> skip
            ;

