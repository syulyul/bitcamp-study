// package bitcamp.test.step03;

// // import bitcamp.util.Calculator; // import 는 컴파일하지 않음 => bitcamp.util.Calculator 를 복사하는 역할

// // 소스 코드에서 Calculator 클래스는 bitcamp.util 패키지에 소속된 클래스를 가리킨다.
// //import bitcamp.util.Calculator; // 컴파일러는 import 문장을 바이트 코드로 바꾸지 않음 // 컴파일 시 없어짐

// // 메소드 활용 (static 변수를 사용하는 이유)

// // (2) 낱개의 변수 재사용
// // (3) 배열 사용
// public class App { // 클래스 밖에 변수 선언 불가

//   public static void main(String[] args) {
//     String[] name = new String[10];
//     int[] kor = new int[10];
//     int[] eng = new int[10];
//     int[] math = new int[10];
//     int[] sum = new int[10];
//     float[] aver = new float[10];
//     int length = 0;

//     name[length] = "홍길동";
//     kor[length] = 100;
//     eng[length] = 100;
//     math[length] = 100;
//     sum[length] = kor[length] + eng[length] + math[length];
//     aver[length] = sum[length] / 3f;

//     // System.out.printf("%s: 합계=%d, 평균=%.1f\n",
//     // name[length], sum[length], aver[length]);

//     length++;

//     name[length] = "임꺽정";
//     kor[length] = 90;
//     eng[length] = 90;
//     math[length] = 90;
//     sum[length] = kor[length] + eng[length] + math[length];
//     aver[length] = sum[length] / 3f;

//     // System.out.printf("%s: 합계=%d, 평균=%.1f\n",
//     // name[length], sum[length], aver[length]);

//     length++;

//     name[length] = "유관순";
//     kor[length] = 80;
//     eng[length] = 80;
//     math[length] = 80;
//     sum[length] = kor[length] + eng[length] + math[length];
//     aver[length] = sum[length] / 3f;

//     // System.out.printf("%s: 합계=%d, 평균=%.1f\n",
//     // name[length], sum[length], aver[length]);

//     length++;

//     for (int i = 0; i < length; i++) {
//       System.out.printf("%s: 합계=%d, 평균=%.1f\n",
//           name[length], sum[length], aver[length]);
//     }

//   }

// }

package bitcamp.test.step03;

// 1) 낱개의 변수 사용
// 2) 낱개의 변수 재사용
// 3) 배열 사용
public class App {

  public static void main(String[] args) {
    String[] name = new String[10];
    int[] kor = new int[10];
    int[] eng = new int[10];
    int[] math = new int[10];
    int[] sum = new int[10];
    float[] aver = new float[10];
    int length = 0;

    name[length] = "홍길동";
    kor[length] = 100;
    eng[length] = 100;
    math[length] = 100;
    sum[length] = kor[length] + eng[length] + math[length];
    aver[length] = sum[length] / 3f;
    length++;

    name[length] = "임꺽정";
    kor[length] = 90;
    eng[length] = 90;
    math[length] = 90;
    sum[length] = kor[length] + eng[length] + math[length];
    aver[length] = sum[length] / 3f;
    length++;

    name[length] = "유관순";
    kor[length] = 80;
    eng[length] = 80;
    math[length] = 80;
    sum[length] = kor[length] + eng[length] + math[length];
    aver[length] = sum[length] / 3f;
    length++;

    for (int i = 0; i < length; i++) {
      System.out.printf("%s: 합계=%d, 평균=%.1f\n",
          name[i], sum[i], aver[i]);
    }

  }

}
