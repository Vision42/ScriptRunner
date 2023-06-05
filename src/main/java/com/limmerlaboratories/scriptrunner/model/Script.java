package com.limmerlaboratories.scriptrunner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Script {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Project project;
    private String path;
    private boolean running;
    private int exitCode;
    private long executionStarted;

}
