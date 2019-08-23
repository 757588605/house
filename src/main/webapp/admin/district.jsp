
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>区域管理</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript" src="js/district.js"></script>
</head>
<body>
<%--显示区域--%>
<table id="dg">


</table>
<%--工具栏--%>
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:upd()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:DeleteMoreById()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">批量删除</a>
    </div>
</div>

<%--添加的对话框--%>
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true" title="添加对话框">
    <form id="addDialogForm" method="post">
        <table>
            <tr>
                <td>区域名称:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="bname" /></td>
            </tr>
        </table>
    </form>
</div>
<%--修改的对话框--%>
<div id="UpdDialog" class="easyui-dialog" buttons="#UpdDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true" title="修改对话框">
    <form id="updDialogForm" method="post">
        <table>
            <tr>
                <td>区域编号:</td>
                <td><input type="text" readonly style="border: none" class="easyui-validatebox" required
                           name="id" id="dname" /></td>
            </tr>
            <tr>
                <td>区域名称:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="cname" /></td>
            </tr>

        </table>
    </form>
</div>

<%--定义保存对话框中的按钮--%>
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseAddDialog()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<%--定义更新对话框中的按钮--%>
<div id="UpdDialogButtons">
    <a href="javascript:SaveUpdDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">更新</a>
    <a href="javascript:CloseUpdDialog('')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<%--显示街道的窗口--%>
<div id="showStreetDialog"  class="easyui-dialog" buttons="#UpdDialogButtons"
     style="width: 500px; height: 350px; padding: 10px 20px;" closed="true" title="街道显示对话框">
   <table id="streetdg"></table>

</div>
</body>
</html>
