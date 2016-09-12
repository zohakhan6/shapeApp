package com.shape.app.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.shape.app.dao.model.RectangleDocument;

@Service
public class RectangleDao extends BaseDao {

    public List<RectangleDocument> getAll() {
        return getDocuments(RectangleDocument.class);
    }

    public void save(RectangleDocument data) {
        try {
            mongoOperations.save(data);
        } catch (Exception e) {
            System.err.print("error occureed while saving");
        }
    }

    public RectangleDocument getRectangleData(String dimensions) {
        Query query = getQueryFromCriteria(DIMENSION_CRITERIA, new String[] { dimensions });
        RectangleDocument data = mongoOperations.findOne(query, RectangleDocument.class);
        return data;
    }

    public void delete(String dimensions) {
        Query query = getQueryFromCriteria(DIMENSION_CRITERIA, new String[] { dimensions });
        RectangleDocument dbDoc = mongoOperations.findAndRemove(query, RectangleDocument.class);
    }
}
