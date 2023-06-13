package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardHandler {

  static final int MAX_SIZE = 100; // 대문자로 해주는 이유 : 강조하기 위함
  static Board[] boards = new Board[MAX_SIZE];
  static int length = 0; // 한 사람 입력 할 때마다 증가시킬 값

  public static void inputBoard() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return; // 함수 종료시키고 싶을 때
    }

    Board board = new Board();
    board.setTitle(Prompt.inputString("제목? "));
    board.setContent(Prompt.inputString("내용? "));
    board.setWriter(Prompt.inputString("작성자? "));
    board.setPassword(Prompt.inputString("비밀번호? "));

    // 위에서 만든 Board 인스턴스의 주소를 잃어버리지 않게 레퍼런스 배열에 담는다.
    boards[length++] = board;
  }

  public static void printBoards() {
    System.out.println("--------------------------------------------");
    System.out.println("번호, 제목, 작성자, 조회수, 등록일");
    System.out.println("--------------------------------------------");


    for (int i = 0; i < length; i++) {
      Board board = boards[i];

      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n", board.getNo(), board.getTitle(),
          board.getWriter(), board.getViewCount(), board.getCreatedDate());
    }
  }

  public static void viewBoard() {
    String boardNo = Prompt.inputString("번호? ");
    // 입력 받은 번호를 가지고 배열에서 해당 게시글을 찾아야 한다.
    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      if (board.getNo() == Integer.parseInt(boardNo)) {
        // i 번째 항목에 저장된 게시글 정보 출력
        System.out.printf("제목: %s\n", board.getTitle());
        System.out.printf("내용: %s\n", board.getContent());
        System.out.printf("작성자: %s\n", board.getWriter());
        System.out.printf("조회수: %d\n", board.getViewCount());
        System.out.printf("등록일: %tY-%1$tm-%1$td\n", board.getCreatedDate());
        return;
      }
    }
    System.out.println("해당 번호의 게시글이 없습니다!");
  }

  // public static String toGenderString(char gender) {
  // return gender == 'M' ? "남성" : "여성";
  // }

  public static void updateBoard() {
    String boardNo = Prompt.inputString("번호? ");
    // 입력 받은 번호를 가지고 배열에서 해당 게시글을 찾아야 한다.

    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      if (Prompt.inputString("암호? ").equals(board.getPassword())) {
        if (board.getNo() == Integer.parseInt(boardNo)) {
          // i 번째 항목에 저장된 회원 정보 출력
          System.out.printf("제목(%s)? ", board.getTitle());
          board.setTitle(Prompt.inputString(""));
          System.out.printf("내용(%s)? ", board.getContent());
          board.setContent(Prompt.inputString(""));
          board.setPassword(Prompt.inputString("새암호? "));
          return;
        }
      } else {
        System.out.println("암호가 올바르지 않습니다.");
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  // private static char inputGender(char gender) {
  // String label;
  // if (gender == 0) {
  // label = "성별?\n";
  // } else {
  // // label = "성별(" + toGenderString(gender) + ")?\n";
  // label = String.format("성별(%s)?\n", toGenderString(gender)); // 문자열 리턴
  // }
  // while (true) {
  // String menuNo = Prompt.inputString(label + " 1. 남자\n" + " 2. 여자\n" + "> ");
  //
  // switch (menuNo) {
  // case "1":
  // return Board.MALE;
  // case "2":
  // return Board.FEMALE;
  // default:
  // System.out.println("무효한 번호입니다.");
  // }
  // }
  // }

  public static void deleteBoard() {
    // 삭제하려는 게시글의 정보가 들어 있는 인덱스를 알아낸다.

    int boardNo = Integer.parseInt(Prompt.inputString("번호? "));

    int deletedIndex = indexOf(boardNo);

    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      boards[i] = boards[i + 1];
    }

    boards[--length] = null;

    System.out.println("삭제했습니다.");
    return;
  }

  private static int indexOf(int boardNo) {
    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      if (board.getNo() == boardNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() { // 같은 클래스 안에서만 사용 가능
    return length < MAX_SIZE;
  }
}
