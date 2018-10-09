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
                    html+="</label></div><div  class=\"fileinput-new thumbnail\" style=\"width:120px;height:85px;position:absolute;left:40%;margin-top:-45px\"><img src=\""+idPicUrls+data.data[i].photoUrl+"\" style=\"width:100%;height:75px;background-color: #cccccc47;\"></div>";
                    html+="<button type=\"button\" class=\"btn btn-primary \" onclick=\"deleteImg("+data.data[i].id+");\"  style=\"margin-left: 333px;margin-top: -54px;\">";
                    html+="<i class=\"fa fa-trash-o\"></i>&nbsp;删除</button></div>";
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




/**
 * 保存照片信息
 */
function saveOrUpdate() {

    var fileObj = document.getElementById("photoUrl1").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#photoUrl").val(fileUrl)

    var carId = $("#carId").val();
    // var bigClass=$("#bigClass").val();
    var bigClass='1';//图片类型暂时写死
    var photoName = $("#photoName").val();
    var photoUrl = $("#photoUrl").val();
    var photoRemark = $("#photoRemark").val();

    $.ajax({
        type: "POST",
        url: '/CarGpsUnInstallPhotoInfo/saveOrUpdate',
        dataType: 'json',
        data: {
            'carId':carId,
            'bigClass':bigClass,
            'photoName':photoName,
            'photoUrl':photoUrl,
            'remark':photoRemark
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
                window.parent.initPhotoMsg();
                parent.layer.close(window.parent.layerIndex);
            }else{
                Feng.info("保存数据失败！"+data.msg);
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });

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

/**
 * 跳转到添加图片的弹框
 */
function showAddGpsuninstall() {
    var applyId = $("#applyId").val();
    var carId = $("#carId").val();
    var index = layer.open({
        type: 2,
        title: '添加拆卸GPS图片信息',
        area: ['800px', '750px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + "/CarGpsUnInstallPhotoInfo/showAdd?applyId="+applyId+"&carId="+carId
    });
    this.layerIndex = index;
}

//删除图片
function deleteImg(id){
    $.ajax({
        type: "POST",
        url: '/CarGpsUnInstallPhotoInfo/deleteById',
        dataType: 'json',
        data: {
            'id':id
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("删除照片成功！");
                $("#showPhotoContent").html("");
                initPhotoMsg();
            }else{
                Feng.info("保存数据失败！"+data.msg);
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });

}

//设置车况维修保养状况和配置表附件的位置
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

//取消按钮
function cancle() {
    layer.close(layerIndex);
}



