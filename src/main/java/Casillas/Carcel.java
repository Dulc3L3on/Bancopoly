/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;

import Jugadores.Jugador;
import java.awt.Font;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author phily
 */
public class Carcel extends Casilla implements Serializable{
    JPanel casillaFisica;
    private String nombreCasilla;
    private int cantidad;//se sobreescribirá con los turnos restantes
    
    public Carcel(JPanel casillaTablero){
        super(casillaTablero, "Cárcel");
        casillaFisica=casillaTablero;
        nombreCasilla="Cárcel";
        definirFormaFisica();
    }
    
    @Override
     public void crearTarjeta(){//en realidad es una lista circular simple, con comportamiento de pila fusionada con cola
        //Aquí esta tarjeta solo mostrará cuanto tiempo le queda par aestar en ella, pero eso quiere decir que debería aparecer
        //cada que se pase el cursor sobre ella-- si quieres, sino no miestra nada
        
        
    }       
    
    @Override
    public void accion(Jugador jugadorLlegado){//por el momento no tiene una acción obligatoria
    // sería bueno talvez que al darle clic el jugador encarcelado a la cárcel, se le permita salir solo sí posee tjt... aunque
    //sale mejor el hecho de cbr la referencia al emplear esta última
        super.accion(jugadorLlegado);
    }
    
    private void definirFormaFisica(){
          JLabel labelNombre= (JLabel)casillaFisica.getComponent(2);
            labelNombre.setFont(new Font("Sawasdee", Font.BOLD ,16));
            labelNombre.setHorizontalTextPosition(SwingConstants.CENTER);
            labelNombre.setText("CÁRCEL");
            
            //Aquí iría el lbl que contendría la imagen
            casillaFisica.updateUI();
    }
    
    private void actualizarFormaFisica(){
        //este sería para quitar la ficha o el color del jugador que dentro de ella se encuentra, siendo uno mismo 
        //para las 2 situacione posibles de estancia, temporal o por algo de tiempo, lo cual para ser general
        //Se deberá basar en la var del jugador que almacena el tiempo de condena, para saber que cuando sea 0
        //puede remover dicha marca del jugador sobre de ella
        
        //solo tendría que colocar la ficha resopectiva, de la duración de estas sobre esta casilla, dependería directamente
        //del jugador, puesto que él mismo es quien hace el cambio de rederencia, que en ese caso se vería frenado,por el hecho de 
        //poseer cierto teimpo de condena
    }
    
    //eso de remover la marca del jugador que se encuentra en su cuerpo físico, no es solo de esta casilla,
    //sino de TODAS
}
