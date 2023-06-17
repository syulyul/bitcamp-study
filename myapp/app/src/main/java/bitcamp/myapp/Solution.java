package bitcamp.myapp;

import java.util.*;

class Solution {
  public static int[] solution(int start, int end) {
    int[] answer = new int[start - end + 1];

    int k = 0;
    for (int i = start; i >= end; i--) {
      answer[k++] = i;
    }

    return answer;
  }

  public static void main(String[] args) {

    int start = 10;
    int end = 3;

    int[] ret = solution(start, end);
    // System.out.println(ret);
    System.out.println(Arrays.toString(ret));
  }
}
