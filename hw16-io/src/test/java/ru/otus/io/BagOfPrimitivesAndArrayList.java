package ru.otus.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BagOfPrimitivesAndArrayList {
    private final int value1;
    private final String value2;
    private final int value3;
    private final List<BagOfPrimitives> bagOfPrimitivesList;

    BagOfPrimitivesAndArrayList(int value1, String value2, int value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.bagOfPrimitivesList = new ArrayList<>();
        this.bagOfPrimitivesList.add(new BagOfPrimitives(22, "test", 10, 12.2, 'd'));
        this.bagOfPrimitivesList.add(new BagOfPrimitives(22, "test", 20, 23.12121212, 's'));
        this.bagOfPrimitivesList.add(new BagOfPrimitives(22, "test", 30, 24.323233, 's'));
    }

    @Override
    public String toString() {
        return "BagOfPrimitivesAndArray{" +
                "value1=" + value1 +
                ", value2='" + value2 + '\'' +
                ", value3=" + value3 +
                ", bagOfPrimitivesList=" + bagOfPrimitivesList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagOfPrimitivesAndArrayList that = (BagOfPrimitivesAndArrayList) o;
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
