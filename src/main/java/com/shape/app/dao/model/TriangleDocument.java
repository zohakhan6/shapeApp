package com.shape.app.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.shape.app.dao.constants.ShapeDocumentConstants;

@Document(collection = "TriangleleDocument")
public class TriangleDocument {

    private int length;
    private int breadth;
    private int height;
    @Id
    @Field(ShapeDocumentConstants.DIMENSIONS)
    private String dimensions;

    public TriangleDocument(int length, int breadth, int height, String dimensions) {
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
        this.dimensions = dimensions;
    }

    @Override
    public String toString() {
        return "TriangleDocument [length=" + length + ", breadth=" + breadth + ", height=" + height + ", dimensions=" + dimensions + "]";
    }

}
