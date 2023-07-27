package bitcamp.report.handler;

import java.util.List;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class MemberListListener implements ActionListener {

  MemberDao memberDao;

  public MemberListListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    prompt.println("---------------------------------------");
    prompt.println("번호, 이름, 전화번호, 직책");
    prompt.println("---------------------------------------");

    List<Member> list = memberDao.list();
    for (Member m : list) {
      prompt.printf("%d, %s, %s, %s\n", m.getNo(), m.getName(), m.getPhone(),
          m.getPosition() == '0' ? "관리자" : "일반직원");
    }

  }

}
