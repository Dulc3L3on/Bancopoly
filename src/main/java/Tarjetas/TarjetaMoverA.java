/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjetas;

import Jugadores.Jugador;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author phily
 */
public class TarjetaMoverA extends Tarjeta implements Serializable{         
        private String definicion;
        String nombre="MoverA";
        private int cantidad;//índiceDestino
        
        public TarjetaMoverA(){//cuando sea la tjt propia no recibirá nada pues el índice será establecido después de haber sido creada la instancia de esta y de la casilla
            super("MoverA");
            
        }//ya que el diálog se mostrará al aprobar el diseño, entonces pedir ahí de una vez todos los datos , no 1 por 1, para qué doble trabajo
        
        
         @Override
    public void accion(Jugador solicitante){
        if(definicion==null){
            JOptionPane.showMessageDialog(null,solicitante.obtnerNombre()+ "trasládate a  " + cantidad +" es una orden xD", "Moverse a", JOptionPane.WARNING_MESSAGE);//si siempre se exe este JOP, es porque no se sobreescribió la var 
        }else{
            JOptionPane.showMessageDialog(null,definicion, "Moverse a", JOptionPane.WARNING_MESSAGE);
        }        
        definirAccionJugador(solicitante);
    }
    
    protected void definirAccionJugador(Jugador jugadorAccionista){//recuerda que depende de la situación el mandamiento                
        //Aquí llamas al método moverseA del jugador [recuerda este una fusión de caminar y retroceder]
        //jugadorAccionista.obtenerUBicacion().quitarFicha(jugadorAccionista.obtnerColor());//pues recuerda, antes de mover un pie debes quitar el otro, además debes saber que color será el que se quittará, por tal razón se debe mandar el color del jugador en cuestión
        jugadorAccionista.moverseA(cantidad);//hace falta llamar al método de la tarjeta a la que se movió, lo cual deberá hacerse de forma explícita, solo que aún no se si 
        //deba ser en el método del jugador o aquí... p
        
    }//fin del método que define la acción
    
    /**
     * Método llamado por el de la casilla el cual es invocado hasta que se ha aprobado el diseño
     * es decir antes de empezar a jugar y por ello haber mostrado el diálogo y haber recibido el 
     * dato ingresado por el usuario
     * @param indiceDestino
     */
    public void establecerDatosFaltantes(String definicionTarjeta, int destino){     
            definicion=definicionTarjeta;        
            establecerIndiceDestino(destino);//al final nadie emplea este método mas que el de establecer DatosFaltantes
        
    }   
    
    public void establecerIndiceDestino(int indiceDestino){
        cantidad=indiceDestino;
    }    
}


//el diálogo es llamado desde la casilla, como se ha venido haciendo con las casillas que requieren de un diálog extra
//más que todo tb porque estas tjt's no tienen entre sus atributos a las casillas lógicas