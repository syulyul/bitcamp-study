package bitcamp.report.handler;

import bitcamp.report.vo.Item;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class ItemDeleteListener implements ActionListener {

  private List list;

  // 생성자: 인스턴스를 사용할 수 있도록 유효한 값으로 초기화시키는 일을 함
  // => 필요한 값을 외부에서 받고 싶으면 파라미터를 선언
  public ItemDeleteListener(List list) {
    this.list = list;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Item(prompt.inputInt("물품 번호? ")))) {
      System.out.println("해당 번호의 물품이 없습니다!");
    }
  }

}
