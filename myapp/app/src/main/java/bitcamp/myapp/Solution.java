package bitcamp.myapp;

import java.util.*;
class Solution {
  public static int[] solution(int[] arr, int[] delete_list) {
    int[] answer = {};
    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < delete_list.length; j++) {
        if (arr[i] == delete_list[j]) {
          // answer[i] = arr[i];
          continue;
        }
      }
      list.add(arr[i]);
    }
    for (int i = 0; i < list.size(); i++) {
      answer[i] = list.get(i);
    }

    return answer;
  }

  public static void main(String[] args) {
    int[] arr = {293, 1000, 395, 678, 94};
    int[] delete_list = {94, 777, 104, 1000, 1, 12};
    int[] ret = solution(arr, delete_list);
    System.out.println(ret);
    // System.out.println(Arrays.toString(ret));
  }
}
