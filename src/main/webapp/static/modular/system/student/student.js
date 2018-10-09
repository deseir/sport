/**
 * 学生管理初始化
 */
var Student = {
    id: "StudentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Student.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '姓名', field: 'name', align: 'center', valign: 'middle', sortable: true,width:'17%'},
        {title: '年龄', field: 'age', align: 'center', valign: 'middle', sortable: true,width:'12%'},
        {title: '班级', field: 'stuClass', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'crtTime', align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updTime', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Student.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Student.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加学生
 */
Student.openAddStudent = function () {
    var index = layer.open({
        type: 2,
        title: '添加学生',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/student/student_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看学生详情
 */
Student.openStudentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '学生详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/student/student_update/' + Student.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除学生
 */
Student.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/student/delete", function (data) {
            Feng.success("删除成功!");
            Student.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("studentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询学生列表
 */
Student.search = function () {
    var queryData = {};
    queryData['logname'] = $("#name").val();
    Student.table.refresh({query: queryData});
};

$(function () {
	init();
	
    var defaultColunms = Student.initColumn();
    var table = new BSTable(Student.id, "/student/list", defaultColunms);
    table.setPaginationType("server");
    Student.table = table.init();
});

function init() {

    var BootstrapTable = $.fn.bootstrapTable.Constructor;
    BootstrapTable.prototype.onSort = function (event) {
        var $this = event.type === "keypress" ? $(event.currentTarget) : $(event.currentTarget).parent(),
            $this_ = this.$header.find('th').eq($this.index()),
            sortName = this.header.sortNames[$this.index()];

        this.$header.add(this.$header_).find('span.order').remove();

        if (this.options.sortName === $this.data('field')) {
            this.options.sortOrder = this.options.sortOrder === 'asc' ? 'desc' : 'asc';
        } else {
            this.options.sortName = sortName || $this.data('field');
            this.options.sortOrder = $this.data('order') === 'asc' ? 'desc' : 'asc';
        }
        this.trigger('sort', this.options.sortName, this.options.sortOrder);

        $this.add($this_).data('order', this.options.sortOrder);

        // Assign the correct sortable arrow
        this.getCaret();

        if (this.options.sidePagination === 'server') {
            this.initServer(this.options.silentSort);
            return;
        }

        this.initSort();
        this.initBody();
    };
    BootstrapTable.prototype.getCaret = function () {
        var that = this;

        $.each(this.$header.find('th'), function (i, th) {
            var sortName = that.header.sortNames[i];
            $(th).find('.sortable').removeClass('desc asc').addClass((sortName || $(th).data('field')) === that.options.sortName ? that.options.sortOrder : 'both');
        });
    };
}
