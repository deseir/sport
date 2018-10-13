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
    window.location.href="/";
}

//查看工程概况
function showPrjDetail() {
    var prjId =$("#prjId").val();
    window.location.href="/qiantai/showPrjDetail?prjId="+prjId;
}