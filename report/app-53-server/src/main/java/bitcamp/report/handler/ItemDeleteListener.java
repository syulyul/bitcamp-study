package bitcamp.report.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.report.dao.ItemDao;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class ItemDeleteListener implements ActionListener {

  ItemDao itemDao;
  SqlSessionFactory sqlSessionFactory;

  public ItemDeleteListener(ItemDao itemDao, SqlSessionFactory sqlSessionFactory) {
    this.itemDao = itemDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    try {
      if (itemDao.delete(prompt.inputInt("물품 번호? ")) == 0) {
        prompt.println("해당 번호의 물품이 없습니다!");
      }
      prompt.println("삭제했습니다!");
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }

}
