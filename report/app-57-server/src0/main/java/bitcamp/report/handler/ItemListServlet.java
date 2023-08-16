package bitcamp.report.handler;

import java.io.PrintWriter;
import java.util.List;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/item/list")
public class ItemListServlet implements Servlet {

  ItemDao itemDao;

  public ItemListServlet(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>물품</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>물품 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/item/form.html'>새 물품</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("  <tr><th>번호</th> <th>물품 이름</th> <th>물품 가격</th> <th>물품 종류</th></tr>");
    out.println("</thead>");

    List<Item> list = itemDao.findAll();
    for (Item item : list) {
      out.printf(
          "<tr>" + " <td>%d</td>" + " <td><a href='/item/detail?no=%d'>%s</a></td>" + " <td>%d</td>"
              + " <td>%s</td></tr>\n",
          item.getNo(), item.getNo(), item.getName(), item.getPrice(), item.getType());
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");

  }

}
