package bitcamp.report;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import bitcamp.dao.MySQLBoardDao;
import bitcamp.dao.MySQLItemDao;
import bitcamp.dao.MySQLMemberDao;
import bitcamp.net.NetProtocol;
import bitcamp.report.dao.BoardDao;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.dao.MemberDao;
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
import bitcamp.report.handler.LoginListener;
import bitcamp.report.handler.MemberAddListener;
import bitcamp.report.handler.MemberDeleteListener;
import bitcamp.report.handler.MemberDetailListener;
import bitcamp.report.handler.MemberListListener;
import bitcamp.report.handler.MemberUpdateListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class ServerApp {

  // 자바 스레드풀 준비
  ExecutorService threadPool = Executors.newFixedThreadPool(2);

  DataSource ds = new DataSource("jdbc:mysql://localhost:3306/studydb", "study", "1111");

  MemberDao memberDao;
  ItemDao itemDao;
  BoardDao boardDao;
  BoardDao noticeDao;

  MenuGroup mainMenu = new MenuGroup("메인");

  int port;

  public ServerApp(int port) throws Exception {

    this.port = port;

    this.memberDao = new MySQLMemberDao(ds);
    this.boardDao = new MySQLBoardDao(ds, 1);
    this.noticeDao = new MySQLBoardDao(ds, 2);
    this.itemDao = new MySQLItemDao(ds);

    prepareMenu();
  }

  public void close() throws Exception {

  }

  public static void main(String[] args) throws Exception {

    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();

  }

  public void execute() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행 중...");

      while (true) {
        Socket socket = serverSocket.accept();
        threadPool.execute(() -> processRequest(socket));
      }

    } catch (Exception e) {
      System.out.println("서버 실행 오류!");
      e.printStackTrace();
    }
  }


  private void processRequest(Socket socket) {
    try (DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      BreadcrumbPrompt prompt = new BreadcrumbPrompt(in, out);

      InetSocketAddress clientAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
      System.out.printf("%s 클라이언트 접속함!\n", clientAddress.getHostString());

      out.writeUTF("[마트 관리 시스템]\n" 
          + "------------------------------------------");

      new LoginListener(memberDao).service(prompt);

      mainMenu.execute(prompt);
      out.writeUTF(NetProtocol.NET_END);

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();
    } finally {
      ds.clean();
    }
  }


  private void prepareMenu() {

    MenuGroup memberMenu = new MenuGroup("직원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberDao, ds)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberDao, ds)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberDao, ds)));
    mainMenu.add(memberMenu);

    MenuGroup itemMenu = new MenuGroup("물품");
    itemMenu.add(new Menu("등록", new ItemAddListener(itemDao, ds)));
    itemMenu.add(new Menu("목록", new ItemListListener(itemDao)));
    itemMenu.add(new Menu("조회", new ItemDetailListener(itemDao)));
    itemMenu.add(new Menu("변경", new ItemUpdateListener(itemDao, ds)));
    itemMenu.add(new Menu("삭제", new ItemDeleteListener(itemDao, ds)));
    mainMenu.add(itemMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardDao, ds)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardDao)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardDao, ds)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardDao, ds)));
    mainMenu.add(boardMenu);

    MenuGroup noticeMenu = new MenuGroup("공지");
    noticeMenu.add(new Menu("등록", new BoardAddListener(noticeDao, ds)));
    noticeMenu.add(new Menu("목록", new BoardListListener(noticeDao)));
    noticeMenu.add(new Menu("조회", new BoardDetailListener(noticeDao)));
    noticeMenu.add(new Menu("변경", new BoardUpdateListener(noticeDao, ds)));
    noticeMenu.add(new Menu("삭제", new BoardDeleteListener(noticeDao, ds)));
    mainMenu.add(noticeMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);
  }
}

