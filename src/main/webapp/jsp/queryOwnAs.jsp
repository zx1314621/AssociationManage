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
	layer.alert('修改成功!', {
        icon: 1,
        skin: 'layer-ext-moon' 
    });
}
</script>
 <%@page import="java.util.Date"%>
 <%@page import="java.text.SimpleDateFormat"%>  
  
 <%
 SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
 String date = df.format(new Date());
 %>
<form class="layui-form"  id="chooseForm" action="submitAsChange.action" method="post">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>社团详情</legend>
</fieldset>
  <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">社团编号</label>
      <div class="layui-input-inline">
        <input type="text" name="as_id" lay-verify="required" autocomplete="off" value="${changeAS.id}" class="layui-input" readonly>
      </div>
    </div>
  </div>
   <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">登录密码</label>
      <div class="layui-input-inline">
        <input type="text" name="password" lay-verify="required" autocomplete="off" value="${changeAS.password}" class="layui-input" >
      </div>
    </div>
  </div>
  <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">社团名</label>
      <div class="layui-input-inline">
        <input type="text" name="asname" lay-verify="required" autocomplete="off" value="${changeAS.asname}" class="layui-input" >
      </div>
    </div>
  </div>
    
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">社团类型</label>
      <div class="layui-input-inline">
       <select id="type" name="type"  lay-verify="required" lay-search="" >
      
      <c:if test="${flag == 1}">
       <option selected>文化</option>
       </c:if>
       <c:if test="${flag != 1}">
       <option>文化</option>
       </c:if>
       <c:if test="${flag == 2}">
       <option selected>兴趣</option>
       </c:if>
       <c:if test="${flag != 2}">
       <option>兴趣</option>
       </c:if>
       <c:if test="${flag == 3}">
       <option selected>学术</option>
       </c:if>
       <c:if test="${flag != 3}">
       <option>学术</option>
       </c:if>
       <c:if test="${flag == 4}">
       <option selected>艺术</option>
       </c:if>
       <c:if test="${flag != 4}">
       <option>艺术</option>
       </c:if>
       
 
        </select>
      </div>
    </div>
</div>

 <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">负责人</label>
      <div class="layui-input-inline">
        <input type="text" name="name" lay-verify="required" autocomplete="off" value="${changeAS.name}" class="layui-input">
      </div>
    </div>
  </div>
 <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">点击修改</button>
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
<% String flagAsChange = (String)request.getAttribute("flagAsChange");
if(flagAsChange!=null&&flagAsChange.length()>0&&flagAsChange.equals("1"))
	
	{%>
 <script type="text/javascript">
                window.onload=function(){
                	func1(); }
 </script>
<%  
request.setAttribute("flagAsChange",null);
	}
%>

</body>
</html>