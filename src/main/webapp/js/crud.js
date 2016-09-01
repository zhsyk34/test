"use strict";
(function ($) {
    var crud = {
        clear: function (target) {
            $(target).find("input:not(:radio,:checkbox),textarea").val("");
            $(target).find(":radio:first").prop("checked", true);
            $(target).find(":checkbox").prop("checked", false);
            $(target).find("select option:first").prop("selected", true);
        },
        save: function (options) {
            var $form = options.form, url = options.url, callback = options.callback, before = options.before, param = options.param || null;

            $form.form("submit", {
                url: url,
                onSubmit: function () {
                    var flag = $form.form("validate");
                    if (!flag) {
                        $.messager.alert({title: $.message.warn, msg: "数据错误"});
                        return false;
                    }
                    if (before) {
                        var exist = true;
                        $.ajax({
                            url: before,
                            async: false,
                            type: "POST",
                            data: param,
                            success: function (data) {
                                exist = data;
                            }
                        });
                        if (exist) {
                            $.messager.alert({title: $.message.warn, msg: "数据已存在"});
                            return false;
                        }
                    }
                    $.messager.progress();
                },
                success: function (feedback) {
                    console.log(feedback);
                    $.messager.progress("close");
                    feedback = (feedback || "error").toLowerCase();

                    switch (feedback) {
                        case "create":
                            $.messager.alert({title: $.message.prompt, msg: "添加成功"});
                            break;
                        case "update":
                            $.messager.alert({title: $.message.prompt, msg: "修改成功"});
                            break;
                        case "exist":
                            $.messager.alert({title: $.message.prompt, msg: "数据已存在"});
                            break;
                        case "error":
                            $.messager.alert({title: $.message.prompt, msg: "出错了"});
                            return;
                    }
                    typeof callback === "function" && callback();
                }
            });
        },
        remove: function (options) {
            var url = options.url, ids = options.ids, callback = options.callback;

            typeof ids === "number" && (ids = [ids]);
            console.log(ids);
            if (ids.length === 0) {
                $.messager.alert({title: $.message.prompt, msg: "请选择数据"});
                return;
            }
            $.messager.confirm({
                title: $.message.warn, msg: "是否删除", fn: function (b) {
                    if (b) {
                        $.ajax({
                            url: url,
                            traditional: true,
                            async: false,
                            type: "POST",
                            data: {ids: ids},
                            success: function (feedback) {
                                feedback = (feedback || "error").toLowerCase();
                                switch (feedback) {
                                    case "relate":
                                        $.messager.alert({title: $.message.prompt, msg: "数据正被使用中"});
                                        return;
                                    case "delete":
                                        $.messager.alert({title: $.message.prompt, msg: "删除成功"});
                                        break;
                                    case "error":
                                        $.messager.alert({title: $.message.prompt, msg: "出错了"});
                                        break;
                                }
                                typeof callback === "function" && callback();
                            }
                        });
                    }
                }
            });
        }
    };

    $.extend({crud: crud});
})(jQuery);

