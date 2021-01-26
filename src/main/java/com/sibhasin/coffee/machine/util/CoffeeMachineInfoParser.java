package com.sibhasin.coffee.machine.util;


import com.sibhasin.coffee.machine.model.Beverage;
import com.sibhasin.coffee.machine.model.Ingredient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class is mainly used by clients to fetch relevant info from the json structure as defined.
 * @author sibhasin
 */
public class CoffeeMachineInfoParser {

  private static final String MACHINE = "machine";
  private static final String OUTLETS = "outlets";
  private static final String COUNTS = "count_n";
  private static final String TOTAL_ITEMS_QUANTITY = "total_items_quantity";
  private static final String BEVERAGES = "beverages";

  public static JSONObject getCoffeeMachineData(String filePath) throws Exception {
    JSONParser jsonParser = new JSONParser();

    FileReader reader = new FileReader(filePath);
    //Read json files
    JSONObject json  = (JSONObject)jsonParser.parse(reader);;

    return (JSONObject) json.get(MACHINE);
  }

  public static int getNumberOfOutletsInCoffeeMachine(JSONObject jsonObject) {
    JSONObject outlets = (JSONObject) jsonObject.get(OUTLETS);
    Long count = (Long) outlets.get(COUNTS);
    return count.intValue();
  }

  public static List<Beverage> getBeverages(JSONObject jsonObject) {
    List<Beverage> beverages = new ArrayList<>();
    JSONObject beveragesJson = (JSONObject)jsonObject.get(BEVERAGES);

    Set beveragesKeySet = beveragesJson.keySet();
    Iterator beveragesKeyIter = beveragesKeySet.iterator();
    while(beveragesKeyIter.hasNext()) {
      String beverageKey = (String) beveragesKeyIter.next();

      JSONObject ingredientsJson = (JSONObject)beveragesJson.get(beverageKey);

      beverages.add(new Beverage(beverageKey, getIngredientsFromJson(ingredientsJson)));
    }

    return beverages;
  }

  private static List<Ingredient> getIngredientsFromJson(JSONObject ingredientsJson) {
    List<Ingredient> ingredients = new ArrayList<>();
    Set ingredientSet = ingredientsJson.keySet();
    Iterator ingredientIter = ingredientSet.iterator();
    while(ingredientIter.hasNext()) {
      String ingredientKey = (String)ingredientIter.next();
      Long val = (Long) ingredientsJson.get(ingredientKey);
      ingredients.add(new Ingredient(ingredientKey, val.intValue()));
    }

    return ingredients;
  }


  public static List<Ingredient> getIngredientsAvailable(JSONObject jsonObject) {
    JSONObject ingredientsJson = (JSONObject)jsonObject.get(TOTAL_ITEMS_QUANTITY);
    return getIngredientsFromJson(ingredientsJson);
  }
}
