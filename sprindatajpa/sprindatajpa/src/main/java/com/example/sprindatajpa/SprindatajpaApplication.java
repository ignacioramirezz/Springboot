package com.example.sprindatajpa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SprindatajpaApplication {

	public static void main(String[] args) {

		//SpringApplication.run(SprindatajpaApplication.class, args);
		ApplicationContext context = SpringApplication.run(SprindatajpaApplication.class,args);
		CocheRepository repository = context.getBean(CocheRepository.class);

		System.out.println("find");
		//System.out.println(repository.count());

		//crear y almacenar un coche en base de datos
		Coche toyota = new Coche(45L,2010,"prius","toyota");
		repository.save(toyota);

		//System.out.println("El numero de coches en la base de datos es: "+repository.count());

	}

}
