
//公共提交操作
function todoSubmit() {
    var applyId = $("#applyId").val();
    var result = $("#checkResult").val();
    var resultTip = $("#checkResultDesc").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var uuid=$('#uuid').val();
    
    $.ajax({
        type: "POST",
        url: interfaceAddress,
        dataType: 'json',
        data: {uuid:uuid, applyId:applyId,result:0},
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

