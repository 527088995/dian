layui.use(['layer', 'form', 'admin', 'laydate',  'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/financeBalanceSheet07Fb/add", function (data) {
            Feng.success("添加成功！");
            //关掉对话框
            admin.closeThisDialog();
            window.location.href = Feng.ctxPath + "/financeBalanceSheet07Fb";

        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
        return false;
    });

    // 渲染时间选择框
    laydate.render({
        elem: '#rptDate'
       // ,type: 'datetime'
    });
    // 渲染时间选择框
    laydate.render({
        elem: '#auditTime'
    });
    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + "/financeBalanceSheet07Fb";
    });
});