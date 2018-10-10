var idPicUrls = $("#idPicUrls").val();
$(document).ready(function(){
    initCarVerify();
    setPx();
    Feng.initValidator("ycInfo", saveOrUpdates.validateFields);
});

var saveOrUpdates = {
	 validateFields:{
		 carAssessmentPrice:{
			 validators: {
//				notEmpty: {
//                    message: '市场价格不能为空'
//                },
				regexp: {
					regexp: /^[0-9]+([.][0-9]{1}){0,1}$/,
	                message: '输入项格式只能为阿拉伯数字允许保留一位小数'
	            },
	            stringLength: {
                    max:8,
                    message: '最大只能输入8个数字'
                }
			}
		 }
	 }
}

//初始化照片信息
function initPhotoMsg() {
    var carId = $("#carId").val();
    $.ajax({
        type: "POST",
        url: '/carPhotoInfo/selectByCarId',
        dataType: 'json',
        data: {
            'carId':carId
        },
        success: function(data) {
            var status = data.status;
            $("#showPhotoContent").html("");
            if(status=='0'){
                var html = "";
                $.each(data.data,function(i){
                    html+="<div class=\"col-sm-6\" style=\"margin-top: 65px;\"><div><label class=\"col-sm-3 control-label\">"
                    +data.data[i].photoName
                    +"</label></div><div class=\"fileinput-new thumbnail\" style=\"width:120px;height:85px;position:absolute;left:30%\"><img style=\"width:100%;height:75px;background-color: #cccccc47\" src=\""+idPicUrls+data.data[i].photoUrl+"\"></div>"
                    +"<button type=\"button\" class=\"btn btn-primary \" onclick=\"deleteImg("+data.data[i].id+");\" style=\"margin-left: 200px;\"><i class=\"fa fa-trash-o\"></i>&nbsp;删除</button></div>";
                });

               $("#showPhotoContent").append(html);
            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}



//初始化验车师
function initCarVerify() {
    var applyId = $("#applyId").val();
    var carId = $("#carId").val();
    $.ajax({
        type: "POST",
        url: '/carInfo/selInitCarverifyByCustId',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'carId':carId
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                $("#carVerifyId").val(data.data.id);
                $("#carId").val(data.data.carId);
                $("#custName").val(data.data.custName);
                $("#sex").val(data.data.sex);
                $("#carConfigName").val(data.data.carConfigName);
                $("#carNum").val(data.data.carNum);

                $("#carCond").val(data.data.carCond);
                $("#suggestion").val(data.data.suggestion);
                $("#configTablePhoto").val(data.data.configTablePhoto);
                if(data.data.configTablePhoto!=""&&data.data.configTablePhoto!=null){
                    $("#configTablePhotoImg").attr("src",data.data.configTablePhoto==null?"":idPicUrls+data.data.configTablePhoto);
                }
                $("#maintainPhoto").val(data.data.maintainPhoto);
                if(data.data.maintainPhoto!=""&&data.data.maintainPhoto!=null){
                    $("#maintainPhotoImg").attr("src",data.data.maintainPhoto==null?"":idPicUrls+data.data.maintainPhoto);
                }

                $("#carAssessmentPrice").val(data.data.carAssessmentPrice);
                setPx();
                initPhotoMsg();

            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}
//保存验车信息
function saveCarVerify() {
    var fileObj = document.getElementById("configTablePhoto1").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#configTablePhoto").val(fileUrl);

    fileObj = document.getElementById("maintainPhoto1").files[0];
    fileUrl = this.uploadFile(fileObj);
    $("#maintainPhoto").val(fileUrl);

    var id = $("#carVerifyId").val();
    var carId = $("#carId").val();
    var carCond = $("#carCond").val();
    var suggestion = $("#suggestion").val();
    var configTablePhoto = $("#configTablePhoto").val();
    var maintainPhoto = $("#maintainPhoto").val();
    var carAssessmentPrice = $("#carAssessmentPrice").val();
    
    if (!saveOrUpdates.validate()) {
    	return
    }
    
    $.ajax({
        type: "POST",
        url: '/carVerifyInfo/saveOrUpdate',
        dataType: 'json',
        data: {
            'id':id,
            'carId':carId,
            'carCond':carCond,
            'suggestion':suggestion,
            'configTablePhoto':configTablePhoto,
            'maintainPhoto':maintainPhoto,
            'carAssessmentPrice':carAssessmentPrice
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
                initCarVerify();
            }else{
                Feng.info("保存数据失败！"+data.msg);
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });

}




/**
 * 保存照片信息
 */
function save() {

    var fileObj = document.getElementById("photoUrl1").files[0];
    var fileUrl = this.uploadFile(fileObj);
    $("#photoUrl").val(fileUrl)

    var carId = $("#carId").val();
    var photoName = $("#photoName").val();
    var photoUrl = $("#photoUrl").val();
    var photoRemark = $("#photoRemark").val();

    $.ajax({
        type: "POST",
        url: '/carPhotoInfo/saveOrUpdate',
        dataType: 'json',
        data: {
            'carId':carId,
            'photoName':photoName,
            'photoUrl':photoUrl,
            'remark':photoRemark
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("保存数据成功！");
                layer.close(layerIndex);
                $("#showPhotoContent").html("");
                initPhotoMsg();
            }else{
                Feng.info("保存数据失败！"+data.msg);
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });

}


/*缩略图*/
function UploadImageicc(ev){
    var  idImgurl=$(ev).parent().nextAll()[0].children[0].id;
    var file = document.getElementById($(ev)[0].id).files[0];
    //判断 FileReader 是否被浏览器所支持
    if (!window.FileReader) return;
    //var file = evv.target.files[0];
    if(file.type.match('image/!*')){//如果是图片，则显示缩略图
        var reader = new FileReader();  // 创建FileReader对象
        reader.readAsDataURL(file); // 读取file对象，读取完毕后会返回result 图片base64格式的结果
        reader.onload = function(e){
            document.getElementById(idImgurl).src = e.target.result;
            document.getElementById(idImgurl).style ="width:100%;height:75px;background-color: #cccccc47";
        }
    }

}

function uploadFile(file) {
    var formData = new FormData();
    formData.append("file", file);
    var returnUrl = "";
    $.ajax({
        type: "POST",
        url: "/file/upload/idCard",
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
                returnUrl = data.data;
            }else{
                returnUrl = "";
            }
        },
        error: function(data) {
            Feng.info("保存异常！");
            returnUrl = "";
        }

    });
    return returnUrl;
}
//返回 跳转到我的待办列表
function back() {
    var applyId = $("#applyId").val();
    var interfaceAddress = $("#interfaceAddress").val();
    window.location.href="/carVerifyInfo/ycsSubmitPage?applyId="+applyId+"&interfaceAddress="+interfaceAddress;
}


function showAdd() {
    var applyId = $("#applyId").val();
    var carId = $("#carId").val();
    var index = layer.open({
        type: 2,
        title: '添加车辆照片',
        area: ['780px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/carPhotoInfo/showAddPics?applyId='+applyId+"&carId="+carId
    });
    this.layerIndex = index;
}

function deleteImg(id){
    $.ajax({
        type: "POST",
        url: '/carPhotoInfo/deleteById',
        dataType: 'json',
        data: {
            'id':id
        },
        success: function(data) {
            var status = data.status;
            if(status=='0'){
                Feng.info("删除照片成功！");
                $("#showPhotoContent").html("");
                initPhotoMsg();
            }else{
                Feng.info("保存数据失败！"+data.msg);
            }
        },
        error: function() {
            Feng.info("保存数据异常！");

        }
    });

}

//设置车况维修保养状况和配置表附件的位置
function setPx() {
    var tmp1 = $("#maintainPhotoImg").attr("src");
    var tmp2 = $("#configTablePhotoImg").attr("src");
    if(tmp1!=""&&tmp1!=null&&tmp1!=undefined){
        $("#maintainPhotoImg").attr("style","width:100%;height:75px;background-color: #cccccc47");
    }
    if(tmp2!=""&&tmp2!=null&&tmp2!=undefined){
        $("#configTablePhotoImg").attr("style","width:100%;height:75px;background-color: #cccccc47");
    }

}

//取消按钮
function cancle() {
    layer.close(layerIndex);
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
saveOrUpdates.set = function(key, val) {
    this.saveOrUpdate[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
saveOrUpdates.get = function(key) {
    return $("#" + key).val();
}

saveOrUpdates.validate= function () {
    $('#ycInfo').data("bootstrapValidator").resetForm();
    $('#ycInfo').bootstrapValidator('validate');
    return $("#ycInfo").data('bootstrapValidator').isValid();
}

