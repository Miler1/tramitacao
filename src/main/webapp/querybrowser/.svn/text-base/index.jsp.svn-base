<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>QueryBrowser</title>
	<link rel="stylesheet" href="style.css" type="text/css" >
</head>

	
<body>
	
	<table id="hor-minimalist-a">
	
		<thead>
	   		<tr>
	       		<th scope="col">QueryBrowser</th>
       		</tr>
	    </thead>
	
		<tr><td><input type="radio" id="sqlType" name="sqlType" value="1" checked>Fazer Consulta SQL</td></tr>
		<tr><td><input type="radio" id="sqlType" name="sqlType" value="2">Fazer INSERT, UPDATE ou DELETE (1 ou vários scripts simultâneos)</td></tr>
		
		<tr><td><textarea id="sql" name="sql" style="width: 750px; height: 200px; margin-bottom: 5px"></textarea></td></tr>
		<tr><td>
			<a href="#" class="button" id="executeButton">Executar</a>
			<img id="loadingGif" src="loading.gif" width="20px" height="20px" style="display: none;">
		</td></tr>
		
	</table>
	
	<div id="responseDiv"></div>
	
</body>
</html>

<script>
	var baseURL = "<% out.write( request.getContextPath() );%>";
</script>

<script type="text/JavaScript" charset="utf-8" src="jquery-1.7.2.js"></script>
<script type="text/JavaScript" charset="utf-8" src="jquery.tmpl.min.js"></script>
<script type="text/JavaScript" charset="utf-8" src="script.js"></script>
<%@include file="templates.html" %>
