<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/style/admin.css" media="all">
</head>
<body>
<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md3">
            <h1><strong> 滕王阁 </strong>图书馆</h1>
            <h4><span id="stime" style="background-color: #0C0C0C; color: #FBFBFB"></span></h4>
            <br>
        </div>
        <div class="layui-col-md6">
            <div style="padding: 18px;">
                <h3><strong>阅读时请保持微笑(*￣︶￣)，因为有人将告诉你，你不知道的事儿</strong></h3>
            </div>
        </div>
        <div class="layui-col-md3">
            <div style="padding: 10px;">
                <a class="layui-btn layui-btn-normal" layadmin-event="toLoginView">登录</a>
                <a class="layui-btn layui-btn-warm" layadmin-event="toHomeView">返回首页</a>
            </div>
        </div>
    </div>
    <div style='padding: 10px;background-image: url("/static/modules/tushu.png"); color: #0C0C0C'>
        <#--    <div style='padding: 10px;background-color: #1EFF43FF' >-->
        <fieldset class="layui-elem-field">
            <legend>注册会员</legend>
            <div class="layui-field-box">
                <form class="layui-form" action="" lay-filter="example" style="">
                    <div class="layui-form-item">
                        <label class="layui-form-label">手机号</label>
                        <div class="layui-input-inline">
                            <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">密码</label>
                        <div class="layui-input-inline">
                            <input type="password" name="password" lay-verify="pass" lay-reqtext="密码不能为空"
                                   placeholder="请输入密码" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">确认密码</label>
                        <div class="layui-input-inline">
                            <input type="password" name="password1" lay-verify="pass" lay-reqtext="密码不能为空"
                                   placeholder="请确认密码" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="realName" lay-verify="required" lay-reqtext="姓名不能为空"
                                   placeholder="请输入姓名" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">性别</label>
                        <div class="layui-input-inline">
                            <input type="radio" name="sex" value="1" title="男" checked="">
                            <input type="radio" name="sex" value="2" title="女">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-inline">
                            <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-register-submit">
                                立即注册
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>
    </div>
</div>
<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'login'], function () {
        var $ = layui.$
            , setter = layui.setter
            , admin = layui.admin
            , form = layui.form
            , router = layui.router()
            , search = router.search;
        form.render();
        //提交
        form.on('submit(LAY-user-register-submit)', function (obj) {
            var password = form.val('password');
            var password1 = form.val('password1');
            if (password != password1) {
                alert('两次输入密码不一致！！！');
                return ;
            }

            //请求登入接口
            admin.req({
                method: 'post'
                , url: '/sys/login/register'
                , data: obj.field
                , done: function (res) {
                    layer.msg('注册成功', {
                        offset: '15px'
                        , icon: 1
                        , time: 1000
                    }, function () {
                        location.href = '/toLoginView'; //后台主页
                    });
                }
            });
        });
        if (window != top) {
            top.location.href = location.href;
        }
        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 5) {
                    return '标题至少得5个字符啊';
                }
            }
            , pass: [
                /^[\S]{6,12}$/
                , '密码必须6到12位，且不能出现空格'
            ]
            , content: function (value) {
                layedit.sync(editIndex);
            }
        });
    });

</script>
</body>
</html>