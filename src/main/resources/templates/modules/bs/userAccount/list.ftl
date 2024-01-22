<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>余额变动列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                    <div class="layui-inline">
                        <label class="layui-form-label">余额操作：</label>
                        <div class="layui-input-inline">
                            <select name="operateType">
                                <option >请选择</option>
                                <option value="0">增加</option>
                                <option value="1">扣减</option>
                            </select>
                        </div>
                    </div>

                        <div class="layui-inline">
                            <button class="layui-btn layuiadmin-btn-list" lay-submit
                                    lay-filter="LAY-app-contlist-search">
                                <i class="layui-icon layui-icon-search layuiadmin-button-btn"> 搜索</i>
                            </button>
                        </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <script type="text/html" id="toolbar-operation-btn">
                        <@shiro.hasPermission name="bs:book:add">
                            <button class="layui-btn layuiadmin-btn-list layui-btn-sm layui-icon layui-icon-add-1"
                                    lay-event="recharge">充值
                            </button>
                        </@shiro.hasPermission>
                    </script>
                    <table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
                    <script type="text/html" id="buttonTpl">
                        {{#  if(d.operateType==0){ }}
                        <button class="layui-btn layui-btn-xs">增加</button>
                        {{#  } else { }}
                        <button class="layui-btn layui-btn-xs layui-btn-danger">扣减</button>
                        {{#  } }}
                    </script>
<#--                    <script type="text/html" id="table-content-list">-->
<#--                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="bookDetail">-->
<#--                            <i class="layui-icon layui-icon-about"></i>详情-->
<#--                        </a>-->
<#--                    </script>-->
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        index: 'lib/index', //主入口模块
        userAccountList: 'bs/userAccountList',
    }).use(['index', 'bs/userAccountList', 'table'], function () {
        var table = layui.table
            , form = layui.form, admin = layui.admin;
        //监听搜索
        form.on('submit(LAY-app-contlist-search)', function (data) {
            var field = data.field;
            //执行重载
            table.reload('LAY-app-content-list', {
                where: field
            });
        });
    });
</script>
</body>
</html>
