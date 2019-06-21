/**
 * 企业借贷账户信息流水表管理初始化
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
    var entAcctInfoFb = {
        tableId: "entAcctInfoFbTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    entAcctInfoFb.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '账户类型', field: 'acctType', visible: true, align: 'center', valign: 'middle'},
            {title: '账户标识码', field: 'acctCode', visible: true, align: 'center', valign: 'middle'},
            {title: '信息报告日期', field: 'rptDate', visible: true, align: 'center', valign: 'middle'},
            {title: '报告时点说明代码', field: 'rptDateCode', visible: true, align: 'center', valign: 'middle'},
            {title: '借款人名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '借款人身份标识类型', field: 'idType', visible: true, align: 'center', valign: 'middle'},
            {title: '借款人身份标识号码', field: 'idNum', visible: true, align: 'center', valign: 'middle'},
            {title: '业务管理机构代码', field: 'mngmtOrgCode', visible: true, align: 'center', valign: 'middle'},
            {title: '借款业务大类', field: 'busiLines', visible: true, align: 'center', valign: 'middle'},
            {title: '借款业务种类细分', field: 'busiDtlLines', visible: true, align: 'center', valign: 'middle'},
            {title: '开户日期', field: 'openDate', visible: true, align: 'center', valign: 'middle'},
            {title: '币种', field: 'cy', visible: true, align: 'center', valign: 'middle'},
            {title: '信用额度', field: 'acctCredLine', visible: true, align: 'center', valign: 'middle'},
            {title: '借款金额', field: 'loanAmt', visible: true, align: 'center', valign: 'middle'},
            {title: '分次放款标志', field: 'flag', visible: true, align: 'center', valign: 'middle'},
            {title: '到期日期', field: 'dueDate', visible: true, align: 'center', valign: 'middle'},
            {title: '还款方式', field: 'repayMode', visible: true, align: 'center', valign: 'middle'},
            {title: '还款频率', field: 'repayFreqcy', visible: true, align: 'center', valign: 'middle'},
            {title: '业务申请地行政区划代码', field: 'applyBusiDist', visible: true, align: 'center', valign: 'middle'},
            {title: '担保方式', field: 'guarMode', visible: true, align: 'center', valign: 'middle'},
            {title: '其他还款保证方式', field: 'othRepyGuarWay', visible: true, align: 'center', valign: 'middle'},
            {title: '借款期限分类', field: 'loanTimeLimCat', visible: true, align: 'center', valign: 'middle'},
            {title: '贷款发放形式', field: 'loafrm', visible: true, align: 'center', valign: 'middle'},
            {title: '贷款实际投向', field: 'actInvest', visible: true, align: 'center', valign: 'middle'},
            {title: '业务经营类型', field: 'fundSou', visible: true, align: 'center', valign: 'middle'},
            {title: '资产转让标志', field: 'assetTrandFlag', visible: true, align: 'center', valign: 'middle'},
            {title: '责任人个数', field: 'rltRepymtNm', visible: true, align: 'center', valign: 'middle'},
            {title: '抵质押合同个数', field: 'ccNum', visible: true, align: 'center', valign: 'middle'},
            {title: '授信协议标识码', field: 'mcc', visible: true, align: 'center', valign: 'middle'},
            {title: '初始债券人名称', field: 'initCredName', visible: true, align: 'center', valign: 'middle'},
            {title: '初始债权人机构代码', field: 'initCedOrgCode', visible: true, align: 'center', valign: 'middle'},
            {title: '原债务种类', field: 'origDbtCate', visible: true, align: 'center', valign: 'middle'},
            {title: '债券转移时还款状态', field: 'initRpySts', visible: true, align: 'center', valign: 'middle'},
            {title: '账户状态', field: 'acctStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '余额', field: 'acctBal', visible: true, align: 'center', valign: 'middle'},
            {title: '余额变化日期', field: 'balChgDate', visible: true, align: 'center', valign: 'middle'},
            {title: '五级分类', field: 'fiveCate', visible: true, align: 'center', valign: 'middle'},
            {title: '五级分类认定日期', field: 'fiveCateAdjDate', visible: true, align: 'center', valign: 'middle'},
            {title: '剩余还款月数', field: 'pymtPrd', visible: true, align: 'center', valign: 'middle'},
            {title: '当前逾期总额', field: 'totOverd', visible: true, align: 'center', valign: 'middle'},
            {title: '当前逾期本金', field: 'overdPrinc', visible: true, align: 'center', valign: 'middle'},
            {title: '当前逾期天数', field: 'overdDy', visible: true, align: 'center', valign: 'middle'},
            {title: '最近一次实际还款日期', field: 'latRpyDate', visible: true, align: 'center', valign: 'middle'},
            {title: '最近一次实际还款金额', field: 'latRpyAmt', visible: true, align: 'center', valign: 'middle'},
            {title: '最近一次实际归还本金', field: 'latRpyPrincAmt', visible: true, align: 'center', valign: 'middle'},
            {title: '还款形式', field: 'rpmtType', visible: true, align: 'center', valign: 'middle'},
            {title: '最近一次约定还款日', field: 'latAgrrRpyDate', visible: true, align: 'center', valign: 'middle'},
            {title: '最近一次约定还款金额', field: 'lagAgrrRpyAmt', visible: true, align: 'center', valign: 'middle'},
            {title: '下一次约定还款日期', field: 'nxtAgrrRpyDate', visible: true, align: 'center', valign: 'middle'},
            {title: '账户关闭日期', field: 'closeDate', visible: true, align: 'center', valign: 'middle'},
            {title: '交易个数', field: 'cagOfTrdNm', visible: true, align: 'center', valign: 'middle'},
            {title: '信息记录状态 ', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '删除标志', field: 'deleteFlag', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新人', field: 'updateUser', visible: true, align: 'center', valign: 'middle'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    entAcctInfoFb.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(entAcctInfoFb.tableId, {where: queryData});
    };

    /**
     * 弹出添加企业借贷账户信息流水表
     */
    entAcctInfoFb.openAddentAcctInfoFb = function () {
        window.location.href = Feng.ctxPath + '/entAcctInfoFb/entAcctInfoFb_add';
    };


    /**
     * 点击编辑企业借贷账户信息流水表
     *
     * @param data 点击按钮时候的行数据
     */
    entAcctInfoFb.onEditentAcctInfoFb = function (data) {
        window.location.href = Feng.ctxPath + '/entAcctInfoFb/entAcctInfoFb_update?entAcctInfoFbId=' + data.id;
        // admin.putTempData('formOk', false);
        // top.layui.admin.open({
        //     type: 2,
        //     title: '企业借贷账户信息流水表详情',
        //     content: Feng.ctxPath + '/entAcctInfoFb/entAcctInfoFb_update/' + data.entAcctInfoFbId,
        //     end: function () {
        //         admin.getTempData('formOk') && table.reload(entAcctInfoFb.tableId);
        //     }
        // });
    };


    /**
     * 点击删除企业借贷账户信息流水表
     *
     * @param data 点击按钮时候的行数据
     */
    entAcctInfoFb.onDeleteentAcctInfoFb = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/entAcctInfoFb/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(entAcctInfoFb.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("entAcctInfoFbId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除企业借贷账户信息流水表 " + data.id + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + entAcctInfoFb.tableId,
        url: Feng.ctxPath + '/entAcctInfoFb/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: entAcctInfoFb.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        entAcctInfoFb.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        entAcctInfoFb.openAddentAcctInfoFb();
    });

    // 工具条点击事件
    table.on('tool(' + entAcctInfoFb.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            entAcctInfoFb.onEditentAcctInfoFb(data);
        } else if (layEvent === 'delete') {
            entAcctInfoFb.onDeleteentAcctInfoFb(data);
        }
    });
});
