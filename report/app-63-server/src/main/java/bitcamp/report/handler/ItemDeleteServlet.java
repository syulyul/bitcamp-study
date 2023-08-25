package bitcamp.report.handler;

import bitcamp.report.dao.ItemDao;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/item/delete")
public class ItemDeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ItemDao itemDao = (ItemDao) this.getServletContext().getAttribute("ItemDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");

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
