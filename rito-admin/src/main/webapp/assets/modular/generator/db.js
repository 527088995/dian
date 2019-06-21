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
        window.location.href = Feng.ctxPath + "/generator/db";
    });

    $('#add_db').click(function () {
        window.location.href = Feng.ctxPath + "/generator/add";
    });

    table.render({
        elem: '#dbTable'
        , url: Feng.ctxPath + '/generator/list'
        , page: false
        , height: "full-158"
        , cols: [[
            {type: 'checkbox'}
            , {field: 'dbId', title: 'id'}
            , {field: 'dbName', title: '数据源名称'}
            , {field: 'jdbcDriver', title: 'jdbc的驱动类型'}
            , {field: 'userName', title: '数据库连接的账号'}
            , {field: 'password', title: '密码'}
            , {field: 'jdbcUrl', title: 'jdbc的url'}
            , {field: 'createTime', title: '创建时间'}
            , {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]]
    });

    // 工具条点击事件
    table.on('tool(dbTable)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'delete') {
            var operation = function () {
                var ajax = new $ax(Feng.ctxPath + "/generator/delete", function () {
                    Feng.success("删除成功!");
                    table.reload("dbTable");
                }, function (data) {
                    Feng.error("删除失败!");
                });
                ajax.set("dbId", data.dbId);
                ajax.start();
            };
            Feng.confirm("是否删除数据源 " + data.dbName + "?", operation);
        }
    });

});