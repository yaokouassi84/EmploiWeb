<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulaire Pays</title>
</head>
<body>
	<form action="addPays" method="post">
		<label for="nomPays">Nom du pays :</label> <input type="text"
			id="nomPays" name="nomPays" required><br>
		<br> <label for="continent">Continent :</label> <input
			type="text" id="continent" name="continent" required><br>
		<br> <label for="population">Population :</label> <input
			type="number" id="population" name="population" required><br>
		<br> <label for="codeIndicatif">Code Indicatif :</label> <input
			type="number" id="codeIndicatif" name="codeIndicatif" required><br>
		<br> <label for="langue">Langue :</label> <input type="text"
			id="langue" name="langue" required><br>
		<br> <input type="submit" value="Ajouter">
		</form>
</body>
</html>