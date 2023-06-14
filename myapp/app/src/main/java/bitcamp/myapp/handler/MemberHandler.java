package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberHandler {

  private static final int MAX_SIZE = 100;
  // variable Initializer(변수 초기화 문장) => static 블록으로 이동
  // 단 final 변수는 static 블록에서 값을 할당하지 않고 그냥 상수로 다룬다.

  private Prompt prompt; // 외부에서 주입받도록

  private Member[] members = new Member[MAX_SIZE];
  // variable Initializer(변수 초기화 문장) => 생성자로 이동

  private int length; // 한 사람 입력 할 마다 증가시킬 값 // 인스턴스 변수는 무조건 초기화 됨

  // 생성자: 인스턴스를 사용할 수 있도록 유효한 값으로 초기화시키는 일을 한다.
  // => 필요한 값을 외부에서 받고 싶으면 파라미터를 선언하라
  public MemberHandler(Prompt prompt) { // 변수 초기화 문장은 생성자에 초기화
    this.prompt = prompt;
  }

  public void inputMember() {
    if (!this.available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return; // 함수 종료시키고 싶을 때
    }

    Member m = new Member();
    m.setName(this.prompt.inputString("이름? "));
    m.setEmail(this.prompt.inputString("이메일? "));
    m.setPassword(this.prompt.inputString("암호? "));
    m.setGender(inputGender((char) 0));

    this.members[this.length++] = m;

  }

  public void printMemebers() {
    System.out.println("--------------------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("--------------------------------------------");

    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      System.out.printf("%d, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(),
          toGenderString(m.getGender()));
    }
  }

  public void viewMember() {
    String memberNo = this.prompt.inputString("번호? ");
    // 입력 받은 번호를 가지고 배열에서 해당 회원을 찾아야 한다.
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        // i 번째 항목에 저장된 회원 정보 출력
        System.out.printf("이름: %s\n", m.getName());
        System.out.printf("이메일: %s\n", m.getEmail());
        System.out.printf("성별: %s\n", toGenderString(m.getGender()));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static String toGenderString(char gender) { // 해당 메서드가 인스턴스를 쓰고 있지 않다면 static
    return gender == 'M' ? "남성" : "여성";
  }

  public void updateMember() {
    String memberNo = this.prompt.inputString("번호? ");
    // 입력 받은 번호를 가지고 배열에서 해당 회원을 찾아야 한다.
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];

      if (m.getNo() == Integer.parseInt(memberNo)) {
        // i 번째 항목에 저장된 회원 정보 출력
        m.setName(this.prompt.inputString("이름(%s)? ", m.getName()));
        m.setEmail(this.prompt.inputString("이메일(%s)? ", m.getEmail()));
        m.setPassword(this.prompt.inputString("새암호? "));
        m.setGender(inputGender(m.getGender()));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private char inputGender(char gender) { // 해당 메서드가 인스턴스를 쓰고 있지 않다면 static
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      // label = "성별(" + toGenderString(gender) + ")?\n";
      label = String.format("성별(%s)?\n", toGenderString(gender)); // 문자열 리턴
    }
    while (true) {
      String menuNo = this.prompt.inputString(label + " 1. 남자\n" + " 2. 여자\n" + "> ");

      switch (menuNo) {
        case "1":
          return Member.MALE;
        case "2":
          return Member.FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public void deleteMember() {
    // 삭제하려는 회원의 정보가 들어 있는 인덱스를 알아낸다.

    int memberNo = Integer.parseInt(this.prompt.inputString("번호? "));

    int deletedIndex = indexOf(memberNo);

    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.members[i] = this.members[i + 1];
    }

    this.members[--this.length] = null;
    // }
    // length를 하나 줄인다.

    System.out.println("삭제했습니다.");
    return;
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

  private boolean available() { // 같은 클래스 안에서만 사용 가능
    return this.length < MAX_SIZE;
  }
}
