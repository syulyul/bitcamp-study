package bitcamp.report.handler;

import bitcamp.util.Prompt;

public class itemHandler {

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

    public static void inputItem() {
        if (!available()) {
            System.out.println("더이상 입력할 수 없습니다!");
            return;
        }

        name[length] = Prompt.inputString("물품 이름? ");
        price[length] = Prompt.inputString("물품 가격? ");
        // System.out.print("한정 판매(true/false)? ");
        // limited_Sale[i] = scanner.nextBoolean();
        type[length] = inputType("0");

        no[length] = itemId++;
        length++;
    }

    public static void printItems() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("물품 번호, 물품 이름, 물품 가격, 종류");
        System.out.println("---------------------------------------------------------------------");

        for (int i = 0; i < length; i++) {
            System.out.printf("%d, %s, %s, %s\n", no[i], name[i], price[i], type[i]);
        }
    }

    public static void viewItem() {
        String itemNo = Prompt.inputString("물품 번호? ");
        for (int i = 0; i < length; i++) {
            if (no[i] == Integer.parseInt(itemNo)) {
                System.out.printf("물품 이름: %s\n", name[i]);
                System.out.printf("물품 가격: %s\n", price[i]);
                System.out.printf("물품 종류: %s\n", type[i]);
                return;
            }
        }
        System.out.println("해당 번호의 물품이 없습니다!");
    }

    public static void updateItem() {
        String itemNo = Prompt.inputString("물품 번호? ");
        for (int i = 0; i < length; i++) {
            if (no[i] == Integer.parseInt(itemNo)) {
                System.out.printf("물품 이름(%s)? ", name[i]);
                name[i] = Prompt.inputString("");
                System.out.printf("물품 가격(%s)? ", price[i]);
                price[i] = Prompt.inputString("");
                type[i] = inputType(type[i]);
                return;
            }
        }
        System.out.println("해당 번호의 물품이 없습니다!");
    }

    private static String inputType(String type) {
        String label;
        if (type == "0") {
            label = "물품 종류?\n";
        } else {
            label = String.format("물품 종류(%s)?\n", type);
        }
        loop: while (true) {
            String typeNo = Prompt.inputString(label +
                    "  1. 식료품\n" +
                    "  2. 생활용품\n" +
                    "  3. 의류\n" +
                    "  4. 가전제품\n" +
                    "  5. 리빙\n" +
                    "> ");

            switch (typeNo) {
                case "1":
                    return FOOD;
                case "2":
                    return HOUSEHOLD_SUPPLIES;
                case "3":
                    return CLOTHES;
                case "4":
                    return HOME_APPLIANCES;
                case "5":
                    return LIVING;
                default:
                    System.out.println("무효한 번호입니다.");
            }
        }
    }

    public static void deleteItem() {
        // 삭제하려는 물품의 정보가 들어있는 인덱스 알아내기

        int itemNo = Integer.parseInt(Prompt.inputString("물품 번호? "));

        int deletedIndex = indexOf(itemNo);

        // // 입력 받은 번호를 가지고 배열에서 해당 물품을 찾아야 함
        // for (int i = 0; i < length; i++) {
        // if (no[i] == Integer.parseInt(itemNo)) {
        // deletedIndex = i;
        // break;
        // }
        // }

        if (deletedIndex == -1) {
            System.out.println("해당 번호의 물품이 없습니다!");
            return;
        }

        // if (deletedIndex < (length - 1)) {
        // // 만약 삭제하려는 항목이 마지막 인덱스의 항목이라면 마지막 인덱스의 값만 0으로 초기화 시키기
        // no[deletedIndex] = 0;
        // name[deletedIndex] = null;
        // price[deletedIndex] = null;
        // type[deletedIndex] = null;
        // } else {
        // 그 밖에는 해당 인덱스부터 반복하면서 앞 인덱스의 값을 당겨오기
        for (int i = deletedIndex; i < length - 1; i++) {
            no[i] = no[i + 1];
            name[i] = name[i + 1];
            price[i] = price[i + 1];
            type[i] = type[i + 1];
        }

        // 배열의 맨 마지막 초기화
        no[length - 1] = 0;
        name[length - 1] = null;
        price[length - 1] = null;
        type[length - 1] = null;
        // }
        // length 하나 줄이기
        length--;

        System.out.println("삭제했습니다.");
        return;
    }

    private static int indexOf(int itemNo) {
        for (int i = 0; i < length; i++) {
            if (no[i] == itemNo) {
                return i;
            }
        }
        return -1;
    }

    public static boolean available() {
        return length < MAX_SIZE;
    }
}
