package bitcamp.report.handler;

import java.io.IOException;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class MemberAddListener implements MemberActionListener {

  MemberDao memberDao;

  public MemberAddListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Member m = new Member();
    try {
      m.setName(prompt.inputString("이름? "));
      m.setPhone(prompt.inputString("전화번호? "));
      m.setPassword(prompt.inputString("암호? "));
      m.setPosition(MemberActionListener.inputPosition((char) 0, prompt));

      memberDao.insert(m);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
