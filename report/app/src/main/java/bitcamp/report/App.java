package bitcamp.report;

import bitcamp.report.handler.MemberHandler;

import bitcamp.report.handler.BoardHandler;
import bitcamp.report.handler.ItemHandler;
import bitcamp.util.Prompt;

public class App {

    public static void main(String[] args) {

        // 기본 생성자를 이용해 Prompt 인스턴스를 준비
        // => 기본 생성자는 Scanner를 키보드와 연결함
        Prompt prompt = new Prompt();

        MemberHandler memberHandler = new MemberHandler(prompt);
        ItemHandler itemHandler = new ItemHandler(prompt);
        BoardHandler boardHandler = new BoardHandler(prompt);
        BoardHandler noticeHandler = new BoardHandler(prompt);

        printTitle();

        printMenu();

        while (true) {
            String menuNo = prompt.inputString("메인> ");
            if (menuNo.equals("99")) {
                break;
            } else if (menuNo.equals("menu")) {
                printMenu();
            } else if (menuNo.equals("1")) {
                memberHandler.inputMember();
            } else if (menuNo.equals("2")) {
                memberHandler.printMembers();
            } else if (menuNo.equals("3")) {
                memberHandler.viewMember();
            } else if (menuNo.equals("4")) {
                memberHandler.updateMember();
            } else if (menuNo.equals("5")) {
                memberHandler.deleteMember();
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
            } else if (menuNo.equals("11")) {
                boardHandler.inputBoard();
            } else if (menuNo.equals("12")) {
                boardHandler.printBoards();
            } else if (menuNo.equals("13")) {
                boardHandler.viewBoard();
            } else if (menuNo.equals("14")) {
                boardHandler.updateBoard();
            } else if (menuNo.equals("15")) {
                boardHandler.deleteBoard();
            } else if (menuNo.equals("16")) {
                noticeHandler.inputBoard();
            } else if (menuNo.equals("17")) {
                noticeHandler.printBoards();
            } else if (menuNo.equals("18")) {
                noticeHandler.viewBoard();
            } else if (menuNo.equals("19")) {
                noticeHandler.updateBoard();
            } else if (menuNo.equals("20")) {
                noticeHandler.deleteBoard();
            } else {
                System.out.println(menuNo);
            }
        }

        prompt.close();
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
        System.out.println("11. 게시글 등록");
        System.out.println("12. 게시글 목록");
        System.out.println("13. 게시글 조회");
        System.out.println("14. 게시글 변경");
        System.out.println("15. 게시글 삭제");
        System.out.println("16. 공지 등록");
        System.out.println("17. 공지 목록");
        System.out.println("18. 공지 조회");
        System.out.println("19. 공지 변경");
        System.out.println("20. 공지 삭제");
        System.out.println("99. 종료");
    }

    static void printTitle() {
        System.out.println("마트 관리 시스템");
        System.out.println("---------------------------------------------------------------------");
    }
}
