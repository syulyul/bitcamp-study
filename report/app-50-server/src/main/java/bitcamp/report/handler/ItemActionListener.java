package bitcamp.report.handler;

import java.io.IOException;
import bitcamp.report.vo.Item;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public interface ItemActionListener extends ActionListener {

  static String inputType(String type, BreadcrumbPrompt prompt) throws IOException {
    String label;
    if (type == "0") {
      label = "물품 종류?\n";
    } else {
      label = String.format("물품 종류(%s)?\n", type);
    }
    while (true) {
      String typeNo = prompt.inputString(label + 
          "  1. 식료품\n" + 
          "  2. 생활용품\n" + 
          "  3. 의류\n" + 
          "  4. 가전제품\n" + 
          "  5. 리빙\n" + "> "
          );

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
}
