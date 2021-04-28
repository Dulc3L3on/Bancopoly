/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBancopoly;
import FrontendBancopoly.Dados;
    import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phily
 */
public class Dado extends Thread {
    Random aleatorio = new Random();
    Random lblAleatorio = new Random();
    Random repeticiones = new Random();
    private int cara, label, numeroDados;
    private int vecesARepetir;
    
    @Override
    public void run(){//dado 1
        vecesARepetir= 3 + repeticiones.nextInt(15);//xD sino se va a aburrir xD
        
        for(int repeticion =0; repeticion<vecesARepetir; repeticion++){
            cara = 1+aleatorio.nextInt(6);
            label=lblAleatorio.nextInt(numeroDados);
            //aqupi llamar al lbl para que muestre los respetivos números
            if(label==0){
                Dados.labelDados[0].setText(String.valueOf(cara));//es el hecho de no ser el mismo obj
                //y por lo tanto no tener los mismos lbl estáticos, lo cual provoca no se evníe nada o mejor
                //dicho se envíe pero hacia ellado donde s enecuentran los lbl estáticos es nuestón...
            }
            if(label==1){
                Dados.labelDados[1].setText(String.valueOf(cara));
            }
            if(label==2){
                Dados.labelDados[2].setText(String.valueOf(cara));
            }
            
            
            try{
                sleep(500);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Dado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void estblecerNumeroDados(int numero){
        numeroDados=numero;
    }
    
    public void pararHIlo(){
        vecesARepetir=0;
    }
    
}

