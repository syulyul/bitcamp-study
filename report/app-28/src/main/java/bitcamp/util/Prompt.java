package bitcamp.util;

import java.io.InputStream;
import java.util.Scanner;

public class Prompt {

  private Scanner scanner;

  // defualt constructor
  public Prompt() {
    scanner = new Scanner(System.in);
  }

  // 다른 입력 도구와 연결한다면
  public Prompt(InputStream in) {
    scanner = new Scanner(in);
  }

  public String inputString(String title, Object... args) {
    System.out.printf(title, args);
    return this.scanner.nextLine();
  }

  public int inputInt(String title, Object... args) {
    return Integer.parseInt(this.inputString(title, args));
  }

  public void close() {
    this.scanner.close();
  }

}
