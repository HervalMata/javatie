package com.herval.javatie.api.controller;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.herval.javatie.domain.model.Permissao;
import com.herval.javatie.domain.repository.PermissaoRepository;
import com.herval.javatie.domain.service.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoService permissaoService;
	
	@GetMapping
	public List<Permissao> listar() {
		return permissaoRepository.findAll();
	}
	
	@GetMapping("/{permissaoId}")
	public Permissao buscar(@PathVariable Long permissaoId) {
		return permissaoService.buscarOuFalhar(permissaoId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Permissao adicionar(@RequestBody Permissao permissao) {
		return permissaoService.salvar(permissao);
	}
	
	@PutMapping("/{permissaoId}")
	public Permissao atualizar(@PathVariable Long permissaoId, @RequestBody Permissao permissao) {
		Permissao permissaoAtual = permissaoService.buscarOuFalhar(permissaoId);
		BeanUtils.copyProperties(permissao, permissaoAtual, "id");
		return permissaoService.salvar(permissaoAtual);
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long permissaoId) {
		permissaoService.excluir(permissaoId);
	}
}
