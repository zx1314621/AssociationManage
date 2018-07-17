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
function func3() {
	layer.alert('发起失败时间场地冲突或未进行场地检查!', {
        icon: 2,
        skin: 'layer-ext-moon' 
    });
}
function func4() {
	layer.alert('请先检测场地是否可用!', {
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
<form class="layui-form"  id="chooseForm" action="submitEdit.action" method="post">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>编辑活动</legend>
</fieldset>
  <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">活动编号</label>
      <div class="layui-input-inline">
        <input type="text" name="activity_id" lay-verify="required" autocomplete="off" value="${activityCustom.id}" class="layui-input" readonly>
      </div>
    </div>
  </div>
  <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">活动名称</label>
      <div class="layui-input-inline">
        <input type="text" name="activity_name" lay-verify="required" autocomplete="off"  value="${activityCustom.name}"class="layui-input">
        
      </div>
    </div>
  </div>
   <div class="layui-form-item">
   <div class="layui-inline">
      <label class="layui-form-label">活动日期</label>
      <div class="layui-input-inline">
        <input type="text" id="day" name="day" lay-verify="required" autocomplete="off" value="${activityCustom.day}" class="layui-input" >
        
      </div>
    </div>
  </div>
   <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">开始时间</label>
      <div class="layui-input-inline">
       <select id="start_time" name="start_time"  lay-verify="required" lay-search="" >
       <c:if test="${flagstart==0 }">
       <option selected>0:00</option>
       </c:if>
        <c:if test="${flagstart!=0 }">
       <option>0:00</option>
       </c:if>
       <c:if test="${flagstart==1 }">
       <option selected>1:00</option>
       </c:if>
        <c:if test="${flagstart!=1 }">
       <option>1:00</option>
       </c:if>
        <c:if test="${flagstart==2 }">
       <option selected>2:00</option>
       </c:if>
        <c:if test="${flagstart!=2 }">
       <option >2:00</option>
       </c:if>
        <c:if test="${flagstart==3 }">
       <option selected>3:00</option>
       </c:if>
       <c:if test="${flagstart!=3 }">
       <option>3:00</option>
       </c:if>  
       <c:if test="${flagstart==4 }">
       <option selected>4:00</option>
       </c:if>
       <c:if test="${flagstart!=4 }">
       <option>4:00</option>
       </c:if>
       <c:if test="${flagstart==5 }">
       <option selected>5:00</option>
       </c:if>
       <c:if test="${flagstart!=5 }">
       <option>5:00</option>
       </c:if>
       <c:if test="${flagstart==6 }">
       <option selected>6:00</option>
       </c:if>
       <c:if test="${flagstart!=6 }">
       <option>6:00</option>
       </c:if>
       <c:if test="${flagstart==7 }">
       <option selected>7:00</option>
       </c:if>
       <c:if test="${flagstart!=7 }">
       <option>7:00</option>
       </c:if>
       <c:if test="${flagstart==8 }">
       <option selected>8:00</option>
       </c:if>
       <c:if test="${flagstart!=8 }">
       <option >8:00</option>
       </c:if>
       <c:if test="${flagstart==9 }">
       <option selected>9:00</option>
       </c:if>
       <c:if test="${flagstart!=9 }">
       <option>9:00</option>
       </c:if>
       <c:if test="${flagstart==10 }">
       <option selected>10:00</option>
       </c:if>
       <c:if test="${flagstart!=10 }">
       <option>10:00</option>
       </c:if>
       <c:if test="${flagstart==11}">
       <option selected>11:00</option>
       </c:if>
       <c:if test="${flagstart!=11 }">
       <option>11:00</option>
       </c:if>
       <c:if test="${flagstart==12 }">
       <option selected>12:00</option>
       </c:if>
       <c:if test="${flagstart!=12 }">
       <option>12:00</option>
       </c:if>
       <c:if test="${flagstart==13 }">
       <option selected>13:00</option>
       </c:if>
       <c:if test="${flagstart!=13 }">
       <option>13:00</option>
       </c:if>
       <c:if test="${flagstart==14 }">
       <option selected>14:00</option>
       </c:if>
       <c:if test="${flagstart!=14 }">
       <option>14:00</option>
       </c:if>
       <c:if test="${flagstart==15 }">
       <option selected>15:00</option>
       </c:if>
       <c:if test="${flagstart!=15 }">
       <option >15:00</option>
       </c:if>
       <c:if test="${flagstart==16 }">
       <option selected>16:00</option>
       </c:if>
       <c:if test="${flagstart!=16 }">
       <option >16:00</option>
       </c:if>
       <c:if test="${flagstart==17 }">
       <option selected>17:00</option>
       </c:if>
       <c:if test="${flagstart!=17 }">
       <option>17:00</option>
       </c:if>
       <c:if test="${flagstart==18}">
       <option selected>18:00</option>
       </c:if>
       <c:if test="${flagstart!=18}">
       <option>18:00</option>
       </c:if>
       <c:if test="${flagstart==19 }">
       <option selected>19:00</option>
       </c:if>
       <c:if test="${flagstart!=19 }">
       <option>19:00</option>
       </c:if>
       <c:if test="${flagstart==20 }">
       <option selected>20:00</option>
       </c:if>
       <c:if test="${flagstart!=20 }">
       <option>20:00</option>
       </c:if>
       <c:if test="${flagstart==21 }">
       <option selected>21:00</option>
       </c:if>
       <c:if test="${flagstart!=21 }">
       <option>21:00</option>
       </c:if>
       <c:if test="${flagstart==22 }">
       <option selected>22:00</option>
       </c:if>
       <c:if test="${flagstart!=22 }">
       <option>22:00</option>
       </c:if>
       <c:if test="${flagstart==23 }">
       <option selected>23:00</option>
       </c:if>
       <c:if test="${flagstart!=23 }">
       <option>23:00</option>
       </c:if>
       <c:if test="${flagstart==24 }">
       <option selected>24:00</option>
       </c:if>
       <c:if test="${flagstart!=24 }">
       <option>24:00</option>
       </c:if>
        </select>
      </div>
    </div>
</div>
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">结束时间</label>
      <div class="layui-input-inline">
       <select id="end_time" name="end_time"  lay-verify="required" lay-search="" >
        <c:if test="${flagend==0 }">
       <option selected>0:00</option>
       </c:if>
        <c:if test="${flagend!=0 }">
       <option>0:00</option>
       </c:if>
       <c:if test="${flagend==1 }">
       <option selected>1:00</option>
       </c:if>
        <c:if test="${flagend!=1 }">
       <option>1:00</option>
       </c:if>
        <c:if test="${flagend==2 }">
       <option selected>2:00</option>
       </c:if>
        <c:if test="${flagend!=2 }">
       <option >2:00</option>
       </c:if>
        <c:if test="${flagend==3 }">
       <option selected>3:00</option>
       </c:if>
       <c:if test="${flagend!=3 }">
       <option>3:00</option>
       </c:if>  
       <c:if test="${flagend==4 }">
       <option selected>4:00</option>
       </c:if>
       <c:if test="${flagend!=4 }">
       <option>4:00</option>
       </c:if>
       <c:if test="${flagend==5 }">
       <option selected>5:00</option>
       </c:if>
       <c:if test="${flagend!=5 }">
       <option>5:00</option>
       </c:if>
       <c:if test="${flagend==6 }">
       <option selected>6:00</option>
       </c:if>
       <c:if test="${flagend!=6 }">
       <option>6:00</option>
       </c:if>
       <c:if test="${flagend==7 }">
       <option selected>7:00</option>
       </c:if>
       <c:if test="${flagend!=7 }">
       <option>7:00</option>
       </c:if>
       <c:if test="${flagend==8 }">
       <option selected>8:00</option>
       </c:if>
       <c:if test="${flagend!=8 }">
       <option >8:00</option>
       </c:if>
       <c:if test="${flagend==9 }">
       <option selected>9:00</option>
       </c:if>
       <c:if test="${flagend!=9 }">
       <option>9:00</option>
       </c:if>
       <c:if test="${flagend==10 }">
       <option selected>10:00</option>
       </c:if>
       <c:if test="${flagend!=10 }">
       <option>10:00</option>
       </c:if>
       <c:if test="${flagend==11}">
       <option selected>11:00</option>
       </c:if>
       <c:if test="${flagend!=11 }">
       <option>11:00</option>
       </c:if>
       <c:if test="${flagend==12 }">
       <option selected>12:00</option>
       </c:if>
       <c:if test="${flagend!=12 }">
       <option>12:00</option>
       </c:if>
       <c:if test="${flagend==13 }">
       <option selected>13:00</option>
       </c:if>
       <c:if test="${flagend!=13 }">
       <option>13:00</option>
       </c:if>
       <c:if test="${flagend==14 }">
       <option selected>14:00</option>
       </c:if>
       <c:if test="${flagend!=14 }">
       <option>14:00</option>
       </c:if>
       <c:if test="${flagend==15 }">
       <option selected>15:00</option>
       </c:if>
       <c:if test="${flagend!=15 }">
       <option >15:00</option>
       </c:if>
       <c:if test="${flagend==16 }">
       <option selected>16:00</option>
       </c:if>
       <c:if test="${flagend!=16 }">
       <option >16:00</option>
       </c:if>
       <c:if test="${flagend==17 }">
       <option selected>17:00</option>
       </c:if>
       <c:if test="${flagend!=17 }">
       <option>17:00</option>
       </c:if>
       <c:if test="${flagend==18}">
       <option selected>18:00</option>
       </c:if>
       <c:if test="${flagend!=18}">
       <option>18:00</option>
       </c:if>
       <c:if test="${flagend==19 }">
       <option selected>19:00</option>
       </c:if>
       <c:if test="${flagend!=19 }">
       <option>19:00</option>
       </c:if>
       <c:if test="${flagend==20 }">
       <option selected>20:00</option>
       </c:if>
       <c:if test="${flagend!=20 }">
       <option>20:00</option>
       </c:if>
       <c:if test="${flagend==21 }">
       <option selected>21:00</option>
       </c:if>
       <c:if test="${flagend!=21 }">
       <option>21:00</option>
       </c:if>
       <c:if test="${flagend==22 }">
       <option selected>22:00</option>
       </c:if>
       <c:if test="${flagend!=22 }">
       <option>22:00</option>
       </c:if>
       <c:if test="${flagend==23 }">
       <option selected>23:00</option>
       </c:if>
       <c:if test="${flagend!=23 }">
       <option>23:00</option>
       </c:if>
       <c:if test="${flagend==24 }">
       <option selected>24:00</option>
       </c:if>
       <c:if test="${flagend!=24 }">
       <option>24:00</option>
       </c:if>
        </select>
      </div>
    </div>
</div>
      
<div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">活动室</label>
      <div class="layui-input-inline">
       <select id="place" name="place"  lay-verify="required" lay-search="" >
        <c:if test="${flagplace==1 }">
       <option selected>活动室1</option>
       </c:if>
       <c:if test="${flagplace!=1 }">
       <option>活动室1</option>
       </c:if>
       <c:if test="${flagplace==2 }">
       <option selected>活动室2</option>
       </c:if>
       <c:if test="${flagplace!=2 }">
       <option>活动室2</option>
       </c:if>
       <c:if test="${flagplace==3 }">
       <option selected>活动室3</option>
       </c:if>
       <c:if test="${flagplace!=3 }">
       <option>活动室3</option>
       </c:if>
       <c:if test="${flagplace==4 }">
       <option selected>活动室4</option>
       </c:if>
       <c:if test="${flagplace!=4 }">
       <option>活动室4</option>
       </c:if>
       <c:if test="${flagplace==5 }">
       <option selected>活动室5</option>
       </c:if>
       <c:if test="${flagplace!=5 }">
       <option>活动室5</option>
       </c:if>
       <c:if test="${flagplace==6 }">
       <option selected>活动室6</option>
       </c:if>
       <c:if test="${flagplace!=6 }">
       <option>活动室6</option>
       </c:if>
       <c:if test="${flagplace==7 }">
       <option selected>活动室7</option>
       </c:if>
       <c:if test="${flagplace!=7 }">
       <option>活动室7</option>
       </c:if>
       <c:if test="${flagplace==8 }">
       <option selected>活动室8</option>
       </c:if>
       <c:if test="${flagplace!=8 }">
       <option>活动室8</option>
       </c:if>
        </select>
      </div>
    </div>
    <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" name="check" id="check" value="123">检测是否可用</button>
</div>
  
 <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">确认修改</button>
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
request.setAttribute("enable",null);
	}
else if(enable!=null&&enable.length()>0&&enable.equals("0")){
	
%>
 <script type="text/javascript">
                window.onload=function(){
                	func2(); }
 </script>
<%
request.setAttribute("enable",null);
} 
%>
<% String flag8 = String.valueOf(request.getAttribute("flag8"));
if(flag8!=null&&flag8.length()>0&&flag8.equals("0"))
	
	{%>
 <script type="text/javascript">
                window.onload=function(){
                	func3(); }
 </script>
<%  
request.setAttribute("flag8",null);
	}
%>

<% String enableflag1 = (String)request.getAttribute("enableflag");
if(enableflag1!=null&&enableflag1.length()>0&&enableflag1.equals("0"))
	
	{%>
 <script type="text/javascript">
                window.onload=function(){
                	func4(); }
 </script>
<%  
request.setAttribute("enableflag",null);
	}
%>
</body>
</html>