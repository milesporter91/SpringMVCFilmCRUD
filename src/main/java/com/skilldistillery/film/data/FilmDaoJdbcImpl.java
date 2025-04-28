package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDaoJdbcImpl implements FilmDAO {
	String USERNAME = "student";
	String PASSWORD = "student";
	public static final String URL = "jdbc:mysql://localhost:3306/sdvid";

	@Override
	public Film findFilmById(int filmId) {
		Film foundFilm = null;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Prepare statement
			String sql = "SELECT * FROM film f WHERE f.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			List<Actor> actorList = findActorsByFilmId(filmId);
			List<String> categories = findCategoriesByFilmId(filmId);
			List<String> rentalCopiesList = findRentalCopiesByFilmId(filmId);
			String language = findLanguageByFilmId(filmId);
			// Extract results
			ResultSet filmData = pst.executeQuery();
			if (filmData.next()) {
				foundFilm = new Film(filmData.getInt("id"), filmData.getString("title"),
						filmData.getString("description"), filmData.getInt("release_year"),
						filmData.getInt("language_id"), filmData.getInt("rental_duration"),
						filmData.getDouble("rental_rate"), filmData.getInt("length"),
						filmData.getDouble("replacement_cost"), filmData.getString("rating"),
						filmData.getString("special_features"), actorList, language, categories, rentalCopiesList);
			}
			filmData.close();
			pst.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundFilm;

	}

	@Override
	public Actor findActorById(int actorId) {
		Actor foundActor = null;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Prepare statement
			String sql = "SELECT * FROM actor a WHERE a.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, actorId);
			// Extract results
			ResultSet actorData = pst.executeQuery();
			actorData.next();
			foundActor = new Actor(actorData.getInt("a.id"), actorData.getString("a.first_name"),
					actorData.getString("a.last_name"));
			pst.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundActor;
	}

	@Override
	public List<String> findCategoriesByFilmId(int filmId) {
		List<String> categoriesOfFilm = new LinkedList<>();
		String foundCategory = null;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Prepare statement
			String sql = " SELECT c.name " + "FROM category c JOIN film_category fc ON c.id = fc.category_id "
					+ "JOIN film f ON fc.film_id = f.id WHERE f.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			// Extract results
			ResultSet categoryData = pst.executeQuery();
			while (categoryData.next()) {
				foundCategory = categoryData.getString("c.name");
				categoriesOfFilm.add(foundCategory);
			}
			categoryData.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoriesOfFilm;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorsInFilm = new LinkedList<>();
		Actor foundActor = null;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Prepare statement
			String sql = " SELECT a.id, a.first_name, a.last_name "
					+ "FROM actor a JOIN film_actor fa ON a.id = fa.actor_id JOIN film f ON fa.film_id = f.id WHERE f.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			// Extract results
			ResultSet actorData = pst.executeQuery();
			while (actorData.next()) {
				foundActor = new Actor(actorData.getInt("a.id"), actorData.getString("a.first_name"),
						actorData.getString("a.last_name"));
				actorsInFilm.add(foundActor);
			}
			actorData.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorsInFilm;
	}

	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> foundFilms = new LinkedList<>();
		Film foundFilm;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// Prepare statement
			String sql = "SELECT * FROM film f WHERE f.title LIKE ? OR f.description LIKE ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + keyword + "%");
			pst.setString(2, "%" + keyword + "%");
			ResultSet filmData = pst.executeQuery();
			while (filmData.next()) {
				int filmId = filmData.getInt("f.id");
				List<Actor> actorList = findActorsByFilmId(filmId);
				List<String> categories = findCategoriesByFilmId(filmId);
				List<String> filmCopiesList = findRentalCopiesByFilmId(filmId);
				String language = findLanguageByFilmId(filmId);
				foundFilm = new Film(filmData.getInt("id"), filmData.getString("title"),
						filmData.getString("description"), filmData.getInt("release_year"),
						filmData.getInt("language_id"), filmData.getInt("rental_duration"),
						filmData.getDouble("rental_rate"), filmData.getInt("length"),
						filmData.getDouble("replacement_cost"), filmData.getString("rating"),
						filmData.getString("special_features"), actorList, language, categories, filmCopiesList);
				foundFilms.add(foundFilm);
			}
			filmData.close();
			pst.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundFilms;

	}

	public String findLanguageByFilmId(int filmId) {
		String language = null;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Prepare statement
			String sql = " SELECT language.name FROM film JOIN language ON language.id = film.language_id WHERE film.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			// Extract results
			ResultSet languageData = pst.executeQuery();
			while (languageData.next()) {
				language = languageData.getString("name");
			}
			languageData.close();
			pst.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return language;
	}

	public List<String> findRentalCopiesByFilmId(int filmId) {
		List<String> rentalCopiesList = new LinkedList<>();
		String rentalCopy;
		try {
			// Connect to database
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Prepare statement
			String sql = "  SELECT DISTINCT i.id, i.media_condition FROM inventory_item i WHERE i.film_id = ? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			// Extract results
			ResultSet rentalCopyData = pst.executeQuery();
			while (rentalCopyData.next()) {
				rentalCopy = "Rental ID: " + rentalCopyData.getString("i.id") + " Rental Condition: "
						+ rentalCopyData.getString("i.media_condition");
				rentalCopiesList.add(rentalCopy);
			}
			rentalCopyData.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rentalCopiesList;
	}

	@Override
	public Film addFilm(Film newFilm) {
		String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false); // Start transaction
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newFilm.getTitle());
			st.setString(2, newFilm.getDescription());
			st.setInt(3, newFilm.getReleaseYear());
			st.setInt(4, newFilm.getLanguageId());
			st.setInt(5, newFilm.getRentalDuration());
			st.setDouble(6, newFilm.getRentalRate());
			st.setInt(7, newFilm.getLength());
			st.setDouble(8, newFilm.getReplacementCost());
			st.setString(9, newFilm.getRating());
			st.setString(10, newFilm.getSpecialFeatures());
			
			int updateCount = st.executeUpdate();
			System.out.println(updateCount + " film records created.");
			// Now get the auto-generated actor IDs:
			ResultSet keys = st.getGeneratedKeys();
			if (keys.next()) {
				int newFilmId = keys.getInt(1);
				newFilm.setId(newFilmId);
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newFilm;
	}

	@Override
	public Film updateFilm(Film updatingFilm) {
		String sql = "UPDATE film SET  " + "WHERE id = ?;";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false); // Start transaction
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setObject(1, updatingFilm.getTitle());
			st.setObject(2, updatingFilm.getLanguageId());
			int updateCount = st.executeUpdate();
			System.out.println(updateCount + " film updated.");
			// Now get the auto-generated actor IDs:
			ResultSet keys = st.getGeneratedKeys();
			int newFilmId = keys.getInt(1);
			updatingFilm.setId(newFilmId);
			if (keys.next()) {
				System.out.println("New film ID: " + newFilmId);
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatingFilm;
	}

	@Override
	public void deleteFilm(Film filmToDelete) {
		String sql = "DELETE FROM film " + "WHERE film.id = ?;";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false); // Start transaction
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setObject(1, filmToDelete.getId());
			st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to load database driver:");
			e.printStackTrace();
		}
	}
}
