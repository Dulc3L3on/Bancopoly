/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBancopoly;

import java.io.Serializable;

/**
 *
 * @author phily
 */
public class Nodo <E> implements Serializable{
    E contenido;
    Nodo<E> nodoSiguiente;
    private int numeroElementosEnNodo;//esta vriable será útil para el proceso en el cual se almacenan las propiedades, por el hecho de saber el número de elementos que debe
    //poseer del grupo para comenzar a construir y saber que tan rico es
    
    /**
     *ctrctor para 1er elemento es decir, cabeza
     * @param elemento
     */
    public Nodo(E elemento){
        this(elemento, null);//mando a llamar al ctrctor que recibe 2 parámetros
    }
    
    /**
     *ctrctor para nodos addi a la cabeza
     * @param elemento
     * @param siguiente 
     */
    public Nodo(E elemento, Nodo siguiente){//a ver si no te da problema el nodo por no estar especigicando su tipo... puesto que esta clase es genérica y aquí estas creando uno sin saber, asi que creo que debería de  especificarselo
        contenido=elemento;
        nodoSiguiente=siguiente;
    }
    
    public void establecerContenido(E contenidoNuevo){
        contenido=contenidoNuevo;
    }
    
    public E obtenerObjectcEnCasilla(){//no será necesario el índice?? para hacer ref a uno específico y obtener sus respect datos??
        return contenido;
    }
    
    public Nodo obtenerSiguiente(){//Aqupi estas refiriendote al nodo, mas no al objeto que dentro de él está contenido
        return nodoSiguiente;
    }   
    
    public void incrementarNumeroElementosNodo(){
        numeroElementosEnNodo++;
    }
    
    public int obtenerNumeroElementosNodo(){
        return numeroElementosEnNodo;
    }
    
    //FUNCIONAMIENTO DE LOS NODOS   
    /*El nodo contiene dentro de sí un objeto del cual el puede conocer o no su tipo, y posee una autorreferencia a el
      entonces cuando quieras crear un objeto nodo tendrás que especificar el tipo de objeto (ya sea por parám o 
      el tipo en concreto), y además crear la referencia al Nodo mismo ya sea nula o no, en este csao no se genera 
     problemas con respecto al tipo de objeto, porque dicho el nodo es de tipo Nodo el cual contiene dentro de sí(como 
     cualquier clase) al objeto, el cual ya tiene su tipo respectivo(como cualquier atributo)especificado en ella o en la 
    que se genera dicho atributo (genéricas)    
    */
    
}
