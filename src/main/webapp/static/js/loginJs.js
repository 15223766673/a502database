$(function ()
{
    //jqury入口函数
    var mytoken = localStorage.getItem('token');
    console.log(mytoken);
    if($.isEmptyObject(mytoken))
    {
        //跳转到登录页面
        window.location.href = '/A502database/login';
    }
    else{
        axios(
            {
                headers:{
                    'Content-Type': 'application/json',
                    'token': mytoken
                },
                method:'POST',
                url:'/A502database/getUserInfo',
                data:{

                }


            }).then(function (response) {
            console.log(response.data);
            if(response.data.status == 200){
                console.log("欢迎");
                console.log(response.data.rtnMap.userName);
            }
            else {
                localStorage.removeItem("token");
                console.log(response.data.status);
                //跳转到登录页面
                window.location.href = '/A502database/login';
            }
        })

    }
})