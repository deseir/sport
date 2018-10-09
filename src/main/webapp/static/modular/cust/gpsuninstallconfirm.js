var idPicUrls = $("#idPicUrls").val();
$(document).ready(function(){
    initPhotoMsg();
});


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

//初始化照片信息
function initPhotoMsg() {
    var applyId = $("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/CarGpsUnInstallPhotoInfo/selectByCarId',
        dataType: 'json',
        data: {
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            $("#carId").val(data.carId);
            $("#showPhotoContent").html("");
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<div style=\"margin-top:95px\" class=\"col-sm-6\"><div><label class=\"col-sm-3 control-label\">";
                    html+=data.data[i].photoName;
                    html+="</label></div><div class=\"fileinput-new thumbnail\" style=\"width:120px;height:85px;position:absolute;left:40%;margin-top:-45px\"><img src=\""+idPicUrls+data.data[i].photoUrl+"\" style=\"width:100%;height:75px;background-color: #cccccc47;\"></div></div>";
                });
               $("#showPhotoContent").append(html);
                setPx();
            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}

function setPx() {
    var tmp1 = $("#maintainPhotoImg").attr("src");
    var tmp2 = $("#configTablePhotoImg").attr("src");
    if(tmp1!=""&&tmp1!=null&&tmp1!=undefined){
        $("#maintainPhotoImg").attr("style","width:100%;height:75px;background-color: #cccccc47");
    }
    if(tmp2!=""&&tmp2!=null&&tmp2!=undefined){
        $("#configTablePhotoImg").attr("style","width:100%;height:75px;background-color: #cccccc47");
    }

}



