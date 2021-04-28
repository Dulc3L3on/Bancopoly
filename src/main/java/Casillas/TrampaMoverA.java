/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;


import FrontendBancopoly.CustomizacionTodasTarjetasDeUnTipo;
import Jugadores.Jugador;
import Tarjetas.Tarjeta;
import Tarjetas.TarjetaMoverA;
import java.awt.Font;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author phily
 */
public class TrampaMoverA extends Trampa implements Serializable{           
       String nombreCasilla;
       private String descripcionCasilla;
       private JPanel casillaFisica;
       private int cantidad;//ya no se empleó porque se entregó el dato de forma directa
       private int indiceCasillaADondeIr;
       private Tarjeta tarjeta;
       
       public TrampaMoverA(JPanel casillaTablero){
           super("Mover a...", casillaTablero);//recuerda que si no se muestra, es por el hecho de que no se está agarrando tiene nada esta var, la cual es la que agarra el método... con ello deberás quitar el parám del ctrct del padre y recibir directamente el dato de la hija
           casillaFisica=casillaTablero;         
           
           crearTarjeta();
           //definirFormaFisica();
       }
       
    @Override
    public void crearTarjeta(){    
        tarjeta = new TarjetaMoverA();//pues en esta yo doy la definición por no ser del tipo escoja una tjt
    }

    @Override
    public void accion(Jugador jugadorLlegado){
        super.accion(jugadorLlegado);
        tarjeta.accion(jugadorLlegado);       
    }

    public void definirFormaFisica(){//Al parecer esto sería lo que podrían compartir las casillas TRAMPA, pues ninguna tiene actualizaciones en su forma física, a menos que sea editada
        JLabel labelNombre;
        JLabel labelDescripcion;
        
        labelNombre=(JLabel) casillaFisica.getComponent(0);
        labelNombre.setText(nombreCasilla);
        labelNombre.setHorizontalTextPosition(SwingConstants.LEADING);
        labelNombre.setFont(new Font("Sawasdee", Font.BOLD ,18));
        
        labelDescripcion=(JLabel) casillaFisica.getComponent(2);
        labelDescripcion.setText(descripcionCasilla);
        labelDescripcion.setHorizontalTextPosition(SwingConstants.LEADING);
        labelDescripcion.setFont(new Font("Sawasdee", Font.BOLD ,18));          
        
        casillaFisica.updateUI();        
    }
    
    /**
     *  Llamado al presionar Listo y ser aprobado el diseño para empezar a jugar
     * es decir se hará antes de comenzar el juego 
     * @param indiceCasillaDestino
     */
    public void establecerIndiceAlCualIr(){//aqupi deberías recibir el último índice para poder hacer que avance una cdad que no alcance a esta casilla, no se requiere del número de índice de la casilla puesto que sin importar donde esté si camina 4 (y son 5) nollegará a la casilla en cuestión
        CustomizacionTodasTarjetasDeUnTipo dialogoMoverA = new CustomizacionTodasTarjetasDeUnTipo(new javax.swing.JFrame(), true);        
        //spiner.setModel(model);//aquí TIENES QUE DEFINIR EL LISTADO DE ÍNDICES DONDE, no permitirás que sea el índice que corresponde a esta casilla
        dialogoMoverA.establecerTItuloSegunCasilla(2);
        //dialogoMoverA.establecerRangoSpinner(2, ultimoIndice);
        dialogoMoverA.setVisible(true);
        
        TarjetaMoverA tarjetaAuxiliar;
        tarjetaAuxiliar= (TarjetaMoverA)tarjeta;
        tarjetaAuxiliar.establecerIndiceDestino(dialogoMoverA.entregarCasillaAIr());
        establecerDefinicionCasilla(dialogoMoverA.entregarDefinicion());//aquí recibiría el dato del diálogo
        
        definirFormaFisica();
    }
    
    public void establecerDefinicionCasilla(String definicion){
        descripcionCasilla=definicion;
    }
    
    //NO CAMBIA DE FORMA FÍSICA
    
}
//recuerda que te falta mandarle el índice máx de las casillas creadas, lo cual corresponde al número de casillas que
//podrá recorrer, sin caer en esta casilla, talvez podrías extraerlo de registro...