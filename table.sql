create table board(
                      board_id bigint not null auto_increment primary key,
                      board_title varchar(500) not null,
                      board_contents varchar(3000) not null,
                      board_author varchar(500) not null,
                      read_count int not null,
                      created_time timestamp not null,
                      updated_time timestamp not null,
                      member_id bigint,
                      foreign key(member_id) references member(member_id)
);

create table member(
                       member_id bigint PRIMARY KEY auto_increment,
                       password varchar(300) not null,
                       nickname varchar(500) not null,
                       email varchar(500) not null,
                       image_url varchar(500) not null,
                       role varchar(500) not null,
                       social_type varchar(500) not null,
                       social_id varchar(500) not null,
                       refresh_token varchar(500) not null,
                       created_time timestamp not null,
                       updated_time timestamp not null
);

create table comment(
                        comment_id bigint not null primary key auto_increment,
                        comment_contents varchar(1000) not null,
                        board_id bigint not null,
                        created_time timestamp not null,
                        updated_time timestamp not null,
                        foreign key(board_id)references board(board_id)
);


insert into board(board_title,board_author,board_contents,read_count,created_time,updated_time)
values('test title','well419','test contents',0,now(),now());

insert into board(board_title,board_author,board_contents,read_count,created_time,updated_time)
values('test title2','wel','test contents2',0,now(),now());

insert into board(board_title,board_author,board_contents,read_count,created_time,updated_time)
values('test title3','wel','test contents3',0,now(),now());

insert into comment(comment_contents, board_id, created_time, updated_time)
values('내용???',1,now(),now());
