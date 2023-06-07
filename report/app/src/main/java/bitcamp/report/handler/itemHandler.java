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

        loop: while (true) {
            String typeNo = Prompt.inputString("종류:\n" +
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

                loop: while (true) {
                    String typeNo = Prompt.inputString("종류(" + type[i] + ")?\n" +
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

                return;
            }
        }
        System.out.println("해당 번호의 물품이 없습니다!");
    }

    public static boolean available() {
        return length < MAX_SIZE;
    }
}
