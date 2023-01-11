package com.example.restdatajpa.controller;
import com.example.restdatajpa.entities.Book;
import com.example.restdatajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    // mostrar mensajes con la hora y detallers
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
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        //guardar el libro recibido por parametro en la base de datos
        if(book.getId() != null){ //quiere decir que existe el id y por tanto no es una creacion
            // log para guardar un archivo con los errores en tiempo real
            // de esta forma la aplicacion puede ver si anda bien
            log.warn("trying to create a book with id");
            System.out.println("intenta realizar una actualizacion con una creacion de un libro");
            return ResponseEntity.badRequest().build();
        }
        Book result =  repository.save(book); // el libro devuelto tiene una clave primaria
        // Metodo post, no colisiona con findAll porque son diferentes metodos HTTP: GET vs POST
        return ResponseEntity.ok(result); // el libro devuelto tiene una clave primaria

    }




    //actualizar un libro existente en base de datos
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId()== null){ // si no tiene  el id lo que estoy haciendo es querer crear en una actualizacion
            log.warn("trying to update a non existent book");
            return ResponseEntity.badRequest().build();
            //seria una condicion que se esta enviando mal la peticion

        }
        if (!repository.existsById(book.getId())){
            //si no existe este id lo que hace es mandar un mensaje de error
            log.warn("Tryomg to update a nom existient book");
            return ResponseEntity.notFound().build();
            //seria un error de que se esta no se encontro el id para hacer la actualizacion
        }
        // El proceso de actualizacion
        Book result = repository.save(book);
        return ResponseEntity.ok(result); // el libro devuelto tiene una clave primaria
    }

    //borrar un libro en base de datos

    @DeleteMapping("api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if (!repository.existsById(id)){
            //si no existe este id lo que hace es mandar un mensaje de error
            log.warn("Tryomg to delete a nom existient book");
            return ResponseEntity.notFound().build();
            //seria un error de que se esta no se encontro el id para hacer la actualizacion
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
        //noContent que todo esta ok bien que se borro el contenido
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll(){
        log.info("Realizando el metodo de borrado de todos los registros");
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
