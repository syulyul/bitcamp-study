<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>

<%@ page import="bitcamp.report.dao.MemberDao"%>
<%@ page import="bitcamp.report.vo.Member"%>
<%@ page import="bitcamp.util.NcpObjectStorageService"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>

<%
    request.setAttribute("refresh", "2;url=list.jsp");

    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");

    Member m = new Member();
    m.setName(request.getParameter("name"));
    m.setPhone(request.getParameter("phone"));
    m.setPassword(request.getParameter("password"));
    m.setPosition(request.getParameter("position").charAt(0));

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile(
      "bitcamp-nc7-bucket-25", "member/", photoPart);
      m.setPhoto(uploadFileUrl);
    }

      memberDao.insert(m);
      sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("list.jsp");

%>