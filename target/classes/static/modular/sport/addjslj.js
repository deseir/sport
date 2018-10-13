function saveJslj() {
    var id = $("#jsljId").val();
    var prjid =$("#prjId").val();
    var prjtype = $("#prjtype").val();
    var mc = $("#mc").val();
    var chang = $("#chang").val();
    var kuan =$("#kuan").val();
    var area = $("#area").val();
    var dbqk = $("#dbqk").val();
    var cllx = $("#cllx").val();
    var pic = $("#pic").val();
    $.ajax({
        type: "POST",
        url: '/cdssjslj/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'prjid':prjid,
            'prjtype':prjtype,
            'mc':mc,
            'chang':chang,
            'kuan':kuan,
            'area':area,
            'dbqk' :dbqk,
            'cllx' :cllx,
            'pic' :pic
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#jsljId").val(data.jsljId);
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
        var id = $("#jsljId").val();

        $.ajax({
            type: "POST",
            url: '/cdssjslj/updateWithOutNull',
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