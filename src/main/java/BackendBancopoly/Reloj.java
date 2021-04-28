/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBancopoly;

import FrontendBancopoly.SolicitudDatos;
import FrontendBancopoly.Tablero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author phily
 */
public class Reloj {
    private int horas;
    private int minutos;
    private int segundos;
    
    Timer timer;       
    
    public Reloj(){//debería emplearlo solo cuando luego de haber recuperado un archivo pero... no me deja tener otro ctrctor que no tenga parámetros
        horas= SolicitudDatos.registroDatos.obtnerHorasRestantes();
        minutos= SolicitudDatos.registroDatos.obtnerMInutosRestantes();
        segundos= SolicitudDatos.registroDatos.obtnerSegundosRestantes();                
    }
    
    public void empezar(){
      timer = new Timer(1000, new ActionListener(){
       @Override
       public void actionPerformed(ActionEvent e){
           
           
           if(segundos<60){
                segundos++;
                
           }else{
               segundos=0;
               minutos++;
                                
                if(minutos==60){    
                    minutos=0;
                    horas++;
                }
           }                                                                                          
           
           Tablero.labelesTiempo[0].setText(String.valueOf(horas));
           Tablero.labelesTiempo[1].setText(String.valueOf(minutos));
           Tablero.labelesTiempo[2].setText(String.valueOf(segundos));                          
           
          }//fin del actionListener       
          
      });
        
        timer.start();
    }
    
    /**
     * Será empleado en la opción del tablero donde se guarda la partida... aquí
     * no se empleó a registro de una vez para mandar a guardar por el hecho de que 
     * puede que sea una partida que no se haya guardado en ningún momento, entonces se
     * haría en vano [lo cual sucedería si este método se exe en lo del listener de arriba
     */
    public void pararReloj(){
        if(Integer.parseInt(Tablero.labelesTiempo[0].getText())!=0 && Integer.parseInt(Tablero.labelesTiempo[1].getText())!=0 && Integer.parseInt(Tablero.labelesTiempo[2].getText())!=0){
            timer.stop();
        }        
    }
    
    public void comenzarDeNuevo(){
        timer.restart();
    }//este para cuando hayan terminado una partida y seleccionen jugar de nuevo y la opción de 
     //"usar el mismo tiempo" sino pues se les mostrará una ventana para que configuren la nueva cdad de tiempo
    
    public void entregarTiempoRestante(){
        SolicitudDatos.registroDatos.recopilarHorasRestantes(horas);
        SolicitudDatos.registroDatos.recopilarMinutosRestantes(minutos);
        SolicitudDatos.registroDatos.recopilarSegundosRestantes(segundos);
    }
    
    
    
}
