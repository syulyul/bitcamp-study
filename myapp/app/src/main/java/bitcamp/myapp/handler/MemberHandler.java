package bitcamp.myapp.handler;

import bitcamp.util.Prompt;

public class MemberHandler {

  static final int MAX_SIZE = 100; // 대문자로 해주는 이유 : 강조하기 위함
  static int[] no = new int[MAX_SIZE];
  static String[] name = new String[MAX_SIZE]; // java에서 []를 앞에 해주는 이유 : primitive type 변수가 아닌 레퍼런스(주소를 담는)라는 것을 알려줌
  static String[] email = new String[MAX_SIZE];
  static String[] password = new String[MAX_SIZE];
  static char[] gender = new char[MAX_SIZE];
  static int userId = 1; // 회원 번호
  static int length = 0; // 한 사람 입력 할 떄마다 증가시킬 값

  static final char MALE = 'M'; // 상수로 정의
  static final char FEMALE = 'W';

  public static void inputMember() {
    
    name[length] = Prompt.inputString("이름? ");
    email[length] = Prompt.inputString("이메일? ");
    password[length] = Prompt.inputString("암호? ");


    loop: while (true) {
      String menuNo = Prompt.inputString("성별:\n" +
      " 1. 남자\n" +
      " 2. 여자\n" +
      "> ");
      

      // if (menuNo.equals("1")) {
      // gender[i] = 'M';
      // break;
      // } else if (menuNo.equals("2")) {
      // gender[i] = 'W';
      // break;
      // } else {
      // System.out.println("무효한 번호입니다.");
      // }

      switch (menuNo) {
        case "1":
          gender[length] = MALE;
          break loop;
        case "2":
          gender[length] = FEMALE;
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }

    // String str = scanner.next();
    // gender[i] = str.charAt(0);
    // gender[i] = scanner.next().charAt(0);

    no[length] = userId++; // 위에서 오류가 발생할 경우 회원번호 부여하지 않기 위해(정상적인 값 입력된 경우 회원 번호를 부여)
    length++;
  }

  public static void printMemebers() {
    System.out.println("--------------------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("--------------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %c\n", no[i], name[i], email[i], gender[i]);
    }
  }

  public static boolean available() {
    return length < MAX_SIZE;
  }
}
