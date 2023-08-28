<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>

<%@ page import="bitcamp.report.dao.BoardDao"%>
<%@ page import="bitcamp.report.vo.AttachedFile"%>
<%@ page import="bitcamp.report.vo.Board"%>
<%@ page import="bitcamp.report.vo.Member"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="bitcamp.util.NcpObjectStorageService"%>

<%
    request.setAttribute("refresh", "2;url=list.jsp?category=" + request.getParameter("category"));

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    int category = Integer.parseInt(request.getParameter("category"));

    Board b = new Board();
    b.setNo(Integer.parseInt(request.getParameter("no")));
    b.setWriter(loginUser);
    b.setCategory(category);

    BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");

    if (boardDao.delete(b) == 0) {
        throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
    } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list.jsp?category=" + request.getParameter("category"));
    }
%>