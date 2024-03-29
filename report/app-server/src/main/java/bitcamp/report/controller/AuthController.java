package bitcamp.report.controller;

import bitcamp.report.service.MemberService;
import bitcamp.report.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/auth/login")
  public String login(
          @RequestParam("phone") String phone,
          @RequestParam("password") String password,
          HttpServletRequest request,
          HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/auth/form.jsp";
    }

    if (request.getParameter("savePhone") != null) {
      Cookie cookie = new Cookie("phone", phone);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("phone", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    Member loginUser = memberService.get(phone, password);

    if (loginUser == null) {
      request.setAttribute("refresh", "2;url=/app/auth/login");
      throw new Exception("회원 정보가 일치하지 않습니다.");
    }
    // 로그인 정보를 다른 요청에도 사용할 수 있도록 세션 보관소에 담아 둔다.
    request.getSession().setAttribute("loginUser", loginUser);
    return "redirect:/";
  }

  @RequestMapping("/auth/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.getSession().invalidate();
    return "redirect:/";
  }
}
