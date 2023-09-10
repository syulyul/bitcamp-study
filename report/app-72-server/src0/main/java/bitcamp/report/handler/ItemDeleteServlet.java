package bitcamp.report.handler;

import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.report.dao.ItemDao;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/item/delete")
public class ItemDeleteServlet implements Servlet {

  ItemDao itemDao;
  SqlSessionFactory sqlSessionFactory;

  public ItemDeleteServlet(ItemDao itemDao, SqlSessionFactory sqlSessionFactory) {
    this.itemDao = itemDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    try {
      if (itemDao.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 번호의 물품이 없습니다!");
      } else {
        response.sendRedirect("/item/list");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }

}
