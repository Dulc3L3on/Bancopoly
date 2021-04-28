/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjetas;

import Casillas.Casilla;
import Jugadores.Jugador;
import java.io.Serializable;
/**
 *
 * @author phily
 */
public class Tarjeta implements Serializable{//Sus respectivos datos, los obtiene de su casilla, esto aunque sea la casilla de tomar un tarjeta
    //JDialog dialogoCustomizacion;//Es creado aquí mismo, no todas las tjtas lo necesitan y además la fomar de crearla es directamente al declararla
    String nombre;
    private String definicion;
    private int cantidad;
    private Casilla casilla;//creo que está aquí por las que referencian a otras... pero pensándolo bien, no lo necesitan las tjt's, ninguna
    //no todas necesitn almacenar al jugador de forma permanente como en el caso de la tjt propiedad, ESTA AQUÍ POR VC (y tb por C creo xD) 
    
   //recuerda que en el método de acción de la CASILLA, se mandará a llamar al método de acción de la tarjeta, lo cual permitirá llamar al método de tjt que decolverá los datos según lo que haya sucedido en su diálogo, lo cual 
    //hará que se pueda actualizar la forma física de la casilla gracias a los datos recibidos del la tjta
    
   public Tarjeta(String nombreCasilla){//no recibirá nada, puesto que caulquier hija tjt requiere manipular los datos 
       nombre=nombreCasilla;
    }        
    
    private void mostrarDialogoDePartida(){//varía según la casilla y por ello la tarjeta, de tal forma que deba ser implementado el método en las hijas, está en el cuerpo de acción donde se escoge cual de los 2 mostrar
        //en algunas simplemente siempre será un JOP, y en otras al principio un diálogo para luego convertirse a un JOP
    }
    
    public void accion(Jugador solicitante){
        //si aquí es donde se ordena al jugador lo que debe hacer, entonces aquí debe existir una referencia a jugador para mandar directamente las acciones,para que al llamar al método de acción de la casilla llame a este y aquí mismo se 
        //asigne la tarea
        
        //PREGUNTa... la tarjeta actualizará directametne la casilla física o actualizará el o los atributos de la casilla para que esta en el bloeque acción los revise y pueda hacer el cambio debido en su forma física... me parece mejor esto 
        //último
        
        
    }
    
    private void definirAccionJugador(Jugador jugadorAccionista){//todas requieren recibir al jugador en cuestión para trabajar con él
        
    }
    
    public String obtenerNombre(){
        return nombre;
    }
    
    public Tarjeta obtenerme(){//Creo que solo será empleado por la tarjeta salga de la cárcel, pues ninguna otra se transfiere 
                                         //aunque también puede ser empleado, para tranferir a otro, pues el otro solo debería hacer .obtenerme
          return this;//debería devolver este objeto en particular xD
    }   
    
}
