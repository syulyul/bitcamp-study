<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bitcamp.report.dao.BoardDao"%>
<%@ page import="bitcamp.report.vo.AttachedFile"%>
<%@ page import="bitcamp.report.vo.Board"%>
<%@ page import="bitcamp.report.vo.Member"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="bitcamp.util.NcpObjectStorageService"%>

<%
    // 에러 발생 시 리프레시 할 경로
    request.setAttribute("refresh", "2;url=list.jsp?category=" + request.getParameter("category"));

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    BoardDao boardDao = (BoardDao) application.getAttribute("boardDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) application.getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) application.getAttribute("ncpObjectStorageService");

      Board board = new Board();
      board.setWriter(loginUser);
      board.setNo(Integer.parseInt(request.getParameter("no")));
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      board.setCategory(Integer.parseInt(request.getParameter("category")));

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      for (Part part : request.getParts()) {
        if (part.getName().equals("files") && part.getSize() > 0) {
          String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-25", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);

      if (boardDao.update(board) == 0) {
        throw new Exception("게시글이 없거나 변경 권한이 없습니다.");
      } else {
        if (attachedFiles.size() > 0) {
          int count = boardDao.insertFiles(board);
          System.out.println(count);
        }

        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list.jsp?category=" + request.getParameter("category"));
      }
%>