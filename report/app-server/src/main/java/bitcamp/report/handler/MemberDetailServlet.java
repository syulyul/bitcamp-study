package bitcamp.report.handler;

import java.io.PrintWriter;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/member/detail")
public class MemberDetailServlet implements Servlet {

  MemberDao memberDao;

  public MemberDetailServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member member = memberDao.findBy(Integer.parseInt(request.getParameter("no")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>직원</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>직원</h1>");

    if (member == null) {
      out.println("<p>해당 번호의 직원이 없습니다!</p>");
    } else {
      out.println("<form action='/member/update'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n",
          member.getNo());
      out.printf("<tr><th>이름</th>"
          + " <td><input type='text' name='name' value='%s'></td></tr>\n", member.getName());
      out.printf("<tr><th>전화번호</th>"
          + " <td><input type='tel' name='phone' value='%s'></td></tr>\n", member.getPhone());
      out.printf("<tr><th>암호</th>"
          + " <td><input type='password' name='password'></td></tr>\n", member.getName());
      out.printf("<tr><th>직책</th>"
          + " <td><select name='position'>\n"
          + " <option value='0' %s>관리자</option>\n"
          + " <option value='1' %s>일반직원</option></select></td></tr>\n", 
          (member.getPosition() == '0' ? "selected" : ""),
          (member.getPosition() == '1' ? "selected" : ""));
      out.println("</table>");
      
      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/member/delete?no=%d'>삭제</a>\n", member.getNo());
      out.println("<a href='/member/list'>목록</a>\n");
      out.println("</div>");
      out.println("</form>");
    }
    
    out.println("</body>");
    out.println("</html>");
    
  }
}
