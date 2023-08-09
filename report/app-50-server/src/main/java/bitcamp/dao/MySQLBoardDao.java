package bitcamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bitcamp.report.dao.BoardDao;
import bitcamp.report.vo.Board;
import bitcamp.report.vo.Member;

public class MySQLBoardDao implements BoardDao {

  Connection con;
  int category;

  public MySQLBoardDao(Connection con, int category) {
    this.con = con;
    this.category = category;
  }

  @Override
  public void insert(Board board) {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into report_board(title,content,writer,password,category)"
        + " values(?,?,?,sha1(?),?)")) {
      
      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getWriter().getNo());
      stmt.setString(4, board.getPassword());
      stmt.setInt(5, this.category);

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  /*
   select 
     b.board_no, 
     b.title, 
     b.view_count, 
     b.created_date, 
     m.member_no, 
     m.name
   from 
     report_board b inner join report_member m on b.writer=m.member_no
   where 
     category=1
   order by 
     board_no desc
   */
  @Override
  public List<Board> list() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select" +
          "  b.board_no, " +
          "  b.title, " +
          "  b.view_count, " +
          "  b.created_date, " + 
          "  m.member_no, " +
          "  m.name " +
          " from  " +
          "  report_board b inner join report_member m on b.writer=m.member_no " +
          " where " +
          "  category=? " +
          " order by " +
          "  board_no desc "
        )) {

      stmt.setInt(1, this.category);
      
      try (ResultSet rs = stmt.executeQuery()) {

        List<Board> list = new ArrayList<>();
  
        while (rs.next()) {
          Board b = new Board();
          b.setNo(rs.getInt("board_no"));
          b.setTitle(rs.getString("title"));
          b.setViewCount(rs.getInt("view_count"));
          b.setCreatedDate(rs.getTimestamp("created_date"));
          
          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));
          b.setWriter(writer);
         
  
          list.add(b);
        }
  
        return list;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Board findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select" +
            "  b.board_no, " +
            "  b.title, " +
            "  b.content, " +
            "  b.view_count, " +
            "  b.created_date, " + 
            "  m.member_no, " +
            "  m.name " +
            " from  " +
            "  report_board b inner join report_member m on b.writer=m.member_no " +
            " where " +
            "  category=? " +
            "  and board_no=? "
        )) {
      
      stmt.setInt(1, this.category);
      stmt.setInt(2, no);

      try (ResultSet rs =stmt.executeQuery()) {
      
        if (rs.next()) {
          Board b = new Board();
          b.setNo(rs.getInt("board_no"));
          b.setTitle(rs.getString("title"));
          b.setContent(rs.getString("content"));
          
          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));
          b.setWriter(writer);
          
          b.setViewCount(rs.getInt("view_count"));
          b.setCreatedDate(rs.getTimestamp("created_date"));
          
          stmt.executeUpdate("update report_board set"
              + " view_count=view_count + 1"
              + " where board_no=" + no);
  
          return b;
        }
  
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Board board) {
    try (PreparedStatement stmt = con.prepareStatement(
        "update report_board set"
        + " title=?,"
        + " content=?"
        + " where category=? and board_no=? and writer=?")) {
      
      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, this.category);
      stmt.setInt(4, board.getNo());
      stmt.setInt(5, board.getWriter().getNo());
      
        return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(Board board) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from report_board"
        + " where category=? and board_no=? and writer=?")) {
      
      stmt.setInt(1, this.category);
      stmt.setInt(2, board.getNo());
      stmt.setInt(3, board.getWriter().getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}