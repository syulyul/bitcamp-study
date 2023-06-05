package bitcamp.myapp;

// 소스 코드에서 Calculator 클래스는 bitcamp.util 패키지에 소속된 클래스를 가리킨다.
import bitcamp.util.Calculator; // 컴파일러는 import 문장을 바이트 코드로 바꾸지 않음 // 컴파일 시 없어짐

// 메소드 활용 (static 변수를 사용하는 이유)
public class Test { // 클래스 밖에 변수 선언 불가
  
  
  public static void main(String[] args) { 
    // 2 * 3 + 7 - 2 / 2 = ?
    // => 연산자 우선 순위를 고려하지 않고 앞에서부터 뒤로 순차적으로 계산한다.

    // int result; // 로컬 변수는 자동초기화 안 됨 => 처음 특정 값으로 초기화 시켜야 함

    Calculator.init(2);
    // bitcamp.util.Calculator.init(2); => 컴파일할 떄 이렇게 바뀜
    Calculator.multiple(3);
    Calculator.plus(7);
    Calculator.minus(2);
    Calculator.divide(2);
    System.out.println(Calculator.result);
  }

  
}
