package bitcamp.report.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.report.dao.BoardDao;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.dao.MySQLBoardDao;
import bitcamp.report.dao.MySQLItemDao;
import bitcamp.report.dao.MySQLMemberDao;
import bitcamp.util.AbstractServlet;
import bitcamp.util.SqlSessionFactoryProxy;

@WebServlet("/init")
public class InitServlet extends AbstractServlet {

  public static SqlSessionFactory sqlSessionFactory;
  public static BoardDao boardDao;
  public static MemberDao memberDao;
  public static ItemDao itemDao;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    sqlSessionFactory = new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("bitcamp/report/config/mybatis-config.xml")));

    boardDao = new MySQLBoardDao(sqlSessionFactory);
    memberDao = new MySQLMemberDao(sqlSessionFactory);
    itemDao = new MySQLItemDao(sqlSessionFactory);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>준비</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>애플리케이션 준비!</h1>");
    out.println("<p>애플리케이션을 실행할 준비를 완료했습니다!<p>");
    out.println("</body>");
    out.println("</html>");
  }
}
