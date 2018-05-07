<%--
  Created by IntelliJ IDEA.
  User: MI
  Date: 2018/5/2
  Time: 0:08
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
    <script type="text/javascript" src="js/reportDataDay.js"></script>
</head>
<body>
<div style="text-align: center; border-bottom:1px dashed #0099FF;padding: 30px">
    <form name="queryForm" action="/queryByFilter" method="post">
        开始日期：<input name="startDate" value="${result.startDate}" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"/>
        结束日期：<input name="endDate" value="${result.endDate}" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" />
        选择应用：<input class="easyui-combobox"
                    name="appCode"
                    value="${result.appCode}"
                    data-options="
					url:'/queryAppCode',
					valueField:'appCode',
					textField:'appCode',
					panelHeight:'auto'
			">
        <input type="submit" value="查询"/>
    </form>
</div>
<div style="text-align: center">
    <table border="1" style="margin: 30px auto">
        <tr>
            <th>日期</th>
            <th>渠道号</th>
            <th>应用号</th>
            <th>点击量</th>
            <th>激活量</th>
            <th>回调量</th>
        </tr>
        <c:forEach var="item" items="${result.reportDataDays}">
            <tr>
                <td><fmt:formatDate value="${item.bizDate}" pattern="yyyy-MM-dd"/></td>
                <td>${item.adverterCode}</td>
                <td>${item.appCode}</td>
                <td>${item.clickCnt}</td>
                <td>${item.activeCnt}</td>
                <td>${item.callbackCnt}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
