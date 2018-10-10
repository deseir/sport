
//公共提交操作
function todoSubmit() {
    var applyId = $("#applyId").val();
    var result = $("#checkResult").val();
    var resultTip = $("#checkResultDesc").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var uuid=$('#uuid').val();
//    if(result==""||result==null){
//        Feng.info("必须选择审核结果")
//        return false;
//    }
    $.ajax({
        type: "POST",
        url: interfaceAddress,
        dataType: 'json',
        data: {uuid:uuid, applyId:applyId,result:0,resultTip:""},
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

function checkW(){
	var applyId = $("#applyId").val();
	window.open("/carGpsDetailInfo/checkW?applyid="+applyId+"&type=1");
}

function checknW(){
	var applyId = $("#applyId").val();
	window.open("/carGpsDetailInfo/checkW?applyid="+applyId+"&type=0");
}

