
$(document).ready(function () {
    Feng.initValidator("addBussQuery", saveOrUpdates.validateFields);
    var id = $("#bussId").val();
    if(id==-1){
        return;
    }else{
        $.ajax({
            type: "POST",
            url: '/creditBussQueryRecord/findById',
            dataType: 'json',
            data: {
                'id':id
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    $("#queryDate").val(data.data.queryDate==null?"":data.data.queryDate.split(" ")[0]);
                    $("#queryOrg").val(data.data.queryOrg);
                    $("#queryReason").val(data.data.queryReason);
                    $("#bussRemark").val(data.data.remark);
                }
            },
            error: function() {
                Feng.info("保存数据异常！");

            }
        });
    }
});
var saveOrUpdates = {
    validateFields: {
        queryDate:{
            validators: {
                notEmpty: {
                    message: '查询时间不能为空'
                }
            }
        },
        queryOrg:{
            validators: {
                notEmpty: {
                    message: '查询机构不能为空'
                }
            }
        },
        queryReason:{
            validators: {
                notEmpty: {
                    message: '查询原因不能为空'
                }
            }
        },
        bussRemark:{
            validators: {
                notEmpty: {
                    message: '备注不能为空'
                }
            }
        }
    }
}



function saveOrUpdate() {
    var id = $("#bussId").val();
    if(id==-1){
        id=null;
    }
    var applyId = $("#applyId").val();
    var type = $("#paramType").val();
    var queryDate =$("#queryDate").val();
    var queryOrg =$("#queryOrg").val();
    var queryReason =$("#queryReason").val();
    var remark =$("#bussRemark").val();
    if (!saveOrUpdates.validate()) {
        return
    }

    $.ajax({
        type: "POST",
        url: '/creditBussQueryRecord/saveOrUpdate',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':type,
            'id':id,
            'queryDate': queryDate,
            'queryOrg':queryOrg,
            'queryReason': queryReason,
            'remark':  remark
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
                window.parent.onlyShowBussQuery();
                parent.layer.close(window.parent.layerIndex);
            }else{
                Feng.info("保存数据失败！"+data.msg);
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });
    
}
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
saveOrUpdates.set = function(key, val) {
    this.saveOrUpdate[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
saveOrUpdates.get = function(key) {
    return $("#" + key).val();
}
saveOrUpdates.validate= function () {
    $('#addBussQuery').data("bootstrapValidator").resetForm();
    $('#addBussQuery').bootstrapValidator('validate');
    return $("#addBussQuery").data('bootstrapValidator').isValid();
}