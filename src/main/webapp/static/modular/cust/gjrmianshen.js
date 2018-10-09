var idPicUrls = $("#idPicUrls").val();
$(document).ready(function(){
	 Feng.initValidator("menu-item10", saveOrUpdates.validateFields);
	 Feng.initValidator("menu-item3", saveOrUpdates.validateFields);
     Feng.initValidator("menu-item4", saveOrUpdates.validateFields);
	 Feng.initValidator("menu-item11", saveOrUpdates.validateFields);
	 Feng.initValidator("menu-item2", saveOrUpdates.validateFields);
	 Feng.initValidator("menu-item1", saveOrUpdates.validateFields);
	 Feng.initValidator("menu-item6", saveOrUpdates.validateFields);
	 Feng.initValidator("gjrjudeicialName", saveOrUpdates.validateFields);
});

var saveOrUpdates = {
		 validateFields:{
			 userName:{
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
			 // judicialCompanyName:{
				//  validators: {
	          //       notEmpty: {
	          //           message: '企业名称不能为空'
	          //       }
	          //   }
			 // },
			 idNum:{
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
			 tdScore:{
					validators: {
						notEmpty: {
		                    message: '评分不能为空'
		                },
						regexp: {
			                regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 brRuleScore:{
					 validators: {
                        // notEmpty: {
		                //     message: '集分不能为空'
		                // },
						regexp: {
			                regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 brCreditScore:{
					 validators: {
                        // notEmpty: {
		                //     message: '信用评分不能为空'
		                // },
						regexp: {
			                regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 houseBussLoanNum:{
					 validators: {
						notEmpty: {
		                    message: '贷款笔数不能为空'
		                },
						regexp: {
			                regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 objectionNum:{
					 validators: {
						notEmpty: {
		                    message: '数量不能为空'
		                },
						regexp: {
			                regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 loanOverdueNum:{
					 validators: {
						notEmpty: {
		                    message: '逾期笔数不能为空'
		                },
						regexp: {
			                regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 loanMaxAmount:{
					 validators: {
						notEmpty: {
		                    message: '总额不能为空'
		                },
						regexp: {
			                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 cardOverdueNum:{
					 validators: {
						notEmpty: {
		                    message: '账户数不能为空'
		                },
						regexp: {
			                regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 cardOverdueMaxAmount:{
					 validators: {
						notEmpty: {
		                    message: '总额不能为空'
		                },
						regexp: {
			                regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 semiCardOverdueNum:{
					 validators: {
						notEmpty: {
		                    message: '账户数不能为空'
		                },
						regexp: {
			                regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 semiCardMaxAmount:{
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
				 loanLegalOrgNum:{
					 validators: {
						notEmpty: {
		                    message: '机构数不能为空'
		                },
						regexp: {
			                regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 loanNum:{
					 validators: {
						notEmpty: {
		                    message: '笔数不能为空'
		                },
						regexp: {
			                regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 loanLeftAmount:{
					 validators: {
						notEmpty: {
		                    message: '余额不能为空'
		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 cardLegalOrgNum:{
					 validators: {
						notEmpty: {
		                    message: '机构数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 cardAccountNum:{
					 validators: {
						notEmpty: {
		                    message: '账户数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 cardMaxAmount:{
					 validators: {
						notEmpty: {
		                    message: '授信额不能为空'
		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 cardUsedAmount:{
					 validators: {
						notEmpty: {
		                    message: '已用额度不能为空'
		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 houseLoanNum:{
					 validators: {
						notEmpty: {
		                    message: '贷款笔数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 otherLoanNum:{
					 validators: {
						notEmpty: {
		                    message: '贷款笔数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 creditCardNum:{
					 validators: {
						notEmpty: {
		                    message: '账户数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 semiCreditCardNum:{
					 validators: {
						notEmpty: {
		                    message: '账户数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 selfDeclareNum:{
					 validators: {
						notEmpty: {
		                    message: '数量不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					} 
				 },
				 loanOverdueMonthNum:{
					 validators: {
						notEmpty: {
		                    message: '月份数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					} 
				 },
				 loanMaxMonthNum:{
					 validators: {
						notEmpty: {
		                    message: '月份数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					} 
				 },
				 cardMonthNum:{
					 validators: {
						notEmpty: {
		                    message: '月份数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					} 
				 },
				 cardMaxMonthNum:{
					 validators: {
						notEmpty: {
		                    message: '月份数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					} 
				 },
				 semiCardMonthNum:{
					 validators: {
						notEmpty: {
		                    message: '月份数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					} 
				 },
				 semiCardMaxMonthNum:{
					 validators: {
						notEmpty: {
		                    message: '月份数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					} 
				 },
				 loanOrgNum:{
					 validators: {
						notEmpty: {
		                    message: '机构数数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}  
				 },
				 loanTotalAmount:{
					 validators: {
						notEmpty: {
		                    message: '总金额不能为空'
		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 loanTotalMonthAmount:{
					 validators: {
						notEmpty: {
		                    message: '总月还款不能为空'
		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 cardOrgNum:{
					 validators: {
						notEmpty: {
		                    message: '机构数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 cardTotalAmount:{
					 validators: {
						notEmpty: {
		                    message: '总额不能为空'
		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 cardMinAmount:{
					 validators: {
						notEmpty: {
		                    message: '授信额不能为空'
		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 cardAvgAmount:{
					 validators: {
						notEmpty: {
		                    message: '使用额不能为空'
		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
             collectionDate:{
                 validators: {
                     notEmpty: {
                         message: '征信报告采集时间不能为空'
                     }
                 }
             },
             firstLoanStartDate:{
                 validators: {
                     notEmpty: {
                         message: '首笔贷款发放月份不能为空'
                     }
                 }
             },

             firstCreditCardStartDate:{
                 validators: {
                     notEmpty: {
                         message: '首张借记卡发卡月份不能为空'
                     }
                 }
             },
             firstSemiCreditCardStartDate:{
                 validators: {
                     notEmpty: {
                         message: '首张准贷记卡发卡月份不能为空'
                     }
                 }
             },
             houseLoanNum:{
                 validators: {
                     notEmpty: {
                         message: '贷款笔数不能为空'
                     },
                     regexp: {
                         regexp: /^[0-9]*$/,
                         message: '输入项格式只能为阿拉伯数字'
                     }
                 }
             },
				 che300Price:{
					 validators: {
						notEmpty: {
		                    message: '价格不能为空'
		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 jingzhenguPrice:{
					 validators: {
						notEmpty: {
		                    message: '价格不能为空'
		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 nakePrice:{
					 validators: {
						notEmpty: {
		                    message: '价格不能为空'
		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 depreciationBase:{
					 validators: {
						notEmpty: {
		                    message: '基数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 creditRatio:{
					 validators: {
						notEmpty: {
		                    message: '授信成数不能为空'
		                },
						regexp: {
							regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
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
				 incomeAmount:{
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
				 companyTel:{
					 validators: {
//							notEmpty: {
//			                    message: '电话不能为空'
//			                },
						regexp: {
			                regexp: /^[0-9]*$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
				 monthIncome:{
					 validators: {
//						notEmpty: {
//		                    message: '金额不能为空'
//		                },
						regexp: {
							regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
			                message: '输入项格式不正确'
			            }
					}
				 },
             courtPersonal:{
                 validators: {
                     notEmpty: {
                         message: '全国法院被执行人信息查询不能为空'
                     },
                 }
             },
             zhixingPersonal:{
                 validators: {
                     notEmpty: {
                         message: '中国执行信息公开网查询(个人)不能为空'
                     },
                 }
             },
             riskPersonal:{
                 validators: {
                     notEmpty: {
                         message: '风险信息网查询(个人)不能为空'
                     },
                 }
             },
             warnPersonal:{
                 validators: {
                     notEmpty: {
                         message: '风险预警网查询(个人)不能为空'
                     },
                 }
             },
             hasJudgement:{
                 validators: {
                     notEmpty: {
                         message: '裁判文书网不能为空'
                     },
                 }
             }
		 }
	}

<!--身份认证回显  begin -->
//身份证认证信息
function  searchIdAuth() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/idAuthInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#sfzList").html("");
            if(status=='0') {
                var html = "<tr align='center'>"
                    + "<td>" +(data.data.isIdAuth==0?"否":"是") + "</td>"
                    //+ "<td>" + data.data.gonganPhotolId + "</td>"
                    + "<td> <img src='" + data.data.idFrontPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td> <img src='" + data.data.idBackPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td><img src='" + data.data.holdIdentifyPhoto + "' style='width:80px;height:40px;/>' </td>"
                    + "<td>" +((data.data.authTime==null||data.data.authTime==undefined)?"":data.data.authTime.split(" ")[0])+ "</td>"
                    + "<td>" + data.data.userName + "</td>"
                    + "<td>" + data.data.idNumber + "</td>"
                    + "<td>" + data.data.nation + "</td>"
                    + "<td>" + data.data.address + "</td>"
                    + "<td>" + data.data.signOrgaization + "</td>"
                    + "<td>" + data.data.validityPeriod + "</td>"
                    + "<td>" + data.data.remark + "</td></tr>";
                $("#sfzList").append(html);
                searchLiveness();
            }

        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}
//活体认证
function  searchLiveness() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/livenessAuthInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#livenessList").html("");
            if(status=='0') {
                var html = "<tr align='center'>"
                    + "<td>" + (data.data.isLivenessAuth==0?"否":"是") + "</td>"
                    + "<td>" +((data.data.livenessAuthTime==null||data.data.livenessAuthTime==undefined)?"":data.data.livenessAuthTime.split(" ")[0])+ "</td>"
                    + "<td> <img src='" + data.data.livenessBlinkPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td> <img src='" + data.data.livenessNodPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td> <img src='" + data.data.livenessMouthPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td> <img src='" + data.data.livenessYawPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td>" + data.data.remark + "</td></tr>";
                $("#livenessList").append(html);
            }

        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

<!--身份认证回显  end -->

function saveOrUpdate(opt){
	var regHz = /^[\u4e00-\u9fa5]{0,}$/;  //汉字校验
	var regNum = /^[0-9]*$/; //数字校验
	var regNumDecimails = /^[0-9]+([.][0-9]{1}){0,1}$/;  //数字或保留一位小数校验
    if (opt=='judicial'){//司法认证
        var fileObj = document.getElementById("courtPersonalPhoto1").files[0];
        var fileUrl = this.uploadFile(fileObj);
        $("#courtPersonalPhoto").val(fileUrl);
        fileObj = document.getElementById("zhixingPersonalPhoto1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#zhixingPersonalPhoto").val(fileUrl);
        fileObj = document.getElementById("riskPersonalPhoto1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#riskPersonalPhoto").val(fileUrl);
        fileObj = document.getElementById("warnPersonalPhoto1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#warnPersonalPhoto").val(fileUrl);

        var courtPersonal = $("#courtPersonal").val();
        var zhixingPersonal = $("#zhixingPersonal").val();
        var riskPersonal = $("#riskPersonal").val();
        var warnPersonal = $("#warnPersonal").val();
        var courtPersonalPhoto = $("#courtPersonalPhoto").val();
        if (!saveOrUpdates.validate4()) {
            return
        }
        if(courtPersonalPhoto==''||courtPersonalPhoto==undefined||courtPersonalPhoto==null){
            Feng.info("请上传全国法院被执行人信息查询(个人)附件！")
            return false;
        }
        var zhixingPersonalPhoto = $("#zhixingPersonalPhoto").val();
        if(zhixingPersonalPhoto==''||zhixingPersonalPhoto==undefined||zhixingPersonalPhoto==null){
            Feng.info("请上传中国执行信息公开网查询(个人)附件！")
            return false;
        }
        var riskPersonalPhoto = $("#riskPersonalPhoto").val();
        if(riskPersonalPhoto==''||riskPersonalPhoto==undefined||riskPersonalPhoto==null){
            Feng.info("请上传风险信息网查询(个人)附件！")
            return false;
        }
        var warnPersonalPhoto = $("#warnPersonalPhoto").val();
        if(warnPersonalPhoto==''||warnPersonalPhoto==undefined||warnPersonalPhoto==null){
            Feng.info("请上传风险预警网查询(个人)附件！")
            return false;
        }
        var id = $("#judicialId").val();
        var applyId = $("#applyId").val();
        var pamType =$("#pamType").val();
        $.ajax({
            type: "POST",
            url: '/judicialAuthInfo/saveOrUpdate',
            dataType: 'json',
            data: {
                'applyId':applyId,
                'type':pamType,
                'id':id,
                'courtPersonal' :courtPersonal,
                'courtPersonalPhoto':courtPersonalPhoto,
                'zhixingPersonal':zhixingPersonal,
                'zhixingPersonalPhoto':zhixingPersonalPhoto,
                'riskPersonal':riskPersonal,
                'riskPersonalPhoto':riskPersonalPhoto,
                'warnPersonal':warnPersonal,
                'warnPersonalPhoto':warnPersonalPhoto
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    Feng.info("保存数据成功！");
                    searchJudicial();
                }else{
                    Feng.info("保存数据失败！"+data.msg);
                }
            },
            error: function() {
                Feng.info("保存数据异常！");

            }
        });
    }else if (opt=='judicialCompany'){//司法认证(企业)
        var fileObj = document.getElementById("courtEnterprisePhoto1").files[0];
        var fileUrl = this.uploadFile(fileObj);
        $("#courtEnterprisePhoto").val(fileUrl)
        fileObj = document.getElementById("zhixingEnterprisePhoto1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#zhixingEnterprisePhoto").val(fileUrl);
        fileObj = document.getElementById("riskEnterprisePhoto1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#riskEnterprisePhoto").val(fileUrl);
        fileObj = document.getElementById("warnEnterprisePhoto1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#warnEnterprisePhoto").val(fileUrl);

        var companyName = $("#judicialCompanyName").val();
        var industry = $("#industry").val();
        var foundTime = $("#foundTime").val();
        var courtEnterprise = $("#courtEnterprise").val();
        var courtEnterprisePhoto = $("#courtEnterprisePhoto").val();
        var zhixingEnterprise = $("#zhixingEnterprise").val();
        var zhixingEnterprisePhoto = $("#zhixingEnterprisePhoto").val();
        var riskEnterprise = $("#riskEnterprise").val();
        var riskEnterprisePhoto = $("#riskEnterprisePhoto").val();
        var warnEnterprise = $("#warnEnterprise").val();
        var warnEnterprisePhoto = $("#warnEnterprisePhoto").val();
        var enterpriseStatus = $("#enterpriseStatus").val();
        var enterpriseRemark = $("#enterpriseRemark").val();
        var remark = $("#companyRemark").val();
        var id = $("#judicialCompanyId").val();
        var applyId = $("#applyId").val();
        var pamType =$("#pamType").val();
        
        if (!saveOrUpdates.validate7()) {
        	return
        }
        
        $.ajax({
            type: "POST",
            url: '/custCompanyInfo/saveOrUpdate',
            dataType: 'json',
            data: {
                'applyId':applyId,
                'type':pamType,
                'id':id,
                'companyName' :companyName,
                'industry':industry,
                'foundTime':foundTime,
                'courtEnterprise':courtEnterprise,
                'courtEnterprisePhoto':courtEnterprisePhoto,
                'courtEnterprisePhoto':courtEnterprisePhoto,
                'zhixingEnterprise':zhixingEnterprise,
                'zhixingEnterprisePhoto' :zhixingEnterprisePhoto,
                'zhixingEnterprisePhoto':zhixingEnterprisePhoto,
                'riskEnterprise':riskEnterprise,
                'riskEnterprisePhoto':riskEnterprisePhoto,
                'warnEnterprise':warnEnterprise,
                'warnEnterprisePhoto':warnEnterprisePhoto,
                'enterpriseStatus':enterpriseStatus,
                'enterpriseRemark':enterpriseRemark,
                'remark':remark
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    Feng.info("保存数据成功！");
                    searchJudicialCompany();
                }else{
                    Feng.info("保存数据失败！"+data.msg);
                }
            },
            error: function() {
                Feng.info("保存数据异常！");

            }
        });
    }else if (opt=='teleComAuth'){//运营商认证
        var id = $("#telecomId").val();
        if(id==null||id==""||id==undefined){
            Feng.info("请先获取数据！");
            return;
        }
        var fileObj = document.getElementById("billDetailUrl1").files[0];
        var fileUrl = this.uploadFile(fileObj);
        $("#billDetailUrl").val(fileUrl)
        fileObj = document.getElementById("reportUrl1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#reportUrl").val(fileUrl);
        fileObj = document.getElementById("compatibleReportUrl1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#compatibleReportUrl").val(fileUrl);


        var applyId = $("#applyId").val();
        var pamType =$("#pamType").val();
        var billDetailUrl = $("#billDetailUrl").val();
        var reportUrl = $("#reportUrl").val();
        var compatibleReportUrl = $("#compatibleReportUrl").val();

        $.ajax({
            type: "POST",
            url: '/telecomAuthInfo/saveOrUpdate',
            dataType: 'json',
            data: {
                'id':id,
                'applyId':applyId,
                'type':pamType,
                'billDetailUrl':billDetailUrl,
                'reportUrl':reportUrl,
                'compatibleReportUrl' :compatibleReportUrl
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    Feng.info("保存数据成功！");
                }else{
                    Feng.info("保存数据失败！"+data.msg);
                }
            },
            error: function() {
                Feng.info("保存数据异常！");

            }
        });
    }else if (opt=='creditAuth'){//信贷认证
        var fileObj = document.getElementById("tdScoreAttachUrl1").files[0];
        var fileUrl = this.uploadFile(fileObj);
        $("#tdScoreAttachUrl").val(fileUrl);
        fileObj = document.getElementById("tdRiskAttachUrl1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#tdRiskAttachUrl").val(fileUrl);
        fileObj = document.getElementById("brAttachUrl1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#brAttachUrl").val(fileUrl);
        fileObj = document.getElementById("judgementAttachUrl1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#judgementAttachUrl").val(fileUrl);

        var applyId = $("#applyId").val();
        var pamType =$("#pamType").val();
        var id = $("#creditAuthId").val();
        var tdScore = $("#tdScore").val();
        var tdScoreAttachUrl = $("#tdScoreAttachUrl").val();
        if (!saveOrUpdates.validate3()) {
            return
        }

        if(tdScoreAttachUrl==''||tdScoreAttachUrl==undefined||tdScoreAttachUrl==null){
            Feng.info("请上传同盾贷前审核，附件！")
            return false;
        }
        var tdRiskAttachUrl = $("#tdRiskAttachUrl").val();
        if(tdRiskAttachUrl==''||tdRiskAttachUrl==undefined||tdRiskAttachUrl==null){
            Feng.info("请上传同盾贷前审核，贷前风险情况附件！")
            return false;
        }
        var brRuleScore = $("#brRuleScore").val();
        var brCreditScore = $("#brCreditScore").val();
        var brAttachUrl = $("#brAttachUrl").val();
        var hasJudgement = $("#hasJudgement").val();
        var judgementAttachUrl = $("#judgementAttachUrl").val();
        if(judgementAttachUrl==''||judgementAttachUrl==undefined||judgementAttachUrl==null){
            Feng.info("请上传裁判文书网附件！")
            return false;
        }

        $.ajax({
            type: "POST",
            url: '/creditAuthInfo/saveOrUpdate',
            dataType: 'json',
            data: {
                'applyId':applyId,
                'type':pamType,
                'id':id,
                'tdScore' :tdScore,
                'tdScoreAttachUrl':tdScoreAttachUrl,
                'tdRiskAttachUrl':tdRiskAttachUrl,
                'brRuleScore':brRuleScore,
                'brCreditScore':brCreditScore,
                'brAttachUrl':brAttachUrl,
                'hasJudgement':hasJudgement,
                'judgementAttachUrl':judgementAttachUrl
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    Feng.info("保存数据成功！");
                    searchCreditAuth();
                }else{
                    Feng.info("保存数据失败！"+data.msg);
                }
            },
            error: function() {
                Feng.info("保存数据异常！");

            }
        });
    }else if (opt=='creditReport'){//央行征信
        var fileObj = document.getElementById("reportAttachUrl1").files[0];
        var fileUrl = this.uploadFile(fileObj);
        $("#reportAttachUrl").val(fileUrl);

        var applyId = $("#applyId").val();
        var pamType =$("#pamType").val();
        var id = $("#creditReportId").val();
        var collectionDate = $("#collectionDate").val();
        var houseLoanNum = $("#houseLoanNum").val();
        var houseBussLoanNum = $("#houseBussLoanNum").val();
        var otherLoanNum = $("#otherLoanNum").val();
        var firstLoanStartDate = $("#firstLoanStartDate").val();
        var creditCardNum = $("#creditCardNum").val();
        var firstCreditCardStartDate = $("#firstCreditCardStartDate").val();
        var semiCreditCardNum = $("#semiCreditCardNum").val();
        var firstSemiCreditCardStartDate =$("#firstSemiCreditCardStartDate").val();
        var selfDeclareNum = $("#selfDeclareNum").val();
        var objectionNum = $("#objectionNum").val();
        var loanOverdueNum = $("#loanOverdueNum").val();
        var loanOverdueMonthNum = $("#loanOverdueMonthNum").val();
        var loanMaxAmount =  $("#loanMaxAmount").val();
        var loanMaxMonthNum = $("#loanMaxMonthNum").val();
        var cardOverdueNum = $("#cardOverdueNum").val();
        var cardMonthNum = $("#cardMonthNum").val();
        var cardOverdueMaxAmount =  $("#cardOverdueMaxAmount").val();
        var cardMaxMonthNum = $("#cardMaxMonthNum").val();
        var semiCardOverdueNum = $("#semiCardOverdueNum").val();
        var semiCardMonthNum = $("#semiCardMonthNum").val();
        var semiCardMaxAmount = $("#semiCardMaxAmount").val();
        var semiCardMaxMonthNum = $("#semiCardMaxMonthNum").val();
        var loanLegalOrgNum = $("#loanLegalOrgNum").val();
        var loanOrgNum = $("#loanOrgNum").val();
        var loanNum = $("#loanNum").val();
        var loanTotalAmount = $("#loanTotalAmount").val();
        var loanLeftAmount = $("#loanLeftAmount").val();
        var loanTotalMonthAmount = $("#loanTotalMonthAmount").val();
        var cardLegalOrgNum = $("#cardLegalOrgNum").val();
        var cardOrgNum = $("#cardOrgNum").val();
        var cardAccountNum = $("#cardAccountNum").val();
        var cardTotalAmount = $("#cardTotalAmount").val();
        var cardMaxAmount = $("#cardMaxAmount").val();
        var cardMinAmount = $("#cardMinAmount").val();
        var cardUsedAmount = $("#cardUsedAmount").val();
        var cardAvgAmount = $("#cardAvgAmount").val();
        var reportAttachUrl = $("#reportAttachUrl").val();
        
        if (!saveOrUpdates.validate11()) {
        	return
        }
        
        $.ajax({
            type: "POST",
            url: '/creditReport/saveOrUpdate',
            dataType: 'json',
            data: {
                'applyId':applyId,
                'type':pamType,
                'id':id,
                'collectionDate':collectionDate,
                'houseLoanNum':houseLoanNum,
                'houseBussLoanNum':houseBussLoanNum,
                'otherLoanNum':otherLoanNum,
                'firstLoanStartDate':firstLoanStartDate,
                'creditCardNum':creditCardNum,
                'firstCreditCardStartDate':firstCreditCardStartDate,
                'semiCreditCardNum':semiCreditCardNum,
                'firstSemiCreditCardStartDate':firstSemiCreditCardStartDate,
                'selfDeclareNum':selfDeclareNum,
                'objectionNum':objectionNum,
                'loanOverdueNum':loanOverdueNum,
                'loanOverdueMonthNum':loanOverdueMonthNum,
                'loanMaxAmount':loanMaxAmount,
                'loanMaxMonthNum':loanMaxMonthNum,
                'cardOverdueNum':cardOverdueNum,
                'cardMonthNum':cardMonthNum,
                'cardOverdueMaxAmount':cardOverdueMaxAmount,
                'cardMaxMonthNum':cardMaxMonthNum,
                'semiCardOverdueNum':semiCardOverdueNum,
                'semiCardMonthNum':semiCardMonthNum,
                'semiCardMaxAmount':semiCardMaxAmount,
                'semiCardMaxMonthNum':semiCardMaxMonthNum,
                'loanLegalOrgNum':loanLegalOrgNum,
                'loanOrgNum':loanOrgNum,
                'loanNum':loanNum,
                'loanTotalAmount':loanTotalAmount,
                'loanLeftAmount':loanLeftAmount,
                'loanTotalMonthAmount':loanTotalMonthAmount,
                'cardLegalOrgNum':cardLegalOrgNum,
                'cardOrgNum':cardOrgNum,
                'cardAccountNum':cardAccountNum,
                'cardTotalAmount':cardTotalAmount,
                'cardMaxAmount':cardMaxAmount,
                'cardMinAmount':cardMinAmount,
                'cardUsedAmount':cardUsedAmount,
                'cardAvgAmount':cardAvgAmount,
                'reportAttachUrl':reportAttachUrl
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    Feng.info("保存数据成功！");
                    searchCreditReport();
                }else{
                    Feng.info("保存数据失败！"+data.msg);
                }
            },
            error: function() {
                Feng.info("保存数据异常！");

            }
        });
    }else if (opt=='price'){//预估可贷金额测算
        var fileObj = document.getElementById("che300AttachUrl1").files[0];
        var fileUrl = this.uploadFile(fileObj);
        $("#che300AttachUrl").val(fileUrl);

        fileObj = document.getElementById("jingzhenguAttachUrl1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#jingzhenguAttachUrl").val(fileUrl);
        var che300Price = $("#che300Price").val();
        var che300AttachUrl = $("#che300AttachUrl").val();
        var jingzhenguPrice = $("#jingzhenguPrice").val();
        var jingzhenguAttachUrl = $("#jingzhenguAttachUrl").val();
        var nakePrice = $("#nakePrice").val();
        var depreciationBase = $("#depreciationBase").val();
        var depreciationRatio = $("#depreciationRatio").val();
        var creditRatio = $("#creditRatio").val();
        var tsingnuoPrice = $("#tsingnuoPrice").val();
        var id = $("#priceId").val();
        var applyId = $("#applyId").val();
        var pamType =$("#pamType").val();
        
        if (!saveOrUpdates.validate2()) {
        	return
        }
        
        $.ajax({
            type: "POST",
            url: '/carPriceInfo/saveOrUpdate',
            dataType: 'json',
            data: {
                'applyId':applyId,
                'type':pamType,
                'id':id,
                'che300Price' :che300Price,
                'che300AttachUrl':che300AttachUrl,
                'jingzhenguPrice':jingzhenguPrice,
                'jingzhenguAttachUrl':jingzhenguAttachUrl,
                'nakePrice':nakePrice,
                'depreciationBase':depreciationBase,
                'depreciationRatio':depreciationRatio,
                'creditRatio':creditRatio,
                'tsingnuoPrice':tsingnuoPrice
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    Feng.info("保存数据成功！");
                    searchCarPrice();
                }else{
                    Feng.info("保存数据失败！"+data.msg);
                }
            },
            error: function() {
                Feng.info("保存数据异常！");

            }
        });
    }else if (opt=='income'){//收入方式认证
        var fileObj = document.getElementById("attachUrl1").files[0];
        var fileUrl = this.uploadFile(fileObj);
        $("#attachUrl").val(fileUrl);

        var id = $("#incomeId").val();
        var incomeType = $("#incomeType").val();
        var incomeAmount = $("#incomeAmount").val();
        var incomeConfirmAmount = $("#incomeConfirmAmount").val();
        var dti = $("#dti").val();
        var loanAmount = $("#loanAmount").val();
        var attachUrl = $("#attachUrl").val();
        var remark = $("#incomeRemark").val();
        var applyId = $("#applyId").val();
        var pamType =$("#pamType").val();
        
        if (!saveOrUpdates.validate1()) {
        	return
        }
        
        $.ajax({
            type: "POST",
            url: '/custIncomeInfo/saveOrUpdate',
            dataType: 'json',
            data: {
                'applyId':applyId,
                'type':pamType,
                'id':id,
                'incomeType' :incomeType,
                'incomeAmount':incomeAmount,
                'incomeConfirmAmount':incomeConfirmAmount,
                'dti':dti,
                'loanAmount':loanAmount,
                'attachUrl':attachUrl,
                'remark':remark
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    Feng.info("保存数据成功！");
                    searchIncome();
                }else{
                    Feng.info("保存数据失败！"+data.msg);
                }
            },
            error: function() {
                Feng.info("保存数据异常！");

            }
        });

    }else if (opt=='gongjie'){//担保人/共借人
        var fileObj = document.getElementById("idFrontPhotoUrl1").files[0];
        var fileUrl = this.uploadFile(fileObj);
        $("#idFrontPhotoUrl").val(fileUrl);

        fileObj = document.getElementById("idBackPhotoUrl1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#idBackPhotoUrl").val(fileUrl);

        fileObj = document.getElementById("companyAttachUrl1").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#companyAttachUrl").val(fileUrl);

        var applyId = $("#applyId").val();
        var id = $("#gongjieId").val();
        var name = $("#name").val();
        var mobile = $("#mobile").val();
        var sex = $("#sex").val();
        var certId = $("#certId").val();
        var idFrontPhotoUrl = $("#idFrontPhotoUrl").val();
        var idBackPhotoUrl = $("#idBackPhotoUrl").val();

        var marryStatus = $("#marryStatus").val();
        var relation = $("#relation").val();
        var liveAddress = $("#liveAddress").val();
        var occupationType = $("#occupationType").val();
        var companyName = $("#companyName").val();
        var companyType = $("#companyType").val();
        var companyAddress = $("#companyAddress").val();

        var companyTel = $("#companyTel").val();
        var department = $("#department").val();
        var job = $("#job").val();
        var monthIncome = $("#monthIncome").val();
        var companyAttachUrl = $("#companyAttachUrl").val();
        var remark = $("#gongjieRemark").val();
        
        if (!saveOrUpdates.validate6()) {
        	return
        }
        

        $.ajax({
            type: "POST",
            url: '/gongjieInfo/saveOrUpdate',
            dataType: 'json',
            data: {
                'applyId':applyId,
                'id' : id,
                'name' : name,
                'mobile' : mobile,
                'sex' : sex,
                'certId' : certId,
                'idFrontPhotoUrl' : idFrontPhotoUrl,
                'idBackPhotoUrl' : idBackPhotoUrl,
                'marryStatus' : marryStatus,
                'relation' : relation,
                'liveAddress' : liveAddress,
                'occupationType' : occupationType,
                'companyName' : companyName,
                'companyType' : companyType,
                'companyAddress' : companyAddress,
                'companyTel' : companyTel,
                'department' : department,
                'job' : job,
                'monthIncome' : monthIncome,
                'companyAttachUrl' :companyAttachUrl,
                'remark' : remark
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    Feng.info("保存数据成功！");
                }else{
                    Feng.info("保存数据失败！"+data.msg);
                }
            },
            error: function() {
                Feng.info("保存数据异常！");

            }
        });
    }else if (opt=='interview'){//面审意见

        var fileObj = document.getElementById("sceneEvidenceUrl11").files[0];
        var fileUrl = this.uploadFile(fileObj);
        $("#sceneEvidenceUrl1").val(fileUrl);

        fileObj = document.getElementById("sceneEvidenceUrl21").files[0];
        fileUrl = this.uploadFile(fileObj);
        $("#sceneEvidenceUrl2").val(fileUrl);

        var applyId = $("#applyId").val();
        var id = $("#interviewId").val();
        var overview = $("#overview").val();
        var interviewResult = $("#interviewResult").val();
        var rejectionReason = $("#rejectionReason").val();
        var loanAmount = $("#interviewLoanAmount").val();
        var loanPeriod = $("#loanPeriod").val();
        var sceneEvidenceUrl1 = $("#sceneEvidenceUrl1").val();
        var sceneEvidenceUrl2 = $("#sceneEvidenceUrl2").val();
        var remark = $("#interviewRemark").val();

        $.ajax({
            type: "POST",
            url: '/custInterviewInfo/saveOrUpdate',
            dataType: 'json',
            data: {
                'applyId':applyId,
                'id' : id,
                'overview' : overview,
                'interviewResult' : interviewResult,
                'rejectionReason' : rejectionReason,
                'loanAmount' : loanAmount,
                'loanPeriod':loanPeriod,
                'sceneEvidenceUrl1':sceneEvidenceUrl1,
                'sceneEvidenceUrl2':sceneEvidenceUrl2,
                'remark' : remark
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    Feng.info("保存数据成功！");
                }else{
                    Feng.info("保存数据失败！"+data.msg);
                }
            },
            error: function() {
                Feng.info("保存数据异常！");

            }
        });
    }

}

/*缩略图*/
function UploadImageicc(ev){
    var  idImgurl=$(ev).parent().nextAll()[0].children[0].id;
    var file = document.getElementById($(ev)[0].id).files[0];
    //判断 FileReader 是否被浏览器所支持
    if (!window.FileReader) return;
    //var file = evv.target.files[0];
    if(file.type.match('image/!*')){//如果是图片，则显示缩略图
        var reader = new FileReader();  // 创建FileReader对象
        reader.readAsDataURL(file); // 读取file对象，读取完毕后会返回result 图片base64格式的结果
        reader.onload = function(e){
            document.getElementById(idImgurl).src = e.target.result;
            document.getElementById(idImgurl).style ="width:100%;height:75px;background-color: #cccccc47";
        }
    }

}

function uploadFile(file) {
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
                returnUrl = data.data;
            }else{
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

//设置回显图片的位置
function setImgPx() {
    var imgList = $("img");
    for(var i=0;i<imgList.length;i++){
        if(imgList.eq(i).attr("src")!=null && imgList.eq(i).attr("src")!=''&&imgList.eq(i).attr("src")!=undefined){
            imgList.eq(i).attr("style","width:100%;height:75px;background-color: #cccccc47");
        }
    }

}
<!--司法认证回显-->
function  searchJudicial() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/judicialAuthInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#judicialId").val(data.data.id);
                $("#courtPersonal").val(data.data.courtPersonal);
                $("#courtPersonalPhoto").val(data.data.courtPersonalPhoto);
                if(data.data.courtPersonalPhoto!=""&&data.data.courtPersonalPhoto!=null){
                    $("#courtPersonalPhotoUrlmImg").attr("src",idPicUrls+data.data.courtPersonalPhoto);
                }
                $("#zhixingPersonal").val(data.data.zhixingPersonal);
                $("#zhixingPersonalPhoto").val(data.data.zhixingPersonalPhoto);
                if(data.data.zhixingPersonalPhoto!=""&&data.data.zhixingPersonalPhoto!=null){
                    $("#zhixingPersonalPhotoUrlmImg").attr("src",idPicUrls+data.data.zhixingPersonalPhoto);
                }
                $("#riskPersonal").val(data.data.riskPersonal);
                $("#riskPersonalPhoto").val(data.data.riskPersonalPhoto);
                if(data.data.riskPersonalPhoto!=""&&data.data.riskPersonalPhoto!=null){
                    $("#riskPersonalPhotoUrlmImg").attr("src",idPicUrls+data.data.riskPersonalPhoto);
                }
                $("#warnPersonal").val(data.data.warnPersonal);
                $("#warnPersonalPhoto").val(data.data.warnPersonalPhoto);
                if(data.data.warnPersonalPhoto!=""&&data.data.warnPersonalPhoto!=null){
                    $("#warnPersonalPhotoUrlmImg").attr("src",idPicUrls+data.data.warnPersonalPhoto);
                }
                setImgPx();
            }else{
                Feng.info("查询失败！"+data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

<!--运营商模块  begin =================-->

//获取数据
function getTelecomData() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    var userName = $("#userName").val();
    var idNum =$("#idNum").val();
    var mobile = $("#mobile").val();
    if (!saveOrUpdates.validate()) {
    	return
    }

    $.ajax({
        type: "POST",
        url: '/telecomAuthInfo/getTelecomData',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType,
            'userName':userName,
            'idNum':idNum,
            'mobile':mobile
        },
        success: function(data) {
            var status = data.status;
            if(status==0){//状态为0 直接查询信息，不需要调用接口
                $("#telecomId").val(data.telecomAuthId);
                searchTelecomBaseInfo();
                searchTelecomAuthInfo();
                searchCallRiskInfo();
                searchFriendCircle();
                searchRoamInfo(3);
                searchCallDetail(3);
                $("#showControl").show();

            }else{
                Feng.info(data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}



//运营商信息回显
function  searchTelecomBaseInfo() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/telecomBasicInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#telecomBaseInfo").html("");
            if(status=='0'){
                var html="<tr align='center'>"
                    +"<td>"+data.data.mobile+"</td>"
                    +"<td>"+(data.data.auditStatus==3?"认证完成":"")+"</td>"
                    +"<td>"+data.data.auditTime+"</td></tr>";
                $("#telecomBaseInfo").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

//运营商认证信息回显
function  searchTelecomAuthInfo() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/telecomAuthInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#telecomAuthInfo").html("");
            if(status=='0'){
                var html="<tr align='center'>"
                    +"<td>"+(data.data.isRealAuth==0?"否":"是")+"</td>"
                    +"<td>"+data.data.nativePlace+"</td>"
                    +"<td>"+data.data.inTime+"</td>"
                    +"<td>"+data.data.active3m+"</td>"
                    +"<td>"+data.data.active6m+"</td></tr>";
                $("#telecomAuthInfo").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}


//运营商风险分析回显
function  searchCallRiskInfo() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/telecomCallRiskAnalysis/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#telecomCallRisk").html("");
            if(status=='0'){
                var html="";
                $.each(data.data,function(i){
                    html+="<tr align='center'>"
                        +"<td>"+data.data[i].analysisItem+"</td>"
                        +"<td>"+data.data[i].analysisDesc+"</td>"
                        +"<td>"+data.data[i].callCnt1m+"</td>"
                        +"<td>"+data.data[i].callCnt3m+"</td>"
                        +"<td>"+data.data[i].callCnt6m+"</td>"
                        +"<td>"+data.data[i].avgCallCnt3m+"</td>"
                        +"<td>"+data.data[i].avgCallCnt6m+"</td>"
                        +"<td>"+data.data[i].callTime1m+"</td>"
                        +"<td>"+data.data[i].callTime3m+"</td>"
                        +"<td>"+data.data[i].callTime6m+"</td>"
                        +"<td>"+data.data[i].avgCallTime3m+"</td>"
                        +"<td>"+data.data[i].avgCallTime6m+"</td></tr>";
                });

                $("#telecomCallRisk").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

//通讯录朋友圈TOP项回显
function  searchFriendCircle() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/telecomFriendCircle/selectByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#telecomFriendCircle").html("");
            if(status=='0'){
                var html="";
                $.each(data.data,function(i){
                    html+="<tr align='center'>"
                        +"<td>"+data.data[i].peerNumber+"</td>"
                        +"<td>"+data.data[i].peerNumLoc+"</td>"
                        +"<td>"+data.data[i].groupName+"</td>"
                        +"<td>"+data.data[i].companyName+"</td>"
                        +"<td>"+data.data[i].callCnt+"</td>"
                        +"<td>"+data.data[i].callTime+"</td>"
                        +"<td>"+data.data[i].dialCnt+"</td>"
                        +"<td>"+data.data[i].dialedCnt+"</td>"
                        +"<td>"+data.data[i].dialTime+"</td>"
                        +"<td>"+data.data[i].dialedTime+"</td>"
                        +"<td>"+(data.data[i].keyTop=="peer_num_top3_3m"?"近3月top3":"近6月top3")+"</td></tr>";
                });

                $("#telecomFriendCircle").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

//运营商漫游信息回显
function  searchRoamInfo(o) {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    var pageNum = null;
    if(o==-1){//前一页
        pageNum = $("#prePage").val();
        if(pageNum==0){
            Feng.info("已经是第一页了哦！");
            return ;
        }
    }else  if(o==1){//后一页
        pageNum = $("#nextPage").val();
        if(pageNum==0){
            Feng.info("已经是最后一页了哦！");
            return ;
        }
    }else  if(o==0){//首页
        pageNum = 1;
    }else  if(o==2){//尾页
        pageNum = $("#pages").text();
    }

    $.ajax({
        type: "POST",
        url: '/telecomRoamInfo/pageQuery',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType,
            'pageNum':pageNum
        },
        success: function(data) {
            var status = data.status;
            $("#telecomRoamInfo").html("");
            if(status=='0'){
                var html="";
                $.each(data.data.list,function(i){
                    html+="<tr align='center'>"
                        +"<td>"+(i+1)+"</td>"
                        +"<td>"+data.data.list[i].location+"</td>"
                        +"<td>"+data.data.list[i].roamDayCnt3m+"</td>"
                        +"<td>"+data.data.list[i].roamDayCnt6m+"</td>"
                        +"<td>"+data.data.list[i].continueRoamCnt3m+"</td>"
                        +"<td>"+data.data.list[i].continueRoamCnt6m+"</td>"
                        +"<td>"+data.data.list[i].maxRoamDayCnt3m+"</td>"
                        +"<td>"+data.data.list[i].maxRoamDayCnt6m+"</td></tr>";
                });
                $("#total").text(data.data.total);
                $("#pages").text(data.data.pages);
                $("#pageNum").text(data.data.pageNum);
                $("#prePage").val(data.data.prePage);
                $("#nextPage").val(data.data.nextPage);


                $("#telecomRoamInfo").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}


//通话记录详细信息回显
function  searchCallDetail(o) {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    var pageNum = null;
    if(o==-1){//前一页
        pageNum = $("#callPrePage").val();
        if(pageNum==0){
            Feng.info("已经是第一页了哦！");
            return ;
        }
    }else  if(o==1){//后一页
        pageNum = $("#callNextPage").val();
        if(pageNum==0){
            Feng.info("已经是最后一页了哦！");
            return ;
        }
    }else  if(o==0){//首页
        pageNum = 1;
    }else  if(o==2){//尾页
        pageNum = $("#callPages").text();
    }

    $.ajax({
        type: "POST",
        url: '/telecomCallContactDetail/pageQuery',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType,
            'pageNum':pageNum
        },
        success: function(data) {
            var status = data.status;
            $("#telecomCallDetail").html("");
            if(status=='0'){
                var html="";
                $.each(data.data.list,function(i){
                    html+="<tr align='center'>"
                        +"<td>"+(i+1)+"</td>"
                        +"<td>"+data.data.list[i].city+"</td>"
                        +"<td>"+data.data.list[i].peerNum+"</td>"
                        +"<td>"+(data.data.list[i].isEmergency==0?"否":"是")+"</td>"
                        +"<td>"+data.data.list[i].pRelation+"</td>"
                        +"<td>"+data.data.list[i].groupName+"</td>"
                        +"<td>"+data.data.list[i].companyName+"</td>"
                        +"<td>"+data.data.list[i].callCnt1w+"</td>"
                        +"<td>"+data.data.list[i].callCnt1m+"</td>"
                        +"<td>"+data.data.list[i].callCnt3m+"</td>"
                        +"<td>"+data.data.list[i].callCnt6m+"</td>"
                        +"<td>"+data.data.list[i].callTime3m+"</td>"
                        +"<td>"+data.data.list[i].callTime6m+"</td>"
                        +"<td><a style='color: #30af90;' href='javascript:showCallDetail("+data.data.list[i].id+")'>查看详细</a></td></tr>";
                });
                $("#callTotal").text(data.data.total);
                $("#callPages").text(data.data.pages);
                $("#callPageNum").text(data.data.pageNum);
                $("#callPrePage").val(data.data.prePage);
                $("#callNextPage").val(data.data.nextPage);


                $("#telecomCallDetail").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}
//显示通话记录详细信息
function showCallDetail(id) {

    var index = layer.open({
        type: 2,
        title: '通话记录详细信息',
        area: ['1236px', '750px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + "/telecomCallContactDetail/showCallDetail?id="+id
    });
    this.layerIndex = index;

}
//点击tab页的运营商认证
function  searchTeleComAuth() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/telecomAuthInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            if(status==0){
                $("#telecomId").val(data.data.id);
                $("#billDetailUrl").val(data.data.billDetailUrl);
                $("#billDetailUrlmImg").attr("src",idPicUrls+data.data.billDetailUrl);
                $("#reportUrl").val(data.data.reportUrl);
                $("#reportUrlmImg").attr("src",idPicUrls+data.data.reportUrl);
                $("#compatibleReportUrl").val(data.data.compatibleReportUrl);
                $("#compatibleReportUrlmImg").attr("src",idPicUrls+data.data.compatibleReportUrl);
                setImgPx();

                searchTelecomBaseInfo();
                searchTelecomAuthInfo();
                searchCallRiskInfo();
                searchFriendCircle();
                searchRoamInfo(3);
                searchCallDetail(3);
                $("#showControl").show();

            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}
<!--运营商模块  end===================================-->

<!--信贷认证回显-->
function  searchCreditAuth() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/creditAuthInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#creditAuthId").val(data.data.id);
                $("#tdScore").val(data.data.tdScore);
                $("#tdScoreAttachUrl").val(data.data.tdScoreAttachUrl);
                if(data.data.tdScoreAttachUrl!=""&&data.data.tdScoreAttachUrl!=null){
                    $("#tdScoreAttachUrlmImg").attr("src",idPicUrls+data.data.tdScoreAttachUrl);
                }
                $("#tdRiskAttachUrl").val(data.data.tdRiskAttachUrl);
                if(data.data.tdRiskAttachUrl!=""&&data.data.tdRiskAttachUrl!=null){
                    $("#tdRiskAttachUrlmImg").attr("src",idPicUrls+data.data.tdRiskAttachUrl);
                }
                $("#brRuleScore").val(data.data.brRuleScore);
                $("#brCreditScore").val(data.data.brCreditScore);
                $("#brAttachUrl").val(data.data.brAttachUrl);
                if(data.data.brAttachUrl!=""&&data.data.brAttachUrl!=null){
                    $("#brAttachUrlmImg").attr("src",idPicUrls+data.data.brAttachUrl);
                }
                $("#hasJudgement").val(data.data.hasJudgement);
                $("#judgementAttachUrl").val(data.data.judgementAttachUrl);
                if(data.data.judgementAttachUrl!=""&&data.data.judgementAttachUrl!=null){
                    $("#judgementAttachUrlmImg").attr("src",idPicUrls+data.data.judgementAttachUrl);
                }
                setImgPx();

            }else{
                Feng.info("查询失败！"+data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

<!--央行征信(信息概要)回显-->
function  searchCreditReport() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/creditReport/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#creditReportId").val(data.data.id);
                $("#collectionDate").val((data.data.collectionDate==null||data.data.collectionDate==undefined)?"":data.data.collectionDate.split(" ")[0]);
                $("#houseLoanNum").val(data.data.houseLoanNum);
                $("#houseBussLoanNum").val(data.data.houseBussLoanNum);
                $("#otherLoanNum").val(data.data.otherLoanNum);
                $("#firstLoanStartDate").val((data.data.firstLoanStartDate==null||data.data.firstLoanStartDate==undefined)?"":data.data.firstLoanStartDate.split(" ")[0]);
                $("#creditCardNum").val(data.data.creditCardNum);
                $("#firstCreditCardStartDate").val((data.data.firstCreditCardStartDate==null||data.data.firstCreditCardStartDate==undefined)?"":data.data.firstCreditCardStartDate.split(" ")[0]);
                $("#semiCreditCardNum").val(data.data.semiCreditCardNum);
                $("#firstSemiCreditCardStartDate").val((data.data.firstSemiCreditCardStartDate==null||data.data.firstSemiCreditCardStartDate==undefined)?"":data.data.firstSemiCreditCardStartDate.split(" ")[0]);
                $("#selfDeclareNum").val(data.data.selfDeclareNum);
                $("#objectionNum").val(data.data.objectionNum);
                $("#loanOverdueNum").val(data.data.loanOverdueNum);
                $("#loanOverdueMonthNum").val(data.data.loanOverdueMonthNum);
                $("#loanMaxAmount").val(data.data.loanMaxAmount);
                $("#loanMaxMonthNum").val(data.data.loanMaxMonthNum);
                $("#cardOverdueNum").val(data.data.cardOverdueNum);
                $("#cardMonthNum").val(data.data.cardMonthNum);
                $("#cardOverdueMaxAmount").val(data.data.cardOverdueMaxAmount);
                $("#cardMaxMonthNum").val(data.data.cardMaxMonthNum);
                $("#semiCardOverdueNum").val(data.data.semiCardOverdueNum);
                $("#semiCardMonthNum").val(data.data.semiCardMonthNum);
                $("#semiCardMaxAmount").val(data.data.semiCardMaxAmount);
                $("#semiCardMaxMonthNum").val(data.data.semiCardMaxMonthNum);
                $("#loanLegalOrgNum").val(data.data.loanLegalOrgNum);
                $("#loanOrgNum").val(data.data.loanOrgNum);
                $("#loanNum").val(data.data.loanNum);
                $("#loanTotalAmount").val(data.data.loanTotalAmount);
                $("#loanLeftAmount").val(data.data.loanLeftAmount);
                $("#loanTotalMonthAmount").val(data.data.loanTotalMonthAmount);
                $("#cardLegalOrgNum").val(data.data.cardLegalOrgNum);
                $("#cardOrgNum").val(data.data.cardOrgNum);
                $("#cardAccountNum").val(data.data.cardAccountNum);
                $("#cardTotalAmount").val(data.data.cardTotalAmount);
                $("#cardMaxAmount").val(data.data.cardMaxAmount);
                $("#cardMinAmount").val(data.data.cardMinAmount);
                $("#cardUsedAmount").val(data.data.cardUsedAmount);
                $("#cardAvgAmount").val(data.data.cardAvgAmount);
                $("#reportAttachUrl").val(data.data.reportAttachUrl);
                if(data.data.reportAttachUrl!=""&&data.data.reportAttachUrl!=null){
                    $("#reportAttachUrlmImg").attr("src",idPicUrls+data.data.reportAttachUrl);
                }
                setImgPx();

            }else{
                Feng.info("查询失败！"+data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

<!--预估可贷金额测算回显-->
function  searchCarPrice() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/carPriceInfo/findByApplyId',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#priceId").val(data.data.id);
                $("#che300Price").val(data.data.che300Price);
                $("#che300AttachUrl").val(data.data.che300AttachUrl);
                if(data.data.che300AttachUrl!=""&&data.data.che300AttachUrl!=null){
                    $("#che300AttachUrlmImg").attr("src",idPicUrls+data.data.che300AttachUrl);
                }
                $("#nakePrice").val(data.data.nakePrice);
                $("#jingzhenguPrice").val(data.data.jingzhenguPrice);
                $("#jingzhenguAttachUrl").val(data.data.jingzhenguAttachUrl);
                if(data.data.jingzhenguAttachUrl!=""&&data.data.jingzhenguAttachUrl!=null){
                    $("#jingzhenguAttachUrlmImg").attr("src",idPicUrls+data.data.jingzhenguAttachUrl);
                }
                $("#depreciationBase").val(data.data.depreciationBase);
                $("#depreciationRatio").val(data.data.depreciationRatio);
                $("#creditRatio").val(data.data.creditRatio);
                $("#tsingnuoPrice").val(data.data.tsingnuoPrice);
                setImgPx();
            }else{
                Feng.info("查询失败！"+data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}
<!--收入方式认证回显-->
function  searchIncome() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/custIncomeInfo/findByApplyId',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#incomeId").val(data.data.id);
                $("#incomeType").val(data.data.incomeType);
                $("#incomeConfirmAmount").val(data.data.incomeConfirmAmount);
                $("#incomeAmount").val(data.data.incomeAmount);
                $("#dti").val(data.data.dti);
                $("#loanAmount").val(data.data.loanAmount);
                $("#incomeRemark").val(data.data.remark);
                $("#attachUrl").val(data.data.attachUrl);
                if(data.data.attachUrl!=""&&data.data.attachUrl!=null){
                    $("#attachUrlmImg").attr("src",idPicUrls+data.data.attachUrl);
                }
                if(data.data.creditLoanMonthAmount != null && data.data.creditLoanMonthAmount != undefined){
                    $("#creditLoanMonthAmount").val(data.data.creditLoanMonthAmount);
                }
                setImgPx();
            }else{
                Feng.info("查询失败！"+data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

<!--担保人/共借人回显-->
function  searchGongjieren() {
    var applyId = $("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/gongjieInfo/findByCustId',
        dataType: 'json',
        data: {
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#gongjieId").val(data.data.id);
                $("#name").val(data.data.name);
                $("#mobile").val(data.data.mobile);
                $("#sex").val(data.data.sex);
                $("#certId").val(data.data.certId);
                $("#idFrontPhotoUrl").val(data.data.idFrontPhotoUrl);
                $("#idFrontPhotoUrlmImg").attr("src",idPicUrls+data.data.idFrontPhotoUrl);
                $("#idBackPhotoUrl").val(data.data.idBackPhotoUrl);
                $("#idBackPhotoUrlmImg").attr("src",idPicUrls+data.data.idBackPhotoUrl);

                $("#marryStatus").val(data.data.marryStatus);
                $("#relation").val(data.data.relation);
                $("#liveAddress").val(data.data.liveAddress);
                $("#occupationType").val(data.data.occupationType);
                $("#companyName").val(data.data.companyName);
                $("#companyType").val(data.data.companyType);
                $("#companyAddress").val(data.data.companyAddress);

                $("#companyTel").val(data.data.companyTel);
                $("#department").val(data.data.department);
                $("#job").val(data.data.job);
                $("#monthIncome").val(data.data.monthIncome);
                $("#companyAttachUrl").val(data.data.companyAttachUrl);
                $("#companyAttachUrlmImg").attr("src",idPicUrls+data.data.companyAttachUrl);
                $("#gongjieRemark").val(data.data.remark);
                setImgPx();
            }else{
                Feng.info("查询失败！"+data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

<!--财力证明木块  begin=======================================-->
//财力证明回显
function  searchFinace() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/custFinanceInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#finaceList").html("");
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<tr align='center'><td><input type='checkbox' value='"+data.data[i].id+"' name='financeChecked'/></td>"
                        +"<td>"+data.data[i].finTypeName+"</td>"
                        +"<td>"+data.data[i].propertyName+"</td>"
                        +"<td>"+data.data[i].statusName+"</td>"
                        +"<td>"+data.data[i].num+"</td>"
                        +"<td>"+data.data[i].remark+"</td></tr>";
                });
                $("#finaceList").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

//修改财力证明信息
var upFinanceId = "";
function updateFinance(){
    if($('input[name=financeChecked]:checked').length!=1){
        Feng.info("只能选择一条记录修改");
        return ;
    }
    upFinanceId = $('input[name=financeChecked]:checked').val();
    showAddFinance('update');
}

//跳转财力证明添加页面
function showAddFinance(opr){
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    var url="";
    if(opr=='add'){
        url='/custFinanceInfo/showAddFinance?applyId='+applyId+'&type='+pamType+"&id=-1";
    }else if(opr='update'){
        url='/custFinanceInfo/showAddFinance?applyId='+applyId+'&type='+pamType+"&id="+upFinanceId;
    }

    var index = layer.open({
        type: 2,
        title: '添加财力证明',
        area: ['780px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + url
    });
    this.layerIndex = index;
}

//删除财力证明
function delFinance() {
    if($('input[name=financeChecked]:checked').length==0){
        Feng.info("请选择至少一条记录删除");
        return ;
    }
    var ids ="";
    $.each($('input[name=financeChecked]:checked'),function () {
        ids+=$(this).val()+",";
    });
    $.ajax({
        type: "POST",
        url: '/custFinanceInfo/deleteByIds',
        dataType: 'json',
        data: {
            'ids':ids
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("删除数据成功！");
                searchFinace();
            }
        },
        error: function() {
            Feng.info("删除数据异常！");

        }
    });

}


<!--财力证明  end  ==================================-->

<!--面审意见回显-->
function  searchInterview() {
    var applyId = $("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/custInterviewInfo/findByApplyId',
        dataType: 'json',
        data: {
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#interviewId").val(data.data.id);
                $("#overview").val(data.data.overview);
                $("#interviewResult").val(data.data.interviewResult);
                $("#rejectionReason").val(data.data.rejectionReason);
                $("#interviewLoanAmount").val(data.data.loanAmount);
                $("#loanPeriod").val(data.data.loanPeriod);
                $("#sceneEvidenceUrl1").val(data.data.sceneEvidenceUrl1);
                $("#sceneEvidenceUrl1mImg").attr("src",idPicUrls+data.data.sceneEvidenceUrl1);
                $("#sceneEvidenceUrl2").val(data.data.sceneEvidenceUrl2);
                $("#sceneEvidenceUrl2mImg").attr("src",idPicUrls+data.data.sceneEvidenceUrl2);
                $("#interviewRemark").val(data.data.remark);
                setImgPx();
            }else{
                Feng.info("查询失败！"+data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}


<!--司法认证企业回显-->
function  searchJudicialCompany() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/custCompanyInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#judicialCompanyId").val(data.data.id);
                $("#judicialCompanyName").val(data.data.companyName);
                $("#industry").val(data.data.industry);
                $("#foundTime").val((data.data.foundTime==null||data.data.foundTime==undefined)?"":data.data.foundTime.split(" ")[0]);
                $("#courtEnterprise").val(data.data.courtEnterprise);
                $("#courtEnterprisePhoto").val(data.data.courtEnterprisePhoto);
                if(data.data.courtEnterprisePhoto!=""&&data.data.courtEnterprisePhoto!=null){
                    $("#courtEnterprisePhotomImg").attr("src",idPicUrls+data.data.courtEnterprisePhoto);
                }
                $("#zhixingEnterprise").val(data.data.zhixingEnterprise);
                $("#zhixingEnterprisePhoto").val(data.data.zhixingEnterprisePhoto);
                if(data.data.zhixingEnterprisePhoto!=""&&data.data.zhixingEnterprisePhoto!=null){
                    $("#zhixingEnterprisePhotomImg").attr("src",idPicUrls+data.data.zhixingEnterprisePhoto);
                }
                $("#riskEnterprise").val(data.data.riskEnterprise);
                $("#riskEnterprisePhoto").val(data.data.riskEnterprisePhoto);
                if(data.data.riskEnterprisePhoto!=""&&data.data.riskEnterprisePhoto!=null){
                    $("#riskEnterprisePhotomImg").attr("src",idPicUrls+data.data.riskEnterprisePhoto);
                }
                $("#warnEnterprise").val(data.data.warnEnterprise);
                $("#warnEnterprisePhoto").val(data.data.warnEnterprisePhoto);
                if(data.data.warnEnterprisePhoto!=""&&data.data.warnEnterprisePhoto!=null){
                    $("#warnEnterprisePhotomImg").attr("src",idPicUrls+data.data.warnEnterprisePhoto);
                }
                $("#enterpriseStatus").val(data.data.enterpriseStatus);
                $("#enterpriseRemark").val(data.data.enterpriseRemark);
                $("#companyRemark").val(data.data.remark);
                setImgPx();
            }else{
                Feng.info("查询失败！"+data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

<!--央行征信模块begin  ============================================================-->
//央行征信（贷款明细回显）
function searchLoanDetail() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/creditLoanDetail/selListByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#creditLoanDetailList").html("");
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<tr align='center'><td><input type='checkbox' value='"+data.data[i].id+"' name='loanChecked'/></td><td>"+data.data[i].loanOrg+"</td>"
                        +"<td>"+data.data[i].loanAmount+"</td>"
                        +"<td>"+data.data[i].loanType+"</td>"
                        +"<td>"+data.data[i].loanPeriod+"</td>"
                        +"<td>"+(data.data[i].loanBeginTime==null?"":data.data[i].loanBeginTime.split(" ")[0])+"</td>"
                        +"<td>"+(data.data[i].loanEndTime==null?"":data.data[i].loanEndTime.split(" ")[0])+"</td>"
                        +"<td>"+data.data[i].accountStatus+"</td>"
                        +"<td>"+data.data[i].fiveClassStatus+"</td>"
                        +"<td>"+data.data[i].capitalAmount+"</td>"
                        +"<td>"+data.data[i].leftPeriod+"</td>"
                        +"<td>"+data.data[i].curMonthPredictAmount+"</td>"
                        +"<td>"+(data.data[i].curMonthDate==null?"":data.data[i].curMonthDate.split(" ")[0])+"</td>"
                        +"<td>"+data.data[i].curMonthActuralAmount+"</td>"
                        +"<td>"+(data.data[i].lastRepaymentDatge==null?"":data.data[i].lastRepaymentDatge.split(" ")[0])+"</td>"
                        +"<td>"+data.data[i].curOverdueNum+"</td>"
                        +"<td>"+data.data[i].curOverdueAmount+"</td>"
                        +"<td>"+data.data[i].overdueM2Capital+"</td>"
                        +"<td>"+data.data[i].overdueM3Capital+"</td>"
                        +"<td>"+data.data[i].overdueM45Capital+"</td>"
                        +"<td>"+data.data[i].overdueM6Capital+"</td>"
                        +"<td>"+data.data[i].repaymentInfo+"</td>"
                        +"<td>"+data.data[i].remark+"</td></tr>";
                });
                $("#creditLoanDetailList").append(html);
            }
            searchCardDetail();
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}

//只回显贷款记录
function onlyShowLoanDetail() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/creditLoanDetail/selListByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#creditLoanDetailList").html("");
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<tr align='center'><td><input type='checkbox' value='"+data.data[i].id+"' name='loanChecked'/></td><td>"+data.data[i].loanOrg+"</td>"
                        +"<td>"+data.data[i].loanAmount+"</td>"
                        +"<td>"+data.data[i].loanType+"</td>"
                        +"<td>"+data.data[i].loanPeriod+"</td>"
                        +"<td>"+(data.data[i].loanBeginTime==null?"":data.data[i].loanBeginTime.split(" ")[0])+"</td>"
                        +"<td>"+(data.data[i].loanEndTime==null?"":data.data[i].loanEndTime.split(" ")[0])+"</td>"
                        +"<td>"+data.data[i].accountStatus+"</td>"
                        +"<td>"+data.data[i].fiveClassStatus+"</td>"
                        +"<td>"+data.data[i].capitalAmount+"</td>"
                        +"<td>"+data.data[i].leftPeriod+"</td>"
                        +"<td>"+data.data[i].curMonthPredictAmount+"</td>"
                        +"<td>"+(data.data[i].curMonthDate==null?"":data.data[i].curMonthDate.split(" ")[0])+"</td>"
                        +"<td>"+data.data[i].curMonthActuralAmount+"</td>"
                        +"<td>"+(data.data[i].lastRepaymentDatge==null?"":data.data[i].lastRepaymentDatge.split(" ")[0])+"</td>"
                        +"<td>"+data.data[i].curOverdueNum+"</td>"
                        +"<td>"+data.data[i].curOverdueAmount+"</td>"
                        +"<td>"+data.data[i].overdueM2Capital+"</td>"
                        +"<td>"+data.data[i].overdueM3Capital+"</td>"
                        +"<td>"+data.data[i].overdueM45Capital+"</td>"
                        +"<td>"+data.data[i].overdueM6Capital+"</td>"
                        +"<td>"+data.data[i].repaymentInfo+"</td>"
                        +"<td>"+data.data[i].remark+"</td></tr>";
                });
                $("#creditLoanDetailList").append(html);
            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}

//只回显信用卡
function onlyShowCardDetail() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/creditCardDetail/selListByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#creditCardDetailList").html("");
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<tr align='center'><td><input type='checkbox' value='"+data.data[i].id+"' name='cardChecked'/></td><td>"+data.data[i].cardOrg+"</td>"
                        +"<td>"+data.data[i].cardAmount+"</td>"
                        +"<td>"+data.data[i].cardShareAmount+"</td>"
                        +"<td>"+data.data[i].cardType+"</td>"
                        +"<td>"+data.data[i].accountStatus+"</td>"
                        +"<td>"+data.data[i].usedAmount+"</td>"
                        +"<td>"+data.data[i].avgUsedAmount+"</td>"
                        +"<td>"+data.data[i].maxUsedAmount+"</td>"
                        +"<td>"+data.data[i].curOverdueNum+"</td>"
                        +"<td>"+data.data[i].curOverdueAmount+"</td>"
                        +"<td>"+data.data[i].remark+"</td></tr>";
                });
                $("#creditCardDetailList").append(html);
            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}
//删除贷款记录
function delLoanDetail() {
    if($('input[name=loanChecked]:checked').length==0){
        Feng.info("请选择至少一条记录删除");
        return ;
    }
    // var arr = new Array();
    var ids ="";
    $.each($('input[name=loanChecked]:checked'),function () {
        // tmp += arr.push($(this).val())+","
        ids+=$(this).val()+",";
    });
    $.ajax({
        type: "POST",
        url: '/creditLoanDetail/deleteByIds',
        dataType: 'json',
        data: {
            'ids':ids
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("删除数据成功！");
                onlyShowLoanDetail();
            }
        },
        error: function() {
            Feng.info("删除数据异常！");

        }
    });

}

//删除信用卡记录
function delCardDetail() {
    if($('input[name=cardChecked]:checked').length==0){
        Feng.info("请选择至少一条记录删除");
        return ;
    }
    // var arr = new Array();
    var ids ="";
    $.each($('input[name=cardChecked]:checked'),function () {
        // tmp += arr.push($(this).val())+","
        ids+=$(this).val()+",";
    });
    $.ajax({
        type: "POST",
        url: '/creditCardDetail/deleteByIds',
        dataType: 'json',
        data: {
            'ids':ids
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("删除数据成功！");
                onlyShowCardDetail();
            }
        },
        error: function() {
            Feng.info("删除数据异常！");

        }
    });

}

//修改贷款记录信息
var tmpId = "";
function updateLoanDetail(){
    if($('input[name=loanChecked]:checked').length!=1){
        Feng.info("只能选择一条记录修改");
        return ;
    }
    tmpId = $('input[name=loanChecked]:checked').val();
    showAddCreditLoanDetail('update');
}

//修改信用卡记录信息
var tmpId2 = "";
function updateCardDetail(){
    if($('input[name=cardChecked]:checked').length!=1){
        Feng.info("只能选择一条记录修改");
        return ;
    }
    tmpId2 = $('input[name=cardChecked]:checked').val();
    showAddCreditCardDetail('update');
}

//跳转贷款添加页面
function showAddCreditLoanDetail(opr){
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    var url="";
    if(opr=='add'){
        url='/creditLoanDetail/showAddLoanDetail?applyId='+applyId+'&type='+pamType+"&id=-1";
    }else if(opr='update'){
        url='/creditLoanDetail/showAddLoanDetail?applyId='+applyId+'&type='+pamType+"&id="+tmpId;
    }
    var index = layer.open({
        type: 2,
        title: '添加贷款记录',
        area: ['950px', '650px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + url
    });
    this.layerIndex = index;
}

//跳转信用卡添加页面
function showAddCreditCardDetail(opr){
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    var url="";
    if(opr=='add'){
        url='/creditCardDetail/showAddCardDetail?applyId='+applyId+'&type='+pamType+"&id=-1";
    }else if(opr=='update'){
        url='/creditCardDetail/showAddCardDetail?applyId='+applyId+'&type='+pamType+"&id="+tmpId2;
    }
    var index = layer.open({
        type: 2,
        title: '添加贷记卡记录',
        area: ['800px', '550px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + url
    });
    this.layerIndex = index;
}


//央行征信（信用卡明细回显）
function searchCardDetail() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/creditCardDetail/selListByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            $("#creditCardDetailList").html("");
            var status = data.status;
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<tr align='center'><td><input type='checkbox' value='"+data.data[i].id+"' name='cardChecked'/></td><td>"+data.data[i].cardOrg+"</td>"
                        +"<td>"+data.data[i].cardAmount+"</td>"
                        +"<td>"+data.data[i].cardShareAmount+"</td>"
                        +"<td>"+data.data[i].cardType+"</td>"
                        +"<td>"+data.data[i].accountStatus+"</td>"
                        +"<td>"+data.data[i].usedAmount+"</td>"
                        +"<td>"+data.data[i].avgUsedAmount+"</td>"
                        +"<td>"+data.data[i].maxUsedAmount+"</td>"
                        +"<td>"+data.data[i].curOverdueNum+"</td>"
                        +"<td>"+data.data[i].curOverdueAmount+"</td>"
                        +"<td>"+data.data[i].remark+"</td></tr>";
                });
                $("#creditCardDetailList").append(html);
            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}
//=征信(查询记录)==============

//央行征信（机构查询记录）
function searchBussQuery() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/creditBussQueryRecord/findListByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#bussQueryList").html("");
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<tr align='center'><td><input type='checkbox' value='"+data.data[i].id+"' name='bussQueryChecked'/></td>"
                        +"<td>"+(data.data[i].queryDate==null?"":data.data[i].queryDate.split(" ")[0])+"</td>"
                        +"<td>"+data.data[i].queryOrg+"</td>"
                        +"<td>"+data.data[i].queryReason+"</td>"
                        +"<td>"+data.data[i].remark+"</td></tr>";
                });
                $("#bussQueryList").append(html);
            }
            searchPersonalQuery();
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}

//每次删除操作之后只更新机构查询记录
function onlyShowBussQuery() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/creditBussQueryRecord/findListByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#bussQueryList").html("");
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<tr align='center'><td><input type='checkbox' value='"+data.data[i].id+"' name='bussQueryChecked'/></td>"
                        +"<td>"+(data.data[i].queryDate==null?"":data.data[i].queryDate.split(" ")[0])+"</td>"
                        +"<td>"+data.data[i].queryOrg+"</td>"
                        +"<td>"+data.data[i].queryReason+"</td>"
                        +"<td>"+data.data[i].remark+"</td></tr>";
                });
                $("#bussQueryList").append(html);
            }
            searchPersonalQuery();
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}

//修改机构查询记录信息
var upBussId = "";
function updateBussQuery(){
    if($('input[name=bussQueryChecked]:checked').length!=1){
        Feng.info("只能选择一条记录修改");
        return ;
    }
    upBussId = $('input[name=bussQueryChecked]:checked').val();
    showAddBussQuery('update');
}

//跳转机构查询添加页面
function showAddBussQuery(opr){
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    var url="";
    if(opr=='add'){
        url='/creditBussQueryRecord/showAddBussQuery?applyId='+applyId+'&type='+pamType+"&id=-1";
    }else if(opr='update'){
        url='/creditBussQueryRecord/showAddBussQuery?applyId='+applyId+'&type='+pamType+"&id="+upBussId;
    }

    var index = layer.open({
        type: 2,
        title: '添加机构查询记录',
        area: ['780px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + url
    });
    this.layerIndex = index;
}

//删除机构查询记录
function delBussQuery() {
    if($('input[name=bussQueryChecked]:checked').length==0){
        Feng.info("请选择至少一条记录删除");
        return ;
    }
    var ids ="";
    $.each($('input[name=bussQueryChecked]:checked'),function () {
        ids+=$(this).val()+",";
    });
    $.ajax({
        type: "POST",
        url: '/creditBussQueryRecord/deleteByIds',
        dataType: 'json',
        data: {
            'ids':ids
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("删除数据成功！");
                onlyShowBussQuery();
            }
        },
        error: function() {
            Feng.info("删除数据异常！");

        }
    });

}

//央行征信（个人查询记录）
function searchPersonalQuery() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/creditPersonalQueryRecord/findListByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#personalQueryList").html("");
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<tr align='center'><td><input type='checkbox' value='"+data.data[i].id+"' name='personalQueryChecked'/></td>"
                        +"<td>"+(data.data[i].queryDate==null?"":data.data[i].queryDate.split(" ")[0])+"</td>"
                        +"<td>"+data.data[i].queryOrg+"</td>"
                        +"<td>"+data.data[i].remark+"</td></tr>";
                });
                $("#personalQueryList").append(html);
            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}

//修改个人查询记录信息
var upPersonalId = "";
function updatePersonalQuery (){
    if($('input[name=personalQueryChecked]:checked').length!=1){
        Feng.info("只能选择一条记录修改");
        return ;
    }
    upPersonalId = $('input[name=personalQueryChecked]:checked').val();
    showAddPersonalQuery('update');
}

//跳转个人查询添加页面
function showAddPersonalQuery(opr){
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    var url="";
    if(opr=='add'){
        url='/creditPersonalQueryRecord/showAddPersonalQuery?applyId='+applyId+'&type='+pamType+"&id=-1";
    }else if(opr=='update'){
        url='/creditPersonalQueryRecord/showAddPersonalQuery?applyId='+applyId+'&type='+pamType+"&id="+upPersonalId;
    }
    var index = layer.open({
        type: 2,
        title: '添加个人查询记录',
        area: ['780px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + url
    });
    this.layerIndex = index;
}

//删除个人查询记录
function delPersonalQuery() {
    if($('input[name=personalQueryChecked]:checked').length==0){
        Feng.info("请选择至少一条记录删除");
        return ;
    }
    var ids ="";
    $.each($('input[name=personalQueryChecked]:checked'),function () {
        ids+=$(this).val()+",";
    });
    $.ajax({
        type: "POST",
        url: '/creditPersonalQueryRecord/deleteByIds',
        dataType: 'json',
        data: {
            'ids':ids
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("删除数据成功！");
                searchPersonalQuery();
            }
        },
        error: function() {
            Feng.info("删除数据异常！");

        }
    });
}

//======================================央行征信 end =================================================

<!--折旧基数设置-->
function setYearOrMonth(){
    var yearOrMonth = $("#yearOrMonth").val();
    if(yearOrMonth==1){//年
        $("#showYearOrMonth").text("年");
    }else if(yearOrMonth==2){//月
        $("#showYearOrMonth").text("月");
    }
}

<!--计算氢诺拟可贷金额 规则为裸车价*折旧基数*折旧系数*授信成数 -->
function  calcTingnuoPrice() {
    //裸车价
    var nakePrice = $("#nakePrice").val();
    //折旧基数
    var depreciationBase = $("#depreciationBase").val();
    //折旧系数
    var depreciationRatio = $("#depreciationRatio").val();
    //授信成数
    var creditRatio = $("#creditRatio").val();

    var tsingnuoPrice = Number(nakePrice)*Number(depreciationBase)*Number(depreciationRatio)*Number(creditRatio);
    $("#tsingnuoPrice").val(tsingnuoPrice);

}
//设置认收基数显示类型
function setShowRsBase() {
    var  incomeType=$("#incomeType").val();
    //0-按揭房贷方案/1-抵押房贷方案/2-全款房方案/4-他行车贷方案，则该处自动生成显示为“月供金额”
    if(incomeType==0||incomeType==1||incomeType==2||incomeType==4){
        $("#showRsBase").text("认收方案基数为:月供金额(元)");
    }else  if(incomeType==3){//3-寿险保单方案则自动生成显示为“年缴金额”；
        $("#showRsBase").text("认收方案基数为:年缴金额(元)");
    }else if(incomeType==5){// 5-为流水结息方案则自动生成显示为“结息金额”；
        $("#showRsBase").text("认收方案基数为:结息金额(元)");
    }else if(incomeType==6){//6-为公积金方案则自动生成显示为“基数金额”；
        $("#showRsBase").text("认收方案基数为:基数金额(元)");
    }else if(incomeType==7){//7-为打卡工资方案则自动生成显示为“月工资金额”
        $("#showRsBase").text("认收方案基数为:月工资金额(元)");
    }
    // setDti();
}

//设置认收金额 规则为认收方案对应基数的金额*对应认收方案类别的收入认定基数
// （按揭房贷方案、抵押房贷方案、全款房方案为8、寿险保单方案为6、他行车贷方案为5、公积金方案为3、打卡工资方案为1.5、流水结息方案待定
function setIncomeConfirmAmount() {
    //认收方案基数
    var  incomeAmount= $("#incomeAmount").val();
    //收入认定基数
    var incomeAmountBase = 0;
    var  incomeType=$("#incomeType").val();
    //0-按揭房贷方案/1-抵押房贷方案/2-全款房方案
    if(incomeType==0||incomeType==1||incomeType==2){
        incomeAmountBase=8;
    }else  if(incomeType==3){//3-寿险保单方案
        incomeAmountBase=6;
    }else if(incomeType==4){// 4-他行车贷方案
        incomeAmountBase=5;
    }else if(incomeType==6){//6-为公积金方案
        incomeAmountBase=3;
    }else if(incomeType==7){//7-为打卡工资方案
        incomeAmountBase=1.5;
    }
    var incomeConfirmAmount = Number(incomeAmount)*Number(incomeAmountBase);
    $("#incomeConfirmAmount").val(incomeConfirmAmount);
    // setDti();
}
//返回  跳转到面审提交页面
function back() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/mianshen/showMsCommon?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
}

//设置dti  规则为 (征信认定的负债+本次拟贷款月供）/收入认定金额
function  setDti() {

    //本次拟贷月供
    var loanAmount = $("#loanAmount").val();
    //认收金额
    var incomeConfirmAmount = $("#incomeConfirmAmount").val();
    //征信认定的负债
    var creditLoanMonthAmount = $("#creditLoanMonthAmount").val();

    if(loanAmount!=null && loanAmount !="" && incomeConfirmAmount !=null &&incomeConfirmAmount !=""
        && creditLoanMonthAmount !=null && creditLoanMonthAmount!=""){
        var totalLoan = Number(creditLoanMonthAmount)+loanAmount;
        var dti = Number(totalLoan)/Number(incomeConfirmAmount);
        // var result = Math.round(Number(dti)*100/100)
        $("#dti").val(dti);
    }

}

//===============共借人特有的js
//跳转主借人信息录入页面
function showZjr() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/mianshen/showMs?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
}
//返回按钮
function backCommon() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var flag = $("#flag").val();
    if(flag==2){//面审主管
        window.location.href="/mianshen/showZg?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
    }else if(flag==3){//终审
        window.location.href="/zhongshen/showZsCommon?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
    }

}
//跳转主借人查看信息页面
function showZjrDetail() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var flag = $("#flag").val();
    window.location.href="/mianshen/showMsDetail?applyId="+applyId+"&interfaceAddress="+interfaceAddress+"&flag="+flag;
}

$(document).ready(function () {
    searchJudicial();
    var applyId = $("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/creditReport/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':1
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#creditLoanMonthAmount").val((data.data.creditLoanMonthAmount==null||data.data.creditLoanMonthAmount==undefined)?0:data.data.creditLoanMonthAmount);
            }else{
                Feng.info("查询失败！"+data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
});

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
    $('#menu-item10').data("bootstrapValidator").resetForm();
    $('#menu-item10').bootstrapValidator('validate');
    return $("#menu-item10").data('bootstrapValidator').isValid();
}
saveOrUpdates.validate3= function () {
    $('#menu-item3').data("bootstrapValidator").resetForm();
    $('#menu-item3').bootstrapValidator('validate');
    return $("#menu-item3").data('bootstrapValidator').isValid();
}
saveOrUpdates.validate4= function () {
    $('#menu-item4').data("bootstrapValidator").resetForm();
    $('#menu-item4').bootstrapValidator('validate');
    return $("#menu-item4").data('bootstrapValidator').isValid();
}
saveOrUpdates.validate11= function () {
    $('#menu-item11').data("bootstrapValidator").resetForm();
    $('#menu-item11').bootstrapValidator('validate');
    return $("#menu-item11").data('bootstrapValidator').isValid();
}
saveOrUpdates.validate2= function () {
    $('#menu-item2').data("bootstrapValidator").resetForm();
    $('#menu-item2').bootstrapValidator('validate');
    return $("#menu-item2").data('bootstrapValidator').isValid();
}
saveOrUpdates.validate1= function () {
    $('#menu-item1').data("bootstrapValidator").resetForm();
    $('#menu-item1').bootstrapValidator('validate');
    return $("#menu-item1").data('bootstrapValidator').isValid();
}
saveOrUpdates.validate6= function () {
    $('#menu-item6').data("bootstrapValidator").resetForm();
    $('#menu-item6').bootstrapValidator('validate');
    return $("#menu-item6").data('bootstrapValidator').isValid();
}
saveOrUpdates.validate7= function () {
    $('#gjrjudeicialName').data("bootstrapValidator").resetForm();
    $('#gjrjudeicialName').bootstrapValidator('validate');
    return $("#gjrjudeicialName").data('bootstrapValidator').isValid();
}

