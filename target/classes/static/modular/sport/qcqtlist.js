$(document).ready(
    function () {
        searchPage(0);
    }
);




function  searchPage(o) {
    var prjType = $("#prjType").val();
    var prjId = $("#prjId").val();
    var qtId = $("#qtId").val();
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
        url: '/cdssqcqt/pageQuery',
        dataType: 'json',
        data: {
            'prjtype':prjType,
            'prjid':prjId,
            'qtid':qtId,
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
                        +"<a href='/cdssqcqt/showQcQtDetail?qcQtId="+data.data.list[i].id+"' title='"+data.data.list[i].jssb+"' target=\"_self\">"
                        +"<img class=\"cover-image\" src=\"/static/img/sport/1524206976.jpg\" alt='"+data.data.list[i].jssb+"'>"
                        +"</a></figure><h4 class=\"card-title m-0 p-x-10 font-size-16 text-xs-center\">"
                        +"<a href='/cdssqcqt/showQcQtDetail?qcQtId=="+data.data.list[i].id+"' title='"+data.data.list[i].jssb+"' class=\"block\" target=\"_self\">"+data.data.list[i].jssb+"</a>"
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



//跳转其他类器材添加页面
function showAddQtQc() {
    var prjId = $("#prjId").val();
    var prjType = $("#prjType").val();
    var qtId = $("#qtId").val();

    window.location.href="/cdssqcqt/showAddQcQt?prjId="+prjId+"&prjType="+prjType+"&qtId="+qtId;

}






















