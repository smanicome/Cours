lexer grammar ex1;
BRACKET:'['~[[]* ']';
REST: . ->skip;
NORMAL_ID:([a-zA-Z_][a-zA-Z_0-9]*) -> skip;