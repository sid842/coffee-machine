package com.sibhasin.coffee.machine.model;

import java.util.concurrent.locks.Lock;

/**
 * This class represents specific ingredient contained inside the machine.
 * @author sibhasin
 */
public class Ingredient {
  private String name;
  private int quantity;

  public Ingredient(String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
