/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjetas;

import Jugadores.Jugador;
import java.io.Serializable;

/**
 *
 * @author phily
 */
public class TarjetaPago extends Tarjeta implements Serializable{//para pago y multa
    private String nombre="Pago";
    private String definicion;
    int cantidad;    
    int cantidadPagos;
    int total;
    
    public TarjetaPago(int pago, int totalPago){
        super("Pago");
        cantidad = pago;
        total=totalPago;
    }
    
    @Override
    public void accion(Jugador solicitante){
        
        
    }
    
     protected void definirAccionJugador(Jugador jugadorAccionista){//recuerda que depende de la situación el mandamiento         
         jugadorAccionista.pagar(total);//sino funcionara lo haces desde las respectivas hijas o... iba a decir que mandaras el dato total, pero eso
         jugadorAccionista.decrementarRIquezas(total);
         //no sería muy bonito hacerlo puesto que no lo estas llamando desde un tipo de clase diferntes sino que desde una que tiene que ver con esta
     }
     
     
    
    
    
    
}
