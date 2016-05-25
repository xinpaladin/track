<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet"
	href="${basePath }resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="${basePath }resources/css/bootstrap-table.css">
<link rel="stylesheet"
	href="${basePath }resources/css/bootstrap-theme.min.css" />
<script src="${basePath }resources/js/jquery-1.12.4.min.js"></script>
<script src="${basePath }resources/js/jquery-1.12.4.js"></script>
<script src="${basePath }resources/js/bootstrap.min.js"></script>
<script src="${basePath }resources/js/bootstrap-table.js"></script>
<!-- put your locale files after bootstrap-table.js -->
<script src="${basePath }resources/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript">
	var base = '${basePath }';
</script>

