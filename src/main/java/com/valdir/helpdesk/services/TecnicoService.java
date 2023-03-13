package com.valdir.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.valdir.helpdesk.domain.Pessoa;
import com.valdir.helpdesk.domain.Tecnico;
import com.valdir.helpdesk.domain.dtos.TecnicoDTO;
import com.valdir.helpdesk.reposirories.PessoaRepository;
import com.valdir.helpdesk.reposirories.TecnicoRepository;
import com.valdir.helpdesk.services.exception.DataIntegrityViolstionExeption;
import com.valdir.helpdesk.services.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		validaPorCPFeEmail(tecnicoDTO);
		Tecnico tecnico = new Tecnico(tecnicoDTO);
		return tecnicoRepository.save(tecnico);
	}

	public void validaPorCPFeEmail(TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> pessoaCPF = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		Optional<Pessoa> pessoaEmail = pessoaRepository.findByCpf(tecnicoDTO.getEmail());

		if (pessoaCPF.isPresent() || pessoaEmail.isPresent()) {
			throw new DataIntegrityViolstionExeption("CPF ou Email já cadastrado no sistema!");
		}
	}

	public Tecnico upDate(Integer id, @Valid TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(id);
		Tecnico tecnico = findById(id);
		validaPorCPFeEmail(tecnicoDTO);
		tecnico = new Tecnico(tecnicoDTO);
		return tecnicoRepository.save(tecnico);
	}

	public void delete(Integer id) {
		Tecnico tecnico = findById(id);
		if (tecnico.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O tecnico possui ordens de serviço e não pode ser deletado");
		}
		tecnicoRepository.deleteById(id);
	}

}
