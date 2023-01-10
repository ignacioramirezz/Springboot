package com.example;

import org.springframework.stereotype.Component;

@Component
public class Calculadora {

    public Calculadora(){
        System.out.println("ejecutando el constructor de calculadora");
    }

    public String holaMundo(){
        return "Hola mundo";
    }


}
