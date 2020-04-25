create user 'seckill'@'%' identified by '123456789';

grant all privileges on seckill.* to 'seckill'@'%';
flush privileges;

create database seckill character set 'utf8mb4';

drop table if exists user;
create table user
(
    user_id      int primary key auto_increment,
    user_name    varchar(20) not null,
    password     varchar(20) not null,
    sex          bit         not null default 1 comment '1代表男,0代表女',
    tel          varchar(20) comment '手机号',
    status       bit         not null default 1 comment '0 禁用，1有效',
    avatar_image_name varchar(100) not null default 'defaultAvatar.jpg' comment '用户头像图片名称',
    created_time datetime    not null default current_timestamp,
    updated_time datetime    not null default current_timestamp on update current_timestamp,
    unique key `uni_user_name` (user_name) using btree
) comment '用户账户信息表';

drop table if exists admin_user;
create table admin_user
(
    admin_user_id int primary key auto_increment,
    user_name     varchar(20) not null,
    password      varchar(20) not null,
    status        bit                  default 1 comment '0代表禁用，1代表有效',
    created_time  datetime    not null default current_timestamp,
    updated_time  datetime    not null default current_timestamp on update current_timestamp,
    unique key `uni_user_name` (user_name) using BTREE
) comment '商家账户表';

drop table if exists goods;
create table goods
(
    goods_id     int primary key auto_increment,
    goods_name   varchar(40)  not null comment '商品名',
    price        double       not null comment '商品价格',
    amount       int          not null comment '数量',
    desc_info    varchar(100) not null default '' comment '商品描述信息',
    seckill_time datetime     not null comment '秒杀时间',
    img_name      varchar(60)  not null comment '商品图片名称',
    status       bit comment '0下架，1上架',
    created_time datetime     not null default current_timestamp,
    updated_time datetime     not null default current_timestamp on update current_timestamp,
    key `idx_good_name` (goods_name) using btree
) comment '商品信息表';

drop table if exists receive_address;
create table receive_address
(
    addr_id       int primary key auto_increment,
    user_id       int         not null comment '这个收货地址属于哪个用户',
    receiver_name varchar(20) comment '收件人姓名',
    province      varchar(20) not null,
    city          varchar(20) not null,
    county        varchar(20) not null,
    detail_addr   varchar(20) not null,
    tel           varchar(40) not null comment '收件人手机号',
    default_addr  bit comment '是否是默认收货地址',
    created_time  datetime    not null default current_timestamp,
    updated_time  datetime    not null default current_timestamp on update current_timestamp,
    key `idx_user_id` (user_id) using btree
) comment '收货地址表';

drop table if exists order_info;
create table order_info
(
    order_id     int primary key auto_increment,
    user_id      int      not null,
    addr_id      int      not null,
    status       tinyint  not null comment '0代表订单已被取消，1代表待付款，2代表待发货，3代表已收货',
    created_time datetime not null default current_timestamp,
    updated_time datetime not null default current_timestamp on update current_timestamp,
    key `idx_user_id` (user_id) using btree
) comment '订单信息表';

drop table if exists order_item;
create table order_item
(
    order_item_id int primary key auto_increment,
    order_id      int      not null,
    goods_id      int,
    amount        int      not null default 1 comment '购买数量',
    created_time  datetime not null default current_timestamp,
    updated_time  datetime not null default current_timestamp on update current_timestamp
) comment '订单条目表';

drop table if exists favorites;
create table favorites
(
    fav_id       int primary key auto_increment,
    user_id      int      not null,
    goods_id     int      not null,
    status       bit default 1 comment '0该收藏夹条目失效，1有效',
    created_time datetime not null default current_timestamp,
    updated_time datetime not null default current_timestamp on update current_timestamp
) comment '收藏夹';


insert into admin_user (user_name, password, status)
values ('root', '123456789', true);
