package com.duoc.recetas.model;

public class Recipe {
    private String name;
    private String cuisineType;
    private String ingredients;
    private String country;
    private String difficulty;
    private String imageUrl;
    private String detailUrl;

    public Recipe(String name, String cuisineType, String ingredients, String country, String difficulty, String imageUrl, String detailUrl) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.ingredients = ingredients;
        this.country = country;
        this.difficulty = difficulty;
        this.imageUrl = imageUrl;
        this.detailUrl = detailUrl;
    }

    // Getters y Setters
    public String getName() { return name; }
    public String getCuisineType() { return cuisineType; }
    public String getIngredients() { return ingredients; }
    public String getCountry() { return country; }
    public String getDifficulty() { return difficulty; }
    public String getImageUrl() { return imageUrl; }
    public String getDetailUrl() { return detailUrl; }
}

