/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;
import Jugadores.Jugador;
import Tarjetas.Tarjeta;
import Tarjetas.TarjetaPropiedad;
import Tarjetas.TarjetaPropiedadServicio;
import java.awt.Font;
import java.io.Serializable;
    import javax.swing.*;

/**
 *
 * @author phily
 */
public class Servicio extends Propiedad implements Serializable{    
    
     JPanel casillaFisica;
     String nombrePropiedad;
     //int precioPropiedad;
     int cantidad;//pago por estancia
    private int precioHipoteca;//aún no se si será individual o general, si es general, entonces se inicializará en el ctrctor del padre    
    private boolean estadoPropiedad;
    private boolean hipotecada;
      Tarjeta tarjeta;  
      Jugador dueno;
    
         
    public Servicio(JPanel casillaTablero, int interes, String nombreLugar, int pagoEstancia, int precioDePropiedad ){
        super(interes, casillaTablero, nombreLugar, pagoEstancia, precioDePropiedad);
        casillaFisica=casillaTablero;
        cantidad=pagoEstancia;        
        nombrePropiedad=nombreLugar;
        //precioPropiedad=precioDePropiedad;
        crearTarjeta();
        formaFisicaInicial();
    }
    
    @Override
     public void crearTarjeta(){
     tarjeta = new TarjetaPropiedadServicio(nombrePropiedad, precioPropiedad, cantidad, this);
    }
    
    @Override
    public void accion(Jugador jugadorLlegado){
        super.accion(jugadorLlegado);
        
         if(!hipotecada){//pues porque si lo está no debería de hacer nada en ningún caso en los que actúa, por medio de la tjt
                //Aqpi se mandará a llamara a la tjt, la cual según el estado de la propiedad, mostrará uno u otro tipo de dialogo
                //se madará a llamar a casilla para hacerle las respectivas actualizaciones                
                tarjeta.accion(jugadorLlegado);//con esto ya se manda a exe las diferentes acciones, que varían según la decisión
                TarjetaPropiedad tarjetaAuxiliar = (TarjetaPropiedadServicio) tarjeta;//Asumo que se queda como de tipo tarjeta la "tarjeta", eso espero
                dueno=tarjetaAuxiliar.retornarDueno();//necesito tener aquí el dueño, pues eso me será útil para actualizar la forma física y talvez después para corroborar si es dueño de todos los grupos, aunque eso se haría 
                actualizarFormaFisica();//revisando directament en jugador por lo cual solo será necesario ver que grupos tieney cuantos integrantes de su totalidad
         }
         //recuerda que para cuando hipotequen/deshipotequen, se mandarán a llamar los métodos de estas casillas para que puedan repintarse según la situación en la que se encuentren
         //de tal manera que se actualice al entrar y haber recien hecho la hipoteca o la deshipoteca xD, pues con la actualización de arriba, harbía que espera que se volviera a referenciar 
         //a la casilla para que esta hiciera referencia a su método acción y así se colocara nuevamente el formato de cuando no está hipotecada, es decir donde se muestra el dueño
    }
    
    public void actualizarFormaFisica(){//Estos debería ir en el padre, pero no se si al estar ahí podría recoger los datos de cada hija, revisa Konquest
         if(dueno!=null){//quiere decir que ya fue adquirida
            estadoPropiedad=true;
            JLabel labelDefinicion= (JLabel)casillaFisica.getComponent(2);
            labelDefinicion.setFont(new Font("Sawasdee", Font.BOLD ,16));
            labelDefinicion.setHorizontalTextPosition(SwingConstants.CENTER);
            labelDefinicion.setText("Dueño: "+ dueno.obtnerNombre());//podrías crear otro lbl para colocar ahí el nombre del dueño, pero habría que tener cuidado por el hecho de que las casillas, varían por el tamaño
            casillaFisica.updateUI();
        }
    }
    
     public void formaFisicaInicial(){
        JLabel labelNombre= (JLabel)casillaFisica.getComponent(0);        
        labelNombre.setFont(new Font("Sawasdee", Font.BOLD ,18));
        labelNombre.setText(nombrePropiedad);
        
        JLabel labelDefinicion= (JLabel)casillaFisica.getComponent(2);
        labelDefinicion.setFont(new Font("Sawasdee", Font.BOLD ,16));
        labelDefinicion.setHorizontalTextPosition(SwingConstants.CENTER);
        labelDefinicion.setText("Precio compra $ "+String.valueOf(precioPropiedad));      
        
        casillaFisica.updateUI();
    }
    
    public void establecerAValoresIniciales(){
        estadoPropiedad=false;           
        hipotecada=false;
        TarjetaPropiedad tarjetaAuxiliar = (TarjetaPropiedad) tarjeta;
        tarjetaAuxiliar.reestablecerEstadoPropiedad();
        formaFisicaInicial();
    }//recuerda algo general se puede hacer específico,pero algo específico no puede volverse a general
    
    public void establecerFormatoHipotecada(){
        JLabel labelDefinicion= (JLabel)casillaFisica.getComponent(2);
         labelDefinicion.setFont(new Font("Sawasdee", Font.BOLD ,16));
         labelDefinicion.setHorizontalTextPosition(SwingConstants.CENTER);
         labelDefinicion.setText("HIPOTECADA");      
         casillaFisica.updateUI();
    }
    
    public void reestablecerValoresOriginales(){
       estadoPropiedad=false;           
        hipotecada=false;
        estadoPropiedad=false;
        hipotecada=false;        
        TarjetaPropiedadServicio auxiliar;
        auxiliar= (TarjetaPropiedadServicio) tarjeta;
        auxiliar.reestablecerValoresOriginales();
        dueno=null;
        formaFisicaInicial();//el deuño también debería hacerse null    
    }
    
}
