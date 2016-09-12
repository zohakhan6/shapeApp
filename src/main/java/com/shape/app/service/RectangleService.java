package com.shape.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.app.dao.RectangleDao;
import com.shape.app.dao.model.RectangleDocument;
import com.shape.app.model.Rectangle;

@Service
public class RectangleService {
    @Autowired
    private RectangleDao rectangleMao;

    public List<Rectangle> findAllRectangles() {
        List<RectangleDocument> rectangleList = rectangleMao.getAll();
        List<Rectangle> rectangles = new ArrayList<Rectangle>();
        for (RectangleDocument data : rectangleList) {
            Rectangle rectangle = new Rectangle(data.getLength(), data.getBreadth(), data.getDimensions());
            rectangles.add(rectangle);
        }
        return rectangles;
    }

    public boolean isRectangleExist(Rectangle rectangle) {
        RectangleDocument data = rectangleMao.getRectangleData(rectangle.getDimensions());
        return data != null;
    }

    public void saveRectangle(Rectangle rectangle) {
        RectangleDocument data = new RectangleDocument(rectangle.getLength(), rectangle.getBreadth(), rectangle.getDimensions());
        rectangleMao.save(data);
    }

    public Rectangle findByDimensions(String dimensions) {
        RectangleDocument data = rectangleMao.getRectangleData(dimensions);
        return new Rectangle(data.getLength(), data.getBreadth(), data.getDimensions());
    }

    public void deleteRectangleByDimensions(String dimensions) {
        rectangleMao.delete(dimensions);
    }

}
