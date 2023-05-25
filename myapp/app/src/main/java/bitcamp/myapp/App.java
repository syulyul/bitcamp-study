package bitcamp.myapp;

public class App {

  public static void main(String[] args) {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("-------------------------------");

    System.out.print("번호"); // 줄 안 바꿈
    System.out.println(100);
    
    System.out.printf("이름: %s", "홍길동"); // printf = 메서드, string 리터럴
    System.out.println();

    System.out.println("나이: " + 20); // 숫자를 문자열로 바꿔서 합쳐서 문자열로 표시

    System.out.printf("재직자: %b\n", true); // boolean 값 : 1,0 쓰지x => 코드 가독성 떨어짐
                                           // \n : 줄바꿈(escape character)
    
    System.out.printf("성별(남자(M), 여자(W)) : %c\n", "M"); // 문자에 "" 하면 error

    System.out.printf("좌우시력: %.1f,%.1f\n", 1.5f, 1.0f);
  }
}
