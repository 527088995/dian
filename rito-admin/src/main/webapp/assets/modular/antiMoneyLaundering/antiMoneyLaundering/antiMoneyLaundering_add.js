layui.use(['layer', 'form', 'admin', 'ax', 'laytpl'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    // 让当前iframe弹层高度适应
    //admin.iframeAuto();

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/antiMoneyLaundering/add", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            // admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
            //window.LiveReload.href= Feng.ctxPath + "/antiMoneyLaundering";
            window.location.href = Feng.ctxPath + "/antiMoneyLaundering";


        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + "/antiMoneyLaundering";
    });
});

layui.use('laydate', function(){
    var laydate = layui.laydate;

    //日期时间选择器
    laydate.render({
        elem: '#stcb_start'
        ,type: 'datetime'
    });

    laydate.render({
        elem: '#stcb_stop'
        ,type: 'datetime'
    });
});

