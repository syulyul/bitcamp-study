package bitcamp.report;

import java.util.Scanner;

public class App {
    
    static Scanner scanner = new Scanner(System.in);
    
    static final int MAX_SIZE = 100;
    static int[] no = new int[MAX_SIZE];
    static String[] name = new String[MAX_SIZE];
    static String[] price = new String[MAX_SIZE];
    // static boolean[] limited_Sale = new boolean[MAX_SIZE];
    static String[] type = new String[MAX_SIZE];
    static int itemId = 1;
    static int length = 0;

    static final String FOOD = "식료품";
    static final String HOUSEHOLD_SUPPLIES = "생활용품";
    static final String CLOTHES = "의류";
    static final String HOME_APPLIANCES = "가전제품";
    static final String LIVING = "리빙";
    
    public static void main(String[] args) {

        printTitle();

        while (length < MAX_SIZE) {
            inputMember();
            if(!promptContinue()){
                break;
            } 
        }

        printMembers();

        scanner.close();
    }

    static void printTitle() {
        System.out.println("마트 물품 관리 시스템");
        System.out.println("---------------------------------------------------------------------");
    }

    static void inputMember() {
        name[length] = prompt("물품 이름? ");
        price[length] = prompt("물품 가격? ");
        // System.out.print("한정 판매(true/false)? ");
        // limited_Sale[i] = scanner.nextBoolean();
            
        loop: while(true) {
            String typeNo = prompt("종류:\n" +
            "  1. 식료품\n" +
            "  2. 생활용품\n" +
            "  3. 의류\n" +
            "  4. 가전제품\n" +
            "  5. 리빙\n" +
            "> ");
            
            switch (typeNo) {
                case "1":
                    type[length] = FOOD;
                    break loop;
                case "2":
                    type[length] = HOUSEHOLD_SUPPLIES;
                    break loop;
                case "3":
                    type[length] = CLOTHES;
                    break loop;
                case "4":
                    type[length] = HOME_APPLIANCES;
                    break loop;
                case "5":
                    type[length] = LIVING;
                    break loop;
                default:
                    System.out.println("무효한 번호입니다.");
            }
        }

        no[length] = itemId++;
        length++;
    }

    static boolean promptContinue() {
        System.out.print("계속 하시겠습니까?(Y/n) ");
        String response = scanner.nextLine();
        if(!response.equals("") && !response.equalsIgnoreCase("Y")) {
            return false;
        }
        return true;
    }

    static void printMembers() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("물품 번호, 물품 이름, 물품 가격, 종류");
        System.out.println("---------------------------------------------------------------------");
        
        for (int i = 0; i < length; i++) {
            System.out.printf("%d, %s, %s, %s\n", no[i], name[i], price[i], type[i]);
        }
    }

    static String prompt(String inputTitle) {
        System.out.print(inputTitle);
        return scanner.nextLine();
    }
}

