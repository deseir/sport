var idPicUrls = $("#idPicUrls").val();
function saveCd() {
    var id = $("#cdId").val();
    var prjid =$("#prjId").val();
    var cdname = $("#cdname").val();
    var prjtype = $("#prjtype").val();
    var chang = $("#chang").val();
    var kuan =$("#kuan").val();
    var area = $("#area").val();
    var dimian = $("#dimian").val();
    var cddbqk = $("#cddbqk").val();
    var beizhu = $("#beizhu").val();
    var pic = $("#pic").val();
    $.ajax({
        type: "POST",
        url: '/cdsscd/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'prjid':prjid,
            'cdname':cdname,
            'prjtype':prjtype,
            'chang':chang,
            'kuan':kuan,
            'area':area,
            'dimian' :dimian,
            'cddbqk' :cddbqk,
            'beizhu' :beizhu,
            'pic' :pic
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#cdId").val(data.cdId);
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
        var id = $("#cdId").val();

        $.ajax({
            type: "POST",
            url: '/cdsscd/updateWithOutNull',
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
    window.location.href="/qiantai/backIndex";
}


//跳转到场地器材列表页面
function showQcCd(){
    var cdId = $("#cdId").val();
    var prjId =$("#prjId").val();
    var prjType = $("#prjtype").val();
    window.location.href="/cdsscd/showQcCdList?cdId="+cdId+"&prjId="+prjId+"&prjType="+prjType;
}


function uploadPic() {
    var cdId = $("#cdId").val();
    if(cdId==null || cdId=="" || cdId ==undefined){
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
            'type':2,//类型--场地类图片
            'prjid':prjId,
            'prjtype':prjtype,
            'cdsscdid':cdId,
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
    var cdId = $("#cdId").val();
    var prjId =$("#prjId").val();
    var prjType = $("#prjtype").val();
    $.ajax({
        type: "POST",
        url: '/attach/pageQuery',
        dataType: 'json',
        data: {
            'cdsscdid':cdId,
            'prjtype':prjType,
            'prjid':prjId,
            'type':2
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
    var cdId = $("#cdId").val();
    if(cdId!=null && cdId!="" &&cdId !=undefined){
        showPics();
        $("#delBtn").show();
        $("#qcGlBtn").show();
    }else{
        $("#delBtn").hide();
        $("#qcGlBtn").hide();
    }
});

function showBtns() {
    var cdId = $("#cdId").val();
    if(cdId !=null && cdId !="" && cdId !=undefined){
        $("#delBtn").show();
        $("#qcGlBtn").show();
    }else{
        $("#delBtn").hide();
        $("#qcGlBtn").hide();
    }
}

//返回场地列表
function backCdList(){
    var prjid =$("#prjId").val();
    var prjtype = $("#prjtype").val();
    window.location.href="/sprjbase/showCdList?prjId="+prjid+"&prjType="+prjtype;
}