package bitcamp.report.handler;

import java.io.PrintWriter;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/item/detail")
public class ItemDetailServlet implements Servlet {

  ItemDao itemDao;

  public ItemDetailServlet(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
      out.println("<form action='/item/update'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n",
          item.getNo());
      out.printf("<tr><th>물품 이름</th>"
          + " <td><input type='text' name='name' value='%s'></td></tr>\n", item.getName());
      out.printf("<tr><th>물품 가격</th>"
          + " <td><input type='text' name='price' value='%d'></td></tr>\n", item.getPrice());
      out.printf("<tr><th>물품 종류</th>"
          + " <td><select name='type'>\n"
          + " <option value='1' %s>식료품</option>\n"
          + " <option value='2' %s>생활용품</option>\n"
          + " <option value='3' %s>의류</option>\n"
          + " <option value='4' %s>가전제품</option>\n"
          + " <option value='5' %s>리빙</option></select></td></tr>\n",
          (item.getType() == "1" ? "selected" : ""),
          (item.getType() == "2" ? "selected" : ""),
          (item.getType() == "3" ? "selected" : ""),
          (item.getType() == "4" ? "selected" : ""),
          (item.getType() == "5" ? "selected" : ""));
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
