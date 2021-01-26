package com.sibhasin.coffee.machine.service;

import com.sibhasin.coffee.machine.model.Beverage;

/**
 * This class encapsulates the task to fetch beverage based on the ingredients available.
 * @author sibhasin
 */
public class FetchBeverageTask implements Runnable {

  private Beverage beverage;
  private IngredientsService service;

  public FetchBeverageTask(Beverage beverage, IngredientsService service) {
    this.beverage = beverage;
    this.service = service;
  }

  @Override
  public void run() {
    System.out.println(service.prepareBeverageWithIngredients(beverage));
  }
}
