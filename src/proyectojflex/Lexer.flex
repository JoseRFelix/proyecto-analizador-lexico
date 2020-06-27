package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
D=[0-9]+

espacio=[ ,\t,\r]+
newline=[\n]+
%{
    public String lexeme;
%}
%%
int |
if |
else |
while {lexeme=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
{newline} {lexeme=yytext(); return Newline;}
"//".* {/*Ignore*/}
"=" {return Igual;}
"+" {return Suma;}
"-" {return Resta;}
"*" {return Multiplicacion;}
"/" {return Division;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}

 . {return ERROR;}