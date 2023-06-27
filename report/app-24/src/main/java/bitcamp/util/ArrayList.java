package bitcamp.util;

import java.lang.reflect.Array;

public class ArrayList<E> implements List<E> {
  private static final int MAX_SIZE = 100;

  private Object[] list = new Object[MAX_SIZE];
  private int length;

  @Override
  // 컴파일러에게 다음 메서드가 수퍼클래스의 메서드를 재정의한 것인지?
  // 또는 인터페이스의 메서드를 구현한 것인지?
  // 검사해달라는 표시다.
  public boolean add(E obj) {
    if (this.length == list.length) {
      increase();
    }
    this.list[this.length++] = obj;
    return true;
  }

  private void increase() {
    Object[] arr = new Object[list.length + (list.length >> 1)];

    for (int i = 0; i < list.length; i++) {
      arr[i] = list[i];
    }
    list = arr;
    System.out.println("배열 확장: " + list.length);
  }

  @Override
  public Object[] toArray() {
    // 리턴할 값을 담을 배열을 생성
    Object[] arr = new Object[this.length];

    // 원본 배열에서 입력된 인스턴스 주소를 꺼내
    // 새 배열에 담는다.
    for (int i = 0; i < this.length; i++) {
      arr[i] = list[i];
    }
    // 새 배열을 리턴한다.
    return arr;
  }

  // 객체 생성과정이 복잡할 때 팩토리 메서드 디자인 패턴을 사용
  @SuppressWarnings("unchecked")
  @Override
  public <T> T[] toArray(T[] arr) {
    T[] values = null;

    if (arr.length < this.length) {
      // 파라미터로 받은 배열이 목록의 개수 보다 작다면,
      // 새 배열을 만들어 저장한다.
      values = (T[]) Array.newInstance(arr.getClass().componentType(), this.length);
    } else {
      // 파라미터로 받은 배열이 목록에 저장된 개수와 같거나 크다면,
      // 파라미터로 받은 배열을 그대로 사용한다.
      values = arr;
    }
    for (int i = 0; i < this.length; i++) {
      values[i] = (T) list[i];
    }
    return values;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E get(int index) {
    if (!isValid(index)) {
      return null;
    }
    return (E) this.list[index];
  }

  @Override
  public boolean remove(E obj) {
    int deletedIndex = indexOf(obj);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.list[i] = this.list[i + 1];
    }

    this.list[--this.length] = null;
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E remove(int index) {
    if (!isValid(index)) {
      return null;
    }

    Object old = this.list[index];

    for (int i = index; i < this.length - 1; i++) {
      this.list[i] = this.list[i + 1];
    }

    this.list[--this.length] = null;
    return (E) old;
  }

  @Override
  public int size() {
    return this.length;
  }

  private boolean isValid(int index) {
    return index >= 0 || index < this.length;
  }

  private int indexOf(E obj) {
    for (int i = 0; i < this.length; i++) {
      Object item = this.list[i];
      if (item.equals(obj)) {
        return i;
      }
    }
    return -1;
  }
}
