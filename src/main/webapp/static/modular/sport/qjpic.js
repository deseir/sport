var idPicUrls = $("#idPicUrls").val();

//上传图片
function uploadPic() {
    var deptId = $("#deptId").val();
    if(null == deptId || ""==deptId){
        alert("部门id为空，请查看原因！");
        return;
    }
    var fileObj = document.getElementById("fileInpBtn").files[0];
    if(fileObj==null||fileObj.name==null||fileObj.name==""){
        alert("请选择图片！");
        return;
    }
    var fileUrl = this.uploadFile(fileObj);
    if(fileUrl==""||fileUrl==null){
        alert("上传图片异常");
        return;
    }
    $.ajax({
        type: "POST",
        url: '/qjpic/saveOrUpdate',
        dataType: 'json',
        data: {
            'deptid':deptId,
            'picurl':fileUrl
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                alert("上传图片成功！");
                showPics();
            }else{
                alert("上传图片失败！"+data.msg);
            }
        },
        error: function() {
            alert("上传图片异常！");

        }
    });

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
                returnUrl = data.filePath;
            }else{
                returnUrl = "";
            }
        },
        error: function(data) {
            alert("上传图片异常！");
            returnUrl = "";
        }

    });
    return returnUrl;
}

function showPics() {
    var deptId = $("#deptId").val();
    if(deptId==null || deptId==""||deptId==undefined){
        alert("部门id为空，请查看原因！")
        return;
    }
    $.ajax({
        type: "POST",
        url: '/qjpic/listAll',
        dataType: 'json',
        data: {
            'deptid':deptId
        },
        success: function(data) {
            var status = data.status;
            $("#picContent").html("");
            if(status=='0'){
                var html ="";
                $.each(data.data,function(i){

                    html +="<div class=\"example col-xs-4 col-md-3\" >"
                        +"<input style='width: 20px;height: 20px;' type='checkbox' name='qjpicChecked' value='"+data.data[i].id+"'>"
                        +"<img class=\"img-rounded\" src=\""+idPicUrls+data.data[i].picurl+"\" ></div>";

                });

                $("#picContent").append(html);

            }
        },
        error: function() {
            alert("保存数据异常！");

        }
    });
}
//删除图片
function delAttach() {
    if($('input[name=qjpicChecked]:checked').length==0){
        alert("请选择至少一张图片删除");
        return ;
    }
    var ids ="";
    $.each($('input[name=qjpicChecked]:checked'),function () {
        ids+=$(this).val()+",";
    });
    $.ajax({
        type: "POST",
        url: '/qjpic/deleteByIds',
        dataType: 'json',
        data: {
            'ids':ids
        },
        success: function(data) {
            alert(data.errMsg);
            if(data.status=='0'){
                showPics();
            }
        },
        error: function() {
            alert("删除图片异常！");

        }
    });

}

$(document).ready(function () {
    var deptId = $("#deptId").val();
    if(deptId!=null && deptId!="" &&deptId !=undefined){
        showPics();
    }
});


//返回器材列表
function backQcList() {
    var deptId = $("#deptId").val();
    var deptPid =$("#deptPid").val();
    window.location.href="/sqc/showQcs?deptId="+deptId+"&deptPid="+deptPid;
}