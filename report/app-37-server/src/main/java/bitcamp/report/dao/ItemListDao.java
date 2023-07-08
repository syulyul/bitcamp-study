package bitcamp.report.dao;

import java.util.ArrayList;
import java.util.List;
import bitcamp.report.vo.Item;
import bitcamp.util.JsonDataHelper;

public class ItemListDao implements ItemDao {
  String filename;
  ArrayList<Item> list = new ArrayList<>();

  public ItemListDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, Item.class);
  }

  @Override
  public void insert(Item item) {
    // 데이터 입력할 때 해당 데이터의 식별 번호는 Dao 에서 관리한다.
    item.setNo(Item.itemId++);
    this.list.add(item);

    // 데이터를 등록할 때마다 즉시 파일에 저장한다.
    JsonDataHelper.saveJson(filename, list);
  }

  @Override
  public List<Item> list() {
    return this.list;
  }

  @Override
  public Item findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Item item = this.list.get(i);
      if (item.getNo() == no) {
        return item;
      }
    }
    return null;
  }

  @Override
  public int update(Item item) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == item.getNo()) {
        list.set(i, item);
        // 데이터를 등록할 때마다 즉시 파일에 저장한다.
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int delete(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        list.remove(i);
        // 데이터를 등록할 때마다 즉시 파일에 저장한다.
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }
}
