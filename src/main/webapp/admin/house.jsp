
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
    <script language="JavaScript" src="js/house.js"></script>
</head>
<body>
<%--显示区域--%>
<table id="dg">


</table>
<%--工具栏--%>
<div id="ToolBar">
    <div style="height: 40px;">
      <a
            href="javascript:DeleteMoreById()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">批量审核</a>
    </div>
    <div>
        标题：<input type="text" id="s_name" name="name">
        区域名称：<input type="text" id="s_tel1" name="telephone">
        街道名称：<input type="text" id="s_tel2" name="telephone">
        类型名称：<input type="text" id="s_tel3" name="telephone">
        <a href="javascript:search()" class="easyui-linkbutton"
           iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

</body>
</html>
