grammar TSmm;

@header{
import ast.*;
import ast.expressions.*;
import ast.statements.*;
import ast.definitions.*;
import ast.types.*;
}

program returns [Program ast] locals [List<Definition> defs = new ArrayList<>()]:
        (d=definition { $defs.addAll($d.ast); })* EOF
        {
            $ast = new Program($defs);
        }
    ;

statement returns [List<Statement> ast] locals [List<Expression> params = new ArrayList<>()]:
    LOG='log' e1=expression
    {
        $ast = new ArrayList<>();
        $ast.add(new Log($e1.ast, $LOG.getLine(), $LOG.getCharPositionInLine() + 1));
    }
    (',' e2=expression
    {
        $ast.add(new Log($e2.ast, $LOG.getLine(), $LOG.getCharPositionInLine() + 1));
    })* ';'

    | INPUT='input' e1=expression {$params.add($e1.ast);} (',' e2=expression {$params.add($e2.ast);} )* ';'
    {
        $ast = new ArrayList<>();
        $ast.add(new Read($params, $INPUT.getLine(), $INPUT.getCharPositionInLine() + 1));
    }

    | e1=expression '=' e2=expression ';'
    {
        $ast = new ArrayList<>();
        $ast.add(new Assignment($e1.ast, $e2.ast, $e1.ast.getLine(), $e1.ast.getColumn()));
    }

    | 'if' '(' e1=expression ')' b1=block
     {
             $ast = new ArrayList<>();
             IfElse ifNode = new IfElse($e1.ast, $b1.ast, $e1.ast.getLine(), $e1.ast.getColumn());

     }
     ('else' b2=block
     {
            ifNode.addElseBody($b2.ast);
     })?
     {$ast.add(ifNode);}

    | 'while' '(' e1=expression ')' b1=block
    {
        $ast = new ArrayList<>();
        $ast.add(new While($e1.ast, $b1.ast, $e1.ast.getLine(), $e1.ast.getColumn()));
    }

    | 'return' e1=expression ';'
    {
        $ast = new ArrayList<>();
        $ast.add(new Return($e1.ast, $e1.ast.getLine(), $e1.ast.getColumn()));
    }

    | ID '(' (e1=expression {$params.add($e1.ast);} (',' e2=expression {$params.add($e2.ast);})*)? ')' ';'
    {
        $ast = new ArrayList<>();
        $ast.add(new FunctionCall($ID.text, $params, $ID.getLine(), $ID.getCharPositionInLine() + 1));
    }
    ;

block returns [List<Statement> ast] locals[List<Statement> stmts = new ArrayList<>()]:
    '{' (st=statement { $stmts.addAll($st.ast); })* '}' { $ast = $stmts; }
    | st=statement { $stmts.addAll($st.ast); $ast = $stmts; }
    ;

expression returns [Expression ast] locals [List<Expression> params = new ArrayList<>()]:
          ID
          {
            $ast = new Variable($ID.text, $ID.getLine(), $ID.getCharPositionInLine()+1);
          }

          | INT_CONSTANT
          {
          $ast = new IntLiteral(LexerHelper.lexemeToInt($INT_CONSTANT.text),$INT_CONSTANT.getLine(),
                                $INT_CONSTANT.getCharPositionInLine() + 1);
          }

          | REAL_CONSTANT
          {
          $ast = new DoubleValue(LexerHelper.lexemeToReal($REAL_CONSTANT.text),$REAL_CONSTANT.getLine(),
                                          $REAL_CONSTANT.getCharPositionInLine() + 1);
          }

          | CHAR_CONSTANT
          {
            $ast = new CharLiteral(LexerHelper.lexemeToChar($CHAR_CONSTANT.text),$CHAR_CONSTANT.getLine(),
                                           $CHAR_CONSTANT.getCharPositionInLine() + 1);
          }

          | '('e1=expression')'
          {
            $ast = $e1.ast;
          }

          | e1=expression '.' ID
          {
            $ast = new FieldAccess($e1.ast,$ID.text,
                $e1.ast.getLine(), $e1.ast.getColumn() + 1);
          }

          | e1=expression '[' e2=expression ']'
          {
            $ast = new ArrayAccess($e1.ast,$e2.ast,
                          $e1.ast.getLine(),$e1.ast.getColumn());
          }

          | '('e1=expression 'as' simple_type')'
          {
            $ast = new Cast($simple_type.ast,$e1.ast,
                    $e1.ast.getLine(),$e1.ast.getColumn());
          }

          | ID'('(e1=expression
          {
            $params.add($e1.ast);
          }
          (','e2=expression
          {
          $params.add($e2.ast);
          }
          )*)?')'
          {
            $ast = new FunctionCall($ID.text,
                $params,
                $ID.getLine(),
                $ID.getCharPositionInLine() + 1);
          }

          | '-' expression
          {
             $ast = new UnaryMinus($expression.ast, $expression.ast.getLine(),
                $expression.ast.getColumn());
          }
          | '!' expression
          {
             $ast = new UnaryNot($expression.ast, $expression.ast.getLine(),
                                           $expression.ast.getColumn());
          }

          | e1=expression OP=('*'|'/'|'%') e2=expression
          {
            $ast = new ArithmeticOperation($e1.ast, $OP.text, $e2.ast,
                              $e1.ast.getLine(), $e1.ast.getColumn() + 1);
            }

          | e1=expression OP=('-'|'+') e2=expression
          {
            $ast = new ArithmeticOperation($e1.ast, $OP.text, $e2.ast,
                $e1.ast.getLine(), $e1.ast.getColumn() + 1);
          }

          | e1=expression OP=('>'|'<'|'>='|'<='|'=='|'!=') e2=expression
          {
            $ast = new ComparisonOperation($e1.ast, $OP.text, $e2.ast,
                 $e1.ast.getLine(), $e1.ast.getColumn() + 1);
          }

          | e1=expression OP=('&&'|'||') e2=expression
          {
            $ast = new LogicOperation($e1.ast, $OP.text, $e2.ast,
                        $e1.ast.getLine(), $e1.ast.getColumn() + 1);
          }
          ;

definition returns [List<Definition> ast]:
    var_definitions {
        $ast = $var_definitions.ast;
    }
    | function_definition {
        $ast = new ArrayList<>();
        $ast.add($function_definition.ast);
    }
    ;

function_definition returns [Definition ast] locals [List<Statement> body = new ArrayList<>()]:
    'function' ID function_type '{'
    (vd=var_definitions {
        for(Definition def : $vd.ast) {
            $body.add((Statement) def);
        }
    })*
    (st=statement { $body.addAll($st.ast); })* '}'

    {
        $ast = new FunctionDefinition($ID.text, $function_type.ast, $body, $ID.getLine(), $ID.getCharPositionInLine() + 1);
    }
    ;

function_type returns [FunctionType ast] locals [List<VarDefinition> params = new ArrayList<>()]:
               '(' (ID1=ID ':' t1=simple_type
                {$params.add(new VarDefinition($t1.ast,$ID1.text, $ID1.getLine(), $ID1.getCharPositionInLine() + 1));}
                (',' ID2=ID ':' t2=simple_type
                {$params.add(new VarDefinition($t2.ast,$ID2.text, $ID2.getLine(), $ID2.getCharPositionInLine() + 1));}
                )*)? ')'
                {$ast = new FunctionType($params,VoidType.getInstance());}

             | '('  (ID1=ID ':' t1=simple_type
               {$params.add(new VarDefinition($t1.ast,$ID1.text, $ID1.getLine(), $ID1.getCharPositionInLine() + 1));}
               (',' ID2=ID ':' t2=simple_type
               {$params.add(new VarDefinition($t2.ast,$ID2.text, $ID2.getLine(), $ID2.getCharPositionInLine() + 1));}
               )*)?  ')' ':' type
               {$ast = new FunctionType($params,$type.ast);}
             ;

var_definitions returns [List<Definition> ast] locals [List<String> ids = new ArrayList<>()]:
                LET='let' ID1=ID {$ids.add($ID1.text);} (',' ID2=ID{$ids.add($ID2.text);})* ':' type ';'
                {$ast = new ArrayList<>();
                    for(String id: $ids){
                        $ast.add(new VarDefinition($type.ast, id, $LET.getLine(), $LET.getCharPositionInLine() + 1));
                    }
                }
                ;

type returns [Type ast] locals [int dim = 1, List<RecordField> records = new ArrayList<>()]:
    simple_type {$ast = $simple_type.ast;}
    | 'void'  {$ast = VoidType.getInstance();}
    | '['('let' ID ':' type ';' {$records.add(new RecordField($type.ast,$ID.text));})*']'
        {$ast = new RecordType($records);}
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

