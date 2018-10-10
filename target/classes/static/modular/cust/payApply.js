var PayApply = {
    payApplyData : {},
    validateFields: {
        custName: {
            validators: {
                notEmpty: {
                    message: '客户名称不能为空'
                },
                regexp: {
                    regexp: /^[\u2E80-\u9FFF]+$/,
                    message: '输入项只能为汉字'
                }
            }
        },
        custMobile: {
            validators: {
                notEmpty: {
                    message: '客户手机号不能为空'
                },
                regexp: {
                    regexp: /^1[34578]\d{9}$/,
                    message: '手机号格式非法或者手机号长度不是11位'
                }
            }
        },
        bankName: {
            validators: {
                notEmpty: {
                    message: '客户银行卡开户行不能为空'
                },
                regexp: {
                    regexp: /^[\u2E80-\u9FFF]+$/,
                    message: '输入项只能为汉字'
                },
                stringLength: {
                    max:10,
                    message: '最大只能输入10个汉字'
                }
            }
        },
        bankCardNo: {
            validators: {
                notEmpty: {
                    message: '客户银行卡号不能为空'
                },
                regexp: {
                    regexp:/^(\d{16}|\d{19})$/,
                    message: '银行卡号输入格式不正确'
                }
            }
        },
        percent:{
        	validators: {
                notEmpty: {
                    message: '百分比不能为空'
                },
                regexp: {
                    regexp:/^[0-9]*$/,
                    message:'输入项格式只能为阿拉伯数字'
                }
            }
        },
        custIdNo: {
            validators: {
                notEmpty: {
                    message: '客户身份证不能为空'
                },
                regexp: {
                    regexp: /(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                    message: '证件号只能为数字、末位可以为字母x且长度为18位'
                }
            }
        },
        totalAmount: {
            validators: {
                notEmpty: {
                    message: '借款总额不能为空'
                },
                regexp: {
                    regexp:/^([1-9]\d*(\.\d[1-9]?|\.\d*0)?|0\.[1-9][1-9]?|0\.0[1-9]|0)$/,
                    message: '输入项只能为大于或者等于0的数字，且最多保留两位小数'
                },
                stringLength: {
                    max:11,
                    message: '借款总额超出最大范围'
                }
            }
        },
        periodNum: {
            validators: {
                notEmpty: {
                    message: '借款期数不能为空'
                }
            }
        },
        receptionAmount: {
            validators: {
                // notEmpty: {
                //     message: '前台服务费基数不能为空'
                // },
                regexp: {
                    regexp:/^([1-9]\d*(\.\d[1-9]?|\.\d*0)?|0\.[1-9][1-9]?|0\.0[1-9]|0)$/,
                    message: '输入项只能为大于或者等于0的数字，且最多保留两位小数'
                },
                stringLength: {
                    max:5,
                    message: '前台服务费基数超出最大范围'
                }
            }
        },
        finalServiceFee: {
            validators: {
                notEmpty: {
                    message: '前台服务费不能为空'
                },
                regexp: {
                    regexp:/^([1-9]\d*(\.\d[1-9]?|\.\d*0)?|0\.[1-9][1-9]?|0\.0[1-9]|0)$/,
                    message: '输入项只能为大于或者等于0的数字，且最多保留两位小数'
                }
            }
        },
        transSource: {
            validators: {
                notEmpty: {
                    message: '业务来源不能为空'
                },
                regexp: {
                    regexp: /^[\u2E80-\u9FFFA-Za-z0-9]+$/,
                    message: '业务来源只能为汉字或字母或数字'
                },
                stringLength: {
                    max:10,
                    message: '最大只能输入10个汉字或字母或汉字'
                }
            }
        }
    }
};

PayApply.validate = function () {
    $('#qksq_form').data("bootstrapValidator").resetForm();
    $('#qksq_form').bootstrapValidator('validate');
    return $("#qksq_form").data('bootstrapValidator').isValid();
}

//公共提交操作
function todoSubmit() {
    var applyId = $("#applyId").val();
    var result = $("#checkResult").val();
    var resultTip = $("#checkResultDesc").val();
    var receptionDepart=$("#receptionDepart").val();
    var receptionManager=$("#receptionManager").val();
    var receptionAmount=$("#receptionAmount").val();
    var isReplaceCost=$("#isReplaceCost").val();
    var isPerCharge=$("#isPerCharge").val();
    var bankCardNo=$("#bankCardNo").val();
    var bankName=$("#bankName").val();
    var custMobile=$("#custMobile").val();
    var interfaceAddress = $("#interfaceAddress").val();
    //var uuid=$('#uuid').val();
//    if(result==""||result==null){
//        Feng.info("必须选择审核结果")
//        return false;
//    }
    
    if(!PayApply.validate()){
    	return false;
    }
    $.ajax({
        type: "POST",
        url: interfaceAddress,
        dataType: 'json',
        data: { applyId:applyId,
        	result:0,resultTip:"",
        	custMobile:custMobile,
        	bankCardNo:bankCardNo,bankName:bankName,
        	receptionDepart:receptionDepart,
        	receptionManager:receptionManager,
        	receptionAmount:receptionAmount,
        	isReplaceCost:isReplaceCost,
        	isPerCharge:isPerCharge},
        success: function(data) {
            var status=data.status;
            if(status==0) {
                Feng.info("提交审批意见成功！");
                window.location.href="/blackboard";
            }else {
                Feng.info("提交审批意见失败！ "+data.msg);
            }

        },
        error: function(data) {
            //me.error(data);
            Feng.info("提交审批意见异常！");
        }
    });

}
//跳转到我的待办列表
function backToDoWork() {
    window.location.href="/blackboard";
}

$(function(){
    if($("#serviceItem").val()==0){
        $("#moneyAmount").parent().parent().parent().hide();
        $("#percent").parent().parent().parent().show();
    }else{
        $("#moneyAmount").parent().parent().parent().show();
        $("#percent").parent().parent().parent().hide();
    }
	Feng.initValidator("qksq_form", PayApply.validateFields);
	var ts=$("#ts").val();//业务来源的id
	//给渠道来源绑定数据源
	$.ajax({
        type: "POST",
        url: '/channelInfo/findChannelId',
        dataType: 'json',
        data: {},
        success: function(data) {
            var status=data.status;
            if(status=='0'){
                $.each(data.data, function (i, val) {
                	if(ts==data.data[i].id){
                		$('#transSource').append("<option value ="+data.data[i].id+" selected=\"selected\">"+data.data[i].channelName+"</option>");
                	}else{
                		$('#transSource').append("<option value ="+data.data[i].id+">"+data.data[i].channelName+"</option>");
                	}
                });
            }else{
                Feng.error("查询渠道名称失败!"+ data.msg);
            }
        },
        error: function(data) {
            Feng.info("查询渠道名称异常！");
        }
    });
	
	//给银行卡输入框绑定识别开户行事件
	$("#bankCardNo").on("blur",function(){
		var bankCardNo=$('#bankCardNo').val();
	    //提交信息
	    $.ajax({
	        type: "POST",
	        url: '/payApply/selectBankName',
	        dataType: 'json',
	        data: {bankCardNo:bankCardNo},
	        success: function(resp) {

	            var status=resp.resultCode;
	            if(status==null){
	                Feng.error("该卡片不支持放款");
	            }else if(status!="0000"){
	                Feng.error(resp.message);
	            } else{
	                var data=resp.data;
	                var bankName=data.bankName;
	                var bankCode=data.bankCode;
	                $('#bankName').val(bankName);
	            }

	        },
	        error: function(data) {
	            Feng.error("获取银行名称异常！")

	        }
	    });
	});
})
/**
 * 是否代收改变
 */
$("#isReplaceCost").change(function () {
    if($("#isReplaceCost").val()==1){
        $("#serviceItem").removeAttr("disabled");
        $("#percent").removeAttr("disabled");
        $("#moneyAmount").removeAttr("disabled");
    }else{
        $("#serviceItem").attr("disabled","true");
        $("#percent").attr("disabled","true");
        $("#moneyAmount").attr("disabled","true");
    }
});
/**
 * 服务费方式改变
 */
$("#serviceItem").change(function () {
    if($("#serviceItem").val()==0&&$("#totalAmount").val()!=""&&$("#percent").val()!=""){
        $("#receptionAmount").val($("#totalAmount").val()*$("#percent").val()/100);
    }else if($("#serviceItem").val()==1&&$("#totalAmount").val()!=""&&$("#moneyAmount").val()!=""){
        $("#receptionAmount").val($("#moneyAmount").val());
    }
    if($("#serviceItem").val()==0){
        $("#percent").parent().parent().parent().show();
        $("#moneyAmount").parent().parent().parent().hide();
    }else{
        $("#percent").parent().parent().parent().hide();
        $("#moneyAmount").parent().parent().parent().show();
    }
});
/**
 * 金额改变
 */
$("#moneyAmount").blur(function () {
    if($("#serviceItem").val()==0&&$("#totalAmount").val()!=""&&$("#percent").val()!=""){
        $("#receptionAmount").val($("#percent").val());
    }else if($("#serviceItem").val()==1&&$("#totalAmount").val()!=""&&$("#moneyAmount").val()!=""){
        $("#receptionAmount").val($("#moneyAmount").val());
    }
});
/**
 * 百分比改变
 */
$("#percent").blur(function () {
    if($("#serviceItem").val()==0&&$("#totalAmount").val()!=""&&$("#percent").val()!=""){
        $("#receptionAmount").val($("#totalAmount").val()*$("#percent").val()/100);
    }else if($("#serviceItem").val()==1&&$("#totalAmount").val()!=""&&$("#moneyAmount").val()!=""){
        $("#receptionAmount").val($("#totalAmount").val()*$("#moneyAmount").val()/100);
    }
});