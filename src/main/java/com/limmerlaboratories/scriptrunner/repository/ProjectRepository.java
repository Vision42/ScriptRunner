package com.limmerlaboratories.scriptrunner.repository;

import com.limmerlaboratories.scriptrunner.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
