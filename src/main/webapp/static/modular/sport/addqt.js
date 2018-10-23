var idPicUrls = $("#idPicUrls").val();
function saveQt() {
    var id = $("#qtId").val();
    var prjid =$("#prjId").val();
    var jsss = $("#jsss").val();
    var prjtype = $("#prjtype").val();
    var num = $("#num").val();
    var area = $("#area").val();
    var wz = $("#wz").val();
    var beizhu = $("#beizhu").val();
    var pic = $("#pic").val();
    $.ajax({
        type: "POST",
        url: '/cdssqt/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'prjid':prjid,
            'jsss':jsss,
            'prjtype':prjtype,
            'num':num,
            'area':area,
            'wz' :wz,
            'beizhu' :beizhu,
            'pic' :pic
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#qtId").val(data.qtId);
                alert("保存数据成功！");
                showBtns();
            }else{
                alert("保存数据失败！"+data.msg);
            }
        },
        error: function() {
            alert("保存数据异常！");

        }
    });

}

function logicDelete() {
        var id = $("#qtId").val();

        $.ajax({
            type: "POST",
            url: '/cdssqt/updateWithOutNull',
            dataType: 'json',
            data: {
                'id':id,
                'isdelete':1
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    alert("删除成功！");
                }else{
                    alert("删除失败！"+data.msg);
                }
            },
            error: function() {
                alert("删除异常！");

            }
        });
}

function back() {
    var prjType = $("#prjtype").val();
    window.location.href="/qiantai/showQt?prjType="+prjType;
}

//查看工程概况
function showPrjDetail() {
    var prjId =$("#prjId").val();
    window.location.href="/qiantai/showPrjDetail?prjId="+prjId;
}

//跳转到其他类器材列表页面
function showQcQt(){
    var qtId = $("#qtId").val();
    var prjId =$("#prjId").val();
    var prjType = $("#prjtype").val();
    window.location.href="/cdssqt/showQcQtList?qtId="+qtId+"&prjId="+prjId+"&prjType="+prjType;
}

function uploadPic() {
    var qtId = $("#qtId").val();
    if(qtId==null || qtId=="" || qtId ==undefined){
        alert("请先保存基本信息！");
        return;
    }
    var fileObj = document.getElementById("fileInpBtn").files[0];
    if(fileObj==null||fileObj.name==null||fileObj.name==""){
        alert("请选择图片！");
        return;
    }
    var fileUrl = this.uploadFile(fileObj);
    var prjId = $("#prjId").val();
    var prjtype = $("#prjtype").val();
    $.ajax({
        type: "POST",
        url: '/attach/saveOrUpdate',
        dataType: 'json',
        data: {
            'type':4,//类型--其他类图片
            'prjid':prjId,
            'prjtype':prjtype,
            'cdssqtid':qtId,
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
            Feng.info("上传图片异常！");
            returnUrl = "";
        }

    });
    return returnUrl;
}

function showPics() {
    var qtId = $("#qtId").val();
    var prjId =$("#prjId").val();
    var prjType = $("#prjtype").val();
    $.ajax({
        type: "POST",
        url: '/attach/pageQuery',
        dataType: 'json',
        data: {
            'cdssqtid':qtId,
            'prjtype':prjType,
            'prjid':prjId,
            'type':4
        },
        success: function(data) {
            var status = data.status;
            $("#met-grid").html("");
            if(status=='0'){
                var html ="";
                $.each(data.data.list,function(i){

                    html +="<li class=\"shown\" >"
                        +"<img class=\"cover-image\" style=\"margin-top: 20px;\" src=\""+idPicUrls+data.data.list[i].picurl+"\" ></li>";

                });

                $("#met-grid").append(html);

            }
        },
        error: function() {
            alert("保存数据异常！");

        }
    });
}
$(document).ready(function () {
    var qtId = $("#qtId").val();
    if(qtId!=null && qtId!="" &&qtId !=undefined){
        showPics();
        $("#delBtn").show();
        $("#qcGlBtn").show();
    }else{
        $("#delBtn").hide();
        $("#qcGlBtn").hide();
    }
});

function showBtns() {
    var qtId = $("#qtId").val();
    if(qtId !=null && qtId !="" && qtId !=undefined){
        $("#delBtn").show();
        $("#qcGlBtn").show();
    }else{
        $("#delBtn").hide();
        $("#qcGlBtn").hide();
    }
}