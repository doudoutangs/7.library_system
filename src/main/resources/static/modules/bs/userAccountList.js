;layui.define(["table", "form"], function (t) {
    var e = layui.$, table = layui.table, n = layui.form, admin = layui.admin;
    table.render({
        elem: "#LAY-app-content-list",
        url: "/bs/userAccount",
        even: true,
        toolbar: "#toolbar-operation-btn",
        skin: 'row',
        cellMinWidth: 80,
        cols: [[
            {type: "checkbox", fixed: "left"},
            // {field: "id", width: 60, title: "ID", sort: !0},
            {field: "vipName", title: "会员姓名",width: 90},
            {field: "operateType", title: "变动类型",width: 90, templet: "#buttonTpl"},
            {field: "amount", title: "变动金额",width: 90},
            {field: "changeReason", title: "变动原因",width: 90},
            {field: "operateName", title: "操作人",width: 90},
            {field: "createTime", title: "变动时间",width: 135,templet:"<div>{{layui.util.toDateString(d.createTime, 'yyyy年MM月dd日')}}</div>"},
            {title: "操作", minWidth: 210, align: "center", fixed: "right", toolbar: "#table-content-list"}
        ]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: {
            none: "暂无数据！"
        }
    });
    table.on('toolbar(LAY-app-content-list)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , checkData = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'recharge':
                layer.open({
                    type: 2
                    , title: '账号充值'
                    , content: '/bs/userAccount/form'
                    , maxmin: true
                    , scrollbar: false
                    , area: ['530px', '320px']
                    , btn: ['确定', '取消']
                    , yes: function (e, idx) {
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-submit");
                        l.layui.form.on("submit(layuiadmin-app-form-submit)", function (res) {
                            var field = res.field; //获取提交的字段
                            admin.req({
                                type: 'post',
                                url: "/bs/userAccount/recharge",
                                data: field,
                                cache: false,
                                done: function (res) {
                                    layer.msg('保存成功！', {
                                        time: 1000 //20s后自动关闭
                                    }, function () {
                                        table.reload("LAY-app-content-list", {
                                            curr: 1
                                        });
                                        layer.close(e); //再执行关闭
                                    });
                                }
                            });
                        }), a.trigger("click");
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
            case "del":
                layer.confirm("确定删除该图书？", function (cf) {
                    admin.req({
                        type: "DELETE",
                        cache: false,
                        url: "/bs/userAccount/delete/" + e.id,
                        done: function (res) {
                            layer.msg('删除成功！', {
                                time: 1000 //20s后自动关闭
                            });
                            table.reload("LAY-app-content-list", {
                                curr: 1
                            });
                            layer.close(cf);
                        }
                    });
                });
                break;
            default:
                break;
        }
    });
    t("bs/userAccountList", {});
});