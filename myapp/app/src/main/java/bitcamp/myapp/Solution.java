package bitcamp.myapp;

class Solution {
  public static int solution(String ineq, String eq, int n, int m) {
    int answer = 0;

    if (eq.equals(">") || eq.equals("=")) {
      if (n >= m)
        answer = 1;
    } else if (eq.equals("<") || eq.equals("=")) {
      if (n <= m)
        answer = 1;
    } else if (eq.equals(">") || eq.equals("!")) {
      if (n > m)
        answer = 1;
    } else if (eq.equals("<") || eq.equals("!")) {
      if (n < m)
        answer = 1;
    }

    return answer;
  }

  public static void main(String[] args) {

    String ineq = "<";
    String eq = "=";
    int n = 41;
    int m = 78;

    int ret = solution(ineq, eq, n, m);
    System.out.println(ret);
  }
}
