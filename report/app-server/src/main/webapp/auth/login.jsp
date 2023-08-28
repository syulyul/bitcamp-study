<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>

<%@ page import="bitcamp.report.dao.MemberDao"%>
<%@ page import="bitcamp.report.vo.Member"%>

<%
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

    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    Member loginUser = memberDao.findByPhoneAndPassword(m);

    if (loginUser != null) {
      // 로그인 정보를 다른 요청에도 사용할 수 있도록 세션 보관소에 담아 둔다.
      request.getSession().setAttribute("loginUser", loginUser);
      response.sendRedirect("/");
      return;
    }

    request.setAttribute("message", "회원 정보가 일치하지 않습니다.");
    request.setAttribute("refresh", "1;url=/auth/form.html");

    request.getRequestDispatcher("/error").forward(request, response);
%>