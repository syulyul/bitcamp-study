package bitcamp.test.step04;

// 1) 낱개의 변수 사용
// 2) 낱개의 변수 재사용
// 3) 배열 사용
// 4) 클래스를 이용하여 데이터 타입 정의
public class App {

  public static void main(String[] args) {
    class Score {
      String name;
      int kor;
      int eng;
      int math;
      int sum;
      float aver;
    } // 설계도

    final int MAX_SIZE = 10;
    Score[] scores = new Score[MAX_SIZE]; // 레퍼런스 배열(주소를 저장)
    int length = 0;

    Score s = new Score(); // 스코어 설계도에 따라서 인스턴스 생성
    s.name = "홍길동";
    s.kor = 100;
    s.eng = 100;
    s.math = 100;
    s.sum = s.kor + s.eng + s.math;
    s.aver = s.sum / 3f;
    scores[length++] = s; // 인스턴스 주소를 잃어버리지 않기 위해 배열에 담음

    s = new Score(); // 레퍼런스 재활용
    s.name = "임꺽정";
    s.kor = 90;
    s.eng = 90;
    s.math = 90;
    s.sum = s.kor + s.eng + s.math;
    s.aver = s.sum / 3f;
    scores[length++] = s; // 인스턴스 주소를 잃어버리지 않기 위해 배열에 담음

    s = new Score(); // 레퍼런스 재활용
    s.name = "유관순";
    s.kor = 80;
    s.eng = 80;
    s.math = 80;
    s.sum = s.kor + s.eng + s.math;
    s.aver = s.sum / 3f;
    scores[length++] = s; // 인스턴스 주소를 잃어버리지 않기 위해 배열에 담음

    for (int i = 0; i < length; i++) {
      s = scores[i]; // s는 임시변수
      System.out.printf("%s: 합계=%d, 평균=%.1f\n",
          s.name, s.sum, s.aver);
    }

  }

}
