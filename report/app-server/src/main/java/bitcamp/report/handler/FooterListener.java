package bitcamp.report.handler;

import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class FooterListener implements ActionListener {
  @Override
  public void service(BreadcrumbPrompt prompt) {
    prompt.printf("Copyright \u00a9 by 네클7기-----------------------------\n");
  }
}
