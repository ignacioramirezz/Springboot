package com.example.restdatajpa;

import com.example.restdatajpa.entities.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.restdatajpa.repository.BookRepository;

import java.time.LocalDate;

@SpringBootApplication
public class RestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		// CRUD
		// crear libro
		Book libro1 = new Book(null,"boca","Messi",12,34.22, LocalDate.of(2010,12,13),true);
		Book libro2 = new Book(null,"boca de mi vida","Paul",16,50.22, LocalDate.of(2005,12,13),true);

		// almacenar un libro
		System.out.println("Numero de libros en base de datos:  " + repository.findAll().size());
		repository.save(libro1);
		repository.save(libro2);

		// recuperar todos lo libros
		System.out.println("Numero de libros en base de datos:  " + repository.findAll().size());

		// borrar un libro
		//repository.deleteById(1L);

		System.out.println("Numero de libros en base de datos:  " + repository.findAll().size());

	}

}
