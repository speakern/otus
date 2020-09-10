package ru.otus.io.entries;

/**
 * @author sergey
 * created on 23.11.18.
 */
public class Cat extends Animal {

  protected String name;

  public Cat(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Cat";
  }

  public String getMyau() {
    return "Myauuu";
  }

}
