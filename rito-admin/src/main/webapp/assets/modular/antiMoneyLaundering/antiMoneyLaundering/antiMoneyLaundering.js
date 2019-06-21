/**
 * 反洗钱管理初始化
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
    var AntiMoneyLaundering = {
        tableId: "antiMoneyLaunderingTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    AntiMoneyLaundering.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: 'numbers', title: '序号', width: 50},
            {title: '报告机构编码', field: 'ricd', visible: true, align: 'center', valign: 'middle'},
            {title: '上报网点代码', field: 'rpnc', visible: true, align: 'center', valign: 'middle'},
            {title: '行业类别', field: 'ritp', visible: true, align: 'center', valign: 'middle'},
            {title: '可疑交易报告紧急程度', field: 'detr', visible: true, align: 'center', valign: 'middle'},
            {title: '报送次数标志', field: 'torp', visible: true, align: 'center', valign: 'middle'},
            {title: '初次报送的可疑交易报告报文名称', field: 'orxn', visible: true, align: 'center', valign: 'middle'},
            {title: '报送方向', field: 'dorp', visible: true, align: 'center', valign: 'middle'},
            {title: '其他报送方向', field: 'odrp', visible: true, align: 'center', valign: 'middle'},
            {title: '可疑交易报告触发点', field: 'tptr', visible: true, align: 'center', valign: 'middle'},
            // {title: '其他可疑交易报告触发点', field: 'otpr', visible: true, align: 'center', valign: 'middle'},
            // {title: '资金交易及客户行为情况', field: 'stcb', visible: true, align: 'center', valign: 'middle'},
            // {title: '疑点分析', field: 'aosp', visible: true, align: 'center', valign: 'middle'},
            // {title: '疑似涉罪类型', field: 'tosc', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑交易特征代码', field: 'stcr', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑交易或事件起始日期', field: 'sbdt', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑交易或事件结束日期', field: 'sedt', visible: true, align: 'center', valign: 'middle'},
            // {title: '交易信息备注', field: 'rotf', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体职业（对私）或行业（对公）', field: 'sevc', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体姓名/名称', field: 'senm', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体身份证件/证明文件类型', field: 'setp', visible: true, align: 'center', valign: 'middle'},
            // {title: '其他身份证件/证明文件类型', field: 'oitp', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体身份证件/证明文件号码', field: 'seid', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体国籍', field: 'stnt', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体联系方式', field: 'scif', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体法定代表人姓名', field: 'srnm', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体法定代表人身份证件类型', field: 'srit', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体法定代表人其他身份证件/证明文件类型', field: 'orit', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体法定代表人身份证件号码', field: 'srid', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体控股股东或实际控制人名称', field: 'scnm', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体控股股东或实际控制人身份证件/证明文件类型', field: 'scit', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体控股股东其他身份证件/证明文件类型', field: 'ocit', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体控股股东或实际控制人身份证件/证明文件号码', field: 'scid', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体所在银行账号', field: 'scba', visible: true, align: 'center', valign: 'middle'},
            // {title: '可疑主体所在银行名称', field: 'scbn', visible: true, align: 'center', valign: 'middle'},
            // {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            // {title: '创建人', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新人', field: 'updateUser', visible: true, align: 'center', valign: 'middle'},
            {title: '删除标记', field: 'delFlag', visible: true, align: 'center', valign: 'middle'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    AntiMoneyLaundering.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(AntiMoneyLaundering.tableId, {where: queryData});
    };

    /**
     * 弹出添加反洗钱
     */
    /**
     * 弹出添加对话框
     */
    AntiMoneyLaundering.openAddAntiMoneyLaundering = function () {
        window.location.href = Feng.ctxPath + '/antiMoneyLaundering/antiMoneyLaundering_add';
    };
    // AntiMoneyLaundering.openAddAntiMoneyLaundering = function () {
    //     admin.putTempData('formOk', false);
    //     top.layui.admin.open({
    //         type: 2,
    //         title: '添加反洗钱',
    //         content: Feng.ctxPath + '/antiMoneyLaundering/antiMoneyLaundering_add',
    //         end: function () {
    //             admin.getTempData('formOk') && table.reload(AntiMoneyLaundering.tableId);
    //         }
    //     });
    // };


    /**
     * 点击编辑反洗钱
     *
     * @param data 点击按钮时候的行数据
     */

    AntiMoneyLaundering.onEditAntiMoneyLaundering = function (data) {
        window.location.href = Feng.ctxPath + '/antiMoneyLaundering/antiMoneyLaundering_update?antiId='+ data.antiId;
    };
    // AntiMoneyLaundering.onEditAntiMoneyLaundering = function (data) {
    //     admin.putTempData('formOk', false);
    //     top.layui.admin.open({
    //         type: 2,
    //         title: '反洗钱详情',
    //         content: Feng.ctxPath + '/antiMoneyLaundering/antiMoneyLaundering_update/' + data.antiId,
    //         end: function () {
    //             admin.getTempData('formOk') && table.reload(AntiMoneyLaundering.tableId);
    //         }
    //     });
    // };


    /**
     * 点击删除反洗钱
     *
     * @param data 点击按钮时候的行数据
     */
    AntiMoneyLaundering.onDeleteAntiMoneyLaundering = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/antiMoneyLaundering/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(AntiMoneyLaundering.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("antiId", data.antiId);
            ajax.start();
        };
        Feng.confirm("是否删除反洗钱 " + data.title + "?", operation);
    };

    //生成XML文件
    AntiMoneyLaundering.oncreateXMLAntiMoneyLaundering = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/antiMoneyLaundering/createXML", function (data) {
                Feng.success("生成XML成功！");
            }, function (data) {
                Feng.error("生成XML失败！" + data.responseJSON.message)
            });
            ajax.set("antiId", data.antiId);
            ajax.start();
        };
        Feng.confirm("antiId:"+data.antiId,operation);
    }

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + AntiMoneyLaundering.tableId,
        url: Feng.ctxPath + '/antiMoneyLaundering/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: AntiMoneyLaundering.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        AntiMoneyLaundering.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        AntiMoneyLaundering.openAddAntiMoneyLaundering();
    });

    // 工具条点击事件
    table.on('tool(' + AntiMoneyLaundering.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            AntiMoneyLaundering.onEditAntiMoneyLaundering(data);
        } else if (layEvent === 'delete') {
            AntiMoneyLaundering.onDeleteAntiMoneyLaundering(data);
        }else if(layEvent === 'createXML'){
            AntiMoneyLaundering.oncreateXMLAntiMoneyLaundering(data);
        }
    });
});
