package com.limmerlaboratories.scriptrunner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
