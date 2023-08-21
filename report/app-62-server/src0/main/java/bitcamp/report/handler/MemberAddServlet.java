package bitcamp.report.handler;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/member/add")
public class MemberAddServlet implements Servlet {

  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;

  public MemberAddServlet(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member m = new Member();
    m.setName(request.getParameter("name"));
    m.setPhone(request.getParameter("phone"));
    m.setPassword(request.getParameter("password"));
    m.setPosition(request.getParameter("position").charAt(0));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<meta http-equiv='refresh' content='1;url=/member/list'>");
    out.println("<title>직원</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>직원 등록</h1>");

    try {
      memberDao.insert(m);
      sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}
