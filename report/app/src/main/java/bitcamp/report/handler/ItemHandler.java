package bitcamp.report.handler;

import bitcamp.report.vo.Item;
import bitcamp.util.Prompt;

public class ItemHandler implements Handler {

  private ArrayList list = new ArrayList();
  private Prompt prompt;
  private String title;

  // 생성자: 인스턴스를 사용할 수 있도록 유효한 값으로 초기화시키는 일을 함
  // => 필요한 값을 외부에서 받고 싶으면 파라미터를 선언
  public ItemHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  public void execute() {
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("%s> ", title);
      if (menuNo.equals("0")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        this.inputItem();
      } else if (menuNo.equals("2")) {
        this.printItems();
      } else if (menuNo.equals("3")) {
        this.viewItem();
      } else if (menuNo.equals("4")) {
        this.updateItem();
      } else if (menuNo.equals("5")) {
        this.deleteItem();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  private static void printMenu() {
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 조회");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 종료");
  }

  private void inputItem() {
    Item item = new Item();
    item.setName(this.prompt.inputString("물품 이름? "));
    item.setPrice(this.prompt.inputInt("물품 가격? "));
    item.setType(inputType("0"));

    this.list.add(item);
  }

  private void printItems() {
    System.out.println("---------------------------------------------------------------------");
    System.out.println("물품 번호, 물품 이름, 물품 가격, 종류");
    System.out.println("---------------------------------------------------------------------");

    Object[] arr = this.list.list();
    for (Object obj : arr) {
      Item item = (Item) obj;
      System.out.printf("%d, %s, %d, %s\n", item.getNo(), item.getName(), item.getPrice(),
          item.getType());
    }
  }

  private void viewItem() {
    int itemNo = this.prompt.inputInt("물품 번호? ");

    Item item = (Item) this.list.get(new Item(itemNo));
    if (item == null) {
      System.out.println("해당 번호의 물품이 없습니다!");
      return;
    }

    System.out.printf("물품 이름: %s\n", item.getName());
    System.out.printf("물품 가격: %d\n", item.getPrice());
    System.out.printf("물품 종류: %s\n", item.getType());
  }

  private void updateItem() {
    int itemNo = this.prompt.inputInt("물품 번호? ");

    Item item = (Item) this.list.get(new Item(itemNo));
    if (item == null) {
      System.out.println("해당 번호의 물품이 없습니다!");
      return;
    }

    item.setName(this.prompt.inputString("물품 이름(%s)? ", item.getName()));
    item.setPrice(this.prompt.inputInt("물품 가격(%d)? ", item.getPrice()));
    item.setType(inputType(item.getType()));
  }

  private String inputType(String type) {
    String label;
    if (type == "0") {
      label = "물품 종류?\n";
    } else {
      label = String.format("물품 종류(%s)?\n", type);
    }
    while (true) {
      String typeNo = this.prompt.inputString(
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

  private void deleteItem() {
    // 삭제하려는 물품의 정보가 들어있는 인덱스 알아내기

    if (!this.list.delete(new Item(prompt.inputInt("물품 번호? ")))) {
      System.out.println("해당 번호의 물품이 없습니다!");
    }
  }

}
