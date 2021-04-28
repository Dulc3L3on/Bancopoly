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
public class TarjetaPerdidaTurnos extends Tarjeta implements Serializable{
        private String definicion;
        private int cantidad;    
        String nombre="PérdidaDeTurnos";
        private boolean unica;
        
    public TarjetaPerdidaTurnos(int turnosPerdidos, boolean esLaUnica){//empleado para la casilla propia y para personalizada... así que se puede saber si es necesario mostrar o no el diálgo dependiendo de la var única
        super("PérdidaDeTurnos");
        cantidad=turnosPerdidos;
        unica=esLaUnica;
    }
    
    public TarjetaPerdidaTurnos( String descripcion, int turnosPerdidos){
        super("PérdidaDeTurnos");
        cantidad=turnosPerdidos;
        definicion= descripcion;
        unica=true;
    }
    
       @Override
    public void accion(Jugador solicitante){
        if(definicion==null && unica){
            JOptionPane.showMessageDialog(null,solicitante.obtnerNombre()+ " tienes descanso gratis, por " + cantidad +" turnos disfútalo xD " , "Pérdida de turnos", JOptionPane.WARNING_MESSAGE);//si siempre se exe este JOP, es porque no se sobreescribió la var 
        }else if(definicion!=null && unica){
            JOptionPane.showMessageDialog(null,definicion, "Pérdida de turnos", JOptionPane.WARNING_MESSAGE);
        }        
        definirAccionJugador(solicitante);
    }
    
    protected void definirAccionJugador(Jugador jugadorAccionista){//recuerda que depende de la situación el mandamiento                
        jugadorAccionista.establecerTurnosPerdidos(cantidad);//recuerda que al ser encontrados != 0 estas var (esta y la de deuda por el momento) se decrementarán
        
    }//fin del método que define la acción
    
    
}
