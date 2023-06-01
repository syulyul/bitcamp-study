package bitcamp.myapp;

// 코드 본문에서 사용할 클래스가 어떤 패키지의 클래스인지 지정
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    
    // 키보드 스캐너 준비
    Scanner scanner = new Scanner(System.in);

    final int MAX_SIZE = 100; // 대문자로 해주는 이유 : 강조하기 위함
    int userId = 1; // 회원 번호
    int length = 0; // 한 사람 입력 할 떄마다 증가시킬 값

    int[] no = new int[MAX_SIZE];
    String[] name = new String[MAX_SIZE]; // java에서 []를 앞에 해주는 이유 : primitive type 변수가 아닌 레퍼런스(주소를 담는)라는 것을 알려줌
    String[] email = new String[MAX_SIZE];
    String[] password = new String[MAX_SIZE];
    char[] gender = new char[MAX_SIZE];

    printTitle();

    // 회원정보 등록 
    for (int i = 0; i < MAX_SIZE; i++) {
      inputMember(scanner, i, name, email, password, gender, no, userId++);
      length++;      
      if (!promptContinue(scanner)) {
        break;
      }
    }
    
    printMemebers(length, no, name, email, gender);

    scanner.close();
  }

  static void printTitle() {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("-------------------------------");
  }

  static void inputMember(Scanner scanner, int i, 
      String[] name, String[] email, String[] password, char[] gender, int[] no, int userId) {
    System.out.print("이름? ");
      name[i] = scanner.next();

      System.out.print("이메일? ");
      email[i] = scanner.next();

      System.out.print("암호? ");
      password[i] = scanner.next();

    loop: while (true) {
      System.out.println("성별: ");
      System.out.println("  1. 남자");
      System.out.println("  2. 여자");
      System.out.print("> ");
      String menuNo = scanner.next();
      scanner.nextLine(); // 입력 값(token)을 읽고 난 후에 남아있는 줄바꿈 코드를 제거한다.

      // if (menuNo.equals("1")) {
      //   gender[i] = 'M';
      //   break;
      // } else if (menuNo.equals("2")) {
      //   gender[i] = 'W';
      //   break;
      // } else {
      //   System.out.println("무효한 번호입니다.");
      // }

      switch (menuNo) {
        case "1": 
          gender[i] = 'M';
          break loop;
        case "2": 
          gender[i] = 'W';
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
    
    // String str = scanner.next();
    // gender[i] = str.charAt(0);
    // gender[i] = scanner.next().charAt(0);

    no[i] = userId++; // 위에서 오류가 발생할 경우 회원번호 부여하지 않기 위해(정상적인 값 입력된 경우 회원 번호를 부여)
  }

  static boolean promptContinue(Scanner scanner) {
    System.out.print("계속 하시겠습니까?(Y/n) ");
      String response = scanner.nextLine(); // nextLine 쓰는 이유 : next()를 쓸 경우 빈문자열 문제,,
      if (!response.equals("") && !response.equalsIgnoreCase ("Y")) { // equalsIgnoreCase : 대소문자
        return false;
      }
      return true;
  }

  static void printMemebers(int length, int[] no, String[] name, String[] email, char[] gender) {
    System.out.println("--------------------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("--------------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %c\n", no[i], name[i], email[i], gender[i]);
    }
  }

}

// System.out.print("번호"); // 줄 안 바꿈
// System.out.println(no);

// System.out.printf("이름: %s", "홍길동"); // printf = 메서드, string 리터럴
// System.out.println();

// System.out.println("나이: " + 20); // 숫자를 문자열로 바꿔서 합쳐서 문자열로 표시

// System.out.printf("재직자: %b\n", true); // boolean 값 : 1,0 쓰지x => 코드 가독성 떨어짐
// // \n : 줄바꿈(escape character)

// System.out.printf("성별(남자(M), 여자(W)) : %c\n", "M"); // 문자에 "" 하면 error

// System.out.printf("좌우시력: %.1f,%.1f\n", 1.5f, 1.0f);