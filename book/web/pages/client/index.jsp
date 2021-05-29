<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%--	静态包含base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			//给加入购物车按钮绑定单击事件，请求后台的cartController
			$("button.addToCart").click(function () {
				//$(this)是把 dom对象转换成jquery对象，然后获取对象的bookId属性的值
				var bookId=$(this).attr("bookId");
				//刷新整个页面
				//location.href="${basePath}cartController?action=addItem&id="+bookId;

				var className = $("#lastName").attr("class");
				//用ajax请求向购物车里添加数据，局部更新
				$.getJSON("${basePath}cartController?","action=ajaxAddItem&id="+bookId+"&className="+className,function (data) {
					$("#itemCounts").text("您的购物车中有"+data.cartTotalCounts+"件商品");
					if (className=="isNull"){
						$("#isNull01").text("您刚刚将");
						$("#lastName").text(data.lastName);
						$("#isNull02").text("加入到了购物车");
					}
					if (className=="notNull"){
						$("#lastName").text(data.lastName);
					}
				})
			});
		});
	</script>
</head>
<body>
	<br/>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.png" >
			<span class="wel_word">网上书城</span>
			<div>
				<%--如果用户没登录，就显示【登录 和注册的菜单】--%>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/regist.jsp">注册</a>
				</c:if>
				<%--用户登录了，就显示用户信息--%>
				<c:if test="${not empty sessionScope.user}">
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临岳麓书城</span>
					<a href="orderController?action=findOrder&userId=${sessionScope.user.id}">我的订单</a>
					<a href="./user?action=logout">注销</a>&nbsp;&nbsp;
				</c:if>
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/clientController" method="post">
					<input type="hidden" name="action" value="pageByPrice"/>
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 —
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<%--购物车为空时的输出信息--%>
			<c:if test="${empty sessionScope.cart.items}">
				<div style="text-align: center">
					<span id="itemCounts" ></span>
					<div>
						<span id="isNull01" ></span>
						<span style="color: red" id="lastName" class="isNull">当前购物车为空</span>
						<span id="isNull02" ></span>
					</div>
				</div>
			</c:if>
			<%--购物车非空时的输出信息--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<div style="text-align: center">
					<span id="itemCounts" >您的购物车中有${sessionScope.cart.totalCounts}件商品</span>
					<div>
						您刚刚将 <span id="lastName" style="color: red" class="notNull">${sessionScope.lastName}</span> 加入到了购物车中
					</div>
				</div>
			</c:if>

			<c:forEach var="book" items="${requestScope.page.items}">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="static/img/default.jpg" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div  class="book_add">
							<button bookId="${book.id}" class="addToCart">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<%--开始分页，静态包含分页条--%>
		<%@include file="/pages/common/page_bar.jsp"%>
	</div>

<%--	静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>