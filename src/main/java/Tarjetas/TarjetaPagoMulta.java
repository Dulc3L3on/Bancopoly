/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjetas;

import FrontendBancopoly.Tablero;
import Jugadores.Jugador;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author phily
 */
public class TarjetaPagoMulta extends Tarjeta implements Serializable{
    
     private String definicion;
    private int cantidad;//cantidad multa _;|
    String nombre = "Multa";
    private boolean unica;
    
    public TarjetaPagoMulta(int multa){//no es única [está mezclada con otra]
        super("Multa");        
        cantidad=multa;        
        unica=false;
    }
    
    public TarjetaPagoMulta(String descripcion, int multa){
        super("Multa");     
        definicion=descripcion;
        cantidad=multa;        
        unica=true;
    }
    
    /**
     *Se encarga de enviar el debido decremento a las riquezas del jugador en cuestión
     */
    @Override
    public void accion(Jugador jugadorLlegado){        
        if(unica){
            JOptionPane.showMessageDialog(null, definicion, "Multa" , JOptionPane.WARNING_MESSAGE);        
        }        
        definirAccionJugador(jugadorLlegado);
        Tablero.actualizarDatosTablaTurno();//recuerda, esto es para actualizar los datos en el momento y así se pueda visualiza
    }
    
     protected void definirAccionJugador(Jugador jugadorAccionista){                   
         //se invoca al método que actualiza las riquezas en este caso restándole a ellas
        jugadorAccionista.pagar(cantidad);     
        jugadorAccionista.decrementarRIquezas(cantidad);
    }//fin del método que define la acción
    
}
