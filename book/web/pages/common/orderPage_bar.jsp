<%--
  Created by IntelliJ IDEA.
  User: 和运气碰碰
  Date: 2021/5/25
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--此分页是对UserOrder进行分页的--%>

    <%--抽取出重复的分页条代码--%>
    <div id="page_nav" >
    <%--页码大于1才显示--%>
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--开始输出页码--%>
    <%--需求:显示5个连续的页码，而且当前页码在中间。
        除了当前页码之外，每个页码都可以点击跳到指定页|--%>
    <c:choose>
        <%--第一种情况，总的页码小于等于5，页码范围是：1-总页码  --%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <%--设置起始和结束索引，然后让foreach执行--%>
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
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
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--输出页码结束--%>

    <%-- 如果已经 是最后一页，则不显示下一页，末页 --%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页， ${requestScope.page.pageTotalCounts}条记录
    跳转到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
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
                location.href="${requestScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
            });
        });
    </script>
</div>

</body>
</html>
