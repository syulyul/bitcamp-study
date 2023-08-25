package bitcamp.report.handler;

import bitcamp.report.dao.BoardDao;
import bitcamp.report.vo.AttachedFile;
import bitcamp.report.vo.Board;
import bitcamp.report.vo.Member;
import bitcamp.util.NcpObjectStorageService;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/board/add")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class BoardAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");

    try {
      // 각각의 파트에서 값을 꺼낸다.
      Board board = new Board();
      board.setWriter(loginUser);
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      board.setCategory(Integer.parseInt(request.getParameter("category")));

      String uploadDir = request.getServletContext().getRealPath("/upload/board/");
      System.out.println(uploadDir);

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

      for (Part part : request.getParts()) {
        // System.out.println(part.getName());
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
        response.sendRedirect("list?category=" + request.getParameter("category"));

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();

      // ErrorServlet 으로 포워딩 하기 전에 ErrorServlet이 사용할 데이터를
      // ServletRequest 보관소에 저장한다.
      request.setAttribute("error", e);
      request.setAttribute("message", "게시글 등록 오류!");
      request.setAttribute("refresh", "2;url=list?category=" + request.getParameter("category"));
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }

}
