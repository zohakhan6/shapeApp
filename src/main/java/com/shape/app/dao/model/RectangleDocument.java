package com.shape.app.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.shape.app.dao.constants.ShapeDocumentConstants;

@Document(collection = "RectangleDocument")
public class RectangleDocument {

    private int length;
    private int breadth;

    @Id
    @Field(ShapeDocumentConstants.DIMENSIONS)
    private String dimensions;

    public RectangleDocument(int length, int breadth, String dimensions) {
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
