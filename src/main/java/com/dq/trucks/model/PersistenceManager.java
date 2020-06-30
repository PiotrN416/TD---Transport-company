package com.dq.trucks.model;

import java.util.List;

public interface PersistenceManager {

    void create(Entity e);

    List<String> findAll(Class<? extends Entity> entity);

    String findById(Integer id, Class<? extends Entity> entity);

    void delete(String field, Object value, Class<? extends Entity> entity);

    void update(Integer id, String fieldName, Object newValue, Class<? extends Entity> entity);

    void createRelation(Integer id1, Class<? extends Entity> entity1, Integer id2, Class<? extends Entity> entity2);

    List<String> findRelated(Integer id, Class<? extends Entity> entity);
}
