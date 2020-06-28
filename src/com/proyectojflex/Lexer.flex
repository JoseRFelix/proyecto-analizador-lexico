package com.proyectojflex;
import static com.proyectojflex.Tokens.*;

%%

%class Lexer
%type Tokens
D=[0-9]+
SUMA=[\+]{1}
RESTA=[\-]{1}
IGUAL=[\=]{1}
MULTIPLICACION=[\*]{1}
DIVISION=[\/]{1}
ESPACIO=[ ,\t,\r]+
NEWLINE=[\n]+

%{
    public String lexeme;
%}

%%

int |
if |
else |
while {lexeme=yytext(); return RESERVADAS;}
"//".* {/*Ignore*/}
{ESPACIO} {/*Ignore*/}
{NEWLINE} {lexeme=yytext(); return NEWLINE;}
{IGUAL} {return IGUAL;}
{SUMA} {return SUMA;}
{RESTA} {return RESTA;}
{MULTIPLICACION} {return MULTIPLICACION;}
{DIVISION} {return DIVISION;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return NUMERO;}

 . {return ERROR;}