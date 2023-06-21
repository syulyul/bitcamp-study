package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

// MemberHandler는 Handler 규칙에 따라 메서드를 구현했다.
// 즉 Handler 인터페이스에 선언된 메서드를 모두 정의했다.
public class MemberUpdateListener extends AbstractMemberListener {

  public MemberUpdateListener(List list) {
    super(list);
  }

  private static String toGenderString(char gender) { // 해당 메서드가 인스턴스를 쓰고 있지 않다면 static
    return gender == 'M' ? "남성" : "여성";
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");

    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    m.setName(prompt.inputString("이름(%s)? ", m.getName()));
    m.setEmail(prompt.inputString("이메일(%s)? ", m.getEmail()));
    m.setPassword(prompt.inputString("새암호? "));
    m.setGender(inputGender(m.getGender(), prompt));
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
