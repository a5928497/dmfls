$(function () {
    $opBtn = $(".opBtn");
    $opBtn.click(function () {
        var opUrl = $(this).attr("opUrl");
        window.location.href = opUrl;
        return false;
    })
})