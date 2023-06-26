package bitcamp.util;

public class Queue<E> extends LinkedList<E> {

  public static void main(String[] args) {
    Queue<String> q = new Queue<>();
    q.offer("홍길동");
    q.offer("임꺽정");
    q.offer("유관순");
    q.offer("안중근");
    q.offer("윤봉길");

    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());

    System.out.println(q.poll()); // 에러
  }

  public void offer(E value) {
    this.add(value);
  }

  public E poll() {
    if (this.size() == 0) { // 더 이상 꺼낼 게 없다면
      return null;
    }
    return this.remove(0); // 맨 앞에 것만 꺼냄
  }
}
