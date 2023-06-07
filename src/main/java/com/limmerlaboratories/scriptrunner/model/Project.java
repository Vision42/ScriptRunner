package com.limmerlaboratories.scriptrunner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Script> scripts;

}
