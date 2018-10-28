$(document).ready(
    function () {
        var prjType = $("#prjType").val();
        if(prjType==1){
            $("#showPrjName").text("农民体育健身工程");
        }else if(prjType==2){
            $("#showPrjName").text("社区体育健身工程");
        }else{
            $("#showPrjName").text("农民体育健身工程");
        }
        searchPage(0);
    }
);




function  searchPage(o) {
    var prjType = $("#prjType").val();
    var deptId = $("#deptId").val();
    var deptSubId =$("#deptSubId").val();
    var pageNum = null;
    if(prjType==null||prjType==""||prjType==undefined){
        prjType=1;
    }
    if(o==-1){//前一页
        pageNum = $("#prePage").val();
        if(pageNum==0){
            alert("已经是第一页了哦！");
            return ;
        }
    }else  if(o==1){//后一页
        pageNum = $("#nextPage").val();
        if(pageNum==0){
            alert("已经是最后一页了哦！");
            return ;
        }
    }else  if(o==0){//首页
        pageNum = 1;
    }else  if(o==2){//尾页
        pageNum = $("#pages").text();
    }

    $.ajax({
        type: "POST",
        url: '/sprjbase/pageQuery',
        dataType: 'json',
        data: {
            'prjtype':prjType,
            'deptid':deptId,
            'deptsubid':deptSubId,
            'pageNum':pageNum
        },
        success: function(data) {
            var status = data.status;
            $("#met-grid").html("");
            if(status=='0'){
                var html ="";
                $.each(data.data.list,function(i){
                    html +="<li class=\"shown\" >"
                        +"<div class=\"card card-shadow\">"
                        +"<figure class=\"card-header cover\">"
                        +"<a href='/qiantai/showPrjDetail?prjId="+data.data.list[i].id+"' title='"+data.data.list[i].place+"' target=\"_self\">"
                        +"<img class=\"cover-image\" src=\"/static/img/sport/1524206976.jpg\" alt='"+data.data.list[i].place+"'></a></figure>"
                        +"<h4 class=\"card-title m-0 p-x-10 font-size-16 text-xs-center\">"
                        +"<a style='color:";
                        if(data.data.list[i].sfxj==0){
                            html+="red;'"
                        }else{
                            html+="black;'"
                        }
                        html+=" href='/qiantai/showPrjDetail?prjId="+data.data.list[i].id+"' title='"+data.data.list[i].place+"' class=\"block\" target=\"_self\">"+data.data.list[i].place+"</a>"
                        +"<p class=\"m-b-0 m-t-5 red-600\"></p></h4></div></li>";

                });
                // $("#total").text(data.data.total);
                $("#pages").text(data.data.pages);
                $("#pageNum").text(data.data.pageNum);
                $("#prePage").val(data.data.prePage);
                $("#nextPage").val(data.data.nextPage);

                $("#met-grid").append(html);

            }
        },
        error: function() {
            alert("保存数据异常！");

        }
    });
}

function showPrjByType(prjType) {
    $("#prjType").val(prjType);
    if(prjType==1){
        $("#showPrjName").text("农民体育健身工程");
    }else if(prjType==2){
        $("#showPrjName").text("社区体育健身工程");
    }else{
        $("#showPrjName").text("农民体育健身工程");
    }
    searchPage(0);
}
//跳转添加项目页面
function showAddPrj() {
    var deptId = $("#deptId").val();
    var deptSubId=$("#deptSubId").val();
    var prjType = $("#prjType").val();
    var deptSubName = $("#deptSubName").text();
    // if(prjType==null || prjType==""||prjType==undefined){
    //     prjType = 1;
    // }
    window.location.href="/qiantai/showAddPrj?prjType="+prjType+"&deptId="+deptId+"&deptSubId="+deptSubId+"&deptSubName="+deptSubName;
}

function showPrjDetail(id) {
    var showPrjName = $("#showPrjName").val();
    window.location.href="/qiantai/showPrjDetail?prjId="+id+"&showPrjName="+showPrjName;

}

//返回首页
function backIndex() {
    window.location.href="/qiantai/backIndex";
}

























