package bitcamp.util;

import java.io.InputStream;
import java.util.Scanner;

public class Prompt {

  private Scanner scanner;

  // default constructor
  public Prompt() {
    this.scanner = new Scanner(System.in);
  }

  // 다른 입력 도구와 연결한다면
  public Prompt(InputStream in) {
    this.scanner = new Scanner(in);
  }

  public String inputString(String title, Object... args) {
    // 가변 파라미터 : string 다음 0개 이상이 올 수 있음
    System.out.printf(title, args);
    return this.scanner.nextLine();
  }

  public int inputInt(String title, Object... args) {
    // System.out.print(title);
    return Integer.parseInt(this.inputString(title, args));
  }

  public void close() {
    this.scanner.close();
  }
}
