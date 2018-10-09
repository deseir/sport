var idPicUrls = $("#idPicUrls").val();

//提交申请
function todoSubmit() {
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
                Feng.info(data.msg);
            }

        },
        error: function(data) {
            Feng.info("提交审批意见异常！");
        }
    });

}
//跳转到我的待办列表
function backToDoWork() {
    window.location.href="/blackboard";
}



//保存
function saveOrUpdate() {
    var fileObj = document.getElementById("settleAttachUrl1").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#settleAttachUrl").val(fileUrl);

    fileObj = document.getElementById("proxyBookUrl1").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#proxyBookUrl").val(fileUrl);

    fileObj = document.getElementById("certPhotoUrl1").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#certPhotoUrl").val(fileUrl);

    var id = $("#detentionId").val();
    var applyId = $("#applyId").val();
    var settleAttachUrl = $("#settleAttachUrl").val();
    var proxyBookUrl = $("#proxyBookUrl").val();
    var certPhotoUrl = $("#certPhotoUrl").val();
    $.ajax({
        type: "POST",
        url: "/carDetentionInfo/saveOrUpdate",
        dataType: 'json',
        data: {
            'id':id,
            'applyId':applyId,
            'settleAttachUrl':settleAttachUrl,
            'proxyBookUrl':proxyBookUrl,
            'certPhotoUrl':certPhotoUrl
        },
        success: function(data) {
            var status=data.status;
            if(status==0) {
                seachDetention();
                Feng.info("保存数据成功！");
            }else {
                Feng.info("保存数据失败！ "+data.msg);
            }

        },
        error: function(data) {
            Feng.info("保存数据异常！");
        }
    });
}

//回显信息
function  seachDetention() {
    var applyId =$("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/carDetentionInfo/findByApplyId',
        dataType: 'json',
        data: {
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            if(status==0){//
                $("#detentionId").val(data.data.id);
                $("#settleAttachUrl").val(data.data.settleAttachUrl);
                if(data.data.settleAttachUrl!=""&&data.data.settleAttachUrl!=null){
                    $("#settleAttachUrlmImg").attr("src",idPicUrls+data.data.settleAttachUrl);
                }
                $("#proxyBookUrl").val(data.data.proxyBookUrl);
                if(data.data.proxyBookUrl!=""&&data.data.proxyBookUrl!=null){
                    $("#proxyBookUrlmImg").attr("src",idPicUrls+data.data.proxyBookUrl);
                }
                $("#certPhotoUrl").val(data.data.certPhotoUrl);
                if(data.data.certPhotoUrl!=""&&data.data.certPhotoUrl!=null){
                    $("#certPhotoUrlmImg").attr("src",idPicUrls+data.data.certPhotoUrl);
                }
                $("#billAttachUrl").val(data.data.billAttachUrl);
                if(data.data.billAttachUrl!=""&&data.data.billAttachUrl!=null){
                    $("#billAttachUrlmImg").attr("src",idPicUrls+data.data.billAttachUrl);
                }
                setImgPx();
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });

}


//接收材料确认提交操作
function receveSubmit() {
    var applyId = $("#applyId").val();
    var result = $("#checkResult").val();
    var resultTip = $("#checkResultDesc").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var uuid=$('#uuid').val();
   if(result==""||result==null){
       Feng.info("必须选择接收资料是否确认")
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
                Feng.info("确认成功！");
                window.location.href="/blackboard";
            }else {
                Feng.info("确认失败！ "+data.msg);
            }

        },
        error: function(data) {
            //me.error(data);
            Feng.info("提交审批意见异常！");
        }
    });

}
//上传解押受理小票
function saveBillAttachUrl(){
    var fileObj = document.getElementById("billAttachUrl1").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#billAttachUrl").val(fileUrl);
    var id = $("#detentionId").val();
    var billAttachUrl = $("#billAttachUrl").val();
    var applyId = $("#applyId").val();

    $.ajax({
        type: "POST",
        url: '/carDetentionInfo/saveOrUpdate',
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


$(document).ready(function () {
    seachDetention();
});




