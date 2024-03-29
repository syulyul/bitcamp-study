// 리프래시 또는 리다이렉트 최종 요청 페이지
package eomcs.servlet.ex08;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ex08/s100")
@SuppressWarnings("serial")
public class Servlet100 extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 이 서블릿은 직접 실행할 용도로 만든 것이 아니다.
    // 리프래시와 리다이렉트를 연습할 용도로 만들었다.
    //
    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("반가워요 - /ex08/s100");
  }
}

