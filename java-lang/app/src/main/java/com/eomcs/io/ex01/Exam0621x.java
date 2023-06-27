// 디렉토리에 들어있는 파일(디렉토리) 목록을 꺼낼 때 필터 적용하기 II
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0621x {
  public static void main(String[] args) throws Exception {
    File dir = new File(".");
    File[] files = dir.listFiles(file -> file.isFile() && file.getName().endsWith(".java"));
    // 인터페이스이고 추상메서드가 하나일 경우 람다문법 사용 가능
    for (File file : files) {
      System.out.printf("%s %12d %s\n", file.isDirectory() ? "d" : "-", file.length(),
          file.getName());
    }
  }
}


