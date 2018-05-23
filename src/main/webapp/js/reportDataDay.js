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
    $('#exportExcel').bind('click', function(){
        exportExcel();
    });
});

//导出excel
function exportExcel() {
    $.messager.confirm('确认', '确认把该搜索结果导出Excel表格 ？', function(r) {
        if (r) {
            var startDate = $('#startDate').val();
            var endDate = $('#endDate').val();
            var appCode = $('#appCode').val();
            $.messager.progress({
                title : '处理中',
                msg : '请稍后',
            });
            $.messager.progress('close');
            location.href="exportExcel?startDate=" + startDate + "&endDate=" + endDate + "&appCode=" + appCode;
        }
    });
}