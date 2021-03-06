<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>大视界后台管理系统</title>
		<meta name="description" content="description">
		<meta name="author" content="Evgeniya">
		<meta name="keyword" content="keywords">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="/plugins/bootstrap/bootstrap.css" rel="stylesheet">
        <script src="/plugins/jquery/jquery.min.js"></script>
        <script src="/js/md5.js"></script>

		<style type="text/css">
			html{
				height: 100%;
				background: url(/img/devoops_getdata.jpg) no-repeat;
				background-size: cover;
			}
			body{
				background-color: transparent;
			}
			.box{
				font-family: "Microsoft Yahei";
				border: 1px solid #eee;
				background-color: #fff;
				padding: 2rem 3rem;
				margin-top: 8rem;
				border-radius: 1rem	;
			}
			.space{
				display: block;
				height: 2rem;
			}
		</style>
	</head>
	<body>
	<div class="container-fluid">
		<div id="page-login" class="row">
			<div class="col-xs-12 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
				<div class="box">
					<div class="box-content">
						<div class="text-center">
							<h3>大视界后台登陆</h3>
						</div>
						<div class="space"></div>
						<div class="form-group">
							<label for="username" class="sr-only">用户名</label>
							<div class="input-group">
								<span class="input-group-addon">用户名</span>
								<input type="text" class="form-control" id="username" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">密　码</label>
							<div class="input-group">
								<span class="input-group-addon">密　码</span>
								<input type="password" class="form-control" id="password" placeholder="">
							</div>
						</div>
						<div class="space"></div>
						<div class="row">
							<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
								<a class="btn btn-success btn-block" onclick="login();" >登陆</a>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
    function login(){
        var username = $("#username").val();
        var password = $("#password").val();
        if(username.replace(/\s/g, '') == ''){
            alert("用户名不能为空");
            return;
        }
        if(password.replace(/\s/g, '') == ''){
            alert("密码不能为空");
            return;
        }
        var passwordCode = hex_md5(password);
        $.ajax({
            cache: true,
            type:"POST",
            url:"/adminLogin.do",
            data:{"username":username, "password":passwordCode},
            async: false,
            success:function(_data){
                var data = $.parseJSON(_data);
//                alert(data);
                if(data.success){
                    window.location.href = "/main.do";
                }else{
                    var _case = {1:"用户名不能为空", 2:"密码不能为空", 3:"该用户不存在", 4:"密码不正确", 5:"登陆失败",6:"您的账号没有权限，请联系管理员"};
                    alert(_case[data.code])
                }
            }
        });
    }
</script>
</body>
</html>
