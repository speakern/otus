package ru.otus.io;

import java.util.Objects;

public class BagOfPrimitives {
    private final Integer value1;
    private final String value2;
    private final int value3;
    private final double value4;
    private final char value5;

    public BagOfPrimitives(Integer value1, String value2, int value3, double value4, char value5) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
    }

    @Override
    public String toString() {
        return "BagOfPrimitives{" +
                "value1=" + value1 +
                ", value2='" + value2 + '\'' +
                ", value3=" + value3 +
                ", value4=" + value4 +
                ", value5=" + value5 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagOfPrimitives that = (BagOfPrimitives) o;
        return value3 == that.value3 &&
                Double.compare(that.value4, value4) == 0 &&
                value5 == that.value5 &&
                Objects.equals(value1, that.value1) &&
                Objects.equals(value2, that.value2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2, value3, value4, value5);
    }
}
