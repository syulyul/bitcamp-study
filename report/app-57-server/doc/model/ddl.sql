-- 제품구매
DROP TABLE IF EXISTS mart_item_purchase RESTRICT;

-- 제품
DROP TABLE IF EXISTS mart_item RESTRICT;

-- 고객
DROP TABLE IF EXISTS mart_customer RESTRICT;

-- 지점
DROP TABLE IF EXISTS mart_branch RESTRICT;

-- 제품사진
DROP TABLE IF EXISTS mart_item_photo RESTRICT;

-- 직원
DROP TABLE IF EXISTS mart_staff RESTRICT;

-- 관리자
DROP TABLE IF EXISTS mart_manager RESTRICT;

-- 회원
DROP TABLE IF EXISTS mart_member RESTRICT;

-- 공지사항
DROP TABLE IF EXISTS mart_notice RESTRICT;

-- 공지유형
DROP TABLE IF EXISTS mart_notice_type RESTRICT;

-- 장바구니
DROP TABLE IF EXISTS mart_cart RESTRICT;

-- 문의사항
DROP TABLE IF EXISTS mart_customer_inquiry RESTRICT;

-- 제품구매
CREATE TABLE mart_item_purchase (
  pno        INTEGER     NOT NULL COMMENT '제품구매번호', -- 제품구매번호
  ino        INTEGER     NOT NULL COMMENT '제품번호', -- 제품번호
  pur_qty    INTEGER     NOT NULL COMMENT '구매수량', -- 구매수량
  pay_method VARCHAR(60) NOT NULL COMMENT '결제수단', -- 결제수단
  cno        INTEGER     NULL     COMMENT '고객번호' -- 고객번호
)
COMMENT '제품구매';

-- 제품구매
ALTER TABLE mart_item_purchase
  ADD CONSTRAINT PK_mart_item_purchase -- 제품구매 기본키
  PRIMARY KEY (
  pno -- 제품구매번호
  );

ALTER TABLE mart_item_purchase
  MODIFY COLUMN pno INTEGER NOT NULL AUTO_INCREMENT COMMENT '제품구매번호';

-- 제품
CREATE TABLE mart_item (
  ino       INTEGER     NOT NULL COMMENT '제품번호', -- 제품번호
  name      VARCHAR(60) NOT NULL COMMENT '제품명', -- 제품명
  price     INTEGER     NOT NULL COMMENT '제품가격', -- 제품가격
  stock_qty INTEGER     NOT NULL COMMENT '재고수량', -- 재고수량
  type      VARCHAR(60) NOT NULL COMMENT '제품종류' -- 제품종류
)
COMMENT '제품';

-- 제품
ALTER TABLE mart_item
  ADD CONSTRAINT PK_mart_item -- 제품 기본키
  PRIMARY KEY (
  ino -- 제품번호
  );

-- 제품 인덱스
CREATE INDEX IX_mart_item
  ON mart_item( -- 제품
    name ASC -- 제품명
  );

ALTER TABLE mart_item
  MODIFY COLUMN ino INTEGER NOT NULL AUTO_INCREMENT COMMENT '제품번호';

-- 고객
CREATE TABLE mart_customer (
  cno      INTEGER      NOT NULL COMMENT '고객번호', -- 고객번호
  post_no  VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
  bas_addr VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
  det_addr VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
  ctno     INTEGER      NULL     COMMENT '장바구니번호' -- 장바구니번호
)
COMMENT '고객';

-- 고객
ALTER TABLE mart_customer
  ADD CONSTRAINT PK_mart_customer -- 고객 기본키
  PRIMARY KEY (
  cno -- 고객번호
  );

-- 지점
CREATE TABLE mart_branch (
  brno     INTEGER      NOT NULL COMMENT '지점번호', -- 지점번호
  name     VARCHAR(60)  NOT NULL COMMENT '지점명', -- 지점명
  post_no  VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
  bas_addr VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
  det_addr VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
  tel      VARCHAR(30)  NOT NULL COMMENT '대표번호' -- 대표번호
)
COMMENT '지점';

-- 지점
ALTER TABLE mart_branch
  ADD CONSTRAINT PK_mart_branch -- 지점 기본키
  PRIMARY KEY (
  brno -- 지점번호
  );

-- 지점 유니크 인덱스
CREATE UNIQUE INDEX UIX_mart_branch
  ON mart_branch ( -- 지점
    name ASC -- 지점명
  );

ALTER TABLE mart_branch
  MODIFY COLUMN brno INTEGER NOT NULL AUTO_INCREMENT COMMENT '지점번호';

-- 제품사진
CREATE TABLE mart_item_photo (
  ipno     INTEGER      NOT NULL COMMENT '제품사진번호', -- 제품사진번호
  ino      INTEGER      NOT NULL COMMENT '제품번호', -- 제품번호
  filename VARCHAR(255) NOT NULL COMMENT '제품사진명' -- 제품사진명
)
COMMENT '제품사진';

-- 제품사진
ALTER TABLE mart_item_photo
  ADD CONSTRAINT PK_mart_item_photo -- 제품사진 기본키
  PRIMARY KEY (
  ipno -- 제품사진번호
  );

ALTER TABLE mart_item_photo
  MODIFY COLUMN ipno INTEGER NOT NULL AUTO_INCREMENT COMMENT '제품사진번호';

-- 직원
CREATE TABLE mart_staff (
  sno      INTEGER      NOT NULL COMMENT '직원번호', -- 직원번호
  brno     INTEGER      NULL     COMMENT '지점번호', -- 지점번호
  dept     VARCHAR(60)  NULL     COMMENT '부서', -- 부서
  posi     VARCHAR(60)  NULL     COMMENT '직위', -- 직위
  post_no  VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
  bas_addr VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
  det_addr VARCHAR(255) NULL     COMMENT '상세주소' -- 상세주소
)
COMMENT '직원';

-- 직원
ALTER TABLE mart_staff
  ADD CONSTRAINT PK_mart_staff -- 직원 기본키
  PRIMARY KEY (
  sno -- 직원번호
  );

-- 관리자
CREATE TABLE mart_manager (
  mno  INTEGER NOT NULL COMMENT '관리자번호', -- 관리자번호
  brno INTEGER NULL     COMMENT '지점번호' -- 지점번호
)
COMMENT '관리자';

-- 관리자
ALTER TABLE mart_manager
  ADD CONSTRAINT PK_mart_manager -- 관리자 기본키
  PRIMARY KEY (
  mno -- 관리자번호
  );

-- 회원
CREATE TABLE mart_member (
  mno   INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
  name  VARCHAR(60) NOT NULL COMMENT '이름', -- 이름
  tel   VARCHAR(30) NOT NULL COMMENT '전화번호', -- 전화번호
  email VARCHAR(40) NOT NULL COMMENT '이메일' -- 이메일
)
COMMENT '회원';

-- 회원
ALTER TABLE mart_member
  ADD CONSTRAINT PK_mart_member -- 회원 기본키
  PRIMARY KEY (
  mno -- 회원번호
  );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_mart_member
  ON mart_member ( -- 회원
    email ASC -- 이메일
  );

-- 회원 인덱스
CREATE INDEX IX_mart_member
  ON mart_member( -- 회원
    name ASC -- 이름
  );

-- 회원 인덱스2
CREATE INDEX IX_mart_member2
  ON mart_member( -- 회원
    tel ASC -- 전화번호
  );

ALTER TABLE mart_member
  MODIFY COLUMN mno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 공지사항
CREATE TABLE mart_notice (
  nno      INTEGER      NOT NULL COMMENT '공지사항번호', -- 공지사항번호
  ntno     INTEGER      NULL     COMMENT '공지유형번호', -- 공지유형번호
  title    VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content  MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  view_cnt INTEGER      NOT NULL COMMENT '조회수', -- 조회수
  cdt      DATE         NOT NULL DEFAULT (current_date()) COMMENT '작성일' -- 작성일
)
COMMENT '공지사항';

-- 공지사항
ALTER TABLE mart_notice
  ADD CONSTRAINT PK_mart_notice -- 공지사항 기본키
  PRIMARY KEY (
  nno -- 공지사항번호
  );

ALTER TABLE mart_notice
  MODIFY COLUMN nno INTEGER NOT NULL AUTO_INCREMENT COMMENT '공지사항번호';

-- 공지유형
CREATE TABLE mart_notice_type (
  ntno INTEGER     NOT NULL COMMENT '공지유형번호', -- 공지유형번호
  type VARCHAR(60) NOT NULL COMMENT '유형명' -- 유형명
)
COMMENT '공지유형';

-- 공지유형
ALTER TABLE mart_notice_type
  ADD CONSTRAINT PK_mart_notice_type -- 공지유형 기본키
  PRIMARY KEY (
  ntno -- 공지유형번호
  );

-- 공지유형 유니크 인덱스
CREATE UNIQUE INDEX UIX_mart_notice_type
  ON mart_notice_type ( -- 공지유형
    type ASC -- 유형명
  );

-- 장바구니
CREATE TABLE mart_cart (
  ctno     INTEGER NOT NULL COMMENT '장바구니번호', -- 장바구니번호
  cart_qty INTEGER NULL     COMMENT '수량', -- 수량
  ino      INTEGER NULL     COMMENT '제품번호' -- 제품번호
)
COMMENT '장바구니';

-- 장바구니
ALTER TABLE mart_cart
  ADD CONSTRAINT PK_mart_cart -- 장바구니 기본키
  PRIMARY KEY (
  ctno -- 장바구니번호
  );

ALTER TABLE mart_cart
  MODIFY COLUMN ctno INTEGER NOT NULL AUTO_INCREMENT COMMENT '장바구니번호';

-- 문의사항
CREATE TABLE mart_customer_inquiry (
  qno      INTEGER      NOT NULL COMMENT '문의사항번호', -- 문의사항번호
  cno      INTEGER      NULL     COMMENT '고객번호', -- 고객번호
  title    VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content  MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  view_cnt INTEGER      NOT NULL COMMENT '조회수', -- 조회수
  cdt      DATE         NOT NULL DEFAULT (current_date()) COMMENT '작성일', -- 작성일
  writer   VARCHAR(60)  NOT NULL COMMENT '작성자', -- 작성자
  resp     MEDIUMTEXT   NULL     COMMENT '답변' -- 답변
)
COMMENT '문의사항';

-- 문의사항
ALTER TABLE mart_customer_inquiry
  ADD CONSTRAINT PK_mart_customer_inquiry -- 문의사항 기본키
  PRIMARY KEY (
  qno -- 문의사항번호
  );

ALTER TABLE mart_customer_inquiry
  MODIFY COLUMN qno INTEGER NOT NULL AUTO_INCREMENT COMMENT '문의사항번호';

-- 제품구매
ALTER TABLE mart_item_purchase
  ADD CONSTRAINT FK_mart_item_TO_mart_item_purchase -- 제품 -> 제품구매
  FOREIGN KEY (
  ino -- 제품번호
  )
  REFERENCES mart_item ( -- 제품
  ino -- 제품번호
  );

-- 제품구매
ALTER TABLE mart_item_purchase
  ADD CONSTRAINT FK_mart_customer_TO_mart_item_purchase -- 고객 -> 제품구매
  FOREIGN KEY (
  cno -- 고객번호
  )
  REFERENCES mart_customer ( -- 고객
  cno -- 고객번호
  );

-- 고객
ALTER TABLE mart_customer
  ADD CONSTRAINT FK_mart_member_TO_mart_customer -- 회원 -> 고객
  FOREIGN KEY (
  cno -- 고객번호
  )
  REFERENCES mart_member ( -- 회원
  mno -- 회원번호
  );

-- 고객
ALTER TABLE mart_customer
  ADD CONSTRAINT FK_mart_cart_TO_mart_customer -- 장바구니 -> 고객
  FOREIGN KEY (
  ctno -- 장바구니번호
  )
  REFERENCES mart_cart ( -- 장바구니
  ctno -- 장바구니번호
  );

-- 제품사진
ALTER TABLE mart_item_photo
  ADD CONSTRAINT FK_mart_item_TO_mart_item_photo -- 제품 -> 제품사진
  FOREIGN KEY (
  ino -- 제품번호
  )
  REFERENCES mart_item ( -- 제품
  ino -- 제품번호
  );

-- 직원
ALTER TABLE mart_staff
  ADD CONSTRAINT FK_mart_branch_TO_mart_staff -- 지점 -> 직원
  FOREIGN KEY (
  brno -- 지점번호
  )
  REFERENCES mart_branch ( -- 지점
  brno -- 지점번호
  );

-- 직원
ALTER TABLE mart_staff
  ADD CONSTRAINT FK_mart_member_TO_mart_staff -- 회원 -> 직원
  FOREIGN KEY (
  sno -- 직원번호
  )
  REFERENCES mart_member ( -- 회원
  mno -- 회원번호
  );

-- 관리자
ALTER TABLE mart_manager
  ADD CONSTRAINT FK_mart_branch_TO_mart_manager -- 지점 -> 관리자
  FOREIGN KEY (
  brno -- 지점번호
  )
  REFERENCES mart_branch ( -- 지점
  brno -- 지점번호
  );

-- 관리자
ALTER TABLE mart_manager
  ADD CONSTRAINT FK_mart_member_TO_mart_manager -- 회원 -> 관리자
  FOREIGN KEY (
  mno -- 관리자번호
  )
  REFERENCES mart_member ( -- 회원
  mno -- 회원번호
  );

-- 공지사항
ALTER TABLE mart_notice
  ADD CONSTRAINT FK_mart_notice_type_TO_mart_notice -- 공지유형 -> 공지사항
  FOREIGN KEY (
  ntno -- 공지유형번호
  )
  REFERENCES mart_notice_type ( -- 공지유형
  ntno -- 공지유형번호
  );

-- 장바구니
ALTER TABLE mart_cart
  ADD CONSTRAINT FK_mart_item_TO_mart_cart -- 제품 -> 장바구니
  FOREIGN KEY (
  ino -- 제품번호
  )
  REFERENCES mart_item ( -- 제품
  ino -- 제품번호
  );

-- 문의사항
ALTER TABLE mart_customer_inquiry
  ADD CONSTRAINT FK_mart_customer_TO_mart_customer_inquiry -- 고객 -> 문의사항
  FOREIGN KEY (
  cno -- 고객번호
  )
  REFERENCES mart_customer ( -- 고객
  cno -- 고객번호
  );