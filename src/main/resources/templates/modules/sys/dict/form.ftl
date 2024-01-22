
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>字典编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
</head>
<body>
<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <input type="text" name="id" class="form-control" hidden="hidden">
        <label class="layui-form-label">字典类型：</label>
        <div class="layui-input-block">
            <select name="parentId" lay-verify="required">
                <option value="-1">根类型</option>
                <#if dictTypeList?? && (dictTypeList?size >0)>
                    <#list dictTypeList as dict>
                        <option value="${dict.id}">${dict.dictName}</option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">字典名称：</label>
        <div class="layui-input-block">
            <input type="text" name="dictName" lay-verify="required" placeholder="请输入字典名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">字典编码：</label>
        <div class="layui-input-block">
            <input type="text" name="dictValue" lay-verify="required" placeholder="请输入字典编号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-col-xs6">
        <label class="layui-form-label">字典排序：</label>
        <div class="layui-input-block">
            <input type="number" name="dictSort" lay-verify="required" autocomplete="off" class="layui-input" placeholder="设置字典顺序">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">启用状态：</label>
        <div class="layui-input-block">
            <input type="checkbox" lay-verify="required" lay-filter="status" value="0" checked name="status" lay-skin="switch" lay-text="启用|禁用">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确认添加">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit" value="确认编辑">
    </div>
</div>
<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        //主入口模块
        index: 'lib/index'
    }).use(['index', 'form'], function(){
        var $ = layui.$,form = layui.form,admin=layui.admin;
        form.on('switch(status)',function(data){
            var state=data.elem.checked?0:1;
            $(data.elem).attr('type', 'hidden').val(state);
        });

    })
</script>
</body>
</html>