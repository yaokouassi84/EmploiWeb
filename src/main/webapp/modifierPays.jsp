<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
</head>
<body>
	<h2>Modifier un pays</h2>
    
    <form action="modifierPays" method="post">
        <!-- Champ caché pour l'ID du pays -->
        <input type="hidden" name="id" value="${pays.id}">
          
		<label for="nomPays">Nom du Pays:</label> 
		<input type="text" id="nomPays" name="nomPays" value="${pays.nomPays}" required="required"><br>

		<label for="continent">Continent:</label> <input type="text"
			id="continent" name="continent" value="${pays.continent}" required="required"><br>

		<label for="population">Population:</label> <input type="text"
			id="population" name="population" value="${pays.population}" required="required"><br>

		<label for="codeIndicatif">Code Indicatif:</label> <input type="text"
			id="codeIndicatif" name="codeIndicatif" value="${pays.codeIndicatif}" required="required"><br> 
			<label for="langue">Langue:</label> 
			<input type="text" id="langue" name="langue" value="${pays.langue}" required="required"><br>
			<input type="submit" value="Modifier">
	</form>
</body>
</html>