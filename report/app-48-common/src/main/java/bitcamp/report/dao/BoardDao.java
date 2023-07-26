package bitcamp.report.dao;

import java.util.List;
import bitcamp.report.vo.Board;

public interface BoardDao {
  void insert(Board board);

  List<Board> list();

  Board findBy(int no);

  int update(Board board);

  int delete(Board board);
}
