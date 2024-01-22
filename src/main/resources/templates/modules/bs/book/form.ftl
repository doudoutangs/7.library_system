<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>图书编辑</title>
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
            <label class="layui-form-label">图书名称：</label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input" placeholder="图书名称">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">图书作者：</label>
            <div class="layui-input-block">
                <input type="text" name="author" lay-verify="required" autocomplete="off" class="layui-input" placeholder="图书作者">
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">

        <div class="layui-col-xs6">
            <label class="layui-form-label">出版社：</label>
            <div class="layui-input-block">
                <select name="press" lay-verify="required">
                    <#if pressList?? && (pressList?size >0)>
                        <#list pressList as press>
                            <option value="${press.dictValue}">${press.dictName}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">首次发行日期：</label>
            <div class="layui-input-block">
                <input type="date" name="releaseDate" lay-verify="required" autocomplete="off" class="layui-input"
                       placeholder="首次发行日期">
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <label class="layui-form-label">图书分类：</label>
        <div class="layui-input-block">
            <select name="category" lay-verify="required">
                <#if categoryList?? && (categoryList?size >0)>
                    <#list categoryList as category>
                        <option value="${category.dictValue}">${category.dictName}</option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <label class="layui-form-label">图书位置：</label>
        <div class="layui-input-block">
            <select name="position" lay-verify="required">
                <#if bookAddressList?? && (bookAddressList?size >0)>
                    <#list bookAddressList as address>
                        <option value="${address.dictValue}">${address.dictName}</option>
                    </#list>
                </#if>
            </select>

        </div>
    </div>
    <div class="layui-row layui-form-item">
        <input type="text" name="id" class="form-control" hidden="hidden">
        <div class="layui-col-xs6">
            <label class="layui-form-label">图书单价：</label>
            <div class="layui-input-block">
                <input type="number" name="price" lay-verify="required" autocomplete="off" class="layui-input" placeholder="图书单价">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">图书数量：</label>
            <div class="layui-input-block">
                <input type="number" name="total" lay-verify="required" autocomplete="off" class="layui-input" placeholder="图书数量">
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs6">
            <label class="layui-form-label">图书状态：</label>
            <div class="layui-input-block">
                <input type="radio" name="state" value="0" title="上架" checked=""><div class="layui-unselect layui-form-radio layui-form-radioed"><i class="layui-anim layui-icon layui-anim-scaleSpring"></i><div>上架</div></div>
                <input type="radio" name="state" value="1" title="下架"><div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i><div>下架</div></div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">图书简介：</label>
        <div class="layui-input-block">
            <textarea name="introduction" placeholder="图书简介" class="layui-textarea"></textarea>
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