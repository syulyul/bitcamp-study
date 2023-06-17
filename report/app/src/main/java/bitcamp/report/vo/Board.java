package bitcamp.report.vo;

public class Board {

  // 모든 인스턴스가 공유하는 값은 스태틱 필드에 보관한다.
  private static int boardNo = 1;

  private int no; // private 이라는 modifier 를 사용하여 접근 제어
  private String title;
  private String content;
  private String writer;
  private String password;
  private int viewCount;
  private long createdDate; // 밀리세컨드

  public Board() { // 생성자
    this.no = boardNo++;
    this.createdDate = System.currentTimeMillis();
  }

  public Board(int no) {
    this.no = no;
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }

    // 위 조건에서 this가 가리키는 인스턴스의 클래스와
    // 파라미터 obj가 가리키는 인스턴스의 클래스가 같다고 결론이 났기 때문에
    // 다음과 같이 obj를 Member 타입으로 형변환한다.
    //
    Board board = (Board) obj;

    if (this.getNo() != board.getNo()) {
      return false;
    }
    // if (this.getName() != null && !this.getName().equals(m.getName())) {
    // return false;
    // }
    // if (this.getPhone() != null && !this.getPhone().equals(m.getPhone())) {
    // return false;
    // }
    // if (this.getPassword() != null && !this.getPassword().equals(m.getPassword())) {
    // return false;
    // }
    // if (this.getPosition() != m.getPosition()) {
    // return false;
    // }
    return true;
  }

  // 겟터/셋터는 인스턴스 필드의 값을 설정하고 꺼내는 메서드다.
  // 보통 외부에서 직접 필드에 접근하는 것을 막았을 때 사용한다.
  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(long createdDate) {
    this.createdDate = createdDate;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
