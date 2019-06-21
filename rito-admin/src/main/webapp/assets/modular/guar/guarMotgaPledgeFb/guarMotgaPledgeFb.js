/**
 * 抵质押合同流水表管理初始化
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
    var guarMotgaPledgeFb = {
        tableId: "guarMotgaPledgeFbTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    guarMotgaPledgeFb.initColumn = function () {
        return [[
            {type: 'checkbox'},
            { type: 'numbers', title: '序号', width: 50 },
                        {title: '企业担保账户信息ID', field: 'guarAcctId', visible: true, align: 'center', valign: 'middle'},
                        {title: '合同标识码', field: 'ccc', visible: true, align: 'center', valign: 'middle'},
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
    guarMotgaPledgeFb.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(guarMotgaPledgeFb.tableId, {where: queryData});
    };

    /**
     * 弹出添加抵质押合同流水表
     */
    guarMotgaPledgeFb.openAddguarMotgaPledgeFb = function () {
        window.location.href = Feng.ctxPath + '/guarMotgaPledgeFb/guarMotgaPledgeFb_add';

    };


    /**
     * 点击编辑抵质押合同流水表
     *
     * @param data 点击按钮时候的行数据
     */
    guarMotgaPledgeFb.onEditguarMotgaPledgeFb = function (data) {

        window.location.href = Feng.ctxPath + '/guarMotgaPledgeFb/guarMotgaPledgeFb_update?guarMotgaPledgeFbId=' + data.id;

    };


    /**
     * 点击删除抵质押合同流水表
     *
     * @param data 点击按钮时候的行数据
     */
    guarMotgaPledgeFb.onDeleteguarMotgaPledgeFb = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/guarMotgaPledgeFb/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(guarMotgaPledgeFb.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("guarMotgaPledgeFbId", data.guarMotgaPledgeFbId);
            ajax.start();
        };
        Feng.confirm("是否删除抵质押合同流水表 " + data.title + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + guarMotgaPledgeFb.tableId,
        url: Feng.ctxPath + '/guarMotgaPledgeFb/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: guarMotgaPledgeFb.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        guarMotgaPledgeFb.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        guarMotgaPledgeFb.openAddguarMotgaPledgeFb();
    });

    // 工具条点击事件
    table.on('tool(' + guarMotgaPledgeFb.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            guarMotgaPledgeFb.onEditguarMotgaPledgeFb(data);
        } else if (layEvent === 'delete') {
            guarMotgaPledgeFb.onDeleteguarMotgaPledgeFb(data);
        }
    });
});
