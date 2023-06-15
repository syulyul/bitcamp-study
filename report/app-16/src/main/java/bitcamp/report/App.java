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

        MemberHandler memberHandler = new MemberHandler(prompt, "직원");
        ItemHandler itemHandler = new ItemHandler(prompt, "물품");
        BoardHandler boardHandler = new BoardHandler(prompt, "게시글");
        BoardHandler noticeHandler = new BoardHandler(prompt, "공지");

        printTitle();

        printMenu();

        while (true) {
            String menuNo = prompt.inputString("메인> ");
            if (menuNo.equals("0")) {
                break;
            } else if (menuNo.equals("menu")) {
                printMenu();
            } else if (menuNo.equals("1")) {
                memberHandler.execute();
            } else if (menuNo.equals("2")) {
                itemHandler.execute();
            } else if (menuNo.equals("3")) {
                boardHandler.service();
            } else if (menuNo.equals("4")) {
                noticeHandler.service();
            } else {
                System.out.println("메뉴 번호가 옳지 않습니다");
            }
        }

        prompt.close();
    }

    static void printMenu() {
        System.out.println("1. 직원");
        System.out.println("2. 물품");
        System.out.println("3. 게시글");
        System.out.println("4. 공지");
        System.out.println("0. 종료");
    }

    static void printTitle() {
        System.out.println("마트 관리 시스템");
        System.out.println("---------------------------------------------------------------------");
    }
}
