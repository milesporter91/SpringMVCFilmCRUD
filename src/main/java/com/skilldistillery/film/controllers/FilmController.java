package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;
	
	@RequestMapping(path = { "home.do", "/" })
	private String goHome(Model model) {
		return "home";
	}
	
	@RequestMapping(path = "GetFilmById.do")
	private ModelAndView getFilm(@RequestParam("filmid") int filmId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmdetails");
		Film foundFilm = filmDao.findFilmById(filmId);
		mv.addObject("film", foundFilm);
		return mv;
	}
	
	@RequestMapping(path = "GetFilmByKeyword.do")
	private ModelAndView getFilm(@RequestParam("keyword") String keyword) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmdetails");
		List<Film> foundFilms = filmDao.findFilmByKeyword(keyword);
		mv.addObject("films", foundFilms);
		return mv;
	}
}
