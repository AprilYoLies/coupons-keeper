## 简介

简易的优惠券卡包系统后台，整体的思想参考慕课网的 《JAVA分布式优惠券系统后台》 课程，具体的实现根据个人理解来完成，原项目采用的 HBase 分布式数据库，这里将其修改为 Mysql 实现。系统主要包括两个模块，商户系统和用户系统，商户系统允许创建商家信息，查询商家信息，商家向系统投放优惠券信息到消息中间件 kafka 中，用户系统主要功能包括创建用户信息、查询用户信息、领取优惠券、查询可领取优惠券、使用优惠券、查询已使用和未使用优惠券、将消息中间件中的优惠券信息入库，以及商户上传优惠券 token 信息等。

## 涉及的技术

整个系统通过 spring-boot 进行构建，数据库采用 Mysql，由 mybatis 充当持久层框架。商户系统和用户系统之间通过消息中间件 kafka 进行信息的交互，做到了解耦合，此外使用 redis 对优惠券的 token 信息进行了缓存。

## 准备环境

创建四张数据表，coupons 优惠券表、merchants 商家表、user_coupons 用户领取的优惠券表、users 用户表，数据表文件在项目 db 目录下。

启动 zookeeper 服务，kafka 服务需要依托于此。

启动 kafka 服务，商户投放的优惠券将通过 kafka 被用户系统消费入库。

启动 redis 服务，优惠券的 token 信息将会基于 redis 来作为缓存。

创建商家上传 token 文件的存储目录，默认为 `/tmp/token`。

> $ZOOKEEPER_HOME/zkServer.sh 

## 相关 api 说明

### 商户系统

商户系统的所有 api 需要在请求头中携带必要的 token 字段，内容为 `coupons_keeper_merchant_token`。

1. 创建商户信息

``` json
POST: localhost:8081/merchant/create
    
BODY: 
{
    "name": "麦当劳",
    "logoUrl": "https://www.mcdonalds.com.cn/",
    "businessLicenseUrl": "https://www.mcdonalds.license.com.cn/",
    "phone": "1234567",
    "address": "上海市"
}
```

2. 查询商户信息

``` json
GET: localhost:8081/merchant/{merchantId}
```

3. 商家投放优惠券
``` json
POST: localhost:8081/merchant/drop

BODY:

{
    "background": 3,
    "desc": "麦当劳优惠券详情",
    "end": 1956759583868,
    "hasToken": false,
    "merchantId": 1,
    "limit": 10000,
    "start": 1555049583866,
    "summary": "麦当劳优惠券摘要",
    "title": "麦当劳优惠券-无token"
}
```

### 用户系统

1. 商家优惠券 token 上传界面

``` json
GET: http://localhost:8080/merchant/upload
```

2. 创建用户信息

``` json
POST: localhost:8080/coupons/create-user

BODY:
{
	"name": "小王",
	"age": 23,
	"sex": "男",
	"phone": "1234567890",
	"address": "四川省成都市"
}
```

3. 用户领取优惠券

``` json
GET: http://localhost:8080/coupons/gain-coupon/{userId}/{couponId}
```

4. 查询用户领取的优惠券

``` json
GET: http://localhost:8080/coupons/user-coupons/{userId}
```

5. 查询用户使用过的优惠券

``` json
GET: http://localhost:8080/coupons/used-coupons/{userId}
```

6. 用户使用优惠券

``` json
GET: http://localhost:8080/coupons/use-coupons/{userCouponId}
```

7. 查询用户可以领取的优惠券，不包括已领取或者失效的优惠券

``` json
GET: http://localhost:8080/coupons/available-coupons/{userId}
```

8. 全局异常测试接口

``` json
GET: http://localhost:8080/coupons/exception
```
