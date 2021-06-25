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
				alert($(this).parent().parent().find("td:first").text());
				/*
				 *confirm() 是确认提示框函数，参数就是它的提示内容
				 * 它有两个按钮，分别是确认、取消
				 * 点击确认就返回true，点击取消返回false
				 * return false 阻止元素的默认行为即不提交请求
				 * return true 允许元素的默认行为即可以提交请求
				 */

				//在事件的function函数中，有一个this对象，这个this对象是当前正在响应事件的dom对象
				return confirm("确定要删除【"+$(this).parent().parent().find("td:first").text()+"】？")
			});
		});
	</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="./static/img/logo.png"/>
			<span class="wel_word">图书管理系统</span>
		<%--静态包含  manager模块管理菜单--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
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
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<%--开始分页，静态包含分页条--%>
		<%@include file="/pages/common/page_bar.jsp"%>
	</div>

	<%--	静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>