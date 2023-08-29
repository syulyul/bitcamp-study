<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>

<%@ page import="bitcamp.report.dao.MemberDao"%>
<%@ page import="bitcamp.report.vo.Member"%>

<%
    request.setAttribute("refresh", "2;url=/auth/form.jsp");

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
%>
<jsp:useBean id="memberDao" type="bitcamp.report.dao.MemberDao" scope="application"/>
<%

    Member loginUser = memberDao.findByPhoneAndPassword(m);

    if (loginUser == null) {
        throw new Exception("회원 정보가 일치하지 않습니다.");
    }

    session.setAttribute("loginUser", loginUser);
    response.sendRedirect("/");
%>