<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>图书借还记录详情列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <input type="text" name="operateNo" class="form-control" hidden="hidden" value="${operateNo}">
    <div class="layui-row layui-col-space10">
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
        index: 'lib/index' //主入口模块
    }).use(['index', 'bs/bookOperateList', 'table'], function(){
        var table = layui.table
            ,form = layui.form,admin=layui.admin;
        //监听搜索
        form.on('submit(LAY-app-contlist-search)', function(data){
            var field = data.field;
            //执行重载
            table.reload('LAY-app-content-list', {
                where: field
            });
        });
        table.render({
            elem: "#LAY-app-content-list",
            url: "/bs/bookOperate/detail/${operateNo}",
            even: true,
            skin:'row',
            cellMinWidth: 80,
            cols: [[
                {field: "vipName", title: "会员姓名",width:100},
                {field: "operateType",title: "操作类型",templet: "#buttonTpl",width:100},
                {field: "bookName",title: "书名",width:150},
                {field: "operateName",title: "管理员姓名",width:100},
                {field: "createTime",title:"操作日期",minWidth: 100,templet:"<div>{{layui.util.toDateString(d.createTime, 'yyyy年MM月dd日 HH:mm:ss')}}</div>"}
            ]],
            page: !0,
            limit: 5,
            limits: [5, 10, 15, 20, 25],
            text: {
                none: "无数据！"
            }
        });
    });
</script>
</body>
</html>
