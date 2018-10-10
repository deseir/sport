
var saveOrUpdates = {
	validateFields:{
        cardOrg:{
            validators: {
                notEmpty: {
                    message: '发卡机构不能为空'
                },
            }
        },
        cardType:{
            validators: {
                notEmpty: {
                    message: '贷款类型不能为空'
                },
            }
        },
        accountStatus:{
            validators: {
                notEmpty: {
                    message: '账户状态不能为空'
                },
            }
        },
        cardRemark:{
            validators: {
                notEmpty: {
                    message: '备注不能为空'
                },
            }
        },
		cardAmount:{
			validators: {
				notEmpty: {
                    message: '额度不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		cardShareAmount:{
			validators: {
				notEmpty: {
                    message: '额度不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		usedAmount:{
			validators: {
				notEmpty: {
                    message: '额度不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		avgUsedAmount:{
			validators: {
				notEmpty: {
                    message: '额度不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		maxUsedAmount:{
			validators: {
				notEmpty: {
                    message: '最大额度不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		curOverdueNum:{
			validators: {
				notEmpty: {
                    message: '逾期数不能为空'
                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		curOverdueAmount:{
			validators: {
				notEmpty: {
                    message: '金额不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		}
	}
}

$(document).ready(function () {
	
	Feng.initValidator("addCard", saveOrUpdates.validateFields);
	
    var id = $("#cardDetailId").val();
    if(id==-1){
        return;
    }else{
        $.ajax({
            type: "POST",
            url: '/creditCardDetail/findById',
            dataType: 'json',
            data: {
                'id':id
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    $("#cardOrg").val(data.data.cardOrg);
                    $("#cardAmount").val(data.data.cardAmount);
                    $("#cardShareAmount").val(data.data.cardShareAmount);
                    $("#cardType").val(data.data.cardType);
                    $("#accountStatus").val(data.data.accountStatus);
                    $("#usedAmount").val(data.data.usedAmount);
                    $("#avgUsedAmount").val(data.data.avgUsedAmount);
                    $("#maxUsedAmount").val(data.data.maxUsedAmount);
                    $("#curOverdueNum").val(data.data.curOverdueNum);
                    $("#curOverdueAmount").val(data.data.curOverdueAmount);
                    $("#cardRemark").val(data.data.remark);
                }
            },
            error: function() {
                Feng.info("查询数据异常！");

            }
        });
    }
});

function saveOrUpdate() {
    var id = $("#cardDetailId").val();
    if(id==-1){
        id=null;
    }
    var applyId = $("#applyId").val();
    var type = $("#paramType").val();
    var cardOrg = $("#cardOrg").val();
    var cardAmount = $("#cardAmount").val();
    var cardShareAmount= $("#cardShareAmount").val();
    var cardType = $("#cardType").val();
    var accountStatus = $("#accountStatus").val();
    var usedAmount  = $("#usedAmount").val();
    var avgUsedAmount =  $("#avgUsedAmount").val();
    var maxUsedAmount = $("#maxUsedAmount").val();
    var curOverdueNum = $("#curOverdueNum").val();
    var curOverdueAmount=$("#curOverdueAmount").val();
    var remark = $("#cardRemark").val();
    
    if (!saveOrUpdates.validate()) {
    	return
    }
    
    $.ajax({
        type: "POST",
        url: '/creditCardDetail/saveOrUpdate',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':type,
            'id':id,
            'cardOrg': cardOrg,
            'cardAmount': cardAmount,
            'cardShareAmount': cardShareAmount,
            'cardType':cardType,
            'accountStatus':accountStatus,
            'usedAmount': usedAmount,
            'avgUsedAmount': avgUsedAmount,
            'maxUsedAmount': maxUsedAmount,
            'curOverdueNum': curOverdueNum,
            'curOverdueAmount':curOverdueAmount,
            'remark': remark
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
                window.parent.searchCardDetail();
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
    $('#addCard').data("bootstrapValidator").resetForm();
    $('#addCard').bootstrapValidator('validate');
    return $("#addCard").data('bootstrapValidator').isValid();
}

