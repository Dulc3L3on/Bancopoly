/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;

import FrontendBancopoly.Tablero;
import Jugadores.Jugador;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author phily
 */
public class Inicio extends Casilla implements Serializable{//recuerda esto cada vez que le hagan referencia
    //private JPanel casillaFisica;
    //private String nombreCasilla;
    //private int cantidad;   
 
    public Inicio(JPanel casillaTablero, int dineroPorVuelta){//deberá ser llamado luego de haber aceptado el inicio,por el hecho de que esta casilla no está entre las op del cbBx de la customización de casillas
        super(casillaTablero, "Inicio");        
        casillaFisica=casillaTablero;
        cantidad=dineroPorVuelta;
        nombreCasilla="Inicio";
    }
    
    @Override
    public void accion(Jugador jugadorLlegado){
        super.accion(jugadorLlegado);
        
        System.out.println("DInero por vuelta completada "+cantidad);//solo para corroborar que se esté mostrando la cantidad correcta, es decir se sobreescriba bien
        JOptionPane.showMessageDialog(null, "Recibes $ "+ cantidad+ "por completar la vuelta, emplealos sabiamente", " ", JOptionPane.INFORMATION_MESSAGE);//Se llama a tjt para que muestre el diálogo correspondiente
        jugadorLlegado.recibirDInero(cantidad);//Se mandará a la var de dinero del jugador la respectiva cantidad                
        jugadorLlegado.incrementarRiquezas(cantidad);
        Tablero.actualizarDatosTablaTurno();
    }    
    
}
