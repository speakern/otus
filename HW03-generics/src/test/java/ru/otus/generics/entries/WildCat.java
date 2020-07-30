package ru.otus.generics.entries;

/**
 * @author sergey
 * created on 23.11.18.
 */
public class WildCat extends Cat {


  public WildCat(String name) {
    super(name);
  }

  @Override
  public String toString() {
    return "WildCat, name:" + name;
  }
}
