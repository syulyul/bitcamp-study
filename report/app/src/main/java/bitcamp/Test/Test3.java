package bitcamp.Test;

public class Test3 {

  public static void main(String[] args) {
    // 17 - 3 - 4 = 10

    Calculator2 c = new Calculator2();

    int result = c.minus(17, 3);
    result = c.minus(result, 4);
    System.out.println(result);

    // 파라미터의 타입이 다르더라도, 개수가 다르더라도, 순서가 다르더라도
    // 같은 기능을 수행한다면 같은 이름을 부여해서
    // 메서드를 호출할 때 통일성 있게 일관되게 사용할 수 있게 해주는 문법
    // => "오버로딩 (overloading)"
    result = c.minus(17, 3, 4);
    System.out.println(result);
  }

}
