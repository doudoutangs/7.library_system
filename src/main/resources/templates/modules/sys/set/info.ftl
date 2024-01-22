

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基本资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label">用户姓名：</label>
        <div class="layui-input-block">
            <input type="text" name="realName" value="${user.realName!}" class="layui-input" readonly="true">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">登录账号：</label>
        <div class="layui-input-block">
            <input type="text" name="username" value="${user.username!}" class="layui-input" readonly="true">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">图书证类型：</label>
        <div class="layui-input-block">
            <input type="text" name="cardTypeName" value="${user.cardTypeName!}" class="layui-input" readonly="true">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">账户余额：</label>
        <div class="layui-input-block">
            <input type="text" name="amount" value="${user.amount!0}￥" class="layui-input" readonly="true">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电话：</label>
        <div class="layui-input-block">
            <input type="text" name="phone" value="${user.phone!}" class="layui-input" readonly>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱：</label>
        <div class="layui-input-block">
            <input type="text" name="email" value="${user.email!}" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">城市：</label>
        <div class="layui-input-block">
            <input type="text" name="cityName" value="${user.cityName!}" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">地址：</label>
        <div class="layui-input-block">
            <input type="text" name="address" value="${user.address!}" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确认添加">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit" value="确认修改">
    </div>
</div>

<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        //主入口模块
        index: 'lib/index',
        treeSelect:'treeSelect/treeSelect',
        xmSelect:'xm-select/xm-select'
    }).use(['index', 'form',"treeSelect",'xmSelect'], function(){
        var $ = layui.$,form = layui.form,admin=layui.admin,treeSelect=layui.treeSelect,xmSelect=layui.xmSelect;
        form.on('switch(status)',function(data){
            var status=data.elem.checked?1:0;
            $(data.elem).attr('type', 'hidden').val(status);
        });
    })
    var url = "/sys/login/modifyPWD";
</script>
</body>
</html>