/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;

import BackendBancopoly.Pila;
import FrontendBancopoly.CustomizacionPersonalizada;
import FrontendBancopoly.CustomizacionTodasTarjetasDeUnTipo;
import FrontendBancopoly.SolicitudDatos;
import Jugadores.Jugador;
import Tarjetas.Caminar;
import Tarjetas.Personalizada;
import Tarjetas.Premio;
import Tarjetas.Retroceder;
import Tarjetas.SalgaCarcel;
import Tarjetas.Tarjeta;
import Tarjetas.TarjetaMoverA;
import Tarjetas.TarjetaPagoMulta;
import Tarjetas.TarjetaPagoTodos;
import Tarjetas.TarjetaPerdidaTurnos;
import Tarjetas.TarjetaVayaACarcel;
import java.awt.Font;
import java.io.Serializable;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author phily
 */
public class TomaUnaTarjeta extends Casilla implements Serializable{
    //aqupi irá el atrib que guardará el listado de tjts permitidas en esta pila(lista en realidad)    
    JPanel casillaFisica;
    String nombreCasilla;
    Casilla carcelReferida;
    //ListaCircular<Tarjeta> seudoPilaTarjetas;
    Pila<Tarjeta> pilaTarjetas;
    Tarjeta tarjeta;
    private int totatTarjetasAsignadas;
    private int repeticionDeCadaTarjeta;
    private int tiposDeTarjetasEnPila;
    private boolean[] tiposTarjetaEnPila = new boolean[10];//pues son 10 tjt's
    private Tarjeta tarjetaCaminar;//de tal forma que puedan asignarse a las ubicaciones una referencia a un mismo objteo y así se pueda al finalizar 
    //establecer los datos necesarios para su funcionamiento, y de una vez en todas [ lo cual debía ser así para no estar creando otro arr en donde venga
    //y se guarden cada uno de los objetos creados y así luego de acpetar, se vaya recorriendo dicho arreglo para asignar a cada una un mimísimo dato
    private Tarjeta premio;//para esta no es necesario hacer esto... pues sus datos los recibe do forma rápida  
    private Tarjeta salgaCarcel;
    private Tarjeta moverACasilla;
    private Tarjeta retroceder;
    private Tarjeta pagar;
    private Tarjeta multa;
    private Tarjeta perderTurnos;
    private Tarjeta vayaACarcel;//no guarda al jugador sino asigna directamente en el la condena, por ello no es afectado el proceso al tener a solo 1 tjt para toads
    private Tarjeta personalizada;       
    private Personalizada personalizadaConCarcel;
    
    boolean hanSeleccionadoCasillaQueMueveEnPersonalizada;//Con esto se podrá mandar a llamar directamente a la tjt personalizada para que luego esta última en el cuerpo del método llamado verifique a quienes debe permitir la solicitud de datos, en ese momento
    boolean hanSeleccionadoCasillaVayaACarcel;    
    
    transient CustomizacionTodasTarjetasDeUnTipo dialogoGeneral;
    transient CustomizacionPersonalizada dialogoPersonalizada = new CustomizacionPersonalizada(new javax.swing.JFrame(), true);
    
    //al parecer no se genera problema por el hecho de crear un solo objeto para que se akmacene en diferentes posiciones del 
    //Arr de la pila, pues al final de cuentas lo que se hará para sacara a una tjt de la pila será, el hecer el espcaio, mas no a la tjtj = null para que se deje de terner en la lista
    //la referencia de la casilla en cuestión [esto para SC]
    
    public TomaUnaTarjeta(JPanel casillaTablero, boolean tarjetasEscogidas[], int repeticionCadaTarjeta){
        super(casillaTablero,"TomaUnaTarjeta" );        
        casillaFisica=casillaTablero;
        repeticionDeCadaTarjeta=repeticionCadaTarjeta;               
        nombreCasilla="TomaUnaTarjeta";
        //dialogoGeneral.recibirCasillFisica(this);//puedes obviarlo porque de todos modos lo quitaste por el hecho de no poder hacer lo mismo con las demás que involucran movimiento... o es porque aquí fue donde no pudiste con ninguna...
        
        definirTIposDeTarjetasEnPila(tarjetasEscogidas);//Aquí el método que asigna los valores al arreglo    
        pilaTarjetas = new Pila(repeticionDeCadaTarjeta*tiposDeTarjetasEnPila) ;
        crearTarjeta();//aquí se crearan las tarjetas lógicas correspondientes a las escogencias
        definirFormaFisica();
    }
    
    @Override
    public void crearTarjeta(){//será literalmente una pila, solo que se iráncreando de forma aleatoria los difererntes objetos escogidos                
        int vecesAsignada[] = new int[10];
        
        while(totatTarjetasAsignadas<(repeticionDeCadaTarjeta*tiposDeTarjetasEnPila)){
            switch(barajearTarjetas()){//todas las casillas antes de crearse muestran su diálogo excepto en los casos especiales de caminar y moverA
                case 0://CAMINAR
                    if(vecesAsignada[0]<repeticionDeCadaTarjeta){
                        vecesAsignada[0]++;//para llebvar la cta de cuantas tarjetas de este tipo han sido asignadas
                        
                        if(vecesAsignada[0]==1){
                            tarjetaCaminar = new Caminar(true);                                                                     
                        }//recueredad que despues se va a mostrar su diálogo cuando el método de esta casilla para asignar valors faltantes se invoque... bueno siempre se invocará pero este mostrará algo o nadad dependiendo de si las casillas que requieren de esto [caminar y moverA] fueron creadas o no
                        
                        totatTarjetasAsignadas++;   
                        pilaTarjetas.addElemento(tarjetaCaminar);//se manda a add a la pila de tjts    
                    }//fin del if que se encarga no se add más de la cdad estipulada a la pila
                    
                break;
            
                case 1://PREMIO
                    if(vecesAsignada[1]<repeticionDeCadaTarjeta){
                        vecesAsignada[1]++;//para llebvar la cta de cuantas tarjetas de este tipo han sido asignadas
                        
                        if(vecesAsignada[1]==1){
                              //aqupi se hace la llamada al diálogo respectivo para tomar la definición y los datos necesarios para crearla, que se encuentre en esta parte, hará que solo pueda definirse un comportamiento general para cada tjt, en cada pila de tjt de cada casila toma una tjt
                              dialogoGeneral = new CustomizacionTodasTarjetasDeUnTipo(new javax.swing.JFrame(), true);//haré abjo un método, debería app los cambios debidos ya que como la var
                              //que hace referencia al diálogo será global, entonces recibirá el cb cuando aquí se cree otra, y como solo 1 vez será creada c/u, entonce depués no habrá problemas porque las demás llevarán los datos que se habían ingresado anteriromente a la var que hace ref a la tjten cuestión
                             dialogoGeneral.establecerTItuloSegunCasilla(1);
                             dialogoGeneral.setLocationRelativeTo(null);
                             dialogoGeneral.setVisible(true);
                              
                             premio = new Premio(dialogoGeneral.entregarDefinicion(), dialogoGeneral.entregarCasillaAIr());//recibe la descripción y la cdad a premiar                             
                        }                   
                    
                        totatTarjetasAsignadas++;    
                        pilaTarjetas.addElemento(premio);//se manda a add a la pila de tjts                        
                    }    
                    
                break;
            
                case 2://SC
                    
                    if(vecesAsignada[2]<repeticionDeCadaTarjeta){
                        vecesAsignada[2]++;
                        salgaCarcel = new SalgaCarcel();                    
                        pilaTarjetas.addElemento(salgaCarcel);//se manda a add a la pila de tjts
                        totatTarjetasAsignadas++;    
                        System.out.println(salgaCarcel);
                    }//no requiere de diálogo, pues no se le debe ingresar ninguna info en particular
                    
                break;
            
                case 3://MoverA
                      if(vecesAsignada[3]<repeticionDeCadaTarjeta){
                        vecesAsignada[3]++;//para llebvar la cta de cuantas tarjetas de este tipo han sido asignadas
                        
                        if(vecesAsignada[3]==1){                              
                             moverACasilla = new TarjetaMoverA();                            
                        }                   
                    
                        totatTarjetasAsignadas++;    
                        pilaTarjetas.addElemento(moverACasilla);//se manda a add a la pila de tjts
                        
                    }    
                break;
            
                case 4://RETROCEDER
                      if(vecesAsignada[4]<repeticionDeCadaTarjeta){
                        vecesAsignada[4]++;//para llebvar la cta de cuantas tarjetas de este tipo han sido asignadas
                        
                        if(vecesAsignada[4]==1){
                              //aqupi se hace la llamada al diálogo respectivo para tomar la definición y los datos necesarios para crearla, que se encuentre en esta parte, hará que solo pueda definirse un comportamiento general para cada tjt, en cada pila de tjt de cada casila toma una tjt
                             dialogoGeneral = new CustomizacionTodasTarjetasDeUnTipo(new javax.swing.JFrame(), true);                              
                             dialogoGeneral.establecerTItuloSegunCasilla(3);
                             //dialogoGeneral.establecerRangoSpinner();/este comentario indica que aquí fue donde no se pudo establecer el rango del spinner a dif de la tarjeta personalizada
                             dialogoGeneral.setLocationRelativeTo(null);
                             dialogoGeneral.setVisible(true);
                             retroceder = new Retroceder(dialogoGeneral.entregarDefinicion(), dialogoGeneral.entregarCasillaAIr());
                             
                        }                   
                        totatTarjetasAsignadas++;    
                        pilaTarjetas.addElemento(retroceder);//se manda a add a la pila de tjts
                        
                    }    
                break;
                        
                case 5://PAGAR A TODOS
                     if(vecesAsignada[5]<repeticionDeCadaTarjeta){
                        vecesAsignada[5]++;//para llebvar la cta de cuantas tarjetas de este tipo han sido asignadas
                        
                        if(vecesAsignada[5]==1){
                              //aqupi se hace la llamada al diálogo respectivo para tomar la definición y los datos necesarios para crearla, que se encuentre en esta parte, hará que solo pueda definirse un comportamiento general para cada tjt, en cada pila de tjt de cada casila toma una tjt
                             dialogoGeneral = new CustomizacionTodasTarjetasDeUnTipo(new javax.swing.JFrame(), true);//creo que ni sería necesario estar creando cada vez el diálogo, sio nada más usar el método para actualizar los títulos
                             dialogoGeneral.establecerTItuloSegunCasilla(4);
                             dialogoGeneral.setLocationRelativeTo(null);
                             dialogoGeneral.setVisible(true);
                             pagar = new TarjetaPagoTodos(dialogoGeneral.entregarCasillaAIr(),SolicitudDatos.registroDatos.obtenerNumeroJugadores() ,dialogoGeneral.entregarDefinicion());//pues para este pto registro ya ha obtenido sus datos                        
                        }                   
                    
                        totatTarjetasAsignadas++;    
                        pilaTarjetas.addElemento(pagar);//se manda a add a la pila de tjts
                        
                    }    
                break;
            
                case 6://MULTA
                       if(vecesAsignada[6]<repeticionDeCadaTarjeta){
                            vecesAsignada[6]++;//para llebvar la cta de cuantas tarjetas de este tipo han sido asignadas
                        
                            if(vecesAsignada[6]==1){
                                  //aqupi se hace la llamada al diálogo respectivo para tomar la definición y los datos necesarios para crearla, que se encuentre en esta parte, hará que solo pueda definirse un comportamiento general para cada tjt, en cada pila de tjt de cada casila toma una tjt
                                dialogoGeneral = new CustomizacionTodasTarjetasDeUnTipo(new javax.swing.JFrame(), true);//creo que ni sería necesario estar creando cada vez el diálogo, sio nada más usar el método para actualizar los títulos
                                dialogoGeneral.establecerTItuloSegunCasilla(5);
                                dialogoGeneral.setLocationRelativeTo(null);
                                dialogoGeneral.setVisible(true);
                                multa = new TarjetaPagoMulta(dialogoGeneral.entregarDefinicion(), dialogoGeneral.entregarCasillaAIr());//recibe la descripción y la cdad a multa
                        
                            }                   
                            
                            totatTarjetasAsignadas++;    
                            pilaTarjetas.addElemento(multa);//se manda a add a la pila de tjts
                        
                        }    
                break;
            
                case 7://PÉRDIDA DE TURNOS
                     if(vecesAsignada[7]<repeticionDeCadaTarjeta){
                            vecesAsignada[7]++;//para llebvar la cta de cuantas tarjetas de este tipo han sido asignadas
                        
                            if(vecesAsignada[7]==1){
                                  //aqupi se hace la llamada al diálogo respectivo para tomar la definición y los datos necesarios para crearla, que se encuentre en esta parte, hará que solo pueda definirse un comportamiento general para cada tjt, en cada pila de tjt de cada casila toma una tjt
                                dialogoGeneral = new CustomizacionTodasTarjetasDeUnTipo(new javax.swing.JFrame(), true);//creo que ni sería necesario estar creando cada vez el diálogo, sio nada más usar el método para actualizar los títulos
                                dialogoGeneral.establecerTItuloSegunCasilla(6);
                                dialogoGeneral.setLocationRelativeTo(null);
                                dialogoGeneral.setVisible(true);
                                perderTurnos = new TarjetaPerdidaTurnos(dialogoGeneral.entregarDefinicion(), dialogoGeneral.entregarCasillaAIr());//recibe la descripción y la cdad a premiar
                                
                            }                   
                    
                            totatTarjetasAsignadas++;    
                            pilaTarjetas.addElemento(perderTurnos);//se manda a add a la pila de tjts
                        
                        }    
                break;
            
                case 8://VC
                             if(vecesAsignada[8]<repeticionDeCadaTarjeta){
                            vecesAsignada[8]++;//para llebvar la cta de cuantas tarjetas de este tipo han sido asignadas
                        
                            if(vecesAsignada[8]==1){
                                  //aqupi se hace la llamada al diálogo respectivo para tomar la definición y los datos necesarios para crearla, que se encuentre en esta parte, hará que solo pueda definirse un comportamiento general para cada tjt, en cada pila de tjt de cada casila toma una tjt                                
                                dialogoGeneral = new CustomizacionTodasTarjetasDeUnTipo(new javax.swing.JFrame(), true);//creo que ni sería necesario estar creando cada vez el diálogo, sio nada más usar el método para actualizar los títulos
                                dialogoGeneral.establecerTItuloSegunCasilla(7);
                                dialogoGeneral.setLocationRelativeTo(null);
                                dialogoGeneral.setVisible(true);                              
                                vayaACarcel = new TarjetaVayaACarcel(dialogoGeneral.entregarDefinicion(), dialogoGeneral.entregarCasillaAIr());//recibe la descripción y la cdad a premiar                                                                                             
                                //RECUERDA que el envío de cárcel se hace al momento de crearla, esto en la princiCustom, donde ya tengo asegurado que se envíe la cárcel más cercana, por ello aquí solo debo enviar los demás datos para crearla
                            }                   
                    
                            totatTarjetasAsignadas++;
                            pilaTarjetas.addElemento(vayaACarcel);//se manda a add a la pila de tjts
                        
                        }    
                break;
            
                case 9://PERSONALIZADA
                      if(vecesAsignada[9]<repeticionDeCadaTarjeta){
                            vecesAsignada[9]++;//para llebvar la cta de cuantas tarjetas de este tipo han sido asignadas
                        
                            if(vecesAsignada[9]==1){
                                 //aqupi se hace la llamada al diálogo para que pueda personaloizar la tjt, para que se pueda tomar la definición y los datos necesarios para crearla, que se encuentre en esta parte, hará que solo pueda definirse un comportamiento general para cada tjt, en cada pila de tjt de cada casila toma una tjt
                                 //aquí se recibe el valor de verdad de las tarjetas para saber si hay que hacer una asignación posterior o no
                                 //Aquí se hace el pase del valor a la var que manda esta casilla a la principal contumización para guardar el objeto toma una tjt                                                                 
                                dialogoPersonalizada.recibirCasilaFisica(casillaFisica);
                                dialogoPersonalizada.setLocationRelativeTo(null);
                                dialogoPersonalizada.setVisible(true);
                                personalizada = new Personalizada(dialogoPersonalizada.entregarDesiciones(), dialogoPersonalizada.entregarValorPrimeraTarjeta(), 
                                dialogoPersonalizada.entregarValorSegundaTarjeta(), dialogoPersonalizada.entergarDescripcion());
                                Personalizada auxiliar;
                                auxiliar=(Personalizada)personalizada;
                                hanSeleccionadoCasillaQueMueveEnPersonalizada=auxiliar.informarNecesedadINgresoDatosPosterior();//esta será la var a enviar para almacenar el obj en la costumiz prin                                
                                
                             /*   Personalizada auxiliarCarcel;
                                auxiliarCarcel=(Personalizada)personalizada;
                                if(hanSeleccionadoCasillaVayaACarcel=auxiliarCarcel.informarNecesidadDeCarcel()){
                                    personalizadaConCarcel=personalizada;
                                }*///lo puedo hacer de forma más directa por medio del diálogo, es decir, elimina el método que devolvía el vV de irACarcel
                                
                                if(hanSeleccionadoCasillaVayaACarcel=dialogoPersonalizada.habranSeleccionadoIrACarcel()){//aquí se revisa la necesidad de forma directa [es decir desde el diiálogo]
                                    personalizadaConCarcel=(Personalizada)personalizada;//pue la tarjeta personalizada es quien posee el métoodo para asignar a quine requiere la cárcel meniconada
                                    dialogoPersonalizada.establecerPermisoParaIrACarcel(false);
                                }
                            }                   
                            totatTarjetasAsignadas++;    
                            pilaTarjetas.addElemento(personalizada);//se manda a add a la pila de tjts                        
                      }                        
                break;       
        
            }//fin del switch
        }//si se hubieran generado distintos objetos, se hubiera tenido la oportunidad de asignar a cada uno de los que recibieran parametros, valores diferentes de tal manera que cada una pudiera actuar de diferente manera, lo cual 
        //talvez hubiera obligado a venir y hacer un botón que dijera igual que la anterior, para que no se aburriera de estar ingresando datos para cada una xD       
        
    }//debido al hecho de que las tjt's caminar y moverA requieren de un asignación de datos posterior, y además porque así se evita tener que crear tantos objetos , se creará nada más una tjt de cada tipo, exceptuando 
    //la tarjeta SC, pues por el hecho de ser pasada a los jugadores y que esta exe una acción con él, puede que tb afecte a los demás que en ese momento no estaban ejerciendo la acción de dicha tjt... entonces al hacer 
    //esto al asignar a una de la var que tiene la referncia de la tjt en la pila que esta replicada una cdad de veces definida, se le asignará a todas las que apunten a dicha referncia... pero creo que de igual forma como 
    //se asigna el valor atodas las demás, así sucederá con la acción, y no quiero que se exe 1 por cada tjt de ese tipo que en la pila se encuentre, sino 1 por la tjt salida de la pila
    
    public void definirTIposDeTarjetasEnPila(boolean estaAsignada[]){//esto es para que se pueda encontrar a que tipo pertenece la tjt asignada
        for (int tarjetasAsignadas = 0; tarjetasAsignadas <tiposTarjetaEnPila.length ; tarjetasAsignadas++) {
            tiposTarjetaEnPila[tarjetasAsignadas]=estaAsignada[tarjetasAsignadas];
            if(estaAsignada[tarjetasAsignadas]==true){
                tiposDeTarjetasEnPila++;
            }
        }
    }
    
     public int barajearTarjetas(){        
         Random tarjetaAleatoria = new Random();
         
         int tarjetaAInsertar = tarjetaAleatoria.nextInt(tiposTarjetaEnPila.length);
         
        if(tiposTarjetaEnPila[tarjetaAInsertar]){
            return tarjetaAInsertar;
        }         
        return -1;
    }
    
    @Override
    public void accion(Jugador jugadorLlegado){//se invoca cada vez que se le haga referncia como las demás casillas
        //se mandará la info de la tjt al diálogo que (mira abajo)
        //se mandará a llamar a la tgt que mostrará la correspondiente de la pila (lista circular xD) que le toque salir       
        //esto luego de haber sido creada la "pila" respectiva, claro xD
        //con listadoCIrcular
        /*tarjeta= seudoPilaTarjetas.obtnerContenidoDelUltimo();
        tarjeta.accion(jugadorLlegado);//aquí ya se encarga cada tarjeta de hacer lo suyo xD
        seudoPilaTarjetas.revisarSiEsSalirCarcel();*///ahí revisas como sucede el proceso de sacar a SC, pues al parecer solo en un pedazo del listado se eliminará, mas no en todo... pues cada una tiene la respectiva referencia de las tjt
        //que se encontraban en el listado antes que ella llegara
        
        super.accion(jugadorLlegado);
        
        //con pila
        if(!pilaTarjetas.estaVacia()){            
            tarjeta=pilaTarjetas.entregarElementoSIguiente();
            tarjeta.accion(jugadorLlegado);
            System.out.println("Nombre de la tarjeta-> "+tarjeta.obtenerNombre());
            if(tarjeta.obtenerNombre().equals("SalgaCárcel")){//Es cierto, esto va a dar nullPointer, por el hecho de que en el métood acción, se procede a hacer la entrga de la tarjeta en cuestióna l jugador, entonces supongo que eso deha a la var tjt 
               pilaTarjetas.sacarElementoPila(tarjeta);//aquí se le envía la tjt para que pueda ser excluida de la pila
            }//NO, realmente lo que se hace en este if, es que se saca de la pila, puesto que la tarjeta de SC ya se encarga por sí misma de pasarse a manos del jugador correspondiente
        }      
        
    }
     
   public TarjetaVayaACarcel obtenerTarjetaVayaACarcel(){//asumo yo que este es por el hecho de los cabos ueltos [que en este caso serían los VC que no poseen C
       TarjetaVayaACarcel tarjetAuxiliar;
       tarjetAuxiliar=(TarjetaVayaACarcel)vayaACarcel;
       return tarjetAuxiliar;
   }
   
   public Personalizada obtenerTarjetaPersonalizadaNecesitadaCarcel(){
       return personalizadaConCarcel;
   }
   
   public boolean hanSeleccionadoEnPersonalizadaCasillaQueMueve(){
       return hanSeleccionadoCasillaQueMueveEnPersonalizada;
   }
    
    /**
     * Método llamado para terminar de establecer los datos de las casillas que requieren de una posterior costumización
     * por el hecho de desconocer todos los datos necesarios para ser creada la respectiva tarjeta
     */
    public void establecerDatosFaltantesTarjetas(){//aquí deberás mandarle al diálog el dato del índice máx, donde para cada tjt servirá de diferente manera a una solo como índice y a otra para mostrar el número casillas que puede ir hacia adelante
        if(tarjetaCaminar!=null){//tb se hubiera podido hacer revisando los valores de verdad de las var booleanas, pues estas tiene el contenido que determina el crear u¿o no una tjt  
            //Se manda a llamar al diálogo
            CustomizacionTodasTarjetasDeUnTipo dialogoMoverA = new CustomizacionTodasTarjetasDeUnTipo(new javax.swing.JFrame(), true);
           dialogoMoverA.establecerTItuloSegunCasilla(0);
            //spiner.setModel(model);//aquí TIENES QUE DEFINIR EL LISTADO DE ÍNDICES DONDE, no permitirás que sea el índice que corresponde a esta casilla
            dialogoMoverA.setVisible(true);            
            
            Caminar tarjetaAuxiliar;
            tarjetaAuxiliar = (Caminar)tarjetaCaminar;
            tarjetaAuxiliar.establecerDatosFaltantes(dialogoMoverA.entregarDefinicion(), dialogoMoverA.entregarCasillaAIr());        }
        
        if(moverACasilla!=null){      
            //Se manda a llamar al diálogo
            CustomizacionTodasTarjetasDeUnTipo dialogoGeneral = new CustomizacionTodasTarjetasDeUnTipo(new javax.swing.JFrame(), true);
            dialogoGeneral.establecerTItuloSegunCasilla(2);
            //spiner.setModel(model);//aquí TIENES QUE DEFINIR EL LISTADO DE ÍNDICES DONDE, no permitirás que sea el índice que corresponde a esta casilla
            dialogoGeneral.setVisible(true);            
            
            TarjetaMoverA tarjetaAuxiliar;
            tarjetaAuxiliar = (TarjetaMoverA)moverACasilla;
            tarjetaAuxiliar.establecerDatosFaltantes(dialogoGeneral.entregarDefinicion(), dialogoGeneral.entregarCasillaAIr());//aquí si se necestitan del método fusionado; donde sus valores osn obtenidos a través del diálogo
            //Aquí en la casilla no requiero de la cadad o desripción, puesto que ni siquiera tiene una forma física cbte y más que todo porque no requiere de 
            //hacer operaciones, ya que esto le corresponde a la tjt en sí
        }
        
        if(hanSeleccionadoCasillaQueMueveEnPersonalizada){
            Personalizada auxiliar;
            auxiliar=(Personalizada)personalizada;
            auxiliar.solicitarDatosFaltantes();//Así de esta manera la tarjeta personalizada se encarga de tratar directamente con el problema,puesto que ella es quien sabe con exactitud como está la situación
        }
        
    }
    
    public boolean hanSeleccionadoVayaACarcel(){
        return hanSeleccionadoCasillaVayaACarcel;
    }
    
    public CustomizacionPersonalizada obtenerDIalogoPersonalizada(){
        return dialogoPersonalizada;
    }
    
    public void asignarCarcelFaltante(Casilla carcel){//o en lugar de esto lo que hubieras podido hacer era crear un método para 
        //obtener la tarjeta, luego castearla [o envairla de una vez como personalizada [pues ella tiene el métodod para asignar la 
        //cárcel ó ... obtnere a la tarjeta personalizdad, par emplear el método btner que daría  al tarjeta VC, lo cual permitiría venir
        //y usar el método de TjtVC de forma directa, pero con este método lo que tendrías que hacer sería guardar la casilla [así 
        //como en el otro proceso] utilizar este método y enviar a la cárcel, por el hecho de que este enlaza a los otros que poseen
        //la capacidad de asignarle de forma correcta la cárcel... pero creo que para que todo sea de forma más profesional [esoo xD]
        //y así reutilizar métodos, mejor emplearás el primer proceso
        Personalizada auxiliarCarcel;
        auxiliarCarcel=(Personalizada)personalizada;
        
        auxiliarCarcel.asignarCarcelFaltante(carcel);
    }//se hará de forma directa desde la prinCostumiz
    
    public void definirFormaFisica(){          
        JLabel labelDescripcion;
        
        labelDescripcion=(JLabel) casillaFisica.getComponent(2);        
        labelDescripcion.setText("Toma una tarjeta");//si le llegas a poner imagen, entonces esto pasa al lblNombre y aquí se colocaría la imagen
        labelDescripcion.setHorizontalTextPosition(SwingConstants.LEADING);
        labelDescripcion.setFont(new Font("Sawasdee", Font.BOLD ,18));
        
    }
    
    //NO CAMBIA DE FORMA FÍSICA
    
    
    //Este no requiere actualizar su propia forma, pero si posiblemente al cuerpo de otra como podría suceder en el 
    //caso de caer en una casilla (al avanzar o retroceder)que no haya sido comprada y decida apoderarse de ella 
    //el jugador
    
    //LO CUAL SE RESULVE COMO LO HABÍA PENSADO,empleando el método para avanzar haciendo referncia a 
    //la ubicación del jugador(que sería la nueva en ese caso) y luego haciendo referncia a su método de acción
    //el cual rxn según su tipo y ahpi no habría problemas y con ello acabaría el método, es decir que este método que 
    //termina referenciando a otro (de acción "similar") es pertenenciente al cuerpo de la tarjeta
    
    //RECUERDA... esta vez no se creó en el mismo diálogo a la tjt en cuestión por el hecho de que no es solo 1 la que tiene esta
    //casilla, como en el caso de las demás casilla, sino tiene una colección, por lo cual sería más útil el hecho de llamarlo y luego
    //emplear la var que se refiere a él para captar la info que sea necesaria para su creación, esto para todas las tjt's, sea que se exe 
    //en el momento el diálogo o que se haga en momentos posteriores    
    
}
