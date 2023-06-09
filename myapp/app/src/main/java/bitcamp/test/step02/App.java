package bitcamp.test.step02;

// import bitcamp.util.Calculator; // import 는 컴파일하지 않음 => bitcamp.util.Calculator 를 복사하는 역할

// 소스 코드에서 Calculator 클래스는 bitcamp.util 패키지에 소속된 클래스를 가리킨다.
//import bitcamp.util.Calculator; // 컴파일러는 import 문장을 바이트 코드로 바꾸지 않음 // 컴파일 시 없어짐

// 메소드 활용 (static 변수를 사용하는 이유)

// 1) 낱개의 변수 사용
// 2) 낱개의 변수 재사용
public class App { // 클래스 밖에 변수 선언 불가

  public static void main(String[] args) {
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float aver;

    name = "홍길동";
    kor = 100;
    eng = 100;
    math = 100;
    sum = kor + eng + math;
    aver = sum / 3f;

    System.out.printf("%s: 합계=%d, 평균=%.1f\n", name, sum, aver);

    name = "임꺽정";
    kor = 90;
    eng = 90;
    math = 90;
    sum = kor + eng + math;
    aver = sum / 3f;

    System.out.printf("%s: 합계=%d, 평균=%.1f\n", name, sum, aver);

    name = "유관순";
    kor = 80;
    eng = 80;
    math = 80;
    sum = kor + eng + math;
    aver = sum / 3f;

    System.out.printf("%s: 합계=%d, 평균=%.1f\n", name, sum, aver);

  }

}
