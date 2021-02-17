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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.herval.javatie.domain.model.Categoria;
import com.herval.javatie.domain.repository.CategoriaRepository;
import com.herval.javatie.domain.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	@GetMapping("/categorias/por-nome")
	public List<Categoria> categoriasPorNome(@RequestParam String nome) {
		return categoriaRepository.findByNome(nome);
	}
	
	@GetMapping("/{categoriaId}")
	public Categoria buscar(@PathVariable Long categoriaId) {
		return categoriaService.buscarOuFalhar(categoriaId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria adicionar(@RequestBody Categoria categoria) {
		return categoriaService.salvar(categoria);
	}
	
	@PutMapping("/{categoriaId}")
	public Categoria atualizar(@PathVariable Long categoriaId, @RequestBody Categoria categoria) {
		Categoria categoriaAtual = categoriaService.buscarOuFalhar(categoriaId);
		BeanUtils.copyProperties(categoria, categoriaAtual, "id");
		return categoriaService.salvar(categoriaAtual);

	}
	
	@DeleteMapping("/{categoriaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long categoriaId) {
		categoriaService.excluir(categoriaId);
	}
}
