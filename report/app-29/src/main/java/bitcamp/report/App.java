package bitcamp.report;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import bitcamp.io.BufferedDataInputStream;
import bitcamp.io.BufferedDataOutputStream;
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
    loadMember();
    loadItem();
    loadBoard("board.data", boardList);
    loadBoard("notice.data", noticeList);
  }

  private void saveData() {
    saveMember();
    saveItem();
    saveBoard("board.data", boardList);
    saveBoard("notice.data", noticeList);
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

  private void loadMember() {
    try {
      BufferedDataInputStream in = new BufferedDataInputStream("member.data");

      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.readInt());
        member.setName(in.readUTF());
        member.setPhone(in.readUTF());
        member.setPassword(in.readUTF());
        member.setPosition(in.readChar());

        memberList.add(member);
      }
      // 데이터를 로딩한 이후에 추가할 회원의 번호를 설정
      Member.userId = memberList.get(memberList.size() - 1).getNo() + 1;

      in.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 읽는 중 오류 발생!");
    }
  }

  private void loadItem() {
    try {
      BufferedDataInputStream in = new BufferedDataInputStream("item.data");

      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Item item = new Item();
        item.setNo(in.readInt());
        item.setName(in.readUTF());
        item.setPrice(in.readInt());
        item.setType(in.readUTF());

        itemList.add(item);
      }
      // 데이터를 로딩한 이후에 추가할 물품의 번호를 설정
      Item.itemId = itemList.get(itemList.size() - 1).getNo() + 1;

      in.close();

    } catch (Exception e) {
      System.out.println("물품 정보를 읽는 중 오류 발생!");
    }
  }

  private void loadBoard(String filename, List<Board> list) {
    try {
      BufferedDataInputStream in = new BufferedDataInputStream(filename);

      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNo(in.readInt());
        board.setTitle(in.readUTF());
        board.setContent(in.readUTF());
        board.setWriter(in.readUTF());
        board.setPassword(in.readUTF());
        board.setViewCount(in.readInt());
        board.setCreatedDate(in.readLong());

        list.add(board);
      }
      Board.boardNo = Math.max(Board.boardNo, list.get(list.size() - 1).getNo() + 1);

      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void saveMember() {
    try {
      BufferedDataOutputStream out = new BufferedDataOutputStream("member.data");

      // 저장할 데이터의 개수를 먼저 출력한다.
      out.writeShort(memberList.size());

      for (Member member : memberList) {
        out.writeInt(member.getNo());
        out.writeUTF(member.getName());
        out.writeUTF(member.getPhone());
        out.writeUTF(member.getPassword());
        out.writeChar(member.getPosition());
      }
      out.close();
    } catch (Exception e) {
      System.out.println("회원 정보를 저장하는 중 오류 발생!");
    }
  }

  private void saveItem() {
    try {
      BufferedDataOutputStream out = new BufferedDataOutputStream("item.data");

      // 저장할 데이터의 개수를 먼저 출력한다.
      out.writeShort(itemList.size());

      for (Item item : itemList) {
        out.writeInt(item.getNo());
        out.writeUTF(item.getName());
        out.writeInt(item.getPrice());
        out.writeUTF(item.getType());
      }
      out.close();
    } catch (Exception e) {
      System.out.println("물품 정보를 저장하는 중 오류 발생!");
    }
  }

  private void saveBoard(String filename, List<Board> list) {
    try {
      BufferedDataOutputStream out = new BufferedDataOutputStream(filename);

      // 저장할 데이터의 개수를 먼저 출력한다.
      out.writeShort(list.size());

      for (Board board : list) {
        out.writeInt(board.getNo());
        out.writeUTF(board.getTitle());
        out.writeUTF(board.getContent());
        out.writeUTF(board.getWriter());
        out.writeUTF(board.getPassword());
        out.writeInt(board.getViewCount());
        out.writeLong(board.getCreatedDate());
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + "파일을 저장하는 중 오류 발생!");
    }
  }
}
