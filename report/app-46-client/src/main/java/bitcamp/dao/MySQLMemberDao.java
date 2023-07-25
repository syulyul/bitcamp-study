package bitcamp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(String.format(
          "insert into report_member(name,phone,password,position) values('%s','%s','%s','%c')", 
          member.getName(),
          member.getPhone(),
          member.getPassword(),
          member.getPosition()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Member> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_no, name, phone, position from report_member order by name asc")) {

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
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_no, name, phone, position from report_member where member_no=" + no)) {

      if (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setPhone(rs.getString("phone"));
        m.setPosition(rs.getString("position").charAt(0));

        return m;
      }

      return null;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Member member) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
          "update report_member set"
          + " name='%s',"
          + " phone='%s',"
          + " password='%s',"
          + " position='%c'"
          + " where member_no=%d", 
          member.getName(),
          member.getPhone(),
          member.getPassword(),
          member.getPosition(),
          member.getNo()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
          "delete from report_member where member_no=%d",
          no));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
