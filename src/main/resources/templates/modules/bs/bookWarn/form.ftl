<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>提醒详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list"
     style="padding: 20px 30px 0 0;">
    <table class="layui-table" lay-even="" lay-skin="nob">
        <thead>
        <tr>
            <th>借阅序号</th>
            <th>${bookWarn.bookOperateNo!}</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>图书名称</td>
            <td>${bookWarn.bookName!}</td>
        </tr>
        <tr>
            <td>借书人姓名</td>
            <td>${bookWarn.vipName!}</td>
        </tr>
        <tr>
            <td>状态</td>
            <td>${bookWarn.status!}</td>
        </tr>
        <tr>
            <td>借出时间</td>
            <td>${bookWarn.lendTime!}</td>
        </tr>
        <tr>
            <td>应归还时间</td>
            <td>${bookWarn.revertTime!}</td>
        </tr>
        <tr>
            <td>最晚应归还时间</td>
            <td>${bookWarn.revertTime2!}</td>
        </tr>
        <tr>
            <td>实际归还时间</td>
            <td>${bookWarn.realRevertTime!}</td>
        </tr>
        <tr>
            <td>逾期天数</td>
            <td>${bookWarn.overDay!}</td>
        </tr>
        <tr>
            <td>违约金</td>
            <td>${bookWarn.damagesAmount!}</td>
        </tr>
        <tr>
            <td>借书操作人</td>
            <td>${bookWarn.lendOperateName!}</td>
        </tr>
        <tr>
            <td>还书操作人</td>
            <td>${bookWarn.revertOperateName!}</td>
        </tr>
        <tr>
            <td>延期操作人</td>
            <td>${bookWarn.extendOperateName!}</td>
        </tr>
        <tr>
            <td>定损操作人</td>
            <td>${bookWarn.lossOperateName!}</td>
        </tr>
        </tbody>
    </table>
</div>
<script src="/static/layui/layui.js"></script>
<script>
    layui.use('layedit', function(){
        var layedit = layui.layedit;
        layedit.build('content', {
            tool: ['strong', 'italic', 'underline', 'del', '|', 'left', 'center' , 'right', 'link', 'unlink', 'face']
        });
    });
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        //主入口模块
        index: 'lib/index'
    }).use(['index', 'form'], function () {
        var $ = layui.$, form = layui.form, admin = layui.admin;
        form.on('switch(status)', function (data) {
            var state = data.elem.checked ? 0 : 1;
            $(data.elem).attr('type', 'hidden').val(state);
        });
    })
</script>
</body>
</html>