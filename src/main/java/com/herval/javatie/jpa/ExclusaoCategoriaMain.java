package com.herval.javatie.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.herval.javatie.JavatieApplication;
import com.herval.javatie.domain.model.Categoria;
import com.herval.javatie.domain.repository.CategoriaRepository;

public class ExclusaoCategoriaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(JavatieApplication.class)
												.web(WebApplicationType.NONE).run(args);
		CategoriaRepository categoriaRepository = applicationContext.getBean(CategoriaRepository.class);
		Categoria categoria = new Categoria();
		categoria.setId(5L);
		categoriaRepository.remover(categoria);
	}

}
