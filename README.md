# bitcamp-study
비트캠프 네이버클라우드 과정 훈련 소스

# 네이버 클라우드 7기(2023-04-24 ~ 2023-10-27)

## 학습 내용

### 1일(5/22, 월)

- 오전 : 매직에콜 대표 세미나
- 오후
  - 프로그래밍 개론
    - 소프트웨어 아키텍처 소개
    - 네이버클라우드 : VPC, Subnet, ACG, Ubuntu Linux 생성
    - 컴파일 방식 

### 2일(5/23, 화)

- 컴파일 방식(계속)
  - C 소스 컴파일 과정 및 gcc 컴파일러 사용법
- 인터프리터 방식 : NodJS 사용
- 하이브리드 방식 : Java 컴파일 및 실행
- JIT 컴파일과 AOT 컴파일
- 기계어, OS, CPU 관계
- 개발 도구 준비
  - JDK 설치 및 환경 변수 설정
  - VSCode 설치 및 플러그인 설치
  - git 클라이언트 설치

### 3일(5/24, 수)

- 개발 도구 준비(계속)
  - 형상관리 시스템 소개
  - git 사용법 : clone, add, commit, push, pull
  - Gradle 설치 및 사용법 : init, build, run
    - 주요 빌드 도구 소개 및 비교 : Ant, Maven, Gradle
- 자바 컴파일러 사용법
  - '-d' 옵션
  - 소스 폴더와 컴파일된 파일을 두는 폴더를 분리 : src, bin
- 학습용 git 저장소 준비
  - 개인 별 git 저장소 생성 및 복제 : bitcamp-study
  - 저장소와 프로젝트 폴더의 관계 : 1:1, 1:다
- 실습 프로젝트 준비
  - Gradle 을 이용한 실습 프로젝트 폴더 구성
  - 구성된 폴더의 용도
- 자바 프로그래밍
  - 소스 파일과 클래스 블록의 관계 (com.eomcs.lang.ex01.*)
  - 클래스의 접근 범위 : public 과 (default)
  - Entry Point : main() 메서드

### 4일(5/25, 목)

- Git 사용법
  - .gitignore 파일 소개
  - 'gitignore.io' 사이트에서 .gitignore 설정 내용 만들기
- 자바 프로그래밍
  - 문자열, 문자, 논리, 정수, 부동소수점 표기법
  - 정수 리터럴
    - 각 진수별 표현방법
    - 2진수로 변환하는 다양한 방법
      - sign-Magnitude, 1's Complement,2's Complement, Excess-K
  - 부동소수점 리터럴
    - 32비트 크기의 2진수로 바꾸는 방법
    - 64비트 크기의 2진수로 바꾸는 방법
- 프로젝트 실습
  - 1. 자바 프로젝트 준비하기
  - 2. 리터럴을 사용하여 데이터 출력하기

### 5일(5/26, 금)
  
- 자바 프로그래밍
  - 문자 리터럴
    - 2진수롤 변환하는 방법
      - ASCII, ISO-8859-1, KSC-5601(완성형), 조합형, MS-949, Unicode, UTF-8
  - 변수 사용법
    - JVM이 관리하는 메모리 영역 소개
    - 변수 선언, 변수에 값 할당
    - Primitive Data Type 소개: byte, short, int, long, char, float, double, boolean
- 프로젝트 실습
  - 3. 변수를 사용하여 데이터를 저장하기

### 6일(5/30, 화)

- 자바 프로그래밍
  - 표준 입출력 API(도구) 사용법(com.eomcs.lang.ex99)
  - 변수 사용법(com.eomcs.lang.ex04)
- 프로젝트 실습
  - 4. 키보드로 값을 입력 받기
  - 5. 배열을 이용하여 여러 개의 회원 데이터를 입력 받기

### 7일(5/31,수)

- 자바 프로그래밍
  - 배열 레퍼런스와 인스턴스
  - Heap과 JVM Stack 메모리
  - 가비지와 가비지 컬렉터, 레퍼런스 카운트
  - 연산자 사용법(com.eomcs.lang.ex05)

### 8일(6/1,목)

- 자바 프로그래밍
  - 전위/후위 연산자 동작원리
  - 배열 초기화 기법
- 프로젝트 실습
  - 5. 배열과 반복문을 이용하여 여러 개의 데이터를 입력 받기(계속)
    - final 적용
  - 6. 조건문을 활용하여 실행 흐름을 제어하기
  - 7. 메서드를 이용하여 기능 단위로 명령문 묶기

### 9일(6/2,금)

- 자바 프로그래밍
  - 흐름제어문 사용법
  - static 메서드 사용법
    - call by value, call by refernce
    - 로컬 변수와 JVM Stack 메모리 영역
    - 인스턴스와 Heap 메모리 영역
    - 레퍼런스를 리턴하기
  - static 변수 사용법

- 프로젝트 실습
  - 8. 스태틱 메서드 간에 변수 공유하기

- 기타
  - 개인 별 *프로그래머스* 회원 가입 및 코딩테스트 연습 시작

### 10일(6/5,월)

- 자바 프로그래밍
  - 클래스 사용법
    - 클래스 문법의 목적
    - 클래스의 public 접근 제어
    - 메서드의 public 접근 제어
  - 패키지 사용법
    - import를 이용하여 패키지를 미리 지정하기
- 프로젝트 실습
  - 8. 메서드 간에 변수 공유하기 : 스태틱 변수 사용법(계속)
    - 리팩토링: 메서드 추가 정의, 상수 선언
  - 9. 메서드를 역할에 따라 분류하기 : 클래스 및 패키지 사용법
- 과제(개인)
  - 개인 프로젝트 수행
    - 프로젝트 폴더 생성 및 초기화
    - 실습 프로젝트와 마찬가지로 1단계에서 9단계까지 진행
    - 각 단계별로 app 폴더를 백업할 것.

### 11일(6/7,수)

- 프로젝트 실습
  - 10. 메뉴 구성 및 CRUD 구현
- 과제(개인) 발표
  - 개인 프로젝트 시연(14:10)
  - 프로그래머스 일별 점수 보고
    - 6/5(점수), 6/6(점수)
  - github.com 잔디 확인
    - 개인별 github.com 의 bitcamp-study 저장소 주소 제출
      - 이름, url
    - to: jinyoung.eom@gmail.com

### 11일(6/7,수)

- 프로젝트 실습
  - 10. 메뉴 구성 및 CRUD 구현
- 과제
  - 프로그래머스 일별 점수 보고(6/5, 6/6)
  - github.com 잔디 확인
    - 개인별 github.com 의 bitcamp-study 저장소 주소 제출
      - 이름, url
    - to: jinyoung.eom@gmail.com

### 12일(6/8,목)

- 프로젝트 실습
  - 11. 사용자 정의 데이터 타입 만들기
- 과제(개인) 발표
  - 개인 프로젝트 시연(17:10)
  - 프로그래머스 일별 점수 보고(6/7)
  - github.com 잔디 확인

### 13일(6/9,금)

- 자바 프로그래밍
  - 클래스 사용법(계속)
    - 스태틱 필드와 스태틱 메서드 사용법
    - 인스턴스 필드와 인스턴스 메서드 사용법
    - 패키지 사용법: import, public 접근 제어
    - GRASP 패턴: Information Expert 패턴 소개 
    - GoF의 Design Patters: Factory Method 패턴 소개
    - 생성자 사용법
    - private/public, setter/getter 사용법

### 14일(6/12,월)

- 자바 프로그래밍
  - 클래스 사용법(복습)
  - Eclipse IDE 도구 설정
    - 다운로드 및 설치, 워크스페이스 설정
    - 프로젝트 임포트
    - 소스 코드 편집과 컴파일, 실행하기
    - git add/commit/push 실행하기
- Gradle 사용법
  - 빌드 스크립트 파일과 플러그인, 태스크 관계
  - `eclipse` 플러그인 사용법
- 프로젝트 실습
  - 12. 생성자, setter, getter 도입하기

### 15일(6/13,화)

- 자바 프로그래밍(com.eomcs.oop.ex03.*)
  - 클래스 로딩과 스태틱 필드: Method Area 메모리 영역
  - new 연산자와 인스턴스 필드: Heap 메모리 영역
  - 메서드와 로컬 ㄹ변수: JVM Stack 메모리 영역
- 프로젝트 실습
  - 13. 게시판 관리 기능 추가


## 웨일즈 화상회의

https://whaleon.us/o/CSrtk1/001aa759f29c46b985e32dd8562f594a

## 구글 미트 접속

https://meet.google.com/sya-jckw-ivq