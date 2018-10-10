/**
 * 还款计算器初始化
 */


$(document).ready(function(){
    SelectchannelName();
    firtGoBackHTML();
    Feng.initValidator("menu-item1", custNqData.validateFields);
    Feng.initValidator("menu-item2", custNqData.validateFields);
    Feng.initValidator("menu-item3", custNqData.validateFields);
    Feng.initValidator("menu-item4", custNqData.validateFields);
    Feng.initValidator("menu-item5", custNqData.validateFields);
    Feng.initValidator("menu-item6", custNqData.validateFields);
    Feng.initValidator("addCust", custNqData.validateFields);
    Feng.initValidator("addCarTransferInfo", custNqData.validateFields);
    Feng.initValidator("newAddCarMort", custNqData.validateFields);
});

var custNqData = {
	 validateFields:{
		 applyAmount: {
			 validators: {
                notEmpty: {
                    message: '申请额度不能为空'
                },
                regexp: {
//                    regexp: /^([5-9]|[1-5][0-9])$/,
                	regexp: /^([5-9]|[1-5][0-9]|60)$/,
                    message: '输入金额只能5-60之间'
                }
            }
		 },
//		 loanUsage6:{
//			 validators: {
//                notEmpty: {
//                    message: '贷款用途不能为空'
//                }
//            }
//		 },
		 name:{
			 validators: {
                notEmpty: {
                    message: '姓名不能为空'
                },
                regexp: {
                    regexp:/^[\u2E80-\u9FFF]+$/,
                    message: '输入项只能为汉字'
                },
                stringLength: {
                    max:20,
                    message: '最大只能输入20个汉字'
                }
            }
		 },
		 mobile:{
			 validators: {
                notEmpty: {
                    message: '手机号不能为空'
                },
                regexp: {
                    regexp: /^1[345678]\d{9}$/,
                    message: '手机号格式不正确'
                }
            }
		 },
		 nation:{
			 validators: {
                notEmpty: {
                    message: '民族不能为空'
                },
                regexp: {
                    regexp: /^[\u2E80-\u9FFF]+$/,
                    message: '输入项只能为汉字'
                },
                stringLength: {
                    max:20,
                    message: '最大只能输入20个汉字'
                }
            }
		 },
		 signOrg:{
			 validators: {
	                notEmpty: {
	                    message: '签发机关不能为空'
	                },
	                regexp: {
	                    regexp: /^[\u2E80-\u9FFF]+$/,
	                    message: '输入项只能为汉字'
	                }
	            }
		 },
		 certId:{
			 validators: {
                notEmpty: {
                    message: '身份证不能为空'
                },
                regexp: {
                    regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                    message: '身份证格式不正确'
                }
            }
		 },
		spousePhone1:{
			validators: {
                notEmpty: {
                    message: '手机号不能为空'
                },
				regexp: {
                    regexp: /^1[345678]\d{9}$/,
                    message: '手机号格式不正确'
                }
            }
		},
		contractName1:{
			validators: {
                notEmpty: {
                    message: '紧急联系人1姓名不能为空'
                },
                regexp: {
                    regexp: /^[\u2E80-\u9FFF]+$/,
                    message: '输入项只能为汉字'
                },
                stringLength: {
                    max:20,
                    message: '最大只能输入20个汉字'
                }
            }
		},
		contractPhone1:{
			validators: {
                notEmpty: {
                    message: '紧急联系人1电话不能为空'
                },
				regexp: {
                    regexp: /^1[345678]\d{9}$/,
                    message: '手机号格式不正确'
                }
            }
		},
		contractRelation1:{
			validators: {
                notEmpty: {
                    message: '关系1不能为空'
                },
				regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            },
	            stringLength: {
	                max:20,
	                message: '最大只能输入20个汉字'
	            }
			}
		},
		contractName2:{
			validators: {
                notEmpty: {
                    message: '紧急联系人2姓名不能为空'
                },
				regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            },
	            stringLength: {
	                max:20,
	                message: '最大只能输入20个汉字'
	            }
			}
		},
		contractPhone2:{
			validators: {
                notEmpty: {
                    message: '紧急联系人2电话不能为空'
                },
				regexp: {
                    regexp: /^1[345678]\d{9}$/,
                    message: '手机号格式不正确'
                }
            }
		},
		contractRelation2:{
			validators: {
                notEmpty: {
                    message: '关系2不能为空'
                },
				regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            },
	            stringLength: {
	                max:20,
	                message: '最大只能输入20个汉字'
	            }
			}
		},
		contractName3:{
			validators: {
                notEmpty: {
                    message: '紧急联系人3姓名不能为空'
                },
				regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            },
	            stringLength: {
	                max:20,
	                message: '最大只能输入20个汉字'
	            }
			}
		},
		contractPhone3:{
			validators: {
                notEmpty: {
                    message: '紧急联系人3电话不能为空'
                },
				regexp: {
                    regexp: /^1[345678]\d{9}$/,
                    message: '手机号格式不正确'
                }
            }
		},
		contractRelation3:{
			validators: {
                notEmpty: {
                    message: '关系3不能为空'
                },
				regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            },
	            stringLength: {
	                max:20,
	                message: '最大只能输入20个汉字'
	            }
			}
		},
		masterName:{
			validators: {
				notEmpty: {
                    message: '户主姓名不能为空'
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
		childNumber:{
			validators: {
                notEmpty: {
                    message: '子女人数不能为空'
                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入格式只能为阿拉伯数字'
	            }
			}
		},
		masterCertId:{
			validators: {
                notEmpty: {
                    message: '身份证不能为空'
                },
                regexp: {
                    regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                    message: '身份证格式不正确'
                }
            }
		},
		spouseName:{
			validators: {
				notEmpty: {
                    message: '姓名不能为空'
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
		divorceName:{
			validators: {
				notEmpty: {
                    message: '姓名不能为空'
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
		divorceCertId:{
			validators: {
                notEmpty: {
                    message: '身份证不能为空'
                },
                regexp: {
                    regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                    message: '身份证格式不正确'
                }
            }
		},
		spouseCertId:{
			validators: {
                notEmpty: {
                    message: '身份证不能为空'
                },
                regexp: {
                    regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                    message: '身份证格式不正确'
                }
            }
		},
		companyName:{
			validators: {
				notEmpty: {
                    message: '名称不能为空'
                },
				regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            }
			}
		},
		companyAddress:{
			validators: {
				notEmpty: {
                    message: '地址不能为空'
                },
				regexp: {
	                regexp: /^(\d|[\u4e00-\u9fa5]){5,}$/,
	                message: '输入项只能为大于5位的汉字或汉字与数字的组合'
	            }
			}
		},
		companyTel:{
			validators: {
				notEmpty: {
                    message: '电话不能为空'
                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		workAge:{
			validators: {
				notEmpty: {
                    message: '年限不能为空'
                },
				regexp: {
	                regexp:  /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式只能为阿拉伯数字允许保留一位小数'
	            }
			}
		},
		monthIncome:{
			validators: {
				notEmpty: {
                    message: '收入不能为空'
                },
				regexp: {
					regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式只能为阿拉伯数字允许保留一位小数'
	            }
			}
		},
		spouseCompanyName:{
			validators: {
//				notEmpty: {
//                    message: '地址不能为空'
//                },
				regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            }
			}
		},
		spouseCompanyTel:{
			validators: {
//				notEmpty: {
//                    message: '电话不能为空'
//                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式不正确'
	            }
			}
		},
		spouseWorkAge:{
			validators: {
//				notEmpty: {
//                    message: '年限不能为空'
//                },
				regexp: {
	                regexp:  /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式只能为阿拉伯数字允许保留一位小数'
	            }
			}
		},
         floatProp:{
             validators: {
                 regexp: {
                     regexp:  /^[0-9]+([.][0-9]{1}){0,1}$/,
                     message: '输入项格式只能为阿拉伯数字允许保留一位小数'
                 }
             }
         },
         totalAmount:{
             validators: {
                 regexp: {
                     regexp:  /^[0-9]+([.][0-9]{1}){0,1}$/,
                     message: '输入项格式只能为阿拉伯数字允许保留一位小数'
                 }
             }
         },
         vehicleTax:{
             validators: {
                 regexp: {
                     regexp:  /^[0-9]+([.][0-9]{1}){0,1}$/,
                     message: '输入项格式只能为阿拉伯数字允许保留一位小数'
                 }
             }
         },
         totalAmount1:{
             validators: {
                 regexp: {
                     regexp:  /^[0-9]+([.][0-9]{1}){0,1}$/,
                     message: '输入项格式只能为阿拉伯数字允许保留一位小数'
                 }
             }
         },
         floatProp1:{
             validators: {
                 regexp: {
                     regexp:  /^[0-9]+([.][0-9]{1}){0,1}$/,
                     message: '输入项格式只能为阿拉伯数字允许保留一位小数'
                 }
             }
         },
		spouseMonthIncome:{
			validators: {
//				notEmpty: {
//                    message: '收入不能为空'
//                },
				regexp: {
	                regexp:  /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式只能为阿拉伯数字允许保留一位小数'
	            }
			}
		},
		displacement:{
			validators: {
				notEmpty: {
                    message: '排量不能为空'
                },
				regexp: {
	                regexp:  /\b\d{4}\b/,
	                message: '输入项格式只能为4位阿拉伯数字'
	            }
			}
		},
		familyBookSubInfoName1:{
			validators: {
//				notEmpty: {
//                    message: '姓名不能为空'
//                },
				regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            }
			}
		},
		familyBookSubInfoCertId1:{
			validators: {
//                notEmpty: {
//                    message: '身份证不能为空'
//                },
                regexp: {
                    regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                    message: '身份证格式不正确'
                }
            }
		},
		familyBookSubInfoRelationship1:{
			validators: {
//				notEmpty: {
//                    message: '关系不能为空'
//                },
				regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            }
			}
		},
		carNum:{
			validators: {
				notEmpty: {
                    message: '车牌号不能为空'
                },
				regexp: {
	                regexp: /^[京津沪渝翼豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/,
	                message: '车牌号格式只能为1个汉字+1个大写字母+5位数的阿拉伯数字或大写字母的组合'
	            }
			}
		},
		engineNo:{
			validators: {
				notEmpty: {
                    message: '发动机号不能为空'
                },
				regexp: {
	                regexp:/^[\dA-Z]{7,8}$/,
	                message: '发动机格式为7~8位大写字母与阿拉伯数字的组合'
	            }
			}
		},
		driverRemark:{
			validators: {
//				notEmpty: {
//                    message: '备注不能为空'
//                },
				regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            }
			}
		},
		totalNum:{
			validators: {
				notEmpty: {
                    message: '累计违章次数不能为空'
                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式只能为阿拉伯数字'
	            }
			}
		},
		totalMoney:{
			validators: {
				notEmpty: {
                    message: '罚款不能为空'
                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式只能为阿拉伯数字'
	            }
			}
		},
		totalValue:{
			validators: {
				notEmpty: {
                    message: '累计扣分不能为空'
                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式只能为阿拉伯数字'
	            }
			}
		},
		totalFullNum:{
			validators: {
				notEmpty: {
                    message: '累计扣12分次数不能为空'
                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式只能为阿拉伯数字'
	            }
			}
		},
		carTransferInfoName:{
			validators: {
				notEmpty: {
                    message: '姓名不能为空'
                },
				regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            }
			}
		},
		carTransferInfoCertId:{
			validators: {
                notEmpty: {
                    message: '身份证不能为空'
                },
                regexp: {
                    regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                    message: '身份证格式不正确'
                }
            }
		},
		custName:{
			validators: {
                notEmpty: {
                    message: '名称不能为空'
                },
                regexp: {
	                regexp: /^[\u2E80-\u9FFF]+$/,
	                message: '输入项只能为汉字'
	            }
            }
		},
		custIdNo:{
			validators: {
                notEmpty: {
                    message: '身份证不能为空'
                },
                regexp: {
                    regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                    message: '身份证格式不正确'
                }
            }
		},
		vin:{
			validators: {
                notEmpty: {
                    message: 'VIN码不能为空'
                },
            }
		},



         idBackPhotoUrlmImg:{
             validators: {
                 notEmpty: {
                     message: '身份证反面照片必须上传'
                 },
             }
		 },
         idFrontPhotoUrlmImg:{
             validators: {
                 notEmpty: {
                     message: '身份证正面照片必须上传'
                 },
             }
		 },
         marryStatus:{
             validators: {
                 notEmpty: {
                     message: "婚姻状况不能为空"
                 },
             }
		 },
         marryDate:{
             validators: {
                 notEmpty: {
                     message: "婚姻登记日期不能为空"
                 },
             }
		 },
         divorceDate:{
             validators: {
                 notEmpty: {
                     message: "离婚登记日期日期不能为空"
                 },
             }
		 },
         spouseSignOrg:{
             validators: {
                 notEmpty: {
                     message: "身份证签发机关不能为空"
                 },
             }
		 },
         spousevalidateBegin:{
             validators: {
                 notEmpty: {
                     message: "身份证有效期不能为空"
                 },
             }
		 },
         driverNo:{
             validators: {
                 notEmpty: {
                     message: '驾驶证档案编号不能为空'
                 },
                 regexp: {
                     regexp: /^[0-9]*$/,
                     message: '输入项格式只能为阿拉伯数字'
                 }
             }
         },
         permitType:{
             validators: {
                 notEmpty: {
                     message: '准驾车型不能为空'
                 },
             }
         },

		 
	 }
}

/**
 * 勾选其他loanUsage5事件
 */
$("#loanUsage5").change(function () {
    if($("#loanUsage5").is(':checked')){
        $("#loanUsage6").show();
    }else{
        $("#loanUsage6").hide();
    }
});
/**
 * 勾选其他childAdult1事件
 */
$("#childAdult1").change(function () {
    if($("#childAdult1").is(':checked')){
        $("#childNum").hide();
        $("#childAdult2").attr('disabled',true);
        $("#childAdult3").attr('disabled',true);
        obj=document.getElementsByName("childAdult");
        for(k in obj){
                if(parseInt(obj[k].value)!=parseInt(1)){
                    obj[k].checked=false;
                }
        }
    }else{
        $("#childNum").show();
        $("#childAdult2").attr('disabled',false);
        $("#childAdult3").attr('disabled',false);
    }
});
//申请人来源勾选事件
$("#incomeType4").change(function () {
    if($("#incomeType4").is(':checked')){
        $("#workerInfo").hide();
    }else{
        $("#workerInfo").show();
    }
});
$("#incomeType5").change(function () {
    if($("#incomeType5").is(':checked')){
        $("#workerInfo").hide();
    }else{
        $("#workerInfo").show();
    }
});
//配偶收入来源勾选事件
$("#spouseIncomeType4").change(function () {
    if($("#spouseIncomeType4").is(':checked')){
        $("#spouseWorkInfo").hide();
    }else{
        $("#spouseWorkInfo").show();
    }
});
$("#spouseIncomeType5").change(function () {
    if($("#spouseIncomeType5").is(':checked')){
        $("#spouseWorkInfo").hide();
    }else{
        $("#spouseWorkInfo").show();
    }
});

$("#spouseIncomeType6").change(function () {
    if($("#spouseIncomeType6").is(':checked')){
    	 var companyName = $("#companyName").val();
    	 var companyType = $("#companyType").val();
    	 var companyAddress = $("#companyAddress").val();
    	 var companyTel = $("#companyTel").val();
    	 if(companyName==""){
    		 $("#spouseCompanyName").val(companyName);
        	 $("#spouseCompanyName").attr('disabled',false);
    	 }else{
    		 $("#spouseCompanyName").val(companyName);
	    	 $("#spouseCompanyName").attr('disabled',true);
    	 }
    	 if(companyType==""){
    		 $("#spouseCompanyType").val(companyType);
        	 $("#spouseCompanyType").attr('disabled',false);
    	 }else{
    		 $("#spouseCompanyType").val(companyType);
	    	 $("#spouseCompanyType").attr('disabled',true);
    	 }
    	 if(companyAddress==""){
        	 $("#spouseCompanyAddress").val(companyAddress);
        	 $("#spouseCompanyAddress").attr('disabled',false);
    	 }else{
	    	 $("#spouseCompanyAddress").val(companyAddress);
	    	 $("#spouseCompanyAddress").attr('disabled',true);
    	 }
    	 if(companyTel==""){
        	 $("#spouseCompanyTel").val(companyTel);
        	 $("#spouseCompanyTel").attr('disabled',false);
    	 }else{
	    	 $("#spouseCompanyTel").val(companyTel);
	    	 $("#spouseCompanyTel").attr('disabled',true);
    	 }
    }else{
    	$("#spouseCompanyName").attr('disabled',false);
    	$("#spouseCompanyType").attr('disabled',false);
    	$("#spouseCompanyAddress").attr('disabled',false);
    	$("#spouseCompanyTel").attr('disabled',false);
    }
});


/**
 * 校验申请额度5-60
 */
function SelectchannelName(){
    if($("#channelId").val()==null||$("#channelId").val()==undefined){
        //查询渠道名称信息
        $.ajax({
            type: "POST",
            url: '/channelInfo/findChannelId',
            dataType: 'json',
            /**
             * 这里用同步方式
             */
            async:false,
            data: {},
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                    $.each(data.data, function (i, val) {

                        $('#channelId').append("<option value ="+data.data[i].id+">"+data.data[i].channelName+"</option>");
                    });
                }else{
                    Feng.error("查询渠道名称失败!"+ data.msg);
                }
            },
            error: function(data) {
                Feng.info("查询渠道名称异常！");
            }
        });
    }
}

/**
 * 整个页面保存功能
 * @param data
 */

//var custNqData = {
//    saveData : {},
//    validateFields: {
//    }
//};

/**
 * 清除数据
 */
custNqData.clearData = function() {
    this.custNqData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
custNqData.set = function(key, val) {
    this.custNqData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
custNqData.get = function(key) {
    return $("#" + key).val();
}

custNqData.validate1 = function () {
    $('#menu-item1').data("bootstrapValidator").resetForm();
    $('#menu-item1').bootstrapValidator('validate');
    return $("#menu-item1").data('bootstrapValidator').isValid();
}
custNqData.validate2 = function () {
    $('#menu-item2').data("bootstrapValidator").resetForm();
    $('#menu-item2').bootstrapValidator('validate');
    return $("#menu-item2").data('bootstrapValidator').isValid();
}
custNqData.validate3 = function () {
    $('#menu-item3').data("bootstrapValidator").resetForm();
    $('#menu-item3').bootstrapValidator('validate');
    return $("#menu-item3").data('bootstrapValidator').isValid();
}
custNqData.validate4 = function () {
    $('#menu-item4').data("bootstrapValidator").resetForm();
    $('#menu-item4').bootstrapValidator('validate');
    return $("#menu-item4").data('bootstrapValidator').isValid();
}
custNqData.validate5 = function () {
    $('#menu-item5').data("bootstrapValidator").resetForm();
    $('#menu-item5').bootstrapValidator('validate');
    return $("#menu-item5").data('bootstrapValidator').isValid();
}
custNqData.validate6 = function () {
    $('#menu-item6').data("bootstrapValidator").resetForm();
    $('#menu-item6').bootstrapValidator('validate');
    return $("#menu-item6").data('bootstrapValidator').isValid();
}
custNqData.validate7 = function () {
    $('#addCust').data("bootstrapValidator").resetForm();
    $('#addCust').bootstrapValidator('validate');
    return $("#addCust").data('bootstrapValidator').isValid();
}
custNqData.validate8 = function () {
    $('#addCarTransferInfo').data("bootstrapValidator").resetForm();
    $('#addCarTransferInfo').bootstrapValidator('validate');
    return $("#addCarTransferInfo").data('bootstrapValidator').isValid();
}
custNqData.validate9 = function () {
    $('#newAddCarMort').data("bootstrapValidator").resetForm();
    $('#newAddCarMort').bootstrapValidator('validate');
    return $("#newAddCarMort").data('bootstrapValidator').isValid();
}
/*$(function (flag) {
    Feng.initValidator("menu-item2", custNqData.validateFields);
});
$(function (flag) {
    Feng.initValidator("menu-item1", custNqData.validateFields);
});*/

//var reg = /^([5-9]|[1-5][0-9])$/; //金额正则校验
//var regUserName = /^[\u4e00-\u9fa5]{0,10}$/;   //姓名为10位字符汉字校验
//var regPhone = /^1[345678]\d{9}$/;  //手机号校验
//var regCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;   //身份证校验
custNqData.collectData = function(flag) {
    if(flag == 1){
        //基本借款信息保存
    	var amount = $("#applyAmount").val();
        var leftamount = $("#leftAmount").val();
        if(leftamount<5){
            Feng.info("没有剩余申请金额！");
            return false;
        }
    	else if(amount>leftamount&&leftamount>5){
            Feng.info("申请金额只能在5~"+leftamount+"之间！");
            return false;
        }

        var loanUsagesum=0;
        var loanUsageOther="";
        if (!custNqData.validate1()) {
 	    	return false
 	    }

        if($(".loanUsage:checked").length==0){
            Feng.info("请至少选一项贷款用途")
			return false
        }
        uurl = "/applyInfo/saveOrUpdateApplyInfo";
        obj=document.getElementsByName("loanUsage");
        for(k in obj){
            if(obj[k].checked){
                loanUsagesum = loanUsagesum|parseInt(obj[k].value);
            }
        }
        if(parseInt(loanUsagesum&16)==parseInt(16)){
            loanUsageOther=$("#loanUsage6").val();
        }
        this.set('channelId').set('applyId').set('productType').set('repaymentType').set('partnerKnow').set('applyAmount')
            .set('applyPeriod').set('isCollection').set('serviceItem').set('percent').set('moneyAmount').set('serviceCharge').set('feeInstallment')
        this.custNqData['loanUsage'] = loanUsagesum;
        this.custNqData['loanUsageOther'] = loanUsageOther;
    }else if(flag == 2){
        //保存身份证信息
    	var userName = $("#name").val();
    	var phone = $("#mobile").val();
    	var card = $("#certId").val();
    	var spousePhone1 = $("#spousePhone1").val();
    	var contractName1 = $("#contractName1").val();
    	var contractPhone1 = $("#contractPhone1").val();
    	var contractName2 = $("#contractName2").val();
    	var contractPhone2 = $("#contractPhone2").val();
    	var contractName3 = $("#contractName3").val();
    	var contractPhone3 = $("#contractPhone3").val();
    	var contractRelation1 = $("#contractRelation1").val();
    	var nation = $("#nation").val();
    	var contractRelation2 = $("#contractRelation2").val();
    	var contractRelation3 = $("#contractRelation3").val();
        var childNum = $("#childNumber").val();
    	if (!custNqData.validate2()) {
  	    	return false
  	    }
    	
        var fileObj = document.getElementById("idFrontPhotoUrl1").files[0];
        if(fileObj!=undefined){
            var fileFrontUrl = this.uploadFile(fileObj);
            $("#idFrontPhotoUrl").val(fileFrontUrl);
		}
        fileObj = document.getElementById("idBackPhotoUrl1").files[0];
        if(fileObj!=undefined){
            var fileBackUrl = this.uploadFile(fileObj);
            $("#idBackPhotoUrl").val(fileBackUrl);
		}
        
        //身份证基本信息
        uurl ="/cust/custInfoAdd";
       
        var childAdultsum=0;
        obj=document.getElementsByName("childAdult");
        for(k in obj){
            if(obj[k].checked){
                childAdultsum = childAdultsum|parseInt(obj[k].value);
            }
        }
        var liveType=0;
        obj=document.getElementsByName("liveType");
        for(k in obj){
            if(obj[k].checked){
                liveType = liveType|parseInt(obj[k].value);
            }
        }
        var togetherLive=0;
        obj=document.getElementsByName("togetherLive");
        for(k in obj){
            if(obj[k].checked){
                togetherLive = togetherLive|parseInt(obj[k].value);
            }
        }
        this.set('name').set('mobile').set('sex').set('nation').set('birthday').set('validateBegin')
            .set('validateEnd').set('signOrg').set('education').set('idFrontPhotoUrl').set('idBackPhotoUrl').set('certId').set('liveAddress')
            .set('contractName1') .set('contractName2') .set('contractName3') .set('contractPhone1') .set('contractPhone2')
            .set('contractPhone3') .set('contractRelation1') .set('contractRelation2') .set('contractRelation3');
        this.custNqData['applyId'] = $("#applyId").val();
        this.custNqData['childAdult'] =childAdultsum;
        this.custNqData['childNum'] =childNum;
        this.custNqData['liveType'] =liveType;
        this.custNqData['togetherLive'] =togetherLive;
        this.custNqData['spouseName'] =$("#spouseName1").val();
        this.custNqData['spousePhone'] =$("#spousePhone1").val();
    }else if(flag == 3){//户口本信息
      /*  var fileObj = document.getElementById("bookPhotoUrl1").files[0];
        var fileFrontUrl = this.uploadFile(fileObj);
        $("#bookPhotoUrl").val(fileFrontUrl);*/
    	
    	var masterName = $("#masterName").val();
    	var masterCertId = $("#masterCertId").val();
    	
    	if (!custNqData.validate3()) {
   	    	return false
   	    }
    	
        var fileObj = document.getElementById("firstPagePhotoUrl1").files[0];
        if(fileObj!=undefined) {
            var fileFrontUrl = this.uploadFile(fileObj);
            $("#firstPagePhotoUrl").val(fileFrontUrl);
        }
        var familyBookSubInfolist=new Array();
        
        
        $("#newAddFamilyBookSubInfoTr tr").each(function () {
            var sex;
            if($(this)[0].cells[4].innerText=='男'){
                sex=0;
            }else{
                sex=1;
            }
                var FamilyBookSubInfo={
                    id:$(this)[0].cells[0].innerText,
                    relationship:$(this)[0].cells[1].innerText,
                    name:$(this)[0].cells[2].innerText,
                    sex:sex,
                    certId:$(this)[0].cells[3].innerText,
                    bookPhotoUrl:$(this)[0].cells[7].innerText
                };
                familyBookSubInfolist.push(FamilyBookSubInfo);
        });
      /*  familyBookSubInfolist.push(FamilyBookSubInfos);*/
        uurl ="/familyBookInfo/familyBookInfoAdd";
        this.set('relationship').set('masterName').set('masterSex').set('firstPagePhotoUrl').set('familyBookInfoId');
        this.custNqData['applyId'] = $("#applyId").val();
        this.custNqData['certId'] = $("#masterCertId").val();
        this.custNqData['familyBookSubInfolist'] = familyBookSubInfolist;

    }else if(flag == 4){//婚姻信息
    	if (!custNqData.validate4()) {
	    	return false
	    }
    	uurl ="/marryInfo/addSaveOrUpdatemarryInfo";
        
        var spouseName = $("#spouseName").val();
        var spouseCertId = $("#spouseCertId").val();
        
        if($("#marryStatus").val()=="0"||$("#marryStatus").val()=="2"){//已婚或再婚
            var fileObj = document.getElementById("spouseidFrontPhotoUrl1").files[0];
            if(fileObj!=undefined) {
                var fileFrontUrl = this.uploadFile(fileObj);
                $("#spouseidFrontPhotoUrl").val(fileFrontUrl);
            }
            var fileObj1 = document.getElementById("marryPhotoUrl1").files[0];
            if(fileObj!=undefined) {
                var fileFrontUrl1 = this.uploadFile(fileObj1);
                $("#marryPhotoUrl").val(fileFrontUrl1);
            }
            var fileObj2 = document.getElementById("spouseidBackPhotoUrl1").files[0];
            if(fileObj!=undefined) {
                var fileFrontUrl2 = this.uploadFile(fileObj2);
                $("#spouseidBackPhotoUrl").val(fileFrontUrl2);
            }
            this.set('marryStatus').set('spouseSex').set('marryDate').set('spousevalidateBegin').set('spouseName').set('spouseCertId')
                .set('spousevalidateEnd').set('spouseSignOrg').set('spouseidFrontPhotoUrl').set('marryPhotoUrl').set('spouseidBackPhotoUrl');
            this.custNqData['applyId'] = $("#applyId").val();
            this.custNqData['marryInfoId'] = $("#marryInfoId").val();
        }else if($("#marryStatus").val()=="1"){//未婚
            this.set('marryInfoId').set('marryStatus');
            this.custNqData['applyId'] = $("#applyId").val();
        }else if($("#marryStatus").val()=="3"){//离异
            var fileObj = document.getElementById("divorcePhotoUrl1").files[0];
            if(fileObj!=undefined) {
                var fileFrontUrl = this.uploadFile(fileObj);
                $("#divorcePhotoUrl").val(fileFrontUrl);
            }
            this.set('divorceSex').set('divorceDate').set('divorceCertId').set('divorceName').set('divorcePhotoUrl').set('marryStatus');
            this.custNqData['applyId'] = $("#applyId").val();
            this.custNqData['marryInfoId'] = $("#marryInfoId").val();
        }else if($("#marryStatus").val()=="4"){//丧偶
            var fileObj = document.getElementById("deathCertPhotoUrl1").files[0];
            if(fileObj!=undefined) {
                var fileFrontUrl = this.uploadFile(fileObj);
                $("#deathCertPhotoUrl").val(fileFrontUrl);
            }
            this.set('spouseSex').set('marryStatus').set('marryDate').set('spouseCertId').set('spouseName').set('marryStatus').set('deathCertPhotoUrl');
            this.custNqData['applyId'] = $("#applyId").val();
            this.custNqData['marryInfoId'] = $("#marryInfoId").val();
        }
    }else if(flag==5){//申请人工作信息
    
        var incomeTypeSum=0;
        var spouseIncomeTypeSum=0;
        
        if (!custNqData.validate5()) {
  	    	return false
  	    }
        uurl ="/custWorkInfo/addOrSaveCustWorkInfo";
       
        obj=document.getElementsByName("incomeType");
        for(k in obj){
            if(obj[k].checked){
                incomeTypeSum = incomeTypeSum|parseInt(obj[k].value);
            }
        }
        obj=document.getElementsByName("spouseIncomeType");
        for(k in obj){
            if(obj[k].checked){
                spouseIncomeTypeSum = spouseIncomeTypeSum|parseInt(obj[k].value);
            }
        }
        this.set('companyName').set('companyType').set('companyAddress').set('companyTel').set('department').set('job').set('workAge').set('monthIncome')
            .set('spouseCompanyName').set('spouseCompanyType').set('spouseCompanyAddress').set('spouseCompanyTel').set('spouseDepartment').set('spouseJob')
            .set('spouseWorkAge').set('spouseMonthIncome');
        this.custNqData['applyId'] = $("#applyId").val();
        this.custNqData['custWorkInfoId'] = $("#custWorkInfoId").val();
        this.custNqData['incomeType'] = incomeTypeSum;
        this.custNqData['spouseIncomeType'] = spouseIncomeTypeSum;
    }else if(flag==6){
    	debugger;
    	if (!custNqData.validate6()) {
	    	return false
	    }
        uurl ="/carInfo/addSaveOrUpdateCarInfo";
        
        this.custNqData['applyId'] = $("#applyId").val();
        var fileObj = document.getElementById("registerPhotoUrl11").files[0];
        if(fileObj!=undefined) {
            var fileFrontUrl = this.uploadFile(fileObj);
            $("#registerPhotoUrl1").val(fileFrontUrl);
        }
        var fileObj = document.getElementById("registerPhotoUrl21").files[0];
        if(fileObj!=undefined) {
            var fileFrontUrl = this.uploadFile(fileObj);
            $("#registerPhotoUrl2").val(fileFrontUrl);
        }
        var fileObj = document.getElementById("registerPhotoUrl31").files[0];
        if(fileObj!=undefined) {
            var fileFrontUrl = this.uploadFile(fileObj);
            $("#registerPhotoUrl3").val(fileFrontUrl);
        }
        var fileObj = document.getElementById("registerPhotoUrl41").files[0];
        if(fileObj!=undefined) {
            var fileFrontUrl = this.uploadFile(fileObj);
            $("#registerPhotoUrl4").val(fileFrontUrl);
        }
        this.set('carConfigName').set('currentLicDate').set('carType').set('engineNo').set('carColor').set('carImportType').set('displacement')
            .set('carUsage').set('firstLicDate').set('carNum').set('productDate').set('carBrand').set('carModel').set('vin').set('fuelType').set('registerPhotoUrl1')
            .set('registerPhotoUrl2').set('manufacturer').set('getType').set('registerPhotoUrl3').set('registerPhotoUrl4').set('mileage');
        this.custNqData['carInfoVoId'] = $("#CarInfoId").val();
        var carTransferInfolist=new Array();
        $("#newAddCarInfoTr tr").each(function () {
            var CarTransferInfo={
                id:$(this)[0].cells[0].innerText,
                name:$(this)[0].cells[1].innerText,
                certId:$(this)[0].cells[2].innerText,
                getType:$(this)[0].cells[3].innerText,
                regDate:$(this)[0].cells[4].innerText,
            };
            carTransferInfolist.push(CarTransferInfo);
        });
        this.custNqData['carTransferInfolist'] = carTransferInfolist;
        var carMortgageInfoList=new Array();
        $("#newAddCarMortgageInfoTr tr").each(function () {
            var CarMortgageInfo={
                id:$(this)[0].cells[0].innerText,
                name:$(this)[0].cells[1].innerText,
                certId:$(this)[0].cells[2].innerText,
                regDate:$(this)[0].cells[3].innerText,
            };
            carMortgageInfoList.push(CarMortgageInfo);
        });
        this.custNqData['carMortgageInfoList'] = carMortgageInfoList;
        var fileObj = document.getElementById("vehicleFrontPhoto1").files[0];
        if(fileObj!=undefined){
            var fileFrontUrl = this.uploadFile(fileObj);
            $("#vehicleFrontPhoto").val(fileFrontUrl);
        }
        var fileObj = document.getElementById("vehicleBackPhoto1").files[0];
        if(fileObj!=undefined) {
            var fileFrontUrl = this.uploadFile(fileObj);
            $("#vehicleBackPhoto").val(fileFrontUrl);
        }
        var fileObj = document.getElementById("driverFrontPhoto1").files[0];
         if(fileObj!=undefined) {
             var fileFrontUrl = this.uploadFile(fileObj);
             $("#driverFrontPhoto").val(fileFrontUrl);
         }
        var fileObj = document.getElementById("driverBackPhoto1").files[0];
        if(fileObj!=undefined) {
            var fileFrontUrl = this.uploadFile(fileObj);
            $("#driverBackPhoto").val(fileFrontUrl);
        }
        var CarDriverInfo={
            id:$("#carDriverInfoId").val(),
            vehicleValidDate:$("#vehicleValidDate").val(),
            driverNo:$("#driverNo").val(),
            permitType:$("#permitType").val(),
            driverBeginDate:$("#driverBeginDate").val(),
            driverEndDate:$("#driverEndDate").val(),
            vehicleFrontPhoto:$("#vehicleFrontPhoto").val(),
            vehicleBackPhoto:$("#vehicleBackPhoto").val(),
            firstDriverDate:$("#firstDriverDate").val(),
            isSelf:$("#isSelf").val(),
            driverRelation:$("#driverRelation").val(),
            driverRemark:$("#driverRemark").val(),
            driverFrontPhoto:$("#driverFrontPhoto").val(),
            driverBackPhoto:$("#driverBackPhoto").val(),
            isDriverLic:$("#isDriverLic").val(),
        };
        this.custNqData['CarDriverInfo'] = CarDriverInfo;
        var fileObj = document.getElementById("photoUrl1").files[0];
        if(fileObj!=undefined) {
            var fileFrontUrl = this.uploadFile(fileObj);
            $("#photoUrl").val(fileFrontUrl);
        }
        var CarTrafficInsureInfo={
            id:$("#carTrafficInsureInfoId").val(),
            instFullName:$("#instFullName").val(),
            bussinessSource:$("#bussinessSource").val(),
            insureBeginTime:$("#insureBeginTime").val(),
            insurePerson:$("#insurePerson").val(),
            totalAmount:$("#totalAmount").val(),
            vehicleTax:$("#vehicleTax").val(),
            photoUrl:$("#photoUrl").val(),
            proxyName:$("#proxyName").val(),
            insureNumber:$("#insureNumber").val(),
            insureEndTime:$("#insureEndTime").val(),
            floatProp:$("#floatProp").val(),
            signDate:$("#signDate").val(),
            specialAgreement:$("#specialAgreement").val(),
            remark:$("#remark").val(),
            
        };
        this.custNqData['CarTrafficInsureInfo'] = CarTrafficInsureInfo;
        var fileObj = document.getElementById("photoUrl11").files[0];
        if(fileObj!=undefined) {
            var fileFrontUrl = this.uploadFile(fileObj);
            $("#photoUrl111").val(fileFrontUrl);
        }
        var CarBussInsureInfo={
            id:$("#CarBussInsureInfoId").val(),
            instFullName:$("#instFullName1").val(),
            bussinessSource:$("#bussinessSource1").val(),
            insureBeginTime:$("#insureBeginTime1").val(),
            insurePerson:$("#insurePerson1").val(),
            totalAmount:$("#totalAmount1").val(),
           // vehicleTax:$("#vehicleTax1").val(),
            photoUrl:$("#photoUrl111").val(),
            proxyName:$("#proxyName1").val(),
            insureNumber:$("#insureNumber1").val(),
            insureEndTime:$("#insureEndTime1").val(),
            floatProp:$("#floatProp1").val(),
            signDate:$("#signDate1").val(),
            specialAgreement:$("#specialAgreement1").val(),
        };
        this.custNqData['CarBussInsureInfo'] = CarBussInsureInfo;
        var carPeccancyInfo={
            id:$("#carPeccancyInfoId").val(),
            totalNum:$("#totalNum").val(),
            totalMoney:$("#totalMoney").val(),
            totalValue:$("#totalValue").val(),
            totalFullNum:$("#totalFullNum").val(),
        };
        this.custNqData['carPeccancyInfo'] = carPeccancyInfo;
        obj=document.getElementsByName("CarInsureDetailInfoId");
        var carInsureDetailInfoList=new Array();
        $("#carInsureDetailInfoCheck").children('div').each(function () {
           if( $(this).children().find('input')[1].checked==true){
               var CarInsureDetailInfo={
                   id: $(this).children().find('input')[0].id,
                   insureId:$("#CarBussInsureInfoId").val(),
                   type: $(this).children().find('input')[1].value,
                   isNoDeduct :$(this).children().find('input')[2].checked=true?$(this).children().find('input')[2].value:$(this).children().find('input')[3].value,
                   maxPayAmount:$(this).children().find('input')[4].value,
                   amount: $(this).children().find('input')[5].value,
               };
               carInsureDetailInfoList.push(CarInsureDetailInfo);
           }
        });
        this.custNqData['carInsureDetailInfoList'] = carInsureDetailInfoList;
    }
}

var uurl;
/**
 * 提交添加
 */
custNqData.addSubmit = function(flag) {
	
    this.clearData();
    //this.collectData(flag);
    if(this.collectData(flag)==false){
    	
    }else {
        $.ajax({
            type: "POST",
            url: uurl,
            dataType: 'json',
            contentType: 'application/json',
            data:JSON.stringify(this.custNqData),
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                    Feng.info("保存成功！");
                    if(flag==2||flag==3){
                        custliclick(this);
                    }else if(flag==6){
                        carInfoVoLiClick(this);
                    }else if(flag==1){
                        liclick(this);
                    }else if(flag==5){
                        custWorkLiClick(this);
                    }else if(flag==4){
                        marryLiClick(this);
                    }
                }else{
                    Feng.error("保存失败!"+ data.msg);
                }
            },
            error: function(data) {
                Feng.info("保存异常！");
            }

        });
    }
   

}

custNqData.uploadFile = function(file) {
    var formData = new FormData();
    formData.append("file", file);
    var returnUrl = "";
    $.ajax({
        type: "POST",
        url: "/file/upload/idCard",
        /**
         *必须false才会自动加上正确的Content-Type
         */
        contentType: false,
        /**
         * 必须false才会避开jQuery对 formdata 的默认处理
         * XMLHttpRequest会对 formdata 进行正确的处理
         */
        processData: false,
        /**
         * 这里用同步方式
         */
        async:false,
        data: formData,
        success: function(data) {
            var status=data.status;
            if(status=='0'){
                Feng.info("保存成功！");
                returnUrl = data.data;
            }else{
                Feng.error("保存失败!"+ data.msg);
                returnUrl = "";
            }
        },
        error: function(data) {
            Feng.info("保存异常！");
            returnUrl = "";
        }

    });
    return returnUrl;
}


/*缩略图*/
function UploadImageicc(ev){
    var  idImgurl=$(ev).parent().nextAll()[0].children[0].id;
    var file = document.getElementById($(ev)[0].id).files[0];
    //判断 FileReader 是否被浏览器所支持
    if (!window.FileReader) return;
    //var file = evv.target.files[0];
    if(!file.type.match('image/!*')){
        //alert('上传的图片必修是png,gif,jpg格式的！');
    	 Feng.info("上传的图片必修是png,gif,jpg格式的！");
        ev.target.value = ""; //显示文件的值赋值为空
        return;
    }
    var reader = new FileReader();  // 创建FileReader对象
    reader.readAsDataURL(file); // 读取file对象，读取完毕后会返回result 图片base64格式的结果
    reader.onload = function(e){
        document.getElementById(idImgurl).src = e.target.result;
        document.getElementById(idImgurl).style ="width:100%;height:75px;background-color: #cccccc47"
    }
}
/**
 * 选择婚姻状况显示相应的div
 * @param ev
 */
//document.getElementById('marryStatus').onchange = function () {
function marryStatus(){
    var marryStatus= $("#marryStatus").val();
    if(marryStatus==0||marryStatus==2){//已婚或再婚
        $("#marryDate").parent().parent().show();
        $("#carPhotoBefore").show();
        $("#carPhotoBehand").show();
        $("#marryPhoto").show();
        $("#divorcePhoto").hide();
        $("#deathCertPhoto").hide();
        $("#infoSpouse").show();
        $("#divorceDate").parent().parent().hide();
        $("#spouseName").parent().parent().show();
        $("#divorceName").parent().parent().hide();
        $("#spousevalidateEnd").parent().parent().show();
        $("#spouseidFrontPhotoUrl").parent().show();
        $("#marryPhotoUrl").parent().show();
        $("#divorcePhotoUrl").parent().hide();
        $("#divorcePhotoUrl1").parent().hide();
        $("#spouseSex").parent().parent().show();
        $("#divorceSex").parent().parent().hide();
        $("#spousevalidateBegin").parent().parent().show();
        $("#spouseCertId").parent().parent().show();
        $("#divorceCertId").parent().parent().hide();
        $("#spouseSignOrg").parent().parent().show();
        $("#spouseidBackPhotoUrl").parent().show();
        $("#deathCertPhotoUrl").parent().hide();
        $("#deathCertPhotoUrl1").parent().hide();
        $("#divorcePhotoUrlmImg").parent().hide();
        $("#deathCertPhotoUrlmImg").parent().hide();
        $("#spouseidBackPhotoUrlmImg").parent().show();
        $("#marryPhotoUrlmImg").parent().show();
        $("#spouseidFrontPhotoUrlmImg").parent().show();
    }else if(marryStatus==1){//未婚，所有的div都需要隐藏起来
        $("#deathCertPhotoUrlmImg").parent().hide();
        $("#divorcePhotoUrlmImg").parent().hide();
        $("#spouseidBackPhotoUrlmImg").parent().hide();
        $("#carPhotoBefore").hide();
        $("#carPhotoBehand").hide();
        $("#divorcePhoto").hide();
        $("#marryPhoto").hide();
        $("#deathCertPhoto").hide();
        $("#infoSpouse").hide();
        $("#infoDivorce").hide()
        $("#marryPhotoUrlmImg").parent().hide();
        $("#spouseidFrontPhotoUrlmImg").parent().hide();
        $("#marryDate").parent().parent().hide();
        $("#divorceDate").parent().parent().hide();
        $("#spouseName").parent().parent().hide();
        $("#divorceName").parent().parent().hide();
        $("#spousevalidateEnd").parent().parent().hide();
        $("#spouseidFrontPhotoUrl").parent().hide();
        $("#spouseidFrontPhotoUrl1").parent().hide();
        $("#marryPhotoUrl").parent().hide();
        $("#divorcePhotoUrl").parent().hide();
        $("#marryPhotoUrl1").parent().hide();
        $("#divorcePhotoUrl1").parent().hide();
        $("#spouseSex").parent().parent().hide();
        $("#divorceSex").parent().parent().hide();
        $("#spousevalidateBegin").parent().parent().hide();
        $("#spouseCertId").parent().parent().hide();
        $("#divorceCertId").parent().parent().hide();
        $("#spouseSignOrg").parent().parent().hide();
        $("#spouseidBackPhotoUrl").parent().hide();
        $("#deathCertPhotoUrl").parent().hide();
        $("#spouseidBackPhotoUrl1").parent().hide();
        $("#deathCertPhotoUrl1").parent().hide();
    }else if(marryStatus==3){//离异，显示离异信息隐藏已婚再婚信息
        $("#deathCertPhotoUrlmImg").parent().hide();
        $("#divorcePhotoUrlmImg").parent().show();
        $("#carPhotoBefore").hide();
        $("#carPhotoBehand").hide();
        $("#marryPhoto").hide();
        $("#deathCertPhoto").hide();
        $("#infoSpouse").hide();
        $("#divorcePhoto").show();
        $("#infoDivorce").show();
        $("#spouseidBackPhotoUrlmImg").parent().hide();
        $("#marryPhotoUrlmImg").parent().hide();
        $("#spouseidFrontPhotoUrlmImg").parent().hide();
        $("#marryDate").parent().parent().hide();
        $("#divorceDate").parent().parent().show();
        $("#spouseName").parent().parent().hide();
        $("#divorceName").parent().parent().show();
        $("#spousevalidateEnd").parent().parent().hide();
        $("#spouseidFrontPhotoUrl").parent().hide();
        $("#marryPhotoUrl").parent().hide();
        $("#spouseidFrontPhotoUrl1").parent().hide();
        $("#marryPhotoUrl1").parent().hide();
        $("#divorcePhotoUrl").parent().show();
        $("#spouseSex").parent().parent().hide();
        $("#divorceSex").parent().parent().show();
        $("#spousevalidateBegin").parent().parent().hide();
        $("#spouseCertId").parent().parent().hide();
        $("#divorceCertId").parent().parent().show();
        $("#spouseSignOrg").parent().parent().hide();
        $("#spouseidBackPhotoUrl").parent().hide();
        $("#deathCertPhotoUrl").parent().hide();
        $("#spouseidBackPhotoUrl1").parent().hide();
        $("#deathCertPhotoUrl1").parent().hide();
    }else if(marryStatus==4){//丧偶，显示丧偶信息隐藏已婚再婚离异信息
        $("#deathCertPhotoUrlmImg").parent().show();
        $("#divorcePhotoUrlmImg").parent().hide();
        $("#carPhotoBefore").hide();
        $("#carPhotoBehand").hide();
        $("#marryPhoto").hide();
        $("#divorcePhoto").hide();
        $("#deathCertPhoto").show();
        $("#infoSpouse").hide();
        $("#infoDivorce").hide()
        $("#spouseidBackPhotoUrlmImg").parent().hide();
        $("#marryPhotoUrlmImg").parent().hide();
        $("#spouseidFrontPhotoUrlmImg").parent().hide();
        $("#marryDate").parent().parent().show();
        $("#divorceDate").parent().parent().hide();
        $("#spouseName").parent().parent().show();
        $("#divorceName").parent().parent().hide();
        $("#spousevalidateEnd").parent().parent().hide();
        $("#spouseidFrontPhotoUrl").parent().hide();
        $("#marryPhotoUrl").parent().hide();
        $("#divorcePhotoUrl").parent().hide();
        $("#spouseSex").parent().parent().show();
        $("#divorceSex").parent().parent().hide();
        $("#spousevalidateBegin").parent().parent().hide();
        $("#spouseCertId").parent().parent().show();
        $("#divorceCertId").parent().parent().hide();
        $("#spouseSignOrg").parent().parent().hide();
        $("#spouseidBackPhotoUrl").parent().hide();
        $("#deathCertPhotoUrl").parent().show();
    }
}

/**
 * 获取内勤资料录入借款基本信息，回显整个页面
 */
function firtGoBackHTML(){
	if($("#serviceItem").val()==0){
		 $("#moneyAmount").parent().parent().hide();
		 $("#percent").parent().parent().show();
	}else{
		 $("#moneyAmount").parent().parent().show();
		 $("#percent").parent().parent().hide();
	}
    if($("#applyId").val()!=null&&$("#applyId").val()!=undefined){
        $.ajax({
            type: "POST",
            url: '/applyInfo/getBackApplyInfo',
            dataType: 'json',
            /**
             * 这里用同步方式
             */
            async:false,
            data:{ApplyId:$("#applyId").val()},
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                	if(data.data.channelId!=null&&data.data.channelId!=""){
                        $('#channelId').val(data.data.channelId);
					}
                    $('#productType').val(data.data.productType==""?"0":data.data.productType);
                    $('#applyId').val(data.data.id);
                    $('#repaymentType').val(data.data.repaymentType==""?"0":data.data.repaymentType);
                    $('#partnerKnow').val(data.data.partnerKnow==""?"0":data.data.partnerKnow);
                    $('#applyAmount').val(data.data.applyAmount/10000);
                    $('#leftAmount').val(data.data.leftAmount/10000);
                    $('#applyPeriod').val(data.data.applyPeriod==""?"36":data.data.applyPeriod);
                    $('#isCollection').val(data.data.isCollection);
                    $('#serviceItem').val(data.data.serviceItem);
                    $('#percent').val(data.data.percent);
                    $('#moneyAmount').val(data.data.moneyAmount);
                    $('#serviceCharge').val(data.data.serviceCharge);
                    $('#feeInstallment').val(data.data.feeInstallment);
                    obj=document.getElementsByName("loanUsage");
                    for(k in obj){
                        if(parseInt(parseInt(data.data.loanUsage)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                            obj[k].checked=true;
                            if(parseInt(obj[k].value)==parseInt(16)){
                                $("#loanUsage6").show();
                            }
                        }
                    }
                    $('#loanUsage6').val(data.data.loanUsageOther);
                    setImgPx();
                }
            },

        });
    }
}
function liclick(self) {
    firtGoBackHTML();
}
var MgrUser = {
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid:0
};
function newAddFamilyBookSubInfo(){
    var index = layer.open({
        type: 2,
        title: '新增户口成员',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/familyBookInfo/familyBookInfoPage'
    });
    this.layerIndex = index;
}
function AddFamilyBookSubInfo(){
	if (!custNqData.validate7()) {
    	return
    }
	var familyBookSubInfoName1 = $("#familyBookSubInfoName1").val();
	var familyBookSubInfoCertId1 = $("#familyBookSubInfoCertId1").val();
	
    var fileObj = document.getElementById("bookPhotoUrl12").files[0];
    if(fileObj!=undefined){
        var fileFrontUrl =custNqData.uploadFile(fileObj);
        $("#bookPhotoUrl2").val(fileFrontUrl);
    }
    if($("#familyBookSubInfoSex1").val()==0){
        var str1='<tr  style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" ></td><td style="text-align: center">'+$("#familyBookSubInfoRelationship1").val()+'</td><td style="text-align: center">'+$("#familyBookSubInfoName1").val()+'</td><td style="text-align: center">'+$("#familyBookSubInfoCertId1").val()+'</td><td style="text-align: center">男</td><td style="text-align: center;display: none">'+$("#bookPhotoUrl2").val()+'</td></tr>';
    }else{
        var str1='<tr  style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" ></td><td style="text-align: center">'+$("#familyBookSubInfoRelationship1").val()+'</td><td style="text-align: center">'+$("#familyBookSubInfoName1").val()+'</td><td style="text-align: center">'+$("#familyBookSubInfoCertId1").val()+'</td><td style="text-align: center">女</td><td style="text-align: center;display: none">'+$("#bookPhotoUrl2").val()+'</td></tr>';
    }

   parent.$("#newAddFamilyBookSubInfoTr").append(str1);
    parent.layer.close(window.parent.layerIndex);

}
//删除户口本
function delFamilyBookSubInfo(event){
    $(event).parent().parent().remove();
    if($(event).parent().parent()[0].cells[0].innerText!=undefined&&$(event).parent().parent()[0].cells[0].innerText!=""){
        var ajax = new $ax(Feng.ctxPath + "/familyBookSubInfo/deleteById", function (data) {
            Feng.success("删除成功!");
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",$(event).parent().parent()[0].cells[0].innerText);
        ajax.start();
    }
}
//删除历史过户
function delCarTransferInfo(event){
    $(event).parent().parent().remove();
    if($(event).parent().parent()[0].cells[0].innerText!=undefined&&$(event).parent().parent()[0].cells[0].innerText!=""){
        var ajax = new $ax(Feng.ctxPath + "/carTransferInfo/deleteById", function (data) {
            Feng.success("删除成功!");
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",$(event).parent().parent()[0].cells[0].innerText);
        ajax.start();
    }
}
//删除抵押登记
function delCarMortgageInfo(event){
    $(event).parent().parent().remove();
    if($(event).parent().parent()[0].cells[0].innerText!=undefined&&$(event).parent().parent()[0].cells[0].innerText!=""){
        var ajax = new $ax(Feng.ctxPath + "/carMortgageInfo/deleteById", function (data) {
            Feng.success("删除成功!");
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",$(event).parent().parent()[0].cells[0].innerText);
        ajax.start();
    }
}
function updFamilyBookSubInfo(event){
    $(event).parent().parent().remove();
    var sex;
    	if($(event).parent().parent()[0].cells[4].innerText=='男'){
    		sex=0;
		}else{
    		sex=1;
		}
        var index = layer.open({
            type: 2,
            title: '修改户口成员',
            area: ['800px', '560px'], //宽高
            fix: false, //不固定
			closeBtn:0,
            maxmin: false,
            content: Feng.ctxPath + '/familyBookInfo/familyBookInfoPage2?familyBookSubInfoRelationship='+$(event).parent().parent()[0].cells[1].innerText+'&familyBookSubInfoName='+$(event).parent().parent()[0].cells[2].innerText
			+"&familyBookSubInfoCertId="+$(event).parent().parent()[0].cells[3].innerText+"&familyBookSubInfoSex="+sex+"&bookPhotoUrl="+$(event).parent().parent()[0].cells[7].innerText+"&id="+$(event).parent().parent()[0].cells[0].innerText
        });
    this.layerIndex = index;
}
function updCarTransferInfo(event){
    $(event).parent().parent().remove();
    var index = layer.open({
        type: 2,
        title: '修改户口成员',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        closeBtn:0,
        maxmin: false,
        content: Feng.ctxPath + '/carInfo/newAddCarInfo2?name='+$(event).parent().parent()[0].cells[1].innerText+'&certId='+$(event).parent().parent()[0].cells[2].innerText
        +"&getType="+$(event).parent().parent()[0].cells[3].innerText+"&regDate="+$(event).parent().parent()[0].cells[4].innerText+"&id="+$(event).parent().parent()[0].cells[0].innerText
    });
    this.layerIndex = index;
}
function updCarMortgageInfo(event){
    $(event).parent().parent().remove();
    var index = layer.open({
        type: 2,
        title: '修改户口成员',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        closeBtn:0,
        maxmin: false,
        content: Feng.ctxPath + '/carInfo/newAddCarMortgageInfo2?name='+$(event).parent().parent()[0].cells[1].innerText+'&certId='+$(event).parent().parent()[0].cells[2].innerText
       +"&regDate="+$(event).parent().parent()[0].cells[3].innerText+"&id="+$(event).parent().parent()[0].cells[0].innerText
    });
    this.layerIndex = index;
}
//户口本信息修改
function AddFamilyBookSubInfos(){
    if (!custNqData.validate7()) {
        return
    }
    var fileObj = document.getElementById("bookPhotoUrl2").files[0];
    if(fileObj!=undefined){
        var fileFrontUrl =custNqData.uploadFile(fileObj);
        $("#bookPhotoUrl22").val(fileFrontUrl);
    }
    if($("#familyBookSubInfoId3").val()=="null"){
        $("#familyBookSubInfoId3").val("");
    }
    if($("#familyBookSubInfoSex2").val()==0){
        var str1='<tr  style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" >'+$("#familyBookSubInfoId3").val()+'</td><td style="text-align: center">'+$("#familyBookSubInfoRelationship2").val()+'</td><td style="text-align: center">'+$("#familyBookSubInfoName2").val()+'</td><td style="text-align: center">'+$("#familyBookSubInfoCertId2").val()+'</td><td style="text-align: center">男</td><td style="text-align: center;display: none">'+$("#bookPhotoUrl22").val()+'</td></tr>';
    }else{
        var str1='<tr  style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" >'+$("#familyBookSubInfoId3").val()+'</td><td style="text-align: center">'+$("#familyBookSubInfoRelationship2").val()+'</td><td style="text-align: center">'+$("#familyBookSubInfoName2").val()+'</td><td style="text-align: center">'+$("#familyBookSubInfoCertId2").val()+'</td><td style="text-align: center">女</td><td style="text-align: center;display: none">'+$("#bookPhotoUrl22").val()+'</td></tr>';
    }
    parent.$("#newAddFamilyBookSubInfoTr").append(str1);
    parent.layer.close(window.parent.layerIndex);
}
//过户信息修改
function AddCarInfos(){
    if (!custNqData.validate8()) {
        return
    }
        if($("#carTransferInfoId3").val()=="null"){
            $("#carTransferInfoId3").val("");
        }
        var str1='<tr style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" >'+$("#carTransferInfoId3").val()+'</td><td style="text-align: center">'+$("#carTransferInfoName2").val()+'</td><td style="text-align: center">'+$("#carTransferInfoCertId2").val()+'</td><td style="text-align: center">'+$("#carTransferInfoGetType2").val()+'</td><td style="text-align: center">'+$("#carTransferInfoRegDate2").val()+'</td></tr>';

    parent.$("#newAddCarInfoTr").append(str1);
    parent.layer.close(window.parent.layerIndex);

}
//抵押记录修改
function AddCarMortgageInfos(){
    if (!custNqData.validate9()) {
        return
    }
    if($("#carMortgageInfoId3").val()=="null"){
        $("#carMortgageInfoId3").val("");
    }
    var str1='<tr style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" >'+$("#carMortgageInfoId3").val()+'</td><td style="text-align: center">'+$("#custName2").val()+'</td><td style="text-align: center">'+$("#custIdNo2").val()+'</td><td style="text-align: center">'+$("#acceptConfirmDate2").val()+'</td></tr>';
    parent.$("#newAddCarMortgageInfoTr").append(str1);
    parent.layer.close(window.parent.layerIndex);

}
/**
 * 点击客户基本信息回显页面
 */
function custliclick(){
    if($("#applyId").val()!=null&&$("#applyId").val()!=undefined){
        $.ajax({
            type: "POST",
            url: '/familyBookInfo/goBackfamilyBookInfoByApplyId',
            dataType: 'json',
            data:{ApplyId:$("#applyId").val()},
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                    var idPicUrls = data.idPicUrls;
                    //身份证信息
                    if(data.data2.marryStatus==0||data.data2.marryStatus==2){
                        $("#spouseName1").parent().parent().show();
                        $("#spousePhone1").parent().parent().show();
                        $("#spouseName1").val(data.data2.spouseName);
                        $("#spousePhone1").val(data.data1.spousePhone);
                    }else{
                        $("#spouseName1").parent().parent().hide();
                        $("#spousePhone1").parent().parent().hide();
                    }
                    $("#contractName1").val(data.data1.contractName1);
                    $("#contractPhone1").val(data.data1.contractPhone1);
                    $("#contractRelation1").val(data.data1.contractRelation1);
                    $("#contractName2").val(data.data1.contractName2);
                    $("#contractPhone2").val(data.data1.contractPhone2);
                    $("#contractRelation2").val(data.data1.contractRelation2);
                    $("#contractName3").val(data.data1.contractName3);
                    $("#contractPhone3").val(data.data1.contractPhone3);
                    $("#contractRelation3").val(data.data1.contractRelation3);
                    $('#name').val(data.data1.name);
                    $('#mobile').val(data.data1.mobile);
                    $('#sex').val(data.data1.sex==""?"0":data.data1.sex);
                    $('#signOrg').val(data.data1.signOrg);
                    $('#validateEnd').val(data.data1.validateEnd=""?"":data.data1.validateEnd.split(" ")[0]);
                    $('#idFrontPhotoUrlmImg').attr('src',data.data1.idFrontPhotoUrl==""?"":(idPicUrls+data.data1.idFrontPhotoUrl));
                    $('#idFrontPhotoUrl').val(data.data1.idFrontPhotoUrl==""?"":(idPicUrls+data.data1.idFrontPhotoUrl));
                    $('#nation').val(data.data1.nation);
                    $('#certId').val(data.data1.certId);
                    $('#education').val(data.data1.education==""?"0":data.data1.education);
                    $('#birthday').val(data.data1.birthday=""?"":data.data1.birthday.split(" ")[0]);
                    $('#validateBegin').val(data.data1.validateBegin=""?"":data.data1.validateBegin.split(" ")[0]);
                    $('#idBackPhotoUrlmImg').attr('src',data.data1.idBackPhotoUrl==""?"":(idPicUrls+data.data1.idBackPhotoUrl));
                    $('#idBackPhotoUrl').val(data.data1.idBackPhotoUrl==""?"":(idPicUrls+data.data1.idBackPhotoUrl));
                    $('#childNumber').val(data.data1.childNum);
                    $('#liveAddress').val(data.data1.liveAddress);
                    obj=document.getElementsByName("childAdult");
                    for(k in obj){
                        if(parseInt(parseInt(data.data1.childAdult)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                            obj[k].checked=true;
                            if(parseInt(obj[k].value)!=parseInt(1)){
                                $("#childNum").show();
                            }
                        }
                    }
                    obj=document.getElementsByName("liveType");
                    for(k in obj){
                        if(parseInt(parseInt(data.data1.liveType)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                            obj[k].checked=true;
                        }
                    }
                    obj=document.getElementsByName("togetherLive");
                    for(k in obj){
                        if(parseInt(parseInt(data.data1.togetherLive)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                            obj[k].checked=true;
                        }
                    }
                    //户口本信息
                    $('#familyBookInfoId').val(data.data.familyBookInfoId);
                    $('#relationship').val(data.data.relationship);
                    $('#masterName').val(data.data.masterName);
                    $('#masterSex').val(data.data.masterSex==""?"0":data.data.masterSex);
                    $('#masterCertId').val(data.data.certId);
                    $('#firstPagePhotoUrlImg').attr('src',data.data.firstPagePhotoUrl==""?"":(idPicUrls+data.data.firstPagePhotoUrl));
                    $('#firstPagePhotoUrl').val(data.data.firstPagePhotoUrl==""?"":(idPicUrls+data.data.firstPagePhotoUrl));
                    var familyBookSubInfolist=data.data.familyBookSubInfolist;
                    if(familyBookSubInfolist.length>0){
                            $("#newAddFamilyBookSubInfoTr").empty();
                           for(var i =0;i<familyBookSubInfolist.length;i++){
                               if(familyBookSubInfolist[i].sex==0){
                                   var str1='<tr  style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" >'+familyBookSubInfolist[i].id+'</td><td style="text-align: center">'+familyBookSubInfolist[i].relationship+'</td><td style="text-align: center">'+familyBookSubInfolist[i].name+'</td><td style="text-align: center">'+familyBookSubInfolist[i].certId+'</td><td style="text-align: center">男</td><td style="display:none">'+idPicUrls+familyBookSubInfolist[i].bookPhotoUrl+'</td></tr>';
                               }else{
                                   var str1='<tr  style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" >'+familyBookSubInfolist[i].id+'</td><td style="text-align: center">'+familyBookSubInfolist[i].relationship+'</td><td style="text-align: center">'+familyBookSubInfolist[i].name+'</td><td style="text-align: center">'+familyBookSubInfolist[i].certId+'</td><td style="text-align: center">女</td><td style="display:none">'+idPicUrls+familyBookSubInfolist[i].bookPhotoUrl+'</td></tr>';
                               }
                              $("#newAddFamilyBookSubInfoTr").append(str1);
                            }

                    }
                }
                setImgPx();
            },

        });
    }
}

/**
 * 点击婚姻信息回显页面
 */
function marryLiClick(){
    if($("#applyId").val()!=null&&$("#applyId").val()!=undefined){
        $.ajax({
            type: "POST",
            url: '/marryInfo/findmarryInfoByApplyId',
            dataType: 'json',
            data:{ApplyId:$("#applyId").val()},
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                    var idPicUrls = data.idPicUrls;
                    var identityPicPath = data.identityPicPath;
                    if(data.data.marryStatus==0||data.data.marryStatus==2){//已婚或再婚
                    	$("#carPhotoBefore").show();
                        $("#carPhotoBehand").show();
                        $("#marryPhoto").show();
                        $("#divorcePhoto").hide();
                        $("#deathCertPhoto").hide();
                        $("#infoSpouse").show();
                        $("#divorcePhotoUrlmImg").parent().hide();
                        $("#deathCertPhotoUrlmImg").parent().hide();
                        $("#spouseidBackPhotoUrlmImg").parent().show();
                        $("#marryPhotoUrlmImg").parent().show();
                        $("#spouseidFrontPhotoUrlmImg").parent().show();
                        $("#marryDate").parent().parent().show();
                        $("#divorceDate").parent().parent().hide();
                        $("#spouseName").parent().parent().show();
                        $("#divorceName").parent().parent().hide();
                        $("#spousevalidateEnd").parent().parent().show();
                        $("#spouseidFrontPhotoUrl").parent().show();
                        $("#marryPhotoUrl").parent().show();
                        $("#divorcePhotoUrl").parent().hide();
                        $("#divorcePhotoUrl1").parent().hide();
                        $("#spouseSex").parent().parent().show();
                        $("#divorceSex").parent().parent().hide();
                        $("#spousevalidateBegin").parent().parent().show();
                        $("#spouseCertId").parent().parent().show();
                        $("#divorceCertId").parent().parent().hide();
                        $("#spouseSignOrg").parent().parent().show();
                        $("#spouseidBackPhotoUrl").parent().show();
                        $("#deathCertPhotoUrl").parent().hide();
                        $("#deathCertPhotoUrl1").parent().hide();
                        $("#marryStatus").val(data.data.marryStatus==""?"0":data.data.marryStatus);
                        $("#marryInfoId").val(data.data.marryInfoId);
                        $("#marryDate").val(data.data.marryDate=""?"":data.data.marryDate.split(" ")[0]);
                        $("#spouseName").val(data.data.spouseName);
                        $("#spousevalidateEnd").val(data.data.spousevalidateEnd=""?"":data.data.spousevalidateEnd.split(" ")[0]);
                        $("#spouseidFrontPhotoUrlmImg").attr('src',data.data.spouseidFrontPhotoUrl==""?"":idPicUrls+data.data.spouseidFrontPhotoUrl);
                        $("#spouseidFrontPhotoUrl").val(data.data.spouseidFrontPhotoUrl==""?"":idPicUrls+data.data.spouseidFrontPhotoUrl);
                        $("#marryPhotoUrlmImg").attr('src',data.data.marryPhotoUrl==""?"":idPicUrls+data.data.marryPhotoUrl);
                        $("#marryPhotoUrl").val(data.data.marryPhotoUrl==""?"":idPicUrls+data.data.marryPhotoUrl);
                        $("#spouseSex").val(data.data.spouseSex==""?"0":data.data.spouseSex);
                        $("#spouseCertId").val(data.data.spouseCertId);
                        $("#spouseSignOrg").val(data.data.spouseSignOrg);
                        $("#spouseidBackPhotoUrlmImg").attr('src',data.data.spouseidBackPhotoUrl==""?"":idPicUrls+data.data.spouseidBackPhotoUrl);
                        $("#spouseidBackPhotoUrl").val(data.data.spouseidBackPhotoUrl==""?"":idPicUrls+data.data.spouseidBackPhotoUrl);
                        $("#spousevalidateBegin").val(data.data.spousevalidateBegin=""?"":data.data.spousevalidateBegin.split(" ")[0]);
                        setImgPx();
                    }else if(data.data.marryStatus==1){//未婚，所有的div都需要隐藏起来
                    	$("#carPhotoBefore").hide();
                        $("#carPhotoBehand").hide();
                        $("#divorcePhoto").hide();
                        $("#marryPhoto").hide();
                        $("#deathCertPhoto").hide();
                        $("#infoSpouse").hide();
                        $("#infoDivorce").hide()
                        $("#deathCertPhotoUrlmImg").parent().hide();
                        $("#divorcePhotoUrlmImg").parent().hide();
                        $("#spouseidBackPhotoUrlmImg").parent().hide();
                        $("#marryPhotoUrlmImg").parent().hide();
                        $("#spouseidFrontPhotoUrlmImg").parent().hide();
                        $("#marryDate").parent().parent().hide();
                        $("#divorceDate").parent().parent().hide();
                        $("#spouseName").parent().parent().hide();
                        $("#divorceName").parent().parent().hide();
                        $("#spousevalidateEnd").parent().parent().hide();
                        $("#spouseidFrontPhotoUrl").parent().hide();
                        $("#spouseidFrontPhotoUrl1").parent().hide();
                        $("#marryPhotoUrl").parent().hide();
                        $("#divorcePhotoUrl").parent().hide();
                        $("#marryPhotoUrl1").parent().hide();
                        $("#divorcePhotoUrl1").parent().hide();
                        $("#spouseSex").parent().parent().hide();
                        $("#divorceSex").parent().parent().hide();
                        $("#spousevalidateBegin").parent().parent().hide();
                        $("#spouseCertId").parent().parent().hide();
                        $("#divorceCertId").parent().parent().hide();
                        $("#spouseSignOrg").parent().parent().hide();
                        $("#spouseidBackPhotoUrl").parent().hide();
                        $("#deathCertPhotoUrl").parent().hide();
                        $("#spouseidBackPhotoUrl1").parent().hide();
                        $("#deathCertPhotoUrl1").parent().hide();
                        $("#marryInfoId").val(data.data.marryInfoId);
                        $("#marryStatus").val(data.data.marryStatus);
                        setImgPx();
                    }else if(data.data.marryStatus==3){//离异，显示离异信息隐藏已婚再婚信息
                    	$("#carPhotoBefore").hide();
                        $("#carPhotoBehand").hide();
                        $("#marryPhoto").hide();
                        $("#deathCertPhoto").hide();
                        $("#infoSpouse").hide();
                        $("#divorcePhoto").show();
                        $("#infoDivorce").show();
                        $("#deathCertPhotoUrlmImg").parent().hide();
                        $("#divorcePhotoUrlmImg").parent().show();
                        $("#spouseidBackPhotoUrlmImg").parent().hide();
                        $("#marryPhotoUrlmImg").parent().hide();
                        $("#spouseidFrontPhotoUrlmImg").parent().hide();
                        $("#marryDate").parent().parent().hide();
                        $("#divorceDate").parent().parent().show();
                        $("#spouseName").parent().parent().hide();
                        $("#divorceName").parent().parent().show();
                        $("#spousevalidateEnd").parent().parent().hide();
                        $("#spouseidFrontPhotoUrl").parent().hide();
                        $("#marryPhotoUrl").parent().hide();
                        $("#spouseidFrontPhotoUrl1").parent().hide();
                        $("#marryPhotoUrl1").parent().hide();
                        $("#divorcePhotoUrl").parent().show();
                        $("#spouseSex").parent().parent().hide();
                        $("#divorceSex").parent().parent().show();
                        $("#spousevalidateBegin").parent().parent().hide();
                        $("#spouseCertId").parent().parent().hide();
                        $("#divorceCertId").parent().parent().show();
                        $("#spouseSignOrg").parent().parent().hide();
                        $("#spouseidBackPhotoUrl").parent().hide();
                        $("#deathCertPhotoUrl").parent().hide();
                        $("#spouseidBackPhotoUrl1").parent().hide();
                        $("#deathCertPhotoUrl1").parent().hide();
                        $("#marryInfoId").val(data.data.marryInfoId);
                        $("#marryStatus").val(data.data.marryStatus);
                        $("#divorceDate").val(data.data.divorceDate=""?"":data.data.divorceDate.split(" ")[0]);
                        $("#divorceName").val(data.data.divorceName);
                        $("#divorcePhotoUrlmImg").attr('src',data.data.divorcePhotoUrl==""?"":idPicUrls+data.data.divorcePhotoUrl);
                        $("#divorcePhotoUrl").val(data.data.divorcePhotoUrl==""?"":idPicUrls+data.data.divorcePhotoUrl);
                        $("#divorceSex").val(data.data.divorceSex);
                        $("#divorceCertId").val(data.data.divorceCertId);
                        setImgPx();
                    }else if(data.data.marryStatus==4){//丧偶，显示丧偶信息隐藏已婚再婚离异信息
                    	 $("#carPhotoBefore").hide();
                         $("#carPhotoBehand").hide();
                         $("#marryPhoto").hide();
                         $("#divorcePhoto").hide();
                         $("#deathCertPhoto").show();
                         $("#infoSpouse").hide();
                         $("#infoDivorce").hide()
                        $("#deathCertPhotoUrlmImg").parent().show();
                        $("#divorcePhotoUrlmImg").parent().hide();
                        $("#spouseidBackPhotoUrlmImg").parent().hide();
                        $("#marryPhotoUrlmImg").parent().hide();
                        $("#spouseidFrontPhotoUrlmImg").parent().hide();
                        $("#marryDate").parent().parent().show();
                        $("#divorceDate").parent().parent().hide();
                        $("#spouseName").parent().parent().show();
                        $("#divorceName").parent().parent().hide();
                        $("#spousevalidateEnd").parent().parent().hide();
                        $("#spouseidFrontPhotoUrl").parent().hide();
                        $("#marryPhotoUrl").parent().hide();
                        $("#divorcePhotoUrl").parent().hide();
                        $("#spouseSex").parent().parent().show();
                        $("#divorceSex").parent().parent().hide();
                        $("#spousevalidateBegin").parent().parent().hide();
                        $("#spouseCertId").parent().parent().show();
                        $("#divorceCertId").parent().parent().hide();
                        $("#spouseSignOrg").parent().parent().hide();
                        $("#spouseidBackPhotoUrl").parent().hide();
                        $("#deathCertPhotoUrl").parent().show();
                        $("#marryInfoId").val(data.data.marryInfoId);
                        $("#marryStatus").val(data.data.marryStatus);
                        $("#marryDate").val(data.data.marryDate=""?"":data.data.marryDate.split(" ")[0]);
                        $("#spouseName").val(data.data.spouseName);
                        $("#spouseSex").val(data.data.spouseSex);
                        $("#spouseCertId").val(data.data.spouseCertId);
                        $("#deathCertPhotoUrlmImg").attr('src',data.data.deathCertPhotoUrl==""?"":idPicUrls+data.data.deathCertPhotoUrl);
                        $("#deathCertPhotoUrl").val(data.data.deathCertPhotoUrl==""?"":idPicUrls+data.data.deathCertPhotoUrl);
                        setImgPx();
                    }
                }
            },

        });
    }
}

//设置回显图片的位置
function setImgPx() {
    var imgList = $("img");
    for(var i=0;i<imgList.length;i++){
        if(imgList.eq(i).attr("src")!=null && imgList.eq(i).attr("src")!=''&&imgList.eq(i).attr("src")!=undefined){
            imgList.eq(i).attr("style","width:100%;height:75px;background-color: #cccccc47");
        }
    }

}
/**
 * 点击申请人工作信息回显页面
 */
function custWorkLiClick(){
    if($("#applyId").val()!=null&&$("#applyId").val()!=undefined){
        $.ajax({
            type: "POST",
            url: '/custWorkInfo/findCustWorkInfoByApplyId',
            dataType: 'json',
            data:{ApplyId:$("#applyId").val()},
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                    if(data.data1.marryStatus==0||data.data1.marryStatus==2){//已婚或再婚
                    	$("#carPhotoBefore").show();
                        $("#carPhotoBehand").show();
                        $("#marryPhoto").show();
                        $("#divorcePhoto").hide();
                        $("#deathCertPhoto").hide();
                        $("#spouseIncomeType1").parent().parent().show();
                        $("#spouseCompanyName").parent().parent().show();
                        $("#spouseCompanyType").parent().parent().show();
                        $("#spouseCompanyAddress").parent().parent().show();
                        $("#spouseCompanyTel").parent().parent().show();
                        $("#spouseDepartment").parent().parent().show();
                        $("#spouseJob").parent().parent().show();
                        $("#spouseWorkAge").parent().parent().show();
                        $("#spouseMonthIncome").parent().parent().show();
                        obj=document.getElementsByName("incomeType");
                        for(k in obj){
                            if(parseInt(parseInt(data.data.incomeType)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                                obj[k].checked=true;
                            }
                        }
                        $("#custWorkInfoId").val(data.data.id);
                       $("#monthIncome").val(data.data.monthIncome==undefined?undefined:data.data.monthIncome/10000);
                       // $("#monthIncome").val(data.data.monthIncome);
                        $("#companyName").val(data.data.companyName);
                        $("#companyType").val(data.data.companyType==undefined?"0":data.data.companyType);
                        $("#companyAddress").val(data.data.companyAddress);
                        $("#companyTel").val(data.data.companyTel);
                        $("#department").val(data.data.department);
                        $("#job").val(data.data.job);
                        $("#workAge").val(data.data.workAge);
                        obj=document.getElementsByName("spouseIncomeType");
                        for(k in obj){
                            if(parseInt(parseInt(data.data.spouseIncomeType)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                                obj[k].checked=true;
                            }
                        }
                        $("#spouseCompanyName").val(data.data.spouseCompanyName);
                        $("#spouseCompanyType").val(data.data.spouseCompanyType==undefined?"0":data.data.spouseCompanyType);
                        $("#spouseCompanyAddress").val(data.data.spouseCompanyAddress);
                        $("#spouseCompanyTel").val(data.data.spouseCompanyTel);
                        $("#spouseDepartment").val(data.data.spouseDepartment);
                        $("#spouseJob").val(data.data.spouseJob);
                        $("#spouseWorkAge").val(data.data.spouseWorkAge);
                       	$("#spouseMonthIncome").val(data.data.spouseMonthIncome==undefined?undefined:data.data.spouseMonthIncome/10000);
                       // $("#spouseMonthIncome").val(data.data.spouseMonthIncome);
                       // $("#spouseMonthIncome").val(data.data.spouseMonthIncome);
                    }else{//配偶信息都需要隐藏起来
                        $("#spouseIncomeType1").parent().parent().hide();
                        $("#spouseCompanyName").parent().parent().hide();
                        $("#spouseCompanyType").parent().parent().hide();
                        $("#spouseCompanyAddress").parent().parent().hide();
                        $("#spouseCompanyTel").parent().parent().hide();
                        $("#spouseDepartment").parent().parent().hide();
                        $("#spouseJob").parent().parent().hide();
                        $("#spouseWorkAge").parent().parent().hide();
                        $("#spouseMonthIncome").parent().parent().hide();
                        obj=document.getElementsByName("incomeType");
                        for(k in obj){
                            if(parseInt(parseInt(data.data.incomeType)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                                obj[k].checked=true;
                            }
                        }
                        $("#custWorkInfoId").val(data.data.id);
                        $("#monthIncome").val(data.data.monthIncome==undefined?undefined:data.data.monthIncome/10000);
                       // $("#monthIncome").val(data.data.monthIncome);
                        $("#companyName").val(data.data.companyName);
                        $("#companyType").val(data.data.companyType);
                        $("#companyAddress").val(data.data.companyAddress);
                        $("#companyTel").val(data.data.companyTel);
                        $("#department").val(data.data.department);
                        $("#job").val(data.data.job);
                        $("#workAge").val(data.data.workAge);
                    }
                }
                setImgPx();
            },

        });
    }
}
function newAddCarInfo(){
    var index = layer.open({
        type: 2,
        title: '添加历史过户记录',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/carInfo/newAddCarInfo'
    });
    this.layerIndex = index;
}
function AddCarInfo(){
	 if (!custNqData.validate8()) {
	    	return
	 }
    var str1='<tr  style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" ></td><td style="text-align: center">'+$("#carTransferInfoName").val()+'</td><td style="text-align: center">'+$("#carTransferInfoCertId").val()+'</td><td style="text-align: center">'+$("#carTransferInfoGetType").val()+'</td><td style="text-align: center">'+$("#carTransferInfoRegDate").val()+'</td></tr>';
    parent.$("#newAddCarInfoTr").append(str1);
    parent.layer.close(window.parent.layerIndex);
}
function newAddCarMortgageInfo(){
    var index = layer.open({
        type: 2,
        title: '添加历史过户记录',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/carInfo/newAddCarMortgageInfo'
    });
    this.layerIndex = index;
}
function AddCarMortgageInfo(){
	if (!custNqData.validate9()) {
	    	return
	 }
    var str1='<tr  style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" ></td><td style="text-align: center">'+$("#custName").val()+'</td><td style="text-align: center">'+$("#custIdNo").val()+'</td><td style="text-align: center">'+$("#acceptConfirmDate").val()+'</td></tr>';
    parent.$("#newAddCarMortgageInfoTr").append(str1);
    parent.layer.close(window.parent.layerIndex);
}

/**
 * 是否申请人持有改变
 */
$("#isSelf").change(function () {
    if($("#isSelf").val()==0){
        $("#driverRelation").parent().parent().hide();
        $("#driverRemark").parent().parent().hide();
    }else{
        $("#driverRelation").parent().parent().show();
        $("#driverRemark").parent().parent().show();
    }
});
/**
 * 是否代收改变
 */
$("#isCollection").change(function () {
    if($("#isCollection").val()==0){
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
    if($("#serviceItem").val()==0&&$("#applyAmount").val()!=""&&$("#percent").val()!=""){
        $("#serviceCharge").val($("#applyAmount").val()*$("#percent").val()*100);
    }else if($("#serviceItem").val()==1&&$("#applyAmount").val()!=""&&$("#moneyAmount").val()!=""){
        $("#serviceCharge").val($("#moneyAmount").val());
    }
    if($("#serviceItem").val()==0){
        $("#percent").parent().parent().show();
        $("#moneyAmount").parent().parent().hide();
    }else{
        $("#percent").parent().parent().hide();
        $("#moneyAmount").parent().parent().show();
    }
});
/**
 * 金额改变
 */
$("#moneyAmount").blur(function () {
    if($("#serviceItem").val()==0&&$("#applyAmount").val()!=""&&$("#percent").val()!=""){
        $("#serviceCharge").val($("#percent").val());
    }else if($("#serviceItem").val()==1&&$("#applyAmount").val()!=""&&$("#moneyAmount").val()!=""){
        $("#serviceCharge").val($("#moneyAmount").val());
	}
});
/**
 * 申请金额改变
 */
$("#applyAmount").blur(function () {
    if($("#serviceItem").val()==0&&$("#applyAmount").val()!=""&&$("#percent").val()!=""){
        $("#serviceCharge").val($("#applyAmount").val()*$("#percent").val()*100);
    }else if($("#serviceItem").val()==1&&$("#applyAmount").val()!=""&&$("#moneyAmount").val()!=""){
        $("#serviceCharge").val($("#applyAmount").val()*$("#moneyAmount").val()*100);
	}
});
/**
 * 百分比改变
 */
$("#percent").blur(function () {
    if($("#serviceItem").val()==0&&$("#applyAmount").val()!=""&&$("#percent").val()!=""){
		$("#serviceCharge").val($("#applyAmount").val()*$("#percent").val()*100);
    }else if($("#serviceItem").val()==1&&$("#applyAmount").val()!=""&&$("#moneyAmount").val()!=""){
        $("#serviceCharge").val($("#applyAmount").val()*$("#moneyAmount").val()*100);
    }
});
/**
 * 根据选取的车辆保险险种详细信息取消或添加遮罩效果，选中才可添加具体信息
 */
$("input[name='CarInsureDetailInfoId']").on('click',function () {
        if($(this)[0].checked==true){
            $(this).parent().nextAll().find("input")[0].disabled=false;
            $(this).parent().nextAll().find("input")[1].disabled=false;
            $(this).parent().nextAll().find("input")[2].disabled=false;
            $(this).parent().nextAll().find("input")[3].disabled=false;
        }else{
           $(this).parent().nextAll().find("input")[0].disabled=true;
            $(this).parent().nextAll().find("input")[1].disabled=true;
            $(this).parent().nextAll().find("input")[2].disabled=true;
            $(this).parent().nextAll().find("input")[3].disabled=true;
        }

});
/**
 * 点击车辆信息回显页面
 */
function carInfoVoLiClick(){
    if($("#applyId").val()!=null&&$("#applyId").val()!=undefined){
        $.ajax({
            type: "POST",
            url: '/carInfo/findCarInfoByApplyId',
            dataType: 'json',
            data:{ApplyId:$("#applyId").val()},
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                    var idPicUrls = data.idPicUrls;
                    $("#CarInfoId").val(data.data.carInfoVoId);
                    $("#carConfigName").val(data.data.carConfigName);
                    $("#currentLicDate").val(data.data.currentLicDate=""?"":data.data.currentLicDate.split(" ")[0]);
                    $("#carType").val(data.data.carType);
                    $("#engineNo").val(data.data.engineNo);
                    $("#carColor").val(data.data.carColor);
                    $("#carImportType").val(data.data.carImportType==""?"0":data.data.carImportType);
                    $("#displacement").val(data.data.displacement);
                    $("#carUsage").val(data.data.carUsage==""?"0":data.data.carUsage);
                    $("#firstLicDate").val(data.data.firstLicDate=""?"":data.data.firstLicDate.split(" ")[0]);
                    $("#registerPhotoUrl1mImg").attr('src',data.data.registerPhotoUrl1==""?"":idPicUrls+data.data.registerPhotoUrl1);
                    $("#registerPhotoUrl1").val(data.data.registerPhotoUrl1==""?"":idPicUrls+data.data.registerPhotoUrl1);
                    $("#registerPhotoUrl2mImg").attr('src',data.data.registerPhotoUrl2==""?"":idPicUrls+data.data.registerPhotoUrl2);
                    $("#registerPhotoUrl2").val(data.data.registerPhotoUrl2==""?"":idPicUrls+data.data.registerPhotoUrl2);
                    $("#carNum").val(data.data.carNum);
                    $("#productDate").val(data.data.productDate=""?"":data.data.productDate.split(" ")[0]);
                    $("#carBrand").val(data.data.carBrand);
                    $("#carModel").val(data.data.carModel);
                    $("#vin").val(data.data.vin);
                    $("#fuelType").val(data.data.fuelType==""?"0":data.data.fuelType);
                    $("#manufacturer").val(data.data.manufacturer);
                    $("#getType").val(data.data.getType==""?"0":data.data.getType);
                    $("#registerPhotoUrl3mImg").attr('src',data.data.registerPhotoUrl3==""?"":idPicUrls+data.data.registerPhotoUrl3);
                    $("#registerPhotoUrl3").val(data.data.registerPhotoUrl3==""?"":idPicUrls+data.data.registerPhotoUrl3);
                    $("#registerPhotoUrl4mImg").attr('src',data.data.registerPhotoUrl4==""?"":idPicUrls+data.data.registerPhotoUrl4);
                    $("#registerPhotoUrl4").val(data.data.registerPhotoUrl4==""?"":idPicUrls+data.data.registerPhotoUrl4);
                    $("#mileage").val(data.data.mileage);
                    var carTransferInfolist=data.data.carTransferInfolist;
                    if(carTransferInfolist.length>0){
                        $("#newAddCarInfoTr").empty();
                        for(var i =0;i<carTransferInfolist.length;i++){
                            carTransferInfolist[i].regDate=""?"":carTransferInfolist[i].regDate.split(" ")[0];
                            var str1='<tr  style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" >'+carTransferInfolist[i].id+'</td><td style="text-align: center">'+carTransferInfolist[i].name+'</td><td style="text-align: center">'+carTransferInfolist[i].certId+'</td><td style="text-align: center">'+carTransferInfolist[i].getType+'</td><td style="text-align: center">'+ carTransferInfolist[i].regDate+'</td></tr>';
                            $("#newAddCarInfoTr").append(str1);
                        }

                    }
                    var carMortgageInfoList=data.data.carMortgageInfoList;
                    if(carMortgageInfoList.length>0){
                        $("#newAddCarMortgageInfoTr").empty();
                        for(var i =0;i<carMortgageInfoList.length;i++){
                           carMortgageInfoList[i].regDate=""?"":carMortgageInfoList[i].regDate.split(" ")[0];
                            var str1='<tr  style="background-color:#FFF;padding: 1px;box-shadow: 0 0 2px #FFF inset;margin-top: 3px"><td type="hidden" style="display:none" >'+carMortgageInfoList[i].id+'</td><td style="text-align: center">'+carMortgageInfoList[i].name+'</td><td style="text-align: center">'+carMortgageInfoList[i].certId+'</td><td style="text-align: center">'+carMortgageInfoList[i].regDate+'</td></tr>';
                            $("#newAddCarMortgageInfoTr").append(str1);
                        }

                    }
                    var carDriverInfo=data.data.carDriverInfo;
                    if(carDriverInfo!=""){
                        $("#carDriverInfoId").val(carDriverInfo.id);
                        $("#vehicleValidDate").val(carDriverInfo.vehicleValidDate=""?"":carDriverInfo.vehicleValidDate.split(" ")[0]);
                        $("#driverNo").val(carDriverInfo.driverNo);
                        $("#permitType").val(carDriverInfo.permitType);
                        $("#driverBeginDate").val(carDriverInfo.driverBeginDate=""?"":carDriverInfo.driverBeginDate.split(" ")[0]);
                        $("#driverEndDate").val(carDriverInfo.driverEndDate=""?"":carDriverInfo.driverEndDate.split(" ")[0]);
                        $("#vehicleFrontPhotoImg").attr('src',carDriverInfo.vehicleFrontPhoto==""?"":idPicUrls+carDriverInfo.vehicleFrontPhoto);
                        $("#vehicleFrontPhoto").val(carDriverInfo.vehicleFrontPhoto==""?"":idPicUrls+carDriverInfo.vehicleFrontPhoto);
                        $("#vehicleBackPhotoImg").attr('src',carDriverInfo.vehicleBackPhoto==""?"":idPicUrls+carDriverInfo.vehicleBackPhoto);
                        $("#vehicleBackPhoto").val(carDriverInfo.vehicleBackPhoto==""?"":idPicUrls+carDriverInfo.vehicleBackPhoto);
                        $("#isDriverLic").val(carDriverInfo.isDriverLic);
                        $("#firstDriverDate").val(carDriverInfo.firstDriverDate=""?"":carDriverInfo.firstDriverDate.split(" ")[0]);
                        $("#isSelf").val(carDriverInfo.isSelf);
                        $("#driverRelation").val(carDriverInfo.driverRelation);
                        $("#driverRemark").val(carDriverInfo.driverRemark);
                        $("#driverFrontPhotoImg").attr('src',carDriverInfo.driverFrontPhoto==""?"":idPicUrls+carDriverInfo.driverFrontPhoto);
                        $("#driverFrontPhoto").val(carDriverInfo.driverFrontPhoto==""?"":idPicUrls+carDriverInfo.driverFrontPhoto);
                        $("#driverBackPhotoImg").attr('src',carDriverInfo.driverBackPhoto==""?"":idPicUrls+carDriverInfo.driverBackPhoto);
                        $("#driverBackPhoto").val(carDriverInfo.driverBackPhoto==""?"":idPicUrls+carDriverInfo.driverBackPhoto);
                    }
                    var carPeccancyInfo=data.data.carPeccancyInfo;
                    if(carPeccancyInfo!=""){
                        $("#carPeccancyInfoId").val(carPeccancyInfo.id);
                        $("#totalNum").val(carPeccancyInfo.totalNum);
                        $("#totalMoney").val(carPeccancyInfo.totalMoney);
                        $("#totalValue").val(carPeccancyInfo.totalValue);
                        $("#totalFullNum").val(carPeccancyInfo.totalFullNum);
                    }
                    var carTrafficInsureInfo=data.data.carTrafficInsureInfo;
                    if(carTrafficInsureInfo!=""){
                        $("#carTrafficInsureInfoId").val(carTrafficInsureInfo.id);
                        $("#instFullName").val(carTrafficInsureInfo.instFullName);
                        $("#bussinessSource").val(carTrafficInsureInfo.bussinessSource);
                        $("#insureBeginTime").val(carTrafficInsureInfo.insureBeginTime=""?"":carTrafficInsureInfo.insureBeginTime.split(" ")[0]);
                        $("#insurePerson").val(carTrafficInsureInfo.insurePerson);
                        $("#totalAmount").val(carTrafficInsureInfo.totalAmount);
                        $("#vehicleTax").val(carTrafficInsureInfo.vehicleTax);
                        $("#photoUrlmImg").attr('src',carTrafficInsureInfo.photoUrl==""?"":idPicUrls+carTrafficInsureInfo.photoUrl);
                        $("#photoUrl").val(carTrafficInsureInfo.photoUrl==""?"":idPicUrls+carTrafficInsureInfo.photoUrl);
                        $("#proxyName").val(carTrafficInsureInfo.proxyName);
                        $("#insureNumber").val(carTrafficInsureInfo.insureNumber);
                        $("#insureEndTime").val(carTrafficInsureInfo.insureEndTime=""?"":carTrafficInsureInfo.insureEndTime.split(" ")[0]);
                        $("#floatProp").val(carTrafficInsureInfo.floatProp);
                        $("#signDate").val(carTrafficInsureInfo.signDate=""?"":carTrafficInsureInfo.signDate.split(" ")[0]);
                        $("#specialAgreement").val(carTrafficInsureInfo.specialAgreement);
                        $("#remark").val(carTrafficInsureInfo.remark);
                    }
                    var carBussInsureInfo=data.data.carBussInsureInfo;
                    if(carBussInsureInfo!=""){
                        $("#CarBussInsureInfoId").val(carBussInsureInfo.id);
                        $("#instFullName1").val(carBussInsureInfo.instFullName);
                        $("#bussinessSource1").val(carBussInsureInfo.bussinessSource);
                        $("#insureBeginTime1").val(carBussInsureInfo.insureBeginTime=""?"":carBussInsureInfo.insureBeginTime.split(" ")[0]);
                        $("#insurePerson1").val(carBussInsureInfo.insurePerson);
                        $("#totalAmount1").val(carBussInsureInfo.totalAmount);
                        $("#vehicleTax1").val(carBussInsureInfo.vehicleTax);
                        $("#photoUrlmImg1").attr('src',carBussInsureInfo.photoUrl==""?"":idPicUrls+carBussInsureInfo.photoUrl);
                        $("#photoUrl111").val(carBussInsureInfo.photoUrl==""?"":idPicUrls+carBussInsureInfo.photoUrl);
                        $("#proxyName1").val(carBussInsureInfo.proxyName);
                        $("#insureNumber1").val(carBussInsureInfo.insureNumber);
                        $("#insureEndTime1").val(carBussInsureInfo.insureEndTime=""?"":carBussInsureInfo.insureEndTime.split(" ")[0]);
                        $("#floatProp1").val(carBussInsureInfo.floatProp);
                        $("#signDate1").val(carBussInsureInfo.signDate=""?"":carBussInsureInfo.signDate.split(" ")[0]);
                        $("#specialAgreement1").val(carBussInsureInfo.specialAgreement);
                    }
                    var carInsureDetailInfoList=data.data.carInsureDetailInfoList;
                    if(carInsureDetailInfoList.length>0){
                        for(var i =0;i<carInsureDetailInfoList.length;i++){
                               $("#carInsureDetailInfoCheck").children('div').each(function () {
                                  if($(this).children().find('input')[1].value==carInsureDetailInfoList[i].type){
                                     $(this).children().find('input')[0].id=carInsureDetailInfoList[i].id;
                                      $(this).children().find('input')[1].checked=true;
                                      if( carInsureDetailInfoList[i].isNoDeduct==0){
                                          $(this).children().find('input')[2].checked=true;
                                      }else{
                                          $(this).children().find('input')[3].checked=true;
                                      }
                                          $(this).children().find('input')[4].value=carInsureDetailInfoList[i].maxPayAmount;
                                           $(this).children().find('input')[5].value=carInsureDetailInfoList[i].amount;
                                  }
                               });
                        }
                    }
                }
                setImgPx();
            },

        });
    }
}
//返回 跳转到提交页面
function back() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/cust/showNqSubmit?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
}
//返回按钮
function backCommon() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var flag = $("#flag").val();
    if(flag == 1){//1 面审
        window.location.href="/mianshen/showMsCommon?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
    }else if(flag==2){//面审主管
        window.location.href="/mianshen/showZg?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
    }else if(flag==3){//终审
        window.location.href="/zhongshen/showZsCommon?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
	}

}

//车辆信息--校验VI码
var Arr = new Array();
var Brr = new Array();
Arr['A'] = 1; Arr['B'] = 2; Arr['C'] = 3; Arr['D'] = 4; Arr['E'] = 5; Arr['F'] = 6; Arr['G'] = 7; Arr['H'] = 8;
Arr['J'] = 1; Arr['K'] = 2; Arr['L'] = 3; Arr['M'] = 4; Arr['N'] = 5; Arr['P'] = 7;
Arr['R'] = 9; Arr['S'] = 2; Arr['T'] = 3; Arr['U'] = 4; Arr['V'] = 5; Arr['W'] = 6; Arr['X'] = 7; Arr['Y'] = 8; Arr['Z'] = 9; 
Arr['1'] = 1; Arr['2'] = 2; Arr['3'] = 3; Arr['4'] = 4; Arr['5'] = 5; Arr['6'] = 6; Arr['7'] = 7; Arr['8'] = 8; Arr['9'] = 9; Arr['0'] = 0;
Brr[1] = 8; Brr[2] = 7; Brr[3] = 6; Brr[4] = 5; Brr[5] = 4; Brr[6] = 3; Brr[7] = 2; Brr[8] = 10; Brr[9] = 0;
Brr[10] = 9; Brr[11] = 8; Brr[12] = 7; Brr[13] = 6; Brr[14] = 5; Brr[15] = 4;  Brr[16] = 3; Brr[17] = 2;
function getCheckCode(sVIN){
	var sKYZF = "ABCDEFGHJKLMNPRSTUVWXYZ1234567890";
	var sJYW = "";
	var bl = false;
	var blKYZF = false;
	if(sVIN.length == 17){
		var iJQS = 0,intTemp = 0;
		ht = Arr;
		htZM = Brr;
		try{
			for(var i=0;i<sVIN.length;i++){
				if(sKYZF.indexOf(sVIN.substr(i,1))!==-1){
					blKYZF = true;
					iJQS = iJQS + parseInt(ht[sVIN.substr(i,1)])*parseInt(htZM[(i+1)]);
				}else{
					blKYZF = false;
					break;
				}
			}
			if(blKYZF){
				intTemp = iJQS%11;
				if(intTemp==10){
					sJYW = "X";
				}
			}else{
				sJYW = intTemp.toString();
			}
			if(sJYW == sVIN.substr(8,1)){
				bl = true;
			}else{
				bl = false;
			}
		}catch(err){
			bl = false;
		}
	}
		return bl
}

