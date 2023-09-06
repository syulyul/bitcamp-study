package bitcamp.report.controller;

import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/auth/login")
public class LoginController implements PageController {

  MemberDao memberDao;

  public LoginController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/auth/form.jsp";
    }

    Member m = new Member();
    m.setPhone(request.getParameter("phone"));
    m.setPassword(request.getParameter("password"));

    if (request.getParameter("savePhone") != null) {
      Cookie cookie = new Cookie("phone", m.getPhone());
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("phone", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    Member loginUser = memberDao.findByPhoneAndPassword(m);

    if (loginUser == null) {
      request.setAttribute("refresh", "2;url=/app/auth/login");
      throw new Exception("회원 정보가 일치하지 않습니다.");
    }
    // 로그인 정보를 다른 요청에도 사용할 수 있도록 세션 보관소에 담아 둔다.
    request.getSession().setAttribute("loginUser", loginUser);
    return "redirect:/";
  }
}
