package bitcamp.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Stateless 방식 + Session 으로 통신하기
public class CalcClient3 {

  static Pattern pattern = Pattern.compile("[0-9]+|\\p{Punct}"); // [...]+ 는 1개이상, * 0개 이상

  public static void main(String[] args) {
    String uuid = "";

    try (Scanner keyscan = new Scanner(System.in);) {
      while (true) {
        System.out.print("계산식(예: + 3)> ");
        String input = keyscan.nextLine();
        if (input.equals("quit")) {
          break;
        }

        Expression expr = null;
        try { // 계산식 잘못 입력해도 서버는 종료되지 않도록 함
          expr = parseExpression(input);
        } catch (ExpressionParseException e) {
          System.out.println(e.getMessage());
          continue;
        }

        try (Socket socket = new Socket("localhost", 8888); // 네트워크 연결
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());) {

          out.writeUTF(uuid);
          out.writeUTF(expr.op);
          out.writeInt(expr.value);

          uuid = in.readUTF();
          String result = in.readUTF();
          System.out.printf("결과: %s\n", result);

        } catch (Exception e) {
          System.out.println("서버 통신 오류!");
        }
      }
    }

  }

  public static Expression parseExpression(String expr) throws ExpressionParseException {
    try {
      Matcher matcher = pattern.matcher(expr);

      ArrayList<String> values = new ArrayList<>(); // 빈배열 준비
      while (matcher.find()) { // 패턴과 일치하는 거 찾아냄
        values.add(matcher.group()); // 몇 개든 일단 담는다
      }

      if (values.size() != 2) {
        throw new Exception("계산식이 옳지 않습니다!"); // RuntimeException 이므로 메서드 선언부에 표시 안 해도 됨
      }

      Expression obj = new Expression();
      obj.op = values.get(0);
      obj.value = Integer.parseInt(values.get(1));

      return obj;
    } catch (Exception e) {
      throw new ExpressionParseException(e);
    }
  }

  static class Expression {
    String op;
    int value;
  }
}
