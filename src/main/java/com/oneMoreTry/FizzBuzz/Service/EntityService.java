package com.oneMoreTry.FizzBuzz.Service;

import com.oneMoreTry.FizzBuzz.DAO.EntityDAO;
import com.oneMoreTry.FizzBuzz.Model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityService {
    private final EntityDAO entityDAO;

    @Autowired
    public EntityService(@Qualifier("RealDB") EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }

    public boolean storeResponse(Entity entity) {
        return entityDAO.storeResponse(entity);
    }

    public List<Entity> history() {
        return entityDAO.history();
    }

    public List<Entity> solveNumber(int number) {
        return entityDAO.solveNumber(number);
    }

    @Modifying
    public boolean deleteAllEntries() {
        return entityDAO.removeAllEntries();
    }

    public int deleteByResponseNumber(int number) {
        return entityDAO.removeByResponseNumber(number);
    }

    public boolean updateByResponseNumber(int responseNumber, Entity newEntity) {
        return entityDAO.editEntityByResponseNumber(responseNumber, newEntity);
    }
}
