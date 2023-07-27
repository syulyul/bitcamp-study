package bitcamp.report.handler;

import java.io.IOException;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class ItemAddListener implements ItemActionListener {

  ItemDao itemDao;
  DataSource ds;

  public ItemAddListener(ItemDao itemDao, DataSource ds) {
    this.itemDao = itemDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Item item = new Item();
    item.setName(prompt.inputString("물품 이름? "));
    item.setPrice(prompt.inputInt("물품 가격? "));
    item.setType(ItemActionListener.inputType("0", prompt));

    try {
      itemDao.insert(item);
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
