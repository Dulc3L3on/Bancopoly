/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;

import Jugadores.Jugador;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author phily
 */
public class Trampa extends Casilla implements Serializable{
    private Jugador jugador;//no es necesaria la lista por el hecho de que solo en el momento en que caiga sobre ella le interesa el jugaor, de ahí en adelante no hay problema si cae otro porque es reemplazado, para que así pueda hacer lo mismo pero con el otro
   private String nombreCasilla ;//será útil para la tarjeta, pues debe contener un título en un lbl
   private String descripcionCasilla;
   private JPanel casillaFisica;
   private int cantidad;//en este caso depende puede ser dinero, turnos, o índice casilla
   

public Trampa(String nombreDeCasilla, JPanel casillaTablero){
        super(casillaTablero,nombreDeCasilla );//talvez se decida mejorar lo de las trampas y por ello se haría que definir forma física sea de esta clase, de la cual las 3 extienden y por ello aquí habría que add la descrpción, pues ya no la vuelven a tocar, al menos mientras el juego y por ello ellas, xD sigan igual
        nombreCasilla=nombreDeCasilla;//si no sucede nada con el hecho de que el padre sea quien reciba directamente esto, será mejor que sea colocado en el ctrctor del padre        
    }

@Override
public void crearTarjeta(){
    
    
    
}

@Override
public void accion(Jugador jugadorLlegado){
    super.accion(jugadorLlegado);
//Este método será sobreescrito por sus repectivas hijas, el comportamiento que podrían compartir sería el enviar los datos a la tjt física trampa, cuando se caiga en esta
}

private void actualizarFormaFisica(){

}


    
}
