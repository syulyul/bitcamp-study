package bitcamp.report.handler;

import bitcamp.report.vo.Member;
import bitcamp.util.Prompt;

public class MemberHandler {

  private static final int MAX_SIZE = 100;

  private Prompt prompt;
  private Member[] members = new Member[MAX_SIZE];
  private int length;

  // 생성자: 인스턴스를 사용할 수 있도록 유효한 값으로 초기화시키는 일을 함
  // => 필요한 값을 외부에서 받고 싶으면 파라미터를 선언
  public MemberHandler(Prompt prompt) {
    this.prompt = prompt;
  }

  public void inputMember() {
    if (!this.available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Member m = new Member();
    m.setName(this.prompt.inputString("이름? "));
    m.setPhone(this.prompt.inputString("전화번호? "));
    m.setPassword(this.prompt.inputString("암호? "));
    m.setPosition(inputPosition((char) 0));

    // 위에서 만든 Member 인스턴스의 주소를 잃어버리지 않게
    // 레퍼런스 배열에 담는다.
    members[this.length++] = m;
  }

  public void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 전화번호, 직책");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      System.out.printf("%d, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getPhone(),
          toPositionString(m.getPosition()));
    }
  }

  public void viewMember() {
    String memberNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("이름: %s\n", m.getName());
        System.out.printf("전화번호: %s\n", m.getPhone());
        System.out.printf("직책: %s\n", toPositionString(m.getPosition()));
        return;
      }
    }
    System.out.println("해당 번호의 직원이 없습니다!");
  }

  public static String toPositionString(char position) {
    return position == '0' ? "관리자" : "일반직원";
  }

  public void updateMember() {
    String memberNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("이름(%s)? ", m.getName());
        m.setName(this.prompt.inputString(""));
        System.out.printf("전화번호(%s)? ", m.getPhone());
        m.setPhone(this.prompt.inputString(""));
        System.out.printf("새암호? ");
        m.setPassword(this.prompt.inputString(""));
        m.setPosition(inputPosition(m.getPosition()));
        return;
      }
    }
    System.out.println("해당 번호의 직원이 없습니다");
  }

  private char inputPosition(char position) {
    String label;
    if (position == 0) {
      label = "직책?\n";
    } else {
      label = String.format("직책(%s)?\n", toPositionString(position));
    }

    while (true) {
      String menuNo = this.prompt.inputString(label +
          "  1. 관리자\n" +
          "  2. 일반직원\n" +
          "> ");

      switch (menuNo) {
        case "1":
          return Member.MANAGER;
        case "2":
          return Member.STAFF;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public void deleteMember() {
    int memberNo = this.prompt.inputInt("번호? ");

    int deletedIndex = indexOf(memberNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 직원이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.members[i] = this.members[i + 1];
    }

    members[--this.length] = null;
  }

  private int indexOf(int memberNo) {
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private boolean available() {
    return this.length < MAX_SIZE;
  }
}
