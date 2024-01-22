;layui.define(["table", "form"], function (t) {
    var e = layui.$, table = layui.table, n = layui.form, admin = layui.admin;
    table.render({
        elem: "#home-book-list",
        url: "/bs/book",
        even: true,
        // toolbar: "#toolbar-operation-btn",
        skin: 'row',
        cellMinWidth: 80,
        cols: [[
            // {type: "checkbox", fixed: "left"},
            // {field: "id", width: 60, title: "ID", sort: !0},
            {field: "bookNo", title: "图书编号"},
            {field: "name", title: "图书名称"},
            {field: "author", title: "作者"},
            {field: "pressName", title: "出版社"},
            {field: "positionName", title: "位置"},
            {field: "total", title: "数量"}
        ]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: {
            none: "暂无图书数据！"
        }
    });
    table.on('toolbar(LAY-app-content-list)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , checkData = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                layer.open({
                    type: 2
                    , title: '添加图书'
                    , content: '/bs/book/form'
                    , maxmin: true
                    , scrollbar: false
                    , area: ['630px', '520px']
                    , btn: ['确定', '取消']
                    , yes: function (e, idx) {
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-submit");
                        l.layui.form.on("submit(layuiadmin-app-form-submit)", function (res) {
                            var field = res.field; //获取提交的字段
                            admin.req({
                                type: 'post',
                                url: "/bs/book/saveOrUpdate",
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
            case 'batchdel':
                if (checkData.length === 0) {
                    return layer.msg('请选择数据');
                }
                var ids = [];
                for (var i = 0; i < checkData.length; i++) {
                    var row = checkData[i];
                    ids.push(row.id);
                }
                layer.confirm('确定删除吗？', function (index) {
                    admin.req({
                        type: "DELETE",
                        cache: false,
                        url: "/bs/book/batchDelete",
                        data: {ids: ids},
                        done: function (res) {
                            layer.msg('删除成功！', {
                                time: 2000 //20s后自动关闭
                            });
                            layer.close(index);
                            table.reload("LAY-app-content-list");
                        }
                    });
                });
                break;
            default:
                break;
        }
    });
    table.on("tool(LAY-app-content-list)", function (t) {
        var e = t.data;
        "del" === t.event ? layer.confirm("确定删除该图书？", function (cf) {
            admin.req({
                type: "DELETE",
                cache: false,
                url: "/bs/book/delete/" + e.id,
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
        }) : "edit" === t.event && layer.open({
            type: 2,
            title: "编辑图书信息",
            content: "/bs/book/form/",
            maxmin: !0,
            scrollbar: false,
            area: ['630px', '520px'],
            btn: ["确定", "取消"],
            yes: function (e, idx) {
                var l = window["layui-layer-iframe" + e],
                    a = idx.find("iframe").contents().find("#layuiadmin-app-form-edit");
                l.layui.form.on("submit(layuiadmin-app-form-edit)", function (res) {
                    var data = res.field;
                    admin.req({
                        type: 'post',
                        url: "/bs/book/saveOrUpdate",
                        data: data,
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
            },
            success: function (layero, index) {
                admin.req({
                    type: "GET",
                    cache: false,
                    url: "/bs/book/view/" + e.id,
                    done: function (res) {
                        var data = res.data;
                        var div = layero.find('iframe').contents().find('#layuiadmin-app-form-list');
                        for (var fd in data) {
                            div.find("input[name=\'" + fd + "\']").not("input:radio").val(data[fd]);
                        }
                        div.find("input[name='state'][value=\'"+data.state+"\']").prop("checked",true);

                        div.find("textarea[name='introduction']").text(data.introduction);
                        div.find("select[name='press']").val(data.press);
                        div.find("select[name='category']").val(data.category);

                    }
                });
            }
        })
    });
    t("bs/homeBookList", {});
});