package bitcamp.report.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.report.dao.ItemDao;
import bitcamp.report.vo.Item;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/item/add")
public class ItemAddListener implements ItemActionListener {

  ItemDao itemDao;
  SqlSessionFactory sqlSessionFactory;

  public ItemAddListener(ItemDao itemDao, SqlSessionFactory sqlSessionFactory) {
    this.itemDao = itemDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Item item = new Item();
    item.setName(prompt.inputString("물품 이름? "));
    item.setPrice(prompt.inputInt("물품 가격? "));
    item.setType(ItemActionListener.inputType("0", prompt));

    try {
      itemDao.insert(item);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }

}
