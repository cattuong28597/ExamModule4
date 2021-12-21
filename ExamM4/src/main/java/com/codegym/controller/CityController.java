package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.service.city.CityService;
import com.codegym.service.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/city")
    public ModelAndView showCity() {
        ModelAndView modelAndView = new ModelAndView("/city/list");
        Iterable<City> cities = cityService.findAll();
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/city/create")
    public ModelAndView showCreateCity() {
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city", new City());
        modelAndView.addObject("countries", countryService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
        public ModelAndView createCity(@ModelAttribute("city") City city) {
            ModelAndView modelAndView = new ModelAndView("/city/create");
            cityService.save(city);
            return modelAndView;
    }

    @GetMapping("/city/delete/{id}")
    public ModelAndView showDeleteCity(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/city/delete");
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteCity(@PathVariable Long id) {
        cityService.remove(id);
        ModelAndView modelAndView = new ModelAndView("city/list");
        return modelAndView;
    }
}
