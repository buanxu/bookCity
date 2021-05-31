<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--	静态包含base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
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
		<br/><br/>
		<p align="center" style="font-size: x-large;font-family: 微软雅黑">编号为【${param.orderId}】的订单中包含的所有商品</p>
		<table style="margin-top: 50px">
			<tr >
				<td>商品名</td>
				<td>商品数量</td>
				<td>商品单价</td>
				<td>商品总金额</td>
			</tr>
			<%--订单非空--%>
			<c:if test="${not empty requestScope.orderItems}">
				<c:forEach var="orderItem" items="${requestScope.orderItems}">
					<tr>
						<td>${orderItem.name}</td>
						<td >${orderItem.counts}</td>
						<td>${orderItem.price}</td>
						<td>${orderItem.totalPrice}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
	
	</div>

	<%--	静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>