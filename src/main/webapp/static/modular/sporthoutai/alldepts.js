var idPicUrls = $("#idPicUrls").val();
/**
 * 系统管理--用户管理的单例对象
 */
var MgrDept = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid:0
};

/**
 * 初始化表格的列
 */
MgrDept.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true,visible: false},
        {title: '序号',align: 'center',valign: 'bottom',formatter: function(value, row, index) {
                // return index + 1;
                var pageSize=$('#managerTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                var pageNumber=$('#managerTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
            }
        },
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: 'pid', field: 'pid', visible: false, align: 'center', valign: 'middle'},
        {title: '部门简称', field: 'simplename', align: 'center', valign: 'middle', sortable: true},
        {title: '部门全称', field: 'fullname', align: 'center', valign: 'middle', sortable: true},
        {title: '巡检状态', align: 'center', valign: 'middle',formatter:operate},
        {title: '操作', align: 'center', valign: 'middle',formatter:operate2}];

    function operate(value,row,index){
        if([row["num"]]==1&&[row["version"]]==1){
            return ['已巡检'].join("");
        }else if([row["num"]]==1&&[row["version"]]==0){
            return ['<span style="color: red;">未巡检</span>'].join("");
        }else{
            return ['--'].join("");
        }
    }
    function operate2(value,row,index){
        if([row["num"]]==1&&[row["version"]]==1){
            return ['<a href="/sqc/showAllQc?deptId='+[row["id"]]+'&deptPid='+[row["pid"]]+'">查看器材</a>'].join("");
        }else if([row["num"]]==1&&[row["version"]]==0){
            return ['<a href="/sqc/showAllQc?deptId='+[row["id"]]+'&deptPid='+[row["pid"]]+'">巡检</a>'].join("");
        }else{
            return ['--'].join("");
        }
    }
    return columns;
};

/**
 * 检查是否选中
 */
MgrDept.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        MgrDept.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
MgrDept.openAddMgr = function () {

    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '添加部门',
            area: ['800px', '560px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sdept/showadd?pid='+this.seItem.id+'&pName='+this.seItem.fullname
        });
        this.layerIndex = index;
    }
};

/**
 * 点击修改按钮时
 * @param userId 管理员id
 */
MgrDept.openChangeUser = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑管理员',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sdept/showedit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};



/**
 * 删除部门
 */
MgrDept.delMgrDept = function () {
    if (this.check()) {

        var operation = function(){
            var deptId = MgrDept.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/dept/delete", function () {
                Feng.success("删除成功!");
                MgrDept.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("deptId", deptId);
            ajax.start();
        };

        Feng.confirm("是否删除部门" + MgrDept.seItem.simplename + "?",operation);
    }
};


MgrDept.resetSearch = function () {
    $("#deptName").val("");
    MgrDept.search();
}

MgrDept.search = function () {
    var deptName = $("#deptName").val();
    var queryData = {};
    queryData['deptid'] = MgrDept.deptid;
    if(deptName!=null&&deptName!=''&&deptName!=undefined){
        queryData['deptName'] = deptName;
    }
    MgrDept.table.refresh({query: queryData});
}

MgrDept.onClickDept = function (e, treeId, treeNode) {
    MgrDept.deptid = treeNode.id;
    MgrDept.search();
};

$(function () {
    var defaultColunms = MgrDept.initColumn();
    var table = new BSTable(MgrDept.id, "/sdept/pageQuery", defaultColunms);
    table.setPaginationType("server");
    MgrDept.table = table.init();

    var ztree = new $ZTree("deptTree", "/dept/tree");
    ztree.bindOnClick(MgrDept.onClickDept);
    ztree.init();
});
