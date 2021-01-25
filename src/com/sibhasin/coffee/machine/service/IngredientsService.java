package com.sibhasin.coffee.machine.service;

import com.sibhasin.coffee.machine.model.Beverage;
import com.sibhasin.coffee.machine.model.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is managing all the ingredients present in the machine.
 * @author sibhasin
 */
public class IngredientsService {

  private Map<String, Ingredient> ingredientMap;

  public IngredientsService() {
    ingredientMap = new HashMap<>();
  }

  public void addIngredients(Ingredient ingredient) {
    ingredientMap.put(ingredient.getName(), ingredient);
  }

  public boolean getBeverageWithIngredients(Beverage beverage) {
    List<Ingredient> ingredients = getRequiredIngredientsPresent(beverage);
    return !ingredients.isEmpty();
  }

  private List<Ingredient> getRequiredIngredientsPresent(Beverage beverage) {
    List<Ingredient> ingredients = new ArrayList<>();

    boolean sufficientSupplies = true;
    for(Ingredient requiredIngredient: beverage.getRequiredIngredients()) {
      if(ingredientMap.containsKey(requiredIngredient.getName())) {
        Ingredient ingredientPresent = ingredientMap.get(requiredIngredient.getName());
        if(ingredientPresent.getQuantity() < requiredIngredient.getQuantity()) {
          sufficientSupplies = false;
        } else {
          ingredientPresent.setQuantity(ingredientPresent.getQuantity()-requiredIngredient.getQuantity());
          ingredients.add(ingredientPresent);
        }
      } else {
        sufficientSupplies = false;
      }
      if(!sufficientSupplies){
        ingredients.clear();
        break;
      }
    }

    return ingredients;
  }
}
