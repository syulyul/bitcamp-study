package bitcamp.report.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/member/update")
public class MemberUpdateListener implements MemberActionListener {

  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;

  public MemberUpdateListener(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
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

    try {
      memberDao.update(m);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }

}
