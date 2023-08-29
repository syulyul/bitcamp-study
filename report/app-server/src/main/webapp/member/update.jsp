<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>

<%@ page import="bitcamp.report.dao.MemberDao"%>
<%@ page import="bitcamp.report.vo.Member"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="bitcamp.util.NcpObjectStorageService"%>

<%
    request.setAttribute("refresh", "2;url=list.jsp");

    Member member = new Member();
    member.setNo(Integer.parseInt(request.getParameter("no")));
    member.setName(request.getParameter("name"));
    member.setPhone(request.getParameter("phone"));
    member.setPassword(request.getParameter("password"));
    member.setPosition(request.getParameter("position").charAt(0));

    MemberDao memberDao = (MemberDao) application.getAttribute("memberDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) application.getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) application.getAttribute("ncpObjectStorageService");

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService
          .uploadFile("bitcamp-nc7-bucket-25", "member/", photoPart);
      member.setPhoto(uploadFileUrl);
    }

    if (memberDao.update(member) == 0) {
        throw new Exception("회원이 없습니다.");
    } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list.jsp");
    }
%>