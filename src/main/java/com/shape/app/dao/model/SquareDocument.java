package com.shape.app.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.shape.app.dao.constants.ShapeDocumentConstants;

@Document(collection = "SquareDocument")
public class SquareDocument {
    @Id
    @Field(ShapeDocumentConstants.SIDE)
    private String side;

    public SquareDocument(String side) {
        super();
        this.side = side;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "SquareDocument [side=" + side + "]";
    }

}
