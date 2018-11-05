var idPicUrls = $("#idPicUrls").val();
$(document).ready(
    function () {
        searchPage(0);
    }
);

function  searchPage(o) {
    var deptId = $("#deptId").val();
    var pageNum = null;
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
        url: '/sqc/pageQuery',
        dataType: 'json',
        data: {
            'deptid':deptId,
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
                        +"<a href=\"javascript:showQcDetail("+data.data.list[i].id+")\" target=\"_self\">"
                        +"<img class=\"cover-image\" src=\""+idPicUrls+data.data.list[i].picUrl+"\" alt='"+data.data.list[i].jssb+"'>"
                        +"</a></figure><h4 class=\"card-title m-0 p-x-10 font-size-16 text-xs-center\">"
                        +"<a href=\"javascript:showQcDetail("+data.data.list[i].id+")\" class=\"block\" target=\"_self\">"+data.data.list[i].jssb+"</a>"
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

//跳转器材添加页面
function showAddQc() {
    var deptId = $("#deptId").val();
    var deptPid = $("#deptPid").val();
    window.location.href="/sqc/showAddQc?deptId="+deptId+"&deptPid="+deptPid;
}

//跳转器材详情页
function showQcDetail(qcId) {
    var deptPid = $("#deptPid").val();
    window.location.href="/sqc/showQcDetail?qcId="+qcId+"&deptPid="+deptPid;
}

//返回次级部门列表页
function showSubDepts() {
    var deptPid = $("#deptPid").val();
    window.location.href="/sdept/showSubDepts?deptId="+deptPid;
}





















