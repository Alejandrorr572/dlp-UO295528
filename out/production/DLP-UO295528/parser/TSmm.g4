grammar TSmm;	

program: definition* EOF
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

expression: ID
          | INT_CONSTANT
          | REAL_CONSTANT
          | CHAR_CONSTANT
          | '('expression')'
          | '['expression']'
          | expression '.' ID
          | '('expression 'as' SIMPLE_TYPE')'
          | ID'('(expression (','expression)*)?')'
          | '-' expression
          | '!' expression
          | expression ('*'|'/'|'%') expression
          | expression ('-'|'+') expression
          | expression ('>'|'<'|'>='|'<='|'=='|'!=') expression
          | expression ('&&'|'||') expression
          | expression '[' expression ']'
          ;

definition: var_definition
        | function_definition
        ;

function_definition: 'function' ID '(' (ID ':' SIMPLE_TYPE (',' ID ':' SIMPLE_TYPE)*)?  ')'
                    '{' var_definition* statement* '}'
             | 'function' ID '('  (ID ':' SIMPLE_TYPE (',' ID ':' SIMPLE_TYPE)*)?  ')' ':' type
                    '{' var_definition* statement* '}'
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

