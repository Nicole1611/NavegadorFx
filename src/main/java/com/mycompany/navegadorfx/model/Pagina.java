/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.navegadorfx.model;

import java.util.Stack;

/**
 *
 * @author User
 */
public class Pagina {
    private String actual;
    private Stack <String> atras;
    private Stack <String> adelante;

    public Pagina(String actual) {
        this.actual = actual;
        atras = new Stack<>();
        adelante = new Stack<>();
    }
      public void nuevaPagina(String nueva) {
        // Guardar la actual en la pila de atrás
        atras.push(actual);
        // Actualizar la página actual
        actual = nueva;
        // Limpiar la pila de adelante (ya no se puede ir hacia adelante)
        adelante.clear();
    }
    public boolean navegarAdelante(){
        //Verificamos si la fila esta vacia
        if(adelante.isEmpty()){
            return false;
        }
        else{
        //Añadir a la pila atras
        atras.push(actual);
        //Actualizar valor actual
        actual=adelante.peek();
        //Eliminar el ultimo dato de la pila adelante
        adelante.pop();
        return true;
        }
    }
    public boolean navegarAtras(){  
        //Verificamos si la fila esta vacia
        if(atras.isEmpty()){
            return false;
        }
        else{
            //Añadir a la pila adelante
            adelante.push(actual);
            //Actualizar el dato actual
            actual=atras.peek();
            //
            atras.pop();
            return true;
        }
               
    }

    public String getActual() {
        return actual;
    }

    public Stack<String> getAtras() {
        return atras;
    }

    public Stack<String> getAdelante() {
        return adelante;
    }
    
}

