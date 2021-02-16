package com.herval.javatie.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.herval.javatie.JavatieApplication;
import com.herval.javatie.domain.model.Categoria;
import com.herval.javatie.domain.repository.CategoriaRepository;

public class ConsultaCategoriaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(JavatieApplication.class)
												.web(WebApplicationType.NONE).run(args);
		CategoriaRepository categoriaRepository = applicationContext.getBean(CategoriaRepository.class);
		List<Categoria> categorias = categoriaRepository.listar();
		for (Categoria categoria : categorias) {
			System.out.println(categoria.getNome());
		}
	}

}
