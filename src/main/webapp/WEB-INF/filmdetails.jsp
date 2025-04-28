<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Site</title>
</head>
<body>
	<c:choose>
		<c:when test="${ empty film and empty films }">
			<h1>Film not found</h1>
		</c:when>
		<c:when test="${not empty films }">
		<ul>
			<c:forEach var="film" items="${ films}">
			<li>
			<h1>${ film.title }</h1>
			<h1>Plot: ${ film.description }</h1>
			<h2>Rating: ${ film.rating }</h2>
			<h2>Release Year: ${ film.releaseYear }</h2>
			<h2>Language ID: ${ film.languageId }</h2>
			<h2>Language: ${ film.language }</h2>
			<h2>Categories: ${ film.categories }</h2>
			<h2>Special Features: ${ film.specialFeatures }</h2>
			<h2>Actors: ${ film.actorList }</h2>
			<h2>Length: ${ film.length }</h2>
			<h2>Rental Duration: ${ film.rentalDuration }</h2>
			<h2>Rental Rate: ${ film.rentalRate }</h2>
			<h2>Replacement Cost: ${ film.replacementCost }</h2>
			<h2>Rentals Copies: ${ film.rentalCopiesList }</h2>
			</li>
			</c:forEach>
			</ul>
		
		</c:when>
		<c:otherwise>
			<h1>${ film.title }</h1>
			<h1>Plot: ${ film.description }</h1>
			<h2>Film ID: ${ film.id }</h2>
			<h2>Rating: ${ film.rating }</h2>
			<h2>Release Year: ${ film.releaseYear }</h2>
			<h2>Language ID: ${ film.languageId }</h2>
			<h2>Language: ${ film.language }</h2>
			<h2>Categories: ${ film.categories }</h2>
			<h2>Special Features: ${ film.specialFeatures }</h2>
			<h2>Actors: ${ film.actorList }</h2>
			<h2>Length: ${ film.length }</h2>
			<h2>Rental Duration: ${ film.rentalDuration }</h2>
			<h2>Rental Rate: ${ film.rentalRate }</h2>
			<h2>Replacement Cost: ${ film.replacementCost }</h2>
			<h2>Rentals Copies: ${ film.rentalCopiesList }</h2>
			<form action="deleteFilm.do" method="POST">
			<input type="hidden" name="filmId" value="${film.id }" />
			<input type="submit" value ="Delete this film" />
			</form>
		</c:otherwise>
	</c:choose>



</body>
</html>