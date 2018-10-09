var idPicUrls = $("#idPicUrls").val();
$(document).ready(function(){
    // initCarVerify();
    // setPx();
    // Feng.initValidator("ycInfo", saveOrUpdates.validateFields);
});



/**
 * 保存照片信息
 */
function save() {

    var fileObj = document.getElementById("photoUrl1").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#photoUrl").val(fileUrl)

    var carId = $("#carId").val();
    var photoName = $("#photoName").val();
    var photoUrl = $("#photoUrl").val();
    var photoRemark = $("#photoRemark").val();

    $.ajax({
        type: "POST",
        url: '/carPhotoInfo/saveOrUpdate',
        dataType: 'json',
        data: {
            'carId':carId,
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


