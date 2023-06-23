package bitcamp.report.handler;

import bitcamp.report.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class MemberAddListener implements ActionListener {

  private List list;

  public MemberAddListener(List list) {
    this.list = list;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Member m = new Member();
    m.setName(prompt.inputString("이름? "));
    m.setPhone(prompt.inputString("전화번호? "));
    m.setPassword(prompt.inputString("암호? "));
    m.setPosition(inputPosition((char) 0, prompt));

    this.list.add(m);
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

}
