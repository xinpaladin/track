$().ready(function(){
    
});
function getItemList(pageNum) {
    var rows = 10;
    $.ajax({
	type : 'GET',
	url : "item/itemList.html",
	data : {
	    pageNum : pageNum,
	    rows : rows
	},
	success : function(result) {
	    arrayLength = result.length;
	    var item = $("#itemTable");
	    if (arrayLength == 0) {

	    } else {
		for (var i = 0; i < result.length; i++) {
		}
	    }
	}

    });
}
$
	.ajax({
	    type : 'GET',
	    dataType : "json",
	    url : "item/getItemCount.html",
	    success : function(result) {

		var pageNums = 0;
		var nums = result;
		if (nums % 10 != 0) {
		    pageNums = parseInt(nums / 10) + 1;
		} else {
		    pageNums = parseInt(nums / 10);
		}
		$(".pagination")
		.append('<li><a onclick="getItemList(1)" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>');
		
		for (var i = 1; i <= pageNums; i++) {
		    $(".pagination").append(
			    "<li><a onclick='getItemList(" + i + ")' >" + i
				    + "</a></li>")
		}
		$(".pagination")
			.append(
				"<li><a onclick='getItemList("
					+ pageNums
					+ ")' aria-label='Next'> <span aria-hidden='true'>&raquo;</span>");
	    },
	    error : function(XMLHttpRequest, error, errorThrown) {

	    }
	});
function initTable() {

}
function getIdSelections() {
   
}

$("#send").click(function() {
    var path = $("#path").val();
    $.ajax({
	type : 'GET',
	data : {
	    path : path
	},
	url : "item/insertData.html",
	success : function(result) {
	    if (result.succes) {
		alert("success");
	    } else {
		alert(result.message);
	    }
	}
    });
});

