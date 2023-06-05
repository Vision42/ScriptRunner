package com.limmerlaboratories.scriptrunner.repository;

import com.limmerlaboratories.scriptrunner.model.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Integer> {
}
