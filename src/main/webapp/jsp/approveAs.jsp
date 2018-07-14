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
 <script>

layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;	  
});	  

function func1() {
	layer.alert('已同意!', {
        icon: 1,
        skin: 'layer-ext-moon' 
    });
}
function func2() {
	layer.alert('已取消!', {
        icon: 1,
        skin: 'layer-ext-moon' 
    });
}
</script>           



<form class="layui-form"  id="chooseForm" action="searchAsManager.action" method="post">
 

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>基本信息</legend>
</fieldset>
    <div class="layui-inline">
      <label class="layui-form-label">社团编号</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" id="as_id" name="as_id" placeholder="社团编号">
      </div>
    </div>
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">点击查找</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
      
</form> 
 <table class="layui-table">
  <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>社团编号</th>
      <th>社团名称</th>
      <th>社团类型</th>
      <th>负责人姓名</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody>
  <form action = submitASRegister.action method = "post">

<c:forEach var = "asList" items = "${asList}">

<tr>
    <td>${asList.id }</td>
    <td>${asList.asname }</td>
    <td>${asList.type }</td>
    <td>${asList.name}</td>
      <td><button class="layui-btn layui-btn-normal layui-btn-mini news_del" id="approve" name="approve"  type="submit"  value="${asList.id}"><i class="layui-icon">&#xe605;</i> 同意</button>
      <button class="layui-btn layui-btn-danger" id="refuse" name="refuse"  type="submit"  value="${asList.id}"><i class="layui-icon">&#x1006;</i> 拒绝</button></td>  
</tr>
</c:forEach>
</form>
  </tbody>
</table>   

 

          



          
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
<% String flagapprove = (String)request.getAttribute("flagapprove");
if(flagapprove!=null&&flagapprove.length()>0&&flagapprove.equals("1"))
	
	{%>
 <script type="text/javascript">
                window.onload=function(){
                	func1(); }
 </script>
<%  
request.setAttribute("flagapprove",null);
	}
%>
<% String flagrefuse = (String)request.getAttribute("flagrefuse");
if(flagrefuse!=null&&flagrefuse.length()>0&&flagrefuse.equals("1"))
	
	{%>
 <script type="text/javascript">
                window.onload=function(){
                	func2(); }
 </script>
<%  
request.setAttribute("flagrefuse",null);
	}
%>
</body>
</html>