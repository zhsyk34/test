"use strict";
(function ($) {
    var view = function (param) {
        var defaults = {
            widget: {
                grid: "#data",
                editor: "#editor",
                form: "#form"
            },
            urls: {
                find: null,
                save: null,
                remove: null,
                exist: null
            },
            before: function () {
            },
            dialogOption: null,// 弹窗配置项
            dialogDeep:false,// 配置覆盖
            beforeOpenAdd: function () {// 弹出新增窗口前
                $("input[name=id],#id").val(0);
            },
            beforeOpenMod: null,// 弹出修改窗口前

            gridOption: null,// 表单配置项
            gridDeep:false,
            findParams: function () {// 查找条件
                return null;
            },
            existParam: function () {// 查询(是否存在)条件
                return null;
            },

            listen: function () {// 默认监听器
                $("#search").on("click", function () {
                    find();
                });
                $("#clear").on("click", function () {
                    $.crud.clear("#nav");
                });
            }
        };

        var options = $.extend(true, {}, defaults, param || {});

        var widget = options.widget, urls = options.urls, listen = options.listen;

        var $grid = $(widget.grid), $form = $(widget.form), $editor = $(widget.editor);

        typeof options.before === "function" && options.before();
        initGrid();
        initDialog();
        listen();

        function initGrid() {
            var gridBar = [{
                text: $.message.add,
                iconCls: "icon-add",
                handler: function () {
                    $form.form("clear");
                    typeof options.beforeOpenAdd === "function" && options.beforeOpenAdd();
                    $editor.dialog({title: $.message.add}).dialog("open");
                }
            }, {
                text: $.message.mod,
                iconCls: "icon-edit",
                handler: function () {
                    var rows = $grid.datagrid("getSelections");
                    if (rows.length === 0) {
                        $.messager.alert({title: $.message.prompt, msg: $.message.select});
                        return;
                    }
                    if (rows.length > 1) {
                        $.messager.alert({title: $.message.prompt, msg: $.message.assign});
                        return;
                    }
                    $form.form("load", rows[0]);
                    typeof options.beforeOpenMod === "function" && options.beforeOpenMod(rows[0]);// before
                    $editor.dialog({title: $.message.mod}).dialog("open");
                }
            }, {
                text: $.message.del,
                iconCls: "icon-remove",
                handler: function () {
                    var rows = $grid.datagrid("getSelections");
                    var ids = [];
                    if (rows.length > 0) {
                        rows.forEach(({id}) => ids.push(id));// TODO
                    }
                    $.crud.remove({url: urls.remove, ids: ids, callback: find});
                }
            }];
            $($grid).datagrid($.extend(!!options.gridDeep,{}, {toolbar: gridBar, url: urls.find}, options.gridOption || {}));
        }

        function find() {
            $grid.datagrid("reload", options.findParams());
        }

        function initDialog() {
            var dialogBar = [{
                text: $.message.sure,
                iconCls: "icon-ok",
                handler: function () {
                    $.crud.save({
                        form: $form,
                        url: urls.save,
                        before: urls.exist,
                        param: options.existParam(),
                        callback: function () {
                            find();
                            $editor.dialog("close");
                        }
                    });
                }
            }, {
                text: $.message.cancel,
                iconCls: "icon-cancel",
                handler: function () {
                    $form.form("clear");
                    $editor.dialog("close");
                }
            }];

            $editor.dialog($.extend({}, {buttons: dialogBar}, options.dialogOption || {}));
        }
    };

    $.extend({base: view});
})(jQuery);