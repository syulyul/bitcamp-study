package bitcamp.report.handler;

import bitcamp.report.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public interface MemberActionListener extends ActionListener {

  static char inputPosition(char position, BreadcrumbPrompt prompt) {
    String label;
    if (position == 0) {
      label = "직책?\n";
    } else {
      label = String.format("직책(%s)?\n", position == '0' ? "관리자" : "일반직원");
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
