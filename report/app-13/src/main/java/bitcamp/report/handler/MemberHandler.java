package bitcamp.report.handler;

import bitcamp.report.vo.Member;
import bitcamp.util.Prompt;

public class MemberHandler {

  static final int MAX_SIZE = 100;
  static Member[] members = new Member[MAX_SIZE];
  static int length = 0;

  public static void inputMember() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Member m = new Member();
    m.setName(Prompt.inputString("이름? "));
    m.setPhone(Prompt.inputString("전화번호? "));
    m.setPassword(Prompt.inputString("암호? "));
    m.setPosition(inputPosition((char) 0));

    // 위에서 만든 Member 인스턴스의 주소를 잃어버리지 않게
    // 레퍼런스 배열에 담는다.
    members[length++] = m;
  }

  public static void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 전화번호, 직책");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getPhone(),
          toPositionString(m.getPosition()));
    }
  }

  public static void viewMember() {
    String memberNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member m = members[i];
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

  public static void updateMember() {
    String memberNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("이름(%s)? ", m.getName());
        m.setName(Prompt.inputString(""));
        System.out.printf("전화번호(%s)? ", m.getPhone());
        m.setPhone(Prompt.inputString(""));
        System.out.printf("새암호? ");
        m.setPassword(Prompt.inputString(""));
        m.setPosition(inputPosition(m.getPosition()));
        return;
      }
    }
    System.out.println("해당 번호의 직원이 없습니다");
  }

  private static char inputPosition(char position) {
    String label;
    if (position == 0) {
      label = "직책?\n";
    } else {
      label = String.format("직책(%s)?\n", toPositionString(position));
    }

    while (true) {
      String menuNo = Prompt.inputString(label +
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

  public static void deleteMember() {
    int memberNo = Prompt.inputInt("번호? ");

    int deletedIndex = indexOf(memberNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 직원이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      members[i] = members[i + 1];
    }

    members[--length] = null;
  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() {
    return length < MAX_SIZE;
  }
}
