package com.rpggame.world.locations.repository.impl;

import com.rpggame.world.locations.model.Location;
import com.rpggame.world.locations.repository.CustomLocationInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomLocationInsertImpl implements CustomLocationInsert {

    @Autowired
    private JdbcAggregateTemplate template;
    @Override
    public void insertLocation(Location location) {
        template.insert(location);
    }
}
