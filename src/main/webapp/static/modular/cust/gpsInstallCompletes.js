var idPicUrls = $("#idPicUrls").val();
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
//回显gps信息
function searchGps() {
    var applyId = $("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/carGpsInfo/findByApplyId',
        dataType: 'json',
        data: {
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#gpsId").val(data.data.id);
                $("#carId").val(data.data.carId);
                $("#gpsInstallDate").val((data.data.gpsInstallDate==null||data.data.gpsInstallDate==undefined)?"":data.data.gpsInstallDate.split(" ")[0]);
                $("#groupPhotoUrl").val(idPicUrls+data.data.groupPhotoUrl);
                $("#gpsRemark").val(data.data.remark);
                setImgPx();
            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}
//查询gpsdetail 信息
function searchGpsDetail() {
    var carId = $("#carId").val();
    $.ajax({
        type: "POST",
        url: '/carGpsDetailInfo/selectByCarId',
        dataType: 'json',
        data: {
            'carId':carId
        },
        success: function(data) {
            var status = data.status;
            $("#gpsDetailList").html("");
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<tr><td><input type='checkbox' value='"+data.data[i].id+"' name='gpsDetailChecked'/></td>"
                        +"<td>"+data.data[i].carId+"</td>"
                        +"<td>"+((data.data[i].isWiredless==0?"否":"是"))+"</td>"
                        +"<td>"+data.data[i].gpsWiredNo+"</td>"
                        +"<td> <img src=\""+idPicUrls+data.data[i].gpsPhotoUrl+"\" style='width: 80px; height: 40px;'></td>"
                        +"<td>"+data.data[i].remark+"</td></tr>";
                });
                $("#gpsDetailList").append(html);
            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}

$(document).ready(function () {
    var applyId = $("#applyId").val();
    $.ajax({
        type: "POST",
        url: '/carGpsInfo/findByApplyId',
        dataType: 'json',
        data: {
            'applyId':applyId
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#gpsId").val(data.data.id);
                $("#carId").val(data.data.carId);
                $("#gpsInstallDate").val((data.data.gpsInstallDate==null||data.data.gpsInstallDate==undefined)?"":data.data.gpsInstallDate.split(" ")[0]);
                $("#groupPhotoUrl").val(idPicUrls+data.data.groupPhotoUrl);
                $("#gpsRemark").val(data.data.remark);
                setImgPx();
                var carId = $("#carId").val();
                if(carId!=""&&carId!=null&&carId!=undefined){
                    searchGpsDetail();
                }
            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });

});


