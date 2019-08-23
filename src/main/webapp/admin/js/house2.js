$(function(){
    //使用datagrid显示区域
    $('#dg').datagrid({
        title:"已经审核房屋信息",
        url:'getHouseYesPass',  //服务器地址
        pagination:true,  //启用分页
        toolbar:"#ToolBar",  //工具栏
        rownumbers:true,  //显示行号
        //singleSelect:true,  //实现单行选择
        pageList:[3,6,9,15,20], //设置每页大小
        pageSize:3, //每页三条
        columns:[[
            {field:'ck',checkbox:true,width:100,align:'left'},
            {field:'id',title:'编号',width:100,align:'left'},
            {field:'title',title:'标题',width:100,align:'left'},
            {field:'price',title:'价格',width:100,align:'left'},
            {field:'pubdate',title:'发布日期',width:100,align:'left'},
            {field:'contact',title:'联系方式',width:100,align:'left'},
            {field:'floorage',title:'面积',width:100,align:'left'},
            {field:'dname',title:'区域名称',width:100,align:'left'},
            {field:'sname',title:'街道名称',width:100,align:'left'},
            {field:'tname',title:'类型',width:100,align:'left'},
            {field:'opt',title:'操作',width:100,align:'left',
                formatter:function(value,row,index){
                    //发送同步请求
                    // return "<a href=\"delDistrict?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    //发送异步请求Ajax
                    return "<a href=\"javascript:houseno("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">未审核a</a>";

                }}
        ]]
    });
});
/*打开添加的对话框*/
function Add() {
    $("#AddDialog").dialog("open")
}
/*打开修改的对话框*/
function upd() {
    var selectRows=$("#dg").datagrid('getSelections'); //返回数组
    if(selectRows.length==1){
        $('#UpdDialog').dialog("open")
        //获取当前行的数据：获取数据，查询数据库获取单行数据，返回json对象

        $.post("getSingleType",{"id":selectRows[0].id},function (data) {
            $("#updDialogForm").form('load',data);//将对象还原表单
        },"json")

    }else {
        $.messager.alert('提示框','您选中多行或行','warn')
    }


}

/*实现区域添加*/
function SaveDialog(){
    //传统使用ajax实现添加
    //1.获取表单对象的数据 2.使用ajax或者post发送异步请求 3处理回调函数

    //使用easyUi提交表单
    // call 'submit' method of form plugin to submit the form
    $('#addDialogForm').form('submit', {
        url:"addType",
        success:function(data){ //注意：返回的是json字符串
            //将json字符串转化为json对象
            var  data=$.parseJSON(data)
            if (data.result==1){
                //关闭对话框
                $("#AddDialog").dialog("close")
                $("#dg").datagrid('reload')
                $.messager.alert('提示框','添加成功！','info')
            }else{
                $.messager.alert('提示框','添加失败！','error')
            }
        }
    });
}
/*实现区域修改*/
function SaveUpdDialog(){
    $('#updDialogForm').form('submit', {
        url:"updType",
        success:function(data){ //注意：返回的是json字符串
            //将json字符串转化为json对象
            var  data=$.parseJSON(data)
            if (data.result==1){
                //关闭对话框
                $("#UpdDialog").dialog("close")
                //实现datagrid的刷新
                $('#dg').datagrid('reload');
                //$.messager.alert('提示框','修改成功！','info')
            }else{
                $.messager.alert('提示框','修改失败！','error')
            }
        }
    });
}

/*实现删除功能*/
function DelType(obj) { //传的的是编号
    //发送异步请求实现删除
    $.messager.confirm('提示框','您真的要删除吗，我不能离开你',function (r) {
        if(r){
            $.post("delType",{"id":obj},function (data) {
                //将json字符串转化为json对象
                if (data.result==1){
                    //实现datagrid的刷新
                    $('#dg').datagrid('reload');
                }else{
                    $.messager.alert('提示框','删除失败','error')
                }
            },"json")
        }else {
            $.messager.alert('提示框','想好再点，可以吗！','error')
        }
    })


}
/*关闭对话框*/
function CloseUpdDialog() {
    $("#UpdDialog").dialog("close")
}
function CloseAddDialog() {
    $("#AddDialog").dialog("close")
}

//批量删除区域
function DeleteMoreById() {
    //获取选中行
    var  SelectRows =$("#dg").datagrid('getSelections');
    if(SelectRows==0){
        $.messager.alert('提示框','您还没有选中行','info');
        //return;
    }else {
        //确认删除
        $.messager.confirm('提示框','你真的想把我删除掉吗？',function (flag) {
            if(flag){
                var value="";
                for (var i=0;i<SelectRows.length;i++) {
                    value=value+SelectRows[i].id+",";
                }
                value=value.substring(0,value.length-1);
                $.post("delMoreType",{"id":value},function (data) {
                    if (data.result>0){
                        //实现datagrid的刷新
                        $('#dg').datagrid('reload');
                    }else{
                        $.messager.alert('提示框','删除失败','error')
                    }
                },"json")
            }
        })
    }

}
/*实现Datagird的搜索功能*/
function search() {
    //实现搜索查询
    //load重新加载，它会将查询条件，随着页码，页大小
    //$("#dg").datagrid("load","")
    var s_name=$("#s_name").val();
    var s_tel=$("#s_tel").val();

    $("#dg").datagrid("load",{"name":s_name,"telephone":s_tel})
}

function houseno(id) {

    //发送异步请求到服务器
    $.post("houseNo",{"id":id},function (data) {
        if(data.result>0){
            $("#dg").datagrid("reload");
        }else {
            $.messager.alert('提示框','审核失败！','info')
        }
    },"json")
}