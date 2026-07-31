grammar ComprasDSL;

// ==========================================
// 1. REGRAS SINTÁTICAS (PARSER)
// ==========================================

// O programa é uma sequência de declarações ou comandos
program
    : statement* EOF
    ;

statement
    : varDecl
    | assignment
    | ifStmt
    | whileStmt
    | domainStmt
    | printStmt
    ;

// Declaração de variável
varDecl
    : type ID (ASSIGN expr)? SEMI
    ;

// Atribuição
assignment
    : ID ASSIGN expr SEMI
    ;

// Tipos obrigatórios (int, float, string)
type
    : INT_TYPE
    | FLOAT_TYPE
    | STRING_TYPE
    ;

// Estruturas de Controle 
ifStmt
    : IF LPAREN expr RPAREN LBRACE statement* RBRACE (ELSE LBRACE statement* RBRACE)?
    ;

whileStmt
    : WHILE LPAREN expr RPAREN LBRACE statement* RBRACE
    ;

// Comandos do Domínio (pelo menos 2 instruções nativas exclusivas)
domainStmt
    : ADD_ITEM LPAREN expr COMMA expr COMMA expr RPAREN SEMI   // addItem(nome, quantidade, preco)
    | SHOW_SUMMARY LPAREN RPAREN SEMI                          // showSummary()
    ;

// Comando auxiliar para impressão geral
printStmt
    : PRINT LPAREN expr RPAREN SEMI
    ;

// Expressões com precedência aritmética e lógica
expr
    : expr op=(MULT | DIV) expr        # MultiplicativeExpr
    | expr op=(PLUS | MINUS) expr      # AdditiveExpr
    | expr op=(GT | LT | GTE | LTE | EQ | NEQ) expr # RelationalExpr
    | LPAREN expr RPAREN               # ParenExpr
    | ID                               # VarExpr
    | INT_LITERAL                      # IntExpr
    | FLOAT_LITERAL                    # FloatExpr
    | STRING_LITERAL                   # StringExpr
    ;


// ==========================================
// 2. REGRAS LÉXICAS (LEXER)
// ==========================================

// Palavras Reservadas dos Tipos
INT_TYPE    : 'int' ;
FLOAT_TYPE  : 'float' ;
STRING_TYPE : 'string' ;

// Palavras Reservadas de Controle
IF    : 'if' ;
ELSE  : 'else' ;
WHILE : 'while' ;

// Palavras Reservadas do Domínio
ADD_ITEM     : 'addItem' ;
SHOW_SUMMARY : 'showSummary' ;
PRINT        : 'print' ;

// Símbolos e Operadores
ASSIGN : '=' ;
PLUS   : '+' ;
MINUS  : '-' ;
MULT   : '*' ;
DIV    : '/' ;

GT  : '>' ;
LT  : '<' ;
GTE : '>=' ;
LTE : '<=' ;
EQ  : '==' ;
NEQ : '!=' ;

LPAREN : '(' ;
RPAREN : ')' ;
LBRACE : '{' ;
RBRACE : '}' ;
SEMI   : ';' ;
COMMA  : ',' ;

// Identificadores (Nomes de variáveis)
ID : [a-zA-Z_][a-zA-Z0-9_]* ;

// Literais de Dados
INT_LITERAL    : [0-9]+ ;
FLOAT_LITERAL  : [0-9]+ '.' [0-9]+ ;
STRING_LITERAL : '"' (~["\r\n])* '"' ;

// Espaços em Branco e Comentários
WS : [ \t\r\n]+ -> skip ;
COMMENT : '//' ~[\r\n]* -> skip ;

// Captura de erro léxico (qualquer caractere não reconhecido acima)
UNKNOWN : . ;