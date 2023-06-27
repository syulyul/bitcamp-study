package bitcamp.report.handler;

import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class MemberListListener extends AbstractMemberListener {

  public MemberListListener(List<Member> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 전화번호, 직책");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Member m = this.list.get(i);
      System.out.printf("%d, %s, %s, %s\n", m.getNo(), m.getName(), m.getPhone(),
          toPositionString(m.getPosition()));
    }
  }

}
