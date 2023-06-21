package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.List;

public abstract class AbstractBoardListener implements ActionListener {

  protected List list;

  public AbstractBoardListener(List list) {
    this.list = list;
  }

  protected Board findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Board b = (Board) this.list.get(i);
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }
  // 추상클래스는 일부 메서드가 구현되지 않은 상태로 있어도 됨
  // 어차피 추상클래스는 인스턴스를 못 만들기 떄문에 그래도됨
}
