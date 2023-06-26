package bitcamp.report.handler;

import bitcamp.report.vo.Item;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class ItemDetailListener extends AbstractItemListener {

  public ItemDetailListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int itemNo = prompt.inputInt("물품 번호? ");

    Item item = this.findBy(itemNo);
    if (item == null) {
      System.out.println("해당 번호의 물품이 없습니다!");
      return;
    }

    System.out.printf("물품 이름: %s\n", item.getName());
    System.out.printf("물품 가격: %d\n", item.getPrice());
    System.out.printf("물품 종류: %s\n", item.getType());
  }

  

}
