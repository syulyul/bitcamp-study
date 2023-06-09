package bitcamp.test.step15.vo;

public class Score {
  private String name; // 코드의 일관성을 위해
  int kor;
  int eng;
  int math;
  private int sum; // 필요에 의해 private 외부에서 접근하면 안되기 때문
  private float aver; // 필요에 의해 private

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
  public int getSum() {
    return this.sum;
  }

  // getter: private 으로 접근이 막힌 변수의 값을 리턴해주는 메서드
  public float getAver() {
    return this.aver;
  }

  public String getName() {
    return this.name;
  }

}
