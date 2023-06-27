package bitcamp.util;

public class ListIterator<E> implements Iterator<E> {

  // 이 객체가 목록에서 값을 꺼낼 수 있도록
  // 목록 객체 주소를 보관한다.
  List<E> list;

  // 어느 인덱스에서 항목을 꺼낼 것인지 그 인덱스를 보관한다.
  int cursor;

  public ListIterator(List<E> list) {
    this.list = list;
  } // 이렇게 하라

  @Override
  public boolean hasNext() {
    return cursor < list.size();
  }

  @Override
  public E next() {
    return list.get(cursor++);
  }
}
