<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>图书借还记录列表</title>
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
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">会员姓名：</label>
                            <div class="layui-input-inline">

                                <select name="vipId" >
                                    <option value="">请选择</option>
                                    <#if vipList?? && (vipList?size >0)>
                                        <#list vipList as vip>
                                            <option value="${vip.id}">${vip.realName}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">操作类型：</label>
                            <div class="layui-input-inline">

                                <select name="operateType" >
                                    <option value="">请选择</option>
                                    <option value="0">借书</option>
                                    <option value="1">还书</option>
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
        </div>
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
                    <script type="text/html" id="buttonTpl">
                        {{#  if(d.operateType){ }}
                        <button class="layui-btn layui-btn-xs">还书</button>
                        {{#  } else { }}
                        <button class="layui-btn layui-btn-xs layui-btn-danger">借书</button>
                        {{#  } }}
                    </script>
                    <script type="text/html" id="table-content-list">
                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="get">
                            <i class="layui-icon layui-icon-about"></i>详情
                        </a>
                    </script>
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
        bookOperateList: 'bs/bookOperateList',
    }).use(['index', 'bs/bookOperateList', 'table'], function () {
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
