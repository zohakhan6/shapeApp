package com.shape.app.model;

import java.util.Map;

public class Circle {

    public Circle(int radius) {
        super();
        this.radius = radius;
    }

    private int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle [radius=" + radius + "]";
    }

    public static Map<String, ?> getDimensions() {
        // TODO Auto-generated method stub
        return null;
    }

}
