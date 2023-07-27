package bitcamp.report.handler;

// 핸들러 사용 규칙
// => 즉 메서드 호출 규칙을 정의
// => 메서드 시그너처와 리턴 타입을 정의
// 시그너처(signature)? 메서드명, 파라미터 목록
// => 메서드 몸체(method body)는 작성하지 않는다.
// 왜? 호출 규칙만 정의하는 것이기 때문이다.
//

public interface Handler {
  // 규칙은 무조건 공개되어야므로 안 적어도 기본이 public
  // 몸체가 존재하지 않으므로 추상적 => abstract 제거해도 기본이 abstract
  void execute();
}

