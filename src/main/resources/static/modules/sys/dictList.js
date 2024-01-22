;layui.define(["table", "treeTable", "form"], function (t) {
    var e = layui.$, table = layui.table, treetable = layui.treeTable, form = layui.form, admin = layui.admin;
    var instb = treetable.render({
        elem: "#LAY-app-content-list",
        url: "/sys/dict",
        tree: {
            iconIndex: 2,
            isPidData: true,
            idName: 'id',
            pidName: 'parentId',
            onlyIconControl: true,
            arrowType: 'arrow2',
            getIcon: 'ew-tree-icon-style2',
            openName: 'open'
        },

        cols: [[{type: "checkbox", fixed: "left"},
            {field: "id", title: "ID", width: 50, sort: !0},
            {field: "dictName", title: "字典名称", minWidth: 150},
            {field: "dictValue", title: "字典代码", minWidth: 100},
            {field: "dictLevel", title: "字典类型", minWidth: 100},
            {field: "dictSort", title: "排序", minWidth: 50},
            {title: "操作", minWidth: 200, align: "center", fixed: "right", toolbar: "#table-content-list"}
        ]],
        page: false,
        text: "对不起，加载出现异常！",
        toolbar: "#toolbar-operation-btn"
    });
    treetable.on("toolbar(LAY-app-content-list)", function (t) {
        var checkData = t.data; //获取选中的数据
        switch (t.event) {
            case 'add':
                layer.open({
                    type: 2
                    , title: '添加字典'
                    , content: '/sys/dict/form/'
                    , maxmin: true
                    , scrollbar: false
                    , area: ['500px', '440px']
                    , btn: ['确定', '取消']
                    , yes: function (e, idx) {
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-submit");
                        l.layui.form.on("submit(layuiadmin-app-form-submit)", function (res) {
                            var field = res.field; //获取提交的字段
                            admin.req({
                                type: 'post',
                                url: "/sys/dict/saveOrUpdate",
                                data: field,
                                cache: false,
                                done: function (res) {
                                    layer.msg('保存成功！', {
                                        time: 1000 //20s后自动关闭
                                    }, function () {
                                        instb.reload();
                                        layer.close(e); //再执行关闭
                                    });
                                }
                            });
                        }), a.trigger("click");
                    }
                });
                break;
            case 'expand':
                instb.expandAll();
                break;
            case 'fold':
                instb.foldAll();
                break;
        }
    });
    treetable.on("tool(LAY-app-content-list)", function (t) {
        var e = t.data;
        switch (t.event) {
            case 'del':
                layer.confirm("确定删除该字典？", function (cf) {
                    admin.req({
                        type: "DELETE",
                        cache: false,
                        url: "/sys/dict/delete/" + e.id,
                        done: function (res) {
                            layer.msg('删除成功！', {
                                time: 1000 //20s后自动关闭
                            });
                            instb.reload();
                            layer.close(cf);
                        }
                    });
                });
                break;
            case 'edit':
                layer.open({
                    type: 2,
                    title: "编辑字典信息",
                    content: "/sys/dict/form/" + e.id,
                    maxmin: !0,
                    area: ['500px', '440px'],
                    btn: ["确定", "取消"],
                    yes: function (e, idx) {
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-edit");
                        l.layui.form.on("submit(layuiadmin-app-form-edit)", function (res) {
                            var data = res.field;
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            admin.req({
                                type: 'post',
                                url: "/sys/dict/saveOrUpdate",
                                data: data,
                                cache: false,
                                done: function (res) {
                                    layer.msg('保存成功！', {
                                        time: 1000 //20s后自动关闭
                                    }, function () {
                                        instb.reload();
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
                            url: "/sys/dict/view/" + e.id,
                            done: function (res) {
                                var data = res.data;
                                var div = layero.find('iframe').contents().find('#layuiadmin-app-form-list');
                                for (fd in data) {
                                    div.find("input[name=\'" + fd + "\']").not("input:radio").val(data[fd]);
                                }
                                var checked = (data.isShow == 0) ? "checked" : "";
                                div.find("input[name='isShow']").prop("checked", checked);
                                div.find("input[name='dictType'][value=\'" + data.dictType + "\']").prop("checked", true);
                                div.find("select[name='parentId']").val(e.parentId);

                            }
                        });
                    }
                });
                break;
            case 'addChild':
                layer.open({
                    type: 2
                    , title: '添加子字典'
                    , content: '/sys/dict/form/' + e.id
                    , maxmin: true
                    , scrollbar: false
                    , area: ['500px', '440px']
                    , btn: ['确定', '取消']
                    , yes: function (e, idx) {

                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-submit");
                        l.layui.form.on("submit(layuiadmin-app-form-submit)", function (res) {
                            var field = res.field; //获取提交的字段
                            admin.req({
                                type: 'post',
                                url: "/sys/dict/saveOrUpdate",
                                data: field,
                                cache: false,
                                done: function (res) {
                                    layer.msg('保存成功！', {
                                        time: 1000 //20s后自动关闭
                                    }, function () {
                                        instb.reload();
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
                            url: "/sys/dict/view/" + e.id,
                            done: function (res) {
                                var data = res.data;
                                var div = layero.find('iframe').contents().find('#layuiadmin-app-form-list');
                                // for (fd in data) {
                                //     div.find("input[name=\'" + fd + "\']").not("input:radio").val(data[fd]);
                                // }
                                div.find("select[name='parentId']").val(e.id);

                            }
                        });
                    }
                });
                break;
        }
    });
    t("sys/dictList", {});
    // form.on('submit(LAY-app-contlist-search)', function(data){
    //     var field = data.field;
    //     instb.filterData(field.keyword);
    // });
});