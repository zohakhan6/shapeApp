package com.shape.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.shape.app.dao.constants.ShapeDocumentConstants;

@Service
public class BaseDao {
    public static final String[] DIMENSION_CRITERIA = new String[] { ShapeDocumentConstants.DIMENSIONS };
    public static final String[] RADIUS_CRITERIA = new String[] { ShapeDocumentConstants.RADIUS };
    public static final String[] SIDE_CRITERIA = new String[] { ShapeDocumentConstants.SIDE };
    @Autowired
    protected MongoOperations mongoOperations;

    public Query getQueryFromCriteria(String[] criteriaFields, String[] values) {
        Criteria criteria = new Criteria();
        Criteria[] criterias = new Criteria[criteriaFields.length];

        for (int i = 0; i < criterias.length; i++) {
            criterias[i] = Criteria.where(criteriaFields[i]).is(values[i]);
        }
        return new Query(criteria.andOperator(criterias));
    }

    public <T> List<T> getDocuments(Class<T> clazz) {
        return mongoOperations.findAll(clazz);
    }
}
