layui.use(['layer', 'ax', 'form', 'laydate', 'element', 'table'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;
    var element = layui.element;
    var table = layui.table;

    $('#code_gen').click(function () {
        window.location.href = Feng.ctxPath + "/generator";
    });

    $('#db_config').click(function () {
        window.location.href = Feng.ctxPath + "/generator/generator";
    });

    $('#add_db').click(function () {
        window.location.href = Feng.ctxPath + "/generator/add";
    });

    // 表单提交事件
    form.on('submit(submitDb)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/generator/addItem", function (data) {
            Feng.success("添加成功！");
            $("#resetBtn").click();
        }, function (data) {
            Feng.error("添加失败！");
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });
});
