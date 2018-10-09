
var MgrUser = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    jieIndex:-1
};

/**
 * 初始化表格的列
 */
MgrUser.initColumn = function () {
    var columns = [
       {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '合同编号', field: 'contractNo', align: 'center', valign: 'middle', sortable: true},
        {title: '合同标题1', field: 'contractTitle1', align: 'center', valign: 'middle', sortable: true},
        {title: '合同地址1', field: 'contractUrl1', align: 'center', valign: 'middle', sortable: true},
        {title: '合同标题2', field: 'contractTitle2', align: 'center', valign: 'middle', sortable: true},
        {title: '合同地址2', field: 'contractUrl2', align: 'center', valign: 'middle', sortable: true},
        {title: '合同标题3', field: 'contractTitle3', align: 'center', valign: 'middle', sortable: true},
        {title: '合同地址3', field: 'contractUrl3', align: 'center', valign: 'middle', sortable: true},
        {title: '合同标题4', field: 'contractTitle4', align: 'center', valign: 'middle', sortable: true},
        {title: '合同地址4', field: 'contractUrl4', align: 'center', valign: 'middle', sortable: true},
        {title: '合同标题5', field: 'contractTitle5', align: 'center', valign: 'middle', sortable: true},
        {title: '合同地址5', field: 'contractUrl5', align: 'center', valign: 'middle', sortable: true},
        {title: '合同标题6', field: 'contractTitle6', align: 'center', valign: 'middle', sortable: true},
        {title: '合同地址6', field: 'contractUrl6', align: 'center', valign: 'middle', sortable: true},
        {title: '线下合同签订时间', field: 'contractSignDate', align: 'center', valign: 'middle', sortable: true},
        {title: '合同签署现场照片地址', field: 'contractSceneUrl', align: 'center', valign: 'middle', sortable: true},
        {title: '合同签订附件地址', field: 'contractAttachUrl', align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true}];
    return columns;
};

/**
 * 检查是否选中
 */
MgrUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        MgrUser.seItem = selected[0];
        return true;
    }
};
MgrUser.resetSearch = function () {
    $("#name").val("");
    MgrUser.search();
}

MgrUser.search = function () {
    var queryData = {};
    queryData['contractNo'] = $("#name").val().trim();
    queryData['contractNo'] = $("#name").val();
    MgrUser.table.refresh({query: queryData});
}
MgrUser.allocation = function () {
    var index = layer.open({
        type: 2,
        title: '上传合同模板pdf',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/contractInfo/uploadField'
    });
    MgrUser.layerIndex = index;
}
MgrUser.uploadField = function () {
    var fileObj = document.getElementById("uploadField1").files[0];
    var formData = new FormData();
    formData.append("file", fileObj);
    var returnUrl = "";
    $.ajax({
        type: "POST",
        url: "/contractInfo/uploadContractInfoField",
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
                Feng.info("保存成功！");
                returnUrl = data.data;
            }else{
                Feng.error("保存失败!"+ data.msg);
                returnUrl = "";
            }
        },
        error: function(data) {
            Feng.info("保存异常！");
            returnUrl = "";
        }

    });
    parent.layer.close(parent.MgrUser.layerIndex);
    return returnUrl;
}
/*缩略图*/
function UploadImageicc(ev){
    var  idImgurl=$(ev).parent().nextAll()[0].children[0].id;
    var file = document.getElementById($(ev)[0].id).files[0];
    //判断 FileReader 是否被浏览器所支持
    if (!window.FileReader) return;
    //var file = evv.target.files[0];
    if(!file.type.match('image/!*')){
        alert('上传的图片必修是png,gif,jpg格式的！');
        ev.target.value = ""; //显示文件的值赋值为空
        return;
    }
    var reader = new FileReader();  // 创建FileReader对象
    reader.readAsDataURL(file); // 读取file对象，读取完毕后会返回result 图片base64格式的结果
    reader.onload = function(e){
        document.getElementById(idImgurl).src = e.target.result;
        document.getElementById(idImgurl).style ="width:80px;height:40px;margin-left:300px;margin-top:-43px;";
    }
}
$(function () {
    var defaultColunms = MgrUser.initColumn();
    var table = new BSTable( "managerTable", "/contractInfo/findlist", defaultColunms);
    table.setPaginationType("server");
    MgrUser.table = table.init();
});
function LookPDF(event){
    var downloadFileurl=event.id;
    window.open("/contractInfo/downloadFile?downloadFileurl="+downloadFileurl);
}
/*
function LookPDF1(event){
    var imgName="D:/contactPdf/"+event.id;
    window.open("/imgLookController/imgLookController?imgName="+imgName);
}*/
