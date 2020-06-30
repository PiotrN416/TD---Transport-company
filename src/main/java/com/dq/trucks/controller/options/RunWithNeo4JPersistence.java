package com.dq.trucks.controller.options;

import com.dq.trucks.controller.SelectOption;
import com.dq.trucks.model.PersistenceManager;
import com.dq.trucks.model.persistence.Neo4jPersistenceManager;

public class RunWithNeo4JPersistence extends SelectOption<PersistenceManager> {

    @Override
    public PersistenceManager execute() {
        return new Neo4jPersistenceManager();
    }

    @Override
    public String getOptionLabel() {
        return "Neo4j";
    }
}
