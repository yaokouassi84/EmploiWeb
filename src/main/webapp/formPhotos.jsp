<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	 <h1>Upload d'Image</h1>
    <form action="enregistrerImage" method="post" enctype="multipart/form-data">
        <label for="nom">Nom de l'image:</label>
        <input type="text" id="nom" name="nom"><br><br>
        <label for="file">Sélectionnez une image:</label>
        <input type="file" id="file" name="url1" accept="image/*" required><br>
        <label for="file">Sélectionnez une image:</label>
        <input type="file" id="file" name="url2" accept="image/*" required><br><br>
        <input type="submit" value="Envoyer">
    </form>
</body>
</html>