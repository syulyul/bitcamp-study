package bitcamp.report.dao;

import java.util.List;
import bitcamp.report.vo.Item;

public interface ItemDao {
  void insert(Item item);

  List<Item> findAll();

  Item findBy(int no);

  int update(Item item);

  int delete(int no);
}
