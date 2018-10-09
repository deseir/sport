
$(document).ready(function () {
	Feng.initValidator("addFinance", saveOrUpdates.validateFields);
    var id = $("#financeId").val();
    if(id==-1){
        return;
    }else{
        $.ajax({
            type: "POST",
            url: '/custFinanceInfo/findById',
            dataType: 'json',
            data: {
                'id':id
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    $("#finType").val(data.data.finType);
                    $("#property").val(data.data.property);
                    $("#status").val(data.data.status);
                    $("#num").val(data.data.num);
                    $("#finaceRemark").val(data.data.remark);
                }
            },
            error: function() {
                Feng.info("查询数据异常！");

            }
        });
    }
});
var saveOrUpdates = {
	 validateFields:{
		 // finType:{
			//  validators: {
	      //           notEmpty: {
	      //               message: '类型选项不能为空'
	      //           }
	      //       }
		 // },
		 // property:{
			//  validators: {
          //       notEmpty: {
          //           message: '性质选项不能为空'
          //       }
	      //       }
		 // },
		 // status:{
			//  validators: {
          //       notEmpty: {
          //           message: '状态选项不能为空'
          //       }
          //   }
		 },
		 num:{
			 validators: {
                // notEmpty: {
                //     message: '数量不能为空'
                // },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式只能为阿拉伯数字'
	            }
			}
		 }
	 }

function saveOrUpdate() {
    var id = $("#financeId").val();
    if(id==-1){
        id=null;
    }
    var applyId = $("#applyId").val();
    var pamType =$("#paramType").val();
    var finType = $("#finType").val();
    var property = $("#property").val();
    var status = $("#status").val();
    var num = $("#num").val();
    var remark = $("#finaceRemark").val();
    
    if (!saveOrUpdates.validate()) {
    	return
    }

    $.ajax({
        type: "POST",
        url: '/custFinanceInfo/saveOrUpdate',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType,
            'id' : id,
            'finType' : finType,
            'property' : property,
            'status' : status,
            'num' : num,
            'remark' : remark
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
                window.parent.searchFinace();
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
    $('#addFinance').data("bootstrapValidator").resetForm();
    $('#addFinance').bootstrapValidator('validate');
    return $("#addFinance").data('bootstrapValidator').isValid();
}

function setProperty() {
    var finType = $("#finType").val();
    if(finType==1){//车辆
        $("#showProperty").hide();
    }else{
        $("#showProperty").show();
    }
}