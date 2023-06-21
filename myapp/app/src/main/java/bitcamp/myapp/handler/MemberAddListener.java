package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

// MemberHandler는 Handler 규칙에 따라 메서드를 구현했다.
// 즉 Handler 인터페이스에 선언된 메서드를 모두 정의했다.
public class MemberAddListener extends AbstractMemberListener {

  public MemberAddListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) { // 내부에서 호출하므로 private
    Member m = new Member();
    m.setName(prompt.inputString("이름? "));
    m.setEmail(prompt.inputString("이메일? "));
    m.setPassword(prompt.inputString("암호? "));
    m.setGender(inputGender((char) 0, prompt));

    this.list.add(m);
    // if (!this.list.add(m)) {
    // System.out.println("입력 실패입니다!");
    // }
  }

  private static String toGenderString(char gender) { // 해당 메서드가 인스턴스를 쓰고 있지 않다면 static
    return gender == 'M' ? "남성" : "여성";
  }

  private char inputGender(char gender, BreadcrumbPrompt prompt) { // 해당 메서드가 인스턴스를 쓰고 있지 않다면 static
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      // label = "성별(" + toGenderString(gender) + ")?\n";
      label = String.format("성별(%s)?\n", toGenderString(gender)); // 문자열 리턴
    }
    while (true) {
      String menuNo = prompt.inputString(label + " 1. 남자\n" + " 2. 여자\n" + "> ");

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


}
