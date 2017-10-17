package com.andrey.rxjavaformvalidation;

/**
 * Created by sts on 04.10.17.
 */

public class Car {

    public String mark, model, type;

    public Car()
    {
        mark = "";
        model = "";
        type = "";
    }

    public Car(String mark, String model)
    {
        this.mark = mark;
        this.model = model;
    }

    public Car(String mark, String model, String type)
    {
        this.mark = mark;
        this.model = model;
        this.type = type;
    }
}
