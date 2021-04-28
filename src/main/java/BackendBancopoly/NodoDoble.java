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
public class NodoDoble <T> implements Serializable{
    NodoDoble<T> siguiente;
    NodoDoble<T> anterior;
    T contenido;
    
    public NodoDoble(T elemento){
        contenido=elemento;
        siguiente=null;
        anterior=null;
    }
    
    /**
     * método empleado para asignar las respectivas referencias a partir
     * del nodo segundo
     * @param nodoSiguiente
     * @param nodoAnterior
     */
    public NodoDoble(T elemento, NodoDoble<T> nodoSiguiente, NodoDoble<T> nodoAnterior){//para cerrar la lista
        siguiente = nodoSiguiente;
        anterior= nodoAnterior;       
        contenido=elemento;
    }//te quedaste revisando porque sale ese contenido nulo, después de esto arreglarías lo que provoca que la esquina inf der no se tome en cuenta en las casillas para colocar ini, lego arreglar las condi del borde izq porque
    //provoca que se peuda escoger culaquiera y eso no va según las restricciones (creo que eso es lo que se hace después de inicio, solo tendría qeu ser arriba arrDer, Der; luego tendrías que proceder a terminar de crear las
    //casillas y con ello add al listado y luego a pasar el listado de las casillas y el recorrido al tablero donde se jugará, y poner las restricc en el frontend... para darle los detallitos, luego ver lo de la ficha y uff a leer,XD, para ello
    //falta poco, con DIOS, todo se puede
    
    public void reestablecerSiguiente(NodoDoble<T> nuevoSiguiente){
        siguiente=nuevoSiguiente;//la verdad este método no es necesario, solo tendrías que acceder a la var siguiente del nodo alq ue se reestablecerá y luego hacer = nodo siguiente nuevo
    }
    
    public void reestablecerAnterior(NodoDoble<T> nuevoAnterior){
        anterior=nuevoAnterior;//la verdad este método no es necesario, solo tendrías que acceder a la var siguiente del nodo alq ue se reestablecerá y luego hacer = nodo siguiente nuevo
    }
    
    public T obtnerContenido(){
        return contenido;
    }
    
    public NodoDoble obtnerSiguiente(){
        return siguiente;
    }
    
    public NodoDoble obtenerAnterior(){
        return anterior;
    }

    
    
    
}
