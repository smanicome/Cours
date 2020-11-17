lexer grammar ex1;
FRENCH : ('de' | 'à' | 'le' | 'la'  | 'et' | 'il' | 'les' | 'un' | 'en' | 'du') {System.out.println("french");};
ENG : ('the' | 'of' | 'and' | 'to'  | 'a' | 'his' | 'in' | 'with' | 'I' | 'which') {System.out.println("english");};
CATCH : . ->skip;
NORMAL_ID : [a-zA-Z_][a-zA-Z_0-9]* -> skip;