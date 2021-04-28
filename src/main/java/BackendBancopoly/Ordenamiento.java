/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBancopoly;

import Jugadores.Jugador;
import java.io.Serializable;

/**
 *
 * @author phily
 */
public class Ordenamiento implements Serializable{
    
    /**
     * Este método será empleado para organizar los datos nuevos a enviar al archivo
     * se empleará un algoritmo de ordenamiento puesto que es una matriz, entocnes
     * con esto, lo que se hará es si no habían datos anteiroes entonces solo se em
     * pleará este método y no el de abajo y por ello estos datos serán los que se 
     * archiven
     * @param jugadoresNuevos
     */
    public Jugador[] ordenamientoMatrizBasadoEnRiquezas(Jugador[] jugadoresNuevos){//ORDENA DE FORMA DESCENDENTE    
        
       
        for(int numeroJugadorActual=0; numeroJugadorActual<jugadoresNuevos.length-1; numeroJugadorActual++){
                int indiceDelMaximo=numeroJugadorActual;
                
                for(int numeroJugadorSiguiente=numeroJugadorActual+1; numeroJugadorSiguiente< jugadoresNuevos.length;numeroJugadorSiguiente++){
                          if(jugadoresNuevos[numeroJugadorSiguiente].darRiquezasActuales()>jugadoresNuevos[indiceDelMaximo].darRiquezasActuales()){
                                   indiceDelMaximo=numeroJugadorSiguiente; 
                          }//fin dle if
                }//fin del for que se encarga de buscar al número menor y asignar la nueva ubicación correspondiente
                
              Jugador jugadorTemporal=jugadoresNuevos[numeroJugadorActual];
              jugadoresNuevos[numeroJugadorActual] = jugadoresNuevos[indiceDelMaximo];
              jugadoresNuevos[indiceDelMaximo] = jugadorTemporal;              
        }                            
     
        return jugadoresNuevos;
    }//final del método que ordena por medio de ordenamiento por inserción
    
           
    /**
     * Este método será llamado al momento de guardar y por ello, la primera vez que se cree
     * este arhivo porque no habpia existido antes o porque lo borraron el 2do param será null
     * y ahí emplearé solo el primer paso del algoritmo y ese será el que deba retornar porque
     * no hay nada más
     * 
     * @param jugadoresNuevos
     * @param jugadoresAntiguos
     * @return
     */
    public ListaDoblementeEnlazada<Jugador> ordenarPorPunteoListadoCompleto(Jugador[] jugadoresNuevos, ListaDoblementeEnlazada<Jugador> jugadoresAntiguos){
        ListaDoblementeEnlazada<Jugador> jugadores= jugadoresAntiguos;
        NodoDoble<Jugador> nodoAuxiliar=jugadores.obtnerInicio();//pues no quiero que cada vez regrese al nodo inicila, porque como el arreglo ya está
        //ordenado de la misma forma que el listado entonces se que media vez pasé de ese nodo ya no tengo necesidad de regregsar
      
            for(int numeroJugador = 0; numeroJugador < jugadoresNuevos.length; numeroJugador++) {
                int cumplimientoAlgunRequisito=0;
                
                while(nodoAuxiliar!=null && cumplimientoAlgunRequisito==0){//pero jamás llegará a ese punto porque el break lo impedirá si en dado caso se tuviera que 
                 //insertar al final, porque antes que llegue ahí se cortará el proceso
                 
                   if(jugadoresNuevos[numeroJugador].darRiquezasActuales()>nodoAuxiliar.contenido.darRiquezasActuales()){                                              
                       jugadores.anadirAntesDe(jugadoresNuevos[numeroJugador], nodoAuxiliar);
                       cumplimientoAlgunRequisito++;                       
                       
                       nodoAuxiliar=nodoAuxiliar.anterior;  //si recuerda que debe ser así porque no sabes que situación se genera con el que recientemente se acaba de hallar algo con un elemento
                           //del listado de los que se debe add, pues de ellos conocemos la situación con sus mismos comparañeros mas no la que se genera con los que ya estaban
                       
                   }else if(jugadoresNuevos[numeroJugador].darRiquezasActuales()<nodoAuxiliar.contenido.darRiquezasActuales()){
                   
                       if(nodoAuxiliar.siguiente==null|| jugadoresNuevos[numeroJugador].darRiquezasActuales()>=nodoAuxiliar.siguiente.contenido.darRiquezasActuales()){//eso queire decir que es el último nodo del listado                                                          
                           jugadores.anadirDespuesDe(jugadoresNuevos[numeroJugador], nodoAuxiliar); 
                           nodoAuxiliar=nodoAuxiliar.anterior;//En este elseif donde se corrobora que los datos son < si deben estar en cada uno de los bloques de condiciones de < jerarq por el hecho de 
                           cumplimientoAlgunRequisito++;                                  //que existe la posibilidad de que sea menor al que estás tras de él y por ello no cumpla la condición                           
                           //te flata la unión del lado der al nuevo contenido y del lado izq al nuevo contenido... piensalo                           
                       } 
                   
                   }else if(jugadoresNuevos[numeroJugador].darRiquezasActuales()==nodoAuxiliar.contenido.darRiquezasActuales()){
                        jugadores.anadirAntesDe(jugadoresNuevos[numeroJugador], nodoAuxiliar);//por el hecho de que puede existir la posibilidad de que al que le añade depués [lo que estaba aquí antes] sea el primer nodo por lo tanto el anteriro sea null
                        nodoAuxiliar=nodoAuxiliar.anterior;//En este elseif donde se corrobora que los datos son < si deben estar en cada uno de los bloques de condiciones de < jerarq por el hecho de 
                        cumplimientoAlgunRequisito++;
                         
                   }//inserto depués del que estoy ubicada es decir después del nodo con valor igual (pues porque llego primero el que estaba xD)
                   
                 
                   nodoAuxiliar=nodoAuxiliar.siguiente;//si al dar break se salta esto, entonces no hay necesidad de hacer al nodo igual a su anterior porque de todos modos se quedará en el
                   //que enocntró el umplimiento de alguna de las condiciones de arriba
                   
                }//fin del while donde se recorre a los nodos del listado antiguao               
               
            }
            System.out.println("\nLISTADO ORDENADO PARA GUARDAR \n");
            NodoDoble<Jugador> nodoNavegador = jugadores.obtnerInicio();
            
            while(nodoNavegador!=null){
                System.out.println(nodoNavegador.contenido.obtnerNombre()+": "+String.valueOf(nodoNavegador.contenido.darRiquezasActuales())+", "+ String.valueOf(nodoNavegador.contenido.darEstadoGanador()));
                nodoNavegador=nodoNavegador.siguiente;
            }
            
            return jugadores;
    }
    
    /**Está configurado para mostrar en forma descendente al igual que todos los demás
     * métdodos de ordenamiento de aquí xD
     *
     * @param jugadoresNuevos
     * @return
     */
    public Jugador[] ordenarJugadoresDeMatrizPorNOmbres(Jugador[] jugadoresNuevos){//se hará otro documento par que no tenga que ordenar más de 6 datos luego de tener un gran listado, sino que solo lo tenga que hacer según el número de agregados recientes
         
        for(int numeroJugadorActual=0; numeroJugadorActual<jugadoresNuevos.length-1; numeroJugadorActual++){
                int indiceDelMaximo=numeroJugadorActual;
                
                for(int numeroJugadorSiguiente=numeroJugadorActual+1; numeroJugadorSiguiente< jugadoresNuevos.length;numeroJugadorSiguiente++){
                          if(jugadoresNuevos[numeroJugadorSiguiente].obtnerNombre().compareToIgnoreCase(jugadoresNuevos[indiceDelMaximo].obtnerNombre())>=0){
                                   indiceDelMaximo=numeroJugadorSiguiente; 
                          }//fin dle if
                }//fin del for que se encarga de buscar al número menor y asignar la nueva ubicación correspondiente
                
              Jugador jugadorTemporal=jugadoresNuevos[numeroJugadorActual];
              jugadoresNuevos[numeroJugadorActual] = jugadoresNuevos[indiceDelMaximo];
              jugadoresNuevos[indiceDelMaximo] = jugadorTemporal;              
        }                            
     
        return jugadoresNuevos;        
        
    }
    
    
    public ListaDoblementeEnlazada<Jugador> ordenarPorNombresListadoCompleto(Jugador[] jugadoresNuevos, ListaDoblementeEnlazada<Jugador> jugadoresAntiguos){
        ListaDoblementeEnlazada<Jugador> jugadores= jugadoresAntiguos;
        NodoDoble<Jugador> nodoAuxiliar=jugadores.obtnerInicio();//pues no quiero que cada vez regrese al nodo inicila, porque como el arreglo ya está
        //ordenado de la misma forma que el listado entonces se que media vez pasé de ese nodo ya no tengo necesidad de regregsar
      
            for(int numeroJugador = 0; numeroJugador < jugadoresNuevos.length; numeroJugador++) {
                int cumplimientoAlgunRequisito=0;
                
                while(nodoAuxiliar!=null && cumplimientoAlgunRequisito==0){//pero jamás llegará a ese punto porque el break lo impedirá si en dado caso se tuviera que 
                 //insertar al final, porque antes que llegue ahí se cortará el proceso
                 
                   if(jugadoresNuevos[numeroJugador].obtnerNombre().compareToIgnoreCase(nodoAuxiliar.contenido.obtnerNombre())>0){
                       if(nodoAuxiliar.anterior==null){//quiere decir que es el primero del listado
                           jugadores.anadirAlInicio(jugadoresNuevos[numeroJugador]);
                           cumplimientoAlgunRequisito++;
                       }else{
                          jugadores.anadirAntesDe(jugadoresNuevos[numeroJugador], nodoAuxiliar);
                          cumplimientoAlgunRequisito++;
                       }                       
                       
                       nodoAuxiliar=nodoAuxiliar.anterior;  //si recuerda que debe ser así porque no sabes que situación se genera con el que recientemente se acaba de hallar algo con un elemento
                           //del listado de los que se debe add, pues de ellos conocemos la situación con sus mismos comparañeros mas no la que se genera con los que ya estaban
                       
                   }else if(jugadoresNuevos[numeroJugador].obtnerNombre().compareToIgnoreCase(nodoAuxiliar.contenido.obtnerNombre())<0){
                   
                       if(nodoAuxiliar.siguiente==null){//eso queire decir que es el último nodo del listado
                           jugadores.anadirAlFinal(jugadoresNuevos[numeroJugador]);
                           cumplimientoAlgunRequisito++;
                           nodoAuxiliar=nodoAuxiliar.anterior;  
                           
                       }else if(jugadoresNuevos[numeroJugador].obtnerNombre().compareToIgnoreCase(nodoAuxiliar.siguiente.contenido.obtnerNombre())>=0){//pues si es igaul dará 0 y si es > 1
                               
                           jugadores.anadirDespuesDe(jugadoresNuevos[numeroJugador], nodoAuxiliar); 
                           nodoAuxiliar=nodoAuxiliar.anterior;//En este elseif donde se corrobora que los datos son < si deben estar en cada uno de los bloques de condiciones de < jerarq por el hecho de 
                           cumplimientoAlgunRequisito++;      //que existe la posibilidad de que sea menor al que estás tras de él y por ello no cumpla la condición
                           
                           //te flata la unión del lado der al nuevo contenido y del lado izq al nuevo contenido... piensalo                           
                       }    
                   
                   }else if(jugadoresNuevos[numeroJugador].obtnerNombre().compareToIgnoreCase(nodoAuxiliar.contenido.obtnerNombre())==0){
                        jugadores.anadirAntesDe(jugadoresNuevos[numeroJugador], nodoAuxiliar);//por la misma situcación que surgía en el ordenamiento por riquezas
                        nodoAuxiliar=nodoAuxiliar.anterior;//En este elseif donde se corrobora que los datos son < si deben estar en cada uno de los bloques de condiciones de < jerarq por el hecho de 
                        cumplimientoAlgunRequisito++;
                         
                   }//inserto depués del que estoy ubicada es decir después del nodo con valor igual (pues porque llego primero el que estaba xD)
                   
                 
                   nodoAuxiliar=nodoAuxiliar.siguiente;//si al dar break se salta esto, entonces no hay necesidad de hacer al nodo igual a su anterior porque de todos modos se quedará en el
                   //que enocntró el umplimiento de alguna de las condiciones de arriba
                   
                }//fin del while donde se recorre a los nodos del listado antiguao               
               
            }
            System.out.println("\nLISTADO ORDENADO POR NOMBRES PARA GUARDAR \n");
            NodoDoble<Jugador> nodoNavegador = jugadores.obtnerInicio();
            
            while(nodoNavegador!=null){
                System.out.println(nodoNavegador.contenido.obtnerNombre()+": "+String.valueOf(nodoNavegador.contenido.darRiquezasActuales())+", "+ String.valueOf(nodoNavegador.contenido.darEstadoGanador()));
                nodoNavegador=nodoNavegador.siguiente;
            }
            
            return jugadores;
    }
    
    
    public Jugador[] darOrdenadaMatrizSegun(int tipoOrden, Jugador[] jugadoresNuevos){
        if(tipoOrden==1){
           return ordenamientoMatrizBasadoEnRiquezas(jugadoresNuevos);           
        }
        
        return ordenarJugadoresDeMatrizPorNOmbres(jugadoresNuevos);
    }
    
    public ListaDoblementeEnlazada<Jugador> darListadoOrdenadoSegun(int tipoOrden, Jugador[] jugadoresNuevos, ListaDoblementeEnlazada jugadoresAntiguos){
        if(tipoOrden==1){
            return ordenarPorPunteoListadoCompleto(jugadoresNuevos, jugadoresAntiguos);
        }
        
        return ordenarPorNombresListadoCompleto(jugadoresNuevos, jugadoresAntiguos);
    }
    
    
    
    
    
}
