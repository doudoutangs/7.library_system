<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>图书借还记录</title>
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
            <label class="layui-form-label">专业名称：</label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input" placeholder="专业名称">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">专业编码：</label>
            <div class="layui-input-block">
                <input type="number" name="no" lay-verify="required" autocomplete="off" class="layui-input" placeholder="专业编码">
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs6">
            <label class="layui-form-label">所属院系：</label>
            <div class="layui-input-block">
                <select name="universitiesId" lay-verify="required">
                    <#if universitiesList?? && (universitiesList?size >0)>
                        <#list universitiesList as universities>
                            <option value="${universities.id}">${universities.name}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">设立日期：</label>
            <div class="layui-input-block">
                <input type="date" name="birth" lay-verify="required" autocomplete="off" class="layui-input"
                       placeholder="设立日期">
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs6">
            <label class="layui-form-label">专业学制：</label>
            <div class="layui-input-block">
                <input type="number" name="year" lay-verify="required" autocomplete="off" class="layui-input" placeholder="专业学制">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">专业分类：</label>
            <div class="layui-input-block">
                <select name="category" lay-verify="required">
                    <#if majorTypeList?? && (majorTypeList?size >0)>
                        <#list majorTypeList as majorType>
                            <option value="${majorType.dictValue}">${majorType.dictName}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">专业简介：</label>
        <div class="layui-input-block">
            <textarea name="introduction" placeholder="专业简介" class="layui-textarea"></textarea>
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