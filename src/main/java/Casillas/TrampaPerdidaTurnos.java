/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;

import Jugadores.Jugador;
import Tarjetas.Tarjeta;
import Tarjetas.TarjetaPerdidaTurnos;
import java.awt.Font;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author phily
 */
public class TrampaPerdidaTurnos extends Trampa implements Serializable{      
       String nombreCasilla;
       private String descripcionCasilla;
       private JPanel casillaFisica;
       private int cantidad;
       Tarjeta tarjeta;       
       
       public TrampaPerdidaTurnos(JPanel casillaTablero, String nombreTrampa,  int turnosPerdidos, String descripcionTrampa){
           super(nombreTrampa, casillaTablero);//o como al parecer van a compartir el definirFormaFisica, entonces podrías hacer que el ctrct tb reciba la definición, puesto que eso ya no lo vuelven a tocar a menos que sean editadas [y por ello eliminadads, al menos por ahora, eso se haría al editar, eliminar]
           casillaFisica=casillaTablero;
           cantidad= turnosPerdidos;
           descripcionCasilla=descripcionTrampa;
           nombreCasilla =nombreTrampa;
           
           crearTarjeta();
           definirFormaFisica();
       }
       
    @Override
    public void crearTarjeta(){//lógica 
        tarjeta = new TarjetaPerdidaTurnos(cantidad, true);
    }

    @Override
    public void accion(Jugador jugadorLlegado){
        super.accion(jugadorLlegado);        
        tarjeta.accion(jugadorLlegado);        
    }

    public void definirFormaFisica(){
        JLabel labelNombre;
        JLabel labelDescripcion;
        
        labelNombre=(JLabel) casillaFisica.getComponent(0);
        labelNombre.setText(nombreCasilla);
        labelNombre.setHorizontalTextPosition(SwingConstants.CENTER);
        labelNombre.setFont(new Font("Sawasdee", Font.BOLD ,18));
        
        labelDescripcion=(JLabel) casillaFisica.getComponent(2);
        labelDescripcion.setText(descripcionCasilla);
        labelDescripcion.setHorizontalTextPosition(SwingConstants.CENTER);
        labelDescripcion.setFont(new Font("Sawasdee", Font.BOLD ,18));          
        
        casillaFisica.updateUI();
    }
    
    //NO CAMBIA DE FORMA FÍSICA
    
}
