package com.oneMoreTry.FizzBuzz.DAO;

import com.oneMoreTry.FizzBuzz.Model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("RealDB")
public class RealEntityDB implements EntityDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public RealEntityDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Entity> solveNumber(int number) {
        List<Entity> returnEntities = new ArrayList<>();

        for (Entity entity : history()) {
            if (entity.getResponseNumber() == 0)
                continue;
            if (number % entity.getResponseNumber() == 0) {
                returnEntities.add(entity);

            }
        }
        return returnEntities;
    }

    @Override
    public boolean storeResponse(Entity entity, UUID id, String date) {
        for (Entity entity1 : history()) {
            if (entity.getResponseNumber() == entity1.getResponseNumber())
                return false;
        }
        addNewEntry(entity, id, date);
        return true;
    }

    @Override
    public List<Entity> history() {
        final String sql = "SELECT * FROM responses_new";
        return jdbcTemplate.query(sql, new Object[]{}, (resultSet, i) -> getEntity(resultSet));
    }

    @SuppressWarnings("SqlWithoutWhere")
    @Override
    public boolean removeAllEntries() {

        final String sql = "DELETE FROM responses_new ";


        return jdbcTemplate.query(sql, new Object[]{}, (resultSet, i) -> getEntity(resultSet)).isEmpty();
    }

    @Override
    public int removeByResponseNumber(int responseNumber) {
        final String sql = "DELETE FROM responses_new WHERE number_of_response  = ?";
        return jdbcTemplate.update(sql, responseNumber);
    }

    @Override
    public boolean editEntityByResponseNumber(int responseNumber, Entity newEntity) {
        for (Entity entity : history()) {
            if (entity.getResponseNumber() == responseNumber) {
                String dateTime = LocalDateTime.now().toString();
                removeByResponseNumber(responseNumber);
                storeResponse(newEntity, entity.getId(),dateTime);
                return true;
            }
        }
        return false;
    }

    private Entity getEntity(ResultSet resultSet) throws SQLException {
        String idStr = resultSet.getString("id");
        UUID id = UUID.fromString(idStr);
        String response = resultSet.getString("response");
        String responseNumberStr = resultSet.getString("number_of_response");
        int responseNumber = Integer.parseInt(responseNumberStr);
        String dateStr = resultSet.getString("date");
        return new Entity(
                id, response, responseNumber, dateStr
        );
    }

    private void addNewEntry(Entity entity, UUID id, String date) {
        final String sql = "INSERT INTO responses_new (number_of_response, response, date, id) VALUES (?,?,?,?)";
        jdbcTemplate.update(
                sql,
                entity.getResponseNumber(),
                entity.getResponse(),
                date,
                id
        );
    }
}
