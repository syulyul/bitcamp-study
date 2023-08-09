package bitcamp.report.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.report.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

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
      if (InitServlet.memberDao.update(member) == 0) {
        out.println("<p>회원이 없습니다.</p>");
      } else {
        InitServlet.sqlSessionFactory.openSession(false).commit();
        out.println("<p>변경했습니다!</p>");
      }

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }

}
