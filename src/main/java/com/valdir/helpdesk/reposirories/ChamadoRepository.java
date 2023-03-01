package com.valdir.helpdesk.reposirories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valdir.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
