package com.valdir.helpdesk.reposirories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valdir.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
