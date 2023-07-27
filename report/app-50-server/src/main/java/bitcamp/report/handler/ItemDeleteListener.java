package bitcamp.report.handler;

import java.io.IOException;
import bitcamp.report.dao.ItemDao;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class ItemDeleteListener implements ActionListener {

  ItemDao itemDao;

  public ItemDeleteListener(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    if (itemDao.delete(prompt.inputInt("물품 번호? ")) == 0) {
      prompt.println("해당 번호의 물품이 없습니다!");
    }
    prompt.println("삭제했습니다!");
  }

}
