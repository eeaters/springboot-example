create table user
(
    id        int auto_increment comment 'ID',
    user_name varchar(25) not null comment '用户名',
    password  varchar(50) not null comment '密码',
    age       int         null comment '年龄',
    constraint user_pk
        primary key (id)
)
    comment '用户';

