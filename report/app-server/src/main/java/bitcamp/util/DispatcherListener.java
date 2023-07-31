package bitcamp.util;

public class DispatcherListener implements ActionListener {
  @Override
  public void service(BreadcrumbPrompt prompt) {
    try {
      prompt.printf("Hello!\n");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
