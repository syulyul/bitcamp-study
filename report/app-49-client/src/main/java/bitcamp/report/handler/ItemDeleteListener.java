package bitcamp.report.handler;

import bitcamp.report.dao.ItemDao;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class ItemDeleteListener implements ActionListener {

  ItemDao itemDao;

  public ItemDeleteListener(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (itemDao.delete(prompt.inputInt("물품 번호? ")) == 0) {
      System.out.println("해당 번호의 물품이 없습니다!");
    }
  }

}
