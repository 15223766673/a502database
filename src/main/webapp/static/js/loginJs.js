$(function ()
{
    //jqury入口函数
    var token = localStorage.getItem("token");
    if($.isEmptyObject(token))
    {
        //跳转到登录页面
        window.location.href = '/a502database/login';
    }
    else{
        //获取用户信息
    }
})