lexer grammar pw2_ex1;
/** A TIME is three fields with two digits each */
@lexer::members {
    static int count =1;
}
TIME : [0][3] ':' [0-9][0-9] ':' [0-9][0-9] | [0][4] ':' [0][0] ':' [0][0]
{ System.out.println("line "+ count + ", " + getText());}
 ;
OTHER : (~[0-9 \t\r\n]+ | ~[: \t\r\n]+) ->skip;
WS  :   [ \t\r]+ -> skip;
EOL  :   [\n] {count++;}->skip ;
