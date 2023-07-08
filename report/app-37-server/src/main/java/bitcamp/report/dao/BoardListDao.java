package bitcamp.report.dao;

import java.util.ArrayList;
import java.util.List;
import bitcamp.report.vo.Board;
import bitcamp.util.JsonDataHelper;

public class BoardListDao implements BoardDao {

  String filename;
  ArrayList<Board> list = new ArrayList<>();

  public BoardListDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, Board.class);
  }

  @Override
  public void insert(Board board) {
    // 데이터 입력할 때 해당 데이터의 식별 번호는 Dao 에서 관리한다.
    board.setNo(Board.boardNo++);
    board.setCreatedDate(System.currentTimeMillis());
    this.list.add(board);

    // 데이터를 등록할 때마다 즉시 파일에 저장한다.
    JsonDataHelper.saveJson(filename, list);
  }

  @Override
  public List<Board> list() {
    return this.list;
  }

  @Override
  public Board findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Board board = this.list.get(i);
      if (board.getNo() == no) {
        return board;
      }
    }
    return null;
  }

  @Override
  public int update(Board board) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == board.getNo()) {
        list.set(i, board);
        // 데이터를 등록할 때마다 즉시 파일에 저장한다.
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int delete(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        list.remove(i);
        // 데이터를 등록할 때마다 즉시 파일에 저장한다.
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

}
