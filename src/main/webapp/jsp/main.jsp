<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- /.website title -->
<title>Backyard Landing Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<!-- CSS Files -->
<link href="test/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="test/css/font-awesome.min.css" rel="stylesheet">
<link href="test/fonts/icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
<link href="test/css/animate.css" rel="stylesheet" media="screen">
<link href="test/css/owl.theme.css" rel="stylesheet">
<link href="test/css/owl.carousel.css" rel="stylesheet">

<!-- Colors -->
<link href="test/css/css-index.css" rel="stylesheet" media="screen">
<!-- <link href="css/css-index-green.css" rel="stylesheet" media="screen"> -->
<!-- <link href="css/css-index-purple.css" rel="stylesheet" media="screen"> -->
<!-- <link href="css/css-index-red.css" rel="stylesheet" media="screen"> -->
<!-- <link href="css/css-index-orange.css" rel="stylesheet" media="screen"> -->
<!-- <link href="css/css-index-yellow.css" rel="stylesheet" media="screen"> -->

<!-- Google Fonts -->
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic" />
<link rel="stylesheet" href="layui/css/layui.css"  media="all">
</head>
  
<body data-spy="scroll" data-target="#navbar-scroll">
<script>

layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;	  
});	  

function func1() {
	layer.alert('登录失败!', {
        icon: 2,
        skin: 'layer-ext-moon' 
    });
}
</script> 
<!-- /.preloader -->
<div id="preloader"></div>
<div id="top"></div>

<!-- /.parallax full screen background image -->
<div class="fullscreen landing parallax" style="background-image:url('test/images/bg.jpg');margin-top:150px;" data-img-width="2000" data-img-height="1333" data-diff="100">
	
	<div class="overlay">
		<div class="container">
			<div class="row">
				<div class="col-md-7">
				
					<!-- /.logo -->
					<div class="logo wow fadeInDown"> <a href=""><img src="test/images/logo.png" alt="logo"></a></div>

					<!-- /.main title -->
						<h1 class="wow fadeInLeft">
						社团管理系统
						</h1>

					<!-- /.header paragraph -->
					<div class="landing-text wow fadeInUp">
						<p>社团管理系统旨在为管理员提高方便快捷高效的对校园社团的管理方式</p>
					</div>				  

	
		  

				</div> 
				
				<!-- /.signup form -->
				<div class="col-md-5">
				
					<div class="signup-header wow fadeInUp">
						<h3 class="form-title text-center">管理员登录</h3>
						<form class="form-header" action="managersignin.action" role="form" method="POST" id="#">
						<input type="hidden" name="u" value="503bdae81fde8612ff4944435">
						<input type="hidden" name="id" value="bfdba52708">
							<div class="form-group">
								<input class="form-control input-lg" name="username" id="username" type="text" <c:if test="${signflag!=null}"> value="${username }"</c:if>placeholder="请输入账号" required>
							</div>
							<div class="form-group">
								<input class="form-control input-lg" name="password" id="password" type="text" <c:if test="${signflag!=null}"> value="${password }"</c:if> placeholder="请输入密码" required>
							</div>
							<div class="form-group last">
								<input type="submit" class="btn btn-warning btn-block btn-lg" value="登录">
							</div>
							<p class="privacy text-center">欢迎使用社团管理系统.</p>
						</form>
					</div>				
				
				</div>
			</div>
		</div> 
	</div> 
</div>
 
	
	<!-- /.javascript files -->
    <script src="test/js/jquery.js"></script>
    <script src="test/js/bootstrap.min.js"></script>
    <script src="test/js/custom.js"></script>
    <script src="test/js/jquery.sticky.js"></script>
	<script src="test/js/wow.min.js"></script>
	<script src="test/js/owl.carousel.min.js"></script>
	<script>
		new WOW().init();
	</script>
	<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        var day = laydate.render({
            elem: '#day'
            ,format: 'yyyy/MM/dd'
            ,min: 0
        });
    });

</script>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
<% String signflag = (String)request.getAttribute("signflag");
if(signflag!=null&&signflag.length()>0&&signflag.equals("1"))
	
	{%>
 <script type="text/javascript">
                window.onload=function(){
                	func1(); }
 </script>
<%  
request.setAttribute("signflag",null);
	}
%>
  </body>
</html>