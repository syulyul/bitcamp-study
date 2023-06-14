package bitcamp.report.handler;

import bitcamp.report.vo.Item;
import bitcamp.util.Prompt;

public class itemHandler {

  static final int MAX_SIZE = 100;
  static Item[] items = new Item[MAX_SIZE];

  static int length = 0;

  public static void inputItem() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Item item = new Item();
    item.setName(Prompt.inputString("물품 이름? "));
    item.setPrice(Prompt.inputInt("물품 가격? "));
    item.setType(inputType("0"));

    // 위에서 만든 Item 인스턴스의 주소를 잃어버리지 않게 레퍼런스 배열에 담기
    items[length++] = item;
  }

  public static void printItems() {
    System.out.println("---------------------------------------------------------------------");
    System.out.println("물품 번호, 물품 이름, 물품 가격, 종류");
    System.out.println("---------------------------------------------------------------------");

    for (int i = 0; i < length; i++) {
      Item item = items[i];
      System.out.printf("%d, %s, %s, %s\n", item.getNo(), item.getName(), item.getPrice(),
          item.getType());
    }
  }

  public static void viewItem() {
    String itemNo = Prompt.inputString("물품 번호? ");
    for (int i = 0; i < length; i++) {
      Item item = items[i];
      if (item.getNo() == Integer.parseInt(itemNo)) {
        System.out.printf("물품 이름: %s\n", item.getName());
        System.out.printf("물품 가격: %s\n", item.getPrice());
        System.out.printf("물품 종류: %s\n", item.getType());
        return;
      }
    }
    System.out.println("해당 번호의 물품이 없습니다!");
  }

  public static void updateItem() {
    String itemNo = Prompt.inputString("물품 번호? ");
    for (int i = 0; i < length; i++) {
      Item item = items[i];
      if (item.getNo() == Integer.parseInt(itemNo)) {
        System.out.printf("물품 이름(%s)? ", item.getName());
        item.setName(Prompt.inputString(""));
        System.out.printf("물품 가격(%s)? ", item.getPrice());
        item.setPrice(Prompt.inputInt(""));
        item.setType(inputType(item.getType()));
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
    while (true) {
      String typeNo = Prompt.inputString(
          label + "  1. 식료품\n" + "  2. 생활용품\n" + "  3. 의류\n" + "  4. 가전제품\n" + "  5. 리빙\n" + "> ");

      switch (typeNo) {
        case "1":
          return Item.FOOD;
        case "2":
          return Item.HOUSEHOLD_SUPPLIES;
        case "3":
          return Item.CLOTHES;
        case "4":
          return Item.HOME_APPLIANCES;
        case "5":
          return Item.LIVING;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static void deleteItem() {
    // 삭제하려는 물품의 정보가 들어있는 인덱스 알아내기

    int itemNo = Integer.parseInt(Prompt.inputString("물품 번호? "));

    int deletedIndex = indexOf(itemNo);

    if (deletedIndex == -1) {
      System.out.println("해당 번호의 물품이 없습니다!");
      return;
    }

    // 만약 삭제하려는 항목이 마지막 인덱스의 항목이라면 마지막 인덱스의 값만 0으로 초기화 시키기
    // 그 밖에는 해당 인덱스부터 반복하면서 앞 인덱스의 값을 당겨오기
    for (int i = deletedIndex; i < length - 1; i++) {
      items[i] = items[i + 1];
    }

    // 배열의 맨 마지막 초기화
    items[length - 1] = null;

    length--;

    System.out.println("삭제했습니다.");
    return;
  }

  private static int indexOf(int itemNo) {
    for (int i = 0; i < length; i++) {
      Item item = items[i];
      if (item.getNo() == itemNo) {
        return i;
      }
    }
    return -1;
  }

  public static boolean available() {
    return length < MAX_SIZE;
  }
}
