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

function func4() {
	layer.alert('请先登录!', {
        icon: 2,
        skin: 'layer-ext-moon' 
    });
}
</script>           



<form class="layui-form"  id="chooseForm" action="queryActivity.action" method="post">
 

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>基本信息</legend>
</fieldset>
    <div class="layui-inline">
      <label class="layui-form-label">活动编号</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" id="activity_id" name="activity_id" placeholder="活动编号">
      </div>
    </div>
   
   <div class="layui-inline">
      <label class="layui-form-label">举办社团</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" id="as_name" name="as_name" placeholder="举办社团">
      </div>
    </div>

    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">点击查找</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      <button  style="float:right;margin-right:100px;"  class="layui-btn layui-btn-warm" name="add" value="1"  type="submit" ><i class="layui-icon">&#xe654;</i> 发起活动</button>
      
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
      <th>活动编号</th>
      <th>活动名称</th>
      <th>活动日期</th>
      <th>开始时间</th>
      <th>结束时间</th>
      <th>活动地点</th>
      <th>活动举办社团</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody>
  <form action = showdetail.action method = "post">

<c:forEach var = "activityList" items = "${activityList}">

<tr>
    <td>${activityList.id }</td>
    <td>${activityList.name }</td>
    <td>${activityList.day }</td>
    <td>${activityList.start_time}:00</td>
    <td>${activityList.end_time }:00</td>
    <td>${activityList.place }</td>
    <td>${activityList.as_name }</td>
      <td><button class="layui-btn layui-btn-normal layui-btn-mini news_del" id="activityId" name="activityId"  type="submit"  value="${activityList.id}"><i class="layui-icon">&#xe615;</i> 查看</button></td>  
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
<% String flag1 = (String)request.getAttribute("flag1");
if(flag1!=null&&flag1.length()>0&&flag1.equals("1"))
	
	{%>
 <script type="text/javascript">
                window.onload=function(){
                	func4(); }
 </script>
<%  
request.setAttribute("flag1",null);
	}
%>
</body>
</html>