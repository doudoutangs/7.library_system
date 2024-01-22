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
                    月上下架统计
                </div>
                <div class="layui-card-body layuiadmin-card-list">
                    <div id="monthUpDown" style="width: 100%;height:410px;"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-sm6 layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">
                    图书分类统计
                </div>
                <div class="layui-card-body layuiadmin-card-list">
                    <div id="bookCategory" style="width: 100%;height:410px;"></div>
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
            var serieList = [
                {type: 'bar', seriesLayoutBy: 'row',itemStyle: {color: '#00FFCC'}},
                {type: 'bar', seriesLayoutBy: 'row',itemStyle: {color: '#33FF33'}}
            ];
            var monthUpDownInfo;
            admin.req({
                type: "GET",
                cache: false,
                async: false,
                contentType: "application/json; charset=utf-8",
                url: "/bs/bookStats/upDownStats",
                done: function (res) {
                    monthUpDownInfo = res.data;
                    for (var i = 0, size = monthUpDownInfo[0].length - 1; i < size; i++) {
                        serieList.push(
                            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1}
                        );
                    }
                }
            });

            var monthUpDownChart = echarts.init(document.getElementById('monthUpDown'));
            monthUpDownOption = {
                legend: {
                    orient: 'horizontal',
                    x: 'center',
                    y: 'top'
                },
                tooltip: {
                    show: true,
                    trigger: 'item'
                },
                dataset: {
                    source: monthUpDownInfo
                },
                xAxis: [
                    {
                        type: 'category',
                        gridIndex: 0,
                        axisTick: {
                            alignWithLabel: true
                        }
                    },
                    {
                        type: 'category',
                        gridIndex: 1,
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis: [{gridIndex: 0}, {gridIndex: 1}],
                grid: [{bottom: '55%'}, {top: '55%'}],
                series: serieList
            };
            if (monthUpDownOption && typeof monthUpDownOption === "object") {
                monthUpDownChart.setOption(monthUpDownOption, true);
            }


            //按图书分类统计
            var bookCategoryInfo;
            admin.req({
                type: "GET",
                cache: false,
                async: false,
                url: "/bs/bookStats/bookCategory",
                done: function (res) {
                    bookCategoryInfo = res.data;
                }
            });
            var bookCategoryChart = echarts.init(document.getElementById('bookCategory'));
            var bookCategoryOption = {
                title: {
                    text: '图书分类',
                    subtext: '图书数量',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        name: '图书数量',
                        type: 'pie',
                        radius: '50%',
                        data: bookCategoryInfo,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            bookCategoryOption && bookCategoryChart.setOption(bookCategoryOption);

        });

    </script>
</div>
</body>
</html>
