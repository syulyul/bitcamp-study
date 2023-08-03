package bitcamp.report.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.report.vo.Member;
import bitcamp.util.Component;

@Component
public class MySQLMemberDao implements MemberDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLMemberDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("bitcamp.report.dao.MemberDao.insert", member);
  }

  @Override
  public List<Member> list() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("bitcamp.report.dao.MemberDao.findAll");
  }

  @Override
  public Member findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("bitcamp.report.dao.MemberDao.findBy", no);
  }

  @Override
  public Member findByPhoneAndPassword(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("bitcamp.report.dao.MemberDao.findByPhoneAndPassword", member);
  }

  @Override
  public int update(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.report.dao.MemberDao.update", member);
  }

  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("bitcamp.report.dao.MemberDao.delete", no);
  }

}
