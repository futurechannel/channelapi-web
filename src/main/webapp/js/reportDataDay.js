function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}

function myparser(s){
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
}

$.messager.defaults = { ok: "是", cancel: "否" };

$(function(){

    //初始化查询框
    $("#startDate").datebox("setValue",getLastDay());

    //初始化表格
    $('#reportDataDayTbl').datagrid({
        url:'queryByFilter',
        queryParams:{
            startDate: getLastDay()
        }
    });

    //导出excel
    $('#exportExcel').bind('click', function(){
        exportExcel();
    });

    //查询
    $('#submitForm').bind('click', function(){
        loadTable();
    });


});

function getLastDay() {
    var lastDay = new Date(new Date()-24*60*60*1000);
    return myformatter(lastDay);
}

function loadTable() {
    var startDate = $('#startDate').datebox('getValue');
    var endDate = $('#endDate').datebox('getValue');
    var appCode = $('#appCode').datebox('getValue');
    $('#reportDataDayTbl').datagrid('load', {
        startDate: startDate,
        endDate: endDate,
        appCode: appCode
    });
}

//导出excel
function exportExcel() {
    $.messager.confirm('确认', '确认把该搜索结果导出Excel表格 ？', function(r) {
        if (r) {
            var startDate = $('#startDate').datebox('getValue');
            var endDate = $('#endDate').datebox('getValue');
            var appCode = $('#appCode').datebox('getValue');
            $.messager.progress({
                title : '处理中',
                msg : '请稍后',
            });
            $.messager.progress('close');
            location.href="exportExcel?startDate=" + startDate + "&endDate=" + endDate + "&appCode=" + appCode;
        }
    });
}