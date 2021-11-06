package com.xzacharb.coresvc.impl.model.dto;

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
