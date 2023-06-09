package bitcamp.report;

import bitcamp.report.handler.itemHandler;
import bitcamp.util.Prompt;

public class App {

    public static void main(String[] args) {

        printTitle();

        while (itemHandler.available()) {
            itemHandler.inputItem();
            if (!promptContinue()) {
                break;
            }
        }

        itemHandler.printItems();

        Prompt.close();
    }

    static void printTitle() {
        System.out.println("마트 물품 관리 시스템");
        System.out.println("---------------------------------------------------------------------");
    }

    static boolean promptContinue() {
        String response = Prompt.inputString("계속 하시겠습니까?(Y/n) ");
        if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
            return false;
        }
        return true;
    }
}
