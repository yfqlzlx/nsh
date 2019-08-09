/**
 * 保证每个页面访问前都已经登录
 */
(function () {
    let user = layui.sessionData('userStore').user;
    if(!user){
        layer.alert("您还没登录，请先登录",function () {
            window.location.href = '/login/login.html'
        });
    }
})();


function getUser(){
    let user = layui.sessionData('userStore').user;
    return user.id;
}

function removeUser() {
    layui.sessionData('userStore',{
        key: 'user',
        remove: true
    })
}