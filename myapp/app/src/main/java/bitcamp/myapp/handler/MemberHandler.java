package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberHandler {

  static final int MAX_SIZE = 100; // 대문자로 해주는 이유 : 강조하기 위함
  static Member[] members = new Member[MAX_SIZE];
  // static int[] no = new int[MAX_SIZE];
  // static String[] name = new String[MAX_SIZE]; // java에서 []를 앞에 해주는 이유 :
  // primitive type 변수가 아닌 레퍼런스(주소를 담는)라는 것을 알려줌
  // static String[] email = new String[MAX_SIZE];
  // static String[] password = new String[MAX_SIZE];
  // static char[] gender = new char[MAX_SIZE];
  static int userId = 1; // 회원 번호
  static int length = 0; // 한 사람 입력 할 떄마다 증가시킬 값

  static final char MALE = 'M'; // 상수로 정의
  static final char FEMALE = 'W';

  public static void inputMember() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return; // 함수 종료시키고 싶을 때
    }

    Member m = new Member();
    m.name = Prompt.inputString("이름? ");
    m.email = Prompt.inputString("이메일? ");
    m.password = Prompt.inputString("암호? ");
    m.gender = inputGender((char) 0);

    // String str = scanner.next();
    // gender[i] = str.charAt(0);
    // gender[i] = scanner.next().charAt(0);

    m.no = userId++; // 위에서 오류가 발생할 경우 회원번호 부여하지 않기 위해(정상적인 값 입력된 경우 회원 번호를 부여)

    // 위에서 만든 Member 인스턴스의 주소를 잃어버리지 않게 레퍼런스 배열에 담는다.
    members[length++] = m;

  }

  public static void printMemebers() {
    System.out.println("--------------------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("--------------------------------------------");

    for (int i = 0; i < length; i++) {
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s\n",
          m.no, m.name, m.email,
          toGenderString(m.gender));
    }
  }

  public static void viewMember() {
    String memberNo = Prompt.inputString("번호? ");
    // 입력 받은 번호를 가지고 배열에서 해당 회원을 찾아야 한다.
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.no == Integer.parseInt(memberNo)) {
        // i 번째 항목에 저장된 회원 정보 출력
        System.out.printf("이름: %s\n", m.name);
        System.out.printf("이메일: %s\n", m.email);
        System.out.printf("성별: %s\n", toGenderString(m.gender));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  public static void updateMember() {
    String memberNo = Prompt.inputString("번호? ");
    // 입력 받은 번호를 가지고 배열에서 해당 회원을 찾아야 한다.
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.no == Integer.parseInt(memberNo)) {
        // i 번째 항목에 저장된 회원 정보 출력
        m.name = Prompt.inputString("이름(" + m.name + ")? ");
        m.email = Prompt.inputString("이메일(" + m.email + ")?\n");
        m.password = Prompt.inputString("새암호? ");
        m.gender = inputGender(m.gender);
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private static char inputGender(char gender) {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      // label = "성별(" + toGenderString(gender) + ")?\n";
      label = String.format("성별(%s)?\n", toGenderString(gender)); // 문자열 리턴
    }
    loop: while (true) {
      String menuNo = Prompt.inputString(label +
          " 1. 남자\n" +
          " 2. 여자\n" +
          "> ");

      switch (menuNo) {
        case "1":
          return MALE;
        case "2":
          return FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static void deleteMember() {
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
      members[i] = members[i + 1];
    }

    members[--length] = null;
    // }
    // length를 하나 줄인다.

    System.out.println("삭제했습니다.");
    return;
  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.no == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() { // 같은 클래스 안에서만 사용 가능
    return length < MAX_SIZE;
  }
}
