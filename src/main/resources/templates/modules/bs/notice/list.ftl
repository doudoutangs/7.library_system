<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>招聘信息列表</title>
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
                            <label class="layui-form-label">公告标题：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="title" placeholder="公告标题" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">状态：</label>
                            <div class="layui-input-inline">
                                <select name="state" lay-verify="">
                                    <option value="">请选择</option>
                                    <option value="0">有效</option>
                                    <option value="1">失效</option>
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
                    <script type="text/html" id="toolbar-operation-btn">
                        <@shiro.hasPermission name="bs:notice:add">
                            <button class="layui-btn layuiadmin-btn-list layui-btn-sm layui-icon layui-icon-add-1"
                                    lay-event="add">添加
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="bs:notice:batchDel">
                            <button class="layui-btn layuiadmin-btn-list layui-btn-primary layui-btn-sm layui-icon layui-icon-delete"
                                    lay-event="batchdel">批量删除
                            </button>
                        </@shiro.hasPermission>
                    </script>
                    <table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
                    <script type="text/html" id="buttonTpl">
                        {{#  if(d.state==0){ }}
                        <button class="layui-btn layui-btn-xs">有效</button>
                        {{#  } else { }}
                        <button class="layui-btn layui-btn-xs layui-btn-danger">失效</button>
                        {{#  } }}
                    </script>
                    <script type="text/html" id="table-content-list">
                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="get"><i
                                    class="layui-icon layui-icon-about"></i>查看</a>
                        <@shiro.hasPermission name="bs:notice:edit">
                            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit"><i
                                        class="layui-icon layui-icon-edit"></i>编辑</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="bs:notice:delete">
                            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i
                                        class="layui-icon layui-icon-delete"></i>删除</a>
                        </@shiro.hasPermission>
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
        noticeList: 'bs/noticeList',
    }).use(['index', 'bs/noticeList', 'table'], function () {
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
