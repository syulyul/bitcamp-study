package bitcamp.report;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("마트 물품 관리 시스템");
        System.out.println("---------------------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("물품 번호? ");
        int no = scanner.nextInt();

        System.out.print("물품 이름? ");
        String name = scanner.next();

        System.out.print("물품 가격? ");
        int price = scanner.nextInt();

        System.out.print("한정판매(true/false)? ");
        boolean temp = scanner.nextBoolean();

        System.out.print("종류(식료품/생활용품/의류/가전제품/리빙)? ");
        String type = scanner.next();

        System.out.println("---------------------------------------------------------------------");

        System.out.printf("물품 번호: %d\n", no);
        System.out.printf("물품 이름: %s\n", name);
        System.out.printf("물품 가격: %d\n", price);
        System.out.printf("한정 판매: %b\n", temp);
        System.out.printf("종류(식료품/생활용품/의류/가전제품/리빙): %s\n", type);

        scanner.close();
    }
}
