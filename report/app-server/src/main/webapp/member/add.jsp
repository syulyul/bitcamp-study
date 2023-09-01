<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>

<%@ page import="bitcamp.report.vo.Member"%>

<jsp:useBean id="memberDao" type="bitcamp.report.dao.MemberDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="bitcamp.util.NcpObjectStorageService" scope="application"/>

<%
    request.setAttribute("refresh", "2;url=list.jsp");

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