/**
 * 企业借贷-特定交易信息流水表管理初始化
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
    var entAcctTransFb = {
        tableId: "entAcctTransFbTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    entAcctTransFb.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '借款账户ID', field: 'entAcctId', visible: true, align: 'center', valign: 'middle'},
            {title: '交易类型', field: 'chanTranType', visible: true, align: 'center', valign: 'middle'},
            {title: '交易日期', field: 'tranDate', visible: true, align: 'center', valign: 'middle'},
            {title: '交易金额', field: 'tranAmt', visible: true, align: 'center', valign: 'middle'},
            {title: '到期日期变更月数', field: 'dueTranMon', visible: true, align: 'center', valign: 'middle'},
            {title: '交易明细信息', field: 'detInfo', visible: true, align: 'center', valign: 'middle'},
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
    entAcctTransFb.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(entAcctTransFb.tableId, {where: queryData});
    };

    /**
     * 弹出添加企业借贷-特定交易信息流水表
     */
    entAcctTransFb.openAddentAcctTransFb = function () {
        window.location.href = Feng.ctxPath + '/entAcctTransFb/entAcctTransFb_add';

    };


    /**
     * 点击编辑企业借贷-特定交易信息流水表
     *
     * @param data 点击按钮时候的行数据
     */
    entAcctTransFb.onEditentAcctTransFb = function (data) {

        window.location.href = Feng.ctxPath + '/entAcctTransFb/entAcctTransFb_update?entAcctTransFbId=' + data.id;

    };


    /**
     * 点击删除企业借贷-特定交易信息流水表
     *
     * @param data 点击按钮时候的行数据
     */
    entAcctTransFb.onDeleteentAcctTransFb = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/entAcctTransFb/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(entAcctTransFb.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("entAcctTransFbId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除企业借贷-特定交易信息流水表 " + data.id + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + entAcctTransFb.tableId,
        url: Feng.ctxPath + '/entAcctTransFb/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: entAcctTransFb.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        entAcctTransFb.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        entAcctTransFb.openAddentAcctTransFb();
    });

    // 工具条点击事件
    table.on('tool(' + entAcctTransFb.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            entAcctTransFb.onEditentAcctTransFb(data);
        } else if (layEvent === 'delete') {
            entAcctTransFb.onDeleteentAcctTransFb(data);
        }
    });
});
