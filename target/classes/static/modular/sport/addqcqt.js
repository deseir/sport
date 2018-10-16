function saveQcQt() {
    var id = $("#qcqtId").val();
    var prjid =$("#prjId").val();
    var prjtype = $("#prjtype").val();
    var qtId = $("#qtId").val();
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
        url: '/cdssqcqt/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'prjid':prjid,
            'prjtype':prjtype,
            'qtid':qtId,
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
                $("#qcqtId").val(data.qcQtId);
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
        var id = $("#qcJsljId").val();

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

function back() {
    window.location.href="/";
}

//查看工程概况
function showPrjDetail() {
    var prjId =$("#prjId").val();
    window.location.href="/qiantai/showPrjDetail?prjId="+prjId;
}