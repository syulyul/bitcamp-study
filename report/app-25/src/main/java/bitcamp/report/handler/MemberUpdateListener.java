package bitcamp.report.handler;

import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class MemberUpdateListener extends AbstractMemberListener {

  public MemberUpdateListener(List<Member> list) {
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
    m.setName(prompt.inputString("이름(%s)? ", m.getName()));
    m.setPhone(prompt.inputString("전화번호(%s)? ", m.getPhone()));
    m.setPassword(prompt.inputString("새암호? "));
    m.setPosition(inputPosition(m.getPosition(), prompt));
    return;
  }

}
