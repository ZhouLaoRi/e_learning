/*$(function ()
{
    $('.change').click(function ()
    {
        //这里.signform 找的是class="signform"  很奇怪，按道理他是找change的div 有这个动作，也就是两个都这样
        //但是他 会复原。。。。
        $(".signform").animate({height: 'toggle', opacity: 'toggle'}, 'slow');
    });
})*/

function start() {
    /*$("#signform").toggle(1000);
    $("#registerform").toggle(1000);*/
    /*document.getElementById('signform').style.display="";
    document.getElementById('registerform').style.display="none";*/
    //$("#signform").toggle(1000);
    $("#signform").animate({height: 'toggle', opacity: 'toggle'}, 'slow');
}

function register() {
    /*document.getElementById('signform').style.display="none"
    document.getElementById('registerform').style.display=""*/
    $("#signform").animate({height: 'toggle', opacity: 'toggle'}, 'slow');
    $("#registerform").animate({height: 'toggle', opacity: 'toggle'}, 'slow');
}

function signclose() {
    document.getElementById('signform').style.display="none"
    document.getElementById('registerform').style.display="none"
}

function loading() {
    document.getElementById('registerloading').style.display=""

    setTimeout(function(){
        $.ajax({
            type: "POST",
            url: "/crud/user/userNameOnly",
            data: {
                userName : $("#registeruser").val()
            },
            success: function(strMsg){
                if(strMsg == false){
                    alert("用户名重复");
                }
            }
        });
        document.getElementById('registerloading').style.display="none"
    }, 1000);
}

//刷新验证码的
function refreshVerify(id) {
    var myData = new Date();
    var times = myData.getTime();
    //src 是路径 属性 src 的更改
    $("#verify").attr("src", "/crud/home/verifyCode/getVerifyCode?times=" + times);
}

function signUp() {

    // 用户名不能为空
    var username = document.registerForm.userName.value;
    alert(username);
    if(username == "" || username.length == 0){
        alert("用户名不能为空");
        return false;
    }
    // 密码不能为空，不能少于6位
    var password = document.registerForm.userPassword.value;
    if(password == "" || password.length < 6){
        alert("密码至少6位");
        return false;
    }
    // 确认密码和密码一致
    var repassword = document.registerForm.userRePassword.value;
    if(repassword == "" || repassword != password){
        alert("两次密码不一致");
        return false;
    }
    // 邮箱
    var email = document.registerForm.email.value;
    var reg= /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
    if(reg.test(email) == false){
        alert("邮箱格式不正确");
        return false;
    }
    // 手机号码为空
    var telephone = document.registerForm.telephone.value;
    if(telephone == "" || telephone.length == 0){
        alert("手机号码");
        return false;
    }
    $.ajax({
        async: false,    //设置为同步
        type: "POST",
        url: "/crud/user/signup",
        data: {
          userName : $("#registeruser").val(),
          userPassword : $("#registerpwd").val(),
          email : $("#email").val(),
          telephone : $("#telephone").val(),
          userLevel : $("#registerlevel").val(),
          userDirection : $("#registerdirection").val()
        },
        success: function(strMsg){
          alert(strMsg);
          start();
        }
    });
}

//只有ajax
function signUp2() {
    // 用户名不能为空
    var username = document.form1.userName.value;
    if(username == "" || username.length == 0){
        alert("用户名不能为空");
        return false;
    }
    // 密码不能为空，不能少于6位
    var password = document.form1.userPassword.value;
    if(password == "" || password.length < 6){
        alert("密码至少6位");
        return false;
    }
    // 确认密码和密码一致
    var repassword = document.form1.userRePassword.value;
    if(repassword == "" || repassword != password){
        alert("两次密码不一致");
        return false;
    }
    // 邮箱
    var email = document.form1.email.value;
    var reg= /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
    if(reg.test(email) == false){
        alert("邮箱格式不正确");
        return false;
    }
    // 手机号码为空
    var telephone = document.form1.telephone.value;
    if(telephone == "" || telephone.length == 0){
        alert("手机号码");
        return false;
    }
    // 可以提交了

    $.ajax({
        async: false,    //设置为同步
        type: "POST",
        url: "/crud/user/signup",
        data: {
            userName : $("#registeruser").val(),
            userPassword : $("#registerpwd").val(),
            email : $("#email").val(),
            telephone : $("#telephone").val(),
            userLevel : $("#registerlevel").val(),
            userDirection : $("#registerdirection").val()
        },
        success: function(strMsg){
            alert(strMsg);
            start();
        }
    });
}

// 完成注册校验
function run(){
    // 用户名不能为空
    var username = document.form1.userName.value;
    if(username == "" || username.length == 0){
        alert("用户名不能为空");
        return false;
    }
    // 密码不能为空，不能少于6位
    var password = document.form1.userPassword.value;
    if(password == "" || password.length < 6){
        alert("密码至少6位");
        return false;
    }
    // 确认密码和密码一致
    var repassword = document.form1.userRePassword.value;
    if(repassword == "" || repassword != password){
        alert("两次密码不一致");
        return false;
    }
    // 邮箱
    var email = document.form1.email.value;
    var reg= /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
    if(reg.test(email) == false){
        alert("邮箱格式不正确");
        return false;
    }
    // 手机号码为空
    var telephone = document.form1.telephone.value;
    if(telephone == "" || telephone.length == 0){
        alert("手机号码不能为空");
        return false;
    }
    // 可以提交了
    alert("注册成功");
}

// 完成校验
function loginrun(){
    // 用户名不能为空
    var username = document.loginForm.userName.value;
    if(username == "" || username.length == 0){
        alert("用户名不能为空");
        return false;
    }
    // 密码不能为空，不能少于6位
    var password = document.loginForm.userPassword.value;
    if(password == "" || password.length < 6){
        alert("密码至少6位");
        return false;
    }
    // 验证码不能为空
    var verifyCode = document.loginForm.verifyCode.value;
    if(verifyCode == "" || verifyCode.length == 0){
        alert("验证码不能为空");
        return false;
    }

    // 验证码是否一致
    //这个取的是 输入的值,所以我应该用  th:value 去赋值  得后台判断，前台这里获取的验证码不是最新的
    /*var trueVerifyCode = document.loginForm.trueVerifyCode.value;
    alert(trueVerifyCode);
    if(trueVerifyCode != verifyCode){
        alert("验证码不正确");
        return false;
    }*/
    // 可以提交了
}

