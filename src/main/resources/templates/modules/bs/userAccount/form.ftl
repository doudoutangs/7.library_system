<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>余额充值</title>
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
        <div class="layui-inline">
            <label class="layui-form-label">搜索会员</label>
            <div class="layui-input-inline">
                <select name="vipId" lay-verify="required" lay-search="">
                    <option value="">直接选择或搜索选择</option>

                    <#if vipList?? && (vipList?size >0)>
                        <#list vipList as vip>
                            <option value="${vip.id}">${vip.realName}-${vip.username}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <input type="text" name="id" class="form-control" hidden="hidden">
        <div class="layui-col-xs6">
            <label class="layui-form-label">充值金额：</label>
            <div class="layui-input-block">
                <input type="number" name="amount" lay-verify="required" autocomplete="off" class="layui-input" placeholder="充值金额">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">变动原因：</label>
        <div class="layui-input-block">
            <select name="changeReason" lay-verify="required" lay-search="">
                <option value="">直接选择或搜索选择</option>
                <#if accountChange?? && (accountChange?size >0)>
                    <#list accountChange as reason>
                        <option value="${reason.dictName}">${reason.dictName}</option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit"
               value="确认提交">
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