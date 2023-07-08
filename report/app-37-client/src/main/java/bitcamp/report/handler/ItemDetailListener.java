package bitcamp.report.handler;

import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class ItemDetailListener implements ActionListener {

  ItemDao itemDao;

  public ItemDetailListener(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int itemNo = prompt.inputInt("물품 번호? ");

    Item item = itemDao.findBy(itemNo);
    if (item == null) {
      System.out.println("해당 번호의 물품이 없습니다!");
      return;
    }

    System.out.printf("물품 이름: %s\n", item.getName());
    System.out.printf("물품 가격: %d\n", item.getPrice());
    System.out.printf("물품 종류: %s\n", item.getType());
  }



}
