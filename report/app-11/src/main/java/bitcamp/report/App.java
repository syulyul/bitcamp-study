package bitcamp.report;

import bitcamp.report.handler.itemHandler;
import bitcamp.util.Prompt;

public class App {

    public static void main(String[] args) {

        printTitle();

        printMenu();

        while (true) {
            String menuNo = Prompt.inputString("메인> ");
            if (menuNo.equals("6")) {
                break;
            } else if (menuNo.equals("menu")) {
                printMenu();
            } else if (menuNo.equals("1")) {
                itemHandler.inputItem();
            } else if (menuNo.equals("2")) {
                itemHandler.printItems();
            } else if (menuNo.equals("3")) {
                itemHandler.viewItem();
            } else if (menuNo.equals("4")) {
                itemHandler.updateItem();
            } else if (menuNo.equals("5")) {
                itemHandler.deleteItem();
            } else {
                System.out.println(menuNo);
            }
        }

        Prompt.close();
    }

    static void printMenu() {
        System.out.println("1. 물품 등록");
        System.out.println("2. 물품 목록");
        System.out.println("3. 물품 조회");
        System.out.println("4. 물품 변경");
        System.out.println("5. 물품 삭제");
        System.out.println("6. 종료");
    }

    static void printTitle() {
        System.out.println("마트 물품 관리 시스템");
        System.out.println("---------------------------------------------------------------------");
    }
}
