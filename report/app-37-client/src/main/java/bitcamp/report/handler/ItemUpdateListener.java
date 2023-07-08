package bitcamp.report.handler;

import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;
import bitcamp.util.BreadcrumbPrompt;

public class ItemUpdateListener implements ItemActionListener {

  ItemDao itemDao;

  public ItemUpdateListener(ItemDao itemDao) {
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

    item.setName(prompt.inputString("물품 이름(%s)? ", item.getName()));
    item.setPrice(prompt.inputInt("물품 가격(%d)? ", item.getPrice()));
    item.setType(ItemActionListener.inputType(item.getType(), prompt));
  }

}
