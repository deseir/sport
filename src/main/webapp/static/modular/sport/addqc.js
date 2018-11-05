var idPicUrls = $("#idPicUrls").val();
function saveQc() {
    var id = $("#qcId").val();
    var deptid = $("#deptId").val();
    var jssb = $("#jssb").val();
    var qcbh =$("#qcbh").val();
    var provider = $("#provider").val();
    var azsj = $("#azsj").val();
    var qcxz = $("#qcxz").val();
    var gzms = $("#gzms").val();
    var hcc = $("#hcc").val();
    var mfwhqx = $("#mfwhqx").val();
    var aqsyqx = $("#aqsyqx").val();
    $.ajax({
        type: "POST",
        url: '/sqc/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'deptid':deptid,
            'jssb':jssb,
            'qcbh':qcbh,
            'provider' :provider,
            'azsj' :azsj,
            'qcxz' :qcxz,
            'gzms':gzms,
            'hcc':hcc,
            'mfwhqx':mfwhqx,
            'aqsyqx':aqsyqx
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#qcId").val(data.qcId);
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
        var id = $("#qcId").val();

        $.ajax({
            type: "POST",
            url: '/cdssqcqt/updateWithOutNull',
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

//返回首页
function backIndex() {
    window.location.href="/qiantai/backIndex";
}

//返回器材列表
function backQcList() {
    var deptId = $("#deptId").val();
    var deptPid =$("#deptPid").val();
    window.location.href="/sqc/showQcs?deptId="+deptId+"&deptPid="+deptPid;
}

//上传图片
function uploadPic() {
    var qcid = $("#qcId").val();
    if(qcid==null || qcid=="" || qcid ==undefined){
        alert("请先保存器材信息！");
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
        url: '/sqcAttach/saveOrUpdate',
        dataType: 'json',
        data: {
            'qcid':qcid,
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
    var qcId = $("#qcId").val();
    $.ajax({
        type: "POST",
        url: '/sqcAttach/pageQuery',
        dataType: 'json',
        data: {
            'qcid':qcId
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
    var qcId = $("#qcId").val();
    if(qcId!=null && qcId!="" &&qcId !=undefined){
        showPics();
        $("#delBtn").show();
    }else{
        $("#delBtn").hide();
    }
});

function showBtns() {
    var qcId = $("#qcId").val();
    if(qcId !=null && qcId !="" && qcId !=undefined){
        $("#delBtn").show();
    }else{
        $("#delBtn").hide();
    }
}
