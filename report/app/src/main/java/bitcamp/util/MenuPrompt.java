package bitcamp.util;

public class MenuPrompt extends Prompt {
  private Stack breadcrumbs = new Stack();

  public void appendBreadcrumbs(String title) {
    breadcrumbs.push(title);
  }

  public void removeBreadcrumbs() {
    breadcrumbs.pop();
  }

  public String inputMenu() {
    String title = ""; // ex) 메인/회원>
    for (int i = 0; i < breadcrumbs.size(); i++) {
      if (title.length() > 0) {
        title += "/";
      }
      title += breadcrumbs.get(i);
    }
    title += ">";
    return this.inputString(title);
  }
}
