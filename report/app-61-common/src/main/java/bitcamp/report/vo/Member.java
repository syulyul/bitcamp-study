package bitcamp.report.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Member implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final char MANAGER = '0';
  public static final char STAFF = '1';

  private int no;
  private String name;
  private String phone;
  private String password;
  private char position;
  private Date createdDate;
  private String photo;

  public Member() {}

  @Override
  public int hashCode() {
    return Objects.hash(no);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Member other = (Member) obj;
    return no == other.no;
  }

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

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

}
