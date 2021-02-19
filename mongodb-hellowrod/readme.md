# 说明
* 本项目代码是源于黑马传智播客教程 视频地址[MongoDB基础入门到高级进阶](https://www.bilibili.com/video/BV1j541187bA?p=20&spm_id_from=pageDriver)
* 源视频中的代码由maven 改为了 gradle, 并新增了一部分测试代码
* 测试sql
```
use articledb;
db.comment.insertMany([
{"_id":"1","articleid":"100001","content":"我们不应该把清晨浪费在手机上，健康很重要，一杯温水幸福你我他。","userid":"1002","nickname":"相忘于江湖","createdatetime":new Date("2019-08- 05T22:08:15.522Z"),"likenum":NumberInt(1000),"state":"1"},
{"_id":"2","articleid":"100001","content":"我夏天空腹喝凉开水，冬天喝温开水","userid":"1005","nickname":"伊人憔 悴","createdatetime":new Date("2019-08-05T23:58:51.485Z"),"likenum":NumberInt(888),"state":"1"},
{"_id":"3","articleid":"100001","content":"我一直喝凉开水，冬天夏天都喝。","userid":"1004","nickname":"杰克船 长","createdatetime":new Date("2019-08-06T01:05:06.321Z"),"likenum":NumberInt(666),"state":"1"},
{"_id":"4","articleid":"100001","content":"专家说不能空腹吃饭，影响健康。","userid":"1003","nickname":"凯 撒","createdatetime":new Date("2019-08-06T08:18:35.288Z"),"likenum":NumberInt(2000),"state":"1"},
{"_id":"5","articleid":"100001","content":"研究表明，刚烧开的水千万不能喝，因为烫 嘴。","userid":"1003","nickname":"凯撒","createdatetime":new Date("2019-08- 06T11:01:02.521Z"),"likenum":NumberInt(3000),"state":"1"}
]);
```
* spring-data-mongo 学习还是推荐看官网，一点点往下看，还是很容易看得懂的[Getting Started](https://docs.spring.io/spring-data/mongodb/docs/3.1.3/reference/html/#mongodb-getting-started)
