/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;

import FrontendBancopoly.SolicitudDatos;
import Jugadores.Jugador;
import Tarjetas.Tarjeta;
import Tarjetas.TarjetaPagoTodos;
import java.awt.Font;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author phily
 */
public class TrampaPagoAOtros extends Trampa implements Serializable{//Solo podrá ser de un solo tipo cada casilla        
        //no es necesaria una listaCirular, por el hecho de que solo en el momento en que casiga sobre ella requiere teiner
        //la referncia a él para hacerle actuar conforme solicite, lo cual no evita que puedan haber más de 1 jugador en esta casilla(y en las demás) 
        //puesto que para estar en una casilla solamente se debe hacer referencia a ella, entonces al finalizar la acción srería bueno cortar de una vez
        //la referencia a la casilla donde se encuentra el jugador, para que así no hayan problemas como el que sucedería al llamar al método de acción, aún cuando el anterior ya lo haya exe
    
       String nombreCasilla;
       String descripcionCasilla;
       private JPanel casillaFisica;
       private int cantidad;//pago en este caso
       private Tarjeta tarjeta;                  
       
       public TrampaPagoAOtros(JPanel casillaTablero, int cantidadTrampa, String nombreDeCasilla, String descripcionTrampa){
           super(nombreDeCasilla, casillaTablero);           
           casillaFisica=casillaTablero;
           cantidad= cantidadTrampa;           
           descripcionCasilla=descripcionTrampa;
           nombreCasilla=nombreDeCasilla;
           crearTarjeta();
           definirFormaFisica();
           
       }
       
       @Override
        public void crearTarjeta(){//En este cao no es necesario un obj tipo tjt puesto que esta misma clase, puede exe la acción porque no requiere de ninguna validadción para hacerla
                                        //Esto no dará problemas puesto que siempre se invoca al método accion de la casilla, la cual acciona de diferente manera, ya sea por medio de un tjt o no
                tarjeta = new TarjetaPagoTodos(cantidad, SolicitudDatos.registroDatos.obtenerNumeroJugadores(), true);
    
        }//para esta hila trampa en partilcular, no es necesario crear tjtas al momento de crear la casilla, con lo cual no será necesario invocar este método al crear la casilla, y tampoco debería sobreescribirlo porque no lo voy a emplear
        //es decir que no lo tengo que llamar en el ctrctor
        
@Override
public void accion(Jugador jugadorLlegado){
        super.accion(jugadorLlegado);
        tarjeta.accion(jugadorLlegado);
    
        //jugador.pagar(cantidad);//aqupi necesitaría devolver el valor para que en la clase partida pueda recibir cada uno de ellos el respectivo "regalo"
        //eso implicaría no tener que usar este método si es que los demás no devuleven algo ó devuelven un tipo diferente

}

public void recibirTodosJugadores(Jugador jugadores[]){//pero dónde... y, está siendo usado este método?
    TarjetaPagoTodos tarjetaPago;
    
    tarjetaPago = (TarjetaPagoTodos) tarjeta;
    tarjetaPago.establecerJugadores(jugadores);
    //aquí mandas a llamar al método de la tarjeta que recibe a los jugadores
}//no olvides que este método es llamado nada más se tengan a todos los jugadores guargados

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

    //NO CAMBIA DE FORMA DURANTE UN MISMO JUEGO
}
