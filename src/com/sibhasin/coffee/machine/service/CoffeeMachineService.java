package com.sibhasin.coffee.machine.service;

import com.sibhasin.coffee.machine.model.Beverage;
import com.sibhasin.coffee.machine.model.Ingredient;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is provided to the clients to access the coffee machine.
 * It has services as add ingredients and get a beverage.
 * To instantiate we need the number of outlets.
 * @author sibhasin
 */
public class CoffeeMachineService {

  private ExecutorService outlets;
  private IngredientsService service;

  public CoffeeMachineService(int numOutlets) {
    outlets = Executors.newFixedThreadPool(numOutlets);
    service = new IngredientsService();
  }

  public void getBeverage(Beverage beverage) {
    outlets.execute(new FetchBeverageTask(beverage, service));
  }

  public void addIngredientsInMachine(List<Ingredient> newIngredients) {
    for(Ingredient ingredient: newIngredients) {
      service.addIngredients(ingredient);
    }
  }

  public void switchOff() {
    outlets.shutdown();
  }
}
