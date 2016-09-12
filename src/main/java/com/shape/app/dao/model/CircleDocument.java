package com.shape.app.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.shape.app.dao.constants.ShapeDocumentConstants;

@Document(collection = "CircleDocument")
public class CircleDocument {
    @Id
    @Field(ShapeDocumentConstants.RADIUS)
    private String radius;

    public CircleDocument(int radius) {
        super();
        this.radius = String.valueOf(radius);
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "CircleDocument [radius=" + radius + "]";
    }

}
