package bitcamp.util;

public class Menu {
  private String title;
  private ArrayList listeners = new ArrayList();

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
    for (int i = 0; i < listeners.size(); i++) {
      ActionListener listener = (ActionListener) listeners.get(i);
      listener.service(prompt);
    }
  }

}
