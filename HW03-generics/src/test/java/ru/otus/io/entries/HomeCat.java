package ru.otus.io.entries;

/**
 * @author sergey
 * created on 23.11.18.
 */
public class HomeCat extends Cat {

  public HomeCat(String name) {
    super(name);
  }

  public void sitOnBoss() {

  }

  @Override
  public String toString() {
    return "HomeCat, name:" + name;
  }
}
