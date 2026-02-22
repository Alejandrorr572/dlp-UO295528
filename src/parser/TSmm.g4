grammar TSmm;	

program: expression* EOF
       ;

statement: ;

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

definition: var_definition (',' var_definition)* ';'
        | function_definition
        ;

function_definition: 'function' ID '(' (SIMPLE_TYPE ID)* ')' body
             | 'function' ID '(' (SIMPLE_TYPE ID)* ')' ':' SIMPLE_TYPE body_return
            ;

body: '{' var_definition* statement* '}'
    | var_definition
    | statement
    ;

body: '{' var_definition* statement* 'return' expression ';' '}'
    | 'return' expression ';'
    ;

var_definition: 'let' ID ':' type
                ;

type: SIMPLE_TYPE
    | 'void'//*
    | ('('INT_CONSTANT')') type
    | '['(var_definition ',')*']'
    ;

// ----------------------------------------------------------

SIMPLE_TYPE: 'int' | 'string' | 'number'
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

