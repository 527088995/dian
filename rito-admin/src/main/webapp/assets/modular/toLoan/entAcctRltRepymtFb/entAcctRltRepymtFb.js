/**
 * 企业借贷-相关还款人信息流水表管理初始化
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
    var entAcctRltRepymtFb = {
        tableId: "entAcctRltRepymtFbTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    entAcctRltRepymtFb.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '借款账户ID', field: 'entAcctId', visible: true, align: 'center', valign: 'middle'},
            {title: '身份类别', field: 'arlpIdType', visible: true, align: 'center', valign: 'middle'},
            {title: '责任人名称', field: 'arlpName', visible: true, align: 'center', valign: 'middle'},
            {title: '责任人身份标识类型', field: 'arlpCertType', visible: true, align: 'center', valign: 'middle'},
            {title: '责任人身份标识号码', field: 'arlpCertNum', visible: true, align: 'center', valign: 'middle'},
            {title: '还款责任人类型', field: 'arlpType', visible: true, align: 'center', valign: 'middle'},
            {title: '还款责任金额', field: 'arlpAmt', visible: true, align: 'center', valign: 'middle'},
            {title: '联保标志', field: 'wartySign', visible: true, align: 'center', valign: 'middle'},
            {title: '保证合同编号', field: 'maxGuarMcc', visible: true, align: 'center', valign: 'middle'},
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
    entAcctRltRepymtFb.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(entAcctRltRepymtFb.tableId, {where: queryData});
    };

    /**
     * 弹出添加企业借贷-相关还款人信息流水表
     */
    entAcctRltRepymtFb.openAddentAcctRltRepymtFb = function () {
        window.location.href = Feng.ctxPath + '/entAcctRltRepymtFb/entAcctRltRepymtFb_add';

    };


    /**
     * 点击编辑企业借贷-相关还款人信息流水表
     *
     * @param data 点击按钮时候的行数据
     */
    entAcctRltRepymtFb.onEditentAcctRltRepymtFb = function (data) {
        window.location.href = Feng.ctxPath + '/entAcctRltRepymtFb/entAcctRltRepymtFb_update?entAcctRltRepymtFbId=' + data.id;
    };


    /**
     * 点击删除企业借贷-相关还款人信息流水表
     *
     * @param data 点击按钮时候的行数据
     */
    entAcctRltRepymtFb.onDeleteentAcctRltRepymtFb = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/entAcctRltRepymtFb/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(entAcctRltRepymtFb.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("entAcctRltRepymtFbId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除企业借贷-相关还款人信息流水表 " + data.id + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + entAcctRltRepymtFb.tableId,
        url: Feng.ctxPath + '/entAcctRltRepymtFb/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: entAcctRltRepymtFb.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        entAcctRltRepymtFb.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        entAcctRltRepymtFb.openAddentAcctRltRepymtFb();
    });

    // 工具条点击事件
    table.on('tool(' + entAcctRltRepymtFb.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            entAcctRltRepymtFb.onEditentAcctRltRepymtFb(data);
        } else if (layEvent === 'delete') {
            entAcctRltRepymtFb.onDeleteentAcctRltRepymtFb(data);
        }
    });
});
