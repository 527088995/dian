layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/sysTaskLog/update", function (data) {
            Feng.success("修改成功！");


            //关掉对话框
            admin.closeThisDialog();
            //返回查询页
            window.location.href = Feng.ctxPath + "/sysTaskLog";
        }, function (data) {
            Feng.error("修改失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
        return false;
    });

    //返回按钮
    $("#closeDialog").click(function () {
        window.location.href = Feng.ctxPath + "/sysTaskLog";
    });
});