var idPicUrls = $("#idPicUrls").val();

var saveOrUpdates = {
	 validateFields:{
		 custBankName:{
			 validators: {
//                notEmpty: {
//                    message: '开户银行不能为空'
//                },
                regexp: {
                    regexp:/^[\u2E80-\u9FFF]+$/,
                    message: '输入项只能为汉字'
                }
            }
		 },
		 custAccountName:{
			 validators: {
//                notEmpty: {
//                    message: '户名不能为空'
//                },
                regexp: {
                    regexp:/^[\u2E80-\u9FFF]+$/,
                    message: '输入项只能为汉字'
                }
            }
		 },
		 accountName:{
			 validators: {
//                notEmpty: {
//                    message: '银行卡户名不能为空'
//                },
                regexp: {
                    regexp:/^[\u2E80-\u9FFF]+$/,
                    message: '输入项只能为汉字'
                }
            }
		 },
		 custBankNum:{
			 validators: {
//                notEmpty: {
//                    message: '借记卡号不能为空'
//                },
                regexp: {
                    regexp:/^(\d{16}|\d{19})$/,
                    message: '借记卡号输入格式不正确'
                }
            }
		 },
		 bankNum:{
			 validators: {
//                notEmpty: {
//                    message: '银行卡账号不能为空'
//                },
                regexp: {
                    regexp:/^(\d{16}|\d{19})$/,
                    message: '银行卡账号输入格式不正确'
                }
            }
		 },
		 bankName:{
			 validators: {
//                notEmpty: {
//                    message: '银行卡开户行不能为空'
//                },
                regexp: {
                    regexp:/^[\u2E80-\u9FFF]+$/,
                    message: '输入项只能为汉字'
                }
            }
		 },
		 sqAmount:{
			 validators: {
				notEmpty: {
                    message: '授权金额不能为空'
                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式只能为阿拉伯数字'
	            }
			}
		 },
		 mobile:{
			 validators: {
//				notEmpty: {
//                    message: '授权金额不能为空'
//                },
				regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式只能为阿拉伯数字'
	            }
			}
		 },
		 creId:{
			 validators: {
//                notEmpty: {
//                    message: '身份证不能为空'
//                },
                regexp: {
                    regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                    message: '身份证格式不正确'
                }
            }
		 }
	 }
}



//公共提交操作
function todoSubmit() {
    var applyId = $("#applyId").val();
    var result = $("#checkResult").val();
    var resultTip = $("#checkResultDesc").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var uuid=$('#uuid').val();
    if(result==""||result==null){
        Feng.info("必须选择是否已签合同")
        return false;
    }
    $.ajax({
        type: "POST",
        url: interfaceAddress,
        dataType: 'json',
        data: {uuid:uuid, applyId:applyId,result:result,resultTip:resultTip},
        success: function(data) {
            var status=data.status;
            if(status==0) {
                Feng.info("提交审批意见成功！");
                window.location.href="/blackboard";
            }else {
                Feng.info(data.msg);
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
//下载合同
function downLoadPdf() {
    var contractTemplete = $("#contractTemplete").val();
    if(contractTemplete==""||contractTemplete==null||contractTemplete==undefined){
        Feng.info("请选择合同模板！");
        return;
    }
    var applyId = $("#applyId").val();
    if(contractTemplete==1){
        window.location.href="/process/downLoadrzglfwxy?applyid="+applyId;
    }else  if(contractTemplete==2){//委托扣款授权书
        var accountName = $("#accountName").val();
        var bankNum = $("#bankNum").val();
        var bankName = $("#bankName").val();
        var creId = $("#creId").val();
        var sqAmount = $("#sqAmount").val();
        var mobile = $("#mobile").val();
        if(accountName==""||accountName==null||accountName==undefined
           ||bankNum==""||bankNum==null||bankNum==undefined
        ||bankName==""||bankName==null||bankName==undefined
            ||creId==""||creId==null||creId==undefined
            ||sqAmount==""||sqAmount==null||sqAmount==undefined
            ||mobile==""||mobile==null||mobile==undefined){
            Feng.info("授权人信息不能为空！")
            return;
        }
        window.location.href="/process/downLoadwtkksqs?applyId="+applyId+"&accountName="+accountName+"&bankNum="+bankNum+"&bankName="+bankName+"&creId="+creId+"&sqAmount="+sqAmount+"&mobile="+mobile;
    }else  if(contractTemplete==3){//汽车抵押合同
        var signAddress = $("#signAddress").val();
        if(signAddress==""||signAddress==null||signAddress==undefined){
            Feng.info("合同签订地点不能为空！")
            return;
        }
        window.location.href="/process/downLoaddrhtcgs?applyid="+applyId+"&signAddress="+signAddress;
    }else  if(contractTemplete==4){//抵押人特别声明
        var accountName = $("#accountName").val();
        var bankNum = $("#bankNum").val();
        var bankName = $("#bankName").val();
        var creId = $("#creId").val();
        var sqAmount = $("#sqAmount").val();
        var mobile = $("#mobile").val();
        window.location.href="/process/downLoadrzzlhtfjwb?applyId="+applyId+"&accountName="+accountName+"&bankNum="+bankNum+"&bankName="+bankName+"&creId="+creId+"&sqAmount="+sqAmount+"&mobile="+mobile;
    }else  if(contractTemplete==5){
        window.location.href="/process/downLoadwts?applyid="+applyId;
    }else  if(contractTemplete==6){//汽车融资租赁合同(主要条款)
        var signAddress = $("#signAddress").val();
        var custAccountName = $("#custAccountName").val();
        var custBankNum = $("#custBankNum").val();
        var custBankName = $("#custBankName").val();
        window.location.href="/process/downLoadrzzlhtzht?applyId="+applyId+"&signAddress="+signAddress+"&custAccountName="+custAccountName+"&custBankNum="+custBankNum+"&custBankName="+custBankName;
    }

}

//保存合同签订信息
function saveContractSceneUrl() {
    var contractSignDate = $("#contractSignDate").val();
    if (!saveOrUpdates.validate()) {
    	return
    }
    if(contractSignDate==""||contractSignDate==null||contractSignDate==undefined){
        Feng.info("合同签订时间不能为空！");
        return;
    }
    var fileObj = document.getElementById("contractSceneUrl1").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#contractSceneUrl").val(fileUrl);
    var applyId = $("#applyId").val();
    var contractSceneUrl = $("#contractSceneUrl").val();
    
    $.ajax({
        type: "POST",
        url: '/contractInfo/saveOrUpdateContractSceneUrl',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'contractSceneUrl':contractSceneUrl,
            'contractSignDate':contractSignDate
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

//合同签订信息回显
function searchContractSceneUrl() {
    var applyId =$("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/contractInfo/findByApplyId',
        dataType: 'json',
        data: {
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            if(status==0){//
                $("#contractSceneUrlmImg").attr("src",idPicUrls+data.data.contractSceneUrl);
                $("#contractSignDate").val((data.data.contractSignDate!=null&&data.data.contractSignDate!=""&&data.data.contractSignDate!=undefined)?data.data.contractSignDate.split(" ")[0]:"");
                setImgPx();
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

$(document).ready(function () {
    searchContractSceneUrl();
    Feng.initValidator("menu-item5", saveOrUpdates.validateFields);
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
    $('#menu-item5').data("bootstrapValidator").resetForm();
    $('#menu-item5').bootstrapValidator('validate');
    return $("#menu-item5").data('bootstrapValidator').isValid();
}