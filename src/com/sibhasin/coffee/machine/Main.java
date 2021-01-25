package com.sibhasin.coffee.machine;

import com.sibhasin.coffee.machine.model.Beverage;
import com.sibhasin.coffee.machine.model.Ingredient;
import com.sibhasin.coffee.machine.service.CoffeeMachineService;
import com.sibhasin.coffee.machine.util.CoffeeMachineInfoParser;
import org.json.simple.JSONObject;

import java.util.List;

public class Main {

    private static final String JSON_FILE = "./resources/coffee-machine-test-1.json";

    public static void main(String[] args) {

      JSONObject coffeeMachineData = null;

      try {
        coffeeMachineData = CoffeeMachineInfoParser.getCoffeeMachineData(JSON_FILE);
      } catch (Exception e) {
        e.printStackTrace();
        return;
      }


      int numberOfOutlets = CoffeeMachineInfoParser.getNumberOfOutletsInCoffeeMachine(coffeeMachineData);

      if(numberOfOutlets == 0) {
        System.out.println("Coffee machine not operable.");
        return;
      }

      CoffeeMachineService service = new CoffeeMachineService(numberOfOutlets);

      List<Ingredient> ingredientsPresent = CoffeeMachineInfoParser.getIngredientsAvailable(coffeeMachineData);
      service.addIngredientsInMachine(ingredientsPresent);

      List<Beverage> beverages = CoffeeMachineInfoParser.getBeverages(coffeeMachineData);

      for(Beverage beverage: beverages) {
        service.getBeverage(beverage);
      }

      service.switchOff();

    }
}
