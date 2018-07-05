function queryIdfaInfo(idfa){
    var appCode = $('#appCode').combobox('getValue');
    if (!appCode){
        $.messager.alert('提示',"请选择应用");
        return;
    }
    $.ajax({
        type: "POST",
        url: "queryIdfaInfo",
        data: {idfa: idfa, appCode: appCode},
        dataType: "json",
        success: function (result) {
            if (result.code == 1){
                var callbackTime = "没有回调信息";
                if (result.data.callbackTime != null){
                    callbackTime = formatDateTime(result.data.callbackTime);
                }
                $.messager.alert('IDFA信息',"上报时间：" + formatDateTime(result.data.reportTime) + '<br>' + "回调时间：" + callbackTime);
            }else {
                $.messager.alert('提示',result.msg);
            }
        },
        error: function (request, status, error) {
            $.messager.alert('提示','网络异常');
        }
    });
}

function formatDateTime(timeStamp) {
    var date = new Date();
    date.setTime(timeStamp);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
}