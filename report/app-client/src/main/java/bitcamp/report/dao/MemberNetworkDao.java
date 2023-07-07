package bitcamp.report.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;
import bitcamp.report.vo.Member;

public class MemberNetworkDao implements MemberDao {

  String DataName;
  DataInputStream in;
  DataOutputStream out;

  public MemberNetworkDao(String DataName, DataInputStream in, DataOutputStream out) {
    this.DataName = DataName;
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Member member) {

  }

  @Override
  public List<Member> list() {
    return null;
  }

  @Override
  public Member findBy(int no) {
    return null;
  }

  @Override
  public int update(Member member) {
    return 0;
  }

  @Override
  public int delete(int no) {
    return 0;
  }

}
