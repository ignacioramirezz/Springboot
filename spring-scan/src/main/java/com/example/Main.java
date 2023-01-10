package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // CONCEPTO 1 COMO OBTENER BEANS DE SPRING

        //Opcion 1. Crear el objeto de una forma formal
        //Calculadora service = new Calculadora();

        //Opcion 2. Recibir un objeto de spring
        Calculadora calculatorService = (Calculadora) context.getBean("calculadora");

        String texto = calculatorService.holaMundo();
        System.out.println(texto);

        //CONCEPTO 2: CARGAR UN BEAN DENTRO DE OTRO BEAN
        //cargar un bean dentro de otro bean
        GestorFacturas gestor = (GestorFacturas) context.getBean("gestorFacturas");
        System.out.println(gestor.calculadora.holaMundo());

        // CONCEPTO 3: SCOPE O ALCANCE
        //los beans por defecto son singleton, se crea el objeto y se reutiliza para toda
        // la aplicacion
        // podemos cambiarlo a scope="prototype" que se cree un nuevo objeto a la vez
        // verificarlo viendo como se ejecuta el constructor
    }
}