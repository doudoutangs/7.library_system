<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>公告编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list"
     style="padding: 20px 30px 0 0;">
    <div class="layui-row layui-form-item">
        <input type="text" name="id" class="form-control" hidden="hidden">
        <div class="layui-col-xs6">
            <label class="layui-form-label">公告标题：</label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="required" autocomplete="off" class="layui-input"
                       placeholder="公告标题">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">发布日期：</label>
            <div class="layui-input-block">
                <input type="date" name="releaseDate" lay-verify="required" autocomplete="off" class="layui-input"
                       placeholder="发布日期">
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">状态：</label>
            <div class="layui-input-inline">
                <input type="radio" name="state" lay-filter="menuType-filter" value="0" title="有效" checked>
                <input type="radio" name="state" lay-filter="menuType-filter" value="1" title="失效">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">公告内容：</label>
        <div class="layui-input-block">
<#--            <textarea name="content" placeholder="公告内容" class="layui-textarea"></textarea>-->
            <textarea id="content" name="content" style="display: none;"></textarea>

        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit"
               value="确认添加">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit"
               value="确认编辑">
    </div>
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