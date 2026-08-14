grammar CartLang;

@parser::header {
package cartlang.parser;
}

@lexer::header {
package cartlang.parser;
}

// --- ANÁLISE SINTÁTICA ---
program
    : statement* EOF
    ;


statement
    : varDecl
    | clienteDecl   
    | assignment
    | itemStmt
    | resumoStmt
    | ifStmt
    | whileStmt
    | block
    ;

varDecl
    : type ID ';'
    | type ID '=' expr ';'
    ;

clienteDecl
    : 'cliente' '(' ID ')' '=' expr ';'
    ;

type
    : 'int'
    | 'float'
    | 'string'
    ;

assignment
    : ID '=' expr ';'
    ;

itemStmt
    : 'item' '(' ID ')' expr ',' expr ';'
    ;

resumoStmt
    : 'resumo' (ID)? ';'
    ;

ifStmt
    : 'if' '(' expr ')' statement ('else' statement)?
    ;

whileStmt
    : 'while' '(' expr ')' statement
    ;

block
    : '{' statement* '}'
    ;

expr
    : expr op=('*' | '/') expr      # OpExpr
    | expr op=('+' | '-') expr      # OpExpr
    | expr op=('>' | '<' | '==') expr # OpExpr
    | ID                            # VarExpr
    | INT                           # IntExpr
    | FLOAT                         # FloatExpr
    | STRING                        # StringExpr
    | '(' expr ')'                  # ParensExpr
    ;

// --- ANÁLISE LÉXICA ---
INT     : [0-9]+ ;
FLOAT   : [0-9]+ '.' [0-9]+ ;
STRING  : '"' (~["\r\n])* '"' ;
ID      : [a-zA-Z_][a-zA-Z0-9_]* ;

WS      : [ \t\r\n]+ -> skip ;
COMMENT : '//' ~[\r\n]* -> skip ;