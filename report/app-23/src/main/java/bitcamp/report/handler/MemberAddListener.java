package bitcamp.report.handler;

import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class MemberAddListener extends AbstractMemberListener {

  public MemberAddListener(List list) {
    super(list);
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

}
