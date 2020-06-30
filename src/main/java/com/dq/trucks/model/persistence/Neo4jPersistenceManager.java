package com.dq.trucks.model.persistence;

import com.dq.trucks.model.Entity;
import com.dq.trucks.model.PersistenceManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.neo4j.driver.*;
import org.neo4j.driver.util.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.neo4j.driver.internal.types.InternalTypeSystem.TYPE_SYSTEM;


public class Neo4jPersistenceManager implements PersistenceManager {

    private Session session;

    public Neo4jPersistenceManager() {
        Driver driver = GraphDatabase.driver(
                "neo4j://localhost:7687", AuthTokens.basic("neo4j", "neo4j2"));
        session = driver.session();
    }

    @Override
    public void create(Entity e) {
        TCommand command = createFromEntity(e);

        try (Transaction tx = session.beginTransaction()) {
            tx.run(command.getCmd(), command.getParameters());
            tx.commit();
        }
    }

    private TCommand createFromEntity(Entity entity) {
        String command = "CREATE (:" + entity.getClass().getSimpleName() + "{";
        Map<String, Object> parameters = new HashMap<>();

        for (Field f : entity.getClass().getDeclaredFields()) {
            try {
                boolean accessible = f.isAccessible();
                f.setAccessible(true);
                String fieldName = f.getName();
                command += fieldName + ":$" + fieldName + ",";
                parameters.put(fieldName, f.get(entity));
                f.setAccessible(accessible);

            } catch (IllegalArgumentException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
        }

        command = command.substring(0, command.length() - 1);
        command += "})";
        return new TCommand(command, parameters);
    }

    @AllArgsConstructor
    private class TCommand {
        @Getter
        private String cmd;
        @Getter
        Map<String, Object> parameters;
    }

    @Override
    public List<String> findAll(Class<? extends Entity> entity) {
        String cmd = new StringBuilder().append("MATCH (").append(getFirstChar(entity)).append(":").append(entity.getSimpleName()).append(") RETURN ").append(getFirstChar(entity)).toString();

        Transaction tx = session.beginTransaction();
        Result resultsIterator = tx.run(cmd);

        List<String> results = new ArrayList<>();

        while (resultsIterator.hasNext()) {
            Record record = resultsIterator.next();
            List<Pair<String, Value>> fields = record.fields();

//            String row =  entity.getSimpleName()+ ": ";
            String row = null;
            for (Pair<String, Value> field : fields) {
                row = field.value().asNode().labels() + ": ";
                row += field.value().asNode().asMap();
            }
            results.add(row);
        }

        tx.close();
        return results;
    }

    @Override
    public String findById(Integer id, Class<? extends Entity> entity) {
//        MATCH (s)
//        WHERE ID(s) = 65110
//        RETURN s
        return null;
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        session.close();
    }

    @Override
    public void delete(String field, Object value, Class<? extends Entity> entity) {
        String cmd = new StringBuilder().append("MATCH (").append(getFirstChar(entity)).append(":").append(entity.getSimpleName()).append(") WHERE ").append(getFirstChar(entity)).append(".").append(field).append(" = ").append(value instanceof String ? "'" + value + "'" : value).append(" DETACH DELETE ").append(getFirstChar(entity)).toString();

        try (Transaction tx = session.beginTransaction()) {
            tx.run(cmd);
            tx.commit();
        }
    }

    @Override
    public void update(Integer id, String fieldName, Object newValue, Class<? extends Entity> entity) {
        String cmd = new StringBuilder().append("MATCH (").append(getFirstChar(entity)).append("{id: ").append(id).append("})").append("SET ").append(getFirstChar(entity)).append(".").append(fieldName).append(" = ").toString();

        cmd += newValue instanceof String ? "'" + newValue + "'" : newValue;

        try (Transaction tx = session.beginTransaction()) {
            tx.run(cmd);
            tx.commit();
        }
    }

    private char getFirstChar(Class<? extends Entity> entity) {
        return entity.getSimpleName().charAt(0);
    }

    @Override
    public void createRelation(Integer id1, Class<? extends Entity> entity1, Integer id2, Class<? extends Entity> entity2) {
        String cmd = new StringBuilder().append("MATCH (").append(getFirstChar(entity1)).append(":").append(entity1.getSimpleName()).append(") , (").append(getFirstChar(entity2)).append(":").append(entity2.getSimpleName()).append(") ").append("WHERE ").append(getFirstChar(entity1)).append(".id = ").append(id1).append(" AND ").append(getFirstChar(entity2)).append(".id = ").append(id2).append(" CREATE (").append(getFirstChar(entity1)).append(")-[r:IS_IN]->(").append(getFirstChar(entity2)).append(") RETURN type(r)").toString();

        try (Transaction tx = session.beginTransaction()) {
            tx.run(cmd);
            tx.commit();
        }
    }

    @Override
    public List<String> findRelated(Integer id, Class<? extends Entity> entity) {
        List<String> res = new ArrayList<>();

        String cmd = new StringBuilder().append("MATCH (").append(getFirstChar(entity)).append(":").append(entity.getSimpleName()).append(")-[r]-(n) ").append(" WHERE ").append(getFirstChar(entity)).append(".id = ").append(id).append(" RETURN ").append(getFirstChar(entity)).append(", r, n").toString();

        Transaction tx = session.beginTransaction();
        Result result = tx.run(cmd);
        while (result.hasNext()) {
            Record record = result.next();
            List<Pair<String, Value>> fields = record.fields();
            for (Pair<String, Value> field : fields) {
                Value value = field.value();
                if (TYPE_SYSTEM.NODE().isTypeOf(value)) {
                    res.add(field.value().asNode().labels() + ": " + field.value().asNode().asMap());
                } else if (TYPE_SYSTEM.RELATIONSHIP().isTypeOf(value)) {
                    res.add("Relation type: " + field.value().asRelationship().type());
                }
            }
        }

        tx.close();
        return res;
    }

}
