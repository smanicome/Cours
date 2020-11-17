lexer grammar pw2_ex1;
/** A TIME is three fields with two digits each */

@lexer::members {static int count=1;}
TIME : [0-9][0-9] ':' [0-9][0-9] ':' [0-9][0-9]
{ System.out.println("line " + count + ", "+getText());}
 ;

EOL  :   [\n] {count++;};
