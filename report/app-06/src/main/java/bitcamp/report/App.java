package bitcamp.report;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("마트 물품 관리 시스템");
        System.out.println("---------------------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);

        final int MAX_SIZE = 100;
        int itemId = 1;
        int length = 0;

        int[] no = new int[MAX_SIZE];
        String[] name = new String[MAX_SIZE];
        int[] price = new int[MAX_SIZE];
        // boolean[] limited_Sale = new boolean[MAX_SIZE];
        String[] type = new String[MAX_SIZE];

        for (int i = 0; i < MAX_SIZE; i++) {

            System.out.print("물품 이름? ");
            name[i] = scanner.next();

            System.out.print("물품 가격? ");
            price[i] = scanner.nextInt();
            
            // System.out.print("한정 판매(true/false)? ");
            // limited_Sale[i] = scanner.nextBoolean();
            
            loop: while(true) {
                System.out.println("종류: ");
                System.out.println("  1. 식료품");
                System.out.println("  2. 생활용품");
                System.out.println("  3. 의류");
                System.out.println("  4. 가전제품");
                System.out.println("  5. 리빙");
                System.out.print("> ");
                String typeNo = scanner.next();
                
                // if (typeNo.equals("1")) {
                //     type[i] = "식료품";
                //     break;
                // } else if (typeNo.equals("2")) {
                //     type[i] = "생활용품";
                //     break;
                // } else if (typeNo.equals("3")) {
                //     type[i] = "의류";
                //     break;
                // } else if (typeNo.equals("4")) {
                //     type[i] = "가전제품";
                //     break;
                // } else if (typeNo. equals("5")) {
                //     type[i] = "리빙";
                //     break;
                // } else {
                //     System.out.println("무효한 번호입니다.");
                // }

                switch (typeNo) {
                    case "1":
                        type[i] = "식료품";
                        break loop;
                    case "2":
                        type[i] = "생활용품";
                        break loop;
                    case "3":
                        type[i] = "의류";
                        break loop;
                    case "4":
                        type[i] = "가전제품";
                        break loop;
                    case "5":
                        type[i] = "리빙";
                        break loop;
                    default:
                        System.out.println("무효한 번호입니다.");
                }
            }

            no[i] = itemId++;

            length++;

            System.out.print("계속 하시겠습니까?(Y/n) ");
            scanner.nextLine();
            String response = scanner.nextLine();
            if(!response.equals("") && !response.equalsIgnoreCase("Y")) {
                break;
            }
        }

        System.out.println("---------------------------------------------------------------------");

        System.out.println("물품 번호, 물품 이름, 물품 가격, 종류");
        System.out.println("---------------------------------------------------------------------");

        for (int i = 0; i < length; i++){
            System.out.printf("%d, %s, %d, %s\n", no[i], name[i], price[i], type[i]);
        }
        
        scanner.close();
    }
}
