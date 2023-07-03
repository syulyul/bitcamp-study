package bitcamp.report.vo;

import java.io.Serializable;

public class Member implements Serializable, CsvObject {
  private static final long serialVersionUID = 1L;

  // 모든 인스턴스가 공유하는 값은 스태틱 필드에 보관한다.
  public static int userId = 1;

  // 상수는 스태틱 필드로 정의한다.
  // 정보를 다룰 때는 그 정보를 갖고 있는 클래스에 그 기능을 둔다.
  // 필드도 마찬가지이다.
  // => GRASP 패턴: Information Expert
  public static final char MANAGER = '0';
  public static final char STAFF = '1';

  // 인스턴스 필드는 각각 개별적으로 유지해야 하는 값을 저장할 때 사용한다.
  // new 명령을 통해 변수를 Heap 영역에 생성한다.
  private int no;
  private String name;
  private String phone;
  private String password;
  private char position;

  public Member() {
    this.no = userId++;
  }

  public Member(int no) {
    this.no = no;
  }

  public static Member fromCsv(String csv) {
    String[] values = csv.split(",");
    Member member = new Member(Integer.parseInt(values[0]));
    member.setName(values[1]);
    member.setPhone(values[2]);
    member.setPassword(values[3]);
    member.setPosition(values[4].charAt(0));

    if (Member.userId <= member.getNo()) {
      Member.userId = member.getNo() + 1;
    }

    return member;
  }

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%c", this.getNo(), this.getName(), this.getPhone(),
        this.getPassword(), this.getPosition());
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
    Member m = (Member) obj;

    if (this.getNo() != m.getNo()) {
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public char getPosition() {
    return position;
  }

  public void setPosition(char position) {
    this.position = position;
  }

}
