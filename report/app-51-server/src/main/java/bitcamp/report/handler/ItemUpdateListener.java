package bitcamp.report.handler;

import java.io.IOException;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class ItemUpdateListener implements ItemActionListener {

  ItemDao itemDao;
  DataSource ds;

  public ItemUpdateListener(ItemDao itemDao, DataSource ds) {
    this.itemDao = itemDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int itemNo = prompt.inputInt("물품 번호? ");

    Item item = itemDao.findBy(itemNo);
    if (item == null) {
      prompt.println("해당 번호의 물품이 없습니다!");
      return;
    }

    item.setName(prompt.inputString("물품 이름(%s)? ", item.getName()));
    item.setPrice(prompt.inputInt("물품 가격(%d)? ", item.getPrice()));
    item.setType(ItemActionListener.inputType(item.getType(), prompt));

    try {
      itemDao.update(item);
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
