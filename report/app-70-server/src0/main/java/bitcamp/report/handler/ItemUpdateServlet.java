package bitcamp.report.handler;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/item/update")
public class ItemUpdateServlet implements Servlet {

  ItemDao itemDao;
  SqlSessionFactory sqlSessionFactory;

  public ItemUpdateServlet(ItemDao itemDao, SqlSessionFactory sqlSessionFactory) {
    this.itemDao = itemDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Item item = new Item();
    item.setNo(Integer.parseInt(request.getParameter("no")));
    item.setName(request.getParameter("name"));
    item.setPrice(Integer.parseInt(request.getParameter("price")));
    item.setType(request.getParameter("type"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<meta http-equiv='refresh' content='1;url=/item/list'>");
    out.println("<title>물품</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>물품 변경</h1>");

    try {
      if (itemDao.update(item) == 0) {
        out.println("<p>물품이 없습니다.</p>");
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
