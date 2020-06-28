package com.proyectojflex;
import static com.proyectojflex.Tokens.*;

%%

%class Lexer
%type Tokens
D=[0-9]+
SUMA=[\+]
RESTA=[\-]
IGUAL=[\=]
MULTIPLICACION=[\*]
PARENTESIS=[\(\)]
DIVISION=[\/]
ESPACIO=[ ,\t,\r]+
NEWLINE=[\n]+
POTENCIA=[\^]

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
{PARENTESIS} {return PARENTESIS;}
{POTENCIA} {return POTENCIA;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return DIGITO;}

 . {return ERROR;}