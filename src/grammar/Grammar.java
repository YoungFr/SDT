package grammar;

// id     -> letter (letter | digit)*
// num    -> digits
// real   -> digits . digits
// digits -> digit+
// digit  -> [0-9]
// letter -> [A-Za-z]
// basic  -> int
//          | char
//          | bool
//          | float

// program -> block
// block   -> { decls stmts }
// decls   -> decls decl
//          | ɛ
// decl    -> type id ;
// type    -> type [ num ]
//          | basic
// stmts   -> stmts stmt
//          | ɛ
//
// stmt -> loc = bool ;
//       | if ( bool ) stmt
//       | if ( bool ) stmt else stmt
//       | while ( bool ) stmt
//       | do stmt while ( bool ) ;
//       | break ;
//       | block
// loc  -> loc [ bool ]
//       | id
//
// bool     -> bool || join
//           | join
// join     -> join && equality
//           | equality
// equality -> equality == rel
//           | equality != rel
//           | rel
// rel      -> expr < expr
//           | expr <= expr
//           | expr > expr
//           | expr >= expr
//           | expr
// expr     -> expr + term
//           | expr - term
//           | term
// term     -> term * unary
//           | term / unary
//           | unary
// unary    -> ! unary
//           | - unary
//           | factor
// factor   -> ( bool )
//           | loc
//           | num
//           | real
//           | true
//           | false
