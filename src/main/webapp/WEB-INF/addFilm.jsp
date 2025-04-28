<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Film</title>
</head>
<body>
<a href="home.do">Back to Home</a>
<form action="addFilm.do" method="post">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="${film.title}" required>
    <br>
    <label for="description">Description:</label>
    <textarea id="description" name="description" required>${film.description}</textarea>
    <br>
    <label for="releaseYear">Release Year:</label>
    <input type="number" id="releaseYear" name="releaseYear" value="${film.releaseYear}" required>
    <br>
    <label for="languageId">Language ID:</label>
    <input type="number" id="languageId" name="languageId" value="${film.languageId}" required>
    <br>
    <label for="rentalDuration">Rental Duration (days):</label>
    <input type="number" id="rentalDuration" name="rentalDuration" value="${film.rentalDuration}" required>
    <br>
    <label for="rentalRate">Rental Rate:</label>
    <input type="number" step="0.01" id="rentalRate" name="rentalRate" value="${film.rentalRate}" required>
    <br>
    <label for="length">Film Length (minutes):</label>
    <input type="number" id="length" name="length" value="${film.length}" required>
    <br>
    <label for="replacementCost">Replacement Cost:</label>
    <input type="number" step="0.01" id="replacementCost" name="replacementCost" value="${film.replacementCost}" required>
    <br>
    <label for="rating">Rating:</label>
    <select id="rating" name="rating">
        <option value="G" ${film.rating == 'G' ? 'selected' : ''}>G</option>
        <option value="PG" ${film.rating == 'PG' ? 'selected' : ''}>PG</option>
        <option value="PG-13" ${film.rating == 'PG-13' ? 'selected' : ''}>PG-13</option>
        <option value="R" ${film.rating == 'R' ? 'selected' : ''}>R</option>
        <option value="NC-17" ${film.rating == 'NC-17' ? 'selected' : ''}>NC-17</option>
    </select>
    <br>
    <label for="specialFeatures">Special Features:</label>
    <textarea id="specialFeatures" name="specialFeatures">${film.specialFeatures}</textarea>
    <br>
    <button type="submit">Add Film</button>
</form>


<c:if test="${not empty error}">
    <div style="color: red;">${error}</div>
</c:if>

</body>
</html>
