/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegistroYManejoTemporalDatos;
import BackendBancopoly.ListaDoblementeEnlazada;
import FrontendBancopoly.SolicitudDatos;
import Jugadores.Jugador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author phily
 */
public class ManejadorDeArchivos {
    String path="/home/phily/Documentos/Carpeta_estudios/TercerSemestre/IPC1/proyectoFInalIPC1/listadoJugadoresPorRiquezas.list";
    String pathNombres="/home/phily/Documentos/Carpeta_estudios/TercerSemestre/IPC1/proyectoFInalIPC1/listadoJugadoresPorNombres.list";
    static String pathVersionesOriginales ="/home/phily/Documentos/Carpeta_estudios/TercerSemestre/IPC1/proyectoFInalIPC1/versionesOriginales/";//Recuerda que no lo debes modificar en nungp´un momento, revisas entonces cuando escribes el archivo
    static String pathReciente;
    static String nombreDeVersionOriginal;
    static int haAceptadoAbrir;
    
    ListaDoblementeEnlazada<Jugador> listado= new ListaDoblementeEnlazada();//este será el lsitado que se tendrá lleno luego de haber leído si es que existe un archivo en la ruta de arriba
    
    
    public void escribirArchivo(){        
        JFileChooser ventanaGuardado= new JFileChooser();
        ventanaGuardado.setCurrentDirectory(new File("."));
        ventanaGuardado.setDialogTitle("Guardar Partida");
        ventanaGuardado.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ventanaGuardado.setAcceptAllFileFilterUsed(false);
        
        if(ventanaGuardado.showOpenDialog(ventanaGuardado)==JFileChooser.APPROVE_OPTION){
            File carpetaSeleecionada = ventanaGuardado.getSelectedFile();
            String direccion = carpetaSeleecionada.getAbsolutePath()+"/"+SolicitudDatos.registroDatos.obtenerNombreJuego()+".board";
            pathReciente=direccion;
            System.out.println("Path [guardadoJuego]-> "+ direccion + "\n");
            
            
            //Recuerda que para el caso de los obhjetos lo que se hace es envolver lo que se va a guardar en forma de objeto para que así cuando se lea se pueda devolver como un objteo lo que del 
            //archivo se extrajo de tal manera que cuando se requiera de la información pueda tratarse toda (aquella información que fue serializada) tratarse por lo que originalemnte era -> un 
            //objeto... pero se requiere enviar un objeto del tipo fileOutputStream para que de esa manera se pueda guardar (o recibir) datos en un archivo específico
            
            try(ObjectOutputStream envolturaObjeto = new ObjectOutputStream(new FileOutputStream(direccion))){//se usa un try catch con recursos para evitar la parte del finally                
                envolturaObjeto.writeObject(SolicitudDatos.registroDatos);//aquí es donde mandas a escribir el objeto tal y como está, en este caso será el objeto registro
                System.out.println("Al guardar->"+ "\n jugadores: "+Arrays.toString(SolicitudDatos.registroDatos.darJugadoresEnPartida())+"\nrecorrido lógico: "+
                        SolicitudDatos.registroDatos.ObtenerRecorridoLogico()+"\nrecorrido físico: "+SolicitudDatos.registroDatos.ObtenerRecorridoFisico());                
                
                //puede que sea porque le mandaste a guardar los datos con una var de tipo referencia estática... pero de primero mira la pila de errores
                
            }catch(IOException error){
                error.printStackTrace();
                JOptionPane.showMessageDialog(ventanaGuardado, "No se guardó correctamente el archivo", "Error de guardado", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//fin del método para guardar el archivo
    
    public static void escribirVersionOriginal(){
        String direccionCOmpleta;
        direccionCOmpleta=pathVersionesOriginales+SolicitudDatos.registroDatos.obtenerNombreJuego()+".VrsO";
        
        try(ObjectOutputStream envolturaObjeto = new ObjectOutputStream(new FileOutputStream(direccionCOmpleta))){//se usa un try catch con recursos para evitar la parte del finally                
                envolturaObjeto.writeObject(SolicitudDatos.registroDatos);//aquí es donde mandas a escribir el objeto tal y como está, en este caso será el objeto registro
                System.out.println("\nversión original guardada CORRECTAMENTE\n");                
                
                //puede que sea porque le mandaste a guardar los datos con una var de tipo referencia estática... pero de primero mira la pila de errores
                
            }catch(IOException error){
                error.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se guardó correctamente el archivo", "Error de guardado", JOptionPane.ERROR_MESSAGE);
            }
        
    }
   
    /**
     * Este será el método para editar la partida y el de arriba para cargarla, donde el método de arriba al leer que 
     * el temporizador está en 0:0:0 mandará a llamar al método del filechooser para obetner el nombre del juego y así
     * usar la versión original de él...
     * @param direccion
     * @return
     */
    public static RegistroTemporalDatosPartida leerVersiones(String direccion){//leer versiones se llamará y ahora lo que sucederá es que recibirá la dirección completa sin importar si es una versión con progreso o la original, pues en sus afueras se encargarán de este proceso y este bloque solo leerá
     
        if(direccion!=null){                                   
            try(ObjectInputStream envolturaObjeto = new ObjectInputStream(new FileInputStream(direccion))){//recuerda esta try ces con recursos, así que tb cierra el arch
                
                System.out.println("Lectura exitosa?\n");
                return (RegistroTemporalDatosPartida) envolturaObjeto.readObject();//recuerda que la envoltura lo que hace es convertir ese flujo recibido del archivo al tipo por el cual se le debe trabtar, en este caso tipo OBJETO, dicha conversión permite hacer parseos y tratar a la info como lo requiere
            }catch(IOException execpcion){
                execpcion.printStackTrace();
                JOptionPane.showMessageDialog(null, "No es posible recibir los\n datos solicitados", "Error de recepción de datos", JOptionPane.ERROR_MESSAGE);
                
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "No es posible realizar la\n conversión solicitada", "Conversión denegada", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        System.out.println("Lectura fallida");//porque no seleccionó ruta alguna
        return null;
    }
    
    /**
     * Se encarga de mostrar el JFileChooser par lectura y devuelve los datos según el #
     * 1-> nombre
     * 2-> direccion
     * 
     * @param tipoDatoRetornar
     * @return
     */
    public static String mostrarJFileChooser(int tipoDatoRetornar){//1-> nombre 2-> direccionCompleta
        JFileChooser ventanaLectura = new JFileChooser();

        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo archivos .board","board");//esto para evitar que pueda seleccionar cualquier otra extesión que no sea la requerida
        ventanaLectura.setFileFilter(filtro);
        
        if(ventanaLectura.showOpenDialog(ventanaLectura)==(haAceptadoAbrir=JFileChooser.APPROVE_OPTION)){
            if(tipoDatoRetornar==1){
                nombreDeVersionOriginal=ventanaLectura.getSelectedFile().getName();
                String[] nombre = nombreDeVersionOriginal.split("\\.");
                
                return nombre[0];
            }
            
            pathReciente=ventanaLectura.getSelectedFile().getAbsolutePath();
            return pathReciente;
        }        

        
        return null;//es decir que no seleccionó ningún solo archivo y por lo tanto no hay que hacer nada  
    }
    
    /**
     * En este caso lo que sucede es que el jugador en si no manipula este archivo por ello yo puedo darle una ubicación de 
     * creación específica de tal manera que deba revisar si existe o no el archivo para saber si puedo ordenar de una vez
     * o tengo que revisar los dato guardados para ordenar a base de ellos, cabe resaltar que el orden predefinido será el
     * basado en las riquezas de manera descendente
     * @param jugadores
     */
    public void escribirArchivoListado(ListaDoblementeEnlazada<Jugador> jugadores, String direccion){//pues de esta manera se logra escribir cualquiera de los dos listados con 1 solo método     
       //no olvides que al escribir si no existe el archivo entonces automáticamente se crea sino se sustituye
        
       try(ObjectOutputStream envolturaObjeto = new ObjectOutputStream(new FileOutputStream(direccion))){
           
           envolturaObjeto.writeObject(jugadores);
          
       }catch(IOException exception){
            System.out.println("Error al intentar escribir listado de jugadores");       
            exception.printStackTrace();            
       }                     
    
    }//en este caso como el archivo será cada vez reemplazado recuerda que lo que harás será añadir al objeto como tal y no al archivo con append
    
    public String escogerListado(int tipoListado){
        if(tipoListado==1){
            return path;                
        }
        
        return pathNombres;    
    }
    
    /**
     *  Estos métodos solo se engargan de hacer lo suyo a parte debes verificar si puedes o no
     *  solicitar sus servicios, esto si existe o no el archivo, de manera respectiva
     * @param path
     * @return
     */
    public ListaDoblementeEnlazada<Jugador> leerArchivoListado(String direccion){
        try(ObjectInputStream salida = new ObjectInputStream(new FileInputStream(direccion))){
           
           return (ListaDoblementeEnlazada<Jugador>) salida.readObject();
          
       }catch(IOException exception){
            System.out.println("Error al intentar leer listado de jugadores");       
            exception.printStackTrace();            
       } catch (ClassNotFoundException ex) {                     
           System.out.println("Error en la conversión de clase");       
            ex.printStackTrace();            
        }                     
        
        return null;
        
    }
    
    /**
     * Este método es empleado para la parte de la edición
     */
    public static void reescribirArchivo(){
          
           try(ObjectOutputStream envolturaObjeto = new ObjectOutputStream(new FileOutputStream(pathReciente))){//se usa un try catch con recursos para evitar la parte del finally                
                envolturaObjeto.writeObject(SolicitudDatos.registroDatos);//aquí es donde mandas a escribir el objeto tal y como está, en este caso será el objeto registro
                System.out.println("Al guardar->"+ "\n jugadores: "+Arrays.toString(SolicitudDatos.registroDatos.darJugadoresEnPartida())+"\nrecorrido lógico: "+
                        SolicitudDatos.registroDatos.ObtenerRecorridoLogico()+"\nrecorrido físico: "+SolicitudDatos.registroDatos.ObtenerRecorridoFisico());                
                
                //puede que sea porque le mandaste a guardar los datos con una var de tipo referencia estática... pero de primero mira la pila de errores
                
            }catch(IOException error){
                error.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se guardó correctamente el archivo", "Error de guardado", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    /**
     * De esta manera puede revisarse de forma general si el archivo en el path dado existe
     * @param path
     * @return
     */
    public boolean verificarArchivo(String path){
        File archivo = new File(path);
        
        return archivo.exists();        
    }
    
    public static String darPathReciente(){//este método será útil para continuar partida, al presionar Continuar partida
        return pathReciente;
    }       
    
    public static int haAceptadoAbrir(){
        return haAceptadoAbrir;
    }
    
    /**
     * Este método será util para llamar al método que se encarga de leer la versión
     * original del juego que se decidió empezar desde 0, en el menú de decisión final
     * @return
     */
    public static String darNobmreDelJuegoReciente(){
        return nombreDeVersionOriginal;
    }
    
    public static String darPathVersionOriginal(){
        return pathVersionesOriginales;
    }
                  
}//Aquí se encuentran los métodos para guardar de forma binaria ( y deplano que también los métodos para guardar archivos de texto)
