<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
</head>
<body>
<div >
    <div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list"
         style="padding: 20px 30px 0 0;">
        <div class="layui-form-item">
            <input type="text" name="id" class="form-control" hidden="hidden" value="${user.id}">
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">原始密码：</label>
            <div class="layui-input-block">
                <input type="password" name="oldpwd" lay-verify="required" placeholder="原始密码" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码：</label>
            <div class="layui-input-block">
                <input type="password" id="password" name="password" lay-verify="required" placeholder="新密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码确认：</label>
            <div class="layui-input-block">
                <input type="password" id="password2" name="password2" lay-verify="required" placeholder="新密码确认"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item ">
            <label class="layui-form-label">
                <input type="button"  lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value=" 提交 ">
            </label>
        </div>
    </div>
</div>

<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        //主入口模块
        index: 'lib/index',
    }).use(['index', 'form'], function () {
        var $ = layui.$,form = layui.form,admin=layui.admin;
        form.on('switch(status)',function(data){
            var status=data.elem.checked?0:1;
            $(data.elem).attr('type', 'hidden').val(status);
        });
        form.on('submit(layuiadmin-app-form-submit)', function (data) {
            var password = $("#password").val();
            var password2 = $("#password2").val();
            if (password != password2) {
                alert("新密码，两次密码输入不一致");
                return false;
            }
            var field = data.field;
            admin.req({
                type: 'post',
                url: "/sys/login/modifyPWD",
                data: field,
                cache: false,
                done: function (res) {
                    layer.msg('保存成功！', {
                        time: 500 //20s后自动关闭
                    }, function () {
                        location.href = '/sys/login/logout'; //后台主页
                        location.href = '/toLoginView';

                    });
                }
            });
            return false;
        });
    })
</script>
</body>
</html>