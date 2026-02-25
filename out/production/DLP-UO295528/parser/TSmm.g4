grammar TSmm;

@header{
import ast.*;
import ast.expressions.*;
}

program returns [Program ast]:
        definition* EOF
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
            ID
          | INT_CONSTANT {$ast = new IntLiteral(LexerHelper.lexemeToInt($INT_CONSTANT.text),$INT_CONSTANT.getLine(),
                                $INT_CONSTANT.getCharPositionInLine() + 1);}
          | REAL_CONSTANT {$ast = new DoubleValue(LexerHelper.lexemeToReal($REAL_CONSTANT.text),$REAL_CONSTANT.getLine(),
                                          $REAL_CONSTANT.getCharPositionInLine() + 1);}
          | CHAR_CONSTANT {$ast = new CharLiteral(LexerHelper.lexemeToChar($CHAR_CONSTANT.text),$CHAR_CONSTANT.getLine(),
                                           $CHAR_CONSTANT.getCharPositionInLine() + 1);}
          | '('expression')'
          | '['expression']'
          | expression '.' ID
          | '('expression 'as' SIMPLE_TYPE')'
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

definition: var_definition
        | function_definition
        ;

function_definition: function_type '{' var_definition* statement* '}'
                    ;

function_type: 'function' ID '(' (ID ':' SIMPLE_TYPE (',' ID ':' SIMPLE_TYPE)*)?  ')'
             | 'function' ID '('  (ID ':' SIMPLE_TYPE (',' ID ':' SIMPLE_TYPE)*)?  ')' ':' type
             ;

var_definition: 'let' ID (',' ID)* ':' type ';'
                ;

type: SIMPLE_TYPE
    | 'void'
    | ('('INT_CONSTANT')') type
    | '['('let' ID ':' type ';')*']'
    | ('['INT_CONSTANT']')+ SIMPLE_TYPE
    ;

// ----------------------------------------------------------

SIMPLE_TYPE: 'int' | 'char' | 'number'
    ;

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

