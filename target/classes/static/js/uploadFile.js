//下载补充材料附件
    function downLoadSupplementaryMaterial(event) {
        var supplementAttachUrl =$("#"+event).val();
        if(supplementAttachUrl==""||supplementAttachUrl==null||supplementAttachUrl==undefined){
            Feng.info("无补充材料附件！");
        }else{
            var params = supplementAttachUrl.replace("///g","\\");
            window.location.href="/supplementInfo/downLoadSupplementaryMaterial?params="+params;
        }
    }