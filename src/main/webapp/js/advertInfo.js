var toolbar = [{
    text:'添加',
    iconCls:'icon-add',
    handler:add
},{
    text:'删除',
    iconCls:'icon-cut',
    handler:remove
},'-',{
    text:'更新',
    iconCls:'icon-edit',
    handler:update
}];

function remove() {
    var row = $('#advertInfoTable').datagrid('getSelected');
    console.log(row);

    if (row == null){
        $.messager.alert('提示','请至少选择一行');
    }else {
        var adverterCode = row.adverterCode;
        var appCode = row.appCode;
        $.messager.confirm('提示', '确认删除渠道商' + adverterCode + "?", function(r){
            if (r){
                $.ajax({
                    type: "POST",
                    url: "deleteAdvertInfo",
                    data: {adverterCode: adverterCode, appCode: appCode},
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 1){
                            $('#advertInfoTable').datagrid('reload');
                            $.messager.alert('提示','删除成功');
                        }else {
                            $.messager.alert('提示','删除失败');
                        }
                    },
                    error: function (request, status, error) {
                        $.messager.alert('提示','网络异常');
                    }
                });
            }
        });
    }

}

function add() {
    $('#formWinAdd').window('open');
}

function update() {
    var selectItem = $('#advertInfoTable').datagrid('getSelected');
    if (selectItem == null){
        $.messager.alert('提示',"请选择一条记录");
        return;
    }
    $('#formWinUpdate').window('open');
    $('#advertInfoFormUpdate').form('load',selectItem);
}


function addAdvertInfo() {
    if (!$('#advertInfoFormAdd').form('validate')){
        alert("输入信息有误！");
        return;
    }
    var adverterCode = $('#adverterCode-add').val();
    var appCode = $('#appCode-add').val();
    var adverterName = $('#adverterName-add').val();
    var balanceRatio = $('#balanceRatio-add').val();
    var comeFrom = $('#comeFrom-add').val();

    var params = {
        adverterCode: adverterCode,
        appCode: appCode,
        adverterName: adverterName,
        balanceRatio: balanceRatio,
        comeFrom: comeFrom
    };

    $.ajax({
        type: "POST",
        url: "insertAdvertInfo",
        data: params,
        dataType: "json",
        success: function (result) {
            if (result.code == 1){
                $('#advertInfoTable').datagrid('reload');
                $('#formWinAdd').window('close');
                $.messager.alert('提示','保存成功');
            }else {
                $.messager.alert('提示',result.msg);
            }
        },
        error: function (request, status, error) {
            $.messager.alert('提示','网络异常');
        }
    });
}


function updateAdvertInfo() {
    if (!$('#advertInfoFormUpdate').form('validate')){
        alert("输入信息有误！");
        return;
    }
    var item = $('#advertInfoTable').datagrid('getSelected');

    var adverterCodeOld = item.adverterCode;
    var appCodeOld = item.appCode;
    var adverterCode = $('#adverterCode-update').val();
    var appCode = $('#appCode-update').val();
    var adverterName = $('#adverterName-update').val();
    var balanceRatio = $('#balanceRatio-update').val();
    var comeFrom = $('#comeFrom-update').val();

    var params = {
        adverterCodeOld: adverterCodeOld,
        appCodeOld: appCodeOld,
        adverterCode: adverterCode,
        appCode: appCode,
        adverterName: adverterName,
        balanceRatio: balanceRatio,
        comeFrom: comeFrom
    };

    $.ajax({
        type: "POST",
        url: "updateAdvertInfo",
        data: params,
        dataType: "json",
        success: function (result) {
            if (result.code == 1){
                $('#advertInfoTable').datagrid('reload');
                $('#formWinUpdate').window('close');
                $.messager.alert('提示','更新成功');
            }else {
                $.messager.alert('提示',result.msg);
            }
        },
        error: function (request, status, error) {
            $.messager.alert('提示','网络异常');
        }
    });
}


function closeAddWin() {
    $('#formWinAdd').window('close');
}

function closeUpdateWin() {
    $('#formWinUpdate').window('close');
}

