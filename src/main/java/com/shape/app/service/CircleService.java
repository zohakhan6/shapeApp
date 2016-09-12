package com.shape.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.app.dao.CircleDao;
import com.shape.app.dao.model.CircleDocument;
import com.shape.app.model.Circle;

@Service
public class CircleService {
    @Autowired
    private CircleDao circledao;

    public List<Circle> findAllCircles() {
        List<CircleDocument> circleList = circledao.getAll();
        List<Circle> circles = new ArrayList<Circle>();
        for (CircleDocument data : circleList) {
            Circle circle = new Circle(Integer.valueOf(data.getRadius()));
            circles.add(circle);
        }
        return circles;
    }

    public boolean isCircleExist(Circle circle) {
        CircleDocument data = circledao.getCircleData(circle.getRadius());
        return data != null;
    }

    public void saveCircle(Circle circle) {
        CircleDocument data = new CircleDocument(circle.getRadius());
        circledao.save(data);

    }

    public Circle findByRadius(int radius) {
        CircleDocument data = circledao.getCircleData(radius);
        return new Circle(Integer.valueOf(data.getRadius()));
    }

    public void deleteCircleByRadius(int radius) {
        circledao.delete(radius);

    }

}
