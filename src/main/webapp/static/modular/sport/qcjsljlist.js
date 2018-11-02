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
    var prjId = $("#prjId").val();
    var jsljId = $("#jsljId").val();
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
        url: '/cdssqcjslj/pageQuery',
        dataType: 'json',
        data: {
            'prjtype':prjType,
            'prjid':prjId,
            'jsljid':jsljId,
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
                        +"<h4 class=\"card-title m-0 p-x-10 font-size-16 text-xs-center\">"
                        +"<a href='/cdssqcjslj/showQcJsljDetail?qcJsljId="+data.data.list[i].id+"' title='"+data.data.list[i].jssb+"' class=\"block\" target=\"_self\">"+data.data.list[i].jssb+"</a>"
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



//跳转健身路径器材添加页面
function showAddJsljQc() {
    var prjId = $("#prjId").val();
    var prjType = $("#prjType").val();
    var jsljId = $("#jsljId").val();

    window.location.href="/cdssqcjslj/showAddQcJslj?prjId="+prjId+"&prjType="+prjType+"&jsljId="+jsljId;

}


//返回场地
function backJsljDetail() {
    var jsljId = $("#jsljId").val();
    window.location.href="/cdssjslj/showJsljDetail?jsljId="+jsljId;
}


















