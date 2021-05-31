<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<%--	静态包含base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
<br/>
		<div id="header">
			<img class="logo_img" alt="" src="./static/img/logo.png" >
			<span class="wel_word">编辑图书</span>

<%--			静态包含  manager模块管理菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>>
		</div>
		
		<div id="main">
			<br/>
			<c:if test="${param.id==null}"><p style="font-size: x-large" align="center">添加图书信息</p></c:if>
			<c:if test="${param.id!=null}"><p style="font-size: x-large" align="center">修改图书信息</p></c:if>
			<form action="manager/bookController" method="get">
				<%--通过判断当前id是否为空来确定是修改还是保存操作，为空是保存，非空为修改--%>
				<input type="hidden" name="action" value="${empty param.id ? "add":"update"}"/>
				<input type="hidden" name="id" value="${requestScope.book.id}"/>
				<%--获取从book_manager.jsp的添加图书链接传过来的当前页码--%>
				<input type="hidden" name="pageNo" value="${param.pageNo}"/>
				<%--获取从book_manager.jsp的添加图书链接传过来的当前页显示了几条数据--%>
				<input type="hidden" name="curPageCounts" value="${param.curPageCounts}"/>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>

		</div>
		<%--	静态包含页脚--%>
		<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>