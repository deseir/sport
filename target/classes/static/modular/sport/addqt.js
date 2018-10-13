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
    window.location.href="/";
}

//查看工程概况
function showPrjDetail() {
    var prjId =$("#prjId").val();
    window.location.href="/qiantai/showPrjDetail?prjId="+prjId;
}