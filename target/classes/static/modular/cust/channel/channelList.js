/**
 * 渠道管理初始化
 */
var ChannelList = {
    id: "ChannelListTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ChannelList.initColumn = function () {

    function toFixFormatter(value, row, index) {
        return value.toFixed(2);
    }

    return [
        {field: 'selectItem', radio: true},
        {title: '渠道编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '渠道名称', field: 'channelName', visible: true, align: 'center', valign: 'middle'},
        {title: '所在城市区域', field: 'city', visible: true, align: 'center', valign: 'middle'},
        {title: '返佣比例', field: 'fanyongRate', visible: true, align: 'center', valign: 'middle'},
        {title: '返佣户名', field: 'accountName', visible: true, align: 'center', valign: 'middle'},
        {title: '对接人名称', field: 'joinPerson', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};


/**
 * 查询渠道总计列表
 */
ChannelList.search = function () {
    var queryData = {};
    queryData['channelName'] = $("#channelName").val().trim();
    ChannelList.table.refresh({query: queryData});
};

/**
 * 重置
 */
ChannelList.resetSearch = function () {
    $("#channelName").val('');
    ChannelList.search();
};

/**
 * 检查是否选中
 */
ChannelList.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ChannelList.seItem = selected[0];
        return true;
    }
};


/**
 * 点击添加渠道信息
 */
ChannelList.openAddChannel = function () {
    var index = layer.open({
        type: 2,
        title: '添加渠道信息',
        area: ['1000px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cust/channelInfo/getAddPage'
    });
    this.layerIndex = index;
};

/**
 * 打开查看渠道详情
 */
ChannelList.openChangeChannel = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '渠道信息修改',
            area: ['1000px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cust/channelInfo/channelInfo_edit/' + ChannelList.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除放款申请
 */
ChannelList.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/payInfo/delete", function (data) {
            Feng.success("删除成功!");
            PayApply.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("payOrderId",this.seItem.id);
        ajax.start();
    }
};


$(function () {
    var defaultColunms = ChannelList.initColumn();
    var table = new BSTable(ChannelList.id, "/cust/channelInfo/list", defaultColunms);
    table.setPaginationType("server");
    table.init();
    ChannelList.table = table;
});
