$(function () {
    $('#dg').datagrid({
        toolbar: "#ToolBar",  //显示工具栏
        title:"区域信息",
        url:'getDistrict',  //服务器地址
        pagination:true,  //启用分页
        pageList:[3,6], //设置每页大小
        pageSize:3, //每页三条
        // singleselect:true,
        columns:[[
            {field:'cb',checkbox:'true'},
            {field:'id',title:'编号',width:100,align:'left'},
            {field:'name',title:'区域名称',width:100,align:'left'},
            {field:'opt',title:'操作',width:100,align:'left',formatter:function (value,row,index) {
                    return "<a href='javascript:DelDistrict("+row.id+")'>删除</a>|<a href='javascript:openShowStreetDialog("+row.id+")'>查看街道</a>";
                }
            }
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

        $.post("getSingleDistrict",{"id":selectRows[0].id},function (data) {
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
        url:"addDistrict",
        success:function(data){ //注意：返回的是json字符串
            //将json字符串转化为json对象
            var  data=$.parseJSON(data)
            if (data.result==1){
                //关闭对话框
                $("#AddDialog").dialog("close")
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
        url:"updDistrict",
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
function DelDistrict(obj) { //传的的是编号
    //发送异步请求实现删除
    $.messager.confirm('提示框','您真的要删除吗，我不能离开你',function (r) {
        if(r){
            $.post("delDistrict",{"id":obj},function (data) {
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
                $.post("delMoreDistrict",{"id":value},function (data) {
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

/*打开街道显示的窗口*/
function openShowStreetDialog(id) {
    //打开窗口
$("#showStreetDialog").dialog("open").dialog("setTitle","街道明细");

//显示当前区域下的街道
    $('#streetdg').datagrid({
        toolbar: "#ToolBar",  //显示工具栏
        title:"街道信息",
        url:'getStreetByDid?id='+id,  //服务器地址
        pagination:true,  //启用分页
        pageList:[3,6], //设置每页大小
        pageSize:3, //每页三条
        // singleselect:true,
        columns:[[
            {field:'cb',checkbox:'true'},
            {field:'id',title:'编号',width:100,align:'left'},
            {field:'name',title:'街道名称',width:100,align:'left'},
            {field:'opt',title:'操作',width:100,align:'left',formatter:function (value,row,index) {
                    return "<a href='javascript:DelDistrict("+row.id+")'>删除</a>";
                }
            }
        ]]
    });
}