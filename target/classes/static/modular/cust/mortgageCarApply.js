var idPicUrls = $("#idPicUrls").val();

$("#checkResult").change(function(){
	var checkResult = $("#checkResult").val();
	if(checkResult == "1" || checkResult == "2"){
		$("#resultHide").show()
	}else{
		$("#checkResultDesc").val("");
		$("#resultHide").hide()
	}
})


//抵押完成和抵押受理提交
function todoFinishSubmit() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var uuid=$('#uuid').val();
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
            Feng.info("提交审批意见异常！");
        }
    });

}

function todoSubmit() {
    var applyId = $("#applyId").val();
    var result = $("#checkResult").val();
    var resultTip = $("#checkResultDesc").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var uuid=$('#uuid').val();
    // if(result==""||result==null){
    //     Feng.info("必须选择审核结果")
    //     return false;
    // }
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

//保存或修改
function saveOrUpdate(flag) {
    var fileObj = document.getElementById("registerPhotoUrl11").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#registerPhotoUrl1").val(fileUrl);

    fileObj = document.getElementById("registerPhotoUrl21").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#registerPhotoUrl2").val(fileUrl);

    fileObj = document.getElementById("registerPhotoUrl31").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#registerPhotoUrl3").val(fileUrl);

    fileObj = document.getElementById("registerPhotoUrl41").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#registerPhotoUrl4").val(fileUrl);

    fileObj = document.getElementById("proxyBookUrl1").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#proxyBookUrl").val(fileUrl);

    fileObj = document.getElementById("mortgageContractUrl1").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#mortgageContractUrl").val(fileUrl);

    fileObj = document.getElementById("certPhotoUrl1").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#certPhotoUrl").val(fileUrl);

    var id = $("#mortgageId").val();
    var applyId = $("#applyId").val();
    var custName = $("#custName").val();
    var custSex = $("#custSex").val();
    var custIdNo = $("#custIdNo").val();
    var registerPhotoUrl1 = $("#registerPhotoUrl1").val();
    var registerPhotoUrl2 = $("#registerPhotoUrl2").val();
    var registerPhotoUrl3 = $("#registerPhotoUrl3").val();
    var registerPhotoUrl4 = $("#registerPhotoUrl4").val();
    var proxyBookUrl = $("#proxyBookUrl").val();
    var mortgageContractUrl = $("#mortgageContractUrl").val();
    var certPhotoUrl = $("#certPhotoUrl").val();
    if(registerPhotoUrl1==''||registerPhotoUrl1==undefined||registerPhotoUrl1==null){
        Feng.info("请上传车辆登记证书1-2页照片！")
        return false;
    }
    if(proxyBookUrl==''||proxyBookUrl==undefined||proxyBookUrl==null){
        Feng.info("请上传委托书(客户签字授权)照片！")
        return false;
    }
    if(mortgageContractUrl==''||mortgageContractUrl==undefined||mortgageContractUrl==null){
        Feng.info("请上传抵押合同(车管所)照片！")
        return false;
    }

    if(certPhotoUrl==''||certPhotoUrl==undefined||certPhotoUrl==null){
        Feng.info("请上传身份证复印件(带有客户签名和手机号码)照片！")
        return false;
    }

    $.ajax({
        type: "POST",
        url: '/carBussMortgageInfo/saveOrUpdate',
        dataType: 'json',
        data: {
        	'id':id,
            'applyId':applyId,
            'custName':custName,
            'custSex':custSex,
            'custIdNo':custIdNo,
            'registerPhotoUrl1':registerPhotoUrl1,
            'registerPhotoUrl2':registerPhotoUrl2,
            'registerPhotoUrl3':registerPhotoUrl3,
            'registerPhotoUrl4':registerPhotoUrl4,
            'proxyBookUrl':proxyBookUrl,
            'mortgageContractUrl':mortgageContractUrl,
            'certPhotoUrl':certPhotoUrl
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                seachMortgage();
                Feng.info("保存数据成功！");
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });
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
                setImgPx();
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });

}
//确认抵押申请材料
function receiveConFirm(){
    var id = $("#mortgageId").val();
    var applyId = $("#applyId").val();

    $.ajax({
        type: "POST",
        url: '/carBussMortgageInfo/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });
}


//上传抵押受理小票
function saveBillAttachUrl(){
    var fileObj = document.getElementById("billAttachUrl1").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#billAttachUrl").val(fileUrl);
    var id = $("#mortgageId").val();
    var billAttachUrl = $("#billAttachUrl").val();
    var applyId = $("#applyId").val();
    if(billAttachUrl==""||billAttachUrl==null||billAttachUrl==undefined){
        Feng.info("请上传抵押受理小票照片");
        return false;
    }

    $.ajax({
        type: "POST",
        url: '/carBussMortgageInfo/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
			'applyId':applyId,
            'billAttachUrl':billAttachUrl
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });
}

//保存登记证书
function saveRegisterPhoto(){
    var fileObj = document.getElementById("registerPhotoUrl51").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#registerPhotoUrl5").val(fileUrl);

    fileObj = document.getElementById("registerPhotoUrl61").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#registerPhotoUrl6").val(fileUrl);

    fileObj = document.getElementById("registerPhotoUrl71").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#registerPhotoUrl7").val(fileUrl);

    fileObj = document.getElementById("registerPhotoUrl81").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#registerPhotoUrl8").val(fileUrl);

    var id = $("#mortgageId").val();
    var applyId = $("#applyId").val();
    var registerPhotoUrl5 = $("#registerPhotoUrl5").val();
    var registerPhotoUrl6 = $("#registerPhotoUrl6").val();
    var registerPhotoUrl7 = $("#registerPhotoUrl7").val();
    var registerPhotoUrl8 = $("#registerPhotoUrl8").val();
    if(registerPhotoUrl5==""||registerPhotoUrl5==null||registerPhotoUrl5==undefined){
        Feng.info("请上传抵押完成后车辆登记证书1-2页照片");
        return false;
    }
    if(registerPhotoUrl6==""||registerPhotoUrl6==null||registerPhotoUrl6==undefined){
        Feng.info("请上传抵押完成后车辆登记证书3-4页照片");
        return false;
    }

    $.ajax({
        type: "POST",
        url: '/carBussMortgageInfo/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'applyId':applyId,
            'registerPhotoUrl5':registerPhotoUrl5,
            'registerPhotoUrl6':registerPhotoUrl6,
            'registerPhotoUrl7':registerPhotoUrl7,
            'registerPhotoUrl8':registerPhotoUrl8
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });
}

$(document).ready(function () {
    seachMortgage();
});




