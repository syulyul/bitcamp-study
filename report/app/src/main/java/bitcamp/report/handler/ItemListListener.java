package bitcamp.report.handler;

import java.util.List;
import bitcamp.report.vo.Item;
import bitcamp.util.BreadcrumbPrompt;

public class ItemListListener extends AbstractItemListener {

  public ItemListListener(List<Item> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------------------------------------");
    System.out.println("물품 번호, 물품 이름, 물품 가격, 종류");
    System.out.println("---------------------------------------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Item item = this.list.get(i);
      System.out.printf("%d, %s, %d, %s\n", item.getNo(), item.getName(), item.getPrice(),
          item.getType());
    }
  }

}
