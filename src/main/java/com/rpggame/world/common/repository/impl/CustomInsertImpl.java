package com.rpggame.world.common.repository.impl;

import com.rpggame.world.locations.model.Location;
import com.rpggame.world.common.repository.CustomInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomInsertImpl<T> implements CustomInsert<T> {

    @Autowired
    private JdbcAggregateTemplate template;
    @Override
    public void insert(T data) {
        template.insert(data);
    }
}
