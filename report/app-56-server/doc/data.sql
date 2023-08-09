-- report_member 테이블 예제 데이터
insert into report_member(member_no, name, phone, password, position) 
  values(1, '직원1', '01011112222', '1111', '1');
insert into report_member(member_no, name, phone, password, position) 
  values(2, '관리자1', '01033334444', '1111', '0');
insert into report_member(member_no, name, phone, password, position) 
  values(3, '직원2', '01055556666', '1111', '1');
insert into report_member(member_no, name, phone, password, position) 
  values(4, '직원3', '01077778888', '1111', '1');
insert into report_member(member_no, name, phone, password, position) 
  values(5, '직원4', '01099990000', '1111', '1');
insert into report_member(member_no, name, phone, password, position) 
  values(6, '관리자2', '01012341234', '1111', '0');
  
-- report_board 테이블 예제 데이터
insert into report_board(board_no, title, content, writer, password, category) 
  values(1, '제목1', '내용', '홍길동', '1111', 1);
insert into report_board(board_no, title, content, writer, password, category) 
  values(2, '제목2', '내용', '임꺽정', '1111', 1);
insert into report_board(board_no, title, content, writer, password, category) 
  values(3, '제목3', '내용', '유관순', '1111', 1);
insert into report_board(board_no, title, content, writer, password, category) 
  values(4, '제목4', '내용', '이순신', '1111', 1);
insert into report_board(board_no, title, content, writer, password, category) 
  values(5, '제목5', '내용', '윤봉길', '1111', 2);
insert into report_board(board_no, title, content, writer, password, category) 
  values(6, '제목6', '내용', '안중근', '1111', 2);
insert into report_board(board_no, title, content, writer, password, category) 
  values(7, '제목1', '내용', '김구', '1111', 2);
  
-- report_item 테이블 예제 데이터
insert into report_item(item_no, name, price, item_type) 
  values(1, '운동복', '39000', '의류');
insert into report_item(item_no, name, price, item_type) 
  values(2, '전자레인지', '149000', '가전제품');
insert into report_item(item_no, name, price, item_type) 
  values(3, '사과(5KG)', '45000', '식료품');
insert into report_item(item_no, name, price, item_type) 
  values(4, '3겹 클래식 화장지 27m 9개입', '5570', '생활용품');
insert into report_item(item_no, name, price, item_type) 
  values(5, '원룸 미니 3단서랍 옷장 800', '99910', '리빙');
  
  