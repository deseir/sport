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
    window.location.href="/";
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
