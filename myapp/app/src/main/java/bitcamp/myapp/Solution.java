package bitcamp.myapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public static int solution(String str1, String str2) {
    int answer = 0;
    int k = 0;
    for (int i = 0; i < str2.length() - str1.length() + 1; i++) {
      String s = "";
      for (int j = k; j < k + str1.length(); j++) {
        s += str2.charAt(j);
      }
      if (s.equals(str1)) {
        
      }
      k++;
    }

    return answer;
  }

  public static void main(String[] args) {

    String str1 = "abc";
    String str2 = "aabcc";

    int ret = solution(str1, str2);
    System.out.println(ret);
  }
}

