<%--
  Created by IntelliJ IDEA.
  User: MI
  Date: 2018/5/12
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css" >
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/advertInfo.js"></script>
</head>
<body>
<div style="width: 1000px;margin: auto">
    <table id="advertInfoTable" class="easyui-datagrid"
           data-options="rownumbers:true,singleSelect:true,fitColumns:true,fit:false,url:'queryAdvertInfo',toolbar:toolbar,loadMsg: '数据加载中...'">
        <thead>
        <tr>
            <th data-options="field:'adverterCode',width:200,align:'center'">AdverterCode</th>
            <th data-options="field:'appCode',width:200,align:'center'">AppCode</th>
            <th data-options="field:'adverterName',width:200,align:'center'">AdverterName</th>
            <th data-options="field:'balanceRatio',width:200,align:'center'">BalanceRatio</th>
            <th data-options="field:'comeFrom',width:200,align:'center'">ComeFrom</th>
        </tr>
        </thead>
    </table>
</div>
<div id="formWinAdd" class="easyui-window" title="添加渠道商信息" data-options="modal:true,closed:true,iconCls:'icon-add',minimizable:false" style="width:500px;height:300px;padding:10px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;" class="easyui-panel">
            <form id="advertInfoFormAdd" method="post">
                <table style="margin: auto">
                    <tr>
                        <td>AdverterCode:</td>
                        <td><input id="adverterCode-add" class="easyui-validatebox" type="text" name="adverterCode" data-options="required:true"></input></td>
                    </tr>
                    <tr>
                        <td>AppCode:</td>
                        <td><input id="appCode-add" class="easyui-validatebox" type="text" name="appCode" data-options="required:true"></input></td>
                    </tr>
                    <tr>
                        <td>AdverterName:</td>
                        <td><input id="adverterName-add" class="easyui-textbox" type="text" name="adverterName"></input></td>
                    </tr>
                    <tr>
                        <td>BalanceRatio:</td>
                        <td><input id="balanceRatio-add" class="easyui-numberbox" type="text" name="balanceRatio" data-options="min:0,max:100,precision:0,required:true"></input></td>
                    </tr>
                    <tr>
                        <td>ComeFrom:</td>
                        <td><input id="comeFrom-add" class="easyui-textbox" type="text" name="comeFrom"></input></td>
                    </tr>
                </table>
            </form>
        </div>
        <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addAdvertInfo()" style="width:80px">保存</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="closeAddWin()" style="width:80px">取消</a>
        </div>
    </div>
</div>

<div id="formWinUpdate" class="easyui-window" title="更新渠道商信息" data-options="modal:true,closed:true,iconCls:'icon-add',minimizable:false" style="width:500px;height:300px;padding:10px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;" class="easyui-panel">
            <form id="advertInfoFormUpdate" method="post">
                <table style="margin: auto">
                    <tr>
                        <td>AdverterCode:</td>
                        <td><input id="adverterCode-update" class="easyui-validatebox" type="text" name="adverterCode" data-options="required:true"></input></td>
                    </tr>
                    <tr>
                        <td>AppCode:</td>
                        <td><input id="appCode-update" class="easyui-validatebox" type="text" name="appCode" data-options="required:true"></input></td>
                    </tr>
                    <tr>
                        <td>AdverterName:</td>
                        <td><input id="adverterName-update" class="easyui-textbox" type="text" name="adverterName"></input></td>
                    </tr>
                    <tr>
                        <td>BalanceRatio:</td>
                        <td><input id="balanceRatio-update" class="easyui-numberbox" type="text" name="balanceRatio" data-options="min:0,max:100,precision:0,required:true"></input></td>
                    </tr>
                    <tr>
                        <td>ComeFrom:</td>
                        <td><input id="comeFrom-update" class="easyui-textbox" type="text" name="comeFrom"></input></td>
                    </tr>
                </table>
            </form>
        </div>
        <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="updateAdvertInfo()" style="width:80px">保存</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="closeUpdateWin()" style="width:80px">取消</a>
        </div>
    </div>
</div>
</body>
</html>