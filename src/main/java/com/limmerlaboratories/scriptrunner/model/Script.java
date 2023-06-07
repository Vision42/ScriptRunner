package com.limmerlaboratories.scriptrunner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"project"})
@JsonIgnoreProperties("project")
public class Script {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
    private String path;
    private boolean running;
    private int exitCode;
    private long executionStarted;
    private long executionEnded;

}
