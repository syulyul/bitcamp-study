package bitcamp.report.handler;

import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class MemberUpdateListener implements MemberActionListener {

  MemberDao memberDao;

  public MemberUpdateListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 직원이 없습니다!");
      return;
    }
    m.setName(prompt.inputString("이름(%s)? ", m.getName()));
    m.setPhone(prompt.inputString("전화번호(%s)? ", m.getPhone()));
    m.setPassword(prompt.inputString("새암호? "));
    m.setPosition(MemberActionListener.inputPosition(m.getPosition(), prompt));

    memberDao.update(m);
  }

}
