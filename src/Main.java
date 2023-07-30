import lexer.Lexer;
import lexer.Token;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var lexer = new Lexer();
        try (var test = new FileInputStream("src\\test")) {
            // 输入流重定向
            System.setIn(test);
            // 每次调用 scan 方法会返回下一个词法单元
            while (true) {
                Token t = lexer.scan();
                if (t.tag == -1) {
                    break;
                }
                System.out.println(t);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
