<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--只负责请求转发，向后台请求并由控制器转发到真正的index.jsp  --%>
<jsp:forward page="/client/clientController?action=page"></jsp:forward>