// On ready
configureActionExecuteButton();


function configureActionExecuteButton() {
	
	$("#executeButton").click( function() {
		
		$("#loadingGif").show();
		$("#responseDiv").empty();
		
		var sqlType = $("#sqlType:checked").val();
		var sql = $("#sql").val();
		var data = { sql: sql };
		
		var url = "";
		if (sqlType == 1)
			url = "/query/select.json";
		else if (sqlType == 2)
			url = "/query/dml.json";
		
		$.ajax({
			  url: baseURL + url,
			  type: "POST",
			  dataType: "json",
			  contentType:"application/json; charset=utf-8",
			  data: JSON.stringify( data ),
			  
			  complete: function(response) {
			    $("#loadingGif").hide();
			  },

			  success: function(response) {
				 $("#loadingGif").hide();
				 $("#responseTemplate").tmpl( response ).appendTo("#responseDiv");
			 },

			  error: function() {
			    $("#loadingGif").hide();
			    
			    var response = { message: "Ocorreu um erro na requisição!! Verifique sua conexão com a internet ou se o usuário está autenticado!"};
			    $("#responseTemplate").tmpl( response ).appendTo("#responseDiv");
			  },
			});

		
	} );
}

