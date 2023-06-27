package bitcamp.util;

// 목록에서 값을 꺼낼 때 호출할 메서의 규칙을 정의
public interface Iterator<E> {
  boolean hasNext(); // 값을 꺼내기 전에 꺼낼 값이 있는지 확인할 때 호출한다.

  E next(); // 목록에서 값을 꺼낸다.
}
