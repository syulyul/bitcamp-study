package bitcamp.report.handler;

import bitcamp.report.ServerApp;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class LoginListener implements MemberActionListener {

  MemberDao memberDao;

  public LoginListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    while (true) {
      Member m = new Member();
      m.setPhone(prompt.inputString("전화번호? "));
      m.setPassword(prompt.inputString("암호? "));

      Member loginUser = memberDao.findByPhoneAndPassword(m);
      if (loginUser == null) {
        System.out.println("회원 정보가 일치하지 않습니다.");
      } else {
        ServerApp.loginUser = loginUser;
        break;
      }
    }
  }

}
