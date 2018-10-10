
var idPicUrls = $("#idPicUrls").val();
$(document).ready(function () {
    searchMainApprove();
    searchFinalJudge();
    searchSupplement();
});
//根据订单编号查询所有审核提交记录
function  searchMainApprove() {
    var applyId = $("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/mainApproveRecord/selectByApplyId',
        dataType: 'json',
        data: {
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            $("#mainApproveList").html("");
            if(status==0){
                var html="";
                $.each(data.data,function(i){
                    html+="<tr align='center'>"
                        +"<td>"+(i+1)+"</td>"
                        +"<td>"+data.data[i].applyId+"</td>"
                        +"<td>"+data.data[i].processNodeDesc+"</td>"
                        +"<td>"+data.data[i].operatorName+"</td>"
                        +"<td>"+data.data[i].auditRemark+"</td>"
                        +"<td>"+data.data[i].createTime+"</td></tr>";
                });

                $("#mainApproveList").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

//设置面审意见选项时控件状态
function dispalyInterview(){
    var interviewResult = $("#judgementResult").val();
    if(interviewResult==0){
        $("#rejectReasult").hide();
        $("#interviewAmount").show();
        $("#interviewloanPeriod").show();
        $("#showRemark").show();
        $("#rejectionReason").val("");
    }else if(interviewResult==1||interviewResult==2){
        $("#rejectReasult").show();
        $("#interviewAmount").hide();
        $("#interviewloanPeriod").hide();
        $("#showRemark").hide();
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
    window.location.href="/mianshen/showCustNq?applyId="+applyId+"&interfaceAddress="+interfaceAddress+"&flag=3";
}

//显示验车师信息只读页面
function showYc() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/carVerifyInfo/showYcs?applyId="+applyId+"&interfaceAddress="+interfaceAddress+"&flag=3";
}

//查看面审详细信息
function showMsDetail() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/mianshen/showMsDetail?applyId="+applyId+"&interfaceAddress="+interfaceAddress+"&flag=3";
}

//终审保存
function zsSaveOrUpdate() {
    var judgementResult = $("#judgementResult").val();
    var loanAmount = $("#loanAmount").val();
    var applyId = $("#applyId").val();
    var id = $("#judgementId").val();
    var rejectionReason = $("#rejectionReason").val();
    var loanPeriod = $("#loanPeriod").val();
    var remark = $("#remark").val();
    if(judgementResult=="" || judgementResult==null || judgementResult==undefined){
        Feng.info("请选择终审意见！");
        return;
    }else if(judgementResult==0){//只有通过时校验金额
        if(loanAmount=="" || loanAmount==null || loanAmount==undefined){
            Feng.info("通过金额不能为空！");
            return;
        }else if(loanAmount<1500||loanAmount>600000){
        	Feng.info("通过金额只能在1500~60万之间！");
        	return;
        }

        if(loanPeriod=="" || loanPeriod==null || loanPeriod==undefined){
            Feng.info("请选择通过的期数！");
            return;
        }
        rejectionReason="";//通过的时候设置拒绝原因为空

    }else{//状态不是通过，则设置金额和期数为空
        loanAmount="";
        loanPeriod="";
    }



    $.ajax({
        type: "POST",
        url: "/finalJudgementInfo/saveOrUpdate",
        dataType: 'json',
        data: {
            'applyId': applyId,
            'id': id,
            'judgementResult':judgementResult,
            'rejectionReason': rejectionReason,
            'loanAmount': loanAmount,
            'loanPeriod': loanPeriod,
            'remark': remark
        },
        success: function (data) {
            var status = data.status;
            if(status==0) {
                searchFinalJudge();
                Feng.info("保存数据成功！");
            }else {
                Feng.info("保存数据失败！ "+data.errMsg);
            }
        },
        error: function() {
            Feng.info("保存数据异常！");
        }
    });

}

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
//下载补充材料附件
function downLoadSupplementaryMaterial() {
    var supplementAttachUrl = $("#supplementAttachUrl").val();
    if(supplementAttachUrl==""||supplementAttachUrl==null||supplementAttachUrl==undefined){
        Feng.info("无补充材料附件！");
    }else{
       var params = supplementAttachUrl.replace("///g","\\");
        window.location.href="/supplementInfo/downLoadSupplementaryMaterial?params="+params;
    }
}
//终审回显
function  searchFinalJudge() {
    var applyId = $("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/finalJudgementInfo/findByApplyId',
        dataType: 'json',
        data: {
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            if(status==0){
                $("#judgementId").val(data.data.id);
                $("#judgementResult").val(data.data.judgementResult);
                $("#rejectionReason").val(data.data.rejectionReason);
                $("#loanAmount").val((data.data.loanAmount));
                $("#loanPeriod").val(data.data.loanPeriod);
                $("#remark").val(data.data.remark);
                dispalyInterview();
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}
//终审提交
function zsSubmit() {
    var applyId = $("#applyId").val();
    var judgementResult = $("#judgementResult").val();
    var remark = $("#remark").val();
    var loanAmount = $("#loanAmount").val();
    var loanPeriod = $("#loanPeriod").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var rejectionReason = $("#rejectionReason").val();

    if(judgementResult==""||judgementResult==null||judgementResult==undefined){
            Feng.info("请选择终审意见！")
            return false;
      }
    if(judgementResult==0 ){
        if(loanAmount=="" || loanAmount==null || loanAmount==undefined){
            Feng.info("通过金额不能为空！");
            return;
        }else if(loanAmount<1500||loanAmount>600000){
            Feng.info("通过金额只能在1500~60万之间！");
            return;
        }

        if(loanPeriod=="" || loanPeriod==null || loanPeriod==undefined){
            Feng.info("请选择通过的期数！");
            return;
        }
        rejectionReason="";//通过的时候设置拒绝原因为空

    }else{//状态不是通过，则设置金额和期数为空
        loanAmount="";
        loanPeriod="";
    }


    $.ajax({
        type: "POST",
        url: interfaceAddress,
        dataType: 'json',
        data: {
            'applyId': applyId,
            'judgementResult': judgementResult,
            'loanAmount': loanAmount,
            'loanPeriod': loanPeriod,
            'rejectionReason':rejectionReason,
            'remark':remark
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