package bitcamp.report.vo;

import java.io.Serializable;

public class Item implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final String FOOD = "z";
  public static final String HOUSEHOLD_SUPPLIES = "생활용품";
  public static final String CLOTHES = "의류";
  public static final String HOME_APPLIANCES = "가전제품";
  public static final String LIVING = "리빙";

  private int no;
  private String name;
  private int price;
  private String type;

  public Item() {}

  public Item(int no) {
    this.no = no;
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    Item item = (Item) obj;

    if (this.getNo() != item.getNo()) {
      return false;
    }
    return true;
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
