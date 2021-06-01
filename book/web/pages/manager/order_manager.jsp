<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--	静态包含base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			//当用户签收后，修改订单状态为已签收
			$("a.sendOut").click(function () {
				//获取当前要修改状态的订单的id
				var orderId = $(this).parent().parent().find("td").eq(1).text();
				//确认修改订单？
				if (confirm("确定给编号为【"+orderId+"】的订单发货？")){
					//确认修改以后再发送ajax请求
					$.getJSON("${basePath}orderController","action=updateOrderStatus&operate=sendOut&orderId="+orderId,function (data) {
					});
					$(this).parent().parent().find("td").eq(4).text("已发货");
					$(this).parent().parent().find("td").eq(5).text("待签收");
				}
			});
		});
	</script>
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
			<form action="orderController" method="post" style="margin-left: 210px;margin-top: 10px">
				<span style="margin-left: -700px">
					<input type="hidden" name="action" value="findUserOrdersByUsername"/>
					用户名：<input  type="text" name="username" value="${requestScope.username}" style="width: 200px">
					<input type="submit" value="查询该用户所有订单" style="width: 140px"/>
				</span>
			</form>
			<form action="orderController" method="post" style="margin-right: 140px;margin-top: -25px">
				<span style="margin-right: -710px">
					<input type="hidden" name="action" value="findOrderByOrderId"/>
					订单编号：<input  type="text" name="orderId" value="${requestScope.orderId}" style="width: 200px">
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
			<%--管理员点击订单管理时，查询出所有用户的所有订单并进行分页--%>
			<c:if test="${requestScope.flag=='backstageOrdersPage'}">
				<c:set var="items" value="${requestScope.page.items}"></c:set>
			</c:if>
			<%--管理员点击查询按钮时，查询相应订单但不进行分页--%>
			<c:if test="${requestScope.flag=='notBackstageOrdersPage'}">
				<c:set var="items" value="${requestScope.userOrders}"></c:set>
			</c:if>
			<%--上面两个if判断是为了获取遍历对象，抽取重复代码，因为除了遍历对象不一样其他的都一样--%>
			<c:forEach var="userOrder" items="${items}">
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
						<c:when test="${userOrder.status==0}"><td><a href="javascript:"  class="sendOut" style="text-decoration:none">点击发货</a></td></c:when>
						<c:when test="${userOrder.status==1}"><td>待签收</td></c:when>
						<c:when test="${userOrder.status==2}"><td>已签收</td></c:when>
					</c:choose>
					<td><a href="orderController?action=findOrderItem&orderId=${userOrder.orderId}" style="text-decoration: none">查看详情</a></td>
				</tr>
			</c:forEach>
		</table>
		<br/>
		<br/>
		<br/>

		<%--静态包含分页条，只有管理员点击订单管理时，查询出所有用户的所有订单才进行分页，点击查询按钮时不进行分页--%>
		<c:if test="${requestScope.flag=='backstageOrdersPage'}">
			<%@include file="/pages/common/orderPage_bar.jsp"%>
		</c:if>
	</div>

	<%--	静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>