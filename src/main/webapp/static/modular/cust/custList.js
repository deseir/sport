var sex={"1":"女","0":"男"}

var CustList = {
    id: "CustListTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CustList.initColumn = function () {

    function operateFormatter() {
        return [
            '<a style="color:#30af90" class="editPingAn" id="operation" href="javascript:void(0)" title="edit">',
            '查看详细',
            '</a >'
        ].join('');
    }

    var operateEvents = {
        'click #operation': function (e, value, row) {
            var custId = row['id'];
            window.location.href="/cust/showCustomerDetail?custId="+custId;
            return false;
        },
    };

    return [
        {field: 'selectItem', radio: true},
        {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle',formatter:function(val){
        	return sex[val];
        }},
        {title: '出生年月日', field: 'birthday', visible: true, align: 'center', valign: 'middle'},
        {title: '身份证号码', field: 'certId', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        /*{title: '教育程度', field: 'education', visible: true, align: 'center', valign: 'middle'},*/
        {title: '民族', field: 'nation', visible: true, align: 'center', valign: 'middle'},
        {title: '居住地址', field: 'liveAddress', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '操作', visible: true, align: 'center', valign: 'middle',
            events: operateEvents,width:'100px',
            formatter: operateFormatter
        }
    ];
};


/**
 * 查询
 */
CustList.search = function () {
    var queryData = {};
    queryData['name'] = $("#custName").val();
    queryData['certId'] = $("#certId").val();
    queryData['mobile'] = $("#mobile").val();
    CustList.table.refresh({query: queryData});
};

/**
 * 重置
 */
CustList.resetSearch = function () {
    $("#custName").val("");
    $("#certId").val("");
    $("#mobile").val("");
    CustList.search();
};

/**
 * 检查是否选中
 */
CustList.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CustList.seItem = selected[0];
        return true;
    }
};


/**
 * 点击添加渠道信息
 */
CustList.openAddChannel = function () {
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
CustList.openChangeChannel = function () {
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
CustList.delete = function () {
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
    var defaultColunms = CustList.initColumn();
    var table = new BSTable(CustList.id, "/carInfo/pageCustDetailQuery", defaultColunms);
    table.setPaginationType("server");
    table.init();
    CustList.table = table;
});
