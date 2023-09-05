package bitcamp.report.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    String pageControllerPath = request.getPathInfo();

    response.setContentType("text/html;charset=UTF-8");

    // 클라이언트가 요청한 페이지 컨트롤러를 실행한다.
    request.getRequestDispatcher(pageControllerPath).include(request, response);

    // 페이지 컨트롤러를 실행한 후, 페이지 컨트롤러가 설정한 페이지를 include 한다.
    // 만약 Redirect URL이 설정되어 있다면, redirect 한다.
    String viewUrl = (String) request.getAttribute("viewUrl");
    if (viewUrl.startsWith("redirect:")) {
      response.sendRedirect(viewUrl.substring(9)); // 예) redirect:/app/board/list
    } else {
      request.getRequestDispatcher(viewUrl).include(request, response);
    }
  }
}
