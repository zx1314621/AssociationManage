<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>社团管理系统</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all" />
    <link rel="stylesheet" href=font-awesome/css/font-awesome.css media="all" />
    <link rel="stylesheet" href="src/css/app.css" media="all" />
    <link rel="stylesheet" href="src/css/themes/default.css" media="all" id="skin" kit-skin />
    <style>
        .admin-side-full{
            position: absolute; cursor: pointer;
            z-index: 19940201;
            left: 200px;
            color: white;
            text-align: center;
            width: 30px;
            height: 30px;
            background-color: #1AA094;
            line-height: 30px;
            top: 25%;
        }
        .admin-side-full{
            left: 240px;
        }
    </style>
</head>

<body class="kit-theme">
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">社团管理系统</div>
        <div class="layui-logo kit-logo-mobile"></div>
        <div class="admin-side-full" id="fullscreen">
            <i class="fa fa-life-bouy" aria-hidden="true"></i>
        </div>
        <ul class="layui-nav layui-layout-right kit-nav">
            <li class="layui-nav-item">
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">后台人员</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="login.jsp"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black kit-side">
        <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span> 审批注册</span></a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" kit-target data-options="{url:'approveAs.action',icon:'&#xe6c6;',title:'审批社团注册信息',id:'1'}">
                                <i class="layui-icon">&#xe6c6;</i><span> 社团注册</span></a>
                        </dd>
                    </dl>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" kit-target data-options="{url:'approveActivity.action',icon:'&#xe6c6;',title:'审批活动',id:'2'}">
                                <i class="layui-icon">&#xe6c6;</i><span> 活动发起</span></a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span> 审批修改</span></a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" kit-target data-options="{url:'managerAsChange.action',icon:'&#xe6c6;',title:'审批社团修改',id:'3'}">
                                <i class="layui-icon">&#xe6c6;</i><span> 社团修改</span></a>
                        </dd>
                    </dl>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" kit-target data-options="{url:'managerActivityChange.action',icon:'&#xe6c6;',title:'审批活动修改',id:'4'}">
                                <i class="layui-icon">&#xe6c6;</i><span> 活动修改</span></a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span> 系统信息</span></a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" kit-target data-options="{url:'managerShowAs.action',icon:'&#xe6c6;',title:'社团信息',id:'5'}">
                                <i class="layui-icon">&#xe6c6;</i><span> 社团信息</span></a>
                        </dd>
                    </dl>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" kit-target data-options="{url:'approveActivity.action',icon:'&#xe6c6;',title:'活动信息',id:'6'}">
                                <i class="layui-icon">&#xe6c6;</i><span> 活动信息</span></a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body" id="container">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">主体内容加载中,请稍等...</div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        2017 &copy;
        <a href="http://baidu.com">https:www.ussthospital.com/</a>

    </div>
</div>
<script src="layui/layui.js"></script>
<script>
    var message;
    layui.config({
        base: 'src/js/',
        version: '1.0.1'
    }).use(['app', 'message'], function() {
        var app = layui.app,
            $ = layui.jquery,
            layer = layui.layer;
        //将message设置为全局以便子页面调用
        message = layui.message;
        //主入口
        app.set({
            type: 'iframe'
        }).init();
        $('dl.skin > dd').on('click', function() {
            var $that = $(this);
            var skin = $that.children('a').data('skin');
            switchSkin(skin);
        });
        var setSkin = function(value) {
                layui.data('kit_skin', {
                    key: 'skin',
                    value: value
                });
            },
            getSkinName = function() {
                return layui.data('kit_skin').skin;
            },
            switchSkin = function(value) {
                var _target = $('link[kit-skin]')[0];
                _target.href = _target.href.substring(0, _target.href.lastIndexOf('/') + 1) + value + _target.href.substring(_target.href.lastIndexOf('.'));
                setSkin(value);
            },
            initSkin = function() {
                var skin = getSkinName();
                switchSkin(skin === undefined ? 'default' : skin);
            }();
    });
</script>
<script>
    window.onload=function(){
        var  div = document.getElementById('fullscreen');

        div.onclick=function(){
            var docElm = document.documentElement;
            //W3C
            if (docElm.requestFullscreen) {
                docElm.requestFullscreen();
            }
            //FireFox
            else if (docElm.mozRequestFullScreen) {
                docElm.mozRequestFullScreen();
            }
            //Chrome等
            else if (docElm.webkitRequestFullScreen) {
                docElm.webkitRequestFullScreen();
            }
            //IE11
            else if (elem.msRequestFullscreen) {
                elem.msRequestFullscreen();
            }
            layer.msg('按Esc即可退出全屏');
        }
    }
</script>
</body>

</html>