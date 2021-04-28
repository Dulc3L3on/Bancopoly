/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBancopoly;

import FrontendBancopoly.SolicitudDatos;
import Jugadores.Jugador;
import RegistroYManejoTemporalDatos.ManejadorDeArchivos;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author phily
 */
public class Ranking{
    final static String PATH ="/home/phily/Documentos/Carpeta_estudios/TercerSemestre/IPC1/proyectoFInalIPC1/listadoJugadoresPorRiquezas.list";
    final static String PATH_NOMBRES ="/home/phily/Documentos/Carpeta_estudios/TercerSemestre/IPC1/proyectoFInalIPC1/listadoJugadoresPorNombres.list";
    
    
    
    public static void anadirAlRanking(int tipoListado){                  
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();//no creo que los tenga que serializar porque se crean cada vez que llamo al método además están
        File archivoListado = new File(manejador.escogerListado(tipoListado));      
        Ordenamiento ordenamiento = new Ordenamiento();//dentro de un método y yo asumo que esta interfaz los obvía, si provoca esto error, tendrás que volver este método static y hacer la instanciación del file local y no global, así de esa manera se obviará esa línea de jugadores y no sucederá nada mal...
        
        
        if(archivoListado.exists()){
            ListaDoblementeEnlazada<Jugador> listadoGuardado = manejador.leerArchivoListado(manejador.escogerListado(tipoListado));            
            Jugador[] listadoAgregados = ordenamiento.darOrdenadaMatrizSegun(tipoListado, crearListadoConLoNecesario());
                        
            manejador.escribirArchivoListado(ordenamiento.darListadoOrdenadoSegun(tipoListado, listadoAgregados, listadoGuardado), manejador.escogerListado(tipoListado));                        
        }else{
            ListaDoblementeEnlazada<Jugador> listadoAGuardar = new ListaDoblementeEnlazada();
            listadoAGuardar = listadoAGuardar.convertirALista(ordenamiento.darOrdenadaMatrizSegun(tipoListado, crearListadoConLoNecesario()));//si da un null pointer será por esto, en ese caso el método de conversión lo tendré que hacer aquí            
            
            manejador.escribirArchivoListado(listadoAGuardar, manejador.escogerListado(tipoListado));
        }            
    }    

    public static void eliminarRanking(){
        File archivoListado = new File(PATH);      
        File archivoListadoPorNOmbres = new File(PATH_NOMBRES);
        
        if(archivoListado.exists()){//ambos debes existir pues se escriben al mismo tiempo, pero para evitar alargar esto mejor se harán if's separados
            archivoListado.delete();
        }
        
        if(archivoListadoPorNOmbres.exists()){
            archivoListadoPorNOmbres.delete();
                
        }else if(!archivoListadoPorNOmbres.exists() && !archivoListado.exists()){
            JOptionPane.showMessageDialog(null, "No hay datos que eliminar", "No existen datos", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    public static String[][] crearListadoDatos(boolean esAscendente, int tipoListado, int tipoVisualizacion){
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        ListaDoblementeEnlazada<Jugador> listadoRanking = visualizaciones(tipoVisualizacion, manejador.leerArchivoListado(manejador.escogerListado(tipoListado)));
            String datosJugadores[][]= new String[listadoRanking.darTamanio()][3];
            
            if(!esAscendente){
                for (int numeroJugadores = 0; numeroJugadores < listadoRanking.darTamanio(); numeroJugadores++) {
                    datosJugadores[numeroJugadores][0]=listadoRanking.darElementoEn(numeroJugadores).obtnerNombre();
                    datosJugadores[numeroJugadores][1]=String.valueOf(listadoRanking.darElementoEn(numeroJugadores).darRiquezasActuales());
                    datosJugadores[numeroJugadores][2]=(listadoRanking.darElementoEn(numeroJugadores).darEstadoGanador())? "ganador":"perdedor";                
                }
            }else{//lo tuve que hacer así porque no se como decirle al modelo de la tabla que lea el arreglo al revés... pues realemtne solo es necesario ordenar de una manera y leer al revés para la otra
                int indiceParaListadoMostrar=0;
                
                for (int numeroJugadores = listadoRanking.darTamanio()-1; numeroJugadores >=0; numeroJugadores--) {                                        
                    datosJugadores[indiceParaListadoMostrar][0]=listadoRanking.darElementoEn(numeroJugadores).obtnerNombre();
                    datosJugadores[indiceParaListadoMostrar][1]=String.valueOf(listadoRanking.darElementoEn(numeroJugadores).darRiquezasActuales());
                    datosJugadores[indiceParaListadoMostrar][2]=(listadoRanking.darElementoEn(numeroJugadores).darEstadoGanador())? "ganador":"perdedor";                
                    
                    indiceParaListadoMostrar++;
                }
            }//además es así solo con el else porque puede que no presionen nada, entonces de esta manera se evitará una excepción
            
        return  datosJugadores;
            
    }
    
    /**
     *  Este método es útil para crear un objeto del mismo tipo que se guardará, que solo contendrá los 
     *  datos que en el ranking se requieren, esto para evitar guardar datos de más y como ant tb se guarda
     *  este objeto pero para otro fin, entonces es doblemente innecesario guardar los otros datos
     * @return
     */
    public static Jugador[] crearListadoConLoNecesario(){
        Jugador[] listado = new Jugador [SolicitudDatos.registroDatos.darJugadoresEnPartida().length];
                
        for(int numeroJugador = 0; numeroJugador < SolicitudDatos.registroDatos.darJugadoresEnPartida().length; numeroJugador++) {
           listado[numeroJugador] = new Jugador(SolicitudDatos.registroDatos.darJugadoresEnPartida()[numeroJugador].obtnerNombre(), 
                   SolicitudDatos.registroDatos.darJugadoresEnPartida()[numeroJugador].darRiquezasActuales(), 
                   SolicitudDatos.registroDatos.darJugadoresEnPartida()[numeroJugador].darEstadoGanador());
        }
        
        return listado;
    }
    
    /**
     *  Este método se aplica luego de haber ordenado el arreglo según
     *  el tipo de dato guía, por lo cual recibirá como argumento el 
     *  arreglo ya ordenado para solamente filtrar los datos.-> 1 para solo ganadores    2-> perdedores  -> 0 ambos 
     * 
     * @param peticionVisualizacion
     * @param listadoOrdenadoSegunPeticion
     * @return 
     */
    public static ListaDoblementeEnlazada<Jugador> visualizaciones(int peticionVisualizacion, ListaDoblementeEnlazada<Jugador> listadoOrdenadoSegunPeticion){
        ListaDoblementeEnlazada<Jugador> listadoFiltrado = listadoOrdenadoSegunPeticion;
        
        if(peticionVisualizacion==1){//es decir solo ganadores
            //aquí lo que podrías hacer es emplear el método emplear hasta solo que de la manera en la que recibe el parám de inicio y final[lo cual es maás general
            //que es más conveniente], deonde dichos parám serán el número de nodo en el que se encontró lo contrario a los olititado y el último parám el último nodo
            //en el que se halló dicha contrariedad ó crear otra lista donde parsarás los datos que están validados para pasar... 
            
            listadoFiltrado= filtrar("false", listadoFiltrado);            
            
        }else if(peticionVisualizacion==2){
            //Al igual que en el if de arriba mandarías a llamar al método para borrar hasta y así enlazar la lista obviando el dato según el tipo de visualización
            listadoFiltrado= filtrar("true", listadoFiltrado); 
        }
        
        return listadoFiltrado;
    }
    
    public static  ListaDoblementeEnlazada<Jugador> filtrar(String datoObviar, ListaDoblementeEnlazada<Jugador> listadoAFiltrar){        
     ListaDoblementeEnlazada<Jugador> listadoFiltrado= new ListaDoblementeEnlazada();
     NodoDoble <Jugador> nodoAuxiliar=listadoAFiltrar.obtnerInicio();
     
     
     for(int numeroNodo=0; numeroNodo< listadoAFiltrar.darTamanio(); numeroNodo++){
         if(!String.valueOf(nodoAuxiliar.contenido.darEstadoGanador()).equalsIgnoreCase(datoObviar)){
             listadoFiltrado.anadirAlFinal(nodoAuxiliar.contenido);
         }
         
         nodoAuxiliar=nodoAuxiliar.siguiente;
     }
     
     return listadoFiltrado;
        
    }
    
    
    public static String darPath(int tipoArchivo){        
        if(tipoArchivo ==1){
            return PATH;
        }
                     
        return PATH_NOMBRES;
    }
}
