package lexer;

import java.io.IOException;
import java.util.HashMap;

public class Lexer {
    private int line;

    private char peek;

    private HashMap<String, Word> words = new HashMap<>();

    public Lexer() {
        line = 1;
        peek = ' ';
        reserve(new Word(Tag.IF,    "if"));
        reserve(new Word(Tag.ELSE,  "else"));
        reserve(new Word(Tag.WHILE, "while"));
        reserve(new Word(Tag.DO,    "do"));
        reserve(new Word(Tag.BREAK, "break"));
        reserve(Word.True);
        reserve(Word.False);
    }

    private void reserve(Word word) {
        words.put(word.lexeme, word);
    }

    public Token scan() throws IOException {
        // 跳过空白字符
        while (true) {
            if (peek == ' ' || peek == '\t' || peek == '\r') {
                readch();
            } else if (peek == '\n') {
                line += 1;
                readch();
            } else {
                break;
            }
        }
        // 文件末尾
        if (peek == (char) -1) {
            return new Token(-1);
        }
        // 需要预读的词法单元
        switch (peek) {
            case '&':
                return readch('&') ? Word.and : new Token('&');
            case '|':
                return readch('|') ? Word.or  : new Token('|');
            case '=':
                return readch('=') ? Word.eq  : new Token('=');
            case '!':
                return readch('=') ? Word.ne  : new Token('!');
            case '<':
                return readch('=') ? Word.le  : new Token('<');
            case '>':
                return readch('=') ? Word.ge  : new Token('>');
        }
        // 识别整数和浮点数
        if (Character.isDigit(peek)) {
            int value = 0;
            do {
                value = 10 * value + Character.digit(peek, 10);
                readch();
            } while (Character.isDigit(peek));
            if (peek != '.') {
                return new Num(value);
            }
            float f = value;
            float d = 10.0f;
            while (true) {
                readch();
                if (!Character.isDigit(peek)) {
                    break;
                }
                f += Character.digit(peek, 10) / d;
                d *= 10;
            }
            return new Real(f);
        }
        // 识别标识符和关键字
        if (Character.isLetter(peek)) {
            StringBuffer sb = new StringBuffer();
            do {
                sb.append(peek);
                readch();
            } while (Character.isLetterOrDigit(peek));
            String s = sb.toString();
            Word word = words.get(s);
            // 已定义的标识符或关键字
            if (word != null) {
                return word;
            }
            // 未定义的标识符
            word = new Word(Tag.ID, s);
            words.put(s, word);
            return word;
        }
        // 其他词法单元
        Token tok = new Token(peek);
        peek = ' ';
        return tok;
    }

    private void readch() throws IOException {
        peek = (char) System.in.read();
    }

    private boolean readch(char c) throws IOException {
        readch();
        if (peek != c) {
            return false;
        }
        peek = ' ';
        return true;
    }
}
