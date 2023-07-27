package bitcamp.util;

import java.util.ArrayList;

public class Menu {
  private String title;
  private ArrayList<ActionListener> listeners = new ArrayList<>();

  public Menu(String title) { // title 을 받는 생성자
    this.title = title;
  }

  public Menu(String title, ActionListener listener) {
    this(title); // 생성자에서 생성자를 호출
    this.addActionListener(listener);
  }

  public void addActionListener(ActionListener listener) {
    this.listeners.add(listener);
  }

  public void removeActionListener(ActionListener listener) {
    this.listeners.remove(listener);
  }

  public String getTitle() {
    return title;
  }

  // 메뉴를 실행할 메서드 -> execute()
  public void execute(BreadcrumbPrompt prompt) {
    try {
      for (int i = 0; i < listeners.size(); i++) {
        ActionListener listener = listeners.get(i);
        listener.service(prompt);
      }
    } catch (Exception e) {
      prompt.clear();
      prompt.println(e.getMessage());

    } finally {
      try {
        prompt.end();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

  }

}
