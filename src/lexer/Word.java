package lexer;

public class Word extends Token {
    public String lexeme;

    public Word(int tag, String lexeme) {
        super(tag);
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return lexeme;
    }

    public static final Word and   = new Word(Tag.AND,   "&&");
    public static final Word or    = new Word(Tag.OR,    "||");
    public static final Word eq    = new Word(Tag.EQ,    "==");
    public static final Word ne    = new Word(Tag.NE,    "!=");
    public static final Word le    = new Word(Tag.LE,    "<=");
    public static final Word ge    = new Word(Tag.GE,    ">=");
    public static final Word minus = new Word(Tag.MINUS, "minus");
    public static final Word True  = new Word(Tag.TRUE,  "true");
    public static final Word False = new Word(Tag.FALSE, "false");
    public static final Word temp  = new Word(Tag.TEMP,  "t");
}
