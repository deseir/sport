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

//gps信息保存
function saveGps() {
    var fileObj = document.getElementById("groupPhotoUrl1").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#groupPhotoUrl").val(fileUrl);
    var id = $("#gpsId").val();
    var applyId = $("#applyId").val();
    var carId = $("#carId").val();
    var gpsInstallDate = $("#gpsInstallDate").val();
    var groupPhotoUrl = $("#groupPhotoUrl").val();
    var remark = $("#gpsRemark").val();


    $.ajax({
        type: "POST",
        url: '/carGpsInfo/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'applyId':applyId,
			'carId':carId,
			'gpsInstallDate':gpsInstallDate,
            'groupPhotoUrl':groupPhotoUrl,
            'remark':remark
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                searchGps();
                searchGpsDetail();
                Feng.info("保存数据成功！");
            }else{
                Feng.info("保存数据失败！"+data.msg);
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });
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
                $("#groupPhotoUrlmImg").attr("src",idPicUrls+data.data.groupPhotoUrl);
                $("#gpsRemark").val(data.data.remark);
                setImgPx();
            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}

//跳转添加GPS详细信息页面
function showAddGpsDetail(){
    var carId = $("#carId").val();
    if(carId==""||carId==null||carId==undefined){
        Feng.info("请先保存GPS信息！");
        return;
    }
    var index = layer.open({
        type: 2,
        title: 'GPS详细信息',
        area: ['1138px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + "/carGpsInfo/showAddGpsDetail?carId="+carId
    });
    this.layerIndex = index;
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

//删除gps详细信息
function delGpsDetail() {
    if($('input[name=gpsDetailChecked]:checked').length==0){
        Feng.info("请选择至少一条记录删除");
        return ;
    }
    var ids ="";
    $.each($('input[name=gpsDetailChecked]:checked'),function () {
        ids+=$(this).val()+",";
    });
    $.ajax({
        type: "POST",
        url: '/carGpsDetailInfo/deleteByIds',
        dataType: 'json',
        data: {
            'ids':ids
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("删除数据成功！");
                searchGpsDetail();
            }
        },
        error: function() {
            Feng.info("删除数据异常！");

        }
    });

}

//跳转修改GPS详细信息页面
function showUpdateGpsDetail(){
    var detailId = $('input[name=gpsDetailChecked]:checked').val();
    var carId = $("#carId").val();
    var index = layer.open({
        type: 2,
        title: '通话记录详细信息',
        area: ['1138px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + "/carGpsInfo/showAddGpsDetail?detailId="+detailId+"&carId="+carId
    });
    this.layerIndex = index;
}

//修改信GPS详细信息
function updateGpsDetail(){
    if($('input[name=gpsDetailChecked]:checked').length!=1){
        Feng.info("只能选择一条记录修改");
        return ;
    }
    showUpdateGpsDetail();
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
                $("#groupPhotoUrlmImg").attr("src",idPicUrls+data.data.groupPhotoUrl);
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


