package bitcamp.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.report.dao.BoardDao;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.dao.MemberDao;
import bitcamp.report.dao.MySQLBoardDao;
import bitcamp.report.dao.MySQLItemDao;
import bitcamp.report.dao.MySQLMemberDao;
import bitcamp.report.handler.BoardAddListener;
import bitcamp.report.handler.BoardDeleteListener;
import bitcamp.report.handler.BoardDetailListener;
import bitcamp.report.handler.BoardListListener;
import bitcamp.report.handler.BoardUpdateListener;
import bitcamp.report.handler.ItemAddListener;
import bitcamp.report.handler.ItemDeleteListener;
import bitcamp.report.handler.ItemDetailListener;
import bitcamp.report.handler.ItemListListener;
import bitcamp.report.handler.ItemUpdateListener;
import bitcamp.report.handler.LoginListener;
import bitcamp.report.handler.MemberAddListener;
import bitcamp.report.handler.MemberDeleteListener;
import bitcamp.report.handler.MemberDetailListener;
import bitcamp.report.handler.MemberListListener;
import bitcamp.report.handler.MemberUpdateListener;

public class DispatcherListener implements ActionListener {

  // 객체 보관소
  Map<String, Object> beanContainer = new HashMap<>();

  public DispatcherListener() throws Exception {

    // Mybatis 준비
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("bitcamp/report/config/mybatis-config.xml")));
    beanContainer.put("sqlSessionFactory", sqlSessionFactory);

    // Dao 준비
    MemberDao memberDao = new MySQLMemberDao(sqlSessionFactory);
    ItemDao itemDao = new MySQLItemDao(sqlSessionFactory);
    BoardDao boardDao = new MySQLBoardDao(sqlSessionFactory);
    beanContainer.put("memberDao", memberDao);
    beanContainer.put("itemDao", itemDao);
    beanContainer.put("boardDao", boardDao);

    // Listener 준비
    beanContainer.put("login", new LoginListener(memberDao));

    beanContainer.put("member/add", new MemberAddListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/list", new MemberListListener(memberDao));
    beanContainer.put("member/detail", new MemberDetailListener(memberDao));
    beanContainer.put("member/update", new MemberUpdateListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/delete", new MemberDeleteListener(memberDao, sqlSessionFactory));

    beanContainer.put("item/add", new ItemAddListener(itemDao, sqlSessionFactory));
    beanContainer.put("item/list", new ItemListListener(itemDao));
    beanContainer.put("item/detail", new ItemDetailListener(itemDao));
    beanContainer.put("item/update", new ItemUpdateListener(itemDao, sqlSessionFactory));
    beanContainer.put("item/delete", new ItemDeleteListener(itemDao, sqlSessionFactory));

    beanContainer.put("board/add", new BoardAddListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/list", new BoardListListener(1, boardDao));
    beanContainer.put("board/detail", new BoardDetailListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/update", new BoardUpdateListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/delete", new BoardDeleteListener(1, boardDao, sqlSessionFactory));

    beanContainer.put("notice/add", new BoardAddListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("notice/list", new BoardListListener(2, boardDao));
    beanContainer.put("notice/detail", new BoardDetailListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("notice/update", new BoardUpdateListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("notice/delete", new BoardDeleteListener(2, boardDao, sqlSessionFactory));
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    ActionListener listener = (ActionListener) beanContainer.get(prompt.getAttribute("menuPath"));
    if (listener == null) {
      throw new RuntimeException("해당 요청을 처리할 수 없습니다.");
    }
    listener.service(prompt);
  }

  public Object getBean(String name) {
    return beanContainer.get(name);
  }
}
