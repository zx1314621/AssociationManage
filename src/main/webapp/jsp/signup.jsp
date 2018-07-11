<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="layui/css/layui.css"  media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<form class="layui-form"  id="chooseForm" action="signup.action" method="post">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>社团注册</legend>
</fieldset>
  <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">账号</label>
      <div class="layui-input-inline">
        <input type="text" name="as_id" lay-verify="required" autocomplete="off" placeholder="请输入账号" class="layui-input" >
      </div>
    </div>
  </div>
  <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">密码</label>
      <div class="layui-input-inline">
        <input type="text" name="password" lay-verify="required" autocomplete="off" placeholder="请输入密码" class="layui-input" >
        
      </div>
    </div>
  </div>
   <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">社团名称</label>
      <div class="layui-input-inline">
        <input type="text" name="as_name" lay-verify="required" autocomplete="off" placeholder="请输入社团名称" class="layui-input" >
        
      </div>
    </div>
  </div>
  <div class="layui-form-item">
  <div class="layui-inline">
      <label class="layui-form-label">社团类别</label>
      <div class="layui-input-inline">
       <select id="as_type" name="as_type"  lay-search="">
          <option value="">社团类别</option>
          <option value="文化">文化</option>
          <option value="艺术">艺术</option>
          <option value="兴趣">兴趣</option>
          <option value="学术">学术</option>
        </select>
      </div>
    </div>
    </div>
   <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">负责人姓名</label>
      <div class="layui-input-inline">
        <input type="text" name="people_name" lay-verify="required" autocomplete="off" placeholder="请输入负责人姓名" class="layui-input" >
        
      </div>
    </div>
  </div>
  
 <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">注册</button>
      <a href="signin.jsp">登录</a>
    </div>
  </div>
</form> 
  


 

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
</body>
</html>