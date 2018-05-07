//定义函数
$(function(){
    //数据
    var treeData=[{
        text:"天数据",
        attributes:{
            url:"/queryByFilter?from=lastDay"
        }
    }];

    //实例化菜单
    $("#tree").tree({
        data:treeData,
        lines:true,
        onClick:function(node){
            if(node.attributes){
                openTab(node.text,node.attributes.url);
            }
        }
    });

    //新增Tab
    function openTab(text,url){
        if($("#tabs").tabs('exists',text)){
            $("#tabs").tabs('select',text);
        }else{
            var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+">+</iframe>";
            $("#tabs").tabs('add',{
                title:text,
                closable:true,
                content:content
            });
        }
    }
});

