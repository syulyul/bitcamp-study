package bitcamp.report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import bitcamp.report.vo.Board;
import bitcamp.report.vo.Item;
import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class App {

  ArrayList<Member> memberList = new ArrayList<>();
  ArrayList<Item> itemList = new ArrayList<>();
  LinkedList<Board> boardList = new LinkedList<>();
  LinkedList<Board> noticeList = new LinkedList<>();

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

    loadData();

    mainMenu.execute(prompt);

    saveData();

    prompt.close();
  }

  private void loadData() {
    loadMember("member.csv", memberList);
    loadItem("item.csv", itemList);
    loadBoard("board.csv", boardList);
    loadBoard("notice.csv", noticeList);
  }

  private void saveData() {
    saveMember("member.csv", memberList);
    saveItem("item.csv", itemList);
    saveBoard("board.csv", boardList);
    saveBoard("notice.csv", noticeList);
  }

  private void prepareMenu() {

    MenuGroup memberMenu = new MenuGroup("직원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberList)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberList)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberList)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberList)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberList)));
    mainMenu.add(memberMenu);

    MenuGroup itemMenu = new MenuGroup("물품");
    itemMenu.add(new Menu("등록", new ItemAddListener(itemList)));
    itemMenu.add(new Menu("목록", new ItemListListener(itemList)));
    itemMenu.add(new Menu("조회", new ItemDetailListener(itemList)));
    itemMenu.add(new Menu("변경", new ItemUpdateListener(itemList)));
    itemMenu.add(new Menu("삭제", new ItemDeleteListener(itemList)));
    mainMenu.add(itemMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardList)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardList)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardList)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardList)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardList)));
    mainMenu.add(boardMenu);

    MenuGroup noticeMenu = new MenuGroup("공지");
    noticeMenu.add(new Menu("등록", new BoardAddListener(noticeList)));
    noticeMenu.add(new Menu("목록", new BoardListListener(noticeList)));
    noticeMenu.add(new Menu("조회", new BoardDetailListener(noticeList)));
    noticeMenu.add(new Menu("변경", new BoardUpdateListener(noticeList)));
    noticeMenu.add(new Menu("삭제", new BoardDeleteListener(noticeList)));
    mainMenu.add(noticeMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);
  }

  private void loadMember(String filename, List<Member> list) {
    try {
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      String line = null;

      while ((line = in.readLine()) != null) {
        String[] values = line.split(",");
        Member member = new Member();
        member.setNo(Integer.parseInt(values[0]));
        member.setName(values[1]);
        member.setPhone(values[2]);
        member.setPassword(values[3]);
        member.setPosition(values[4].charAt(0));
        list.add(member);
      }
      // 데이터를 로딩한 이후에 추가할 회원의 번호를 설정
      Member.userId = list.get(list.size() - 1).getNo() + 1;

      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void loadItem(String filename, List<Item> list) {
    try {
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      String line = null;

      while ((line = in.readLine()) != null) {
        String[] values = line.split(",");
        Item item = new Item();
        item.setNo(Integer.parseInt(values[0]));
        item.setName(values[1]);
        item.setPrice(Integer.parseInt(values[2]));
        item.setType(values[3]);
        list.add(item);
      }
      // 데이터를 로딩한 이후에 추가할 물품의 번호를 설정
      Item.itemId = itemList.get(itemList.size() - 1).getNo() + 1;

      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void loadBoard(String filename, List<Board> list) {
    try {
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      String line = null;

      while ((line = in.readLine()) != null) {
        Board board = new Board();
        String[] values = line.split(",");
        board.setNo(Integer.parseInt(values[0]));
        board.setTitle(values[1]);
        board.setContent(values[2]);
        board.setWriter(values[3]);
        board.setPassword(values[4]);
        board.setViewCount(Integer.parseInt(values[5]));
        board.setCreatedDate(Long.parseLong(values[6]));
        list.add(board);
      }
      Board.boardNo = Math.max(Board.boardNo, list.get(list.size() - 1).getNo() + 1);

      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void saveMember(String filename, List<Member> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== decorator 역할 수행
      PrintWriter out = new PrintWriter(out1); // <== Decorator(장식품) 역할

      for (Member member : list) {
        out.printf("%d,%s,%s,%s,%c\n", // 공백 띄우면 안됨
            member.getNo(), member.getName(), member.getPhone(), member.getPassword(),
            member.getPosition());
      }
      out.close();
    } catch (Exception e) {
      System.out.println(filename + " 파일 저장하는 중 오류 발생!");
    }
  }

  private void saveItem(String filename, List<Item> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator 역할을 수행!
      PrintWriter out = new PrintWriter(out1); // <== Decorator(장식품) 역할

      for (Item item : list) {
        out.printf("%d,%s,%d,%s\n", item.getNo(), item.getName(), item.getPrice(), item.getType());
      }
      out.close();
    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }

  private void saveBoard(String filename, List<Board> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator 역할을 수행!
      PrintWriter out = new PrintWriter(out1); // <== Decorator(장식품) 역할

      for (Board board : list) {
        out.printf("%d,%s,%s,%s,%s,%d,%d\n", board.getNo(), board.getTitle(), board.getContent(),
            board.getWriter(), board.getPassword(), board.getViewCount(), board.getCreatedDate());
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }
}
