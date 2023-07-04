package bitcamp.report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import bitcamp.report.vo.AutoIncrement;
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
    loadJson("member.json", memberList, Member.class);
    loadJson("item.json", itemList, Item.class);
    loadJson("board.json", boardList, Board.class);
    loadJson("notice.json", noticeList, Board.class);
  }

  private void saveData() {
    saveJson("member.json", memberList);
    saveJson("item.json", itemList);
    saveJson("board.json", boardList);
    saveJson("notice.json", noticeList);
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

  private <T> void loadJson(String filename, List<T> list, Class<T> clazz) {
    try {

      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      StringBuilder strBuilder = new StringBuilder();
      String line = null;

      while ((line = in.readLine()) != null) {
        strBuilder.append(line);
      }

      in.close();

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

      Collection<T> objects = gson.fromJson(strBuilder.toString(),
          TypeToken.getParameterized(Collection.class, clazz).getType());

      list.addAll(objects);

      Class<?>[] interfaces = clazz.getInterfaces();
      for (Class<?> info : interfaces) {
        if (info == AutoIncrement.class) {
          AutoIncrement autoIncrement = (AutoIncrement) list.get(list.size() - 1);
          autoIncrement.updateKey();
          break;
        }
      }

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void saveJson(String filename, List<?> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out = new BufferedWriter(out0); // <== decorator 역할 수행

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
      out.write(gson.toJson(list));

      out.close();
    } catch (Exception e) {
      System.out.println(filename + " 파일 저장하는 중 오류 발생!");
    }
  }

}
