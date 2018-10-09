/**
 * 新增客户
 */



/**
 *新增客户查询
 */
var leftAmount;
function search() {

    var name = $("#name").val();
    var regUserName = /^[\u4e00-\u9fa5]{0,11}$/;
    var idcard = $("#idcard").val();
//    var _idcard=idcard;
   
    if(name==""||name==null||name==undefined){

        Feng.info("请输入借款人姓名！");
        return false;
    }else if(!(regUserName.test(name))){
    	Feng.info("姓名只能为长度小于10的汉字！");
		return false;
    }
    
    
    //身份证校验-测试（18位数字）
//    var regCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
//    if(idcard==""||idcard==null||idcard==undefined){
//        Feng.info("请输入借款人身份证号码！");
//        return false;
//    }else if(!(regCard.test(idcard))){
//    	Feng.info("输入的身份证信息格式不正确");
//		return false;
//    }
    
    //身份证校验 - 实名(真实身份证)
    var regCard = /^[1-9]\d{5}((1[89]|20)\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}[\dx]$/i;
    var city = {
    	11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",
    	34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
    	51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",
    	82:"澳门",91:"国外"
    }
    if(idcard==""||idcard==null||idcard==undefined){
        Feng.info("请输入借款人身份证号码！");
        return false;
    }else if(!(regCard.test(idcard))){
    	Feng.info("输入的身份证信息格式不正确");
		return false;
    }else if(!city[idcard.substr(0,2)]){
    	alert(idcard)
    	Feng.info("地址编码错误");
		return false;
    }else{
    	if(idcard.length == 18){
//    		idcard = idcard.split('');
    		var factor = [7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2];
    		//校验位
    		var parity= [1,0,'X',9,8,7,6,5,4,3,2];
    		var sum = 0;
    		var ai = 0;
    		var wi = 0;
    		for(var i = 0;i<17;i++){
    			ai = idcard[i];
    			wi = factor[i];
    			sum += ai * wi;
    		}
    		var last = parity[sum%11];
    		if(parity[sum % 11] != idcard[17]){
    			Feng.info("校验位错误");
    			return false;
    		}
    	}
    }
    
    
    
    $('#custAddTable tbody').empty();
    $('#isEnsure').empty();
    $('#refuseMsg').empty();
    $('#hasRecord').empty();
    $('#leftAmount').empty();

    //提交信息
    $.ajax({
        type: "POST",
        url: '/cust/searchCustAddInfo',
        dataType: 'json',
        data: {name:name,idcard:idcard},
        success: function(data) {
        	
            var status=data.status;
            console.log(data);
            if(status=='0'){
                $('#isEnsure').val(data.data.applyFlag==false?'拒绝':'通过');
                $('#refuseMsg').val(data.data.refuseMsg);
                $('#hasRecord').val(data.data.list.length==0?'无':'有');
                $('#leftAmount').val(data.data.leftAmount.toFixed(2));
                leftAmount=data.data.leftAmount.toFixed(2);
                var trHtml = '';
                $.each(data.data.list, function (i, val) {
                    trHtml += '<tr>'
                        + '<td style="text-align: center; vertical-align: middle; ">' + val.applyId + '</td>'
                        + '<td style="text-align: center; vertical-align: middle; ">' + val.name + '</td>'
                        + '<td style="text-align: center; vertical-align: middle; ">' + val.certId + '</td>'
                        + '<td style="text-align: center; vertical-align: middle; ">' + val.relationship + '</td>'
                        + '<td style="text-align: center; vertical-align: middle; ">' + val.loanDate + '</td>'
                        + '<td style="text-align: center; vertical-align: middle; ">' + Number(val.loanAmount).toFixed(2) + '</td>'
                        + '<td style="text-align: center; vertical-align: middle; ">' + val.loanPreiod + '</td>'
                        + '<td style="text-align: center; vertical-align: middle; ">' + val.loanStatus + '</td>'
                        + '<td style="text-align: center; vertical-align: middle; ">' + val.overdueNum + '</td>'
                        + '</tr>'
                });
                $('#custAddTable tbody').append(trHtml);

            }else{
                Feng.error("新增客户查询失败!"+ data.msg);
            }

        },
        error: function(data) {
            Feng.info("新增客户查询异常！");

        }
    });

};


function addSub(name, idcard,leftAmount){
    $.ajax({
        type: "POST",
        url: '/cust/custAddInfo',
        dataType: 'json',
        data: {name:name,idcard:idcard,leftAmount:leftAmount},
        success: function(data) {

            var status = data.status;
            if(status=='0'){
                Feng.info("新增客户成功！");
                top.location.reload();
            }else{
                Feng.info("新增客户失败！"+data.msg);
            }
        },
        error: function(data) {
            Feng.info("新增客户查询异常！");

        }
    });
}

/**
 *新增客户
 */
function add() {
    var name = $("#name").val();
    var idcard = $("#idcard").val();
    var isEnsure = $('#isEnsure').val();

    if (name == "") {
        Feng.info("请输入借款人姓名！");
        return false;
    }
    if (idcard == "") {
        Feng.info("请输入借款人身份证号！");
        return false;
    }

    if (idcard.length != 18) {
        Feng.info("请输入正确身份证号");
        return false;
    }

    if(isEnsure == ""){
        Feng.info("请先查询该客户信息");
        return false;
    }

    if(isEnsure == "拒绝"){
    	Feng.info("新增客户失败");
       /* Feng.confirm("该客户已被系统拒绝，是否新增该客户?",function(){
            addSub(name, idcard,leftAmount);
        });*/
    }else{
        addSub(name, idcard,leftAmount);
    }
};



function SelectchannelName(){
    if($("#channelId").val()==null||$("#channelId").val()==undefined){
        //查询渠道名称信息
        $.ajax({
            type: "POST",
            url: '/channelInfo/findChannelId',
            dataType: 'json',
            data: {},
            success: function(data) {
                var status=data.status;
                if(status=='0'){
                    $.each(data.data, function (i, val) {

                        $('#channelId').append("<option value ="+data.data[i].id+">"+data.data[i].channelName+"</option>");
                    });
                }else{
                    Feng.error("查询渠道名称失败!"+ data.msg);
                }
            },
            error: function(data) {
                Feng.info("查询渠道名称异常！");
            }
        });
    }
}

$(document).ready(function () {
    SelectchannelName();
});
