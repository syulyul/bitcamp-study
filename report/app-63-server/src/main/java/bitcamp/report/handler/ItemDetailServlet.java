package bitcamp.report.handler;

import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/item/detail")
public class ItemDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ItemDao itemDao = (ItemDao) this.getServletContext().getAttribute("ItemDao");

    Item item = itemDao.findBy(Integer.parseInt(request.getParameter("no")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>물품</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>물품</h1>");

    if (item == null) {
      out.println("<p>해당 번호의 물품이 없습니다!</p>");
    } else {
      out.println("<form action='/item/update' method='post'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n",
          item.getNo());
      out.printf(
          "<tr><th>물품 이름</th>" + " <td><input type='text' name='name' value='%s'></td></tr>\n",
          item.getName());
      out.printf(
          "<tr><th>물품 가격</th>" + " <td><input type='text' name='price' value='%d'></td></tr>\n",
          item.getPrice());
      out.printf("<tr><th>물품 종류</th>" + " <td><select name='type'>\n"
          + " <option value='식료품' %s>식료품</option>\n" + " <option value='생활용품' %s>생활용품</option>\n"
          + " <option value='의류' %s>의류</option>\n" + " <option value='가전제품' %s>가전제품</option>\n"
          + " <option value='리빙' %s>리빙</option></select></td></tr>\n",
          (item.getType() == "식료품" ? "selected" : ""), (item.getType() == "생활용품" ? "selected" : ""),
          (item.getType() == "의류" ? "selected" : ""), (item.getType() == "가전제품" ? "selected" : ""),
          (item.getType() == "리빙" ? "selected" : ""));
      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/item/delete?no=%d'>삭제</a>\n", item.getNo());
      out.println("<a href='/item/list'>목록</a>\n");
      out.println("</div>");
      out.println("</form>");
    }

    out.println("</body>");
    out.println("</html>");
  }

}
