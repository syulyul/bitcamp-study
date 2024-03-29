package bitcamp.report.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.report.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

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
    List<Member> list = InitServlet.memberDao.findAll();
    for (Member m : list) {
      out.printf("<tr>" + " <td>%d</td>" + " <td><a href='/member/detail?no=%d'>%s</a></td>"
          + " <td>%s</td></tr>\n", m.getNo(), m.getNo(), m.getName(), m.getPhone());
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }

}
