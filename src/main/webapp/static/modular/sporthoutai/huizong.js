var MgrQc = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    jieIndex:-1
};


/**
 * 搜索
 */
MgrQc.search = function () {
    var queryData = {};
    queryData['deptid'] = $("#deptid").val().trim();
    MgrQc.table.refresh({query: queryData});
}

/**
 * 重置
 */
MgrQc.resetSearch = function () {
    $("#deptid").val("");
    MgrQc.search();
}

/**
 * 导出汇总
 */
MgrQc.exportHuizong=function(){
    window.location.href="/huizongStatis/exportHuizongList";
}


/**
 * 初始化表格的列
 */
MgrQc.initColumn = function () {

    var columns = [
        {field: 'selectItem', radio: true,visible: false},
        {title: '序号',align: 'center',align: 'center',valign: 'middle',formatter: function(value, row, index) {
                // return index + 1;
                var pageSize=$('#managerTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                var pageNumber=$('#managerTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
            }
        },
        {title: '部门名称', field: 'deptName',align: 'center', valign: 'middle'},
        {title: '总数', field: 'totalCount', align: 'center', valign: 'middle', sortable: false},
        {title: '损坏', field: 'badCount', align: 'center', valign: 'middle', sortable: false},
        {title: '维修', field:'weixiu',align: 'center', valign: 'middle',sortable: false},
        {title: '拆除', field:'chaichu',align: 'center', valign: 'middle',sortable: false},
        {title: '正常使用', field:'normal',align: 'center', valign: 'middle',sortable: false},
        {title: '单位',field:'danwei',align: 'center', valign: 'middle',sortable: false}];

    return columns;
};



$(function () {
    var defaultColunms = MgrQc.initColumn();
    var table = new BSTable( MgrQc.id, "/huizongStatis/pageQuery", defaultColunms);
    table.setPaginationType("server");
    MgrQc.table = table.init();
});

$(document).ready(function () {
    $.ajax({
        type:"POST",
        url:"/dept/getAllSubDeptByCurUser",
        dataType:"json",
        success:function (data) {
            var html ="";
            $.each(data,function (i) {
                html+="<option value='"+data[i].id+"'>"+data[i].simplename+"</option>"
            });
            $("#deptid").append(html);
        },
        error:function (data) {
            Feng.info("获取部门失败！"+data)
        }
    });
});
