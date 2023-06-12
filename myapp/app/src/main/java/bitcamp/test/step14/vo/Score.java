package bitcamp.test.step14.vo;

public class Score {
  public String name;
  int kor;
  int eng;
  int math;
  private int sum;
  private float aver; // 패키지 안에서만 사용 가능

  public Score(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    this.compute();
  }

  void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }

  // getter: private 으로 접근이 막힌 변수의 값을 리턴해주는 메서드
  public int getSum() { // 값을 꺼내주는 메서드 // 인스턴스 주소를 가지고 호출
    return this.sum;
  }

  // getter: private 으로 접근이 막힌 변수의 값을 리턴해주는 메서드
  public float getAver() {
    return this.aver;
  }

}
