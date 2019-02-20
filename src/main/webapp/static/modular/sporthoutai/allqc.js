var idPicUrls = $("#idPicUrls").val();
var MgrQc = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    jieIndex:-1
};

/**
 * 初始化表格的列
 */
MgrQc.initColumn = function () {

    var columns = [
        {field: 'selectItem', radio: true,visible: true},
        {title: '序号',align: 'center',valign: 'bottom',formatter: function(value, row, index) {
                // return index + 1;
                var pageSize=$('#managerTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                var pageNumber=$('#managerTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
            }
        },
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '部门名称', field: 'deptname',align: 'center', valign: 'middle'},
        {title: '健身设备', field: 'jssb', align: 'center', valign: 'middle', sortable: false},
        {title: '金额(元)', field: 'amount', align: 'center', valign: 'middle', sortable: false},
        {title: '器材编号', field: 'qcbh', align: 'center', valign: 'middle', sortable: false},
        {title: '供应商', field:'provider',align: 'center', valign: 'middle',sortable: false},
        {title: '安装时间', field:'azsj',align: 'center', valign: 'middle',sortable: false},
        {title: '器材现状', field:'qcxz',align: 'center', valign: 'middle',sortable: false},
        // {title: '是否报废', field:'sfbf',align: 'center', valign: 'middle',sortable: false},
        {title: '故障描述', field:'gzms',align: 'center', valign: 'middle',sortable: false},
        {title: '缓冲层', field:'hcc',align: 'center', valign: 'middle',sortable: false},
        {title: '免费维护期限', field:'mfwhqx',align: 'center', valign: 'middle',sortable: false},
        {title: '安全使用期限', field:'aqsyqx',align: 'center', valign: 'middle',sortable: false},
        {title: '地理经度', field:'dljd',align: 'center', valign: 'middle',sortable: false},
        {title: '地理纬度', field:'dlwd',align: 'center', valign: 'middle',sortable: false},
        {title: '操作', align: 'center', valign: 'middle',formatter:operate}];

        function operate(value,row,index){
            return ['<a href="/sqc/showHoutaiQcDetail?qcId='+[row["id"]]+'">查看详细</a>'].join("");
        }

    return columns;
};



/**
 * 检查是否选中
 */
MgrQc.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        MgrQc.seItem = selected[0];
        return true;
    }
};
MgrQc.resetSearch = function () {
    $("#jssb").val("");
    MgrQc.search();
}

MgrQc.search = function () {
    var queryData = {};
    queryData['jssb'] = $("#jssb").val().trim();
    // queryData['carBrand'] = $("#carBrand").val();
    // queryData['carModel'] = $("#carModel").val();
    MgrQc.table.refresh({query: queryData});
}

//返回部门列表
MgrQc.back = function () {
    window.location.href="/showAll";
}

//点击添加
MgrQc.openAddQc = function () {
    var deptId =$("#deptId").val();
    var deptPid =$("#deptPid").val();
        var index = layer.open({
            type: 2,
            title: '添加器材',
            area: ['800px', '560px'], //宽高
            fix: false, //不固定
            maxmin: true,
            closeBtn:1,
            content: Feng.ctxPath + '/sqc/openAddQc?deptId='+deptId+"&deptPid="+deptPid,
            cancel: function(){//关闭回调刷新列表页
                MgrQc.table.refresh();
            }
        });
        this.layerIndex = index;
};

//编辑器材
MgrQc.openQcEdit = function () {
    if (this.check()) {
        var qcId = MgrQc.seItem.id;
        var index = layer.open({
            type: 2,
            title: '编辑器材',
            area: ['800px', '560px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sqc/openQcEdit?qcId=' + qcId,
            cancel: function(){//关闭回调刷新列表页
                MgrQc.table.refresh();
            }

        });
        this.layerIndex = index;
    }
}

//删除器材
MgrQc.delMgrQc = function () {
    if (this.check()) {

        var operation = function(){
            var qcId = MgrQc.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/sqc/deleteById", function () {
                Feng.success("删除成功!");
                MgrQc.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", qcId);
            ajax.start();
        };

        Feng.confirm("是否删除器材" + MgrQc.seItem.jssb + "?",operation);
    }
};

//确认已巡检
MgrQc.confirmXj=function() {
    var deptId = $("#deptId").val();
    $.ajax({
        type: "POST",
        url: '/sdept/sfxj',
        dataType: 'json',
        data: {
            'deptId':deptId,
            'sfxj':'1' // 1-已巡检
        },
        success: function(data) {
            alert(data.msg);
        },
        error: function() {
            alert("修改数据异常！");

        }
    });

}

MgrQc.seeQjPic=function(){
    var deptId=$("#deptId").val();
window.location.href="/sqc/houtai/showQjPic?deptId="+deptId;
}


$(function () {
    var defaultColunms = MgrQc.initColumn();
    var deptId=$("#deptId").val();
    var table = new BSTable( MgrQc.id, "/sqc/houtai/pageQuery", defaultColunms);
    table.queryParam={"deptid":deptId};
    table.setPaginationType("server");
    MgrQc.table = table.init();
});