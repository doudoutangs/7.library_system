;layui.define(["table", "form"], function (t) {
    var e = layui.$, table = layui.table, n = layui.form, admin = layui.admin;
    table.render({
        elem: "#LAY-app-content-list",
        url: "/bs/bookWarn",
        even: true,
        toolbar: "#toolbar-operation-btn",
        skin: 'row',
        cellMinWidth: 80,
        cols: [[
            // {type: "checkbox", fixed: "left"},
            // {field: "id", width: 60, title: "ID", sort: !0},
            // {field: "bookOperateNo", title: "借阅序号",width: 88},
            {field: "bookName", title: "图书名称",minWidth: 100},
            {field: "status", title: "状态", templet: "#buttonTpl", width: 80, align: "center"},
            {field: "revertTime", title: "应归还时间",width: 135,templet:"<div>{{layui.util.toDateString(d.revertTime, 'yyyy年MM月dd日')}}</div>"},
            {field: "revertTime2", title: "最晚归还时间",width: 135,templet:"<div>{{layui.util.toDateString(d.revertTime2, 'yyyy年MM月dd日')}}</div>"},
            {field: "lendTime", title: "借出时间",width: 135,templet:"<div>{{layui.util.toDateString(d.lendTime, 'yyyy年MM月dd日')}}</div>"},
            {field: "realRevertTime", title: "实际归还时间",width: 135, templet: "#realRevertTimeTpl", align: "center"},
            {field: "overDay", title: "逾期天数", width: 90},
            {field: "damagesAmount", title: "违约金", width: 80},
            {title: "操作", width: 80, align: "center", fixed: "right", toolbar: "#table-content-list"}
        ]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: {
            none: "暂无信息！"
        }
    });
    table.on('toolbar(LAY-app-content-list)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , checkData = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            default:
                break;
        }
    });
    table.on("tool(LAY-app-content-list)", function (t) {
        var e = t.data;
        switch (t.event) {
            case 'detail':
                layer.open({
                    type: 2,
                    title: "查看提醒详情",
                    content: "/bs/bookWarn/form/"+e.id,
                    maxmin: !0,
                    scrollbar: false,
                    area: ['550px', '450px'],
                    yes: function (e, idx) {
                    }
                })
                break;
            default:
                break;
        }
    });
    t("bs/bookWarnList", {});
});