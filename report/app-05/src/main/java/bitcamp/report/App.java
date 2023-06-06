package bitcamp.report;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("마트 물품 관리 시스템");
        System.out.println("---------------------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);

        final int SIZE = 3;

        int[] no = new int[SIZE];
        String[] name = new String[SIZE];
        int[] price = new int[SIZE];
        boolean[] limited_Sale = new boolean[SIZE];
        String[] type = new String[SIZE];

        for (int i = 0; i < SIZE; i++) {
            System.out.print("물품 번호? ");
            no[i] = scanner.nextInt();

            System.out.print("물품 이름? ");
            name[i] = scanner.next();

            System.out.print("물품 가격? ");
            price[i] = scanner.nextInt();

            System.out.print("한정 판매(true/false)? ");
            limited_Sale[i] = scanner.nextBoolean();

            System.out.print("종류(식료품/생활용품/의류/가전제품/리빙)? ");
            type[i] = scanner.next();

        }

        System.out.println("---------------------------------------------------------------------");

        for (int i = 0; i < SIZE; i++) {
            System.out.printf("물품 번호: %d\n", no[i]);
            System.out.printf("물품 이름: %s\n", name[i]);
            System.out.printf("물품 가격: %d\n", price[i]);
            System.out.printf("한정 판매: %b\n", limited_Sale[i]);
            System.out.printf("종류(식료품/생활용품/의류/가전제품/리빙): %s\n", type[i]);
        }

        scanner.close();
    }
}
