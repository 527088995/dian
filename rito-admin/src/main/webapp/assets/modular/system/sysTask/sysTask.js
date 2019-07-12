/**
 * 任务管理管理初始化
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
    var sysTask = {
        tableId: "sysTaskTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    sysTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: '任务名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            // {title: '任务组', field: 'jobGroup', visible: true, align: 'center', valign: 'middle'},
            {title: '执行类', field: 'jobClass', visible: true, align: 'center', valign: 'middle'},
            {title: '定时规则', field: 'cron', visible: true, align: 'center', valign: 'middle'},
            {title: '任务说明', field: 'note', visible: true, align: 'center', valign: 'middle'},
            {title: '执行时间', field: 'execAt', visible: true, align: 'center', valign: 'middle'},
            {title: '执行结果', field: 'execResult', visible: true, align: 'center', valign: 'middle'},
            {title: '执行参数', field: 'data', visible: true, align: 'center', valign: 'middle'},
            {title: '是否禁用', field: 'disabled', visible: true, templet: viewDisabled, align: 'center', valign: 'middle'},
            {title: '是否允许并发', field: 'concurrent', visible: true, align: 'center', valign: 'middle'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    //格式化单据状态
    function viewDisabled(d) {
        return Feng.getDictName("TASK_DISABLED", d.disabled);
    }

    /**
     * 点击查询按钮
     */
    sysTask.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(sysTask.tableId, {where: queryData});
    };

    /**
     * 弹出添加任务管理
     */
    sysTask.openAddsysTask = function () {
        window.location.href = Feng.ctxPath + '/sysTask/sysTask_add';

    };


    /**
     * 点击编辑任务管理
     *
     * @param data 点击按钮时候的行数据
     */
    sysTask.onEditsysTask = function (data) {

        window.location.href = Feng.ctxPath + '/sysTask/sysTask_update?sysTaskId=' + data.id;

    };

    /**
     *
     * @param data 查看日志
     */
    sysTask.viewLog = function (data) {
        window.location.href = Feng.ctxPath + '/sysTaskLog/viewLog/' + data.id;

    };


    /**
     * 点击删除任务管理
     *
     * @param data 点击按钮时候的行数据
     */
    sysTask.onDeletesysTask = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sysTask/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(sysTask.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("sysTaskId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除任务管理 " + data.name + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + sysTask.tableId,
        url: Feng.ctxPath + '/sysTask/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: sysTask.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        sysTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        sysTask.openAddsysTask();
    });

    // 工具条点击事件
    table.on('tool(' + sysTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            sysTask.onEditsysTask(data);
        } else if (layEvent === 'delete') {
            sysTask.onDeletesysTask(data);
        } else if (layEvent === 'viewLog') {
            sysTask.viewLog(data);
        }
    });
});
