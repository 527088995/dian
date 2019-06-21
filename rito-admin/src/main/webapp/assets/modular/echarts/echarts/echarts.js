/**
 * 测试Echarts管理初始化
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
    var Echarts = {
        tableId: "echartsTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    Echarts.initColumn = function () {
        return [[
            {type: 'checkbox'},
            { type: 'numbers', title: '序号', width: 50 },
                        {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
                        {title: '', field: 'type', visible: true, align: 'center', valign: 'middle'},
                        {title: '', field: 'value', visible: true, align: 'center', valign: 'middle'},
                        {title: '', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
                        {title: '', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
                        {title: '', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
                        {title: '', field: 'updateUser', visible: true, align: 'center', valign: 'middle'},
                        {title: '', field: 'delFlag', visible: true, align: 'center', valign: 'middle'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    // 渲染页面
    var tableResult = table.render({
        elem: '#' + Echarts.tableId,
        url: Feng.ctxPath + '/echarts/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Echarts.initColumn()
    });

});
