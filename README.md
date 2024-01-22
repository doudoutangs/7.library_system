# 7.图书管理系统

## 一、系统介绍
本系统为图书管理系统，主要围绕图书管理和会员管理两个核心内容展开，图书管理包括图书的上架，下架，图书的借阅，归还，定损等；
会员管理包括会员注册，充值，损坏扣费，延期归还扣费，用户借阅记录等。

系统默认有三个角色：管理员，会员，普通用户
- 管理员（admin/admin）：可以操作所有功能
- 会员(身份证号/身份证号后6位)：
- 普通用户（身份证号/身份证号后6位）：
## 二、角色运行图
### 首页
![首页](https://gitcode.net/tbb414/library_system/-/raw/c7efcdd574e2453bf8619fe91d375ff63bfc4c41/%E5%9B%BE%E4%B9%A6%E7%AE%A1%E7%90%86/0-1%E9%A6%96%E9%A1%B5.jpg)
### 注册
![注册](https://gitcode.net/tbb414/library_system/-/raw/c7efcdd574e2453bf8619fe91d375ff63bfc4c41/%E5%9B%BE%E4%B9%A6%E7%AE%A1%E7%90%86/0-2%E6%B3%A8%E5%86%8C.jpg)
### 登录
![登录](https://gitcode.net/tbb414/library_system/-/raw/c7efcdd574e2453bf8619fe91d375ff63bfc4c41/%E5%9B%BE%E4%B9%A6%E7%AE%A1%E7%90%86/0-3%E7%99%BB%E5%BD%95.jpg)
### 图书检索
![图书检索](https://gitcode.net/tbb414/library_system/-/raw/c7efcdd574e2453bf8619fe91d375ff63bfc4c41/%E5%9B%BE%E4%B9%A6%E7%AE%A1%E7%90%86/1-1%E5%9B%BE%E4%B9%A6%E6%A3%80%E7%B4%A2.jpg)
### 图书统计
![图书统计](https://gitcode.net/tbb414/library_system/-/raw/c7efcdd574e2453bf8619fe91d375ff63bfc4c41/%E5%9B%BE%E4%B9%A6%E7%AE%A1%E7%90%86/2-1%E5%9B%BE%E4%B9%A6%E7%BB%9F%E8%AE%A1.jpg)
### 借书统计
![借书统计](https://gitcode.net/tbb414/library_system/-/raw/c7efcdd574e2453bf8619fe91d375ff63bfc4c41/%E5%9B%BE%E4%B9%A6%E7%AE%A1%E7%90%86/2-2%E5%80%9F%E4%B9%A6%E7%BB%9F%E8%AE%A1.jpg)
### 图书上架
![图书上架](https://gitcode.net/tbb414/library_system/-/raw/c7efcdd574e2453bf8619fe91d375ff63bfc4c41/%E5%9B%BE%E4%B9%A6%E7%AE%A1%E7%90%86/3-1%E5%9B%BE%E4%B9%A6%E4%B8%8A%E6%9E%B6.jpg)
### 图书归还
![图书归还](https://gitcode.net/tbb414/library_system/-/raw/c7efcdd574e2453bf8619fe91d375ff63bfc4c41/%E5%9B%BE%E4%B9%A6%E7%AE%A1%E7%90%86/3-2%E5%9B%BE%E4%B9%A6%E5%BD%92%E8%BF%98.jpg)

## 四、软件架构

基础环境：
1. JDK:1.8
2. MySQL:5.7
3. Maven3.0

使用框架：

1. 核心框架：Spring Boot 2.3.12.RELEASE
2. ORM框架：mybatis 3.4.0
3. 数据库连接池：Druid 1.2.8
4. 安全框架：Apache Shiro 1.8.0
5. 日志：SLF4J，Log4j
6. 前端框架：LayUI,jquery,ECharts



## 五、安装教程
1. 导入mysql脚本,数据库名称：sp_library
2. 修改数据库配置：

![修改配置](https://gitcode.net/tbb414/library_system/-/raw/5b802d0c626bb3c162d05a9b1f9656dd176853f9/%E5%9B%BE%E4%B9%A6%E7%AE%A1%E7%90%86/0-4%E9%85%8D%E7%BD%AE.jpg)
3. 启动java工程

![启动项目](https://gitcode.net/tbb414/library_system/-/raw/5b802d0c626bb3c162d05a9b1f9656dd176853f9/%E5%9B%BE%E4%B9%A6%E7%AE%A1%E7%90%86/0-5%E5%90%AF%E5%8A%A8.jpg)
4. 访问：http://localhost:8806（账号admin/admin）

## 六、说明
0. QQ:553039957
1. gitcode主页： https://gitcode.com/user/tbb414 (推荐)
2. github主页：https://github.com/doudoutangs
## 七、其他项目
1. [招投标管理系统](https://gitcode.com/tbb414/bid_system/overview)
2. [OA系统](https://gitcode.com/tbb414/oa_system/overview)
3. [薪资管理系统](https://gitcode.com/tbb414/salary_system/overview)
4. [人事管理系统](https://gitcode.com/tbb414/person_system/overview)
5. [绩效考核系统](https://gitcode.com/tbb414/assess_system/overview)
6. [就业管理系统](https://gitcode.com/tbb414/eta_system/overview)
7. [图书管理系统](https://gitcode.com/tbb414/library_system/overview)