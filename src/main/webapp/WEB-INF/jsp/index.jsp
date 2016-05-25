<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="./js.jsp"></jsp:include>
<script src="${basePath }resources/js/console/index.js"></script>
<jsp:include page="./header.jsp"></jsp:include>

</head>
<body>
	文件路径：
	<input id="path" name="path" />
	<button id="send">发送</button>

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">Panel heading</div>

		<div id="toolbar">
			<button id="remove" class="btn btn-danger" disabled>
				<i class="glyphicon glyphicon-remove"></i> 删除
			</button>
		</div>
		<!-- Table -->
		<table id="itemTable" class="table">
			<thead>
				<tr>
					<th></th>
					<th>驾驶员</th>
					<th>驾驶行为</th>
					<th>舒适性</th>
					<th>速度性</th>
					<th>平稳性</th>
					<th>总体评价</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<!-- Single button -->
	<div class="btn-group">
		<button type="button" class="btn btn-default dropdown-toggle"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			Action <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><a href="#">10</a></li>
			<li><a href="#">20</a></li>
			<li><a href="#">50</a></li>
			<li><a href="#">100</a></li>
		</ul>
	</div>
	<nav>
		<ul class="pagination">

		</ul>
	</nav>
</body>
</html>