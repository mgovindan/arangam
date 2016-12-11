package com.example.lenovo.available.model;

import com.example.lenovo.available.Program;

/**
 * Created by Lenovo on 12/9/2016.
 */

public class Artists {
    private String id, name;

    private Program program;

    public Artists() {

    }

    public Artists(String id, String name, Program program) {
        this.id = id;
        this.name = name;
        this.program = program;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}