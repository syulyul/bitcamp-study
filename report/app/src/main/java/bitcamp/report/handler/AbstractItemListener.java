package bitcamp.report.handler;

import java.util.List;
import bitcamp.report.vo.Item;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public abstract class AbstractItemListener implements ActionListener {

  protected List<Item> list;

  public AbstractItemListener(List<Item> list) {
    this.list = list;
  }

  protected Item findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Item item = this.list.get(i);
      if (item.getNo() == no) {
        return item;
      }
    }
    return null;
  }

  protected String inputType(String type, BreadcrumbPrompt prompt) {
    String label;
    if (type == "0") {
      label = "물품 종류?\n";
    } else {
      label = String.format("물품 종류(%s)?\n", type);
    }
    while (true) {
      String typeNo = prompt.inputString(
          label + "  1. 식료품\n" + "  2. 생활용품\n" + "  3. 의류\n" + "  4. 가전제품\n" + "  5. 리빙\n" + "> ");

      switch (typeNo) {
        case "1":
          return Item.FOOD;
        case "2":
          return Item.HOUSEHOLD_SUPPLIES;
        case "3":
          return Item.CLOTHES;
        case "4":
          return Item.HOME_APPLIANCES;
        case "5":
          return Item.LIVING;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }
}
