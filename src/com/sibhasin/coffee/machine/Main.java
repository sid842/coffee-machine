package com.sibhasin.coffee.machine;

import com.sibhasin.coffee.machine.model.Beverage;
import com.sibhasin.coffee.machine.model.Ingredient;
import com.sibhasin.coffee.machine.service.CoffeeMachineService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
      CoffeeMachineService service = new CoffeeMachineService(2);
      List<Ingredient> ingredientsPresent = new ArrayList<>();
      ingredientsPresent.add(new Ingredient("A", 10));
      ingredientsPresent.add(new Ingredient("B", 10));
      ingredientsPresent.add(new Ingredient("C", 10));

      service.addIngredientsInMachine(ingredientsPresent);

      List<Ingredient> ingredientsRequired1 = new ArrayList<>();
      ingredientsRequired1.add(new Ingredient("A", 6));
      ingredientsRequired1.add(new Ingredient("B", 4));

      Beverage beverage1 = new Beverage("b1", ingredientsRequired1);

      List<Ingredient> ingredientsRequired2 = new ArrayList<>();
      ingredientsRequired2.add(new Ingredient("C", 5));
      ingredientsRequired2.add(new Ingredient("B", 66));


      Beverage beverage2 = new Beverage("b2", ingredientsRequired2);

      service.getBeverage(beverage1);
      service.getBeverage(beverage2);

      service.switchOff();

    }
}
