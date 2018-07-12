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
	layer.alert('删除申请已提交!', {
        icon: 1,
        skin: 'layer-ext-moon' 
    });
}
</script> 
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
      <th>活动状态</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody>
<form action = editactivity.action  method = "post">
<c:forEach var = "activityList" items = "${activityList}">
<tr>
     <td>${activityList.id }</td>
    <td>${activityList.name }</td>
    <td>${activityList.day }</td>
    <td>${activityList.start_time}:00</td>
    <td>${activityList.end_time }:00</td>
    <td>${activityList.place }</td>
    <td>${activityList.as_name }</td>
    <td>${activityList.activity_status}</td>
    
    <c:if test="${activityList.status==4}"><td></td></c:if>
      <c:if test="${activityList.status!=4}"><td><button class="layui-btn layui-btn-sm layui-btn-normal" id="edit" name="edit"  type="submit"  value="${activityList.id}"><i class="layui-icon">&#xe642;</i> 编辑</button>
      <button class="layui-btn layui-btn-sm layui-btn-danger" id="delete" name="delete"  type="submit"  value="${activityList.id}"><i class="layui-icon">&#xe640;</i> 删除</button></td> </c:if> 

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
<% String deleteflag = (String)request.getAttribute("deleteflag");
if(deleteflag!=null&&deleteflag.length()>0&&deleteflag.equals("1"))
	
	{%>
 <script type="text/javascript">
                window.onload=function(){
                	func4(); }
 </script>
<%  
request.setAttribute("deleteflag",null);
	}
%>
</body>
</html>