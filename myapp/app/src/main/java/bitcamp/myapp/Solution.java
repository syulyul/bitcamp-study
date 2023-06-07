package bitcamp.myapp;

class Solution {
  public static String solution(String[] arr) {
    String answer = "";

    for (int i = 0; i < arr.length; i++) {
      answer += arr[i];
    }

    return answer;
  }

  public static void main(String[] args) {

    String[] arr = { "a", "b", "c" };
    String ret = solution(arr);
    System.out.println(ret);
  }
}
