package bitcamp.report.vo;

public class Item {
  private static int itemId = 1;

  public static final String FOOD = "식료품";
  public static final String HOUSEHOLD_SUPPLIES = "생활용품";
  public static final String CLOTHES = "의류";
  public static final String HOME_APPLIANCES = "가전제품";
  public static final String LIVING = "리빙";

  private int no;
  private String name;
  private int price;
  private String type;

  public Item() {
    this.no = itemId++;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


}
