package com.shape.app.model;

public class Triangle {
    private int length;
    private int breadth;
    private int height;
    private String dimensions;

    public Triangle(int length, int breadth, int height, String dimensions) {
        super();
        this.length = length;
        this.breadth = breadth;
        this.height = height;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        String generatedId = getLength() + "*" + getBreadth() + "*" + getHeight();
        this.dimensions = generatedId;
    }

    @Override
    public String toString() {
        return "Triangle [length=" + length + ", breadth=" + breadth + ", height=" + height + "]";
    }

}
