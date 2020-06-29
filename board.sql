create table board (
    no  number,
    title   varchar2(500) not null,
    content varchar2(4000),
    hit number,
    reg_date    date    not null,
    user_no number   not null,
    primary key (no),
    constraint board_fk foreign key(user_no)
    references users(no)
);
    
create sequence seq_board_no
increment by 1
start with 1;
    
select * from
board;

select * from users;