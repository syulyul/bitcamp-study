package bitcamp.report.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.report.vo.Item;

public class MySQLItemDao implements ItemDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLItemDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Item item) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("bitcamp.report.dao.ItemDao.insert", item);
  }

  @Override
  public List<Item> list() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("bitcamp.report.dao.ItemDao.findAll");
  }

  @Override
  public Item findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("bitcamp.report.dao.ItemDao.findBy", no);
  }

  @Override
  public int update(Item item) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.report.dao.ItemDao.update", item);
  }

  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("bitcamp.report.dao.ItemDao.delete", no);
  }

}
