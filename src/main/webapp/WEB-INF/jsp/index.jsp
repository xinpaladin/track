<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="./js.jsp"></jsp:include>
<script src="${basePath }resources/js/console/index.js"></script>
<jsp:include page="./header.jsp"></jsp:include>
<script type="text/javascript">
	
</script>

</head>
<body>
	文件路径：
	<input id="path" name="path" />
	<button id="send">发送</button>
	
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">Panel heading</div>

		<!-- Table -->
		<table id="table" class="table">
			<tr>
				<th>驾驶员</th>
				<th>驾驶动作</th>
				<th>舒适性</th>
				<th>平顺性</th>
				<th>稳定性</th>
				<th>总体评价</th>
				<th>操作</th>
			</tr>
		</table>
	</div>
	
	<nav>
	<ul class="pagination">
		<li><a href="#" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>
		<li><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>
		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
	</nav>
</body>
</html>