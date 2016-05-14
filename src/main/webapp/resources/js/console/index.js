$().ready(function() {
		$("#send").click(function() {
			var path = $("#path").val();
			$.ajax({
				type : 'GET',
				data : {
					path : path
				},
				url : "insertData.html",
				success : function(result) {
					if(result.succes){
						alert("success");
					}else{
						alert(result.message);
					}
				}
			});
		});

	});