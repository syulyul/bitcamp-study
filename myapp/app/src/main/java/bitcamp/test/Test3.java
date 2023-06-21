package bitcamp.test;

import bitcamp.test.p1.A;

public class Test3 extends A {
  public static void main(String[] args) {
    A obj = new A();

    // obj.v1 = 100; // 접근 불가!
    // obj.v2 = 200; // 접근 불가!
    // obj.v3 = 300; // 접근 불가! <= 상속 받은 멤버가 아니다.
    obj.v4 = 400;
    // obj.m(); // 접근 불가! <== 상속 받은 멤버가 아니다.

    m2(); // 같은 스태틱 멤버이므로 그냥 호출 가능

    Test3 obj2 = new Test3();
    obj2.m3(); // 논스태틱 메서드이므로 호출하려면 인스턴스 주소가 필요
    // obj2.v1 = 100; // 접근 불가!
    // 원래 v1이라는 인스턴스변수가선언된 A 라는 클래스만이 접근 가능 (ㅐ키지 다름)
    // obj2.v2 = 200; // 접근 불가!
    obj2.v3 = 300; // 자식 클래스가 상속받아서 사용하는 멤버!
    // (위에는 A 객체거, 밑에 v3 는 Test3거)
    obj2.v4 = 400;
    obj2.m(); // 자식 클래스가 상속 받아서 사용하는 멤버!
  }

  static void m2() {

  }

  void m3() {

  }
}
