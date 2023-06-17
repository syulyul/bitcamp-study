package bitcamp.report.handler;

import bitcamp.report.vo.Member;
import bitcamp.util.Prompt;

public class MemberHandler implements Handler {

  private ArrayList list = new ArrayList();
  private Prompt prompt;
  private String title;

  // 생성자: 인스턴스를 사용할 수 있도록 유효한 값으로 초기화시키는 일을 함
  // => 필요한 값을 외부에서 받고 싶으면 파라미터를 선언
  public MemberHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  public void execute() {
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("%s> ", this.title);
      if (menuNo.equals("0")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        this.inputMember();
      } else if (menuNo.equals("2")) {
        this.printMembers();
      } else if (menuNo.equals("3")) {
        this.viewMember();
      } else if (menuNo.equals("4")) {
        this.updateMember();
      } else if (menuNo.equals("5")) {
        this.deleteMember();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  private static void printMenu() {
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 조회");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 종료");
  }

  private void inputMember() {
    Member m = new Member();
    m.setName(this.prompt.inputString("이름? "));
    m.setPhone(this.prompt.inputString("전화번호? "));
    m.setPassword(this.prompt.inputString("암호? "));
    m.setPosition(inputPosition((char) 0));

    if (!this.list.add(m)) {
      System.out.println("입력 실패입니다!");
    }
  }

  private void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 전화번호, 직책");
    System.out.println("---------------------------------------");

    Object[] arr = this.list.list();
    for (Object obj : arr) {
      Member m = (Member) obj;
      System.out.printf("%d, %s, %s, %s\n", m.getNo(), m.getName(), m.getPhone(),
          toPositionString(m.getPosition()));
    }
  }

  private void viewMember() {
    int memberNo = this.prompt.inputInt("번호? ");

    Member m = (Member) this.list.get(new Member(memberNo));
    if (m == null) {
      System.out.println("해당 번호의 직원이 없습니다!");
      return;
    }
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("전화번호: %s\n", m.getPhone());
    System.out.printf("직책: %s\n", toPositionString(m.getPosition()));
  }

  private static String toPositionString(char position) {
    return position == '0' ? "관리자" : "일반직원";
  }

  private void updateMember() {
    int memberNo = this.prompt.inputInt("번호? ");

    Member m = (Member) this.list.get(new Member(memberNo));
    if (m == null) {
      System.out.println("해당 번호의 직원이 없습니다!");
      return;
    }
    m.setName(this.prompt.inputString("이름(%s)? ", m.getName()));
    m.setPhone(this.prompt.inputString("전화번호(%s)? ", m.getPhone()));
    m.setPassword(this.prompt.inputString("새암호? "));
    m.setPosition(inputPosition(m.getPosition()));
    return;
  }

  private char inputPosition(char position) {
    String label;
    if (position == 0) {
      label = "직책?\n";
    } else {
      label = String.format("직책(%s)?\n", toPositionString(position));
    }

    while (true) {
      String menuNo = this.prompt.inputString(label + "  1. 관리자\n" + "  2. 일반직원\n" + "> ");

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

  private void deleteMember() {

    if (!this.list.delete(new Member(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 직원이 없습니다!");
    }
  }

}
