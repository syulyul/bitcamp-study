package bitcamp.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// 메서드에 붙일 애노테이션

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Bean {

}
