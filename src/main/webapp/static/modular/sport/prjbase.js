var idPicUrls = $("#idPicUrls").val();
function savePrj() {
    var id = $("#prjId").val();
    var prjname = $("#prjname").val();
    var prjtype = $("#prjtype").val();
    var local =$("#local").val();
    var place = $("#place").val();
    var area = $("#area").val();
    var begintime = $("#begintime").val();
    var endtime = $("#endtime").val();
    var provider = $("#provider").val();
    var jsfa = $("#jsfa").val();
    var dljd = $("#dljd").val();
    var dlwd = $("#dlwd").val();
    var qjpic1 = $("#qjpic1").val();
    var qjpic2 = $("#qjpic2").val();
    var qjpic3 = $("#qjpic3").val();

    $.ajax({
        type: "POST",
        url: '/sprjbase/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'prjname':prjname,
            'prjtype':prjtype,
            'local':local,
            'place':place,
            'area':area,
            'begintime' :begintime,
            'endtime' :endtime,
            'provider' :provider,
            'jsfa' :jsfa,
            'dljd' :dljd,
            'dlwd' :dlwd,
            'qjpic1' :qjpic1,
            'qjpic2' :qjpic2,
            'qjpic3' :qjpic3
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#prjId").val(data.prjId);
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
        var id = $("#prjId").val();

        $.ajax({
            type: "POST",
            url: '/sprjbase/updateWithOutNull',
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

//跳转场地列表页
function showCd() {
    var prjId = $("#prjId").val();
    var prjtype = $("#prjtype").val();
    window.location.href="/sprjbase/showCdList?prjId="+prjId+"&prjType="+prjtype;
}

//跳转健身路径列表页
function showJslj() {
    var prjId = $("#prjId").val();
    var prjtype = $("#prjtype").val();
    window.location.href="/sprjbase/showJsljList?prjId="+prjId+"&prjType="+prjtype;
}


//跳转其他列表页
function showQt() {
    var prjId = $("#prjId").val();
    var prjtype = $("#prjtype").val();
    window.location.href="/sprjbase/showQtList?prjId="+prjId+"&prjType="+prjtype;
}


function uploadPic() {
    var fileObj = document.getElementById("fileInpBtn").files[0];
    if(fileObj==null||fileObj.name==null||fileObj.name==""){
        alert("请选择图片！");
        return;
    }
    var prjId = $("#prjId").val();
    if(prjId==""||prjId==null||prjId==undefined){
        alert("请先保存基本信息！");
        return;
    }
    var fileUrl = this.uploadFile(fileObj);
    if(fileUrl==""||fileUrl==null){
        alert("上传图片失败!");
        return;
    }
    var prjtype = $("#prjtype").val();
    $.ajax({
        type: "POST",
        url: '/attach/saveOrUpdate',
        dataType: 'json',
        data: {
            'type':1,
            'prjid':prjId,
            'prjtype':prjtype,
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
    var prjId = $("#prjId").val();
    var formData = new FormData();
    formData.append("file", file);
    formData.append("prjId",prjId);
    var returnUrl = "";
    $.ajax({
        type: "POST",
        url: "/prjfile/upload/pic",
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
                var jd = data.jd;
                var wd = data.wd;
                if(jd!=null&&jd!=undefined&&jd!=""){
                    $("#dljd").val(jd);
                }
                if(wd!=null&&wd!=undefined&&wd!=""){
                    $("#dlwd").val(wd);
                }

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
    var prjId = $("#prjId").val();
    var prjType = $("#prjtype").val();
    $.ajax({
        type: "POST",
        url: '/attach/pageQuery',
        dataType: 'json',
        data: {
            'prjtype':prjType,
            'prjid':prjId,
            'type':1
        },
        success: function(data) {
            var status = data.status;
            $("#met-grid").html("");
            if(status=='0'){
                var html ="";
                $.each(data.data.list,function(i){

                    html +="<li class=\"shown\" >"
                        +"<div class=\"card card-shadow\">"
                        +"<img class=\"cover-image\" src=\""+idPicUrls+data.data.list[i].picurl+"\" ></li>";

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
    showPics();
    showBtns();
});

function showBtns() {
    var prjId = $("#prjId").val();
    if(prjId !=null && prjId !="" && prjId !=undefined){
        $("#delBtn").show();
        $("#seeCdBtn").show();
        $("#seeJsljBtn").show();
        $("#seeQtBtn").show();
    }else{
        $("#delBtn").hide();
        $("#seeCdBtn").hide();
        $("#seeJsljBtn").hide();
        $("#seeQtBtn").hide();
    }
}