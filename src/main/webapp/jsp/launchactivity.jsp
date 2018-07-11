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
	layer.alert('活动室可用!', {
        icon: 1,
        skin: 'layer-ext-moon' 
    });
}
function func2() {
	layer.alert('活动室不可用!', {
        icon: 2,
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
<form class="layui-form"  id="chooseForm" action="launchActivity.action" method="post">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>发起活动</legend>
</fieldset>
  <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">活动编号</label>
      <div class="layui-input-inline">
        <input type="text" name="activity_id" lay-verify="required" autocomplete="off" value="${activity_id}" class="layui-input" readonly>
      </div>
    </div>
  </div>
  <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">活动名称</label>
      <div class="layui-input-inline">
        <input type="text" name="activity_name" lay-verify="required" autocomplete="off" placeholder="请输入活动名称" class="layui-input">
        
      </div>
    </div>
  </div>
   <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">活动日期</label>
      <div class="layui-input-inline">
        <input type="text" id="day" name="day" lay-verify="required" autocomplete="off" value="<%=date %>" class="layui-input" >
        
      </div>
    </div>
  </div>
   <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">开始时间</label>
      <div class="layui-input-inline">
       <select id="start_time" name="start_time"  lay-verify="required" lay-search="" >
          <option >0:00</option>
          <option >1:00</option>
          <option >2:00</option>
          <option >3:00</option>
          <option >4:00</option>
          <option >5:00</option>
          <option >6:00</option>
          <option >7:00</option>
          <option >8:00</option>
          <option >9:00</option>
          <option >10:00</option>
          <option >11:00</option>
          <option >12:00</option>
          <option >13:00</option>
          <option >14:00</option>
          <option >15:00</option>
          <option >16:00</option>
          <option >17:00</option>
          <option >18:00</option>
          <option >19:00</option>
          <option >20:00</option>
          <option >21:00</option>
          <option >22:00</option>
          <option >23:00</option>
          <option >24:00</option>
        </select>
      </div>
    </div>
</div>
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">结束时间</label>
      <div class="layui-input-inline">
       <select id="end_time" name="end_time"  lay-verify="required" lay-search="" >
          <option >0:00</option>
          <option >1:00</option>
          <option >2:00</option>
          <option >3:00</option>
          <option >4:00</option>
          <option >5:00</option>
          <option >6:00</option>
          <option >7:00</option>
          <option >8:00</option>
          <option >9:00</option>
          <option >10:00</option>
          <option >11:00</option>
          <option >12:00</option>
          <option >13:00</option>
          <option >14:00</option>
          <option >15:00</option>
          <option >16:00</option>
          <option >17:00</option>
          <option >18:00</option>
          <option >19:00</option>
          <option >20:00</option>
          <option >21:00</option>
          <option >22:00</option>
          <option >23:00</option>
          <option selected>24:00</option>
        </select>
      </div>
    </div>
</div>
      
<div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">活动室</label>
      <div class="layui-input-inline">
       <select id="place" name="place"  lay-verify="required" lay-search="" >
        <c:choose>
        <c:when test ="${f1==1}">
          <option >活动室1</option>
        </c:when>
        <c:when test ="${f2==1}">
          <option >活动室2</option>
        </c:when>
        <c:when test ="${f3==1}">
          <option >活动室3</option>
        </c:when>
        <c:when test ="${f4==1}">
          <option >活动室4</option>
        </c:when>
        <c:when test ="${f5==1}">
          <option >活动室5</option>
        </c:when>
        <c:when test ="${f6==1}">
          <option >活动室6</option>
        </c:when>
        <c:when test ="${f7==1}">
          <option >活动室7</option>
        </c:when>
        <c:when test ="${f8==1}">
          <option >活动室8</option>
        </c:when>
        </c:choose>
        </select>
      </div>
    </div>
    <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" name="check" id="check" value="123">检测是否可用</button>
</div>
  
 <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">点击发起</button>
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
<% String enable = (String)request.getAttribute("enable");
if(enable!=null&&enable.length()>0&&enable.equals("1"))
	
	{%>
 <script type="text/javascript">
                window.onload=function(){
                	func1(); }
 </script>
<%  
session.setAttribute("enable",null);
	}
if(enable!=null&&enable.length()>0&&enable.equals("0")){
	
%>
 <script type="text/javascript">
                window.onload=function(){
                	func2(); }
 </script>
<%
session.setAttribute("enable",null);
} 
%>
</body>
</html>