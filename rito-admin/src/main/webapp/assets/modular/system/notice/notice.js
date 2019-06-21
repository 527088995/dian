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
    var Notice = {
        tableId: "noticeTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    Notice.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'noticeId', hide: true, sort: true, title: 'id'},
            {field: 'title', sort: true, title: '标题'},
            {field: 'content', sort: true, title: '内容'},
            {field: 'createrName', sort: true, title: '发布者'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Notice.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Notice.tableId, {where: queryData});
    };

    /**
     * 弹出添加通知
     */
    Notice.openAddNotice = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加通知',
            content: Feng.ctxPath + '/notice/notice_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Notice.tableId);
            }
        });
    };

    /**
     * 点击编辑通知
     *
     * @param data 点击按钮时候的行数据
     */
    Notice.onEditNotice = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '通知详情',
            content: Feng.ctxPath + '/notice/notice_update/' + data.noticeId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Notice.tableId);
            }
        });
    };

    /**
     * 点击删除通知
     *
     * @param data 点击按钮时候的行数据
     */
    Notice.onDeleteNotice = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/notice/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Notice.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("noticeId", data.noticeId);
            ajax.start();
        };
        Feng.confirm("是否删除通知 " + data.title + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Notice.tableId,
        url: Feng.ctxPath + '/notice/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Notice.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Notice.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Notice.openAddNotice();
    });

    // 工具条点击事件
    table.on('tool(' + Notice.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Notice.onEditNotice(data);
        } else if (layEvent === 'delete') {
            Notice.onDeleteNotice(data);
        }
    });
});
