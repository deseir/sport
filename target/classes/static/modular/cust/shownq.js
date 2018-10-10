function showAddNq() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/cust/custNqDataAddPage2?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
}

//内勤提交
function nqSubmit() {
    var applyId = $("#applyId").val();
    var result = $("#checkResult").val();
    var resultTip = $("#checkResultDesc").val();
    var interfaceAddress = $("#interfaceAddress").val();

    $.ajax({
        type: "POST",
        url: interfaceAddress,
        dataType: 'json',
        data: {
            'applyId': applyId,
            'result': result,
            'resultTip': resultTip
        },
        success: function (data) {
            var status = data.status;
            if(status==0) {
                Feng.info("提交审批意见成功！");
                window.location.href="/blackboard";
            }else {
                Feng.info("提交审批意见失败！ "+data.msg);
            }
        },
        error: function() {
            Feng.info("提交审批意见异常！");
        }
    });

}
//跳转到我的待办列表
function backToDoWork() {
    window.location.href="/blackboard";
}

//显示内勤录入信息只读页面
function showCustNq() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/mianshen/showCustNq?applyId="+applyId+"&interfaceAddress="+interfaceAddress+"&flag=2";
}