package com.shape.app.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.shape.app.dao.model.TriangleDocument;

public class TriangleDao extends BaseDao {

    public List<TriangleDocument> getAll() {
        return getDocuments(TriangleDocument.class);
    }

    public void save(TriangleDocument data) {
        try {
            mongoOperations.save(data);
        } catch (Exception e) {
            System.err.print("error occureed while saving");
        }
    }

    public TriangleDocument getTriangleData(String dimensions) {
        Query query = getQueryFromCriteria(DIMENSION_CRITERIA, new String[] { dimensions });
        TriangleDocument data = mongoOperations.findOne(query, TriangleDocument.class);
        return data;
    }

    public void delete(String dimensions) {
        Query query = getQueryFromCriteria(DIMENSION_CRITERIA, new String[] { dimensions });
        TriangleDocument dbDoc = mongoOperations.findAndRemove(query, TriangleDocument.class);
    }
}
