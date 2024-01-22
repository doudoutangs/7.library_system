;layui.define(["table", "form"], function (t) {
    var e = layui.$, table = layui.table, n = layui.form, admin = layui.admin;
    table.render({
        elem: "#LAY-app-content-list",
        url: "/bs/bookRevert",
        even: true,
        toolbar: "#toolbar-operation-btn",
        skin: 'row',
        cellMinWidth: 80,
        cols: [[
            {type: "checkbox", fixed: "left"},
            // {field: "id", width: 60, title: "ID", sort: !0},
            {field: "bookOperateNo", title: "借阅序号",width: 88},
            {field: "vipName", title: "借书人",width: 80},
            {field: "vipPhone", title: "借书人电话",width: 116},
            {field: "bookName", title: "图书名称",width: 100},
            {field: "status", title: "状态", templet: "#buttonTpl", width: 80, align: "center"},
            {field: "revertTime", title: "应归还时间",width: 135,templet:"<div>{{layui.util.toDateString(d.revertTime, 'yyyy年MM月dd日')}}</div>"},
            {field: "revertTime2", title: "最晚归还时间",width: 135, templet: "#revertTime2Tpl", align: "center"},
            {field: "lendTime", title: "借出时间",width: 135,templet:"<div>{{layui.util.toDateString(d.lendTime, 'yyyy年MM月dd日')}}</div>"},
            {field: "realRevertTime", title: "实际归还时间",width: 135, templet: "#realRevertTimeTpl", align: "center"},
            {field: "overDay", title: "逾期天数", width: 90},
            {field: "damagesAmount", title: "违约金", width: 80},
            {title: "操作", width: 81, align: "center", fixed: "right", toolbar: "#table-content-list"}
        ]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: {
            none: "暂无记录！"
        }
    });
    table.on('toolbar(LAY-app-content-list)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , checkData = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'revert':
                if (checkData.length === 0) {
                    return layer.msg('请选择要还的书籍');
                }
                var bookWarnIds = [];
                for (var i = 0; i < checkData.length; i++) {
                    var row = checkData[i];
                    bookWarnIds.push(row.id);
                }
                admin.req({
                    type: "POST",
                    cache: false,
                    url: "/bs/bookRevert/revert",
                    data: {bookWarnIds: bookWarnIds},
                    done: function (res) {
                        layer.msg('还书成功！', {
                            time: 2000 //20s后自动关闭
                        });
                        layer.close(index);
                        table.reload("LAY-app-content-list");
                    }
                });
                break;
            case 'delayRevert':
                if (checkData.length === 0) {
                    return layer.msg('请选择要还的书籍');
                }
                var bookWarnIds = [];
                for (var i = 0; i < checkData.length; i++) {
                    var row = checkData[i];
                    bookWarnIds.push(row.id);
                }
                admin.req({
                    type: "POST",
                    cache: false,
                    url: "/bs/bookRevert/delayRevert",
                    data: {bookWarnIds: bookWarnIds},
                    done: function (res) {
                        layer.msg('延期还书成功！', {
                            time: 2000 //20s后自动关闭
                        });
                        layer.close(index);
                        table.reload("LAY-app-content-list");
                    }
                });
                break;
            case 'loss':
                if (checkData.length === 0) {
                    return layer.msg('请选择要还的书籍');
                }
                var bookWarnIds = [];
                for (var i = 0; i < checkData.length; i++) {
                    var row = checkData[i];
                    bookWarnIds.push(row.id);
                }
                admin.req({
                    type: "POST",
                    cache: false,
                    url: "/bs/bookRevert/revert",
                    data: {bookWarnIds: bookWarnIds},
                    done: function (res) {
                        layer.msg('还书成功！', {
                            time: 2000 //20s后自动关闭
                        });
                        layer.close(index);
                        table.reload("LAY-app-content-list");
                    }
                });
                break;
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
    t("bs/bookRevertList", {});
});