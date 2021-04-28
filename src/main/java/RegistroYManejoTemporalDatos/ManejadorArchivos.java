/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegistroYManejoTemporalDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

/**
 *
 * @author phily
 */
public class ManejadorArchivos {
    String jugadores[];
    Integer riquezas[];
    boolean ganador[];
    
    RegistroTemporalDatosPartida registro = new RegistroTemporalDatosPartida();
            
    
        File primerArchivo = new File("/proyectoFInalIPC1/archivoJugadores.txt");
    
    
       public ManejadorArchivos(String nombreJUgador[],Integer riquezasAdquiridas[], boolean Esganador[]){      
            jugadores=nombreJUgador;
            riquezas=riquezasAdquiridas;
            ganador=Esganador;            
        }   
 
       
       
       public void guardadoArchivos(){
            File archivo = new File("archivoJugadores.txt");
                    try (PrintStream impresoraTexto = new PrintStream(new FileOutputStream(primerArchivo, true))) {
                    
                impresoraTexto.println(jugadores.length);
                for(int jugador=0; jugador<jugadores.length; jugador++){
                    impresoraTexto.println(jugadores[jugador]);
                 }                
              for(int jugador=0; jugador<jugadores.length; jugador++){
                  impresoraTexto.println(riquezas[jugador]);
              }
              for(int jugador=0; jugador<jugadores.length; jugador++){
                  impresoraTexto.println(ganador[jugador]);
              } 
                
            } catch (Exception e) {
                System.out.println("Excepcion con archivo " + primerArchivo);
                e.printStackTrace();
            }
       
       }
       
       
       public void lecturaArchivo(){
            try (FileReader fileReader = new FileReader(primerArchivo);
            BufferedReader reader = new BufferedReader(fileReader)) {
                String contenido = "";
                String linea = reader.readLine();
                while (linea != null) {
                    contenido = contenido + linea + "\n";
                    linea = reader.readLine();
                }
                System.out.println("contenido del archivo:");
                System.out.println(contenido);
            } catch (Exception e) {
                System.out.println("Excepcion con archivo " + primerArchivo);
                e.printStackTrace();
            }
       }
        // lectura de alto nivel para texto
       
        
     

    
}
