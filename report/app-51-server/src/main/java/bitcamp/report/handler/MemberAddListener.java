package bitcamp.report.handler;

import java.io.IOException;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class MemberAddListener implements MemberActionListener {

  MemberDao memberDao;
  DataSource ds;

  public MemberAddListener(MemberDao memberDao, DataSource ds) {
    this.memberDao = memberDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Member m = new Member();
    m.setName(prompt.inputString("이름? "));
    m.setPhone(prompt.inputString("전화번호? "));
    m.setPassword(prompt.inputString("암호? "));
    m.setPosition(MemberActionListener.inputPosition((char) 0, prompt));

    try {
      memberDao.insert(m);
      ds.getConnection().commit();

    } catch (Exception e) {
      try {
        ds.getConnection().rollback();
      } catch (Exception e2) {
      }
      throw new RuntimeException(e);
    }
  }

}
