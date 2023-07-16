package bitcamp.util;

import java.util.ArrayList;

public class ThreadPool implements ResourcePool<ManagedThread> {
  ArrayList<ManagedThread> list = new ArrayList<>();

  @Override
  public ManagedThread getResource() {
    if (list.size() == 0) {
      ManagedThread t = new ManagedThread(this);
      System.out.println("새 스레드 생성!");

      t.start();
      System.out.println("새 스레드 시작!");

      // 위에서 생성한 스레드가 바로 실행될 수 있도록,
      // main 스레드는 잠시 CPU 사용권을 반납한다.
      // 스레드가 실행되면 jobBox 객체에 대해 대기 상태가 된다.
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
      }

      System.out.println("새 스레드 리턴!");
      return t;
    }

    System.out.println("기존 스레드 리턴!");
    return list.remove(0);
  }

  @Override
  public void returnResource(ManagedThread resource) {
    list.add(resource);
  }
}
