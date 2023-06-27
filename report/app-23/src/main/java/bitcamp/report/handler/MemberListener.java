package bitcamp.report.handler;

public class MemberListener implements ActionListener {

  protected List list;
  
  public MemberListener(List list) {
    this.list = list;
  }

  private Member findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }
}
