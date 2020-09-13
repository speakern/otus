package ru.otus.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BagOfPrimitivesAndArray {
    private final int value1;
    private final String value2;
    private final int value3;
    private final List<BagOfPrimitives> bagOfPrimitivesList;

    BagOfPrimitivesAndArray(int value1, String value2, int value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.bagOfPrimitivesList = new ArrayList<>();
        this.bagOfPrimitivesList.add(new BagOfPrimitives(22, "test", 10, 12.2));
        this.bagOfPrimitivesList.add(new BagOfPrimitives(22, "test", 20, 23.12121212));
        this.bagOfPrimitivesList.add(new BagOfPrimitives(22, "test", 30, 24.323233));
    }

    @Override
    public String toString() {
        return "BagOfPrimitives{" +
                "value1=" + value1 +
                ", value2='" + value2 + '\'' +
                ", value3=" + value3 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagOfPrimitivesAndArray that = (BagOfPrimitivesAndArray) o;
        return value1 == that.value1 &&
                value3 == that.value3 &&
                Objects.equals(value2, that.value2) &&
                Objects.equals(bagOfPrimitivesList, that.bagOfPrimitivesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2, value3, bagOfPrimitivesList);
    }
}
