package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
  public Film findFilmById(int filmId);
  public Actor findActorById(int actorId);
  public List<Actor> findActorsByFilmId(int filmId);
  public List<Film> findFilmByKeyword(String keyword);
  public List<String> findCategoriesByFilmId(int filmid);
  public List<String> findRentalCopiesByFilmId(int filmId);
  public Film addFilm(Film film);
  public Film updateFilm(Film film);
  public void deleteFilm(Film film);
}
