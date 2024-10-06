CREATE TABLE `user_info` (
                             `user_id` bigint(20) NOT NULL primary key,
                             `name` varchar(64) NOT NULL DEFAULT '',
                             `password` varchar(128) NOT NULL DEFAULT '',
                             `gender` int(4) NOT NULL DEFAULT '0' COMMENT '1-男性，2-女性',
                             `age` int(11) NOT NULL DEFAULT '0',
                             `telphone` varchar(255) NOT NULL DEFAULT '',
                             `register_mode` varchar(255) NOT NULL DEFAULT '' COMMENT 'byphone,bywechat,byalipay',
                             `third_party_id` varchar(64) NOT NULL DEFAULT ''
) COMMENT '用户表';

CREATE UNIQUE INDEX telphone_unique_index ON user_info(telphone);

CREATE TABLE `product` (
                           `product_id` bigint(20) NOT NULL primary key,
                           `title` varchar(64) NOT NULL DEFAULT '',
                           `price` decimal(10,0) NOT NULL DEFAULT '0',
                           `description` varchar(500) NOT NULL DEFAULT '',
                           `sales` int(11) NOT NULL DEFAULT '0' COMMENT '商品销量',
                           `img_url` varchar(255) NOT NULL DEFAULT '' COMMENT '商品图片'
) COMMENT '商品表';

CREATE TABLE `product_stock` (
                                 `stock_id` bigint(20) NOT NULL primary key,
                                 `stock` int(11) NOT NULL DEFAULT '0' COMMENT '商品库存',
                                 `product_id` bigint(20) NOT NULL DEFAULT '0'
) COMMENT '商品库存表';
CREATE UNIQUE INDEX product_id_unique_index ON product_stock(product_id);

CREATE TABLE `promo` (
                         `promo_id` bigint(20) NOT NULL primary key,
                         `promo_name` varchar(255) NOT NULL DEFAULT '',
                         `product_id` bigint(20) NOT NULL DEFAULT '0',
                         `promo_product_price` decimal(10,0) NOT NULL DEFAULT '0',
                         `start_date` datetime NOT NULL DEFAULT now(),
                         `end_date` datetime NOT NULL DEFAULT now()
) COMMENT '秒杀活动表';

CREATE TABLE `order_info` (
                              `order_id` bigint(20) NOT NULL primary key,
                              `user_id` bigint(20) NOT NULL DEFAULT '0',
                              `product_id` bigint(20) NOT NULL DEFAULT '0',
                              `product_price` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '商品价格',
                              `amount` int(11) NOT NULL DEFAULT '0' COMMENT '下单数量',
                              `order_price` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '订单价格',
                              `promo_id` bigint(20) NOT NULL DEFAULT '0'
) COMMENT '订单表';