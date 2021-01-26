package com.sibhasin.coffee.machine.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class represents specific ingredient contained inside the machine.
 * We implement the comparable interface so that we are able to define some order between
 * different ingredients.
 * This helps us decide an order while locking different ingredients.
 * @author sibhasin
 */
public class Ingredient implements Comparable<Ingredient> {

  private static final int THRESHOLD_QUANTITY = 10;

  private String name;
  private int quantity;
  private boolean isLow;
  private Lock lock;

  public Ingredient(String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
    this.setLow();
    this.lock = new ReentrantLock();
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
    this.setLow();
  }

  public boolean isLow() {
    return isLow;
  }

  public void setLow() {
    isLow = this.getQuantity() < THRESHOLD_QUANTITY;
  }

  public void lock() {
    lock.lock();
  }

  public void unlock() {
    lock.unlock();
  }

  @Override
  public int compareTo(Ingredient other) {
    return name.compareTo(other.getName());
  }
}
