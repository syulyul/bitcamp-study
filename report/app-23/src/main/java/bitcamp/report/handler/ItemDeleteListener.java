package bitcamp.report.handler;

import bitcamp.report.vo.Item;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class ItemDeleteListener extends AbstractItemListener {

  public ItemDeleteListener(List list) {
   	super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Item(prompt.inputInt("물품 번호? ")))) {
      System.out.println("해당 번호의 물품이 없습니다!");
    }
  }

}
