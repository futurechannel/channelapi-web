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
    <form id="queryForm" name="queryForm" method="post">
        <label for="startDate">开始日期：</label><input id = "startDate" name="startDate" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"/>
        <label for="endDate">结束日期：</label><input id = "endDate" name="endDate" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" />
        <label for="appCode">选择应用：</label><input id = "appCode" class="easyui-combobox"
                    name="appCode"
                    data-options="
					url:'queryAppCode',
					valueField:'appCode',
					textField:'appCode',
					panelHeight:'auto'
			">
        <%--<input type="submit" value="查询"/>--%>
        <a id = "submitForm" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a id = "exportExcel" class="easyui-linkbutton" iconCls="icon-redo">导出表</a>
    </form>
</div>
<div style="width: 1000px;margin: 30px auto">
    <table id="reportDataDayTbl"
           data-options="rownumbers:true,singleSelect:true,fitColumns:true,fit:false,loadMsg: '数据加载中...'">
        <thead>
        <tr>
            <th data-options="field:'bizDate',width:200,align:'center',
            formatter: function(value,rowData,rowIndex){
				return value?myformatter(new Date(value)):value;
			}">日期</th>
            <th data-options="field:'adverterCode',width:200,align:'center'">渠道号</th>
            <th data-options="field:'appCode',width:200,align:'center'">应用号</th>
            <th data-options="field:'clickCnt',width:200,align:'center'">点击量</th>
            <%--<th data-options="field:'activeCnt',width:200,align:'center'">激活量</th>--%>
            <th data-options="field:'callbackCnt',width:200,align:'center'">回调量</th>
            <%--<th data-options="field:'activeRate',width:200,align:'center',--%>
            <%--formatter: function(value,rowData,rowIndex){--%>
				<%--return (rowData.activeCnt == 0?0:rowData.activeCnt/rowData.clickCnt).toFixed(3);--%>
			<%--}">激活率</th>--%>
            <%--<th data-options="field:'callbackRate',width:200,align:'center',--%>
            <%--formatter: function(value,rowData,rowIndex){--%>
				<%--return (rowData.callbackCnt == 0?0:rowData.callbackCnt/rowData.activeCnt).toFixed(3);--%>
			<%--}">回调率</th>--%>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
