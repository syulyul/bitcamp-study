package bitcamp.report.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;
import bitcamp.report.vo.Item;

public class ItemNetworkDao implements ItemDao {

  String DataName;
  DataInputStream in;
  DataOutputStream out;

  public ItemNetworkDao(String DataName, DataInputStream in, DataOutputStream out) {
    this.DataName = DataName;
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Item item) {

  }

  @Override
  public List<Item> list() {
    return null;
  }

  @Override
  public Item findBy(int no) {
    return null;
  }

  @Override
  public int update(Item item) {
    return 0;
  }

  @Override
  public int delete(int no) {
    return 0;
  }
}
