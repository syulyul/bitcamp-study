package bitcamp.util;

import java.lang.reflect.Array;

public class LinkedList<E> extends AbstractList<E> {

  Node<E> head;
  Node<E> tail;

  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(Integer.valueOf(100)); // index: 0
    list.add(Integer.valueOf(200)); // index: 1
    list.add(Integer.valueOf(300)); // index: 2
    list.add(Integer.valueOf(400)); // index: 3
    list.add(Integer.valueOf(500)); // index: 4

    print(list);

    // System.out.println(list.remove(Integer.valueOf(300)));
    // System.out.println(list.remove(Integer.valueOf(500)));
    // System.out.println(list.remove(Integer.valueOf(100)));
    // System.out.println(list.remove(Integer.valueOf(200)));
    // System.out.println(list.remove(Integer.valueOf(400)));
    // System.out.println(list.remove(Integer.valueOf(600)));

    System.out.println(list.remove(2));
    System.out.println(list.remove(3));
    System.out.println(list.remove(0));
    System.out.println(list.remove(0));
    System.out.println(list.remove(0));

    list.add(Integer.valueOf(1000));
    list.add(Integer.valueOf(2000));

    print(list);

    // System.out.println(list.retrieve(100));
    // System.out.println(list.retrieve(300));
    // System.out.println(list.retrieve(500));
    // System.out.println(list.retrieve(600));
  }

  static void print(LinkedList<Integer> list) {
    Object[] arr = list.toArray();
    for (Object obj : arr) {
      System.out.print(obj);
      System.out.print(", ");
    }
    System.out.println();
  }

  @Override
  public boolean add(E value) {
    // 1. 새 노드를 생성한다.
    Node<E> node = new Node<E>();
    // 2. 새 노드에 값 저장
    node.value = value;

    if (this.head == null) {
      this.head = node;
    } else if (this.tail != null) { // 3. 리스트의 마지막 노드에 새 노드를 연결
      this.tail.next = node;
    }
    this.tail = node;
    this.size++;
    return true;
  }

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[this.size];

    Node<E> cursor = this.head;
    for (int i = 0; i < this.size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }

  // 객체 생성과정이 복잡할 때 팩토리 메서드 디자인 패턴을 사용
  @SuppressWarnings("unchecked")
  @Override
  public <T> T[] toArray(T[] arr) {
    T[] values = null;

    if (arr.length < this.size) {
      // 파라미터로 받은 배열이 목록의 개수 보다 작다면,
      // 새 배열을 만들어 저장한다.
      values = (T[]) Array.newInstance(arr.getClass().getComponentType(), this.size);
    } else {
      // 파라미터로 받은 배열이 목록에 저장된 개수와 같거나 크다면,
      // 파라미터로 받은 배열을 그대로 사용한다.
      values = arr;
    }
    Node<E> cursor = this.head;
    for (int i = 0; i < this.size; i++) {
      arr[i] = (T) cursor.value;
      cursor = cursor.next;
    }
    return values;
  }

  @Override
  public E get(int index) {
    if (!isValid(index)) {
      return null;
    }

    Node<E> cursor = this.head;

    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    return cursor.value;
  }

  public boolean remove(E value) {
    Node<E> prev = null;
    Node<E> cursor = this.head;

    while (cursor != null) {
      if (cursor.value.equals(value)) {

        // 삭제할 노드가 시작 노드라면
        if (prev == null) {
          this.head = cursor.next;

          // 삭제할 노드가 끝 노드라면
          if (this.head == null) {
            this.tail = null;
          }
        } else if (cursor.next == null) { // 삭제할 노드가 끝 노드라면
          this.tail = prev;
          this.tail.next = null;
        } else {
          // 중간 노드라면, 다음 노드의 주소를 이전 노드에 저장한다.
          prev.next = cursor.next;
        }

        this.size--;

        // 가비지 객체를 초기화시켜서 가비지가 인스턴스를 가리키지 않도록 한다.
        cursor.next = null;
        cursor.value = null;
        return true;

      }
      // 현재 cursor가 가리키는 노드를 prev에 보관한다.
      prev = cursor;
      // 현재 커서를 다음 노드로 이동한다.
      cursor = cursor.next;
    }
    return false;
  }

  public E remove(int index) {
    if (!isValid(index)) {
      return null;
    }

    Node<E> prev = null;
    Node<E> cursor = this.head;

    // 삭제하려는 값이 있는 노드까지 이동한다.
    for (int i = 0; i < index; i++) {
      prev = cursor; // 다음 노드로 이동하기 전에 현재 커서가 가리키는 노드를 prev에 보관한다.
      cursor = cursor.next; // 커서를 다음 노드로 이동시킨다.
    }

    // 삭제할 값을 리턴할 수 있도록 보관한다.
    E old = cursor.value;


    // 삭제할 노드가 시작 노드라면
    if (prev == null) {
      head = cursor.next;

      // 삭제할 노드가 끝 노드라면
      if (this.head == null) {
        this.tail = null;
      }
    } else if (cursor.next == null) { // 삭제할 노드가 끝 노드라면
      this.tail = prev;
      this.tail.next = null;
    } else {
      // 중간 노드라면, 다음 노드의 주소를 이전 노드에 저장한다.
      prev.next = cursor.next; // 현재 커서의 다음 노드를 현재 커서의 이전 노드와 연결한다.
    }

    this.size--;

    // 가비지 객체를 초기화시켜서 가비지가 인스턴스를 가리키지 않도록 한다.
    cursor.next = null;
    cursor.value = null;

    return old;
  }



  static class Node<T> {
    T value;
    Node<T> next; // 주소를 저장하는 변수
  }
}
