package bitcamp.report;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
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
import bitcamp.report.handler.MemberAddListener;
import bitcamp.report.handler.MemberDeleteListener;
import bitcamp.report.handler.MemberDetailListener;
import bitcamp.report.handler.MemberListListener;
import bitcamp.report.handler.MemberUpdateListener;
import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class ServerApp {

  public static Member loginUser;

  Connection con;
  MemberDao memberDao;
  ItemDao itemDao;
  BoardDao boardDao;
  BoardDao noticeDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  int port;

  public ServerApp(int port) throws Exception {

    this.port = port;

    con = DriverManager.getConnection(
        "jdbc:mysql://study:1111@localhost:3306/studydb" // JDBC URL
    );

    this.memberDao = new MySQLMemberDao(con);
    this.boardDao = new MySQLBoardDao(con, 1);
    this.noticeDao = new MySQLBoardDao(con, 2);
    this.itemDao = new MySQLItemDao(con);

    prepareMenu();
  }
  
  public void close() throws Exception {
    con.close();
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
        try (Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

          InetSocketAddress clientAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
          System.out.printf("%s 클라이언트 접속함!\n", clientAddress.getHostString());

          out.writeUTF("[마트 관리 시스템]\n" 
              + "------------------------------------------");

          while (true) {
            mainMenu.execute(prompt);
            String request = in.readUTF();
            if (request.equals("exit")) {
              break;
            }

            out.writeUTF("응답1: " + request);
            out.writeUTF("응답2: " + request);
            out.writeUTF("응답3: " + request);
            out.writeUTF("응답4: " + request);
            out.writeUTF(NetProtocol.RESPONSE_END);
          }

        } catch (Exception e) {
          System.out.println("클라이언트 통신 오류!");
          e.printStackTrace();
        }
      }

    } catch (Exception e) {
      System.out.println("서버 실행 오류!");
      e.printStackTrace();
    }
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

