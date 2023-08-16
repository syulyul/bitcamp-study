package bitcamp.report.handler;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/member/update")
public class MemberUpdateServlet implements Servlet {

  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;

  public MemberUpdateServlet(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {


    Member member = new Member();
    member.setNo(Integer.parseInt(request.getParameter("no")));
    member.setName(request.getParameter("name"));
    member.setPhone(request.getParameter("phone"));
    member.setPassword(request.getParameter("password"));
    member.setPosition(request.getParameter("position").charAt(0));

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
    out.println("<h1>직원 변경</h1>");

    try {
      if (memberDao.update(member) == 0) {
        out.println("<p>회원이 없습니다.</p>");
      } else {
        sqlSessionFactory.openSession(false).commit();
        out.println("<p>변경했습니다!</p>");
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }

}
