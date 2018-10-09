
var MgrUser = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    jieIndex:-1
};

/**
 * 初始化表格的列
 */
MgrUser.initColumn = function () {

    function operateFormatter(row) {
        return [
            '<a style="color:#30af90" class="editPingAn" id="operation" href="javascript:void(0)" title="edit">',
            '查看详细',
            '</a >'
        ].join('');
    }

    var operateEvents = {
        'click #operation': function (e, value, row) {
            var carId = row['id'];
            window.location.href="/carInfo/showCar?carId="+carId;
            return false;
        },
    };

    var columns = [
       {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '车牌号', field: 'carNum', align: 'center', valign: 'middle', sortable: true},
        {title: '车辆通俗配置名称', field: 'carConfigName', align: 'center', valign: 'middle', sortable: true},
        {title: '车辆类型', field: 'carType', align: 'center', valign: 'middle', sortable: true},
        {title: '车辆品牌', field: 'carBrand', align: 'center', valign: 'middle', sortable: true},
        {title: '车辆型号', field: 'carModel', align: 'center', valign: 'middle', sortable: true},
        {title: '车辆识别号', field: 'vin', align: 'center', valign: 'middle', sortable: true},
        {title: '发动机号', field: 'engineNo', align: 'center', valign: 'middle', sortable: true},
        {title: '车身颜色', field: 'carColor', align: 'center', valign: 'middle', sortable: true},
        /*{title: '国产/进口', field: 'carImportType', align: 'center', valign: 'middle', sortable: true},
        {title: '燃料种类', field: 'fuelType', align: 'center', valign: 'middle', sortable: true},*/
        {title: '排量(ml)', field: 'displacement', align: 'center', valign: 'middle', sortable: true},
        {title: '制造厂名称', field: 'manufacturer', align: 'center', valign: 'middle', sortable: true},
        /*{title: '使用性质', field: 'carUsage', align: 'center', valign: 'middle', sortable: true},
        {title: '车辆获得方式', field: 'getType', align: 'center', valign: 'middle', sortable: true},*/
        {title: '出厂日期', field: 'productDate', align: 'center', valign: 'middle', sortable: true},
        {title: '操作', visible: true, align: 'center', valign: 'middle',
            events: operateEvents,width:'100px',
            formatter: operateFormatter
        }
        ];
    return columns;
};



/**
 * 检查是否选中
 */
MgrUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        MgrUser.seItem = selected[0];
        return true;
    }
};
MgrUser.resetSearch = function () {
    $("#carNum").val("");
    $("#carBrand").val("");
    $("#carModel").val("");
    MgrUser.search();
}

MgrUser.search = function () {
    var queryData = {};
    queryData['carNum'] = $("#carNum").val().trim();
    queryData['carBrand'] = $("#carBrand").val();
    queryData['carModel'] = $("#carModel").val();
    MgrUser.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = MgrUser.initColumn();
    var table = new BSTable( MgrUser.id, "/carInfo/pageQuery", defaultColunms);
    table.setPaginationType("server");
    MgrUser.table = table.init();
});
