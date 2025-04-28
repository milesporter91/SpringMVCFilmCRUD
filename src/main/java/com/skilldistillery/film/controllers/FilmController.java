package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	// GET addFilm form
	 @RequestMapping(path = "addFilmForm.do", method = RequestMethod.GET)
	    public String showAddFilmForm(Model model) {
	        model.addAttribute("film", new Film());
	        return "addFilm"; 
	    }
	 

    // POST form submission and add new film
    @RequestMapping(path = "addFilm.do", method = RequestMethod.POST)
    public String addFilm(@ModelAttribute("film") Film newFilm, Model model) {
        Film addedFilm = filmDao.addFilm(newFilm);

        if (addedFilm == null) {
            model.addAttribute("error", "Failed to add film. Please try again.");
            return "addFilm"; // Return to the form with error
        } else {
            model.addAttribute("film", addedFilm);
            return "filmdetails"; // Redirect to the film details page, if insert successfull 
        }
    }

    // DELETE FILM
    
    @RequestMapping(path="deleteFilm.do", method= RequestMethod.POST)
    public void deleteFilm(Film filmToDelete) {
    	filmDao.deleteFilm(filmToDelete);
    	if (filmToDelete == null) {
    		System.err.println("Film deleted.");
    	}
    }
    
}
