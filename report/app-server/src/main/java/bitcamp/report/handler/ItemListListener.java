package bitcamp.report.handler;

import java.util.List;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class ItemListListener implements ActionListener {

  ItemDao itemDao;

  public ItemListListener(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------------------------------------");
    System.out.println("물품 번호, 물품 이름, 물품 가격, 종류");
    System.out.println("---------------------------------------------------------------------");

    List<Item> list = itemDao.list();
    for (Item item : list) {
      System.out.printf("%d, %s, %d, %s\n", item.getNo(), item.getName(), item.getPrice(),
          item.getType());
    }
  }

}
