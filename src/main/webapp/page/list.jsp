<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
  <META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <LINK rel=stylesheet type=text/css href="../css/style.css">
  <META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" src="../admin/js/jquery-1.8.3.js"></script>
  <script language="JavaScript">
    $(function(){  //加载事件
      //使用异步加载类型
      $.post("getType",null,function(data){
        for(var i=0;i<data.length;i++){
          //创建option
          var node=$("<option value="+data[i].id+">"+data[i].name+"</option>");
          $("#typeId").append(node);
        }

        //设置选中项
        $("#typeId").val(${condition.typeid});
      },"json");


      //使用异步加载区域
      $.post("getDistrict",null,function(data){
        for(var i=0;i<data.length;i++){
          //创建option
          var node=$("<option value="+data[i].id+">"+data[i].name+"</option>");
          $("#district_id").append(node);
        }

        //设置区域的选中项
        $("#district_id").val(${condition.districtid});

        //加载街道
        //alert($("#district_id").val());
        loadStreet( $("#district_id").val());
      },"json");

      //给区域列表添加改变事件
      $("#district_id").change(function(){
        loadStreet($(this).val());  //重新加载街道
      });
    });

    function loadStreet(did){
      //发送异步请求获取区域下的街道
      $.post("getStreetByDid",{"id":did},function (data) {
        //清空原有数据
        $("#street_id>option:gt(0)").remove();
        for(var i=0;i<data.length;i++){
          //使用$()工厂函数创建标签
          var node=$("<option value="+data[i].id+">"+data[i].name+"</option>");
          //将创建的标签添加下拉列表
          $("#street_id").append(node);
        }
        $("#street_id").val(${condition.streetid});  //设置街道的选中项
      },"json");
    }

    //提交搜索条件的表单  去分页
    function goPage(pageNum){
      //首先设置页码
      $("#savepage").val(pageNum);
      //提交表单
      $("#sform").submit();   //js提交表单
    }


  </script>


</HEAD>
<BODY>
<DIV id=header class=wrap>
  <DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
  <FORM id=sform method=post action=getBroswerHouse>
    <input  name="page" id="savepage" type="hidden" value="1">
    <div>
      标题：<INPUT class=text type=text name=title value="${condition.title}">
      类型:<SELECT id="typeId" name=typeid>
      <OPTION selected value="">不限</OPTION>
    </SELECT>
      区域:<SELECT id="district_id" name=districtid>
      <OPTION selected value="">不限</OPTION>
    </SELECT>
      街道:<SELECT name=streetid id="street_id">
      <OPTION selected value="">不限</OPTION>
    </SELECT>
      价格:<INPUT class=text type=text name=startPrice value="${condition.startPrice}">-<INPUT class=text value="${condition.endPrice}" type=text name=endPrice>元
      <LABEL class=ui-blue><INPUT value=搜索房屋 type=submit name=search></LABEL>
    </div>
  </FORM>
</DIV>
<DIV class="main wrap">
  <c:if test="${! empty pageInfo.list}">
    <TABLE class=house-list>
      <TBODY>

      <c:forEach items="${pageInfo.list}" var="h">
        <TR>
          <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="http://localhost:81/${h.path}" width="100" height="75" alt=""></a></span></TD>
          <TD>
            <DL>
              <DT><A href="details.jsp" target="_blank">${h.title}</A></DT>
              <DD>${h.dname}==${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
          <TD class=house-type>${h.tname}</TD>
          <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD>
        </TR>
      </c:forEach>

      </TBODY></TABLE>

    <DIV class=pager>
      <UL>
        <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
        <LI><A href="javascript:goPage(${pageInfo.prePage==0?1:pageInfo.prePage})">上一页</A></LI>
        <LI><A href="javascript:goPage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage})">下一页</A></LI>
        <LI><A href="javascript:goPage(${pageInfo.pages})">末页</A></LI></UL><SPAN
            class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV>
  </c:if>

  <c:if test="${empty pageInfo.list}">
    <center  style="color: red; font-size: 24px;">暂无出租房信息</center>
  </c:if>
</DIV>
<DIV id=footer class=wrap>
  <DL>
    <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
    <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
