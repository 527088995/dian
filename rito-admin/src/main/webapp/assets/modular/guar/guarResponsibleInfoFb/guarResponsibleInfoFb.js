/**
 * 相关还款责任人流水表管理初始化
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
    var guarResponsibleInfoFb = {
        tableId: "guarResponsibleInfoFbTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    guarResponsibleInfoFb.initColumn = function () {
        return [[
            {type: 'checkbox'},
            { type: 'numbers', title: '序号', width: 50 },
                        {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
                        {title: '企业担保账户信息ID', field: 'guarAcctId', visible: true, align: 'center', valign: 'middle'},
                        {title: '身份类别', field: 'infoIdType', visible: true, align: 'center', valign: 'middle'},
                        {title: '责任人名称', field: 'arlpName', visible: true, align: 'center', valign: 'middle'},
                        {title: '责任人身份标识类型', field: 'arlpCertType', visible: true, align: 'center', valign: 'middle'},
                        {title: '责任人身份标识号码', field: 'arlpCertNum', visible: true, align: 'center', valign: 'middle'},
                        {title: '还款责任人类型', field: 'arlpType', visible: true, align: 'center', valign: 'middle'},
                        {title: '还款责任金额', field: 'arlpAmt', visible: true, align: 'center', valign: 'middle'},
                        {title: '联保标志', field: 'wartySign', visible: true, align: 'center', valign: 'middle'},
                        {title: '保证合同编号', field: 'maxGuarMcc', visible: true, align: 'center', valign: 'middle'},
                        {title: '创建人用户主键ID', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
                        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
                        {title: '最后修改人员的用户ID', field: 'updateUser', visible: true, align: 'center', valign: 'middle'},
                        {title: '最后修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
                        {title: '删除标志', field: 'deleteFlag', visible: true, align: 'center', valign: 'middle'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    guarResponsibleInfoFb.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(guarResponsibleInfoFb.tableId, {where: queryData});
    };

    /**
     * 弹出添加相关还款责任人流水表
     */
    guarResponsibleInfoFb.openAddguarResponsibleInfoFb = function () {
        window.location.href = Feng.ctxPath + '/guarResponsibleInfoFb/guarResponsibleInfoFb_add';

    };


    /**
     * 点击编辑相关还款责任人流水表
     *
     * @param data 点击按钮时候的行数据
     */
    guarResponsibleInfoFb.onEditguarResponsibleInfoFb = function (data) {

        window.location.href = Feng.ctxPath + '/guarResponsibleInfoFb/guarResponsibleInfoFb_update?guarResponsibleInfoFbId=' + data.id;

    };


    /**
     * 点击删除相关还款责任人流水表
     *
     * @param data 点击按钮时候的行数据
     */
    guarResponsibleInfoFb.onDeleteguarResponsibleInfoFb = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/guarResponsibleInfoFb/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(guarResponsibleInfoFb.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("guarResponsibleInfoFbId", data.guarResponsibleInfoFbId);
            ajax.start();
        };
        Feng.confirm("是否删除相关还款责任人流水表 " + data.title + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + guarResponsibleInfoFb.tableId,
        url: Feng.ctxPath + '/guarResponsibleInfoFb/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: guarResponsibleInfoFb.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        guarResponsibleInfoFb.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        guarResponsibleInfoFb.openAddguarResponsibleInfoFb();
    });

    // 工具条点击事件
    table.on('tool(' + guarResponsibleInfoFb.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            guarResponsibleInfoFb.onEditguarResponsibleInfoFb(data);
        } else if (layEvent === 'delete') {
            guarResponsibleInfoFb.onDeleteguarResponsibleInfoFb(data);
        }
    });
});
