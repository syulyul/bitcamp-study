package bitcamp.report.dao;

import java.util.List;
import bitcamp.report.vo.Member;

public interface MemberDao {
  void insert(Member member);

  List<Member> list();

  Member findBy(int no);

  Member findByPhoneAndPassword(Member m);

  int update(Member member);

  int delete(int no);


}
