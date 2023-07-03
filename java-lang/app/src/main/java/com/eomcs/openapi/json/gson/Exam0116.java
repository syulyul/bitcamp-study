// 메서드 chaining call - after
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

public class Exam0116 {
  public static void main(String[] args) {
    
    class Member {
      int no;
      String name;
      String email;
      String password;
      boolean working;
      
      public int getNo() {return no;}
      public Member setNo(int no) {this.no = no; return this;}
      public String getName() {return name;}
      public Member setName(String name) {this.name = name; return this;}
      public String getEmail() {return email;}
      public Member setEmail(String email) {this.email = email; return this;}
      public String getPassword() {return password;}
      public Member setPassword(String password) {this.password = password; return this;}
      public boolean isWorking() {return working;}
      public Member setWorking(boolean working) {this.working = working; return this;}
      
    }
    
    Member m = new Member()
        .setNo(100)
        .setName("홍길동")
        .setEmail("hong@test.com")
        .setPassword("1111")
        .setWorking(true);
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


