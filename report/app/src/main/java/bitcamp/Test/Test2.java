package bitcamp.Test;

import bitcamp.report.vo.Member;

public class Test2 {

  public static void main(String[] args) {
    Member m1 = new Member();
    m1.setNo(1);
    m1.setName("홍길동");
    m1.setPhone("01011112222");
    m1.setPassword("1111");
    m1.setPosition('0');

    Member m2 = new Member();
    m2.setNo(1);
    m2.setName("홍길동");
    m2.setPhone("01011112222");
    m2.setPassword("1111");
    m2.setPosition('0');

    System.out.println(m1 == m2);
    System.out.println(m1.equals(m2));
  }

}
