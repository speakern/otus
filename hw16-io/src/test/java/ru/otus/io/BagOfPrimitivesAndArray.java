package ru.otus.io;

import java.util.Arrays;
import java.util.Objects;

public class BagOfPrimitivesAndArray{
    private final int value1;
    private final String value2;
    private final int value3;
    private final int[] array;

    public BagOfPrimitivesAndArray(int value1, String value2, int value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.array = new int[] {12, 20};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagOfPrimitivesAndArray that = (BagOfPrimitivesAndArray) o;
        return value1 == that.value1 &&
                value3 == that.value3 &&
                Objects.equals(value2, that.value2) &&
                Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(value1, value2, value3);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        return "BagOfPrimitivesAndArray{" +
                "value1=" + value1 +
                ", value2='" + value2 + '\'' +
                ", value3=" + value3 +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}
