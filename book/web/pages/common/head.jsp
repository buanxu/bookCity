 <%--
  Created by IntelliJ IDEA.
  User: 和运气碰碰
  Date: 2020/11/28
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--动态获取base标签--%>
<%
    String basePath=request.getScheme()
            + "://"
            +request.getServerName()
            + ":"
            +request.getServerPort()
            +request.getContextPath()
            +"/";
    request.setAttribute("basePath", basePath);
%>

<%--<%=basePath%>--%>

<%--base标签  固定相对路径跳转的结果--%>
<%--<base href="http://localhost:8080/book/">--%>
<base href=<%=basePath%>>

<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>