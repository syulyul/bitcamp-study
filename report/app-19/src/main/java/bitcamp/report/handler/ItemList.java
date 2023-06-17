package bitcamp.report.handler;

import bitcamp.report.vo.Item;

public class ItemList {

  private static final int DEFAULT_SIZE = 100;

  private Item[] items = new Item[DEFAULT_SIZE];
  private int length;

  public boolean add(Item item) {
    if (this.length == items.length) {
      increase();
    }
    this.items[this.length++] = item;
    return true;
  }

  private void increase() {
    Item[] arr = new Item[items.length + (items.length >> 1)];
    for (int i = 0; i < items.length; i++) {
      arr[i] = items[i];
    }
    items = arr;
    System.out.println("배열 확장: " + items.length);
  }

  public Item[] list() {
    Item[] arr = new Item[this.length];

    for (int i = 0; i < this.length; i++) {
      arr[i] = this.items[i];
    }

    return arr;
  }

  public Item get(int no) {
    for (int i = 0; i < this.length; i++) {
      Item item = this.items[i];
      if (item.getNo() == no) {
        return item;
      }
    }
    return null;
  }

  public boolean delete(int no) {
    int deletedIndex = indexOf(no);

    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.items[i] = this.items[i + 1];
    }
    this.items[--this.length] = null;
    return true;
  }

  private int indexOf(int itemNo) {
    for (int i = 0; i < this.length; i++) {
      Item item = this.items[i];
      if (item.getNo() == itemNo) {
        return i;
      }
    }
    return -1;
  }

}
