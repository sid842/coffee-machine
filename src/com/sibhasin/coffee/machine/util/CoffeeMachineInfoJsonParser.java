package com.sibhasin.coffee.machine.util;


import com.sibhasin.coffee.machine.model.Beverage;
import com.sibhasin.coffee.machine.model.Ingredient;
import org.json.JSONObject;

import java.util.List;

/**
 * This class is mainly used by clients to fetch relevant info from the json structure as defined.
 * @author sibhasin
 */
public class CoffeeMachineInfoJsonParser {

  public static final String OUTLETS = "outlets";
  public static final String TOTAL_ITEMS_QUANTITY = "total_items_quantity";
  public static final String BEVERAGES = "beverages";

  public static int getNumberOfOutletsInCoffeeMachine(JSONObject jsonObject) {
    return 0;
  }

  public static List<Beverage> getBeverages(JSONObject jsonObject) {
    return null;
  }

  public static List<Ingredient> getIngredients(JSONObject jsonObject) {
    return null;
  }
}
