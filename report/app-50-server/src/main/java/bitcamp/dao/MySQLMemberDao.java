package bitcamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.vo.Member;

public class MySQLMemberDao implements MemberDao {

  Connection con;

  public MySQLMemberDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member member) {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into report_member(name,phone,password,position)"
        + " values(?,?,sha1(?),?)")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getPhone());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, String.valueOf(member.getPosition()));

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Member> list() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no, name, phone, position"
        + " from report_member"
        + " order by name asc");
        ResultSet rs = stmt.executeQuery()) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setPhone(rs.getString("phone"));
        m.setPosition(rs.getString("position").charAt(0));

        list.add(m);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Member findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no, name, phone, position, created_date"
        + " from report_member"
        + " where member_no=?")) {
      
      stmt.setInt(1, no);
    
      try (ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
          Member m = new Member();
          m.setNo(rs.getInt("member_no"));
          m.setName(rs.getString("name"));
          m.setPhone(rs.getString("phone"));
          m.setPosition(rs.getString("position").charAt(0));
          m.setCreatedDate(rs.getDate("created_date"));
  
          return m;
        }
  
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public Member findByPhoneAndPassword(Member param) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no, name, phone, position, created_date"
        + " from report_member"
        + " where phone=? and password=sha1(?)")) {
      
      stmt.setString(1, param.getPhone());
      stmt.setString(2, param.getPassword());
    
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member m = new Member();
          m.setNo(rs.getInt("member_no"));
          m.setName(rs.getString("name"));
          m.setPhone(rs.getString("phone"));
          m.setPosition(rs.getString("position").charAt(0));
          m.setCreatedDate(rs.getDate("created_date"));
  
          return m;
        }
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Member member) {
    try (PreparedStatement stmt = con.prepareStatement(
        "update report_member set"
        + " name=?,"
        + " phone=?,"
        + " password=sha1(?),"
        + " position=?"
        + " where member_no=?")) {
      
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getPhone());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, String.valueOf(member.getPosition()));
      stmt.setInt(5, member.getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from report_member where member_no=?")) {

      stmt.setInt(1, no);
      
      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
