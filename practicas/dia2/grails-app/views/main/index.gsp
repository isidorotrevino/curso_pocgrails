<html>
<head>
	<title>Pantalla Main</title>
	<meta name="layout" content="especial"/>
</head>
<body>
HOLA MUNDO!!<br/>
	<table>
		<tr>
			<th>Id</th>
			<th>Resultado</th>
		</tr>
		<g:each in="${lista}" var="lst">
			<tr>	
				<td>${lst['id']}</td>
				<td>${lst['resultado']}</td>
			</tr>
		</g:each>		
	</table>
</body>
</html>
