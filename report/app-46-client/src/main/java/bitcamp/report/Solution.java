package bitcamp.report;

public class Solution {

  public static int solution(int[] arr1, int[] arr2) {
    int a = 0;
    int b = 0;
    if (arr1.length > arr2.length) {
      return 1;
    } else if (arr1.length < arr2.length) {
      return -1;
    } else if (arr1.length == arr2.length) {
      for (int i = 0; i < arr1.length; i++) {
        a += arr1[i];
        b += arr2[i];
      }
      if (a > b) {
        return 1;
      } else if (a < b) {
        return -1;
      } else {
        return 0;
      }
    } else {
      return 0;
    }
  }

  public static void main(String[] args) {
    int[] b = {1, 2, 3, 4, 5};
    int[] c = {3, 3, 3, 3, 3};
    int a = solution(b, c);
    System.out.println(a);
  }

}
