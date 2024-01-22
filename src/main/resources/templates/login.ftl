<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登陆</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/style/admin.css" media="all">
    <link rel="stylesheet" href="/static/style/login.css" media="all">
    <link rel="icon" href="/static/favicon.ico">

</head>
<body>

<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>滕王阁图书管理系统</h2>
            <p>漫游在知识的海洋里</p>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
                <input type="text" name="username" id="LAY-user-login-username" lay-verify="required" placeholder="用户名" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                <input type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
            </div>
<#--            <div class="layui-form-item" style="margin-bottom: 20px;">-->
<#--                <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">-->
<#--            </div>-->
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 录</button>
            </div>
        </div>
    </div>
</div>

<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'login'], function(){
        var $ = layui.$
                ,setter = layui.setter
                ,admin = layui.admin
                ,form = layui.form
                ,router = layui.router()
                ,search = router.search;
        form.render();
        //提交
        form.on('submit(LAY-user-login-submit)', function(obj){
            //请求登入接口
            admin.req({
                method: 'post'
                ,url: '/sys/login/signIn'
                ,data: obj.field
                ,done: function(res){
                    layer.msg('登入成功', {
                        offset: '15px'
                        ,icon: 1
                        ,time: 1000
                    }, function(){
                        location.href = '/toIndex'; //后台主页
                    });
                }
            });
        });
        if (window != top) {
            top.location.href = location.href;
        }
    });
</script>
</body>
</html>