/**
 * 利润及利润分配07流水表管理初始化
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
    var financeIncomeStatement07Fb = {
        tableId: "financeIncomeStatement07FbTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    financeIncomeStatement07Fb.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '企业编号', field: 'entName', visible: true, align: 'center', valign: 'middle'},
            {title: '信息报告日期', field: 'entCertType', visible: true, align: 'center', valign: 'middle'},
            {title: '企业身份标识号码', field: 'entCertNum', visible: true, align: 'center', valign: 'middle'},
            {title: '信息报告日期', field: 'rptDate', visible: true, align: 'center', valign: 'middle'},
            {title: '报表年份', field: 'sheetYear', visible: true, align: 'center', valign: 'middle'},
            {title: '报表类型', field: 'sheetType', visible: true, align: 'center', valign: 'middle'},
            {title: '报表类型细分', field: 'sheetTypeDivide', visible: true, align: 'center', valign: 'middle'},
            {title: '审计事务所名称', field: 'auditFirmName', visible: true, align: 'center', valign: 'middle'},
            {title: '审计人员名称', field: 'auditorName', visible: true, align: 'center', valign: 'middle'},
            {title: '审计时间', field: 'auditTime', visible: true, align: 'center', valign: 'middle'},
            {title: '客户资料维护机构代码', field: 'cimoc', visible: true, align: 'center', valign: 'middle'},
            {title: '报告时点说明代码', field: 'prtDateCode', visible: true, align: 'center', valign: 'middle'},
            {title: '营业收入', field: 'revenueOfSales', visible: true, align: 'center', valign: 'middle'},
            {title: '营业成本', field: 'costOfSales', visible: true, align: 'center', valign: 'middle'},
            {title: '营业税金及附加', field: 'businessAndOtherTaxes', visible: true, align: 'center', valign: 'middle'},
            {title: '销售费用', field: 'sellingExpenses', visible: true, align: 'center', valign: 'middle'},
            {title: '管理费用', field: 'generalAndAdministrativeExp', visible: true, align: 'center', valign: 'middle'},
            {title: '财务费用', field: 'financialExpense', visible: true, align: 'center', valign: 'middle'},
            {title: '资产减值损失', field: 'impairmentLossOfAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '公允价值变动净收益', field: 'polafcifv', visible: true, align: 'center', valign: 'middle'},
            {title: '投资净收益', field: 'investmentIncome', visible: true, align: 'center', valign: 'middle'},
            {title: '对联营企业和合营企业的投资收益', field: 'iifabace', visible: true, align: 'center', valign: 'middle'},
            {title: '营业利润', field: 'operatingProfits', visible: true, align: 'center', valign: 'middle'},
            {title: '营业外收入', field: 'nonOperatingIncome', visible: true, align: 'center', valign: 'middle'},
            {title: '营业外支出', field: 'nonOperatingExpenses', visible: true, align: 'center', valign: 'middle'},
            {title: '非流动资产损失', field: 'nonCurrentAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '利润总额', field: 'profitBeforeTax', visible: true, align: 'center', valign: 'middle'},
            {title: '所得税费用', field: 'incomeTaxExpense', visible: true, align: 'center', valign: 'middle'},
            {title: '净利润', field: 'netProfit', visible: true, align: 'center', valign: 'middle'},
            {title: '基本每股收益', field: 'basicEarningsPerShare', visible: true, align: 'center', valign: 'middle'},
            {title: '稀释每股收益', field: 'dilutedEarningsPerShare', visible: true, align: 'center', valign: 'middle'},
            {title: '信息记录状态 ', field: 'status', visible: true, align: 'center', valign: 'middle'},
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
    financeIncomeStatement07Fb.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(financeIncomeStatement07Fb.tableId, {where: queryData});
    };

    /**
     * 弹出添加利润及利润分配07流水表
     */
    financeIncomeStatement07Fb.openAddfinanceIncomeStatement07Fb = function () {
        window.location.href = Feng.ctxPath + '/financeIncomeStatement07Fb/financeIncomeStatement07Fb_add';

    };


    /**
     * 点击编辑利润及利润分配07流水表
     *
     * @param data 点击按钮时候的行数据
     */
    financeIncomeStatement07Fb.onEditfinanceIncomeStatement07Fb = function (data) {

        window.location.href = Feng.ctxPath + '/financeIncomeStatement07Fb/financeIncomeStatement07Fb_update?financeIncomeStatement07FbId=' + data.id;

    };


    /**
     * 点击删除利润及利润分配07流水表
     *
     * @param data 点击按钮时候的行数据
     */
    financeIncomeStatement07Fb.onDeletefinanceIncomeStatement07Fb = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/financeIncomeStatement07Fb/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(financeIncomeStatement07Fb.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("financeIncomeStatement07FbId", data.financeIncomeStatement07FbId);
            ajax.start();
        };
        Feng.confirm("是否删除利润及利润分配07流水表 " + data.title + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + financeIncomeStatement07Fb.tableId,
        url: Feng.ctxPath + '/financeIncomeStatement07Fb/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: financeIncomeStatement07Fb.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        financeIncomeStatement07Fb.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        financeIncomeStatement07Fb.openAddfinanceIncomeStatement07Fb();
    });

    // 工具条点击事件
    table.on('tool(' + financeIncomeStatement07Fb.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            financeIncomeStatement07Fb.onEditfinanceIncomeStatement07Fb(data);
        } else if (layEvent === 'delete') {
            financeIncomeStatement07Fb.onDeletefinanceIncomeStatement07Fb(data);
        }
    });
});
