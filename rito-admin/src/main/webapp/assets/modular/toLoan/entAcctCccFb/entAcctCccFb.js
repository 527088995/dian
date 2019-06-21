/**
 * 企业借贷-抵质押物信息流水表管理初始化
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
    var entAcctCccFb = {
        tableId: "entAcctCccFbTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    entAcctCccFb.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: '借款账户ID', field: 'entAcctId', visible: true, align: 'center', valign: 'middle'},
            {title: '抵质押合同标识码', field: 'ccc', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'updateUser', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'deleteFlag', visible: true, align: 'center', valign: 'middle'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    entAcctCccFb.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(entAcctCccFb.tableId, {where: queryData});
    };

    /**
     * 弹出添加企业借贷-抵质押物信息流水表
     */
    entAcctCccFb.openAddentAcctCccFb = function () {
        window.location.href = Feng.ctxPath + '/entAcctCccFb/entAcctCccFb_add';

    };


    /**
     * 点击编辑企业借贷-抵质押物信息流水表
     *
     * @param data 点击按钮时候的行数据
     */
    entAcctCccFb.onEditentAcctCccFb = function (data) {

        window.location.href = Feng.ctxPath + '/entAcctCccFb/entAcctCccFb_update?entAcctCccFbId=' + data.id;

    };


    /**
     * 点击删除企业借贷-抵质押物信息流水表
     *
     * @param data 点击按钮时候的行数据
     */
    entAcctCccFb.onDeleteentAcctCccFb = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/entAcctCccFb/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(entAcctCccFb.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("entAcctCccFbId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除企业借贷-抵质押物信息流水表 " + data.entAcctId + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + entAcctCccFb.tableId,
        url: Feng.ctxPath + '/entAcctCccFb/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: entAcctCccFb.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        entAcctCccFb.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        entAcctCccFb.openAddentAcctCccFb();
    });

    // 工具条点击事件
    table.on('tool(' + entAcctCccFb.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            entAcctCccFb.onEditentAcctCccFb(data);
        } else if (layEvent === 'delete') {
            entAcctCccFb.onDeleteentAcctCccFb(data);
        }
    });
});
