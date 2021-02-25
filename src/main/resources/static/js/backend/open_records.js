$(function () {
    $confirmBtn = $("#confirm");

    $confirmBtn.click(function () {
        var url = $(this).attr("href");
        var is_ok = confirm("确认已开户吗？");
        //修改样式并移除确认按钮
        if (is_ok) {
            $.get(url,function (data) {
                if (null != data && "" != data) {
                    var oprecord = eval('(' + data + ')');
                    $("#is_open" + oprecord.id).text("已确认").css("color","red");
                    $confirmBtn.parent().remove();
                }
            })
        }
        return false;
    });
})