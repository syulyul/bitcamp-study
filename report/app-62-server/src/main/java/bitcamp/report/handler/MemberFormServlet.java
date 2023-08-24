package bitcamp.report.handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/member/form")
public class MemberFormServlet extends HttpServlet {

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
    out.println("<title>비트캠프</title>");
    out.println("</head>");
    out.println("<body>");

    request.getRequestDispatcher("/header").include(request, response);

    out.println("<h1>회원</h1>");
    out.println("<form action='/member/add' method='post' enctype='multipart/form-data'>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("<th>이름</th> <td style='width:200px;'><input type='text' name='name'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<th>전화번호</th> <td><input type='tel' name='phone'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<th>암호</th> <td><input type='password' name='password'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<th>직책</th>");
    out.println("<td>");
    out.println("<select name='position'>");
    out.println("<option value='0'>관리자</option>");
    out.println("<option value='1'>일반직원</option>");
    out.println("</select>");
    out.println("</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<th>사진</th> <td><input type='file' name='photo'></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("<button>등록</button>");
    out.println("</form>");

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");
  }

}
