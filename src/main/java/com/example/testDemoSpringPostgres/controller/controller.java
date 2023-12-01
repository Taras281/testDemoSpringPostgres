package com.example.testDemoSpringPostgres.controller;

import com.example.testDemoSpringPostgres.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unit")
public class controller {
    @Autowired
    private com.example.testDemoSpringPostgres.repository.unitRepository unitRepository;


    @GetMapping()
    public List<Unit> getTest(){
        List res = (List<Unit>) unitRepository.findAll();
        return res;
    }

    @PostMapping()
    public Unit create(@RequestBody Unit unit){

        return unitRepository.save(unit);
    }


}
