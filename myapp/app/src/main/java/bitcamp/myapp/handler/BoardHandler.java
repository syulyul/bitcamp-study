package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardHandler {

  static final int MAX_SIZE = 100; // 대문자로 해주는 이유 : 강조하기 위함
  static Board[] boards = new Board[MAX_SIZE];
  static int length = 0; // 한 사람 입력 할 떄마다 증가시킬 값

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
    // String memberNo = Prompt.inputString("번호? ");
    // // 입력 받은 번호를 가지고 배열에서 해당 회원을 찾아야 한다.
    // for (int i = 0; i < length; i++) {
    // Board m = boards[i];
    // if (m.getNo() == Integer.parseInt(memberNo)) {
    // // i 번째 항목에 저장된 회원 정보 출력
    // System.out.printf("이름: %s\n", m.getName());
    // System.out.printf("이메일: %s\n", m.getEmail());
    // System.out.printf("성별: %s\n", toGenderString(m.getGender()));
    // return;
    // }
    // }
    // System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  public static void updateBoard() {
    // String memberNo = Prompt.inputString("번호? ");
    // // 입력 받은 번호를 가지고 배열에서 해당 회원을 찾아야 한다.
    // for (int i = 0; i < length; i++) {
    // Board m = boards[i];
    //
    // if (m.getNo() == Integer.parseInt(memberNo)) {
    // // i 번째 항목에 저장된 회원 정보 출력
    // m.setName(Prompt.inputString("이름(" + m.getName() + ")? "));
    // m.setEmail(Prompt.inputString("이메일(" + m.getEmail() + ")?\n"));
    // m.setPassword(Prompt.inputString("새암호? "));
    // m.setGender(inputGender(m.getGender()));
    // return;
    // }
    // }
    // System.out.println("해당 번호의 회원이 없습니다!");
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
    // 삭제하려는 회원의 정보가 들어 있는 인덱스를 알아낸다.

    int memberNo = Integer.parseInt(Prompt.inputString("번호? "));

    int deletedIndex = indexOf(memberNo);

    // // 입력 받은 번호를 가지고 배열에서 해당 회원을 찾아야 한다.
    // for (int i = 0; i < length; i++) {
    // if (no[i] == Integer.parseInt(memberNo)) {
    // deletedIndex = i;
    // break;
    // }
    // }

    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    // if (deletedIndex < (length - 1)) {
    // // 만약 삭제하려는 항목이 마지막 인덱스의 항목이라면 마지막 인덱스의 값만 0으로 초기화시킨다.
    // no[deletedIndex] = 0;
    // name[deletedIndex] = null;
    // email[deletedIndex] = null;
    // password[deletedIndex] = null;
    // gender[deletedIndex] = (char) 0;
    // } else {
    // 그 밖에는 해당 인덱스부터 반복하면서 앞 인덱스의 값을 당겨온다.
    for (int i = deletedIndex; i < length - 1; i++) {
      boards[i] = boards[i + 1];
    }

    boards[--length] = null;
    // }
    // length를 하나 줄인다.

    System.out.println("삭제했습니다.");
    return;
  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      Board m = boards[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() { // 같은 클래스 안에서만 사용 가능
    return length < MAX_SIZE;
  }
}
