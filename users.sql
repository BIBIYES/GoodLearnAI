create table users
(
    id         int auto_increment comment '用户id'
        primary key,
    name       varchar(100)                        not null comment '用户姓名',
    email      varchar(100)                        not null comment '用户邮箱',
    password   varchar(100)                        not null comment '用户密码',
    role       enum ('teacher', 'student')         not null comment '老师 or 学生',
    created_at timestamp default CURRENT_TIMESTAMP not null comment '用户创建时间',
    updated_at timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    constraint users_pk
        unique (email)
)
    comment '老师和学生';


