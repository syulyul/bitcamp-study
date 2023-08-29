<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bitcamp.report.vo.AttachedFile"%>
<%@ page import="bitcamp.report.vo.Board"%>
<%@ page import="bitcamp.report.vo.Member"%>

<%
    // 오류가 발생했을 때 refresh 할 URL 지정
    request.setAttribute("refresh", "2;url=list.jsp?category=" + request.getParameter("category"));

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }
%>

<jsp:useBean id="boardDao" type="bitcamp.report.dao.BoardDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="bitcamp.util.NcpObjectStorageService" scope="application"/>

<%

    // 각각의 파트에서 값을 꺼낸다.
    Board board = new Board();
    board.setWriter(loginUser);
    board.setTitle(request.getParameter("title"));
    board.setContent(request.getParameter("content"));
    board.setCategory(Integer.parseInt(request.getParameter("category")));

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

    for (Part part : request.getParts()) {
        if (part.getName().equals("files") && part.getSize() > 0) {
          String uploadFileUrl = ncpObjectStorageService
              .uploadFile("bitcamp-nc7-bucket-25", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
    }
    board.setAttachedFiles(attachedFiles);

    boardDao.insert(board);
    if (attachedFiles.size() > 0) {
      int count = boardDao.insertFiles(board);
      System.out.println(count);
    }

    sqlSessionFactory.openSession(false).commit();
    response.sendRedirect("list.jsp?category=" + request.getParameter("category"));
%>