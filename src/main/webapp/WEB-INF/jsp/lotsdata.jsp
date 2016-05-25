<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>加载海量点</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<style type="text/css">
body, html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

#map {
	width: 100%;
	height: 100%;
}
</style>
<jsp:include page="./js.jsp"></jsp:include>
<script src="${basePath }resources/js/console/index.js"></script>
<jsp:include page="./header.jsp"></jsp:include>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=Y4luUSoc9rtwB91M0wmFnQk8ZRc9XkHb"></script>
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/data/points-sample-data.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
</head>
<body>
	<input id="filename" type="text" style="width: 600px;">文件路径
	</input>
	<button id="getdata">获取数据</button>
	<button id="ranging">测距</button>
	<button id="clear">清除</button>
	<div id="map"></div>
	<script type="text/javascript">
		$().ready(function() {
			var map = new BMap.Map("map", {}); // 创建Map实例
    	 	map.addControl(new BMap.MapTypeControl()); 
    	 	map.centerAndZoom("西安", 20);// 初始化地图,设置中心点坐标和地图级别
    	 	map.enableScrollWheelZoom();//启用滚轮放大缩小
    	 	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    		var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
    		map.addControl(top_left_control);        
    		map.addControl(top_left_navigation); 
    	 	var myDis = new BMapLib.DistanceTool(map);
			map.addEventListener("load",function(){
				myDis.open();  //开启鼠标测距
				//myDis.close();  //关闭鼠标测距大
			});
			
			$("#getdata").click(function() {
				myDis.open();
			});
			$("#clear").click(function() {
				map.clearOverlays();
			});
			$("#getdata").click(function() {
				var filename = $("#filename").text();
				filename=$("#filename").val();
				if(filename!=null){
					$.ajax({
					     type: 'GET',
					     data:{filename:filename},
					     url: "getData.html",
					     success:
					    	 function(datas) {  
					    	 	var speeds = datas.speeds;
					    		var transData = datas.transData;//百度地图转换经纬度
					    		var length = 0;
					    		if(transData.length == speeds.length){
					    			length = transData.length;
					    		}
					    		
								var point = new BMap.Point(transData[0].x,transData[0].y);
															
								map.centerAndZoom(point, 30);// 初始化地图,设置中心点坐标和地图级别
								if (document.createElement('canvas').getContext) { // 判断当前浏览器是否支持绘制海量点
									var points = []; // 添加海量点数据
									for (var i = 0; i < length; i++) {
										points.push(new BMap.Point(transData[i].x,transData[i].y));
									}
									
									var polyline = new BMap.Polyline(points, {
										strokeColor : "black",
										strokeWeight : 2,
										strokeOpacity : 0.5
									})
									map.addOverlay(polyline);
									var step = 50;
									for(var i = 0 ;i<length/step ; i++){
										var point1  = new BMap.Point(transData[i*step].x,transData[i*step].y);
										var point2 = new BMap.Point(transData[i*step].x+speeds[i*step].velEast/20000,transData[i*step].y+speeds[i*step].velNorth/20000);
										var speed = Math.sqrt(Math.pow(speeds[i*step].velEast,2)+Math.pow(speeds[i*step].velNorth,2));
										var color = "";
										if(speed<2.7){
											color = "chartreuse";
										}else if(speed <5.5 && speed >=2.7){
											color = "lawngreen";
										}else if(speed <8.3 && speed >=5.5){
											color = "greenyellow";
										}else if(speed <11.1 && speed >=8.3){
											color = "gold";
										}else if(speed <13.8 && speed >=11.1){
											color = "red";
										}else if(speed <16.7 && speed >=13.8){
											color = "darkred";
										}else if(speed >= 16 && speed <19.4){
											color = "";
										}else if(speed >= 19.4){
											color = "";
										}
										var polylineSpeed = new BMap.Polyline(
											[point1,point2], 
											{
											strokeColor : color,
											strokeWeight : 2,
											strokeOpacity : 0.5
										})
										map.addOverlay(polylineSpeed);
										
										
									}
								} else {
									alert('请在chrome、safari、IE8+以上浏览器查看本示例');
								}
					    	},
	
					    dataType: 'json'
	
					});
				}else{
					alert("未能找到文件")
				}
				
			});
		});
		
		
	</script>
</body>
</html>

