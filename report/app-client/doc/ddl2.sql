create table report_board(
  board_no int not null,
  title varchar(255) not null,
  content text null,
  writer int not null,
  password varchar(100) null,
  view_count int default 0,
  created_date datetime default now(),
  category int not null
);

alter table report_board
  add constraint primary key (board_no),
  modify column board_no int not null auto_increment;


create table report_member(
  member_no int not null,
  name varchar(20) not null,
  phone varchar(20) not null,
  password varchar(100) not null,
  position char(1) not null,
  created_date date default (current_date())
);

alter table report_member
  add constraint primary key (member_no),
  modify column member_no int not null auto_increment;
  

create table report_item(
  item_no int not null,
  name varchar(100) not null,
  price int not null,
  item_type varchar(20) not null
);

alter table report_item
  add constraint primary key (item_no),
  modify column item_no int not null auto_increment;

-- 게시판 작성자에 대해 외부키 설정
alter table report_board
  add constraint report_board_fk foreign key (writer) references report_member (member_no);

  