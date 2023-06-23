package bitcamp.report.handler;

import bitcamp.report.vo.Item;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class ItemListListener implements ActionListener {

  private List list;

  public ItemListListener(List list) {

    this.list = list;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------------------------------------");
    System.out.println("물품 번호, 물품 이름, 물품 가격, 종류");
    System.out.println("---------------------------------------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Item item = (Item) this.list.get(i);
      System.out.printf("%d, %s, %d, %s\n", item.getNo(), item.getName(), item.getPrice(),
          item.getType());
    }
  }

}
