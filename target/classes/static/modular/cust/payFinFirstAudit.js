var PayFinFirstAudit = {
	PayFinFirstAuditData : {},
    validateFields: {
        amount: {
            validators: {
                notEmpty: {
                    message: '首次放款金额不能为空'
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
        }
    }
};

PayFinFirstAudit.validate = function () {
    $('#finfirst').data("bootstrapValidator").resetForm();
    $('#finfirst').bootstrapValidator('validate');
    return $("#finfirst").data('bootstrapValidator').isValid();
}

$(function(){
	Feng.initValidator("finfirst", PayFinFirstAudit.validateFields);
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
})
//公共提交操作
function todoSubmit() {
    var applyId = $("#applyId").val();
    var result = $("#checkResult").val();
    var resultTip = $("#checkResultDesc").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var amount=$("#amount").val();
    var uuid=$('#uuid').val();
    if(result==""||result==null){
        Feng.info("必须选择审核结果")
        return false;
    }
    if(!PayFinFirstAudit.validate()){
    	return false;
    }
    $.ajax({
        type: "POST",
        url: interfaceAddress,
        dataType: 'json',
        data: {uuid:uuid, applyId:applyId,result:result,resultTip:resultTip,amount:amount},
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

//显示内勤录入只读页面
function showCustNq() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/mianshen/showCustNq?applyId="+applyId+"&interfaceAddress="+interfaceAddress+"&flag=2";
}

//显示验车师信息只读页面
function showYc() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/carVerifyInfo/showYcs?applyId="+applyId+"&interfaceAddress="+interfaceAddress+"&flag=2";
}

//查看面审详细信息
function showMsDetail() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/mianshen/showMsDetail?applyId="+applyId+"&interfaceAddress="+interfaceAddress+"&flag=2";
}

