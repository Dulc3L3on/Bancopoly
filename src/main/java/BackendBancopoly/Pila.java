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
public class Pila<E> implements Serializable{
    E pila[];
    private int tamanioPila;
    private int inicio;    
    private int numeroElementosAdd=0;
    private int numeroElementosEnPila;//elementos totales, por lo tanto inicia su conteo desde 1
    
    public Pila(){//este ctrct será útil para cuando se necesiten pilas de tamaño indefinido, pues seguirán creciendo
        pila = (E[])new Object[1];//y como todo, se debe partir de algo, en este caso de tam 1
    }
    
    public Pila(int tamanio){
        tamanioPila=tamanio;        
        numeroElementosEnPila=tamanioPila;
        inicio=tamanioPila;
         pila = (E[]) new Object[tamanio];//pues debe hacerse la conversión al tipo real de la pila
    }
    
    public void addElemento(E elelmentoAAdd){
        pila[numeroElementosAdd]=elelmentoAAdd;        
        numeroElementosAdd++;
    }
    
    public E entregarElementoSIguiente(){
        //Revisar que el si el índice es 0 regresarlo al valor máx correspondiente    
            darSeguimiento();
            inicio--;//no es necesario hacer algo con los índices puesto que el que se haya redimensionado, 
            //no quiere decir que los índice cambien de lugar, además va hacia abajo, entonces aunque cb lo 
            //que arriba de él esté, no le importará, pues el sigue hacia ab, cuando vuelva al pico, harbá diferencia 
            //por el hecho de que ya no serán los mismo ele, pero nada más; aunque esté en el pico mismo no importará
            //auqnue fuera el pico de 1 solo elemento, pues afuera se asegura que cuando no hayan elementos, que es lo que sucedería 
            return pila[inicio];              
    }//recuerda que el hecho de estar vacía no se revisa aquí, pues cada método se concentra en hacer lo que tiene asignado
    
    private void darSeguimiento(){
        if(inicio==0){//ya que nunca se pasará a números negativos por el hecho de que el número de elementos será 0 cuando no haya nada y para esa ocasión ya no se le permitirá exe el método para sacar los ele
            inicio=numeroElementosEnPila;//pues se decrementa el valor por lo tanto no importa que sea un número más del índice porque a la hora de
            //trabajar con este valor de primero se hará la debida resta
        }
    }
    
    public void sacarElementoPila(E elementoELiminar){        
            numeroElementosEnPila--;
            E pilaAuxiliar[]=pila;
            pila= (E[]) new Object[numeroElementosEnPila];        
            int indiceAsignador=-1;
        
                  for (int indiceElemento = 0; indiceElemento < pila.length; indiceElemento++) {            
                      indiceAsignador++;
                  
                      if(pilaAuxiliar[indiceElemento].equals(elementoELiminar)){//pues esta es la que posee a los elementos a pasar para pila
                            indiceAsignador++;
                      }              
                       pila[indiceElemento]=pilaAuxiliar[indiceAsignador];                  
                  }//fin del for          
        }
    
    /**
     * Método para pilas con tamaño indefinido
     * @param elemento
     */
    public void agregarELementosPila(E elemento){
            pila[numeroElementosAdd]=elemento;
            numeroElementosAdd++;            
            E pilaAuxiliar[]=pila;
            pila =(E[]) new Object[numeroElementosAdd+1];
            
            for (int indice = 0; indice < pilaAuxiliar.length; indice++) {//pues debe ser suficiente para add lo sele del auxi a la pila
                pila[indice]=pilaAuxiliar[indice];
            }
        }
    
        public boolean estaVacia(){
            return (numeroElementosEnPila==0);
        }
        
        //los métodos de sacar y entregar hacen su trabajo cada vez que son llamados, por lo tanto el que los llama
        //debe ser quien sepa cuando ya no puede hacerlo y por ello evitar que se les invoque de tal manera que estos
        //métodos en su cuerpo no verifiquen esto sino solo se encarguen de su tarea
}
