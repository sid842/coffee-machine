package com.sibhasin.coffee.machine.service;

import com.sibhasin.coffee.machine.model.Beverage;
import com.sibhasin.coffee.machine.model.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is managing all the ingredients present in the machine.
 * @author sibhasin
 */
public class IngredientsService {

  enum IngredientsStatus {
    UNAVAILABLE("not available"),
    INSUFFICIENT("not sufficient"),
    AVAILABLE("available");

    IngredientsStatus(String status) {
      this.status = status;
    }

    public String getStatus() {
      return status;
    }

    private String status;
  }

  private Map<String, Ingredient> ingredientMap;

  public IngredientsService() {
    ingredientMap = new HashMap<>();
  }

  public void addIngredients(Ingredient ingredient) {
    ingredientMap.put(ingredient.getName(), ingredient);
  }

  public String prepareBeverageWithIngredients(Beverage beverage) {
    StringBuilder missingIngredient = new StringBuilder();
    IngredientsStatus status = checkIfRequiredIngredientsPresent(beverage, missingIngredient);

    if(status == IngredientsStatus.AVAILABLE) {
     return beverage.getName() + " is prepared.";
    }

    return beverage.getName() + " cannot be prepared because item " +
                missingIngredient.toString() + " is " +  status.getStatus();
  }

  private IngredientsStatus checkIfRequiredIngredientsPresent(Beverage beverage, StringBuilder missingIngredient) {

    /**
     * This sort is necessary so as to avoid deadlock.
     * We acquire locks in increasing order of ingredients.
     */
    Collections.sort(beverage.getRequiredIngredients());

    List<Ingredient> ingredientsModified = new ArrayList<>();
    List<Ingredient> requiredIngredients = beverage.getRequiredIngredients();

    IngredientsStatus status = IngredientsStatus.AVAILABLE;

    for(Ingredient requiredIngredient: requiredIngredients) {
      if(ingredientMap.containsKey(requiredIngredient.getName())) {
        Ingredient presentIngredient = ingredientMap.get(requiredIngredient.getName());
        presentIngredient.lock();
        if(presentIngredient.getQuantity() < requiredIngredient.getQuantity()) {
          status = IngredientsStatus.INSUFFICIENT;
          presentIngredient.unlock();
        } else {
          ingredientsModified.add(presentIngredient);
        }
      } else {
        status = IngredientsStatus.UNAVAILABLE;
      }

      if(status != IngredientsStatus.AVAILABLE) {
        missingIngredient.append(requiredIngredient.getName());
        for(Ingredient ingredient: ingredientsModified)
          ingredient.unlock();

        return status;
      }

    }

    for(int i = 0; i < ingredientsModified.size(); ++i) {
      Ingredient currentIngredient = ingredientsModified.get(i);
      currentIngredient.setQuantity(currentIngredient.getQuantity()-requiredIngredients.get(i).getQuantity());
      currentIngredient.unlock();
    }

    return IngredientsStatus.AVAILABLE;
  }

  public List<Ingredient> getIngredientsWithLowQuantity() {
    List<Ingredient> lowQuantityIngredients = new ArrayList<>();
    Set<Map.Entry<String, Ingredient>> ingredientsMapEntries = ingredientMap.entrySet();
    for(Map.Entry<String, Ingredient> ingredientEntry: ingredientsMapEntries) {
      Ingredient ingredient = ingredientEntry.getValue();
      if(ingredient.isLow())
        lowQuantityIngredients.add(ingredient);
    }

    return lowQuantityIngredients;
  }
}
