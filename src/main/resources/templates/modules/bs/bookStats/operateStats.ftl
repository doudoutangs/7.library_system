<!DOCTYPE html>
<html lang="zh_CN">

<head>
    <meta charset="utf-8">
    <title>图书统计</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/style/admin.css" media="all">
    <script type="text/javascript" src="/static/lib/echarts.min.js"></script>

</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space16">
        <div class="layui-col-sm6 layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">
                    月借书统计
                </div>
                <div class="layui-card-body layuiadmin-card-list">
                    <div id="monthLend" style="width: 100%;height:160px;"></div>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">
                    月还书统计
                </div>
                <div class="layui-card-body layuiadmin-card-list">
                    <div id="monthRevert" style="width: 100%;height:160px;"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-sm6 layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">
                    热门图书
                </div>
                <div class="layui-card-body layuiadmin-card-list">
                    <div id="hotBook" style="width: 100%;height:400px;"></div>
                </div>
            </div>
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
            var yearList;
            admin.req({
                type: "GET",
                cache: false,
                async: false,
                contentType: "application/json; charset=utf-8",
                url: "/bs/bookStats/yearStats",
                done: function (res) {
                    yearList = res.data;
                }
            });

            //1.按月统计借书数量
            var monthLendParam = JSON.stringify(yearList);
            var serieList = [];
            var monthLendInfo;
            admin.req({
                type: "POST",
                cache: false,
                async: false,
                contentType: "application/json; charset=utf-8",
                data: monthLendParam,
                url: "/bs/bookStats/operateStats/0",
                done: function (res) {
                    monthLendInfo = res.data.statis;
                    for (var i = 0, size = monthLendInfo[0].length - 1; i < size; i++) {
                        serieList.push({
                            type: 'bar',
                            itemStyle: {
                                color: '#a90000'
                            }
                        });
                    }
                }
            });

            var monthLendChart = echarts.init(document.getElementById('monthLend'));
            monthLendOption = {
                legend: {show: true},
                tooltip: {},
                dataset: {
                    source: monthLendInfo
                },
                xAxis: {
                    type: 'category',
                    axisTick: {
                        alignWithLabel: true
                    }
                },
                yAxis: {},
                series: serieList
            };
            if (monthLendOption && typeof monthLendOption === "object") {
                monthLendChart.setOption(monthLendOption, true);
            }
            //2.按月统计还书数量
            var monthRevertParam = JSON.stringify(yearList);
            var serieList = [];
            var monthRevertInfo;
            admin.req({
                type: "POST",
                cache: false,
                async: false,
                contentType: "application/json; charset=utf-8",
                data: monthRevertParam,
                url: "/bs/bookStats/operateStats/1",
                done: function (res) {
                    monthRevertInfo = res.data.statis;
                    for (var i = 0, size = monthRevertInfo[0].length - 1; i < size; i++) {
                        serieList.push({
                            type: 'bar'
                        });
                    }
                }
            });

            var monthRevertChart = echarts.init(document.getElementById('monthRevert'));
            monthRevertOption = {
                legend: {show: true},
                tooltip: {},
                dataset: {
                    source: monthRevertInfo
                },
                xAxis: {
                    type: 'category',
                    axisTick: {
                        alignWithLabel: true
                    }
                },
                yAxis: {},
                series: serieList
            };
            if (monthRevertOption && typeof monthRevertOption === "object") {
                monthRevertChart.setOption(monthRevertOption, true);
            }
            //热门图书排行
            var hotBookInfo;
            admin.req({
                type: "POST",
                cache: false,
                async: false,
                url: "/bs/bookStats/hotBookStats",
                contentType: "application/json; charset=utf-8",
                done: function (res) {
                    hotBookInfo = res.data;

                }
            });
            var hotBookChart = echarts.init(document.getElementById('hotBook'));
            var hotBookOption = {
                dataset: {
                    source: hotBookInfo
                },
                grid: {containLabel: true},
                xAxis: {name: 'hot'},
                yAxis: {
                    type: 'category',
                    axisLabel: {interval: 0},
                    axisTick: {
                        alignWithLabel: true
                    }
                },
                // yAxis: {type: 'category', axisLabel: {interval: 0, rotate: 40}},
                series: [
                    {
                        type: 'bar',
                        encode: {
                            // Map the "amount" column to X axis.
                            x: 'hot',
                            // Map the "product" column to Y axis
                            y: 'product'
                        }
                    }
                ]
            };
            hotBookOption && hotBookChart.setOption(hotBookOption);

        });

    </script>
</div>
</body>
</html>
