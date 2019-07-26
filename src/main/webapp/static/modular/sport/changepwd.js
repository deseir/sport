//返回首页
function backIndex() {
    window.location.href="/qiantai/backIndex";
}

function changePwd() {
    var oldPwd = $("#oldPwd").val();
    var newPwd = $("#newPwd").val();
    var rePwd = $("#rePwd").val();
    $.ajax({
        type: "POST",
        url: '/mgr/changePwdQt',
        dataType: 'json',
        data: {
            'oldPwd':oldPwd,
            'newPwd':newPwd,
            'rePwd':rePwd
        },
        success: function(data) {

            if(data.status=='0'){
                alert("密码修改成功，请重新登录！");
                window.location.href='/qiantai/logout';
            }else{
                alert(data.msg);
            }
        },
        error: function() {
            alert("保存数据异常！");

        }
    });

}

