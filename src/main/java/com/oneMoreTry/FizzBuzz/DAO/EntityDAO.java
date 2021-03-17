package com.oneMoreTry.FizzBuzz.DAO;

import com.oneMoreTry.FizzBuzz.Model.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EntityDAO {
    List<Entity> solveNumber(int number);

    boolean storeResponse(Entity entity, UUID id, String date);

    default boolean storeResponse(Entity entity) {
        UUID id = UUID.randomUUID();
        String dateTime = LocalDateTime.now().toString();
        return storeResponse(entity, id,dateTime);
    }

    List<Entity> history();

    boolean removeAllEntries();

    int removeByResponseNumber(int responseNumber);

    boolean editEntityByResponseNumber(int responseNumber, Entity newEntity);
}
