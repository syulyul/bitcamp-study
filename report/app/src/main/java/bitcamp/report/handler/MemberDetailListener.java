package bitcamp.report.handler;

import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class MemberDetailListener extends AbstractMemberListener {

  public MemberDetailListener(List<Member> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");

    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 직원이 없습니다!");
      return;
    }
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("전화번호: %s\n", m.getPhone());
    System.out.printf("직책: %s\n", toPositionString(m.getPosition()));
  }

}
