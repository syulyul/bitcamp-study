package bitcamp.report;

import bitcamp.report.handler.BoardHandler;
import bitcamp.report.handler.Handler;
import bitcamp.report.handler.ItemHandler;
import bitcamp.report.handler.MemberHandler;
import bitcamp.util.ArrayList;
import bitcamp.util.LinkedList;
import bitcamp.util.Prompt;

public class App {

  public static void main(String[] args) {

    // 기본 생성자를 이용해 Prompt 인스턴스를 준비
    // => 기본 생성자는 Scanner를 키보드와 연결함
    Prompt prompt = new Prompt();

    // 모든 핸들러는 Handler 규칙에 따라 정의되었기 때문에
    // Handler 레퍼런스에 그 주소를 담을 수 있다.
    Handler memberHandler = new MemberHandler(prompt, "직원", new ArrayList());
    Handler itemHandler = new ItemHandler(prompt, "물품", new ArrayList());
    Handler boardHandler = new BoardHandler(prompt, "게시글", new LinkedList());
    Handler noticeHandler = new BoardHandler(prompt, "공지", new LinkedList());

    printTitle();

    printMenu();

    while (true) {
      String menuNo = prompt.inputString("메인> ");
      if (menuNo.equals("0")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        memberHandler.execute();
      } else if (menuNo.equals("2")) {
        itemHandler.execute();
      } else if (menuNo.equals("3")) {
        boardHandler.execute();
      } else if (menuNo.equals("4")) {
        noticeHandler.execute();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }

    prompt.close();
  }

  static void printMenu() {
    System.out.println("1. 직원");
    System.out.println("2. 물품");
    System.out.println("3. 게시글");
    System.out.println("4. 공지");
    System.out.println("0. 종료");
  }

  static void printTitle() {
    System.out.println("마트 관리 시스템");
    System.out.println("---------------------------------------------------------------------");
  }
}
