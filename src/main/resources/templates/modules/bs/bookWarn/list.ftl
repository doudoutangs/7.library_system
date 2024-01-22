<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>超期提醒列表</title>
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
                            <label class="layui-form-label">标题：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="title" placeholder="标题" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">状态：</label>
                            <div class="layui-input-inline">
                                <select name="status" lay-verify="">
                                    <option value="">请选择</option>
                                    <option value="0">待归还</option>
                                    <option value="1">已归还</option>
                                    <option value="2">已损坏</option>
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
                        {{#  if(d.status==0){ }}
                        <button class="layui-btn layui-btn-xs layui-btn-warm">待归还</button>
                        {{#  } else if(d.status==1){ }}
                        <button class="layui-btn layui-btn-xs ">已归还</button>
                        {{#  } else if(d.status==2){ }}
                        <button class="layui-btn layui-btn-xs layui-btn-danger">已损坏</button>
                        {{#  } }}
                    </script>
                    <script type="text/html" id="revertTime2Tpl">
                        {{#  if(d.revertTime2 == null){ }}
                        <div>-</div>
                        {{#  } else { }}
                        <div>{{layui.util.toDateString(d.revertTime2, 'yyyy年MM月dd日')}}</div>
                        {{#  } }}
                    </script>
                    <script type="text/html" id="realRevertTimeTpl">
                        {{#  if(d.realRevertTime == null){ }}
                        <div>-</div>
                        {{#  } else { }}
                        <div>{{layui.util.toDateString(d.realRevertTime, 'yyyy年MM月dd日')}}</div>
                        {{#  } }}
                    </script>
                    <script type="text/html" id="table-content-list">
                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail"><i
                                    class="layui-icon layui-icon-about"></i>查看</a>
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
        bookWarnList: 'bs/bookWarnList',
    }).use(['index', 'bs/bookWarnList', 'table'], function () {
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
