package bitcamp.myapp;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt();
    int b = sc.nextInt();
    sc.close();

    System.out.printf("a = %d\n", a);
    System.out.printf("b = %d\n", b);
  }
}
