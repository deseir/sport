/**
 * 初始化学生详情对话框
 */
var StudentInfoDlg = {
    studentInfoData : {},
	ztreeInstance: null,
	validateFields: {
    name: {
        validators: {
            notEmpty: {
                message: '姓名不能为空'
            }
        }
    },
    age: {
        validators: {
            notEmpty: {
                message: '年龄不能为空'
            }
        }
    },
    stuClass: {
        validators: {
            notEmpty: {
                message: '课程不能为空'
            }
        }
    }
}
};

/**
 * 清除数据
 */
StudentInfoDlg.clearData = function() {
    this.studentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudentInfoDlg.set = function(key, val) {
    this.studentInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StudentInfoDlg.close = function() {
    parent.layer.close(window.parent.Student.layerIndex);
}

/**
 * 收集数据
 */
StudentInfoDlg.collectData = function() {
    this.set('id').set("name").set("age").set("stuClass");
}

/**
 * 验证数据是否为空
 */
StudentInfoDlg.validate = function () {
    $('#studentInfoForm').data("bootstrapValidator").resetForm();
    $('#studentInfoForm').bootstrapValidator('validate');
    return $("#studentInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
StudentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/student/add", function(data){
        Feng.success("添加成功!");
        window.parent.Student.table.refresh();
        StudentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
StudentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/student/update", function(data){
        Feng.success("修改成功!");
        window.parent.Student.table.refresh();
        StudentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("studentInfoForm", StudentInfoDlg.validateFields);
});
