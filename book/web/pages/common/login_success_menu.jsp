<%--
  Created by IntelliJ IDEA.
  User: 和运气碰碰
  Date: 2020/11/28
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临岳麓书城</span>

    <%--用户登陆以后才显示我的订单和注销菜单--%>
    <c:if test="${not empty sessionScope.user}">
        <a href="pages/order/order.jsp">我的订单</a>
        <a href="./user?action=logout">注销</a>&nbsp;&nbsp;
    </c:if>
    <a href="./index.jsp">返回</a>
</div>