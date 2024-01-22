<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>字典列表</title>
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
                            <label class="layui-form-label">字典名称：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="dictName" placeholder="字典名称" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">根字典类型：</label>
                            <div class="layui-input-inline">
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
                    <script type="text/html" id="toolbar-operation-btn">
                        <@shiro.hasPermission name="sys:dict:add">
                            <button class="layui-btn layuiadmin-btn-list layui-btn-sm layui-icon layui-icon-add-1"
                                    lay-event="add"> 添加字典
                            </button>
                        </@shiro.hasPermission>
                        <button class="layui-btn layuiadmin-btn-list layui-btn-primary layui-btn-sm layui-icon layui-icon-down"
                                lay-event="expand"> 全部展开
                        </button>
                        <button class="layui-btn layuiadmin-btn-list layui-btn-primary layui-btn-sm layui-icon layui-icon-up"
                                lay-event="fold"> 全部折叠
                        </button>
                    </script>
                    <script type="text/html" id="table-content-list">
                        {{# if(d.dictType!='api'){ }}
                        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="addChild"><i
                                    class="layui-icon layui-icon-edit"></i>添加子节点</a>
                        {{# } else { }}
                        <a class="layui-btn layui-btn-primary layui-btn-xs layui-btn-disabled"><i
                                    class="layui-icon layui-icon-edit"></i>添加子节点</a>
                        {{#  } }}
                        <@shiro.hasPermission name="sys:dict:edit">
                            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit"><i
                                        class="layui-icon layui-icon-edit"></i>编辑</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="sys:dict:delete">
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
        treeTable: 'treetable/treeTable',
        dictList: 'sys/dictList'
    }).use(['index', 'sys/dictList', 'table', 'treeTable'], function () {
        var table = layui.table
            , form = layui.form, admin = layui.admin, treeTable = layui.treeTable;
        //监听搜索
        form.on('submit(LAY-app-contlist-search)', function (data) {
            var field = data.field;
            //执行重载
            treeTable.reload('LAY-app-content-list', {
                where: field
            });
        });
    });
</script>
</body>
</html>
