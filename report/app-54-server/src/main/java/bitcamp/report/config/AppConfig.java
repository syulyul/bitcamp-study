package bitcamp.report.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.util.Bean;
import bitcamp.util.ComponentScan;
import bitcamp.util.SqlSessionFactoryProxy;

// Application을 실행하는데 필요한 객체를 설정하는 일을 한다.
//
@ComponentScan(basePackages = {"bitcamp.report.dao", "bitcamp.report.handler"})
public class AppConfig {

  // Mybatis 객체 준비
  @Bean
  public SqlSessionFactory SqlSessionFactory() throws Exception {
    return new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("bitcamp/report/config/mybatis-config.xml")));
  }

}
