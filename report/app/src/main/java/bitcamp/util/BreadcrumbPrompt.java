package bitcamp.util;

import java.util.Stack;

public class BreadcrumbPrompt extends Prompt {

  private Stack<String> breadcrumbs = new Stack<>();

  public void appendBreadcrumbs(String title) {
    this.breadcrumbs.push(title);
  }

  public void removeBreadcrumbs() {
    this.breadcrumbs.pop();
  }

  public String inputMenu() {
    StringBuilder titleBuilder = new StringBuilder(); // 예) 메인/회원>
    for (int i = 0; i < breadcrumbs.size(); i++) {
      if (titleBuilder.length() > 0) {
        titleBuilder.append("/");
      }
      titleBuilder.append(breadcrumbs.get(i));
    }
    titleBuilder.append("> ");

    return this.inputString(titleBuilder.toString());
  }

}
