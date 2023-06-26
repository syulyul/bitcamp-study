package bitcamp.report.handler;

import bitcamp.report.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class MemberUpdateListener implements ActionListener {

  private List list;

  public MemberUpdateListener(List list) {
    this.list = list;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");

    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 직원이 없습니다!");
      return;
    }
    m.setName(prompt.inputString("이름(%s)? ", m.getName()));
    m.setPhone(prompt.inputString("전화번호(%s)? ", m.getPhone()));
    m.setPassword(prompt.inputString("새암호? "));
    m.setPosition(inputPosition(m.getPosition(), prompt));
    return;
  }


  private static String toPositionString(char position) {
    return position == '0' ? "관리자" : "일반직원";
  }


  private char inputPosition(char position, BreadcrumbPrompt prompt) {
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

  private Member findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

}
