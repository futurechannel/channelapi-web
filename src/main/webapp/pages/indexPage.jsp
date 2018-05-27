<%--
  Created by IntelliJ IDEA.
  User: MI
  Date: 2018/5/1
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>渠道数据管理系统</title>
    <%
        //权限验证
        if(session.getAttribute("currentUser")==null){
            response.sendRedirect("loginPage.jsp");
            return;
        }
    %>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css" >
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
</head>
<body class="easyui-layout" >
<div region="north" style="height:80px;background-color:#E0EDFF">
    <div align="left" style="float:left;display: inline">
        <img src="images/main1.jpg" style="width: 70px;height: 70px;margin: 5px 5px">
    </div>
    <div style="color: #e4943e; font-size: 30px;font-weight: bold; display: inline; position: absolute; top: 35%;">
        四通启航后台管理系统
    </div>
    <div style="float: right; position: relative; top: 35%; right: 10px">
        <div style="display: inline; font-size: 20px; margin-right: 10px; ">
            欢迎您！<span style="color: red" >${currentUser.getUsername()}</span>
        </div>
        <div style="display: inline; float: right; margin-top: 2px">
            <a id = "logout" href="logOut" class="easyui-linkbutton" iconCls="icon-cancel">注销</a>
        </div>
    </div>

</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" >
            <div align="center" style="padding-top: 150px; color: red; font-size: 40px">欢迎使用！</div>
        </div>
    </div>
</div>
<div region="west" style="width: 150px;" title="导航菜单" split="true">
    <ul id="menuTree"></ul>
</div>
<div region="south" style="height: 25px;" align="center">Copyright &#169;2018 stonggo all rights reserved. </div>
</body>
</html>
