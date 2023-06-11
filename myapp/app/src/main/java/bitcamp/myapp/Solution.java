package bitcamp.myapp;

class Solution {
  public static int solution(String my_string, String target) {

    int answer = 0;
    String ms = "";
    String t = "";

    for (int i = 0; i < my_string.length(); i++) {
      for (int j = 0; j < target.length(); j++) {
        if (my_string.charAt(i) == target.charAt(j)) {
          ms += my_string.charAt(i);
          t += target.charAt(j);
          if (ms.equals(t))
            answer = 1;
        }
      }
    }

    return answer;
  }

  public static void main(String[] args) {

    String my_String = "banana";
    String target = "ana";

    int ret = solution(my_String, target);
    System.out.println(ret);
  }
}
