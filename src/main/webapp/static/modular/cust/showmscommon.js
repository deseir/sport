$(document).ready(function () {
    searchSupplement();
    searchInterview();
});
var idPicUrls = $("#idPicUrls").val();
//跳转面审信息录入页面
function showMs() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/mianshen/showMs?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
}

//面审意见保存
function saveInterview(){

    var interviewResult = $("#interviewResult").val();
    if(interviewResult=="" || interviewResult==null || interviewResult==undefined){
        Feng.info("请选择审核结果！");
        return;
    }
    var fileObj = document.getElementById("sceneEvidenceUrl11").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#sceneEvidenceUrl1").val(fileUrl);

    fileObj = document.getElementById("sceneEvidenceUrl21").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#sceneEvidenceUrl2").val(fileUrl);

    var applyId = $("#applyId").val();
    var id = $("#interviewId").val();
    var overview = $("#overview").val();
    var rejectionReason = $("#rejectionReason").val();
    var loanAmount = $("#interviewLoanAmount").val();
    var loanPeriod = $("#loanPeriod").val();
    var sceneEvidenceUrl1 = $("#sceneEvidenceUrl1").val();
    var sceneEvidenceUrl2 = $("#sceneEvidenceUrl2").val();
    // var remark = $("#interviewRemark").val();

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
            'sceneEvidenceUrl2':sceneEvidenceUrl2
            // 'remark' : remark
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
                searchInterview();
            }else{
                Feng.info("保存数据失败！"+data.msg);
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });
}

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
                if(data.data.sceneEvidenceUrl1!=""&&data.data.sceneEvidenceUrl1!=null){
                    $("#sceneEvidenceUrl1mImg").attr("src",idPicUrls+data.data.sceneEvidenceUrl1);
                }
                $("#sceneEvidenceUrl2").val(data.data.sceneEvidenceUrl2);
                if(data.data.sceneEvidenceUrl2!=""&&data.data.sceneEvidenceUrl2!=null){
                    $("#sceneEvidenceUrl2mImg").attr("src",idPicUrls+data.data.sceneEvidenceUrl2);
                }
                setImgPx();
                dispalyInterview();
            }else{
                Feng.info("查询失败！"+data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

//面审提交
function msSubmit() {
    var interviewResult = $("#interviewResult").val();
    if(interviewResult=="" || interviewResult==null || interviewResult==undefined){
        Feng.info("请选择审核结果！");
        return;
    }
    var fileObj = document.getElementById("sceneEvidenceUrl11").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#sceneEvidenceUrl1").val(fileUrl);

    fileObj = document.getElementById("sceneEvidenceUrl21").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#sceneEvidenceUrl2").val(fileUrl);

    var applyId = $("#applyId").val();
    var id = $("#interviewId").val();
    var overview = $("#overview").val();
    var sceneEvidenceUrl1 = $("#sceneEvidenceUrl1").val();
    var sceneEvidenceUrl2 = $("#sceneEvidenceUrl2").val();
    // var remark = $("#interviewRemark").val();

    if(interviewResult==0){
        var loanPeriod = $("#loanPeriod").val();
        var interviewLoanAmount = $("#interviewLoanAmount").val();
        if(loanPeriod==""||loanPeriod==null||loanPeriod==undefined){
            Feng.info("请选择通过的期数！");
            return;
        }
        if(interviewLoanAmount==""||interviewLoanAmount==null||interviewLoanAmount==undefined||interviewLoanAmount<=0){
            Feng.info("通过的金额不能为空并且大于0！");
            return;
        }
        var interfaceAddress = $("#interfaceAddress").val();
        $.ajax({
            type: "POST",
            url: interfaceAddress,
            dataType: 'json',
            data: {
                'applyId':applyId,
                'id' : id,
                'overview' : overview,
                'result' : interviewResult,
                'loanAmount' : interviewLoanAmount,
                'loanPeriod':loanPeriod,
                'sceneEvidenceUrl1':sceneEvidenceUrl1,
                'sceneEvidenceUrl2':sceneEvidenceUrl2
                // 'remark' : remark
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
                'applyId':applyId,
                'id' : id,
                'overview' : overview,
                'result' : interviewResult,
                'sceneEvidenceUrl1':sceneEvidenceUrl1,
                'sceneEvidenceUrl2':sceneEvidenceUrl2
                // 'remark' : remark
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
//跳转到首页
function backToDoWork() {
    window.location.href="/blackboard";
}

//显示内勤信息只读页面
function showCustNq() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/mianshen/showCustNq?applyId="+applyId+"&interfaceAddress="+interfaceAddress+"&flag=1";
}
//显示验车师信息只读页面
function showYc() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/carVerifyInfo/showYcs?applyId="+applyId+"&interfaceAddress="+interfaceAddress+"&flag=1";
}

//保存材料
function supplementSaveOrUpdate() {
    var fileObj = document.getElementById("supplementAttachUrl1").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#supplementAttachUrl").val(fileUrl);


    var applyId = $("#applyId").val();
    var id = $("#supplementId").val();
    var supplementDesc = $("#supplementDesc").val();
    var supplementAttachUrl = $("#supplementAttachUrl").val();

    $.ajax({
        type: "POST",
        url: "/supplementInfo/saveOrUpdate",
        dataType: 'json',
        data: {
            'applyId': applyId,
            'id': id,
            'supplementDesc':supplementDesc,
            'supplementAttachUrl': supplementAttachUrl
        },
        success: function (data) {
            var status = data.status;
            if(status==0) {
                searchSupplement();
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
                $("#supplementId").val(data.data.id);
                $("#supplementDesc").val(data.data.supplementDesc);
                $("#supplementAttachUrlmImg").attr("src",idPicUrls+data.data.supplementAttachUrl);
                setImgPx();
                dispalyInterview();
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

//设置面审意见选项时控件状态
function dispalyInterview(){
    var interviewResult = $("#interviewResult").val();
    if(interviewResult==0){
        //$("#rejectReasult").hide();
        $("#interviewAmount").show();
        $("#interviewloanPeriod").show();
    }else if(interviewResult==1||interviewResult==2){
        //$("#rejectReasult").show();
        $("#interviewAmount").hide();
        $("#interviewloanPeriod").hide();
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