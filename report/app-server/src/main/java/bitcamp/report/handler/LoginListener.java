package bitcamp.report.handler;

import java.io.IOException;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/auth/login")
public class LoginListener implements MemberActionListener {

  MemberDao memberDao;

  public LoginListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    while (true) {
      Member m = new Member();
      m.setPhone(prompt.inputString("전화번호? "));
      m.setPassword(prompt.inputString("암호? "));

      Member loginUser = memberDao.findByPhoneAndPassword(m);
      if (loginUser == null) {
        prompt.println("회원 정보가 일치하지 않습니다.");
      } else {
        prompt.setAttribute("loginUser", loginUser);
        break;
      }
      prompt.end();
    }
  }

}
