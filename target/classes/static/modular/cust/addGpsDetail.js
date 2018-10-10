
var idPicUrls = $("#idPicUrls").val();
//gps信息保存
function saveOrUpdate() {
    var fileObj = document.getElementById("gpsPhotoUrl1").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#gpsPhotoUrl").val(fileUrl);
    var id = $("#gpsDetailId").val();
    if(id==-1){
        id=null;
    }
    var carId = $("#carId").val();
    var isWiredless = $("#isWiredless").val();
    var gpsWiredNo = $("#gpsWiredNo").val();
    var gpsPhotoUrl = $("#gpsPhotoUrl").val();
    var remark = $("#gpsDetailRemark").val();
    $.ajax({
        type: "POST",
        url: '/carGpsDetailInfo/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'carId':carId,
            'isWiredless':isWiredless,
            'gpsWiredNo':gpsWiredNo,
            'gpsPhotoUrl':gpsPhotoUrl,
            'remark':remark
        },
        success: function(data) {
            var status = data.status;
            if(status==0){
                Feng.info("保存数据成功！");
                window.parent.searchGpsDetail();
                parent.layer.close(window.parent.layerIndex);
            }else{
                Feng.info("保存数据失败！"+data.msg);
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });
}

$(document).ready(function () {
	

    var id = $("#gpsDetailId").val();
    if(id==-1){
        return;
    }else{
        $.ajax({
            type: "POST",
            url: '/carGpsDetailInfo/findById',
            dataType: 'json',
            data: {
                'id':id
            },
            success: function(data) {
                var status = data.status;
                if(status=='0'){
                    $("#gpsDetailId").val(data.data.id);
                    $("#carId").val(data.data.carId);
                    $("#isWiredless").val(data.data.isWiredless);
                    $("#gpsWiredNo").val(data.data.gpsWiredNo);
                    $("#gpsPhotoUrl").val(data.data.gpsPhotoUrl);
                    $("#gpsPhotoUrlmImg").attr("src",idPicUrls+data.data.gpsPhotoUrl);
                    $("#gpsDetailRemark").val(data.data.remark);
                    setImgPx();
                }
            },
            error: function() {
                Feng.info("查询数据异常！");

            }
        });
    }
});

function saveOrUpdate1111() {
    var id = $("#cardDetailId").val();
    if(id==-1){
        id=null;
    }
    var applyId = $("#applyId").val();
    var type = $("#paramType").val();
    var cardOrg = $("#cardOrg").val();
    var cardAmount = $("#cardAmount").val();
    var cardShareAmount= $("#cardShareAmount").val();
    var cardType = $("#cardType").val();
    var accountStatus = $("#accountStatus").val();
    var usedAmount  = $("#usedAmount").val();
    var avgUsedAmount =  $("#avgUsedAmount").val();
    var maxUsedAmount = $("#maxUsedAmount").val();
    var curOverdueNum = $("#curOverdueNum").val();
    var curOverdueAmount=$("#curOverdueAmount").val();
    var remark = $("#cardRemark").val();
    

    
    $.ajax({
        type: "POST",
        url: '/creditCardDetail/saveOrUpdate',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':type,
            'id':id,
            'cardOrg': cardOrg,
            'cardAmount': cardAmount,
            'cardShareAmount': cardShareAmount,
            'cardType':cardType,
            'accountStatus':accountStatus,
            'usedAmount': usedAmount,
            'avgUsedAmount': avgUsedAmount,
            'maxUsedAmount': maxUsedAmount,
            'curOverdueNum': curOverdueNum,
            'curOverdueAmount':curOverdueAmount,
            'remark': remark
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
                window.parent.searchCardDetail();
                parent.layer.close(window.parent.layerIndex);
            }else{
                Feng.info("保存数据失败！"+data.msg);
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });
    
}

