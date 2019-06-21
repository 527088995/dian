/**
 * 财务报表-现金流量表信息07流水表管理初始化
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
    var financeCashFlows07Fb = {
        tableId: "financeCashFlows07FbTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    financeCashFlows07Fb.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
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
            {title: '销售商品和提供劳务收到的现金', field: 'crfsogoros', visible: true, align: 'center', valign: 'middle'},
            {title: '收到的税费返还', field: 'taxRefunds', visible: true, align: 'center', valign: 'middle'},
            {title: '收到其他与经营活动有关的现金', field: 'ocrrtoa', visible: true, align: 'center', valign: 'middle'},
            {title: '经营活动现金流入小计', field: 'tcifoa', visible: true, align: 'center', valign: 'middle'},
            {title: '购买商品、接受劳务支付的现金', field: 'cpfgas', visible: true, align: 'center', valign: 'middle'},
            {title: '支付给职工以及为职工支付的现金', field: 'cptaoboe', visible: true, align: 'center', valign: 'middle'},
            {title: '支付的各项税费', field: 'paymentsOfAllTypesOfTaxes', visible: true, align: 'center', valign: 'middle'},
            {title: '支付其他与经营活动有关的现金', field: 'ocpfoa', visible: true, align: 'center', valign: 'middle'},
            {title: '经营活动现金流出小计', field: 'tcofoa', visible: true, align: 'center', valign: 'middle'},
            {title: '经营活动产生的现金流量净额', field: 'ncfoa', visible: true, align: 'center', valign: 'middle'},
            {title: '收回投资所收到的现金', field: 'crfroi', visible: true, align: 'center', valign: 'middle'},
            {
                title: '取得投资收益所收到的现金',
                field: 'cashReceivedFromOnvestments',
                visible: true,
                align: 'center',
                valign: 'middle'
            },
            {
                title: '处置固定资产无形资产和其他长期资产所收回的现金净额',
                field: 'ncrfdofaaaolta',
                visible: true,
                align: 'center',
                valign: 'middle'
            },
            {title: '处置子公司及其他营业单位收到的现金净额', field: 'nciodosaobe', visible: true, align: 'center', valign: 'middle'},
            {title: '收到其他与投资活动有关的现金', field: 'crrtoia', visible: true, align: 'center', valign: 'middle'},
            {title: '投资活动现金流入小计', field: 'tcifia', visible: true, align: 'center', valign: 'middle'},
            {
                title: '购建固定资产无形资产和其他长期资产所支付的现金',
                field: 'cptafaiaaolta',
                visible: true,
                align: 'center',
                valign: 'middle'
            },
            {title: '投资所支付的现金', field: 'cashPaymentsForInvestments', visible: true, align: 'center', valign: 'middle'},
            {title: '取得子公司及其他营业单位支付的现金净额', field: 'ncooposaobu', visible: true, align: 'center', valign: 'middle'},
            {title: '支付其他与投资活动有关的现金', field: 'cprtoia', visible: true, align: 'center', valign: 'middle'},
            {title: '投资活动现金流出小计', field: 'subTotalOfCashOutflows', visible: true, align: 'center', valign: 'middle'},
            {title: '投资活动产生的现金流量净额', field: 'ncffia', visible: true, align: 'center', valign: 'middle'},
            {title: '吸收投资收到的现金', field: 'cashReceivedFromInvestors', visible: true, align: 'center', valign: 'middle'},
            {title: '取得借款收到的现金', field: 'cashFromBorrowings', visible: true, align: 'center', valign: 'middle'},
            {title: '收到其他与筹资活动有关的现金', field: 'ocrrtfa', visible: true, align: 'center', valign: 'middle'},
            {title: '筹资活动现金流入小计', field: 'tciffa', visible: true, align: 'center', valign: 'middle'},
            {title: '偿还债务所支付的现金', field: 'cashRepaymentsForDebts', visible: true, align: 'center', valign: 'middle'},
            {title: '分配股利、利润或偿付利息所支付的现金', field: 'cpfdodopae', visible: true, align: 'center', valign: 'middle'},
            {title: '支付其他与筹资活动有关的现金', field: 'cprtofa', visible: true, align: 'center', valign: 'middle'},
            {title: '筹资活动现金流出小计', field: 'tcoffa', visible: true, align: 'center', valign: 'middle'},
            {title: '筹集活动产生的现金流量净额', field: 'ncfffa', visible: true, align: 'center', valign: 'middle'},
            {title: '汇率变动对现金及现金等价物的影响', field: 'dodrcoc', visible: true, align: 'center', valign: 'middle'},
            {title: '现金及现金等价物净增加额(五)', field: 'nicace', visible: true, align: 'center', valign: 'middle'},
            {title: '期初现金及现金等价物余额', field: 'icaceb', visible: true, align: 'center', valign: 'middle'},
            {title: '期末现金及现金等价物余额(六)', field: 'tfcaceb', visible: true, align: 'center', valign: 'middle'},
            {title: '净利润', field: 'netProfit', visible: true, align: 'center', valign: 'middle'},
            {title: '资产减值准备', field: 'provisionForAssetImpairment', visible: true, align: 'center', valign: 'middle'},
            {
                title: '固定资产折旧、油气资产折耗、生产性生物资产折旧',
                field: 'depreciationOfFixedAssets',
                visible: true,
                align: 'center',
                valign: 'middle'
            },
            {title: '无形资产摊销', field: 'aoia', visible: true, align: 'center', valign: 'middle'},
            {title: '长期待摊费用摊销', field: 'aoltde', visible: true, align: 'center', valign: 'middle'},
            {title: '待摊费用减少', field: 'decreaseOfDefferedExpenses', visible: true, align: 'center', valign: 'middle'},
            {title: '预提费用增加', field: 'additionOfAccuedExpense', visible: true, align: 'center', valign: 'middle'},
            {title: '处置固定资产无形资产和其他长期资产的损失', field: 'lodofaiaaolta', visible: true, align: 'center', valign: 'middle'},
            {title: '固定资产报废损失', field: 'losofa', visible: true, align: 'center', valign: 'middle'},
            {title: '公允价值变动损失', field: 'polfcifv', visible: true, align: 'center', valign: 'middle'},
            {title: '财务费用', field: 'financeExpense', visible: true, align: 'center', valign: 'middle'},
            {title: '投资损失', field: 'lossesArsingFrominvestment', visible: true, align: 'center', valign: 'middle'},
            {title: '递延所得税资产减少', field: 'deferredIncomeTaxAssets', visible: true, align: 'center', valign: 'middle'},
            {
                title: '递延所得税负债增加',
                field: 'deferredIncomeTaxLiabilitie',
                visible: true,
                align: 'center',
                valign: 'middle'
            },
            {title: '存货的减少', field: 'decreaseInInventories', visible: true, align: 'center', valign: 'middle'},
            {title: '经营性应收项目的减少', field: 'dior', visible: true, align: 'center', valign: 'middle'},
            {title: '经营性应付项目的增加', field: 'iior', visible: true, align: 'center', valign: 'middle'},
            {title: '其他1', field: 'others', visible: true, align: 'center', valign: 'middle'},
            {title: '经营活动产生的现金流量净额2', field: 'ncffoa', visible: true, align: 'center', valign: 'middle'},
            {title: '债务转为资本', field: 'debtsTransferToCapital', visible: true, align: 'center', valign: 'middle'},
            {
                title: '一年内到期的可转换公司债券',
                field: 'oneYearDueConvertibleBonds',
                visible: true,
                align: 'center',
                valign: 'middle'
            },
            {title: '融资租入固定资产', field: 'frttfa', visible: true, align: 'center', valign: 'middle'},
            {title: '其他2', field: 'nonCashOthers', visible: true, align: 'center', valign: 'middle'},
            {title: '现金的期末余额', field: 'cashAtTheEndOfPeriod', visible: true, align: 'center', valign: 'middle'},
            {title: '现金的期初余额', field: 'catbotp', visible: true, align: 'center', valign: 'middle'},
            {title: '现金等价物的期末余额', field: 'ceateotp', visible: true, align: 'center', valign: 'middle'},
            {title: '现金等价物的期初余额', field: 'ceatbotp', visible: true, align: 'center', valign: 'middle'},
            {title: '现金及现金等价物净增加额', field: 'niicace', visible: true, align: 'center', valign: 'middle'},
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
    financeCashFlows07Fb.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(financeCashFlows07Fb.tableId, {where: queryData});
    };

    /**
     * 弹出添加财务报表-现金流量表信息07流水表
     */
    financeCashFlows07Fb.openAddfinanceCashFlows07Fb = function () {
        window.location.href = Feng.ctxPath + '/financeCashFlows07Fb/financeCashFlows07Fb_add';

    };


    /**
     * 点击编辑财务报表-现金流量表信息07流水表
     *
     * @param data 点击按钮时候的行数据
     */
    financeCashFlows07Fb.onEditfinanceCashFlows07Fb = function (data) {

        window.location.href = Feng.ctxPath + '/financeCashFlows07Fb/financeCashFlows07Fb_update?financeCashFlows07FbId=' + data.id;

    };


    /**
     * 点击删除财务报表-现金流量表信息07流水表
     *
     * @param data 点击按钮时候的行数据
     */
    financeCashFlows07Fb.onDeletefinanceCashFlows07Fb = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/financeCashFlows07Fb/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(financeCashFlows07Fb.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("financeCashFlows07FbId", data.financeCashFlows07FbId);
            ajax.start();
        };
        Feng.confirm("是否删除财务报表-现金流量表信息07流水表 " + data.title + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + financeCashFlows07Fb.tableId,
        url: Feng.ctxPath + '/financeCashFlows07Fb/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: financeCashFlows07Fb.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        financeCashFlows07Fb.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        financeCashFlows07Fb.openAddfinanceCashFlows07Fb();
    });

    // 工具条点击事件
    table.on('tool(' + financeCashFlows07Fb.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            financeCashFlows07Fb.onEditfinanceCashFlows07Fb(data);
        } else if (layEvent === 'delete') {
            financeCashFlows07Fb.onDeletefinanceCashFlows07Fb(data);
        }
    });
});
