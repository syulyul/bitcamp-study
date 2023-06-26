package bitcamp.util;

public class Test {
  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<>();
    names.add(new String("홍길동"));
    names.add("임꺽정");
    names.add("유관순");
    names.add("안중근");

    // Object[] arr = names.toArray();

    String[] arr = new String[names.size()];
    String[] temp = names.toArray(arr);

    System.out.println(arr == temp);

    for (Object item : temp) {
      System.out.println(item); // arr[i] 의 실제 타입은 String 이다.
    }
  }
}
