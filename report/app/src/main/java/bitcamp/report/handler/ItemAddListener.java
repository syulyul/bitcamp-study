package bitcamp.report.handler;

import bitcamp.report.vo.Item;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class ItemAddListener extends AbstractItemListener {

  public ItemAddListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Item item = new Item();
    item.setName(prompt.inputString("물품 이름? "));
    item.setPrice(prompt.inputInt("물품 가격? "));
    item.setType(inputType("0", prompt));

    this.list.add(item);
  }

}
