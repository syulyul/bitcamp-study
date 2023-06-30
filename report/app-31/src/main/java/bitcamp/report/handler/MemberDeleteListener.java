package bitcamp.report.handler;

import java.util.List;
import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class MemberDeleteListener extends AbstractMemberListener {

  public MemberDeleteListener(List<Member> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Member(prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 직원이 없습니다!");
    }
  }

}
