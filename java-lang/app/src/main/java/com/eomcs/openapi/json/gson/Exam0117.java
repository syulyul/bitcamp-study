// 메서드 chaining call - before
package com.eomcs.openapi.json.gson;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class Exam0117 {
  public static void main(String[] args) {
    
    class Member {
      int no;
      String name;
      String email;
      String password;
      boolean working;
      public int getNo() {return no;}
      public void setNo(int no) {this.no = no;}
      public String getName() {return name;}
      public void setName(String name) {this.name = name;}
      public String getEmail() {return email;}
      public void setEmail(String email) {this.email = email;}
      public String getPassword() {return password;}
      public void setPassword(String password) {this.password = password;}
      public boolean isWorking() {return working;}
      public void setWorking(boolean working) {this.working = working;}
      
      
      public Member no(int no) {this.no = no; return this;}
      public Member name(String name) {this.name = name; return this;}
      public Member email(String email) {this.email = email; return this;}
      public Member password(String password) {this.password = password; return this;}
      public Member working(boolean working) {this.working = working; return this;}  
    }
    
    Member m = new Member()
        .no(100)
        .name("홍길동")
        .email("hong@test.com")
        .password("1111")
        .working(true);
  }
}

// JSON 객체 형식 - { 객체 정보 }
// => { "프로퍼티명" : 값, "프로퍼티명": 값, ...}
//
// 값:
// - 문자열 => "값"
// - 숫자 => 값
// - 논리 => true, false
//
// 프로퍼티명은 반드시 문자열로 표현해야 한다.


