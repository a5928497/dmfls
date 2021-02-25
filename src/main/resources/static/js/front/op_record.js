$(function () {
    $submitBtn = $("#submitBtn");
    $name = $("[name = 'name']");
    $email = $("[name = 'email']");

    $submitBtn.click(function () {
        //格式化数据
        var name = $name.val().toUpperCase();
        var email = emailFormat($email.val());
        $name.val(name);
        $email.val(email);
        $.ajax({
            type: "POST",   //提交的方法
            url:"/oprecord", //提交的地址
            data:$("#opRecordForm").serialize(),// 序列化表单值
            async: false,
            error: function (request) {
                alert("连接超时！");
            },
            success: function (data) {
                if (null != data && "" != data) {
                    alert(data);
                }else {
                    alert("发送失败！");
                }
            }
        })
        return false;
    });

    function emailFormat(email) {
        var translator = email.split("@");
        var email_new = translator[0] + "@" + translator[1].toLowerCase();
        return email_new;
    }
})