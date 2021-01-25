package com.sibhasin.coffee.machine.model;

import java.util.List;

/**
 * This class is used to represent a specific beverage.
 * It mainly holds the list of ingredients used to make the beverage.
 * @author sibhasin
 */
public class Beverage {
  private String name;
  private List<Ingredient> requiredIngredients;

  public Beverage(String name, List<Ingredient> requiredIngredients) {
    this.name = name;
    this.requiredIngredients = requiredIngredients;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Ingredient> getRequiredIngredients() {
    return requiredIngredients;
  }

  public void setRequiredIngredients(List<Ingredient> requiredIngredients) {
    this.requiredIngredients = requiredIngredients;
  }
}
