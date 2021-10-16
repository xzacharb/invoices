package com.xzacharb.coresvc.common;

public class TupleData<T,V extends Number> {
    T someObject;
    V numericObject;

    public TupleData(T someObject, V numericObject) {
        this.someObject = someObject;
        this.numericObject = numericObject;
    }

    public T getFirstObject() {
        return someObject;
    }

    public V getSecondNumericObject() {
        return numericObject;
    }
}
