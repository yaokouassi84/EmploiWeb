<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Liste des Pays</title>
<style>
.europe {
	color: green;
	font-weight: bold;
}

.bouton-vert {
	background-color: green;
	color: white;
	border: none;
	padding: 5px 10px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	cursor: pointer;
}
</style>
</head>
<body>
	<h1>Liste des Pays</h1>
	<table border="2">
		<thead>
			<tr>
				<!-- <th>ID</th> -->
				<th>Nom du Pays</th>
				<th>Continent</th>
				<th>Population</th>
				<th>Code Indicatif</th>
				<th>Langue</th>
				<th>Suppression</th>
				<th>Modifiaction</th>
				<th>Détails Pays</th>
				<!-- Nouvelle colonne pour les actions -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listePays}" var="pays">
				<tr>
					<%-- <td>${pays.id}</td> --%>
					<td>${pays.nomPays}</td>
					<%-- <td>${pays.continent}</td> --%>
					<td class="${pays.continent == 'Europe' ? 'europe' : ''}">${pays.continent}</td>
					<td>${pays.population}</td>
					<td>${pays.codeIndicatif}</td>
					<td>${pays.langue}</td>
					<td>
						<!-- Bouton pour supprimer le pays -->
						<form action="supprimerPays" method="post"
							onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer ce pays ?');">
							<input type="hidden" name="id" value="${pays.id}"> 
							<input class="bouton-vert" type="submit" value="Supprimer">
						</form>
					</td>
					<td>
						<%-- <a href="/detailPays?id=${pays.id}">Détails</a> --%> <!-- Bouton pour accéder à la page de détails du pays -->
						<form action="form_Modif" method="get">
							<input type="hidden" name="idPays" value="${pays.id}"> 
							<input type="submit" value="Modifier Pays">
								
						</form>
					</td>
					<td>
						<%-- <a href="/detailPays?id=${pays.id}">Détails</a> --%> <!-- Bouton pour accéder à la page de détails du pays -->
						<form action="recupererPays" method="get">
							<input type="hidden" name="idPays" value="${pays.id}"> 
							<input type="submit" value="Détails Pays">
								<!-- <button type="button" class="btn btn-info">Détails Pays</button> -->
						</form>
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
