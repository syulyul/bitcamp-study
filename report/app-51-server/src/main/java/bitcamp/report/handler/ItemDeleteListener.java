package bitcamp.report.handler;

import java.io.IOException;
import bitcamp.report.dao.ItemDao;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class ItemDeleteListener implements ActionListener {

  ItemDao itemDao;
  DataSource ds;

  public ItemDeleteListener(ItemDao itemDao, DataSource ds) {
    this.itemDao = itemDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    try {
      if (itemDao.delete(prompt.inputInt("물품 번호? ")) == 0) {
        prompt.println("해당 번호의 물품이 없습니다!");
      }
      prompt.println("삭제했습니다!");
      ds.getConnection().commit();

    } catch (Exception e) {
      try {
        ds.getConnection().rollback();
      } catch (Exception e2) {
      }
      throw new RuntimeException(e);
    }
  }

}
