package com.duoc.recetas.controller;

import com.duoc.recetas.model.Recipe; // Asegúrate de importar el modelo
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RecipeController {

    private List<Recipe> recipes = new ArrayList<>();
    
    public RecipeController() {
        recipes.add(new Recipe("Ensalada César", "Americana", "Lechuga, Pollo, Aderezo César", "Estados Unidos", "Fácil", "https://via.placeholder.com/300", "/recipes/1"));
        recipes.add(new Recipe("Tacos al Pastor", "Mexicana", "Carne de cerdo, Piña, Tortillas", "México", "Fácil", "https://via.placeholder.com/300", "/recipes/2"));
        recipes.add(new Recipe("Spaghetti Carbonara", "Italiana", "Pasta, Panceta, Queso", "Italia", "Medio", "https://via.placeholder.com/300", "/recipes/3"));
    }
    @GetMapping("/recipes") 
    public String searchRecipes(Model model, 
                       @RequestParam(required = false) String recipeName, 
                       @RequestParam(required = false) String cuisineType, 
                       @RequestParam(required = false) String ingredients, 
                       @RequestParam(required = false) String country, 
                       @RequestParam(required = false) String difficulty) {
        
        // Filtrar recetas según los parámetros de búsqueda
        List<Recipe> filteredRecipes = recipes.stream()
            .filter(recipe -> (recipeName == null || recipeName.isEmpty() || recipe.getName().toLowerCase().contains(recipeName.toLowerCase())) &&
                              (cuisineType == null || cuisineType.isEmpty() || recipe.getCuisineType().toLowerCase().contains(cuisineType.toLowerCase())) &&
                              (ingredients == null || ingredients.isEmpty() || recipe.getIngredients().toLowerCase().contains(ingredients.toLowerCase())) &&
                              (country == null || country.isEmpty() || recipe.getCountry().toLowerCase().contains(country.toLowerCase())) &&
                              (difficulty == null || difficulty.isEmpty() || recipe.getDifficulty().equalsIgnoreCase(difficulty)))
            .collect(Collectors.toList());

        model.addAttribute("recipes", filteredRecipes); // Agrega la lista filtrada de recetas al modelo
        return "recipes"; // Asegúrate de que este sea el nombre correcto de la vista
    }

    @GetMapping("/recipes/{id}") 
    public String getRecipe(Model model, @PathVariable int id) {
        if (id >= 1 && id < recipes.size() + 1) {
            model.addAttribute("recipe", recipes.get(id - 1));
            return "recipe"; 
        }
        return "404"; 
    }
}

