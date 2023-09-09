package bitcamp.report.dao;

import bitcamp.report.vo.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberDao {
  int insert(Member member);
  List<Member> findAll();
  Member findBy(int no);
  Member findByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);
  int update(Member member);
  int delete(int no);
}
