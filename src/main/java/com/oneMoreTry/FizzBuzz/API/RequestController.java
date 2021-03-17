package com.oneMoreTry.FizzBuzz.API;

import com.oneMoreTry.FizzBuzz.Model.Entity;
import com.oneMoreTry.FizzBuzz.Service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;

import javax.servlet.annotation.WebFilter;
import java.util.List;
@CrossOrigin
@RequestMapping("api/v1/entity")
@RestController
@WebFilter
public class RequestController {
    private final EntityService entityService;
    @Autowired
    public RequestController(EntityService entityService) {
        this.entityService = entityService;
    }
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public boolean storeResponse(@RequestBody @Validated @NonNull Entity entity){
        System.out.println(entity);
        return entityService.storeResponse(entity);
    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Entity> history(){
        return entityService.history();
    }
    @GetMapping (
            path = "{number}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public List<Entity> solveNumber(@PathVariable("number") int number){
        System.out.println(number);
        return entityService.solveNumber(number);
    }
    @Modifying
    @DeleteMapping
    public boolean deleteAllEntries(){
        return entityService.deleteAllEntries();
    }
    @DeleteMapping (
            path = "{number_of_response}"
    )
    public int deleteByNumber(@PathVariable("number_of_response") int number){
        return entityService.deleteByResponseNumber(number);
    }
    @PutMapping (
           path = "{number_of_response}"
    )
    public boolean updateByResponseNumber(@PathVariable("number_of_response") int responseNumber, @RequestBody @Validated Entity newEntity){
       return entityService.updateByResponseNumber(responseNumber, newEntity);
    }
}
