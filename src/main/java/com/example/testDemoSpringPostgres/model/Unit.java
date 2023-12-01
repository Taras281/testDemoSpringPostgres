package com.example.testDemoSpringPostgres.model;

//import jakarta.persistence.*;

import jakarta.persistence.*;


@Entity
@Table(name = "unit")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String nameNetwork;

    Unit(int id, String nameNetwork) {
        this.id = id;
        this.nameNetwork = nameNetwork;
    }

    public Unit() {

    }

    public void setName(String name) {
        this.nameNetwork = name;
    }

    public String getNameNetwork() {
        return nameNetwork;
    }
}
