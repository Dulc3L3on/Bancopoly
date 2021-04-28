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
public class Premio extends Tarjeta implements Serializable{
    private String definicion;
    private int cantidad;//cantidad de regalo UwU
    String nombre="Premio";
    private boolean unica;
    
    public Premio(int regaloEconomico){
        super("Premio");      
        cantidad=regaloEconomico;
        unica=false;//Auqnue es redundante, puesto que su valor por defecto es falso
    }
    
    public Premio(String descripcion, int regaloEconomico){
        super("Premio");      
        definicion=descripcion;
        cantidad=regaloEconomico;
        unica=true;
    }
    
    /**
     *Se encarga de dar el premio económico al jugador en cuestión, esto luego de 
     * haber caido en la casilla y haber resultado esta tjt
     * @param solicitante
     */
    @Override
    public void accion(Jugador solicitante){        
        if(unica){
            JOptionPane.showMessageDialog(null,definicion ," Premio :) " , JOptionPane.INFORMATION_MESSAGE);
        }//no tiene otro JOP, puesto que no teiene una casilla exclusiva, lo cual provoca que yio xD sea quien le de la definición        
        definirAccionJugador(solicitante);
        Tablero.actualizarDatosTablaTurno();
    }
    
      protected void definirAccionJugador(Jugador jugadorAccionista){                   
        //se manda a llamar el método para establecer el incremento a las riquezas del jugador
        jugadorAccionista.recibirDInero(cantidad);
        jugadorAccionista.incrementarRiquezas(cantidad);
        
    }//fin del método que define la acción
    
}
