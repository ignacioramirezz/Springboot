package com.example.restdatajpa.controller;
import com.example.restdatajpa.entities.Book;
import com.example.restdatajpa.repository.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class BookController {

    //CRUD sobre la entidad book
    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    //Buscar todos los libros
    // ya con esto hace que bookcontroller necesita de bookrepository
    // http://localhost:8080/api/books
    @GetMapping("/api/books")
    public List<Book> findAll(){
        // recuperar y devolver los libros de base de datos
        return repository.findAll();
    }



    //Buscar un solo libro en caso de datos segun su id
    @GetMapping("api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){

        Optional<Book> bookOptional = repository.findById(id);
        if(bookOptional.isPresent())
            return ResponseEntity.ok(bookOptional.get());
        else
            return ResponseEntity.notFound().build(); //devuelve error 404
    }


    //crear un nuevo libro en base de datos
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        //guardar el libro recibido por parametro en la base de datos
        return  repository.save(book);
    }




    //actualizar un libro existente en base de datos

    //borrar un libro en base de datos

}
