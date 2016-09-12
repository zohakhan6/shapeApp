package com.shape.app.model;

public class Rectangle {
    private int length;
    private int breadth;
    private String dimensions;

    public Rectangle(int length, int breadth, String dimensions) {
        super();
        this.length = length;
        this.breadth = breadth;
        this.dimensions = dimensions;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        String generatedId = getLength() + "*" + getBreadth();
        this.dimensions = generatedId;
    }

    @Override
    public String toString() {
        return "Rectangle [length=" + length + ", breadth=" + breadth + "]";
    }

}
