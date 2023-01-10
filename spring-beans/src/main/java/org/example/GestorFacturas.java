package org.example;

public class GestorFacturas {

    Calculadora calculadora;
    String nombre;

    public GestorFacturas(String nombre,Calculadora calculadora){
        System.out.println("Ejecutando constructor GestorFacturas");
        this.nombre=nombre;
        this.calculadora=calculadora;
    }


}
