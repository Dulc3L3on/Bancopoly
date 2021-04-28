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
public class Cola<E> implements Serializable{
    E cola[];
    private int tamanioCola;//vendría a ser sustituidad po r la var de numeroElesAdd
    private int inicio=0;
    private int numeroElementosAdd=0;
    
    public Cola(){
        cola = (E[]) new Object[1];
        tamanioCola=1;
    }
    
        /**
     * Método para pilas con tamaño indefinido
     * @param elemento
     */
    public void agregarELementosCola(E elemento){
            cola[numeroElementosAdd]=elemento;
            numeroElementosAdd++;//para tener el índice de add siguiente      
            E pilaAuxiliar[]=cola;
            cola =(E[]) new Object[numeroElementosAdd+1];//pues el tamaño es 1 más al numero de add's
            
            for (int indice = 0; indice < pilaAuxiliar.length; indice++) {//pues debe ser suficiente para add lo sele del auxi a la pila
                cola[indice]=pilaAuxiliar[indice];
                //numeroElementosAdd++;//no debe estar aquí puesto que ya posee el índice que le corresponde, tomando en cta que ha crecido la cola
                //esto sucede porque se queda con el'índice siguiente al del agregado, lo cual vuene a comvertirse en el siguiente la próxima vez
                
            }
        }
    
    public E entregarElementos(int siguiente){//si tu haces que solito el método devuleva el siguiente que contiene la colas,
        //deberá el incremento de los índices, partiendo siempre desde 0 y verificando que no se pase del número de elemnteos en la fila
        //de forma análoga a lo de la pila, solo que esta vez con incremneto
        return cola[siguiente];
    }
    
    public int darTamanio(){
        return cola.length;
    }
    
    public boolean estaVacia(){
        return cola[0]==null;//pues si lo es entonces no tiene a nadie encolado xD
    }
    
    
    
}
