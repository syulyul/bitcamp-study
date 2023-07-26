package bitcamp.report.handler;

import bitcamp.report.dao.BoardDao;
import bitcamp.report.vo.Board;
import bitcamp.report.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class BoardAddListener implements ActionListener {

  BoardDao boardDao;

  public BoardAddListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Board board = new Board();

    board.setTitle(prompt.inputString("제목? "));
    board.setContent(prompt.inputString("내용? "));

    Member writer = new Member();
    writer.setNo(prompt.inputInt("작성자? "));
    board.setWriter(writer);

    board.setPassword(prompt.inputString("암호? "));

    boardDao.insert(board);
  }

}
