// 관계 연산자 : 부동소수점 비교 
package com.eomcs.lang.ex05;

public class Exam0220 {
  public static void main(String[] args) {
    double d1 = 987.6543;
    double d2 = 1.111111;
    System.out.println((d1 + d2) == 988.765411);
    // 결과는 false이다.
    // 이유?
    // - 부동소수점 값을 IEEE 754 명세에 따라 2진수로 바꿔 메모리에 담을 때
    // 정규화(소수점 이하의 수를 2진수로 바꾸는) 과정에서
    // 정수로 딱 떨어지지 않는 경우가 있다.
    // 즉 극한의 미세 소수점이 붙을 수 있다.
    // - CPU나 OS, JVM의 문제가 아니다.
    // - IEEE 754 명세에 따라 부동소수점을 처리하는 모든
    // 컴퓨터에서 발생하는 문제이다.
    // - 이런 부동소수점을 계산할 때 기대하는 값과 다른 값이 나올 수 있다.
    // - 또한 연산한 결과를 메모리에 담을 때도 정규화 과정에서
    // 극한의 미세 소수점이 붙을 수 있다.
    System.out.println(d1);
    System.out.println(d2);
    System.out.println(d1 + d2);
    // 987.6543 + 1.111111 = 988.7654110000001
    // => 결과 뒤에 극소수의 값이 붙는다.
    // => 그래서 부동 소수점의 비교를 대충 다루지 말라!
    // 0 10000001000 1110110111010011110000000001101000110110111000101111 (987.6543)
    // 0 01111111111 0001110001110001110001010011111100111001110100011011 (1.111111)
    //
    // 1.1110110111010011110000000001101000110110111000101111
    // 0.0000000010001110001110001110001010011111100111001110
    // ---------------------------------------------------------
    // 1.1110111001100001111110001111110011010110011111111101
    //
    // 0 10000001000 1110111001100001111110001111110011010110011111111101
    // 0 10000001000 1110111001100001111110001111110011010110011111111101

    double x = 234.765411;
    double y = 754.0;
    System.out.println((x + y) == 988.765411);

    System.out.println(x);
    System.out.println(y);
    System.out.println(x + y);
    // d1 + d2와 달리 x + y의 계산 결과는 뒤에 극소수의 값이 붙지 않는다. // 극한의 값이 무조건 붙는 것은 아님
    // 234.765411 + 754.0 = 988.765411
    //
    // 0 10000000110 1101010110000111111000111111001101011001111111110101
    // (234.765411)
    // 0 10000001000 0111100100000000000000000000000000000000000000000000 (754.0)
    //
    // 0.0111010101100001111110001111110011010110011111111101
    // 1.0111100100000000000000000000000000000000000000000000
    // ------------------------------------------------------
    // 1.1110111001100001111110001111110011010110011111111101
    //
    // 0 10000001000 1110111001100001111110001111110011010110011111111101
    //

    // IEEE 754의 변환 공식에 따라 발생되는 이런 문제를
    // 실무 프로그래밍 할 때 해결하는 방법?
    //
    System.out.println((d1 + d2) == (x + y)); // false

    // 소수점 뒤에 붙은 극소수의 값을 무시하면 된다.
    // => JVM이 자동으로 처리하지 않는다.
    // => 다음과 같이 개발자가 직접 처리해야 한다.
    double EPSILON = 0.00001;
    System.out.println(Math.abs((d1 + d2) - (x + y)) < EPSILON);
  }
}
