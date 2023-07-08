package bitcamp.report.handler;

import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;
import bitcamp.util.BreadcrumbPrompt;

public class ItemAddListener implements ItemActionListener {

  ItemDao itemDao;

  public ItemAddListener(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Item item = new Item();

    item.setName(prompt.inputString("물품 이름? "));
    item.setPrice(prompt.inputInt("물품 가격? "));
    item.setType(ItemActionListener.inputType("0", prompt));

    itemDao.insert(item);
  }

}
