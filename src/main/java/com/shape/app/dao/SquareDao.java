package com.shape.app.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.shape.app.dao.model.SquareDocument;

public class SquareDao extends BaseDao {

    public List<SquareDocument> getAll() {
        return getDocuments(SquareDocument.class);
    }

    public void save(SquareDocument data) {
        try {
            mongoOperations.save(data);
        } catch (Exception e) {
            System.err.print("error occureed while saving");
        }
    }

    public SquareDocument getSquareData(int side) {
        Query query = getQueryFromCriteria(SIDE_CRITERIA, new String[] { String.valueOf(side) });
        SquareDocument data = mongoOperations.findOne(query, SquareDocument.class);
        return data;
    }

    public void delete(int side) {
        Query query = getQueryFromCriteria(SIDE_CRITERIA, new String[] { String.valueOf(side) });
        SquareDocument dbDoc = mongoOperations.findAndRemove(query, SquareDocument.class);
    }
}
