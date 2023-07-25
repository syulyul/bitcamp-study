package bitcamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;

public class MySQLItemDao implements ItemDao {

  Connection con;

  public MySQLItemDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Item item) {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into report_item(name,price,item_type)"
        + " values(?,?,?)")) {

      stmt.setString(1, item.getName());
      stmt.setInt(2, item.getPrice());
      stmt.setString(3, item.getType());

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Item> list() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select item_no, name, price, item_type"
        + " from report_item"
        + " order by name asc");
        ResultSet rs = stmt.executeQuery()) {

      List<Item> list = new ArrayList<>();

      while (rs.next()) {
        Item item = new Item();
        item.setNo(rs.getInt("item_no"));
        item.setName(rs.getString("name"));
        item.setPrice(rs.getInt("price"));
        item.setType(rs.getString("item_type"));

        list.add(item);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Item findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select item_no, name, price, item_type"
        + " from report_item"
        + " where item_no=?")) {
      
      stmt.setInt(1, no);
    
      try (ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
          Item item = new Item();
          item.setNo(rs.getInt("item_no"));
          item.setName(rs.getString("name"));
          item.setPrice(rs.getInt("price"));
          item.setType(rs.getString("item_type"));
  
          return item;
        }
  
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Item item) {
    try (PreparedStatement stmt = con.prepareStatement(
        "update report_item set"
        + " name=?,"
        + " price=?,"
        + " item_type=?"
        + " where item_no=?")) {
      
      stmt.setString(1, item.getName());
      stmt.setInt(2, item.getPrice());
      stmt.setString(3, item.getType());
      stmt.setInt(4, item.getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from report_item where item_no=?")) {

      stmt.setInt(1, no);
      
      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
