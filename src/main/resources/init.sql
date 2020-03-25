create user 'seckill'@'%' identified by '123456789';

grant all privileges on seckill.* to 'seckill'@'%';
flush privileges;

create database seckill character set 'utf8mb4';


create table user
(
    user_id   int primary key auto_increment,
    user_name varchar(20) not null,
    password  varchar(20) not null,
    sex       bit         not null default 1 comment '1代表男,0代表女',
    tel       varchar(20) comment '手机号',
    status    bit         not null default 1 comment '0 禁用，1有效'
) comment '用户账户信息表';

create table admin_user
(
    admin_user_id int primary key auto_increment,
    user_name     varchar(20) not null,
    password      varchar(20) not null,
    status        bit default 1 comment '0代表禁用，1代表有效'
) comment '商家账户表';

create table goods
(
    goods_id     int primary key auto_increment,
    goods_name   varchar(40)  not null comment '商品名',
    price        double       not null comment '商品价格',
    amount       int          not null comment '数量',
    desc_info    varchar(100) not null default '' comment '商品描述信息',
    seckill_time datetime     not null comment '秒杀时间',
    img_url      varchar(60)  not null comment '商品图片url地址',
    status       bit comment '0下架，1上架'
) comment '商品信息表';

create table receive_addr
(
    addr_id       int primary key auto_increment,
    user_id       int         not null,
    receiver_name varchar(20) comment '收件人姓名',
    province      varchar(20) not null,
    city          varchar(20) not null,
    county        varchar(20) not null,
    detail_addr   varchar(20) not null,
    tel           varchar(40) not null comment '收件人手机号',
    default_addr  bit comment '是否是默认收货地址'
) comment '收货地址表';

create table order_info
(
    order_id    int primary key auto_increment,
    user_id     int      not null,
    addr_id     int      not null,
    status      tinyint  not null comment '0代表订单已被取消，1代表待付款，2代表待发货，3代表已收货',
    create_time datetime not null default now() comment '订单创建时间'
) comment '订单信息表';

create table order_item
(
    order_item_id int primary key auto_increment,
    order_id      int not null,
    goods_id      int,
    amount        int not null default 1 comment '购买数量'
) comment '订单条目表';

create table favorites
(
    fav_id   int primary key auto_increment,
    user_id  int not null,
    goods_id int not null,
    status   bit comment '0该收藏夹条目失效，1有效'
) comment '收藏夹';


insert into admin_user (user_name, password, status)
values ('root', '123456789', true);
