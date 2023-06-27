package bitcamp.report.handler;

import bitcamp.report.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public abstract class AbstractMemberListener implements ActionListener {

  protected List<Member> list;

  public AbstractMemberListener(List<Member> list) {
    this.list = list;
  }

  protected static String toPositionString(char position) {
    return position == '0' ? "관리자" : "일반직원";
  }

  protected Member findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Member m = this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  protected char inputPosition(char position, BreadcrumbPrompt prompt) {
    String label;
    if (position == 0) {
      label = "직책?\n";
    } else {
      label = String.format("직책(%s)?\n", toPositionString(position));
    }

    while (true) {
      String menuNo = prompt.inputString(label + "  1. 관리자\n" + "  2. 일반직원\n" + "> ");

      switch (menuNo) {
        case "1":
          return Member.MANAGER;
        case "2":
          return Member.STAFF;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }
}
