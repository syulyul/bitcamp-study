package bitcamp.report;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int MAX_SIZE = 100;
        int itemId = 1;
        int length = 0;

        int[] no = new int[MAX_SIZE];
        String[] name = new String[MAX_SIZE];
        int[] price = new int[MAX_SIZE];
        // boolean[] limited_Sale = new boolean[MAX_SIZE];
        String[] type = new String[MAX_SIZE];

        printTitle();

        for (int i = 0; i < MAX_SIZE; i++) {
            inputMember(scanner, i, name, price, type, no, itemId++);
            length++;
            if(!promptContinue(scanner)){
                break;
            } 
        }

        printMembers(length, no, name, price, type);

        scanner.close();
    }

    static void printTitle() {
        System.out.println("마트 물품 관리 시스템");
        System.out.println("---------------------------------------------------------------------");
    }

    static void inputMember(Scanner scanner, int i, String[] name, 
            int[] price, String[] type, int[] no, int typeId) {

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
            scanner.nextLine(); // 입력 받은 후 남아있는 \n 제거

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
        no[i] = typeId;
    }

    static boolean promptContinue(Scanner scanner) {
        System.out.print("계속 하시겠습니까?(Y/n) ");
        String response = scanner.nextLine();
        if(!response.equals("") && !response.equalsIgnoreCase("Y")) {
            return false;
        }
        return true;
    }

    static void printMembers(int length, int[] no, String[] name, int[] price, String[] type) {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("물품 번호, 물품 이름, 물품 가격, 종류");
        System.out.println("---------------------------------------------------------------------");
        
        for (int i = 0; i < length; i++) {
            System.out.printf("%d, %s, %d, %s\n", no[i], name[i], price[i], type[i]);
        }
    }
}

