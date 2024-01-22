<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>滕王阁图书管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/style/admin.css" media="all">
</head>
<body onload="ShowTime();">

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <p>
        <div class="layui-row">
            <h1> 欢迎使用 <strong> 滕王阁</strong>图书管理系统</h1><br/>
            <h2> 现在是北京时间：<span id="stime"></span></h2>
        </div>
        </p>
    </div>
</div>

<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    });

    // .use(['index', 'sample']);

    function check(val) {
        if (val < 10) {
            return ("0" + val);
        } else {
            return (val);
        }
    }

    function ShowTime() {
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hour = date.getHours();
        var minutes = date.getMinutes();
        var second = date.getSeconds();
        var timestr = year + "年" + month + "月" + day + "日  " + check(hour)
            + ":" + check(minutes) + ":" + check(second);
        document.getElementById("stime").innerHTML = timestr;
        var timerID = setTimeout('ShowTime()', 1000);
    }
</script>
</body>
</html>