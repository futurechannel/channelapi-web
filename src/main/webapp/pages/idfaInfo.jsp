<%--
  Created by IntelliJ IDEA.
  User: MI
  Date: 2018/5/16
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css" >
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/idfaInfo.js"></script>
</head>
<body>
<input class="easyui-searchbox" style="width:500px"
       data-options="searcher:queryIdfaInfo,prompt:'请输入IDFA',menu:'#appCode'"></input>
<div id="appCode" style="width:120px">
    <div data-options="name:'appCode'">mangguo</div>
</div>
<div>

</div>
</body>
</html>
