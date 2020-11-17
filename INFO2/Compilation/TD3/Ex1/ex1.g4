lexer grammar ex1;
ATN: 'ATN' { System.out.println("ATnet");};
CATCH: .-> skip;
OTHERCARAC:([a-zA-Z_][a-zA-Z_0-9]*) -> skip;