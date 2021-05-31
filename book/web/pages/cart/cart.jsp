<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--	静态包含base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			//给删除绑定单击事件
			$("a.deleteBtn").click(function () {
				var name=$(this).parent().parent().find("td:first").text()
				return confirm("确定要删除【"+name+"】?");
			});

			//给清空购物车绑定单击事件
			$("#clearBtn").click(function () {
				return confirm("确定要清空购物车？");
			});

			//修改商品数量
			//当输入框的内容发生改变时触发change事件
			$(".updateCount").change(function () {
				//获取商品名称
				var name=$(this).parent().parent().find("td:first").text();
				//获取商品id
				var id=$(this).attr("bookId");
				//获取商品数量
				var counts = this.value;
				if ( confirm("确定要将【" + name + "】商品的数量修改为：" + counts + " ?") ){
					//修改了商品的数量以后直接请求后台
					location.href="${basePath}cartController?action=updateCount&id="+id+"&counts="+counts;
				}else {//不修改商品的数量则恢复商品原来的数量
					// defaultValue属性是表单项Dom对象的属性。它表示input默认的value属性值。
					this.value=this.defaultValue;
				}
			});
		});
	</script>
</head>
<body >
	<br/>
	<div id="header">
			<img class="logo_img" alt="" src="./static/img/logo.png" >
			<span class="wel_word">购物车</span>

		<%--			静态包含登录成功后的菜单页面--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<%--购物车为空，给出提示信息--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5">当前购物车为空，<a href="index.jsp" >先去浏览商品吧</a></td>
				</tr>
			</c:if>
			<%--从session域中取数据，当前购物车非空则显示购物车中的商品信息--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach var="entry" items="${sessionScope.cart.items}">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" bookId="${entry.value.id}" type="text"
								   value="${entry.value.counts}" style="width:40px"/>
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteBtn" href="cartController?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCounts}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearBtn" href="cartController?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderController?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	
	</div>

	<%--	静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>