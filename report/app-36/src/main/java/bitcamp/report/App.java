package bitcamp.report;

import bitcamp.report.dao.BoardDao;
import bitcamp.report.dao.BoardListDao;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.dao.ItemListDao;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.dao.MemberListDao;
import bitcamp.report.handler.BoardAddListener;
import bitcamp.report.handler.BoardDeleteListener;
import bitcamp.report.handler.BoardDetailListener;
import bitcamp.report.handler.BoardListListener;
import bitcamp.report.handler.BoardUpdateListener;
import bitcamp.report.handler.FooterListener;
import bitcamp.report.handler.HeaderListener;
import bitcamp.report.handler.HelloListener;
import bitcamp.report.handler.ItemAddListener;
import bitcamp.report.handler.ItemDeleteListener;
import bitcamp.report.handler.ItemDetailListener;
import bitcamp.report.handler.ItemListListener;
import bitcamp.report.handler.ItemUpdateListener;
import bitcamp.report.handler.MemberAddListener;
import bitcamp.report.handler.MemberDeleteListener;
import bitcamp.report.handler.MemberDetailListener;
import bitcamp.report.handler.MemberListListener;
import bitcamp.report.handler.MemberUpdateListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class App {

  MemberDao memberDao = new MemberListDao("member.json");
  ItemDao itemDao = new ItemListDao("item.json");
  BoardDao boardDao = new BoardListDao("board.json");
  BoardDao noticeDao = new BoardListDao("notice.json");

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public App() {
    prepareMenu();
  }

  public static void main(String[] args) {
    new App().execute();
  }

  static void printTitle() {
    System.out.println("마트 관리 시스템");
    System.out.println("----------------------------------------");
  }

  public void execute() {
    printTitle();

    mainMenu.execute(prompt);

    prompt.close();
  }

  private void prepareMenu() {

    MenuGroup memberMenu = new MenuGroup("직원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberDao)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberDao)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberDao)));
    mainMenu.add(memberMenu);

    MenuGroup itemMenu = new MenuGroup("물품");
    itemMenu.add(new Menu("등록", new ItemAddListener(itemDao)));
    itemMenu.add(new Menu("목록", new ItemListListener(itemDao)));
    itemMenu.add(new Menu("조회", new ItemDetailListener(itemDao)));
    itemMenu.add(new Menu("변경", new ItemUpdateListener(itemDao)));
    itemMenu.add(new Menu("삭제", new ItemDeleteListener(itemDao)));
    mainMenu.add(itemMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardDao)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardDao)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardDao)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardDao)));
    mainMenu.add(boardMenu);

    MenuGroup noticeMenu = new MenuGroup("공지");
    noticeMenu.add(new Menu("등록", new BoardAddListener(noticeDao)));
    noticeMenu.add(new Menu("목록", new BoardListListener(noticeDao)));
    noticeMenu.add(new Menu("조회", new BoardDetailListener(noticeDao)));
    noticeMenu.add(new Menu("변경", new BoardUpdateListener(noticeDao)));
    noticeMenu.add(new Menu("삭제", new BoardDeleteListener(noticeDao)));
    mainMenu.add(noticeMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);
  }
}
