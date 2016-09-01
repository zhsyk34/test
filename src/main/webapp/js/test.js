$(function () {
    $("#accordion").on("click", "li", function (e) {
        e.preventDefault();

        var a = $(this).children("a:first");
        var title = a.text();

        var tabs = $("#tabs");
        var exist = tabs.tabs("exists", title);

        if (exist) {
            tabs.tabs("select", title);
        } else {
            var frame = "<iframe src='" + a.attr("href") + "'/>";
            tabs.tabs("add", {
                title: title,
                content: frame,
                closable: true
            });
        }
    });

    $("#mod").on("click", "a", function (e) {
        e.preventDefault();
        $("#original,#password,#confirm").textbox("clear");
        $("#editor").dialog("open");
    });

    $("#editor").dialog({
        width: 360,
        height: 260,
        closed: true,
        closable: true,
        title: "密码修改",
        buttons: [{
            text: "确定",
            iconCls: "icon-ok",
            handler: function () {
                var valid = $("#form").form("validate");
                if (!valid) {
                    $.messager.alert({
                        title: $.message.warn,
                        msg: "数据错误"
                    });
                    return;
                }
                var id = parseInt($("#id").val()) || 0;
                console.log(id);
                if (id === 0) {
                    redirect();
                    return;
                }

                var original = $("#original").textbox("getValue");
                var password = $("#password").textbox("getValue");
                var confirm = $("#confirm").textbox("getValue");
                if (password !== confirm) {
                    $.messager.alert({title: $.message.warn, msg: "两次输入的密码不一致"});
                    return false;
                }

                $.ajax({
                    url: "user/update",
                    sync: false,
                    data: {id: id, original: original, password: password},
                    success: function (data) {
                        data = (data || "error").toLowerCase();
                        switch (data) {
                            case "success":
                                $.messager.alert({title: $.message.prompt, msg: "修改成功"});
                                $("#editor").dialog("close");
                                break;
                            case "wrong":
                                $.messager.alert({title: $.message.prompt, msg: "原密码不正确"});
                                break;
                            case "error":
                                $.messager.alert({title: $.message.prompt, msg: "出错了"});
                                break;
                        }
                    }
                });
            }
        }, {
            text: $.message.cancel,
            iconCls: "icon-cancel",
            handler: function () {
                $("#editor").dialog("close");
            }
        }]
    });

    showTime();

    function showTime() {
        Date.prototype.format = function (pattern) {
            var info = {
                "y+": this.getFullYear(),
                "M+": this.getMonth() + 1,
                "d+": this.getDate(),
                "H+": this.getHours(),
                "m+": this.getMinutes(),
                "s+": this.getSeconds()
            };
            for (var key in info) {
                var r = eval("/" + key + "/").exec(pattern);
                r && (pattern = pattern.replace(r[0], ("0" + info[key]).slice(-r[0].length)));
            }
            return pattern;
        }
        date();
        setInterval(date, 1000);
        function date() {
            var current = new Date();

            var week = " 星期" + "一二三四五六七".charAt(current.getDay());
            var date = current.format("yyyy-MM-dd HH:mm:ss");
            var result = date.slice(0, 11) + week + date.slice(-9);
            $("#date").html(result);
        }
    }
});