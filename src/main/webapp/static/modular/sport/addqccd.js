var idPicUrls = $("#idPicUrls").val();
function saveQcCd() {
    var id = $("#qccdId").val();
    var prjid =$("#prjId").val();
    var prjtype = $("#prjtype").val();
    var cdId = $("#cdId").val();
    var jssb = $("#jssb").val();
    var bh =$("#bh").val();
    var provider = $("#provider").val();
    var azsj = $("#azsj").val();
    var qcxz = $("#qcxz").val();
    var gzms = $("#gzms").val();
    var hcc = $("#hcc").val();
    var mfwhqx = $("#mfwhqx").val();
    var aqsyqx = $("#aqsyqx").val();
    var pic = $("#pic").val();
    $.ajax({
        type: "POST",
        url: '/cdssqccd/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'prjid':prjid,
            'prjtype':prjtype,
            'cdid':cdId,
            'jssb':jssb,
            'bh':bh,
            'provider' :provider,
            'azsj' :azsj,
            'qcxz' :qcxz,
            'gzms':gzms,
            'hcc':hcc,
            'mfwhqx':mfwhqx,
            'aqsyqx':aqsyqx,
            'pic' :pic
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#qccdId").val(data.qcCdId);
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
        var id = $("#qccdId").val();

        $.ajax({
            type: "POST",
            url: '/cdssqccd/updateWithOutNull',
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
    window.location.href="/";
}

//查看工程概况
function showPrjDetail() {
    var prjId =$("#prjId").val();
    window.location.href="/qiantai/showPrjDetail?prjId="+prjId;
}

function uploadPic() {
    var qccdId = $("#qccdId").val();
    if(qccdId==null || qccdId=="" || qccdId ==undefined){
        alert("请先保存基本信息！");
        return;
    }
    var fileObj = document.getElementById("fileInpBtn").files[0];
    if(fileObj==null||fileObj.name==null||fileObj.name==""){
        alert("请选择图片！");
        return;
    }
    var fileUrl = this.uploadFile(fileObj);
    var prjId =$("#prjId").val();
    var prjtype = $("#prjtype").val();
    var cdId = $("#cdId").val();
    $.ajax({
        type: "POST",
        url: '/attach/saveOrUpdate',
        dataType: 'json',
        data: {
            'type':5,//类型--场地类器材图片
            'prjid':prjId,
            'prjtype':prjtype,
            'cdsscdid':cdId,
            'qccdid':qccdId,
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
    var qccdId = $("#qccdId").val();
    var prjId =$("#prjId").val();
    var prjType = $("#prjtype").val();
    var cdId = $("#cdId").val();
    $.ajax({
        type: "POST",
        url: '/attach/pageQuery',
        dataType: 'json',
        data: {
            'qccdid':qccdId,
            'cdsscdid':cdId,
            'prjtype':prjType,
            'prjid':prjId,
            'type':5
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
    var qccdId = $("#qccdId").val();
    if(qccdId!=null && qccdId!="" &&qccdId !=undefined){
        showPics();
        $("#delBtn").show();
    }else{
        $("#delBtn").hide();
    }
});

function showBtns() {
    var qccdId = $("#qccdId").val();
    if(qccdId !=null && qccdId !="" && qccdId !=undefined){
        $("#delBtn").show();
    }else{
        $("#delBtn").hide();
    }
}