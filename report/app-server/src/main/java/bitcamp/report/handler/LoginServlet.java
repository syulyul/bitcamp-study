package bitcamp.report.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.report.vo.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

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

    Member loginUser = InitServlet.memberDao.findByPhoneAndPassword(m);

    if (loginUser != null) {
      // 로그인 정보를 다른 요청에도 사용할 수 있도록 세션 보관소에 담아 둔다.
      request.getSession().setAttribute("loginUser", loginUser);
      response.sendRedirect("/");
      return;
    }

    request.setAttribute("message", "회원 정보가 일치하지 않습니다.");
    request.setAttribute("refresh", "1;url=/auth/form.html");

    request.getRequestDispatcher("/error").forward(request, response);
  }
}
