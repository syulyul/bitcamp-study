package bitcamp.report.controller;

import bitcamp.report.dao.BoardDao;
import bitcamp.report.vo.AttachedFile;
import bitcamp.report.vo.Board;
import bitcamp.report.vo.Member;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/fileDelete")
public class BoardFileDeleteController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/login");
      return;
    }

    BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");

    Board board = null;
    try {
      int category = Integer.parseInt(request.getParameter("category"));
      int fileNo = Integer.parseInt(request.getParameter("no"));

      AttachedFile attachedFile = boardDao.findFileBy(fileNo);
      board = boardDao.findBy(category, attachedFile.getBoardNo());
      if (board.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("게시글 변경 권한이 없습니다!");
      }

      if (boardDao.deleteFile(fileNo) == 0) {
        throw new Exception("해당 번호의 첨부파일이 없거나 삭제 권한이 없습니다.");
      } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("/board/detail?category=" + category + "&no=" + board.getNo());
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "2;url=/board/detail?category=" + request.getParameter("category") +
              "&no=" + board.getNo());

      throw new ServletException(e);
    }
  }

}
