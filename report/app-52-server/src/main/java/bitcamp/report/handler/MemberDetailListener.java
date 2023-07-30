package bitcamp.report.handler;

import java.io.IOException;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class MemberDetailListener implements ActionListener {

  MemberDao memberDao;

  public MemberDetailListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int memberNo = prompt.inputInt("번호? ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      prompt.println("해당 번호의 직원이 없습니다!");
      return;
    }
    prompt.printf("이름: %s\n", m.getName());
    prompt.printf("전화번호: %s\n", m.getPhone());
    prompt.printf("직책: %s\n", m.getPosition() == '0' ? "관리자" : "일반직원");
    prompt.printf("가입일: %s\n", m.getCreatedDate());

  }

}
