package ru.otus.io;

import java.util.Objects;

public class BagOfPrimitivesAndObject {
    private final int value1;
    private final String value2;
    private final int value3;
    private final double value4;
    private BagOfPrimitives bagOfPrimitives;
    private EmptyClass emptyClass;

    BagOfPrimitivesAndObject(int value1, String value2, int value3, double value4) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.bagOfPrimitives = new BagOfPrimitives(22, "test", 20, 23.12121212, 'e');
        this.emptyClass = new EmptyClass();
    }

    public BagOfPrimitivesAndObject(int value1, String value2, int value3, double value4, BagOfPrimitives bagOfPrimitives) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.bagOfPrimitives = bagOfPrimitives;
    }

    @Override
    public String toString() {
        return "BagOfPrimitivesAndObject{" +
                "value1=" + value1 +
                ", value2='" + value2 + '\'' +
                ", value3=" + value3 +
                ", value4=" + value4 +
                ", bagOfPrimitives=" + bagOfPrimitives +
                ", emptyClass=" + emptyClass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagOfPrimitivesAndObject that = (BagOfPrimitivesAndObject) o;
        return value1 == that.value1 &&
                value3 == that.value3 &&
                Double.compare(that.value4, value4) == 0 &&
                Objects.equals(value2, that.value2) &&
                Objects.equals(bagOfPrimitives, that.bagOfPrimitives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2, value3, value4, bagOfPrimitives);
    }
}
