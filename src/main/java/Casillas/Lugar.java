/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;

import Jugadores.Jugador;
import Tarjetas.Tarjeta;
import Tarjetas.TarjetaPropiedad;
import Tarjetas.TarjetaPropiedadLugar;
import java.awt.Font;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author phily
 */
public class Lugar extends Propiedad implements Serializable{//recuerda, compartidas [heredadas] publicas, las que no privadas
    private String grupoPropiedad;
    private boolean grupoCompleto;//se cb al poseer todos
    String nombreCasilla;
     //int precioPropiedad;
     //int cantidad;//pago por estancia
    private int precioHipoteca;//aún no se si será individual o general, si es general, entonces se inicializará en el ctrctor del padre
    private int precioPorSimpleCtrc;
    private int precioPorSofisticadaCtrc;
    private int cantidadSimplesPoseidas;
    private int cantidadSofisticadasPoseidas;
    private boolean estadoPropiedad;
    private boolean hipotecada;//con esto no se debería de desplegar el JOP, para cobrar renta
    Tarjeta tarjeta;     
    Jugador dueno;
    JPanel casillaFisica;       
    
       public Lugar(JPanel casillaTablero, int interesHIpoteca, int precioSimple, int precioSofisticada, String grupoDePropiedad, String nombreLugar, int pagoEstancia, int precioDePropiedad ){//si el precio por hipoteca es individual pues entonces tb recíbelo aquí, en este ctrtor no en ninguno de sus padres, pues debes trabajar con él aquí
        super(interesHIpoteca, casillaTablero, nombreLugar, pagoEstancia, precioDePropiedad);
        casillaFisica=casillaTablero;
        //cantidad=pagoEstancia;//no debería estar aquí
        grupoPropiedad=grupoDePropiedad;
        nombreCasilla=nombreLugar;
        //precioPropiedad=precioDePropiedad;
        precioPorSimpleCtrc= precioSimple;
        precioPorSofisticadaCtrc= precioSofisticada;               
        crearTarjeta();
        //la forma física inicial
        formaFisicaInicial();
        //NO olvides pedir el color del grupooo xD
    }
       
    @Override
     public void crearTarjeta(){//los datos ya están aquí guardados, entonces no es necesario que se envíen paráms a este método, mas no es el mismo caso para las tjtas
     //creará a la tarjeta lógica, que puede mostrar uno de dos diálogos, dependiendo de su estado de 
     //comprada i hipotecada, lo cual se decide en el cuerpo de ella
     tarjeta = new TarjetaPropiedadLugar(nombreCasilla, precioPropiedad, cantidad, precioPorSimpleCtrc, precioPorSofisticadaCtrc, this);
     
        
    }//recuerad que cada casilla guardará su debida tjt en un atrib, lo cual evita que en los param de la creación de estas tjtas, no será necesario enviarle la casilla lógica además de los datos que realmente necesita
       
    @Override
       public void accion(Jugador jugadorLlegado){ 
           super.accion(jugadorLlegado);
           
           if(!hipotecada){//pues porque si lo está no debería de hacer nada en ningún caso en los que actúa, por medio de la tjt
                //se llama a la tjt, la cual según el estado de la propiedad, mostrará uno u otro tipo de dialogo
                //se llamar a casilla para hacerle las respectivas actualizaciones
                tarjeta.accion(jugadorLlegado);//con esto ya se manda a exe las diferentes acciones, que varían según la decisión
                TarjetaPropiedad tarjetaAuxiliar = (TarjetaPropiedad) tarjeta;//Asumo que se queda como de tipo tarjeta la "tarjeta", eso espero
                dueno=tarjetaAuxiliar.retornarDueno();//necesito tener aquí el dueño, pues eso me será útil para actualizar la forma física y talvez después para corroborar si es dueño de todos los grupos, aunque eso se haría 
                
                actualizarFormaFisica();//revisando directament en jugador por lo cual solo será necesario ver que grupos tieney cuantos integrantes de su totalidad
             
           }          
       }
    
    
    public void actualizarFormaFisica(){//creo que a este se le hará la add, por lo de las fichas
       if(dueno!=null){//quiere decir que ya fue adquirida
            estadoPropiedad=true;//Aunque estos valores podrían ser enviados por la tjt de tal forma que no se repita tanto el proceso, solo cuando sea necesario
            JLabel labelDefinicion= (JLabel)casillaFisica.getComponent(2);
            labelDefinicion.setFont(new Font("Sawasdee", Font.BOLD ,16));
            labelDefinicion.setHorizontalTextPosition(SwingConstants.CENTER);
            labelDefinicion.setText("Dueño: "+ dueno.obtnerNombre());//podrías crear otro lbl para colocar ahí el nombre del dueño, pero habría que tener cuidado por el hecho de que las casillas, varían por el tamaño
            casillaFisica.updateUI();
        }//para reestablecer los valores al declararse en bancarrota se hará desde jugador, accediendo a las var de la casilla y de la tjt en cuestión para reestablecer a los valores iniciales
       
    }
    
      public void formaFisicaInicial(){
        JLabel labelNombre= (JLabel)casillaFisica.getComponent(0);
        labelNombre.setName(grupoPropiedad);
        labelNombre.setFont(new Font("Sawasdee", Font.BOLD ,18));
        labelNombre.setText(nombreCasilla);
        
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
    }
       
       public void establecerFormatoHipotecada(){
           JLabel labelDefinicion= (JLabel)casillaFisica.getComponent(2);
           labelDefinicion.setFont(new Font("Sawasdee", Font.BOLD ,16));
           labelDefinicion.setHorizontalTextPosition(SwingConstants.CENTER);
           labelDefinicion.setText("HIPOTECADA");      
           casillaFisica.updateUI();
       }
       
       public void establecerCantidadSimples(int cantidad){
           cantidadSimplesPoseidas+=cantidad;
       }//se usará este mismo método para vender, solo que se enviarán datos negativos, para hacer el decremento indicado
       
       public void establecerCantidadSofisticadas(int cantidad){
           cantidadSofisticadasPoseidas+=cantidad;
       }    
       
       public void reestablecerValoresOriginales(){
           grupoCompleto=false;
           cantidadSimplesPoseidas=0;
           cantidadSofisticadasPoseidas=0;
           estadoPropiedad=false;
           hipotecada=false;
           dueno=null;           
           TarjetaPropiedadLugar auxiliar;
           auxiliar =(TarjetaPropiedadLugar) tarjeta;
           auxiliar.reestablecerEstadoOriginal();
           formaFisicaInicial();
       }

    @Override
    public String obtenerNombre() {
        return super.obtenerNombre(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String obtenerGrupo(){
        return grupoPropiedad;
    }
    
    public boolean estaElGrupoCOmpleto(){
        return grupoCompleto;
    }
    
      public int obtenerPrecioPropiedadSImple(){
        return precioPorSimpleCtrc;
    }
    
    public int obtnerPrecioPropiedadSofisticada(){
        return precioPorSofisticadaCtrc;
    }
    
      public int obtenerCantidadSimplesPoseidas(){
        return cantidadSimplesPoseidas;        
    }
    
    public int obtenerCantidadSoisticadasPoseidas(){
        return cantidadSofisticadasPoseidas;
    }
       
       
    
}//0 -> nombre, 1-> indiceRecorrido, 3-> definición [empleada para colocar el precio, el nombre del dueño y el estar hipotecada o no

//A LA HORA DE DECLARARSE EN BANCARROTA, lo que sucederá con las casillas propiedad, será el hacer dueño=null y a hipotecada =false; con esto ya vuelve a su estado iniciai (al menos el de la partida), pues ya se tienen
//los valores iniciales, que permiten deplegar el diálogo de compra mas no el de cobro para que suceda el ciclo nuevamente

//podrías poner un lbl hasta el final, de tal manera que ahí se muestre la cdad deconstrucciones por tipo que tiene la casilla

//debes ver que apariencia tienen las letras cuando se empequeñece la casilla, para así ponerle un tamaño que se ajuste al de la casilla