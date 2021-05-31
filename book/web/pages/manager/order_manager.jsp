<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--	静态包含base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
<br/>
	<div id="header">
			<img class="logo_img" alt="" src="./static/img/logo.png"/>
			<span class="wel_word">订单管理系统</span>
		<%--			静态包含  manager模块管理菜单--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<div align="center">
			<form action="orderController" method="post">
				<span style="margin-left: -700px">
					<input type="hidden" name="action" value="findUserOrdersByUsername"/>
					用户名：<input  type="text" name="username"  style="width: 200px">
					<input type="submit" value="查询用户所有订单" style="width: 120px"/>
				</span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span style="margin-right: -710px">
					<input type="hidden" name="action" value="findOrderByOrderId"/>
					订单编号：<input  type="text" name="orderId" style="width: 200px">
					<input type="submit" value="查询订单" style="width: 70px"/>
				</span>
			</form>
		</div>
		<table style="margin-top: 50px">
			<tr>
				<td>用户名</td>
				<td>订单号</td>
				<td>下单日期</td>
				<td>订单金额</td>
				<td>状态</td>
				<td>发货</td>
				<td>详情</td>
			</tr>
			<c:forEach var="userOrder" items="${requestScope.userOrders}">
				<tr>
					<td>${userOrder.username}</td>
					<td>${userOrder.orderId}</td>
					<td>${userOrder.createTime}</td>
					<td>${userOrder.price}</td>
					<c:choose>
						<c:when test="${userOrder.status==0}"><td>未发货</td></c:when>
						<c:when test="${userOrder.status==1}"><td>已发货</td></c:when>
						<c:when test="${userOrder.status==2}"><td>已签收</td></c:when>
					</c:choose>
					<c:choose>
						<c:when test="${userOrder.status==0}"><td><a href="orderController?action=updateOrderStatus&orderId=${userOrder.orderId}&userId=${userOrder.userId}&operate=sendOut"  class="sendOut" style="text-decoration:none">点击发货</a></td></c:when>
						<c:when test="${userOrder.status==1}"><td>待签收</td></c:when>
						<c:when test="${userOrder.status==2}"><td>已签收</td></c:when>
					</c:choose>
					<td><a href="orderController?action=findOrderItem&orderId=${userOrder.orderId}" style="text-decoration: none">查看详情</a></td>
				</tr>
			</c:forEach>

			<%--<tr>
				<td>nanjun</td>
				<td>78349203329087</td>
				<td>2015.04.23</td>
				<td>90.00</td>
				<td><a href="#">查看详情</a></td>
				<td><a href="#">点击发货</a></td>
			</tr>
			<tr>
				<td>liyumou</td>
				<td>9087657890432</td>
				<td>2015.04.20</td>
				<td>20.00</td>
				<td><a href="#">查看详情</a></td>
				<td>已发货</td>
			</tr>
			<tr>
				<td>xuyan</td>
				<td>678906543987654</td>
				<td>2014.01.23</td>
				<td>190.00</td>
				<td><a href="#">查看详情</a></td>
				<td>等待收货</td>
			</tr>
			<tr>
				<td>xuyan</td>
				<td>678906543987654</td>
				<td>2014.01.23</td>
				<td>190.00</td>
				<td><a href="#">查看详情</a></td>
				<td>等待收货</td>
			</tr>
			<tr>
				<td>xuyan</td>
				<td>678906543987654</td>
				<td>2014.01.23</td>
				<td>190.00</td>
				<td><a href="#">查看详情</a></td>
				<td>等待收货</td>
			</tr>--%>
		</table>
		<br/>
		<br/>
		<br/>
		<%--静态包含分页条--%>
<%--		<%@include file="/pages/common/page_bar.jsp"%>--%>
	</div>

	<%--	静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>