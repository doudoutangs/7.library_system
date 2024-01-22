;layui.define(["table", "form"], function (t) {
    var e = layui.$, table = layui.table, n = layui.form,admin=layui.admin;

    table.render({
        elem: "#LAY-app-content-list",
        url: "/bs/bookOperate",
        even: true,
        toolbar:"#toolbar-operation-btn",
        skin:'row',
        cols: [[
            // {type: "checkbox", fixed: "left"},
            {field: "id", width: 100, title: "操作序号"},
            {field: "vipName", title: "会员姓名",width: 100},
            {field: "bookName", title: "图书名称"},
            {field: "operateType",title: "操作类型",width: 90,templet: "#buttonTpl"},
            {field: "operateCount",title: "操作数量",width: 90},
            {field:"createTime",title:"操作日期",width: 200,templet:"<div>{{layui.util.toDateString(d.createTime, 'yyyy年MM月dd日 HH:mm:ss')}}</div>"},
            {title: "操作",width: 90,align: "center",fixed: "right",toolbar: "#table-content-list"}
        ]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: {
            none: "暂无操作数据！"
        }
    });
    table.on("tool(LAY-app-content-list)", function (t) {
        var e = t.data;
        switch (t.event) {
            case 'get':
                layer.open({
                    type: 2,
                    title: "查看借还详情",
                    content: "/bs/bookOperate/form/"+ e.id,
                    maxmin: !0,
                    scrollbar: false,
                    area: ['730px', '420px']
                })
                break;
            default:
                break;
        }
    });
    t("bs/bookOperateList", {});

});