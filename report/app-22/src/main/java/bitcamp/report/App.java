package bitcamp.report;

import bitcamp.report.handler.BoardHandler;
import bitcamp.report.handler.Handler;
import bitcamp.report.handler.ItemHandler;
import bitcamp.report.handler.MemberHandler;
import bitcamp.util.ArrayList;
import bitcamp.util.LinkedList;
import bitcamp.util.MenuPrompt;

public class App {

  public static void main(String[] args) {

    MenuPrompt prompt = new MenuPrompt();
    prompt.appendBreadcrumbs("메인", getMenu());

    // 모든 핸들러는 Handler 규칙에 따라 정의되었기 때문에
    // Handler 레퍼런스에 그 주소를 담을 수 있다.
    Handler memberHandler = new MemberHandler(prompt, "직원", new ArrayList());
    Handler itemHandler = new ItemHandler(prompt, "물품", new ArrayList());
    Handler boardHandler = new BoardHandler(prompt, "게시글", new LinkedList());
    Handler noticeHandler = new BoardHandler(prompt, "공지", new LinkedList());

    printTitle();

    prompt.printMenu();

    loop: while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0": break loop;
        case "1": memberHandler.execute(); break;
        case "2": itemHandler.execute(); break;
        case "3": boardHandler.execute(); break;
        case "4": noticeHandler.execute(); break;
      }
    }

    prompt.close();
  }

  static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 직원\n");
    menu.append("2. 물품\n");
    menu.append("3. 게시글\n");
    menu.append("4. 공지\n");
    menu.append("0. 종료\n");
    return menu.toString();
  }

  static void printTitle() {
    System.out.println("마트 관리 시스템");
    System.out.println("---------------------------------------------------------------------");
  }
}
