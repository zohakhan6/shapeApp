package com.shape.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.app.dao.TriangleDao;
import com.shape.app.dao.model.TriangleDocument;
import com.shape.app.model.Triangle;

@Service
public class TriangleService {

    @Autowired
    private TriangleDao triangleMao;

    public List<Triangle> findAllTriangles() {
        List<TriangleDocument> TriangleList = triangleMao.getAll();
        List<Triangle> Triangles = new ArrayList<Triangle>();
        for (TriangleDocument data : TriangleList) {
            Triangle Triangle = new Triangle(data.getLength(), data.getBreadth(), data.getHeight(), data.getDimensions());
            Triangles.add(Triangle);
        }
        return Triangles;
    }

    public boolean isTriangleExist(Triangle triangle) {
        TriangleDocument data = triangleMao.getTriangleData(triangle.getDimensions());
        return data != null;
    }

    public void saveTriangle(Triangle triangle) {
        TriangleDocument data = new TriangleDocument(triangle.getLength(), triangle.getBreadth(), triangle.getHeight(), triangle.getDimensions());
        triangleMao.save(data);
    }

    public Triangle findByDimensions(String dimensions) {
        TriangleDocument data = triangleMao.getTriangleData(dimensions);
        return new Triangle(data.getLength(), data.getBreadth(), data.getHeight(), data.getDimensions());
    }

    public void deleteTriangleByDimensions(String dimensions) {
        triangleMao.delete(dimensions);
    }

}
