package bitcamp.report;

import bitcamp.report.handler.MemberHandler;
import bitcamp.report.handler.itemHandler;
import bitcamp.util.Prompt;

public class App {

    public static void main(String[] args) {

        printTitle();

        printMenu();

        while (true) {
            String menuNo = Prompt.inputString("메인> ");
            if (menuNo.equals("99")) {
                break;
            } else if (menuNo.equals("menu")) {
                printMenu();
            } else if (menuNo.equals("1")) {
                MemberHandler.inputMember();
            } else if (menuNo.equals("2")) {
                MemberHandler.printMembers();
            } else if (menuNo.equals("3")) {
                MemberHandler.viewMember();
            } else if (menuNo.equals("4")) {
                MemberHandler.updateMember();
            } else if (menuNo.equals("5")) {
                MemberHandler.deleteMember();
            } else if (menuNo.equals("6")) {
                itemHandler.inputItem();
            } else if (menuNo.equals("7")) {
                itemHandler.printItems();
            } else if (menuNo.equals("8")) {
                itemHandler.viewItem();
            } else if (menuNo.equals("9")) {
                itemHandler.updateItem();
            } else if (menuNo.equals("10")) {
                itemHandler.deleteItem();
            } else {
                System.out.println(menuNo);
            }
        }

        Prompt.close();
    }

    static void printMenu() {
        System.out.println("1. 관리자 등록");
        System.out.println("2. 관리자 목록");
        System.out.println("3. 관리자 조회");
        System.out.println("4. 관리자 변경");
        System.out.println("5. 관리자 삭제");
        System.out.println("6. 물품 등록");
        System.out.println("7. 물품 목록");
        System.out.println("8. 물품 조회");
        System.out.println("9. 물품 변경");
        System.out.println("10. 물품 삭제");
        System.out.println("99. 종료");
    }

    static void printTitle() {
        System.out.println("마트 물품 관리 시스템");
        System.out.println("---------------------------------------------------------------------");
    }
}
