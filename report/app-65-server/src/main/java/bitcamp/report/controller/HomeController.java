package bitcamp.report.controller;

import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index.jsp")
public class HomeController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    // 인클루딩 하는 경우,
    // 여기서 콘텐트 타입을 미리 설정해야 한다.
    response.setContentType("text/html;charset=UTF-8");

    // View 컴포넌트를 인클루딩 한다.
    request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").include(request, response);
  }
}
