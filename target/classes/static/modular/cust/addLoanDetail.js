
var saveOrUpdates = {
	validateFields:{
        loanOrg:{
            validators: {
                notEmpty: {
                    message: '贷款机构不能为空'
                }
            }
        },
        loanType:{
            validators: {
                notEmpty: {
                    message: '贷款类型不能为空'
                }
            }

        },
        loanBeginTime:{
            validators: {
                notEmpty: {
                    message: '贷款开始时间不能为空'
                }
            }
        },
        loanEndTime:{
            validators: {
                notEmpty: {
                    message: '贷款到期时间不能为空'
                }
            }
        },
        accountStatus:{
            validators: {
                notEmpty: {
                    message: '账户状态不能为空'
                }
            }
        },
        fiveClassStatus:{
            validators: {
                notEmpty: {
                    message: '五级分类不能为空'
                }
            }
        },
        curMonthDate:{
            validators: {
                notEmpty: {
                    message: '本次应还款日不能为空'
                }
            }
        },
        lastRepaymentDatge:{
            validators: {
                notEmpty: {
                    message: '最近一次还款日期不能为空'
                }
            }
        },
        repaymentInfo:{
            validators: {
                notEmpty: {
                    message: '两年前还款记录情况不能为空'
                }
            }
        },
        // loanRemark:{
        //     validators: {
        //         notEmpty: {
        //             message: '备注不能为空'
        //         }
        //     }
        // },
		loanAmount:{
			validators: {
				notEmpty: {
                    message: '金额不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		loanPeriod:{
			validators: {
				notEmpty: {
                    message: '期数不能为空'
                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		capitalAmount:{
			validators: {
				notEmpty: {
                    message: '金额不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		leftPeriod:{
			validators: {
				notEmpty: {
                    message: '期数不能为空'
                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		curMonthPredictAmount:{
			validators: {
				notEmpty: {
                    message: '金额不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		curMonthActuralAmount:{
			validators: {
				notEmpty: {
                    message: '金额不能为空'
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
                    message: '期数不能为空'
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
		},
		overdueM2Capital:{
			validators: {
				notEmpty: {
                    message: '本金不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		overdueM3Capital:{
			validators: {
				notEmpty: {
                    message: '本金不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		overdueM45Capital:{
			validators: {
				notEmpty: {
                    message: '本金不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		overdueM6Capital:{
			validators: {
				notEmpty: {
                    message: '本金不能为空'
                },
				regexp: {
	                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		// repaymentInfo:{
		// 	validators: {
         //        regexp: {
         //            regexp:/^[\u2E80-\u9FFF]+$/,
         //            message: '输入项只能为汉字'
         //        }
         //    }
		// }
	}
}

$(document).ready(function () {
	
	Feng.initValidator("addLoan", saveOrUpdates.validateFields);
	
    var id = $("#loanDetailId").val();
    if(id==-1){
        return;
    }else{
        $.ajax({
            type: "POST",
            url: '/creditLoanDetail/findById',
            dataType: 'json',
            data: {
                'id':id
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    $("#loanOrg").val(data.data.loanOrg);
                    $("#loanAmount").val(data.data.loanAmount);
                    $("#loanType").val(data.data.loanType);
                    $("#loanPeriod").val(data.data.loanPeriod);
                    $("#loanBeginTime").val(data.data.loanBeginTime==null?"":data.data.loanBeginTime.split(" ")[0]);
                    $("#loanEndTime").val(data.data.loanEndTime==null?"":data.data.loanEndTime.split(" ")[0]);
                    $("#accountStatus").val(data.data.accountStatus);
                    $("#fiveClassStatus").val(data.data.fiveClassStatus);
                    $("#capitalAmount").val(data.data.capitalAmount);
                    $("#leftPeriod").val(data.data.leftPeriod);
                    $("#curMonthPredictAmount").val(data.data.curMonthPredictAmount);
                    $("#curMonthDate").val(data.data.curMonthDate==null?"":data.data.curMonthDate.split(" ")[0]);
                    $("#curMonthActuralAmount").val(data.data.curMonthActuralAmount);
                    $("#lastRepaymentDatge").val(data.data.lastRepaymentDatge==null?"":data.data.lastRepaymentDatge.split(" ")[0]);
                    $("#curOverdueNum").val(data.data.curOverdueNum);
                    $("#curOverdueAmount").val(data.data.curOverdueAmount);
                    $("#overdueM2Capital").val(data.data.overdueM2Capital);
                    $("#overdueM3Capital").val(data.data.overdueM3Capital);
                    $("#overdueM45Capital").val(data.data.overdueM45Capital);
                    $("#overdueM6Capital").val(data.data.overdueM6Capital);
                    $("#repaymentInfo").val(data.data.repaymentInfo);
                    $("#loanRemark").val(data.data.remark);
                }
            },
            error: function() {
                Feng.info("保存数据异常！");

            }
        });
    }
});

function saveOrUpdate() {
    var id = $("#loanDetailId").val();
    if(id==-1){
        id=null;
    }
    var applyId = $("#applyId").val();
    var type = $("#paramType").val();
    var loanOrg =$("#loanOrg").val();
    var loanAmount =$("#loanAmount").val();
    var loanType =$("#loanType").val();
    var loanPeriod =$("#loanPeriod").val();
    var loanBeginTime =$("#loanBeginTime").val();
    var loanEndTime  =$("#loanEndTime").val();
    var accountStatus =$("#accountStatus").val();
    var fiveClassStatus  =$("#fiveClassStatus").val();
    var capitalAmount =$("#capitalAmount").val();
    var leftPeriod  =$("#leftPeriod").val();
    var curMonthPredictAmount=$("#curMonthPredictAmount").val();
    var curMonthDate =$("#curMonthDate").val();
    var curMonthActuralAmount=$("#curMonthActuralAmount").val();
    var lastRepaymentDatge =$("#lastRepaymentDatge").val();
    var curOverdueNum =$("#curOverdueNum").val();
    var curOverdueAmount =$("#curOverdueAmount").val();
    var overdueM2Capital =$("#overdueM2Capital").val();
    var overdueM3Capital =$("#overdueM3Capital").val();
    var overdueM45Capital =$("#overdueM45Capital").val();
    var overdueM6Capital =$("#overdueM6Capital").val();
    var repaymentInfo =$("#repaymentInfo").val();
    var remark =$("#loanRemark").val();
    
    if (!saveOrUpdates.validate()) {
    	return
    }
    
    $.ajax({
        type: "POST",
        url: '/creditLoanDetail/saveOrUpdate',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':type,
            'id':id,
            'loanOrg': loanOrg,
            'loanAmount':loanAmount,
            'loanType': loanType,
            'loanPeriod': loanPeriod,
            'loanBeginTime': loanBeginTime,
            'loanEndTime': loanEndTime,
            'accountStatus': accountStatus,
            'fiveClassStatus': fiveClassStatus,
            'capitalAmount':  capitalAmount,
            'leftPeriod':  leftPeriod,
            'curMonthPredictAmount':curMonthPredictAmount,
            'curMonthDate': curMonthDate,
            'curMonthActuralAmount':curMonthActuralAmount,
            'lastRepaymentDatge':   lastRepaymentDatge,
            'curOverdueNum':  curOverdueNum,
            'curOverdueAmount':  curOverdueAmount,
            'overdueM2Capital':  overdueM2Capital,
            'overdueM3Capital':  overdueM3Capital,
            'overdueM45Capital':  overdueM45Capital,
            'overdueM6Capital':  overdueM6Capital,
            'repaymentInfo': repaymentInfo,
            'remark':  remark
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
                window.parent.onlyShowLoanDetail();
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
    $('#addLoan').data("bootstrapValidator").resetForm();
    $('#addLoan').bootstrapValidator('validate');
    return $("#addLoan").data('bootstrapValidator').isValid();
}
