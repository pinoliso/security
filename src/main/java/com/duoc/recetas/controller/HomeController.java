package com.duoc.recetas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duoc.recetas.model.Recipe;

@Controller
public class HomeController {
    
    private List<Recipe> recipes = new ArrayList<>();
    
    public HomeController() {
        recipes.add(new Recipe("Ensalada César", "Americana", "Lechuga, Pollo, Aderezo César", "Estados Unidos", "Fácil", "https://via.placeholder.com/300", "/recipes/1"));
        recipes.add(new Recipe("Tacos al Pastor", "Mexicana", "Carne de cerdo, Piña, Tortillas", "México", "Fácil", "https://via.placeholder.com/300", "/recipes/2"));
        recipes.add(new Recipe("Spaghetti Carbonara", "Italiana", "Pasta, Panceta, Queso", "Italia", "Medio", "https://via.placeholder.com/300", "/recipes/3"));
    }
    @GetMapping("/")
    public String root(@RequestParam(required=false, defaultValue="Seguridad y Calidad en el Desarrollo") String name, Model model) {
        model.addAttribute("recipes", recipes);
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/home")
    public String home(@RequestParam(required=false, defaultValue="Seguridad y Calidad en el Desarrollo") String name, Model model) {
        model.addAttribute("recipes", recipes);
        model.addAttribute("name", name);
        return "home";
    }


}
