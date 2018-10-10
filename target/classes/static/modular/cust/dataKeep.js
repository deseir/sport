
var saveOrUpdates = {

    validateFields:{
		 paperdatanum:{
			 validators: {
               notEmpty: {
                   message: '资料份数不能为空'
               },
				 regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式只能为阿拉伯数字'
	            }
            }
		 },
		 contractnum:{
			 validators: {
              notEmpty: {
                  message: '合同份数不能为空'
              },
				 regexp: {
	                regexp: /^[0-9]*$/,
	                message: '输入项格式只能为阿拉伯数字'
	            }
           }
		 },
        iscarregister:{
            validators: {
                notEmpty: {
                    message: '请选择存留抵押登记证书'
                },
            }
        },
        iscarkey:{
            validators: {
                notEmpty: {
                    message: '请选择存有客户备用钥匙'
                },
            }
        },
        keepdate:{
            validators: {
                notEmpty: {
                    message: '请选择存档时间'
                },
            }
        },
        unkeepdate:{
            validators: {
                notEmpty: {
                    message: '请选择归还时间'
                },
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
        Feng.info("必须选择审核结果")
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

//保存
function save(){
	var applyId = $("#applyId").val();

	if (!saveOrUpdates.validate()) {
    	return
    }

	$.ajax({
        type: "POST",
        url: "/dataKeepInfo/save",
        dataType: 'json',
        data: {keepDate:$("#keepdate").val(), applyId:applyId,
        	unkeepDate:$("#unkeepdate").val(),
        	isCarRegister:$("#iscarregister").val(),
        	isCarKey:$("#iscarkey").val(),
        	paperDataNum:$("#paperdatanum").val(),
        	contractNum:$("#contractnum").val()},
        success: function(data) {
            var status=data.status;
            if(status==0) {
                Feng.info("保存成功！");
            }else {
                Feng.info("保存失败！ "+data.msg);
            }

        },
        error: function(data) {
            //me.error(data);
            Feng.info("保存异常！");
        }
    });
	
}
$(document).ready(function () {
    Feng.initValidator("menu-item5", saveOrUpdates.validateFields);
    seachMortgage();
});
/*function backtoDataKeepInfoByApplyId(){
    var applyId = $("#applyId").val();
    $.ajax({
        type: "POST",
        url: "/dataKeepInfo/findByApplyId",
        dataType: 'json',
        data: {  'applyId':applyId},
        success: function(data) {
            var status=data.status;
            if(status==0) {
                $("#paperdatanum").val(data.data.paperDataNum);
                $("#contractnum").val(data.data.contractNum);
                $("#iscarregister").val(data.data.isCarRegister);
                $("#iscarkey").val(data.data.isCarKey);
                $("#keepdate").val(data.data.keepDate==undefined?"":data.data.keepDate.split(" ")[0]);
                $("#unkeepdate").val(data.data.unkeepDate==undefined?"":data.data.unkeepDate.split(" ")[0]);
            }
        },

    });
};*/
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
//回显信息
function  seachMortgage() {
    var applyId =$("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/carBussMortgageInfo/selectByApplyId',
        dataType: 'json',
        data: {
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            if(status==0){//
                $("#mortgageId").val(data.data.id);
                $("#registerPhotoUrl1").val(data.data.registerPhotoUrl1);
                if(data.data.registerPhotoUrl1!=""&&data.data.registerPhotoUrl1!=null){
                    $("#registerPhotoUrl1mImg").attr("src",idPicUrls+data.data.registerPhotoUrl1);
                }
                $("#registerPhotoUrl2").val(data.data.registerPhotoUrl2);
                if(data.data.registerPhotoUrl2!=""&&data.data.registerPhotoUrl2!=null){
                    $("#registerPhotoUrl2mImg").attr("src",idPicUrls+data.data.registerPhotoUrl2);
                }
                $("#registerPhotoUrl3").val(data.data.registerPhotoUrl3);
                if(data.data.registerPhotoUrl3!=""&&data.data.registerPhotoUrl3!=null){
                    $("#registerPhotoUrl3mImg").attr("src",idPicUrls+data.data.registerPhotoUrl3);
                }
                $("#registerPhotoUrl4").val(data.data.registerPhotoUrl4);
                if(data.data.registerPhotoUrl4!=""&&data.data.registerPhotoUrl4!=null){
                    $("#registerPhotoUrl4mImg").attr("src",idPicUrls+data.data.registerPhotoUrl4);
                }
                $("#proxyBookUrl").val(data.data.proxyBookUrl);
                if(data.data.proxyBookUrl!=""&&data.data.proxyBookUrl!=null){
                    $("#proxyBookUrlmImg").attr("src",idPicUrls+data.data.proxyBookUrl);
                }
                $("#mortgageContractUrl").val(data.data.mortgageContractUrl);
                if(data.data.mortgageContractUrl!=""&&data.data.mortgageContractUrl!=null){
                    $("#mortgageContractUrlmImg").attr("src",idPicUrls+data.data.mortgageContractUrl);
                }
                $("#certPhotoUrl").val(data.data.certPhotoUrl);
                if(data.data.certPhotoUrl!=""&&data.data.certPhotoUrl!=null){
                    $("#certPhotoUrlmImg").attr("src",idPicUrls+data.data.certPhotoUrl);
                }
                $("#billAttachUrl").val(data.data.billAttachUrl);
                if(data.data.billAttachUrl!=""&&data.data.billAttachUrl!=null){
                    $("#billAttachUrlmImg").attr("src",idPicUrls+data.data.billAttachUrl);
                }
                $("#registerPhotoUrl5").val(data.data.registerPhotoUrl5);
                if(data.data.registerPhotoUrl5!=""&&data.data.registerPhotoUrl5!=null){
                    $("#registerPhotoUrl5mImg").attr("src",idPicUrls+data.data.registerPhotoUrl5);
                }
                $("#registerPhotoUrl6").val(data.data.registerPhotoUrl6);
                if(data.data.registerPhotoUrl6!=""&&data.data.registerPhotoUrl6!=null){
                    $("#registerPhotoUrl6mImg").attr("src",idPicUrls+data.data.registerPhotoUrl6);
                }
                $("#registerPhotoUrl7").val(data.data.registerPhotoUrl7);
                if(data.data.registerPhotoUrl7!=""&&data.data.registerPhotoUrl7!=null){
                    $("#registerPhotoUrl7mImg").attr("src",idPicUrls+data.data.registerPhotoUrl7);
                }
                $("#registerPhotoUrl8").val(data.data.registerPhotoUrl8);
                if(data.data.registerPhotoUrl8!=""&&data.data.registerPhotoUrl8!=null){
                    $("#registerPhotoUrl8mImg").attr("src",idPicUrls+data.data.registerPhotoUrl8);
                }
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });

}
