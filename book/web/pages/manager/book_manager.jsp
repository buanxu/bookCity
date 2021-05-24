<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--	静态包含base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			//给所有class属性为deleteClass的a标签绑定单击事件，用于删除的确认操作提示
			$("a.deleteClass").click(function () {
				/*
				 *confirm() 是确认提示框函数，参数就是它的提示内容
				 * 它有两个按钮，分别是确认、取消
				 * 点击确认就返回true，点击取消返回false
				 * return false 阻止元素的默认行为即不提交请求
				 * return true 允许元素的默认行为即可以提交请求
				 */

				//在事件的function函数中，有一个this对象，这个this对象是当前正在响应事件的dom对象
				return confirm("确定要删除【"+$(this).parent().parent().find("td:first").text()+"】？")
			})
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="./static/img/logo.png" >
			<span class="wel_word">图书管理系统</span>
		<%--			静态包含  manager模块管理菜单--%>
		<%@include file="/pages/common/manager_menu.jsp"%>>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>


			<c:forEach  items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<%--修改了某条数据后仍然显示当前页--%>
					<td><a href="manager/bookController?action=getBookInfo&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<%--通过a标签和class属性来查找所有带删除操作的a标签--%>
					<%--每次删除一条数据后，最后一页显示的数据个数可能会发生变化，删除某条数据后仍然显示当前页--%>
					<td><a class="deleteClass" href="manager/bookController?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}&curPageCounts=${requestScope.page.items.size()}&pageTotal=${requestScope.page.pageTotal}">删除</a></td>
				</tr>
			</c:forEach>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<%--每次添加一条数据后，都跳到最后一页以显示新添加的数据--%>
				<td><a href="./pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}&curPageCounts=${requestScope.page.items.size()}">添加图书</a></td>
			</tr>

		</table>
		<div id="page_nav">
			<%--页码大于1才显示--%>
			<c:if test="${requestScope.page.pageNo>1}">
				<a href="manager/bookController?action=page&pageNo=1">首页</a>
				<a href="manager/bookController?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>

				<%--开始输出页码--%>
				<%--需求:显示5个连续的页码，而且当前页码在中间。
					除了当前页码之外，每个页码都可以点击跳到指定页|--%>
				<c:choose>
					<%--第一种情况，总的页码小于等于5，页码范围是：1-总页码  --%>
					<c:when test="${requestScope.page.pageTotal<=5}">
						<%--设置起始和结束索引，然后让foreach执行--%>
						<c:set var="begin" value="1"></c:set>
						<c:set var="end" value="{requestScope.page.pageTotal}"></c:set>
					</c:when>

					<%--第二种情况，总的页码大于5 --%>
					<c:when test="${requestScope.page.pageTotal>5}">
						<%--分三种小情况--%>
						<c:choose>
							<%--小情况1：当前页码为前三个：1、2、3，则页码范围是：1-5--%>
							<c:when test="${requestScope.page.pageNo<=3}">
								<c:set var="begin" value="1"></c:set>
								<c:set var="end" value="5"></c:set>
							</c:when>
							<%--小情况2：当前页码为后：8、9、10，则页码范围是：总页码-4——总页码  --%>
							<c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
								<c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
								<c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
							</c:when>
							<%--小情况3：当前页码为：4-7(4——n-4)中的任意一个，4、5、6、7，则页码范围是：1-5--%>
							<c:otherwise >
								<c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
								<c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
							</c:otherwise>
						</c:choose>
					</c:when>
			</c:choose>
			<%--抽取出遍历输出页码的重复代码--%>
			<c:forEach var="i" begin="${begin}" end="${end}">
				<%--当前页码，只显示不可点击跳转--%>
				<c:if test="${i == requestScope.page.pageNo}">
					【${i}】
				</c:if>
				<%--非当前页码不仅要显示还要可点击跳转--%>
				<c:if test="${i != requestScope.page.pageNo}">
					<a href="manager/bookController?action=page&pageNo=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<%--输出页码结束--%>

			<%-- 如果已经 是最后一页，则不显示下一页，末页 --%>
			<c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
				<a href="manager/bookController?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="manager/bookController?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>

			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCounts}条记录
			到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
				<input id="searchPageBtn" type="button" value="确定">

			<script type="text/javascript">
				$(function () {
					$("#searchPageBtn").click(function () {
						//获取要跳转的页码
						var pageNo = $("#pn_input").val();

						//先对页码进行有效边界检查
						if(pageNo<1 || pageNo>${requestScope.page.pageTotal}){
							alert("要跳转的页码超出了有效范围，请重新输入！");
							return false
						}
						//js提供了一个地址栏对象location，它有一个href属性，可以获取当前浏览器地址栏中的地址，且该属性可读可写
						location.href="${requestScope.basePath}manager/bookController?action=page&pageNo="+pageNo;
					});
				});
			</script>
		</div>
	</div>

	<%--	静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>