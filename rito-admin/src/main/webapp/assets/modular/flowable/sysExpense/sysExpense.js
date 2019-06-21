/**
 * 报销管理-报销表管理初始化
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
    var sysExpense = {
        tableId: "sysExpenseTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    sysExpense.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '报销金额', field: 'money', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'descc', visible: true, align: 'center', valign: 'middle'},
            {title: '流程Id', field: 'processId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'updateUser', visible: true, align: 'center', valign: 'middle'},
            {title: '删除标记', field: 'deleteFlag', visible: true, align: 'center', valign: 'middle'},
            {
                title: '审核状态',
                field: 'state',
                visible: true,
                align: 'center',
                valign: 'middle'
            },
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    sysExpense.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(sysExpense.tableId, {where: queryData});
    };

    /**
     * 弹出添加报销管理-报销表
     */
    sysExpense.openAddsysExpense = function () {
        window.location.href = Feng.ctxPath + '/sysExpense/sysExpense_add';

    };


    // /**
    //  * 点击编辑报销管理-报销表
    //  *
    //  * @param data 点击按钮时候的行数据
    //  */
    // sysExpense.onEditsysExpense = function (data) {
    //
    //     window.location.href = Feng.ctxPath + '/sysExpense/sysExpense_update?sysExpenseId=' + data.id;
    //
    // };
    /**
     * 流程详情
     */
    sysExpense.findRecord = function (data) {
        var index = layer.open({
            type: 2,
            title: '流程详情',
            area: ['1000px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sysExpense/sysExpense_update/' + data.id
        });
        this.layerIndex = index;
    };

    /**
     * 点击删除报销管理-报销表
     *
     * @param data 点击按钮时候的行数据
     */
    sysExpense.onDeletesysExpense = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sysExpense/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(sysExpense.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("sysExpenseId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除报销管理-报销表 " + data.id + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + sysExpense.tableId,
        url: Feng.ctxPath + '/sysExpense/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: sysExpense.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        sysExpense.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        sysExpense.openAddsysExpense();
    });

    // 提交按钮点击事件
    $('#btnSubmit').click(function () {
        var operation = function () {
            var checkRows = table.checkStatus(sysExpense.tableId);
            if (checkRows.data.length != 1) {
                Feng.error("请选择要提交的数据");
            } else {
                var taskId = checkRows.data[0].processId;
                var ajax = new $ax(Feng.ctxPath + "/process/pass", function (taskId) {
                    Feng.success("提交成功!");
                    table.reload(sysExpense.tableId);
                }, function (data) {
                    Feng.error("提交失败!" + data.responseJSON.message + "!");
                });
                ajax.set("taskId", taskId);
                ajax.start();
            }
        };
        Feng.confirm("是否提交当前选择数据 ?", operation);
    });
    // 通过按钮点击事件
    $('#btnAdopt').click(function () {
        var operation = function () {
            var checkRows = table.checkStatus(sysExpense.tableId);
            if (checkRows.data.length != 1) {
                Feng.error("请选择要通过的数据");
            } else {
                var taskId = checkRows.data[0].processId;
                var ajax = new $ax(Feng.ctxPath + "/process/pass", function (taskId) {
                    Feng.success("通过成功!");
                    table.reload(sysExpense.tableId);
                }, function (data) {
                    Feng.error("通过失败!" + data.responseJSON.message + "!");
                });
                ajax.set("taskId", taskId);
                ajax.start();
            }
        };
        Feng.confirm("是否通过当前选择数据 ?", operation);
    });
    // 驳回按钮点击事件
    $('#btnReject').click(function () {
        var operation = function () {
            var checkRows = table.checkStatus(sysExpense.tableId);
            if (checkRows.data.length != 1) {
                Feng.error("请选择要驳回的数据");
            } else {
                var taskId = checkRows.data[0].processId;
                var ajax = new $ax(Feng.ctxPath + "/process/unPass", function (taskId) {
                    Feng.success("驳回成功!");
                    table.reload(sysExpense.tableId);
                }, function (data) {
                    Feng.error("驳回失败!" + data.responseJSON.message + "!");
                });
                ajax.set("taskId", taskId);
                ajax.start();
            }
        };
        Feng.confirm("是否驳回当前选择数据 ?", operation);
    });

    // 工具条点击事件
    table.on('tool(' + sysExpense.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            sysExpense.findRecord(data);
        } else if (layEvent === 'delete') {
            sysExpense.onDeletesysExpense(data);
        }
    });
});
