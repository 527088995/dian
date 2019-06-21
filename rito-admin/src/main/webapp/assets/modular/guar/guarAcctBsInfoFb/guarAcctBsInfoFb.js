/**
 * 账户信息流水表管理初始化
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
    var guarAcctBsInfoFb = {
        tableId: "guarAcctBsInfoFbTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    guarAcctBsInfoFb.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '账户类型', field: 'acctType', visible: true, align: 'center', valign: 'middle'},
            {title: '账户标识码', field: 'acctCode', visible: true, align: 'center', valign: 'middle'},
            {title: '信息报告日期', field: 'rptDate', visible: true, align: 'center', valign: 'middle'},
            {title: '报告时点说明代码', field: 'rptDateCode', visible: true, align: 'center', valign: 'middle'},
            {title: '债务人名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '债务人身份标识类型', field: 'idType', visible: true, align: 'center', valign: 'middle'},
            {title: '债务人身份标识号码', field: 'idNum', visible: true, align: 'center', valign: 'middle'},
            {title: '业务管理机构代码', field: 'mngmtOrgCode', visible: true, align: 'center', valign: 'middle'},
            {title: '担保业务大类', field: 'busiLines', visible: true, align: 'center', valign: 'middle'},
            {title: '担保业务种类细分', field: 'busiDtilLines', visible: true, align: 'center', valign: 'middle'},
            {title: '开户日期', field: 'openDate', visible: true, align: 'center', valign: 'middle'},
            {title: '担保金额', field: 'guarAmt', visible: true, align: 'center', valign: 'middle'},
            {title: '币种', field: 'cy', visible: true, align: 'center', valign: 'middle'},
            {title: '到期日期', field: 'dueDate', visible: true, align: 'center', valign: 'middle'},
            {title: '反担保方式', field: 'guarMode', visible: true, align: 'center', valign: 'middle'},
            {title: '其他还款保证方式', field: 'othRepGuarWay', visible: true, align: 'center', valign: 'middle'},
            {title: '保证金百分比', field: 'secDep', visible: true, align: 'center', valign: 'middle'},
            {title: '担保合同文本编号', field: 'ctrctTxtCode', visible: true, align: 'center', valign: 'middle'},
            {title: '责任人个数', field: 'rltRepymtNm', visible: true, align: 'center', valign: 'middle'},
            {title: '抵质押合同个数', field: 'ccNm', visible: true, align: 'center', valign: 'middle'},
            {title: '授信协议标识码', field: 'mcc', visible: true, align: 'center', valign: 'middle'},
            {title: '账户状态', field: 'acctStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '在保余额', field: 'loanAmt', visible: true, align: 'center', valign: 'middle'},
            {title: '余额变化日期', field: 'repayPrd', visible: true, align: 'center', valign: 'middle'},
            {title: '五级分类', field: 'fiveCate', visible: true, align: 'center', valign: 'middle'},
            {title: '五级分类认定日期', field: 'fiveCateAdjDate', visible: true, align: 'center', valign: 'middle'},
            {title: '风险敞口', field: 'riex', visible: true, align: 'center', valign: 'middle'},
            {title: '代偿（垫款）标志', field: 'compAdvFlag', visible: true, align: 'center', valign: 'middle'},
            {title: '账户关闭日期', field: 'closeDate', visible: true, align: 'center', valign: 'middle'},
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
    guarAcctBsInfoFb.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(guarAcctBsInfoFb.tableId, {where: queryData});
    };

    /**
     * 弹出添加账户信息流水表
     */
    guarAcctBsInfoFb.openAddguarAcctBsInfoFb = function () {
        window.location.href = Feng.ctxPath + '/guarAcctBsInfoFb/guarAcctBsInfoFb_add';

    };


    /**
     * 点击编辑账户信息流水表
     *
     * @param data 点击按钮时候的行数据
     */
    guarAcctBsInfoFb.onEditguarAcctBsInfoFb = function (data) {

        window.location.href = Feng.ctxPath + '/guarAcctBsInfoFb/guarAcctBsInfoFb_update?guarAcctBsInfoFbId=' + data.id;

    };


    /**
     * 点击删除账户信息流水表
     *
     * @param data 点击按钮时候的行数据
     */
    guarAcctBsInfoFb.onDeleteguarAcctBsInfoFb = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/guarAcctBsInfoFb/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(guarAcctBsInfoFb.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("guarAcctBsInfoFbId", data.guarAcctBsInfoFbId);
            ajax.start();
        };
        Feng.confirm("是否删除账户信息流水表 " + data.title + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + guarAcctBsInfoFb.tableId,
        url: Feng.ctxPath + '/guarAcctBsInfoFb/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: guarAcctBsInfoFb.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        guarAcctBsInfoFb.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        guarAcctBsInfoFb.openAddguarAcctBsInfoFb();
    });

    // 工具条点击事件
    table.on('tool(' + guarAcctBsInfoFb.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            guarAcctBsInfoFb.onEditguarAcctBsInfoFb(data);
        } else if (layEvent === 'delete') {
            guarAcctBsInfoFb.onDeleteguarAcctBsInfoFb(data);
        }
    });
});
