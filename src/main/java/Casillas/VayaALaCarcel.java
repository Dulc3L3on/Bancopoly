/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;
import Jugadores.Jugador;
import Tarjetas.Tarjeta;
import Tarjetas.TarjetaVayaACarcel;
import java.awt.Font;
import java.io.Serializable;
import javax.swing.*;
/**
 *
 * @author phily
 */
public class VayaALaCarcel extends Casilla implements Serializable{//simpre hace perder un turno
    private Jugador encarcelado;
    JPanel casillaFisica;
    Casilla carcelMasCercana;//pues el determinar lo de la cercanía se hace antes de mandarla acá
    private Tarjeta tarjeta;    
    private int cantidad=1;//esta casilla siempre dará unicamente 1 turno de sentencia, a diferencia de la tjt en la cual se establecerá la cdad de turnos de condena, por ello la tjtj SIEMPRE debe recibir la cada de condena a contrario de 
    //ESTA casillaa
    
    public VayaALaCarcel(JPanel casillaTablero){//puesto que se define solo 1 la casilla, alc ontrario que la tarjeta, donde ella si deberá establecer y por ello recibir la cdad de castigo
        super(casillaTablero, "Vaya a la cárcel");
        casillaFisica=casillaTablero;        
        definirFormaFisica();
        
    }//TIENES QUE HACER EL MÉTODO DE LA LISTA ENLAZADA DE ORDENAMIENTO ASCENDENTEM LUEGO SIGUES CON LA CASILLA TOMA UNA TJT (es la últma), recuerda que este deberás emplearlo en donde se add a la lista
    //Enlazada para hacer la validación
    
    @Override
     public void crearTarjeta(){//llamado hasta que se tiene la cárcel, para que no se tenga a la casilla como null
         tarjeta = new TarjetaVayaACarcel(carcelMasCercana, cantidad);
         
    }       
     
    @Override
      public void accion(Jugador jugadorLlegado){
          super.accion(jugadorLlegado);
          tarjeta.accion(jugadorLlegado);                 
         
          //recuerda que la decrementación de la sentencia se hará directamente en la partida, luego de revisar y encontrar que sí tiene sentencia, entonces se procederá a reducri dicha cdad en 1
          //[esto se haría en el else donde se evitó lo de los turno] y a esto solo se tendrá acceso si no se declaró en bancarrota. si sí entonces no se hará nada, pues al momento de él declararse en BC
          //se liberaron todas sus pertenencias
       }
       
       private void definirFormaFisica(){//Se supone que debe tener una imagen en lugar de la descripción!
            JLabel labelNombre= (JLabel)casillaFisica.getComponent(0);
            labelNombre.setFont(new Font("Sawasdee", Font.BOLD ,18));
            labelNombre.setHorizontalTextPosition(SwingConstants.CENTER);
            //labelNombre.setBackground(Color.GRAY);//Pues ahora se hace desde la principal costumización
            labelNombre.setText("VÁYASE A LA CÁRCEL");//podrías crear otro lbl para colocar ahí el nombre del dueño, pero habría que tener cuidado por el hecho de que las casillas, varían por el tamaño
            casillaFisica.updateUI();
       }
      
    /**
     * Llamado desde la prinCustom, cuando ya haya creado la cárcel que le 
     * pertence a esta casilla, de tal manera que ya se pueda crear la tarjeta, completita.
     * @param carcel
     */
    public void recibirCarcelMasCercana(Casilla carcel){
          //si es que se pasa la condi de que exista mínimo 1 en el listado de cárcel
          carcelMasCercana = carcel;
          crearTarjeta();
          
      }
      
      public JPanel devolverCasillaFisica(){
          return casillaFisica;
      }
    
    //NO CAMBIA PARA NADA SU FORMA FÍSICA
    
    
    
}
//te falta implementar el código de tjts y casillas, hacer la lista circular, es decir solo poner el el backendTablero la llamda de la
//lista para agregar y enviarle el objeto casilla, que ya tendrá sys respectivos atributos llenos, es decir la casilla
//o los lbls y las tjts

//Arreglar es decir hacer un panel por cada customización de las tjts  y con ello ya puedes pensar como le harás con la ficha 
//y luego las demás acciones correspondientes a las casillas, comprar, transferir, hacer la lista circular simple de las tjts que irán en la casilla

//IDEAS ANTERIORES (referentes a esta casilla)
        //en realidad es una lista circular simple, con comportamiento de pila fusionada con cola
        //la tarjeta lógica solo deberá tener como acción/es lo que veo por el momento) el establecer el tiempo de 
        //condena en la var respectiva del jugador, por medio del método de este
        //la forma física de la tjt solo sería mostrando la cadad de turno de condena y el nombre del jugador condenado