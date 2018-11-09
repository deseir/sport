var idPicUrls = $("#idPicUrls").val();

function showPics() {
    var qcId = $("#qcId").val();
    if(qcId==null || qcId==""||qcId==undefined){
     return;
    }
    $.ajax({
        type: "POST",
        url: '/sqcAttach/pageQuery',
        dataType: 'json',
        data: {
            'qcid':qcId
        },
        success: function(data) {
            var status = data.status;
            $("#picContent").html("");
            if(status=='0'){
                var html ="";
                $.each(data.data.list,function(i){

                    html +="<div class=\"example col-xs-4 col-md-3\" >"
                        +"<img class=\"img-rounded\" src=\""+idPicUrls+data.data.list[i].picurl+"\" ></div>";

                });

                $("#picContent").append(html);

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
    }else{
    }
});

//返回器材列表
function backQcList() {
    var deptId = $("#deptId").val();
    window.location.href="/sqc/showAllQc?deptId="+deptId;
}