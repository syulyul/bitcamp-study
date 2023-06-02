package bitcamp.myapp;

public class Test {
  public static void main(String[] args) { // args 도 local 변수
    System.out.println(args);
    int a = 100, b = 200;
    swap(a, b);
    System.out.printf("main() : %d, %d\n", a, b);
  }

  static void swap(int a, int b){
    int temp = a;
    a = b;
    b = temp;
    System.out.printf("swap() : %d, %d\n", a, b);
  }
}
