var idPicUrls = $("#idPicUrls").val();
//面审主管审核提交
function msZgSubmit() {
    var applyId = $("#applyId").val();
    var result = $("#checkResult").val();
    if(result==""||result==null||result==undefined){
        Feng.info("请选择审核结果！")
        return false;
    }
    var overview = $("#overview").val();
    if(result==0){
        var interfaceAddress = $("#interfaceAddress").val();
        $.ajax({
            type: "POST",
            url: interfaceAddress,
            dataType: 'json',
            data: {
                'applyId': applyId,
                'result': result,
                'resultTip': overview
            },
            success: function (data) {
                var status = data.status;
                if(status==0) {
                    Feng.info("提交审批意见成功！");
                    window.location.href="/blackboard";
                }else {
                    Feng.info(data.msg);
                }
            },
            error: function() {
                Feng.info("提交审批意见异常！");
            }
        });
    }else {
        $.ajax({
            type: "POST",
            url: "/custInterviewInfo/updateNqYcStatus",
            dataType: 'json',
            data: {
                'applyId': applyId,
                'result': result,
                'overview': overview
            },
            success: function (data) {
                var status = data.status;
                if(status==0) {
                    Feng.info("驳回成功！");
                    window.location.href="/blackboard";
                }else {
                    Feng.info(data.errMsg);
                }
            },
            error: function() {
                Feng.info("驳回操作异常！");
            }
        });

    }

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
$(document).ready(function () {
    searchSupplement();
});
//补充资料回显
function  searchSupplement() {
    var applyId = $("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/supplementInfo/findByApplyId',
        dataType: 'json',
        data: {
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            if(status==0){
                $("#supplementDesc").val(data.data.supplementDesc);
                if(data.data.supplementAttachUrl!=""&&data.data.supplementAttachUrl!=null){
                    $("#supplementAttachUrl").val(idPicUrls+data.data.supplementAttachUrl);
                }
                setImgPx();
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}
//设置回显图片的位置
function setImgPx() {
    var imgList = $("img");
    for(var i=0;i<imgList.length;i++){
        if(imgList.eq(i).attr("src")!=null && imgList.eq(i).attr("src")!=''&&imgList.eq(i).attr("src")!=undefined){
            imgList.eq(i).attr("style","width:80px;height:40px;margin-left:300px;margin-top:-43px;");
        }
    }

}