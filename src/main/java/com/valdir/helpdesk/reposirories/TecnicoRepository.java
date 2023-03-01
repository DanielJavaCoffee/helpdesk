package com.valdir.helpdesk.reposirories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valdir.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
