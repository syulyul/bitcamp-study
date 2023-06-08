package bitcamp.myapp;

class Solution {
  public static int solution(int a, int b) {
    int answer = 0;

    String sa = (String.valueOf(a));
    String sb = (String.valueOf(b));

    if ((Integer.parseInt(sa + sb)) > (Integer.parseInt(sb + sa))) {
      answer = (Integer.parseInt(sa + sb));
    } else if ((Integer.parseInt(sa + sb)) == (Integer.parseInt(sb + sa))) {
      answer = (Integer.parseInt(sa + sb));
    } else {
      answer = (Integer.parseInt(sb + sa));
    }

    return answer;
  }

  public static void main(String[] args) {

    int a = 9;
    int b = 91;

    int ret = solution(a, b);
    System.out.println(ret);
  }
}
