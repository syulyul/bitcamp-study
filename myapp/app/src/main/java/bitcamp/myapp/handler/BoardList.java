package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;

public class BoardList {

  private static final int DEFAULT_SIZE = 3;

  private Board[] boards = new Board[DEFAULT_SIZE];
  private int length = 0; // 한 사람 입력 할 때마다 증가시킬 값

  public void add(Board board) {
    if (this.length == boards.length) {
      increase();
    }
    this.boards[this.length++] = board;
  }

  private void increase() {
    // 기존 배열 보다 50% 큰 배열을 새로 만든다.
    Board[] arr = new Board[boards.length + (boards.length >> 1)]; // boards.length / 2

    // 기존 배열의 값을 새 배열로 복사한다.
    for (int i = 0; i < boards.length; i++) {
      arr[i] = boards[i];
    }

    // boards 레퍼런스가 새 배열을 가리키도록 한다.
    boards = arr;

    // System.out.println("배열 확장: " + boards.length);
  }

  public Board[] list() {
    Board[] arr = new Board[this.length]; // 입력된 보드객체를 담을 만큼의 배열 생성

    for (int i = 0; i < this.length; i++) {
      arr[i] = this.boards[i];
    }
    return arr;
  }

  public Board get(int no) {
    for (int i = 0; i < this.length; i++) {
      Board board = this.boards[i]; // board:레퍼런스 배열에 있는 인스턴스 주소값을 가져옴
      if (board.getNo() == no) {
        board.setViewCount(board.getViewCount() + 1);
        return board;
      }
    }
    return null;
  }

  public boolean delete(int no) {
    int deletedIndex = this.indexOf(no);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.boards[i] = this.boards[i + 1];
    }
    this.boards[--this.length] = null;
    return true;
  }

  private int indexOf(int boardNo) {
    for (int i = 0; i < this.length; i++) {
      Board board = this.boards[i];
      if (board.getNo() == boardNo) {
        return i;
      }
    }
    return -1;
  }

}
