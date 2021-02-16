package com.herval.javatie.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.herval.javatie.domain.exception.EntidadeEmUsoException;
import com.herval.javatie.domain.exception.EntidadeNaoEncontradaException;
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
	public ResponseEntity<Categoria> buscar(@PathVariable Long categoriaId) {
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
		if (categoria.isPresent()) {
			return ResponseEntity.ok(categoria.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria adicionar(@RequestBody Categoria categoria) {
		return categoriaService.salvar(categoria);
	}
	
	@PutMapping("/{categoriaId}")
	public ResponseEntity<?> atualizar(@PathVariable Long categoriaId, @RequestBody Categoria categoria) {
		Optional<Categoria> categoriaAtual = categoriaRepository.findById(categoriaId);
		if (categoriaAtual.isPresent()) {
			BeanUtils.copyProperties(categoria, categoriaAtual.get(), "id");
			Categoria categoriaSalva = categoriaRepository.save(categoriaAtual.get());
			return ResponseEntity.ok(categoriaSalva);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{categoriaId}")
	public ResponseEntity<Categoria> remover(@PathVariable Long categoriaId) {
		try {
			categoriaService.excluir(categoriaId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}  catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}	
	}
}
