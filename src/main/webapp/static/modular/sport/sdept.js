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
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '部门简称', field: 'simplename', align: 'center', valign: 'middle', sortable: true},
        {title: '部门全称', field: 'fullname', align: 'center', valign: 'middle', sortable: true},
        {title: '上级部门', field: 'pname', align: 'center', valign: 'middle', sortable: true},
        {title: '图片', align: 'center', valign: 'middle',formatter:operate}];

    function operate(value,row,index){
        return ['<img style="width: 100px;height: 50px;" src="'+idPicUrls+row["tips"]+'">'].join("");
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
            title: '编辑部门',
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
