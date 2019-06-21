/**
 * 任务管理日志管理初始化
 */

layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--消息管理
     */
    var sysTaskLog = {
        tableId: "sysTaskLogTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    sysTaskLog.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '任务名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '执行时间', field: 'execAt', visible: true, align: 'center', valign: 'middle'},
            {title: '执行结果（成功:1、失败:0)', field: 'execSuccess', visible: true, align: 'center', valign: 'middle'},
            {title: '抛出异常', field: 'jobException', visible: true, align: 'center', valign: 'middle'},
        ]];
    };

    /**
     * 点击查询按钮
     */
    sysTaskLog.search = function () {
        var queryData = {};
        queryData['taskId'] = $("#taskId").val();
        table.reload(sysTaskLog.tableId, {where: queryData});
    };

    /**
     * 弹出添加任务管理日志
     */
    sysTaskLog.openAddsysTaskLog = function () {
        window.location.href = Feng.ctxPath + '/sysTaskLog/sysTaskLog_add';

    };

    // 关闭页面
    $('#btnBack').click(function () {
        window.location.href = Feng.ctxPath + "/sysTask";
    });
    /**
     * 点击编辑任务管理日志
     *
     * @param data 点击按钮时候的行数据
     */
    sysTaskLog.onEditsysTaskLog = function (data) {

        window.location.href = Feng.ctxPath + '/sysTaskLog/sysTaskLog_update?sysTaskLogId=' + data.id;

    };


    /**
     * 点击删除任务管理日志
     *
     * @param data 点击按钮时候的行数据
     */
    sysTaskLog.onDeletesysTaskLog = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sysTaskLog/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(sysTaskLog.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("sysTaskLogId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除任务管理日志 " + data.name + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + sysTaskLog.tableId,
        url: Feng.ctxPath + '/sysTaskLog/list?taskId=' + $("#taskId").val(),
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: sysTaskLog.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        sysTaskLog.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        sysTaskLog.openAddsysTaskLog();
    });

    // 工具条点击事件
    table.on('tool(' + sysTaskLog.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            sysTaskLog.onEditsysTaskLog(data);
        } else if (layEvent === 'delete') {
            sysTaskLog.onDeletesysTaskLog(data);
        }
    });
});
