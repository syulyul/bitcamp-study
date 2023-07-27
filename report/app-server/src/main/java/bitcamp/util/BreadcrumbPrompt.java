package bitcamp.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Stack;

public class BreadcrumbPrompt extends Prompt {

  private Stack<String> breadcrumbs = new Stack<>();

  public BreadcrumbPrompt(DataInputStream in, DataOutputStream out) {
    super(in, out);
  }

  public void appendBreadcrumbs(String title) {
    this.breadcrumbs.push(title);
  }

  public void removeBreadcrumbs() {
    this.breadcrumbs.pop();
  }

  public String inputMenu() throws Exception {
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
