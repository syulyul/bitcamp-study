package bitcamp.report.handler;

import java.io.IOException;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/item/detail")
public class ItemDetailListener implements ActionListener {

  ItemDao itemDao;

  public ItemDetailListener(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int itemNo = prompt.inputInt("물품 번호? ");

    Item item = itemDao.findBy(itemNo);
    if (item == null) {
      prompt.println("해당 번호의 물품이 없습니다!");
      return;
    }

    prompt.printf("물품 이름: %s\n", item.getName());
    prompt.printf("물품 가격: %d\n", item.getPrice());
    prompt.printf("물품 종류: %s\n", item.getType());
  }



}
