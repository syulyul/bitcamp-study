package bitcamp.report.handler;

import java.io.PrintWriter;
import java.util.List;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/member/list")
public class MemberListServlet implements Servlet {

  MemberDao memberDao;

  public MemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>직원</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>직원 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/member/form.html'>새 직원</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("  <tr><th>번호</th> <th>이름</th> <th>전화번호</th></tr>");
    out.println("</thead>");

    out.println("<tbody>");
    List<Member> list = memberDao.findAll();
    for (Member m : list) {
      out.printf("<tr>"
          + " <td>%d</td>"
          + " <td><a href='/member/detail?no=%d'>%s</a></td>"
          + " <td>%s</td></tr>\n", 
          m.getNo(), m.getNo(), m.getName(), m.getPhone());
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }

}
