var idPicUrls = $("#idPicUrls").val();
$(document).ready(function(){
    initCarVerify();

});

//初始化照片信息
function initPhotoMsg() {
    var carId = $("#carId").val();
    $.ajax({
        type: "POST",
        url: '/carPhotoInfo/selectByCarId',
        dataType: 'json',
        data: {
            'carId':carId
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<div class=\"col-sm-6\" style=\"margin-top: 65px;\"><div><label class=\"col-sm-3 control-label\">"
                        +data.data[i].photoName
                        +"</label></div><div class=\"fileinput-new thumbnail\" style=\"width:120px;height:85px;position:absolute;left:30%\"><img style=\"width:100%;height:75px;background-color: #cccccc47\" src=\""+idPicUrls+data.data[i].photoUrl+"\"></div>"
                        +"</div>";
                });

                $("#showPhotoContent").append(html);
               // setImgPx();
            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}



//初始化验车师
function initCarVerify() {
    var applyId = $("#applyId").val();
    var carId = $("#carId").val();
    $.ajax({
        type: "POST",
        url: '/carInfo/selInitCarverifyByCustId',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'carId':carId
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#carVerifyId").val(data.data.id);
                $("#carId").val(data.data.carId);
                $("#custName").val(data.data.custName);
                $("#sex").val(data.data.sex);
                $("#carConfigName").val(data.data.carConfigName);
                $("#carNum").val(data.data.carNum);

                $("#carCond").val(data.data.carCond);
                $("#suggestion").val(data.data.suggestion);
                $("#configTablePhoto").val(data.data.configTablePhoto);
                $("#configTablePhotoImg").attr("src",data.data.configTablePhoto==null?"":idPicUrls+data.data.configTablePhoto);
                $("#maintainPhoto").val(data.data.maintainPhoto);
                $("#maintainPhotoImg").attr("src",data.data.maintainPhoto==null?"":idPicUrls+data.data.maintainPhoto);
                $("#carAssessmentPrice").val(data.data.carAssessmentPrice);
                //setImgPx();
                initPhotoMsg();

            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}

//返回按钮
function backCommon() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    var flag = $("#flag").val();
    if(flag == 1){//1 面审
        window.location.href="/mianshen/showMsCommon?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
    }else if(flag==2){//面审主管
        window.location.href="/mianshen/showZg?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
    }else if(flag==3){//终审
        window.location.href="/zhongshen/showZsCommon?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
    }

}