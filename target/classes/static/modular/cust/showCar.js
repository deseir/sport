 var idPicUrls =$("#idPicUrls").val();
/**
 * 车辆信息回显页面
 */
function carInfoVoLiClick(){
    if($("#carId").val()!=null&&$("#carId").val()!=undefined){
        $.ajax({
            type: "POST",
            url: '/carInfo/findCarInfoById',
            dataType: 'json',
            data:{carId:$("#carId").val()},
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                    $("#CarInfoId").val(data.data.carInfoVoId);
                    $("#carConfigName").val(data.data.carConfigName);
                    $("#currentLicDate").val(data.data.currentLicDate=""?"":data.data.currentLicDate.split(" ")[0]);
                    $("#carType").val(data.data.carType);
                    $("#engineNo").val(data.data.engineNo);
                    $("#mileage").val(data.data.mileage);
                    $("#carColor").val(data.data.carColor);
                    $("#carImportType").val(data.data.carImportType);
                    $("#displacement").val(data.data.displacement);
                    $("#carUsage").val(data.data.carUsage);
                    $("#firstLicDate").val(data.data.firstLicDate=""?"":data.data.firstLicDate.split(" ")[0]);
                    $("#registerPhotoUrl1mImg").attr('src',data.data.registerPhotoUrl1==""?"":idPicUrls+data.data.registerPhotoUrl1);
                    $("#registerPhotoUrl1").val(data.data.registerPhotoUrl1==""?"":idPicUrls+data.data.registerPhotoUrl1);
                    $("#registerPhotoUrl2mImg").attr('src',data.data.registerPhotoUrl2==""?"":idPicUrls+data.data.registerPhotoUrl2);
                    $("#registerPhotoUrl2").val(data.data.registerPhotoUrl2==""?"":idPicUrls+data.data.registerPhotoUrl2);
                    $("#carNum").val(data.data.carNum);
                    $("#productDate").val(data.data.productDate=""?"":data.data.productDate.split(" ")[0]);
                    $("#carBrand").val(data.data.carBrand);
                    $("#carModel").val(data.data.carModel);
                    $("#vin").val(data.data.vin);
                    $("#fuelType").val(data.data.fuelType);
                    $("#manufacturer").val(data.data.manufacturer);
                    $("#getType").val(data.data.getType);
                    $("#registerPhotoUrl3mImg").attr('src',data.data.registerPhotoUrl3==""?"":idPicUrls+data.data.registerPhotoUrl3);
                    $("#registerPhotoUrl3").val(data.data.registerPhotoUrl3==""?"":idPicUrls+data.data.registerPhotoUrl3);
                    $("#registerPhotoUrl4mImg").attr('src',idPicUrls+data.data.registerPhotoUrl4==""?"":idPicUrls+data.data.registerPhotoUrl4);
                    $("#registerPhotoUrl4").val(idPicUrls+data.data.registerPhotoUrl4==""?"":data.data.registerPhotoUrl4);
                    var carTransferInfolist=data.data.carTransferInfolist;
                    if(carTransferInfolist.length>0){
                        $("#newAddCarInfoTr").empty();
                        for(var i =0;i<carTransferInfolist.length;i++){
                            carTransferInfolist[i].regDate=""?"":carTransferInfolist[i].regDate.split(" ")[0];
                            var str1='<tr><td type="hidden" style="display:none" >'+carTransferInfolist[i].id+'</td><td style="text-align: center">'+carTransferInfolist[i].name+'</td><td style="text-align: center">'+carTransferInfolist[i].certId+'</td><td style="text-align: center">'+carTransferInfolist[i].getType+'</td><td style="text-align: center">'+ carTransferInfolist[i].regDate+'</td></tr>';
                            $("#newAddCarInfoTr").append(str1);
                        }

                    }
                    var carMortgageInfoList=data.data.carMortgageInfoList;
                    if(carMortgageInfoList.length>0){
                        $("#newAddCarMortgageInfoTr").empty();
                        for(var i =0;i<carMortgageInfoList.length;i++){
                            carMortgageInfoList[i].regDate=""?"":carMortgageInfoList[i].regDate.split(" ")[0];
                            var str1='<tr><td type="hidden" style="display:none" >'+carMortgageInfoList[i].id+'</td><td style="text-align: center">'+carMortgageInfoList[i].name+'</td><td style="text-align: center">'+carMortgageInfoList[i].certId+'</td><td style="text-align: center">'+carMortgageInfoList[i].regDate+'</td></tr>';
                            $("#newAddCarMortgageInfoTr").append(str1);
                        }

                    }
                    var carDriverInfo=data.data.carDriverInfo;
                    if(carDriverInfo!=""){
                        $("#carDriverInfoId").val(carDriverInfo.id);
                        $("#vehicleValidDate").val(carDriverInfo.vehicleValidDate=""?"":carDriverInfo.vehicleValidDate.split(" ")[0]);
                        $("#driverNo").val(carDriverInfo.driverNo);
                        $("#permitType").val(carDriverInfo.permitType);
                        $("#driverBeginDate").val(carDriverInfo.driverBeginDate=""?"":carDriverInfo.driverBeginDate.split(" ")[0]);
                        $("#driverEndDate").val(carDriverInfo.driverEndDate=""?"":carDriverInfo.driverEndDate.split(" ")[0]);
                        $("#vehicleFrontPhotoImg").attr('src',carDriverInfo.vehicleFrontPhoto==""?"":idPicUrls+carDriverInfo.vehicleFrontPhoto);
                        $("#vehicleFrontPhoto").val(carDriverInfo.vehicleFrontPhoto==""?"":idPicUrls+carDriverInfo.vehicleFrontPhoto);
                        $("#vehicleBackPhotoImg").attr('src',carDriverInfo.vehicleBackPhoto==""?"":idPicUrls+carDriverInfo.vehicleBackPhoto);
                        $("#vehicleBackPhoto").val(carDriverInfo.vehicleBackPhoto==""?"":idPicUrls+carDriverInfo.vehicleBackPhoto);
                        $("#isDriverLic").val(carDriverInfo.isDriverLic);
                        $("#firstDriverDate").val(carDriverInfo.firstDriverDate=""?"":carDriverInfo.firstDriverDate.split(" ")[0]);
                        $("#isSelf").val(carDriverInfo.isSelf);
                        $("#driverRelation").val(carDriverInfo.driverRelation);
                        $("#driverRemark").val(carDriverInfo.driverRemark);
                        $("#driverFrontPhotoImg").attr('src',carDriverInfo.driverFrontPhoto==""?"":idPicUrls+carDriverInfo.driverFrontPhoto);
                        $("#driverFrontPhoto").val(carDriverInfo.driverFrontPhoto==""?"":idPicUrls+carDriverInfo.driverFrontPhoto);
                        $("#driverBackPhotoImg").attr('src',carDriverInfo.driverBackPhoto==""?"":idPicUrls+carDriverInfo.driverBackPhoto);
                        $("#driverBackPhoto").val(carDriverInfo.driverBackPhoto==""?"":idPicUrls+carDriverInfo.driverBackPhoto);
                    }
                    var carPeccancyInfo=data.data.carPeccancyInfo;
                    if(carPeccancyInfo!=""){
                        $("#carPeccancyInfoId").val(carPeccancyInfo.id);
                        $("#totalNum").val(carPeccancyInfo.totalNum);
                        $("#totalMoney").val(carPeccancyInfo.totalMoney);
                        $("#totalValue").val(carPeccancyInfo.totalValue);
                        $("#totalFullNum").val(carPeccancyInfo.totalFullNum);
                    }
                    var carTrafficInsureInfo=data.data.carTrafficInsureInfo;
                    if(carTrafficInsureInfo!=""){
                        $("#carTrafficInsureInfoId").val(carTrafficInsureInfo.id);
                        $("#instFullName").val(carTrafficInsureInfo.instFullName);
                        $("#bussinessSource").val(carTrafficInsureInfo.bussinessSource);
                        $("#insureBeginTime").val(carTrafficInsureInfo.insureBeginTime=""?"":carTrafficInsureInfo.insureBeginTime.split(" ")[0]);
                        $("#insurePerson").val(carTrafficInsureInfo.insurePerson);
                        $("#totalAmount").val(carTrafficInsureInfo.totalAmount);
                        $("#vehicleTax").val(carTrafficInsureInfo.vehicleTax);
                        $("#photoUrlmImg").attr('src',carTrafficInsureInfo.photoUrl==""?"":idPicUrls+carTrafficInsureInfo.photoUrl);
                        $("#photoUrl").val(carTrafficInsureInfo.photoUrl==""?"":idPicUrls+carTrafficInsureInfo.photoUrl);
                        $("#proxyName").val(carTrafficInsureInfo.proxyName);
                        $("#insureNumber").val(carTrafficInsureInfo.insureNumber);
                        $("#insureEndTime").val(carTrafficInsureInfo.insureEndTime=""?"":carTrafficInsureInfo.insureEndTime.split(" ")[0]);
                        $("#floatProp").val(carTrafficInsureInfo.floatProp);
                        $("#signDate").val(carTrafficInsureInfo.signDate=""?"":carTrafficInsureInfo.signDate.split(" ")[0]);
                        $("#specialAgreement").val(carTrafficInsureInfo.specialAgreement);
                        $("#remark").val(carTrafficInsureInfo.remark);
                    }
                    var carBussInsureInfo=data.data.carBussInsureInfo;
                    if(carBussInsureInfo!=""){
                        $("#CarBussInsureInfoId").val(carBussInsureInfo.id);
                        $("#instFullName1").val(carBussInsureInfo.instFullName);
                        $("#bussinessSource1").val(carBussInsureInfo.bussinessSource);
                        $("#insureBeginTime1").val(carBussInsureInfo.insureBeginTime=""?"":carBussInsureInfo.insureBeginTime.split(" ")[0]);
                        $("#insurePerson1").val(carBussInsureInfo.insurePerson);
                        $("#totalAmount1").val(carBussInsureInfo.totalAmount);
                        $("#vehicleTax1").val(carBussInsureInfo.vehicleTax);
                        $("#photoUrlmImg1").attr('src',carBussInsureInfo.photoUrl==""?"":idPicUrls+carBussInsureInfo.photoUrl);
                        $("#photoUrl111").val(carBussInsureInfo.photoUrl==""?"":idPicUrls+carBussInsureInfo.photoUrl);
                        $("#proxyName1").val(carBussInsureInfo.proxyName);
                        $("#insureNumber1").val(carBussInsureInfo.insureNumber);
                        $("#insureEndTime1").val(carBussInsureInfo.insureEndTime=""?"":carBussInsureInfo.insureEndTime.split(" ")[0]);
                        $("#floatProp1").val(carBussInsureInfo.floatProp);
                        $("#signDate1").val(carBussInsureInfo.signDate=""?"":carBussInsureInfo.signDate.split(" ")[0]);
                        $("#specialAgreement1").val(carBussInsureInfo.specialAgreement);
                    }
                    var carInsureDetailInfoList=data.data.carInsureDetailInfoList;
                    if(carInsureDetailInfoList.length>0){
                        for(var i =0;i<carInsureDetailInfoList.length;i++){
                            $("#carInsureDetailInfoCheck").children('div').each(function () {
                                if($(this).children().find('input')[1].value==carInsureDetailInfoList[i].type){
                                    $(this).children().find('input')[0].id=carInsureDetailInfoList[i].id;
                                    $(this).children().find('input')[1].checked=true;
                                    if( carInsureDetailInfoList[i].isNoDeduct==0){
                                        $(this).children().find('input')[2].checked=true;
                                    }else{
                                        $(this).children().find('input')[3].checked=true;
                                    }
                                    $(this).children().find('input')[4].value=carInsureDetailInfoList[i].maxPayAmount;
                                    $(this).children().find('input')[5].value=carInsureDetailInfoList[i].amount;
                                }
                            });
                        }
                    }
                }
                setImgPx();
            },

        });
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
                    html+="<div style=\"margin-top:95px\" class=\"col-sm-6\"><div><label class=\"col-sm-3 control-label\">"
                        +data.data[i].photoName
                        +"</label></div><div class=\"fileinput-new thumbnail\" style=\"width:120px;height:85px;position:absolute;left:40%;margin-top:-45px\"><img style=\"width:100%;height:75px;background-color: #cccccc47\" src=\""+idPicUrls+data.data[i].photoUrl+"\"></div>"
                        +"</div>";
                });
                $("#showPhotoContent").append(html);
                // setImgPx();
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
            if(status==0){
                $("#carVerifyId").val(data.data.id);
                $("#carId").val(data.data.carId);
                $("#custName").val(data.data.custName);
                $("#sex").val(data.data.sex);
                // $("#carConfigName").val(data.data.carConfigName);
                // $("#carNum").val(data.data.carNum);

                $("#carCond").val(data.data.carCond);
                $("#suggestion").val(data.data.suggestion);
                $("#configTablePhoto").val(data.data.configTablePhoto);
                $("#configTablePhotoImg").attr("src",data.data.configTablePhoto==null?"":idPicUrls+data.data.configTablePhoto);
                $("#maintainPhoto").val(data.data.maintainPhoto);
                $("#maintainPhotoImg").attr("src",data.data.maintainPhoto==null?"":idPicUrls+data.data.maintainPhoto);
                $("#carAssessmentPrice").val(data.data.carAssessmentPrice);
                //setImgPx();
                initPhotoMsg();

            }
        },
        error: function() {
            Feng.info("查询数据异常！");

        }
    });
}

$(document).ready(function () {
    carInfoVoLiClick();
    initCarVerify();
});

//设置回显图片的位置
function setImgPx() {
    var imgList = $("img");
    for(var i=0;i<imgList.length;i++){
        if(imgList.eq(i).attr("src")!=null && imgList.eq(i).attr("src")!=''&&imgList.eq(i).attr("src")!=undefined){
            imgList.eq(i).attr("style","width:100%;height:75px;background-color: #cccccc47");
        }
    }

}