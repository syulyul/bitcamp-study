package bitcamp.util; // 폴더를 다른 패키지로 이동할 경우 패키지 자동으로 바뀜

public class Calculator { // 패키지에 직속하는 클래스 => top level class
  public static int result; // 스태틱 변수는 기본 값으로 0으로 초기화된다. -> 정수 0, 부동소수점 0.0, 문자도 0 // static field

  public static void init(int a) {
    result = a;
  }

  public static void plus(int a) {
    result += a;
  }

  public static void minus(int a) {
    result -= a;
  }
  
  public static void multiple(int a) {
    result *= a;
  }

  public static void divide(int a) {
    result /= a;
  }
}
