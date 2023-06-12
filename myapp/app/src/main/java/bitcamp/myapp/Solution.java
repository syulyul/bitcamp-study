package bitcamp.myapp;

class Solution {
  public static String solution(String rny_string) {

    String answer = "";

    for (int i = 0; i < rny_string.length(); i++) {
      if (rny_string.charAt(i) == 'r' && rny_string.charAt(i + 1) == 'n') {
        answer += rny_string.charAt(i);
      }
    }

    return answer;
  }

  public static void main(String[] args) {

    String rny_String = "masterpiece";

    String ret = solution(rny_String);
    System.out.println(ret);
  }
}
