package com.shape.app.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.shape.app.dao.model.CircleDocument;

public class CircleDao extends BaseDao {

    public List<CircleDocument> getAll() {
        return getDocuments(CircleDocument.class);
    }

    public void save(CircleDocument data) {
        try {
            mongoOperations.save(data);
        } catch (Exception e) {
            System.err.print("error occureed while saving " + e);
        }
    }

    public CircleDocument getCircleData(int radius) {
        Query query = getQueryFromCriteria(RADIUS_CRITERIA, new String[] { String.valueOf(radius) });
        CircleDocument data = mongoOperations.findOne(query, CircleDocument.class);
        return data;
    }

    public void delete(int radius) {
        Query query = getQueryFromCriteria(RADIUS_CRITERIA, new String[] { String.valueOf(radius) });
        CircleDocument dbDoc = mongoOperations.findAndRemove(query, CircleDocument.class);
    }

}
