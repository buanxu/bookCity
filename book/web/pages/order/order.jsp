<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<style type="text/css">
		h1 {
			text-align: center;
			margin-top: 200px;
		}
	</style>
	<%--	静态包含base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			//当用户签收后，修改订单状态为已签收
			$("a.receive").click(function () {
				//获取当前要修改状态的订单的id
				var orderId = $(this).parent().parent().find("td").eq(0).text();
				//确认修改订单？
				if (confirm("确认签收编号为【"+orderId+"】的订单？")){
					//确认修改以后再发送ajax请求
					$.getJSON("${basePath}orderController","action=updateOrderStatus&operate=receive&orderId="+orderId,function (data) {
					});
					$(this).parent().parent().find("td").eq(3).text("已签收");
					$(this).parent().parent().find("td").eq(4).text("已签收");
				}
			});
		});
	</script>
</head>
<body>
<br/>
<div id="header">
			<img class="logo_img" alt="" src="./static/img/logo.png" >
			<span class="wel_word">我的订单</span>

		<%--			静态包含登录成功后的菜单页面--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		<p align="center" style="font-size: x-large;font-family: 微软雅黑">用户【${sessionScope.user.username}】的全部订单</p>

		<table style="margin-top: 50px">
			<tr>
				<td>订单编号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>签收</td>
				<td>详情</td>
			</tr>

			<%--订单为空--%>
			<c:if test="${empty requestScope.orders}">
				<tr>
					<td colspan="5">当前没有订单，<a href="index.jsp" >先去浏览商品，再创建订单吧</a></td>
				</tr>
			</c:if>
			<%--订单非空--%>
			<c:if test="${not empty requestScope.orders}">
				<c:forEach var="order" items="${requestScope.orders}">
					<tr>
						<td>${order.orderId}</td>
						<td width="50px">${order.createTime}</td>
						<td>${order.price}</td>
						<c:choose >
							<c:when test="${order.status==0}"><td>未发货</td></c:when>
							<c:when test="${order.status==1}"><td>已发货</td></c:when>
							<c:when test="${order.status==2}"><td>已签收</td></c:when>
						</c:choose>
						<c:choose >
							<c:when test="${order.status==0}"><td>无法签收</td></c:when>
							<c:when test="${order.status==1}"><td><a href="javascript:"  class="receive" style="text-decoration:none">确认签收</a></td></c:when>
<%--							<c:when test="${order.status==1}"><td><a href="orderController?action=updateOrderStatus&orderId=${order.orderId}&userId=${order.userId}&operate=receive"  class="receive" style="text-decoration:none">确认签收</a></td></c:when>--%>
							<c:when test="${order.status==2}"><td>已签收</td></c:when>
						</c:choose>
						<td><a href="orderController?action=findOrderItem&orderId=${order.orderId}">查看详情</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
	
	</div>

	<%--	静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>