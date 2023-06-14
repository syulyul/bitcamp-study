package bitcamp.myapp;

import java.util.ArrayList;
import java.util.List;

class Solution {
  public static List<Integer> solution(int n) {

    // int[] answer = {};
    List<Integer> list = new ArrayList<>();
    int x = n;
    list.add(n);

    while (x != 1) {
      if ((x & 1) == 0) {
        x /= 2;
        list.add(x);
      } else {
        x = 3 * x + 2;
        list.add(x);
      }
      return list;
    }
    // return answer;
    return list;
  }

  public static void main(String[] args) {

    int n = 10;

    List<Integer> ret = solution(n);
    System.out.println(ret);
  }
}