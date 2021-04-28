/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjetas;

import Casillas.Casilla;
import FrontendBancopoly.CustomizacionTodasTarjetasDeUnTipo;
import FrontendBancopoly.SolicitudDatos;
import Jugadores.Jugador;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author phily
 */
public class Personalizada extends Tarjeta implements Serializable{    
    private String definicion;//la general en este caso
    String nombre="Personalizada";
    private boolean caminar;
    private boolean moverA;
    private Tarjeta tarjeta;
    private Tarjeta tarjeta2;
    private int cantidad;
    private int cantidad2;
    private boolean tarjetasFusionadas[];    
    
    
    public Personalizada(boolean tarjetasUsadas[], int cdadTarjeta1, int cantidadTarjeta2, String descripcion){//tn falta recibir los valores de verdad de las demás tjt's pues también las demás se deben crear xD
        super("Personalizada");
        tarjetasFusionadas=tarjetasUsadas;        
        cantidad=cdadTarjeta1;
        cantidad2=cantidadTarjeta2;
        definicion=descripcion;
       fusionar();//Recuerda que en unas se mostrará la solicitud de datos de una vez y en otroas (caminar y moverA)
       //después
        
    }
    
    public void fusionar(){//xD
        //aquí se le hará la asignación a tarjeta 1 dependiendo cuál de las que pertenecen a asu grupo se escogió
        //de forma similar para tarjeta 2, con esto es solamente necesario un switch
        
        //Recuerdate que aquí no se debe activar el JOP de la tarjeta a fusionar, este solo deberá mostrarse cuando
        //Actúe como una tjt independiente
        
        if(tarjetasFusionadas[0]){            
            tarjeta= new Caminar(false);            
        }
        if(tarjetasFusionadas[1]){
            tarjeta= new TarjetaMoverA();
        }
        if(tarjetasFusionadas[2]){            
            tarjeta= new Retroceder(cantidad);
        }
        if(tarjetasFusionadas[3]){
            
            tarjeta = new Premio(cantidad);//la cdad a premiar
        }
        if(tarjetasFusionadas[4]){            
            tarjeta2 = new TarjetaPagoTodos(cantidad2,SolicitudDatos.registroDatos.obtenerNumeroJugadores(), false);//pues para este pto registro ya ha obtenido sus datos
        }
        if(tarjetasFusionadas[5]){            
            tarjeta2 = new TarjetaPagoMulta(cantidad2);//recibe la descripción y la cdad a multa
        }
        if(tarjetasFusionadas[6]){           
           tarjeta2 = new TarjetaPerdidaTurnos(cantidad2, false);//recibe la descripción y la cdad a premiar
        }
        if(tarjetasFusionadas[7]){                                   
             tarjeta2= new TarjetaVayaACarcel(cantidad2, false);//solamente cb la var de tipo tjt donde refernciaba para hacer le casteo, puesto que le pertenecía a ptra, el haber hecho
             //esto no arrastra errores, por el hecho de que lo único que se trae del diálogo son los valores de verdad de las tarjetas creadas, y como no les cb el índice sino la tarjeta a
             //donde se dirigirá, lo cual no afecta porque dicho proceso es creado aquí mismo y no se trae de otra clase
        }
    }
    
    /**
     *  Llama a las acciones de las tarjetas según hayan sido escogidas, por lo tanto deberían
     *  suceder sin problema de manera secuencial, aunque una de ellas sea una tjt que mueve
     *  y sin importar el orden
     * @param solicitante
     */
    @Override
    public void accion(Jugador solicitante){//Debería ser y hacer la fusión entre las respectivas casillas
                                   //o declarar cada uno de los métodos aquí en la clase, para 
                                   //luego unificarlos
         JOptionPane.showMessageDialog(null, definicion, "", JOptionPane.INFORMATION_MESSAGE);
         tarjeta.accion(solicitante);
         tarjeta2.accion(solicitante);
        //no será necesario mandar a llamar el método para actualizar la tabla, puesto que las tjt's ya tienen a dicho método en sus cuespos, así que...
    }
    
    public boolean informarNecesedadINgresoDatosPosterior(){//podría haberse evitado este método si se solitaran los datos del diálog directamente, como de todos modos ya teine esos métodos pues debe informarle a esta tjt que debe crear
        return (tarjetasFusionadas[0]==true || tarjetasFusionadas[1]==true);
    }
    
    public void solicitarDatosFaltantes(){//Recuerda que los diálogos de mover a y caminar, los muestra la clase que realmente sepa quienes son los que requieren de él y tb son las que contienen a dichas tjt's, en este caso fue una tjtj el contenedor de ellas
           if(tarjetasFusionadas[0]){//aquí se mandan a llamar a los métodos de estas, que permiten que se termine de llenar la información para que pueda funcionar bien
               CustomizacionTodasTarjetasDeUnTipo dialogoCaminar = new CustomizacionTodasTarjetasDeUnTipo(new javax.swing.JFrame(), true);
               dialogoCaminar.establecerTItuloSegunCasilla(0);
               
               Caminar tarjetaAuxiliar;
               tarjetaAuxiliar = (Caminar)tarjeta;//TRANQUILA, NO HAY PROBLEMA PORQUE SOLO UNA DE ESTAS 2 PODRÍA SER ESCOGIDA EN UN ASOLA PERSONALIZADA
               tarjetaAuxiliar.establecerDatosFaltantes(definicion, cantidad);
           }
           if(tarjetasFusionadas[1]){
                 CustomizacionTodasTarjetasDeUnTipo dialogoMoverA = new CustomizacionTodasTarjetasDeUnTipo(new javax.swing.JFrame(), true);
                dialogoMoverA.establecerTItuloSegunCasilla(2);
                
                TarjetaMoverA tarjetaAuxiliarParaMoverA;
                tarjetaAuxiliarParaMoverA=(TarjetaMoverA)tarjeta;//dudo mucho en que el cb de tjt donde se guardará repercuta de mala manera en los demás procesos, pero por si acaso... lo único que hiciste fue cb tarjea por tarjeta2 en movA y VC
                tarjetaAuxiliarParaMoverA.establecerIndiceDestino(cantidad);//Pero no quiere decir que no vaya a mostrar un JOP, pues no se ha colocado la condi de que lo haga cuando sea independiente
           }//MOSTRADOS POR MEDIO DE PRINCIPAL CONSTUM al ser aprobado el diseño
        
    }
    
    public boolean informarNecesidadDeCarcel(){
        return tarjetasFusionadas[7];
    }//mejor lo hago de forma directa desde el diálogo
    
    public TarjetaVayaACarcel obtenerPersonalizadaParaAsignarCarcel(){
        return (TarjetaVayaACarcel)tarjeta2;

    }
    
    public void asignarCarcelFaltante(Casilla carcel){
        TarjetaVayaACarcel tarjetaAuxiliar;
        tarjetaAuxiliar=(TarjetaVayaACarcel) tarjeta2;
        tarjetaAuxiliar.recibirCarcelMasCercana(carcel);//solo esto es necesario puesto que ya fue creada, además como solamente un objteto es creado por tipo... no habrá problema 
        //al hacer esto
    }
    
}
