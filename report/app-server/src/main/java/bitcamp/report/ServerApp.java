package bitcamp.report;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.util.ApplicationContext;
import bitcamp.util.DispatcherListener;
import bitcamp.util.SqlSessionFactoryProxy;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.NettyOutbound;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

public class ServerApp {

  ApplicationContext iocContainer;
  DispatcherListener facadeListener;

  int port;

  public ServerApp(int port) throws Exception {
    this.port = port;
    // iocContainer = new ApplicationContext(AppConfig.class);
    // facadeListener = new DispatcherListener(iocContainer);
  }

  public void close() throws Exception {

  }

  public static void main(String[] args) throws Exception {

    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();

  }

  public void execute() throws Exception {
    Path file = Paths.get(ServerApp.class.getResource("/static/index.html").toURI());
    DisposableServer server = HttpServer.create().port(8888)
        .handle((request, response) -> processRequest(request, response))
        // .route(routes -> routes // 클라이언트 요청을 처리할 객체를 등록한다.
        // .get("/hello", (request, response) -> response.sendString(Mono.just("Hello, world!")))
        // .get("/board/list", (request, response) -> response.sendString(Mono.just("게시글 목록")))
        // .get("/board/add", (request, response) -> response.sendString(Mono.just("게시글 등록")))
        // .get("/board/detail", (request, response) -> response.sendString(Mono.just("게시글 조회")))
        // .file("/index.html", file)
        // )
        .bindNow();
    System.out.println("서버 실행됨!");

    server.onDispose().block();
    System.out.println("서버 종료됨!");
  }


  private NettyOutbound processRequest(HttpServerRequest request, HttpServerResponse response) {
    try {
      return response.sendString(Mono.just("hello, world!"));

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();

    } finally {
      SqlSessionFactoryProxy sqlSessionFactoryProxy =
          (SqlSessionFactoryProxy) iocContainer.getBean(SqlSessionFactory.class);
      sqlSessionFactoryProxy.clean();
    }
    return null;
  }
}

