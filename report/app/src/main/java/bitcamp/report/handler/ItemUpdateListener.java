package bitcamp.report.handler;

import bitcamp.report.vo.Item;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class ItemUpdateListener implements ActionListener {

  private List list;

  // 생성자: 인스턴스를 사용할 수 있도록 유효한 값으로 초기화시키는 일을 함
  // => 필요한 값을 외부에서 받고 싶으면 파라미터를 선언
  public ItemUpdateListener(List list) {
    this.list = list;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int itemNo = prompt.inputInt("물품 번호? ");

    Item item = this.findBy(itemNo);
    if (item == null) {
      System.out.println("해당 번호의 물품이 없습니다!");
      return;
    }

    item.setName(prompt.inputString("물품 이름(%s)? ", item.getName()));
    item.setPrice(prompt.inputInt("물품 가격(%d)? ", item.getPrice()));
    item.setType(inputType(item.getType(), prompt));
  }

  private String inputType(String type, BreadcrumbPrompt prompt) {
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

  private Item findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Item item = (Item) this.list.get(i);
      if (item.getNo() == no) {
        return item;
      }
    }
    return null;
  }

}
