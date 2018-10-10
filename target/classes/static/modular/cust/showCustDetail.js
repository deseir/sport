$(document).ready(function(){
    custliclick();
});

/**
 * 点击客户基本信息回显页面
 */
function custliclick(){
    if($("#applyId").val()!=null&&$("#applyId").val()!=undefined){
        $.ajax({
            type: "POST",
            url: '/familyBookInfo/goBackfamilyBookInfoByApplyId',
            dataType: 'json',
            data:{ApplyId:$("#applyId").val()},
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                    var idPicUrls = data.idPicUrls;
                    //身份证信息
                    if(data.data2.marryStatus==0||data.data2.marryStatus==2){
                        $("#spouseName1").parent().parent().show();
                        $("#spousePhone1").parent().parent().show();
                        $("#spouseName1").val(data.data2.spouseName);
                        $("#spousePhone1").val(data.data1.spousePhone);
                    }else{
                        $("#spouseName1").parent().parent().hide();
                        $("#spousePhone1").parent().parent().hide();
                    }
                    $("#contractName1").val(data.data1.contractName1);
                    $("#contractPhone1").val(data.data1.contractPhone1);
                    $("#contractRelation1").val(data.data1.contractRelation1);
                    $("#contractName2").val(data.data1.contractName2);
                    $("#contractPhone2").val(data.data1.contractPhone2);
                    $("#contractRelation2").val(data.data1.contractRelation2);
                    $("#contractName3").val(data.data1.contractName3);
                    $("#contractPhone3").val(data.data1.contractPhone3);
                    $("#contractRelation3").val(data.data1.contractRelation3);
                    $('#name').val(data.data1.name);
                    $('#mobile').val(data.data1.mobile);
                    $('#sex').val(data.data1.sex==""?"0":data.data1.sex);
                    $('#signOrg').val(data.data1.signOrg);
                    $('#validateEnd').val(data.data1.validateEnd=""?"":data.data1.validateEnd.split(" ")[0]);
                    $('#idFrontPhotoUrlmImg').attr('src',data.data1.idFrontPhotoUrl==""?"":(idPicUrls+data.data1.idFrontPhotoUrl));
                    $('#idFrontPhotoUrl').val(data.data1.idFrontPhotoUrl==""?"":(idPicUrls+data.data1.idFrontPhotoUrl));
                    $('#nation').val(data.data1.nation);
                    $('#certId').val(data.data1.certId);
                    $('#education').val(data.data1.education==""?"0":data.data1.education);
                    $('#birthday').val(data.data1.birthday=""?"":data.data1.birthday.split(" ")[0]);
                    $('#validateBegin').val(data.data1.validateBegin=""?"":data.data1.validateBegin.split(" ")[0]);
                    $('#idBackPhotoUrlmImg').attr('src',data.data1.idBackPhotoUrl==""?"":(idPicUrls+data.data1.idBackPhotoUrl));
                    $('#idBackPhotoUrl').val(data.data1.idBackPhotoUrl==""?"":(idPicUrls+data.data1.idBackPhotoUrl));
                    $('#childNum').val(data.data1.childNum);
                    $('#liveAddress').val(data.data1.liveAddress);
                    obj=document.getElementsByName("childAdult");
                    for(k in obj){
                        if(parseInt(parseInt(data.data1.childAdult)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                            obj[k].checked=true;
                            if(parseInt(obj[k].value)!=parseInt(1)){
                                $("#childNum").show();
                            }
                        }
                    }
                    obj=document.getElementsByName("liveType");
                    for(k in obj){
                        if(parseInt(parseInt(data.data1.liveType)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                            obj[k].checked=true;
                        }
                    }
                    obj=document.getElementsByName("togetherLive");
                    for(k in obj){
                        if(parseInt(parseInt(data.data1.togetherLive)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                            obj[k].checked=true;
                        }
                    }
                    //户口本信息
                    $('#familyBookInfoId').val(data.data.familyBookInfoId);
                    $('#relationship').val(data.data.relationship);
                    $('#masterName').val(data.data.masterName);
                    $('#masterSex').val(data.data.masterSex==""?"0":data.data.masterSex);
                    $('#masterCertId').val(data.data.certId);
                    $('#firstPagePhotoUrlImg').attr('src',data.data.firstPagePhotoUrl==""?"":(idPicUrls+data.data.firstPagePhotoUrl));
                    $('#firstPagePhotoUrl').val(data.data.firstPagePhotoUrl==""?"":(idPicUrls+data.data.firstPagePhotoUrl));
                    var familyBookSubInfolist=data.data.familyBookSubInfolist;
                    if(familyBookSubInfolist.length>0){
                            $("#newAddFamilyBookSubInfoTr").empty();
                           for(var i =0;i<familyBookSubInfolist.length;i++){
                               if(familyBookSubInfolist[i].sex==0){
                                   var str1='<tr><td type="hidden" style="display:none" >'+familyBookSubInfolist[i].id+'</td><td style="text-align: center">'+familyBookSubInfolist[i].relationship+'</td><td style="text-align: center">'+familyBookSubInfolist[i].name+'</td><td style="text-align: center">'+familyBookSubInfolist[i].certId+'</td><td style="text-align: center">男</td><td style="display:none">'+idPicUrls+familyBookSubInfolist[i].bookPhotoUrl+'</td></tr>';
                               }else{
                                   var str1='<tr><td type="hidden" style="display:none" >'+familyBookSubInfolist[i].id+'</td><td style="text-align: center">'+familyBookSubInfolist[i].relationship+'</td><td style="text-align: center">'+familyBookSubInfolist[i].name+'</td><td style="text-align: center">'+familyBookSubInfolist[i].certId+'</td><td style="text-align: center">女</td><td style="display:none">'+idPicUrls+familyBookSubInfolist[i].bookPhotoUrl+'</td></tr>';
                               }
                              $("#newAddFamilyBookSubInfoTr").append(str1);
                            }

                    }
                }
                setImgPx();
            },

        });
    }
}

/**
 * 点击婚姻信息回显页面
 */
function marryLiClick(){
    if($("#applyId").val()!=null&&$("#applyId").val()!=undefined){
        $.ajax({
            type: "POST",
            url: '/marryInfo/findmarryInfoByApplyId',
            dataType: 'json',
            data:{ApplyId:$("#applyId").val()},
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                    var idPicUrls = data.idPicUrls;
                    var identityPicPath = data.identityPicPath;
                    if(data.data.marryStatus==0||data.data.marryStatus==2){//已婚或再婚
                    	$("#carPhotoBefore").show();
                        $("#carPhotoBehand").show();
                        $("#marryPhoto").show();
                        $("#divorcePhoto").hide();
                        $("#deathCertPhoto").hide();
                        $("#divorcePhotoUrlmImg").parent().hide();
                        $("#deathCertPhotoUrlmImg").parent().hide();
                        $("#spouseidBackPhotoUrlmImg").parent().show();
                        $("#marryPhotoUrlmImg").parent().show();
                        $("#spouseidFrontPhotoUrlmImg").parent().show();
                        $("#marryDate").parent().parent().show();
                        $("#divorceDate").parent().parent().hide();
                        $("#spouseName").parent().parent().show();
                        $("#divorceName").parent().parent().hide();
                        $("#spousevalidateEnd").parent().parent().show();
                        $("#spouseidFrontPhotoUrl").parent().show();
                        $("#marryPhotoUrl").parent().show();
                        $("#divorcePhotoUrl").parent().hide();
                        $("#divorcePhotoUrl1").parent().hide();
                        $("#spouseSex").parent().parent().show();
                        $("#divorceSex").parent().parent().hide();
                        $("#spousevalidateBegin").parent().parent().show();
                        $("#spouseCertId").parent().parent().show();
                        $("#divorceCertId").parent().parent().hide();
                        $("#spouseSignOrg").parent().parent().show();
                        $("#spouseidBackPhotoUrl").parent().show();
                        $("#deathCertPhotoUrl").parent().hide();
                        $("#deathCertPhotoUrl1").parent().hide();
                        $("#marryStatus").val(data.data.marryStatus==""?"0":data.data.marryStatus);
                        $("#marryInfoId").val(data.data.marryInfoId);
                        $("#marryDate").val(data.data.marryDate=""?"":data.data.marryDate.split(" ")[0]);
                        $("#spouseName").val(data.data.spouseName);
                        $("#spousevalidateEnd").val(data.data.spousevalidateEnd=""?"":data.data.spousevalidateEnd.split(" ")[0]);
                        $("#spouseidFrontPhotoUrlmImg").attr('src',data.data.spouseidFrontPhotoUrl==""?"":idPicUrls+data.data.spouseidFrontPhotoUrl);
                        $("#spouseidFrontPhotoUrl").val(data.data.spouseidFrontPhotoUrl==""?"":idPicUrls+data.data.spouseidFrontPhotoUrl);
                        $("#marryPhotoUrlmImg").attr('src',data.data.marryPhotoUrl==""?"":idPicUrls+data.data.marryPhotoUrl);
                        $("#marryPhotoUrl").val(data.data.marryPhotoUrl==""?"":idPicUrls+data.data.marryPhotoUrl);
                        $("#spouseSex").val(data.data.spouseSex==""?"0":data.data.spouseSex);
                        $("#spouseCertId").val(data.data.spouseCertId);
                        $("#spouseSignOrg").val(data.data.spouseSignOrg);
                        $("#spouseidBackPhotoUrlmImg").attr('src',data.data.spouseidBackPhotoUrl==""?"":idPicUrls+data.data.spouseidBackPhotoUrl);
                        $("#spouseidBackPhotoUrl").val(data.data.spouseidBackPhotoUrl==""?"":idPicUrls+data.data.spouseidBackPhotoUrl);
                        $("#spousevalidateBegin").val(data.data.spousevalidateBegin=""?"":data.data.spousevalidateBegin.split(" ")[0]);
                        setImgPx();
                    }else if(data.data.marryStatus==1){//未婚，所有的div都需要隐藏起来
                    	 $("#carPhotoBefore").hide();
                         $("#carPhotoBehand").hide();
                         $("#divorcePhoto").hide();
                         $("#marryPhoto").hide();
                         $("#deathCertPhoto").hide();
                        $("#deathCertPhotoUrlmImg").parent().hide();
                        $("#divorcePhotoUrlmImg").parent().hide();
                        $("#spouseidBackPhotoUrlmImg").parent().hide();
                        $("#marryPhotoUrlmImg").parent().hide();
                        $("#spouseidFrontPhotoUrlmImg").parent().hide();
                        $("#marryDate").parent().parent().hide();
                        $("#divorceDate").parent().parent().hide();
                        $("#spouseName").parent().parent().hide();
                        $("#divorceName").parent().parent().hide();
                        $("#spousevalidateEnd").parent().parent().hide();
                        $("#spouseidFrontPhotoUrl").parent().hide();
                        $("#spouseidFrontPhotoUrl1").parent().hide();
                        $("#marryPhotoUrl").parent().hide();
                        $("#divorcePhotoUrl").parent().hide();
                        $("#marryPhotoUrl1").parent().hide();
                        $("#divorcePhotoUrl1").parent().hide();
                        $("#spouseSex").parent().parent().hide();
                        $("#divorceSex").parent().parent().hide();
                        $("#spousevalidateBegin").parent().parent().hide();
                        $("#spouseCertId").parent().parent().hide();
                        $("#divorceCertId").parent().parent().hide();
                        $("#spouseSignOrg").parent().parent().hide();
                        $("#spouseidBackPhotoUrl").parent().hide();
                        $("#deathCertPhotoUrl").parent().hide();
                        $("#spouseidBackPhotoUrl1").parent().hide();
                        $("#deathCertPhotoUrl1").parent().hide();
                        $("#marryInfoId").val(data.data.marryInfoId);
                        $("#marryStatus").val(data.data.marryStatus);
                        setImgPx();
                    }else if(data.data.marryStatus==3){//离异，显示离异信息隐藏已婚再婚信息
                    	 $("#carPhotoBefore").hide();
                         $("#carPhotoBehand").hide();
                         $("#marryPhoto").hide();
                         $("#deathCertPhoto").hide();
                        $("#deathCertPhotoUrlmImg").parent().hide();
                        $("#divorcePhotoUrlmImg").parent().show();
                        $("#spouseidBackPhotoUrlmImg").parent().hide();
                        $("#marryPhotoUrlmImg").parent().hide();
                        $("#spouseidFrontPhotoUrlmImg").parent().hide();
                        $("#marryDate").parent().parent().hide();
                        $("#divorceDate").parent().parent().show();
                        $("#spouseName").parent().parent().hide();
                        $("#divorceName").parent().parent().show();
                        $("#spousevalidateEnd").parent().parent().hide();
                        $("#spouseidFrontPhotoUrl").parent().hide();
                        $("#marryPhotoUrl").parent().hide();
                        $("#spouseidFrontPhotoUrl1").parent().hide();
                        $("#marryPhotoUrl1").parent().hide();
                        $("#divorcePhotoUrl").parent().show();
                        $("#spouseSex").parent().parent().hide();
                        $("#divorceSex").parent().parent().show();
                        $("#spousevalidateBegin").parent().parent().hide();
                        $("#spouseCertId").parent().parent().hide();
                        $("#divorceCertId").parent().parent().show();
                        $("#spouseSignOrg").parent().parent().hide();
                        $("#spouseidBackPhotoUrl").parent().hide();
                        $("#deathCertPhotoUrl").parent().hide();
                        $("#spouseidBackPhotoUrl1").parent().hide();
                        $("#deathCertPhotoUrl1").parent().hide();
                        $("#marryInfoId").val(data.data.marryInfoId);
                        $("#marryStatus").val(data.data.marryStatus);
                        $("#divorceDate").val(data.data.divorceDate=""?"":data.data.divorceDate.split(" ")[0]);
                        $("#divorceName").val(data.data.divorceName);
                        $("#divorcePhotoUrlmImg").attr('src',data.data.divorcePhotoUrl==""?"":idPicUrls+data.data.divorcePhotoUrl);
                        $("#divorcePhotoUrl").val(data.data.divorcePhotoUrl==""?"":idPicUrls+data.data.divorcePhotoUrl);
                        $("#divorceSex").val(data.data.divorceSex);
                        $("#divorceCertId").val(data.data.divorceCertId);
                        setImgPx();
                    }else if(data.data.marryStatus==4){//丧偶，显示丧偶信息隐藏已婚再婚离异信息
                    	 $("#carPhotoBefore").hide();
                         $("#carPhotoBehand").hide();
                         $("#marryPhoto").hide();
                         $("#divorcePhoto").hide();
                         $("#deathCertPhoto").show();
                        $("#deathCertPhotoUrlmImg").parent().show();
                        $("#divorcePhotoUrlmImg").parent().hide();
                        $("#spouseidBackPhotoUrlmImg").parent().hide();
                        $("#marryPhotoUrlmImg").parent().hide();
                        $("#spouseidFrontPhotoUrlmImg").parent().hide();
                        $("#marryDate").parent().parent().show();
                        $("#divorceDate").parent().parent().hide();
                        $("#spouseName").parent().parent().show();
                        $("#divorceName").parent().parent().hide();
                        $("#spousevalidateEnd").parent().parent().hide();
                        $("#spouseidFrontPhotoUrl").parent().hide();
                        $("#marryPhotoUrl").parent().hide();
                        $("#divorcePhotoUrl").parent().hide();
                        $("#spouseSex").parent().parent().show();
                        $("#divorceSex").parent().parent().hide();
                        $("#spousevalidateBegin").parent().parent().hide();
                        $("#spouseCertId").parent().parent().show();
                        $("#divorceCertId").parent().parent().hide();
                        $("#spouseSignOrg").parent().parent().hide();
                        $("#spouseidBackPhotoUrl").parent().hide();
                        $("#deathCertPhotoUrl").parent().show();
                        $("#marryInfoId").val(data.data.marryInfoId);
                        $("#marryStatus").val(data.data.marryStatus);
                        $("#marryDate").val(data.data.marryDate=""?"":data.data.marryDate.split(" ")[0]);
                        $("#spouseName").val(data.data.spouseName);
                        $("#spouseSex").val(data.data.spouseSex);
                        $("#spouseCertId").val(data.data.spouseCertId);
                        $("#deathCertPhotoUrlmImg").attr('src',data.data.deathCertPhotoUrl==""?"":idPicUrls+data.data.deathCertPhotoUrl);
                        $("#deathCertPhotoUrl").val(data.data.deathCertPhotoUrl==""?"":idPicUrls+data.data.deathCertPhotoUrl);
                        setImgPx();
                    }
                }
            },

        });
    }
}

//设置回显图片的位置
function setImgPx() {
    var imgList = $("img");
    for(var i=0;i<imgList.length;i++){
        if(imgList.eq(i).attr("src")!=null && imgList.eq(i).attr("src")!=''&&imgList.eq(i).attr("src")!=undefined){
            imgList.eq(i).attr("style","width:100%;height:75px;background-color: #cccccc47");
        }
    }

}
/**
 * 点击申请人工作信息回显页面
 */
function custWorkLiClick(){
    if($("#applyId").val()!=null&&$("#applyId").val()!=undefined){
        $.ajax({
            type: "POST",
            url: '/custWorkInfo/findCustWorkInfoByApplyId',
            dataType: 'json',
            data:{ApplyId:$("#applyId").val()},
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                    if(data.data1.marryStatus==0||data.data1.marryStatus==2){//已婚或再婚
                        $("#spouseIncomeType1").parent().parent().show();
                        $("#spouseCompanyName").parent().parent().show();
                        $("#spouseCompanyType").parent().parent().show();
                        $("#spouseCompanyAddress").parent().parent().show();
                        $("#spouseCompanyTel").parent().parent().show();
                        $("#spouseDepartment").parent().parent().show();
                        $("#spouseJob").parent().parent().show();
                        $("#spouseWorkAge").parent().parent().show();
                        $("#spouseMonthIncome").parent().parent().show();
                        obj=document.getElementsByName("incomeType");
                        for(k in obj){
                            if(parseInt(parseInt(data.data.incomeType)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                                obj[k].checked=true;
                            }
                        }
                        $("#custWorkInfoId").val(data.data.id);
                       $("#monthIncome").val(data.data.monthIncome==undefined?undefined:data.data.monthIncome/10000);
                       // $("#monthIncome").val(data.data.monthIncome);
                        $("#companyName").val(data.data.companyName);
                        $("#companyType").val(data.data.companyType==undefined?"0":data.data.companyType);
                        $("#companyAddress").val(data.data.companyAddress);
                        $("#companyTel").val(data.data.companyTel);
                        $("#department").val(data.data.department);
                        $("#job").val(data.data.job);
                        $("#workAge").val(data.data.workAge);
                        obj=document.getElementsByName("spouseIncomeType");
                        for(k in obj){
                            if(parseInt(parseInt(data.data.spouseIncomeType)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                                obj[k].checked=true;
                            }
                        }
                        $("#spouseCompanyName").val(data.data.spouseCompanyName);
                        $("#spouseCompanyType").val(data.data.spouseCompanyType==undefined?"0":data.data.spouseCompanyType);
                        $("#spouseCompanyAddress").val(data.data.spouseCompanyAddress);
                        $("#spouseCompanyTel").val(data.data.spouseCompanyTel);
                        $("#spouseDepartment").val(data.data.spouseDepartment);
                        $("#spouseJob").val(data.data.spouseJob);
                        $("#spouseWorkAge").val(data.data.spouseWorkAge);
                       	$("#spouseMonthIncome").val(data.data.spouseMonthIncome==undefined?undefined:data.data.spouseMonthIncome/10000);
                       // $("#spouseMonthIncome").val(data.data.spouseMonthIncome);
                       // $("#spouseMonthIncome").val(data.data.spouseMonthIncome);
                    }else{//配偶信息都需要隐藏起来
                        $("#spouseIncomeType1").parent().parent().hide();
                        $("#spouseCompanyName").parent().parent().hide();
                        $("#spouseCompanyType").parent().parent().hide();
                        $("#spouseCompanyAddress").parent().parent().hide();
                        $("#spouseCompanyTel").parent().parent().hide();
                        $("#spouseDepartment").parent().parent().hide();
                        $("#spouseJob").parent().parent().hide();
                        $("#spouseWorkAge").parent().parent().hide();
                        $("#spouseMonthIncome").parent().parent().hide();
                        obj=document.getElementsByName("incomeType");
                        for(k in obj){
                            if(parseInt(parseInt(data.data.incomeType)&parseInt(obj[k].value))==parseInt(obj[k].value)){
                                obj[k].checked=true;
                            }
                        }
                        $("#custWorkInfoId").val(data.data.id);
                        $("#monthIncome").val(data.data.monthIncome==undefined?undefined:data.data.monthIncome/10000);
                       // $("#monthIncome").val(data.data.monthIncome);
                        $("#companyName").val(data.data.companyName);
                        $("#companyType").val(data.data.companyType);
                        $("#companyAddress").val(data.data.companyAddress);
                        $("#companyTel").val(data.data.companyTel);
                        $("#department").val(data.data.department);
                        $("#job").val(data.data.job);
                        $("#workAge").val(data.data.workAge);
                    }
                }
                setImgPx();
            },

        });
    }
}

//客户信息详细页面返回按钮
function custDetailBack() {
	window.location.href='/customerInfo/getPage';
}

<!--身份认证回显  begin -->
//身份证认证信息
function  searchIdAuth() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/idAuthInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#sfzList").html("");
            if(status=='0') {
                var html = "<tr align='center'>"
                    + "<td>" +(data.data.isIdAuth==0?"否":"是") + "</td>"
                    //+ "<td>" + data.data.gonganPhotolId + "</td>"
                    + "<td> <img src='" + data.data.idFrontPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td> <img src='" + data.data.idBackPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td><img src='" + data.data.holdIdentifyPhoto + "' style='width:80px;height:40px;/>' </td>"
                    + "<td>" +((data.data.authTime==null||data.data.authTime==undefined)?"":data.data.authTime.split(" ")[0])+ "</td>"
                    + "<td>" + data.data.userName + "</td>"
                    + "<td>" + data.data.idNumber + "</td>"
                    + "<td>" + data.data.nation + "</td>"
                    + "<td>" + data.data.address + "</td>"
                    + "<td>" + data.data.signOrgaization + "</td>"
                    + "<td>" + data.data.validityPeriod + "</td>"
                    + "<td>" + data.data.remark + "</td></tr>";
                $("#sfzList").append(html);
                searchLiveness();
            }

        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}
//活体认证
function  searchLiveness() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/livenessAuthInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#livenessList").html("");
            if(status=='0') {
                var html = "<tr align='center'>"
                    + "<td>" + (data.data.isLivenessAuth==0?"否":"是") + "</td>"
                    + "<td>" +((data.data.livenessAuthTime==null||data.data.livenessAuthTime==undefined)?"":data.data.livenessAuthTime.split(" ")[0])+ "</td>"
                    + "<td> <img src='" + data.data.livenessBlinkPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td> <img src='" + data.data.livenessNodPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td> <img src='" + data.data.livenessMouthPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td> <img src='" + data.data.livenessYawPhotoUrl + "' style='width:80px;height:40px;/>' </td>"
                    + "<td>" + data.data.remark + "</td></tr>";
                $("#livenessList").append(html);
            }

        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

<!--身份认证回显  end -->

<!--运营商模块  begin =================-->

//获取数据
function getTelecomData() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    var userName = $("#userName").val();
    var idNum =$("#idNum").val();
    var mobile = $("#mobile").val();

    $.ajax({
        type: "POST",
        url: '/telecomAuthInfo/getTelecomData',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType,
            'userName':userName,
            'idNum':idNum,
            'mobile':mobile
        },
        success: function(data) {
            var status = data.status;
            if(status==0){//状态为0 直接查询信息，不需要调用接口
                $("#telecomId").val(data.telecomAuthId);
                searchTelecomBaseInfo();
                searchTelecomAuthInfo();
                searchCallRiskInfo();
                searchFriendCircle();
                searchRoamInfo(3);
                searchCallDetail(3);
                $("#showControl").show();

            }else{
                Feng.info(data.errMsg);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}



//运营商信息回显
function  searchTelecomBaseInfo() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/telecomBasicInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#telecomBaseInfo").html("");
            if(status=='0'){
                var html="<tr align='center'>"
                    +"<td>"+data.data.mobile+"</td>"
                    +"<td>"+(data.data.auditStatus==3?"认证完成":"")+"</td>"
                    +"<td>"+data.data.auditTime+"</td></tr>";
                $("#telecomBaseInfo").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

//运营商认证信息回显
function  searchTelecomAuthInfo() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/telecomAuthInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#telecomAuthInfo").html("");
            if(status=='0'){
                var html="<tr align='center'>"
                    +"<td>"+(data.data.isRealAuth==0?"否":"是")+"</td>"
                    +"<td>"+data.data.nativePlace+"</td>"
                    +"<td>"+data.data.inTime+"</td>"
                    +"<td>"+data.data.active3m+"</td>"
                    +"<td>"+data.data.active6m+"</td></tr>";
                $("#telecomAuthInfo").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}


//运营商风险分析回显
function  searchCallRiskInfo() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/telecomCallRiskAnalysis/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#telecomCallRisk").html("");
            if(status=='0'){
                var html="";
                $.each(data.data,function(i){
                    html+="<tr align='center'>"
                        +"<td>"+data.data[i].analysisItem+"</td>"
                        +"<td>"+data.data[i].analysisDesc+"</td>"
                        +"<td>"+data.data[i].callCnt1m+"</td>"
                        +"<td>"+data.data[i].callCnt3m+"</td>"
                        +"<td>"+data.data[i].callCnt6m+"</td>"
                        +"<td>"+data.data[i].avgCallCnt3m+"</td>"
                        +"<td>"+data.data[i].avgCallCnt6m+"</td>"
                        +"<td>"+data.data[i].callTime1m+"</td>"
                        +"<td>"+data.data[i].callTime3m+"</td>"
                        +"<td>"+data.data[i].callTime6m+"</td>"
                        +"<td>"+data.data[i].avgCallTime3m+"</td>"
                        +"<td>"+data.data[i].avgCallTime6m+"</td></tr>";
                });

                $("#telecomCallRisk").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

//通讯录朋友圈TOP项回显
function  searchFriendCircle() {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/telecomFriendCircle/selectByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            $("#telecomFriendCircle").html("");
            if(status=='0'){
                var html="";
                $.each(data.data,function(i){
                    html+="<tr align='center'>"
                        +"<td>"+data.data[i].peerNumber+"</td>"
                        +"<td>"+data.data[i].peerNumLoc+"</td>"
                        +"<td>"+data.data[i].groupName+"</td>"
                        +"<td>"+data.data[i].companyName+"</td>"
                        +"<td>"+data.data[i].callCnt+"</td>"
                        +"<td>"+data.data[i].callTime+"</td>"
                        +"<td>"+data.data[i].dialCnt+"</td>"
                        +"<td>"+data.data[i].dialedCnt+"</td>"
                        +"<td>"+data.data[i].dialTime+"</td>"
                        +"<td>"+data.data[i].dialedTime+"</td>"
                        +"<td>"+(data.data[i].keyTop=="peer_num_top3_3m"?"近3月top3":"近6月top3")+"</td></tr>";
                });

                $("#telecomFriendCircle").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}

//运营商漫游信息回显
function  searchRoamInfo(o) {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    var pageNum = null;
    if(o==-1){//前一页
        pageNum = $("#prePage").val();
        if(pageNum==0){
            Feng.info("已经是第一页了哦！");
            return ;
        }
    }else  if(o==1){//后一页
        pageNum = $("#nextPage").val();
        if(pageNum==0){
            Feng.info("已经是最后一页了哦！");
            return ;
        }
    }else  if(o==0){//首页
        pageNum = 1;
    }else  if(o==2){//尾页
        pageNum = $("#pages").text();
    }

    $.ajax({
        type: "POST",
        url: '/telecomRoamInfo/pageQuery',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType,
            'pageNum':pageNum
        },
        success: function(data) {
            var status = data.status;
            $("#telecomRoamInfo").html("");
            if(status=='0'){
                var html="";
                $.each(data.data.list,function(i){
                    html+="<tr align='center'>"
                        +"<td>"+(i+1)+"</td>"
                        +"<td>"+data.data.list[i].location+"</td>"
                        +"<td>"+data.data.list[i].roamDayCnt3m+"</td>"
                        +"<td>"+data.data.list[i].roamDayCnt6m+"</td>"
                        +"<td>"+data.data.list[i].continueRoamCnt3m+"</td>"
                        +"<td>"+data.data.list[i].continueRoamCnt6m+"</td>"
                        +"<td>"+data.data.list[i].maxRoamDayCnt3m+"</td>"
                        +"<td>"+data.data.list[i].maxRoamDayCnt6m+"</td></tr>";
                });
                $("#total").text(data.data.total);
                $("#pages").text(data.data.pages);
                $("#pageNum").text(data.data.pageNum);
                $("#prePage").val(data.data.prePage);
                $("#nextPage").val(data.data.nextPage);


                $("#telecomRoamInfo").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}


//通话记录详细信息回显
function  searchCallDetail(o) {
    var applyId = $("#applyId").val();
    var pamType = $("#pamType").val();
    var pageNum = null;
    if(o==-1){//前一页
        pageNum = $("#callPrePage").val();
        if(pageNum==0){
            Feng.info("已经是第一页了哦！");
            return ;
        }
    }else  if(o==1){//后一页
        pageNum = $("#callNextPage").val();
        if(pageNum==0){
            Feng.info("已经是最后一页了哦！");
            return ;
        }
    }else  if(o==0){//首页
        pageNum = 1;
    }else  if(o==2){//尾页
        pageNum = $("#callPages").text();
    }

    $.ajax({
        type: "POST",
        url: '/telecomCallContactDetail/pageQuery',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType,
            'pageNum':pageNum
        },
        success: function(data) {
            var status = data.status;
            $("#telecomCallDetail").html("");
            if(status=='0'){
                var html="";
                $.each(data.data.list,function(i){
                    html+="<tr align='center'>"
                        +"<td>"+(i+1)+"</td>"
                        +"<td>"+data.data.list[i].city+"</td>"
                        +"<td>"+data.data.list[i].peerNum+"</td>"
                        +"<td>"+(data.data.list[i].isEmergency==0?"否":"是")+"</td>"
                        +"<td>"+data.data.list[i].pRelation+"</td>"
                        +"<td>"+data.data.list[i].groupName+"</td>"
                        +"<td>"+data.data.list[i].companyName+"</td>"
                        +"<td>"+data.data.list[i].callCnt1w+"</td>"
                        +"<td>"+data.data.list[i].callCnt1m+"</td>"
                        +"<td>"+data.data.list[i].callCnt3m+"</td>"
                        +"<td>"+data.data.list[i].callCnt6m+"</td>"
                        +"<td>"+data.data.list[i].callTime3m+"</td>"
                        +"<td>"+data.data.list[i].callTime6m+"</td>"
                        +"<td><a href='javascript:showCallDetail("+data.data.list[i].id+")'>查看详细</a></td></tr>";
                });
                $("#callTotal").text(data.data.total);
                $("#callPages").text(data.data.pages);
                $("#callPageNum").text(data.data.pageNum);
                $("#callPrePage").val(data.data.prePage);
                $("#callNextPage").val(data.data.nextPage);


                $("#telecomCallDetail").append(html);
            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}
//显示通话记录详细信息
function showCallDetail(id) {

    var index = layer.open({
        type: 2,
        title: '通话记录详细信息',
        area: ['1236px', '750px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + "/telecomCallContactDetail/showCallDetail?id="+id
    });
    this.layerIndex = index;

}
//点击tab页的运营商认证
function  searchTeleComAuth() {
    var applyId =$("#applyId").val();
    var pamType =$("#pamType").val();
    $.ajax({
        type: "POST",
        url: '/telecomAuthInfo/findByApplyIdAndType',
        dataType: 'json',
        data: {
            'applyId':applyId,
            'type':pamType
        },
        success: function(data) {
            var status = data.status;
            if(status==0){
                $("#telecomId").val(data.data.id);
                $("#billDetailUrl").val(data.data.billDetailUrl);
                $("#billDetailUrlmImg").attr("src",idPicUrls+data.data.billDetailUrl);
                $("#reportUrl").val(data.data.reportUrl);
                $("#reportUrlmImg").attr("src",idPicUrls+data.data.reportUrl);
                $("#compatibleReportUrl").val(data.data.compatibleReportUrl);
                $("#compatibleReportUrlmImg").attr("src",idPicUrls+data.data.compatibleReportUrl);
                setImgPx();

                searchTelecomBaseInfo();
                searchTelecomAuthInfo();
                searchCallRiskInfo();
                searchFriendCircle();
                searchRoamInfo(3);
                searchCallDetail(3);
                $("#showControl").show();

            }
        },
        error: function() {
            Feng.info("查询异常！");
        }
    });
}
<!--运营商模块  end===================================-->
