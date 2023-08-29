<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>

<%@ page import="bitcamp.report.dao.MemberDao"%>
<%@ page import="bitcamp.report.vo.Member"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>

<%
    request.setAttribute("refresh", "2;url=list.jsp");

    MemberDao memberDao = (MemberDao) application.getAttribute("memberDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) application.getAttribute("sqlSessionFactory");

    if (memberDao.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다!");
    } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("/member/list.jsp");
    }

%>