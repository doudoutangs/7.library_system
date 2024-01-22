<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/style/admin.css" media="all">
</head>
<body onload="ShowTime();">
<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md4">
            <h1><strong> 滕王阁 </strong>图书馆</h1>
            <h4><span id="stime" style="background-color: #0C0C0C; color: #FBFBFB"></span></h4>
            <br>
        </div>
        <div class="layui-col-md6">
            <div style="padding: 18px;">
                <h3><strong>阅读时请保持微笑(*￣︶￣)，因为有人将告诉你，你不知道的事儿</strong></h3>
            </div>
        </div>
        <div class="layui-col-md2">
            <div style="padding: 10px;">
                <a class="layui-btn layui-btn-normal" layadmin-event="toLoginView">登录</a>
                <a class="layui-btn layui-btn-warm" layadmin-event="toRegisterView">注册</a>
            </div>
        </div>
    </div>

    <div class="layui-bg-gray" style="padding: 10px;">
        <fieldset class="layui-elem-field">
            <legend>馆内通知</legend>
            <div class="layui-field-box">
                <div class="layui-collapse" lay-filter="test">
                    <#if noticeList?? && (noticeList?size >0)>
                        <#list noticeList as notice>
                            <div class="layui-colla-item">
                                <h2 class="layui-colla-title">${notice.title}</h2>
                                <div class="layui-colla-content">
                                    ${notice.content}
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
        </fieldset>
    </div>
    <hr class="layui-border-blue" style="width: 10px;">
    <div class="layui-bg-gray" style="padding: 10px;">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">

                <fieldset class="layui-elem-field">
                    <legend>图书检索</legend>
                    <div class="layui-field-box">
                        <div class="layui-row layui-col-space10">
                            <div class="layui-col-md12">
                                <div class="layui-card">
                                    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <label class="layui-form-label"
                                                       style="width:85px">关键字检索：</label>
                                                <div class="layui-input-inline" style="width:290px">
                                                    <input type="text" name="nameOrAuthor"
                                                           placeholder="图书名称/图书作者/图书编号" autocomplete="off"
                                                           class="layui-input">
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <button class="layui-btn layuiadmin-btn-list" lay-submit
                                                        lay-filter="LAY-app-contlist-search">
                                                    <i class="layui-icon layui-icon-search layuiadmin-button-btn">
                                                        检索</i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-col-md12">
                                    <div class="layui-card">
                                        <div class="layui-card-body">
                                            <table id="home-book-list" lay-filter="LAY-app-content-list"></table>
                                            <script type="text/html" id="buttonTpl">
                                                {{#  if(d.state==0){ }}
                                                <button class="layui-btn layui-btn-xs">上架</button>
                                                {{#  } else { }}
                                                <button class="layui-btn layui-btn-xs layui-btn-danger">下架</button>
                                                {{#  } }}
                                            </script>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                </fieldset>
            </div>
        </div>
    </div>
    <div style="padding: 10px;text-align: center">
        <ul>
            <li><a target="_blank" href="">京ICP备05014420号</a> <span>|</span>
                <a>电话：（+86 10）88881234</a> <span>|</span> <b>© 滕王阁图书馆版权所有</b>
            </li>
        </ul>
    </div>
</div>

<script src="/static/layui/layui.js"></script>
<script>


    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        index: 'lib/index', //主入口模块
        homeBookList: 'bs/homeBookList',
    }).use(['index', 'bs/homeBookList', 'table'], function () {
        var table = layui.table
            , form = layui.form, admin = layui.admin;
        //监听搜索
        form.on('submit(LAY-app-contlist-search)', function (data) {
            var field = data.field;
            //执行重载
            table.reload('home-book-list', {
                where: field
            });
        });
    });

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