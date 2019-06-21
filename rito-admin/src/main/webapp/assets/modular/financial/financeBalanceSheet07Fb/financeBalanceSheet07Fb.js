/**
 * 财务报表-资产负债07流水表管理初始化
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
    var financeBalanceSheet07Fb = {
        tableId: "financeBalanceSheet07FbTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    financeBalanceSheet07Fb.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '企业名称', field: 'entName', visible: true, align: 'center', valign: 'middle'},
            {title: '企业身份标识类型', field: 'entCertType', visible: true, align: 'center', valign: 'middle'},
            {title: '企业身份标识号码', field: 'entCertNum', visible: true, align: 'center', valign: 'middle'},
            {title: '信息报告日期', field: 'rptDate', visible: true, align: 'center', valign: 'middle'},
            {title: '报表年份', field: 'sheetYear', visible: true, align: 'center', valign: 'middle'},
            {title: '报表类型', field: 'sheetType', visible: true, align: 'center', valign: 'middle'},
            {title: '报表类型细分', field: 'sheetTypeDivide', visible: true, align: 'center', valign: 'middle'},
            {title: '审计事务所名称', field: 'auditFirmName', visible: true, align: 'center', valign: 'middle'},
            {title: '审计人员名称', field: 'auditorName', visible: true, align: 'center', valign: 'middle'},
            {title: '审计时间', field: 'auditTime', visible: true, align: 'center', valign: 'middle'},
            {title: '客户资料维护机构代码', field: 'cimoc', visible: true, align: 'center', valign: 'middle'},
            {title: '报告时点说明代码', field: 'rptDateCode', visible: true, align: 'center', valign: 'middle'},
            {title: '货币资金', field: 'currencyFunds', visible: true, align: 'center', valign: 'middle'},
            {title: '交易性金融资产', field: 'financialAssetsHeldForTrad', visible: true, align: 'center', valign: 'middle'},
            {title: '应收票据', field: 'notesReceivable', visible: true, align: 'center', valign: 'middle'},
            {title: '应收账款', field: 'accountsReceivable', visible: true, align: 'center', valign: 'middle'},
            {title: '预付账款', field: 'prepayments', visible: true, align: 'center', valign: 'middle'},
            {title: '应收利息', field: 'interestReceivable', visible: true, align: 'center', valign: 'middle'},
            {title: '应收股利', field: 'dividendsReceivable', visible: true, align: 'center', valign: 'middle'},
            {title: '其他应收款', field: 'otherReceivables', visible: true, align: 'center', valign: 'middle'},
            {title: '存货', field: 'inventories', visible: true, align: 'center', valign: 'middle'},
            {title: '一年内到期的非流动资产', field: 'cponca', visible: true, align: 'center', valign: 'middle'},
            {title: '其他流动资产', field: 'otherCurrentAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '流动资产合计', field: 'totalCurrentAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '可供出售的金融资产', field: 'faafs', visible: true, align: 'center', valign: 'middle'},
            {title: '持有至到期投资', field: 'heldToMaturityInvestments', visible: true, align: 'center', valign: 'middle'},
            {title: '长期股权投资', field: 'longTermEquityinvestment', visible: true, align: 'center', valign: 'middle'},
            {title: '长期应收款', field: 'longTermReceivables', visible: true, align: 'center', valign: 'middle'},
            {title: '投资性房地产', field: 'investmentProperties', visible: true, align: 'center', valign: 'middle'},
            {title: '固定资产', field: 'fixedAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '在建工程', field: 'constructionInProgress', visible: true, align: 'center', valign: 'middle'},
            {title: '工程物资', field: 'constructionMaterials', visible: true, align: 'center', valign: 'middle'},
            {title: '固定资产清理', field: 'fapfd', visible: true, align: 'center', valign: 'middle'},
            {title: '生产性生物资产', field: 'nonCurrentBiologicalAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '油气资产', field: 'oilAndGasAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '无形资产', field: 'intangibleAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '开发支出', field: 'developmentDisbursements', visible: true, align: 'center', valign: 'middle'},
            {title: '商誉', field: 'goodwill', visible: true, align: 'center', valign: 'middle'},
            {title: '长期待摊费用', field: 'longTermDeferredExpenses', visible: true, align: 'center', valign: 'middle'},
            {title: '递延所得税资产', field: 'deferredTaxAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '其他非流动资产', field: 'otherNonCurrentAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '非流动资产总计', field: 'totalNonCurrentAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '资产总计', field: 'totalAssets', visible: true, align: 'center', valign: 'middle'},
            {title: '短期借款', field: 'shortTermBorrowings', visible: true, align: 'center', valign: 'middle'},
            {title: '交易性金融负债', field: 'flhft', visible: true, align: 'center', valign: 'middle'},
            {title: '应付票据', field: 'notesPayable', visible: true, align: 'center', valign: 'middle'},
            {title: '应付账款', field: 'accountsPayable', visible: true, align: 'center', valign: 'middle'},
            {title: '预收账款', field: 'receiptsInadvance', visible: true, align: 'center', valign: 'middle'},
            {title: '应付利息', field: 'interestPayable', visible: true, align: 'center', valign: 'middle'},
            {title: '应付职工薪酬', field: 'employeeBenefitsPayable', visible: true, align: 'center', valign: 'middle'},
            {title: '应交税费', field: 'taxsPayable', visible: true, align: 'center', valign: 'middle'},
            {title: '应付股利', field: 'dividendsPayable', visible: true, align: 'center', valign: 'middle'},
            {title: '其他应付款', field: 'otherPayables', visible: true, align: 'center', valign: 'middle'},
            {title: '一年内到期的非流动负债', field: 'cpoltl', visible: true, align: 'center', valign: 'middle'},
            {title: '其他流动负债', field: 'otherCurrentLiabilities', visible: true, align: 'center', valign: 'middle'},
            {title: '流动负债合计', field: 'totalCurrentLiabilities', visible: true, align: 'center', valign: 'middle'},
            {title: '长期借款', field: 'longTermBorrowings', visible: true, align: 'center', valign: 'middle'},
            {title: '应付债券', field: 'bondsPayables', visible: true, align: 'center', valign: 'middle'},
            {title: '长期应付款', field: 'longTermPayables', visible: true, align: 'center', valign: 'middle'},
            {title: '专项应付款', field: 'grantsPayable', visible: true, align: 'center', valign: 'middle'},
            {title: '预计负债', field: 'provisions', visible: true, align: 'center', valign: 'middle'},
            {title: '递延所得税负债', field: 'deferredTaxliabilities', visible: true, align: 'center', valign: 'middle'},
            {title: '其他非流动负债', field: 'otherNonCurrentLiabilities', visible: true, align: 'center', valign: 'middle'},
            {title: '非流动负债合计', field: 'totalNonCurrentLiabilities', visible: true, align: 'center', valign: 'middle'},
            {title: '负债合计', field: 'totalLiabilities', visible: true, align: 'center', valign: 'middle'},
            {title: '实收资本（或股本）', field: 'pcosc', visible: true, align: 'center', valign: 'middle'},
            {title: '资本公积', field: 'capitalrRserve', visible: true, align: 'center', valign: 'middle'},
            {title: '减：库存股', field: 'lessTreasuryStocks', visible: true, align: 'center', valign: 'middle'},
            {title: '盈余公积', field: 'surplusReserve', visible: true, align: 'center', valign: 'middle'},
            {title: '未分配利润', field: 'unappropriatedProfit', visible: true, align: 'center', valign: 'middle'},
            {title: '所有者权益合计', field: 'totalEquity', visible: true, align: 'center', valign: 'middle'},
            {title: '负债和所有者权益合计', field: 'totalEquityAndLiabilities', visible: true, align: 'center', valign: 'middle'},
            {title: '信息记录状态 ', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '删除标志', field: 'deleteFlag', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人用户主键ID', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
            {title: '最后修改人员的用户ID', field: 'updateUser', visible: true, align: 'center', valign: 'middle'},
            {title: '最后修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    financeBalanceSheet07Fb.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(financeBalanceSheet07Fb.tableId, {where: queryData});
    };

    /**
     * 弹出添加财务报表-资产负债07流水表
     */
    financeBalanceSheet07Fb.openAddfinanceBalanceSheet07Fb = function () {
        window.location.href = Feng.ctxPath + '/financeBalanceSheet07Fb/financeBalanceSheet07Fb_add';

    };


    /**
     * 点击编辑财务报表-资产负债07流水表
     *
     * @param data 点击按钮时候的行数据
     */
    financeBalanceSheet07Fb.onEditfinanceBalanceSheet07Fb = function (data) {

        window.location.href = Feng.ctxPath + '/financeBalanceSheet07Fb/financeBalanceSheet07Fb_update?financeBalanceSheet07FbId=' + data.id;

    };


    /**
     * 点击删除财务报表-资产负债07流水表
     *
     * @param data 点击按钮时候的行数据
     */
    financeBalanceSheet07Fb.onDeletefinanceBalanceSheet07Fb = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/financeBalanceSheet07Fb/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(financeBalanceSheet07Fb.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("financeBalanceSheet07FbId", data.financeBalanceSheet07FbId);
            ajax.start();
        };
        Feng.confirm("是否删除财务报表-资产负债07流水表 " + data.title + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + financeBalanceSheet07Fb.tableId,
        url: Feng.ctxPath + '/financeBalanceSheet07Fb/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: financeBalanceSheet07Fb.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        financeBalanceSheet07Fb.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        financeBalanceSheet07Fb.openAddfinanceBalanceSheet07Fb();
    });

    // 工具条点击事件
    table.on('tool(' + financeBalanceSheet07Fb.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            financeBalanceSheet07Fb.onEditfinanceBalanceSheet07Fb(data);
        } else if (layEvent === 'delete') {
            financeBalanceSheet07Fb.onDeletefinanceBalanceSheet07Fb(data);
        }
    });
});
