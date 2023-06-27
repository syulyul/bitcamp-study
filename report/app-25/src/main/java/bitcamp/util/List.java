package bitcamp.util;

public interface List<E> {
  boolean add(E value);

  E get(int index);

  Object[] toArray();

  // 타입 파라미터를 컴파일러가 일반 클래스로 해석할 수 있으므로 이렇게 써줌
  <T> T[] toArray(T[] arr);
  // toArray가 호출될 때 넘어오는 파라미터로 결정됨
  // 멤버 배열을 넘기면 멤버베열로 리턴

  boolean remove(E value);

  E remove(int index);

  int size();

  Iterator<E> iterator();
}
