/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jugadores;

import BackendBancopoly.ListaEnlazada;
import BackendBancopoly.Nodo;
import BackendBancopoly.NodoDoble;
import BackendBancopoly.Ranking;
import Casillas.Casilla;
import Casillas.Lugar;
import Casillas.Estacion;
import Casillas.Servicio;
import Casillas.Propiedad;
import FrontendBancopoly.MenuDecisionFinal;
import FrontendBancopoly.SolicitudDatos;
import Partida.DesarrolloPartida;
import Tarjetas.SalgaCarcel;
import java.awt.Color;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author phily
 */
public class Jugador implements Serializable{
    
    private String nombre;
    private NodoDoble <Casilla> nodoPosicionActual;
    private Casilla ubicacionActual;//por medio de la cual se podrá avanzar a partir de su posición, al inicio de la partida no poseerá dicho obj sino hasta depues de haber tirado los dados y haber determinado qu eposición le corresponda en el tablero
    private int dinero;
    private Color colorDeFicha;
    //private ListaEnlazada<Propiedad> propiedades;//este será el arreglo al que acceda al emplear el diálogo de mis posecione, para modificar de una lo que tenga que con ella en el recorrido y por ello en el mapa
    ListaEnlazada<ListaEnlazada<Casilla>> lugares = new ListaEnlazada();
    ListaEnlazada<Casilla> servicios = new ListaEnlazada();
    ListaEnlazada<Casilla> estaciones = new ListaEnlazada();//en estos 3 será donde hará la debida add de los listados enlazados correspondientes
    private ListaEnlazada<SalgaCarcel> tarjetasSalgaCarcel= new ListaEnlazada();
    //lo referente a la ficha... aún no se si sea el color o una imagen ... o no śe
    private boolean bancarrota;
    private boolean encarcelado;
    private int condena;//aquí tendrá la condena restante
    private int debe;//esta será la que se revise cada vez, para permitirle terminar el turno cuando sea 0
    private int turnosPerdidos;
    private int riquezas;
    private int propiedadesTotales;//todas las prop
    private boolean ganador;
   //el número de turno no, porque no está ligado completamente con él además eso se controla por la partida       
    
    MenuDecisionFinal menuFinal;
    
    public Jugador(String nombreJugador, int riquezasObtenidas, boolean fueGanador){
        nombre=nombreJugador;
        riquezas=riquezasObtenidas;
        ganador=fueGanador;
    }

    public Jugador(String nombreJugador, Color colorFicha, int dineroInicial, NodoDoble nodoInicial){//Es mejor que sus datos lo sreciba directamente de la solicitud general, esto por el hecho de terner que ditar y porque cuando quiera volvera jugar, no tendrá que estar pidiendo nuevamente los datos sino simplemente trabajar a base de los que recibió en un principio, de tal manera que es mejorq que reciba los datos de manera directa y que luego los qu
        nombre=nombreJugador;
        colorDeFicha=colorFicha;
        dinero=dineroInicial;
        riquezas=dinero;
        nodoPosicionActual=nodoInicial;
        ubicacionActual=nodoPosicionActual.obtnerContenido();
        ubicacionActual.colocarFicha(colorFicha);//pues debe tener la ficha el inicio, así de esta manera no tienes que andar creando otro método para que reciba a los jugadores de 
       /*lugares.simularArreglo(registro.obtenerListaGruposLugares().darTamanio());
        servicios.simularArreglo(registro.obtenerListaGruposServicios().darTamanio());
        estaciones.simularArreglo(registro.obtenerListaGruposEstaciones().darTamanio());*///ya no porque los métodos ahora trabajan conforme le vayan llegando las cosas
        
        //A montón y la casilla misma les coloque el color, lo cula resulta ser ilógico por el hecho de que una casilla no puede hacer esto... ademá debes colocarlo porque sino al quitar la ficha
        //no hallará el color que busca y tronitos XD
        //RECIBIR el nodo Actual inicial, es decir, inicio xD
        
    }
    
    public void incrementarPropiedades(){
        propiedadesTotales++;//puesto que cada compra admite como máximo una propiedad
    }
    
    public int pagar(int debe){
        dinero-=debe;
        return debe;//será mandado a los jugadores respectivos hacia su métod recibirDInerp
    }
    
    public void recibirDInero(int monto){//útil para el dinero inicial, tanto para los demás incrementos que en sus riquezas se den
        dinero+=monto;
    }
    
    public void incrementarRiquezas(int incremento){
        riquezas+=incremento;
    }
    
    public void decrementarRIquezas(int decremento){
        riquezas-=decremento;
    }
       
    private void declararBancarrota(){
        bancarrota=true;        
    }
    
    public void declaraseGanador(){
        ganador=true;
    }
    
    public boolean darEstadoGanador(){
        return ganador;
    }
    
    public void recibirCondena(int sentencia){
        encarcelado=true;
        condena=sentencia;
    }
    
    public void establecerTurnosPerdidos(int numeroTurnos){
        turnosPerdidos += numeroTurnos;//se me hace que debería ser una suma
    }
    
    public void reducirCondena(){
        condena-=1;
    }
    
    public void reducirTurnosPerdidos(){//bien hubiera podido ser -> establecerTurnosPeridos(obtnerTUrnosPerdidos-1)
        turnosPerdidos-=1;
    }
    
    public void recibirTarjetaParaSalirDeCarcel(SalgaCarcel tarjetaDeLibertad){
        tarjetasSalgaCarcel.anadirAlFinal(tarjetaDeLibertad);
    }
    
    public void reacomodarTarjetas(){//no pongo transferir tarjeta porque esto se hará al utilizar el método obtener tarjetas y luego el método de ellas que permite retornar el último elemento        
        Nodo<SalgaCarcel> nodoAuxiliar;
        nodoAuxiliar = tarjetasSalgaCarcel.obtnerPrimerNodo();
                 
        tarjetasSalgaCarcel.eliminarUltimo();//si no funciona es porque no se hizo bien la asignación en ese caso debería usar un métood establecer y luego pasarle el valor null, al último nodo        
    }
    
    
            
    public void recibirLibertad(){
        condena=0;
    }//podría hacerse 0 de una vez, pero para que encaje con el hecho de que la tjt es quien le otorga la libertad   
    
    public void establecerNodoActual(NodoDoble<Casilla> nodo){//si da problemas ha de se por el hecho de que no se especificó su tipo... pero no lo permitía...
        nodoPosicionActual= nodo;
        establecerUbicacion(nodoPosicionActual.obtnerContenido());
    }//recuerda que esto siempre se app, aunque el inicio no deba exe su acción la 1er vez, eso se contempla en el cuerpo de él
    
    public void establecerUbicacion(Casilla nuevaUbicacion){//esto será devuelto por el método de la lista circular, donde ya solo bastará obtener el contenido del nodo, 
        ubicacionActual=nuevaUbicacion;                                 //o no hacer algo como esto al devolver dicho método el respecetivo contenido al llegar a la posición, por 
                                                                                           //medio del for
    
      ubicacionActual.accion(this);//aquí se invoca de una vez la acción de jugador 
        System.out.println("");
    }
    
    public void moverseA(int indiceCasillaDestino){//Este será enviado en el método de la casilla, donde exe su acción, haciendo contenido.ObtnerIndiceRecorrido
        if(indiceCasillaDestino>ubicacionActual.obtnerIndiceRecorrido()){//si de alguna manera puede que cb el índice mas no el contenido, sería mas seguro revisar directamente el contenido hasta que se encuentre uno igual, de tal forma que pare y salga del ciclo, para no seguir avanzando y así exe la acción del contenido y así se pueda hacer específicamente lo de la casilla no lo de su ubicación...
            int vecesARecorrer=indiceCasillaDestino-(ubicacionActual.obtnerIndiceRecorrido());//por el hecho de que comienza por el 0... sino podrías usar un for y así no tener que sumar ese 1            
            avanzar(vecesARecorrer);
            
        }else{//pues no podrá referenciarse nunca a sí misma, nos encargaremos de ello
              int vecesARecorrer=ubicacionActual.obtnerIndiceRecorrido()-(indiceCasillaDestino);//no es necesario sumarl e1 puesto que el inicio tiene la casilla #0 y una distancia es una distancia, sin importar de como se comienze a enumerar
              retroceder(vecesARecorrer);
        }
    }//Creo que va a tener que llamar de forma similar a avanzar cuando es inicio, al método quitar ficha, puesto que no se sale y vuelve a entrar a jugar, sino rpovocará lo mismo que sucedía con avanzar e ir a inicio
    
    public void avanzar(int cantidadACaminar){
        if(cantidadACaminar!=0 /*&& (cantidadACaminar% totalCasillas [es decir el último índice +1]!=0*/){//pues no será su múltiplo yyy no habrá error por el hecho de que yo me encaro de enviar el número según su sentido, es decir si + entonces avanzar, si - entonces retroceder
            ubicacionActual.quitarFicha(colorDeFicha);//así con esto mato de una vez el problema de estar averifuando donde debo de llamar al método de quitar la ficha, pueto que sin importar donde sea que se necesite mover, siempre se quitará la ficha antes de moverse 
        
            while(cantidadACaminar>1){//Debe ser >1 por la línea que siempre se exe, por lo tanto hay que qudarse un nodo antes, para que salga bien ó simplemente quítala xD
                cantidadACaminar--;                
                nodoPosicionActual=nodoPosicionActual.obtnerSiguiente();
                
                if(nodoPosicionActual.obtnerContenido().obtenerNombre().equalsIgnoreCase("Inicio")){
                     establecerNodoActual(nodoPosicionActual);
                     ubicacionActual.quitarFicha(colorDeFicha);
                }
            }
            
            establecerNodoActual(nodoPosicionActual.obtnerSiguiente());//con esto ya se hace la llamada al método acción de la casilla respectiva, esto pore medio del método establecedor del nodo actual
        }else{
            JOptionPane.showMessageDialog(null, "Oh! yo crei que irías a otra parte xD", "", JOptionPane.INFORMATION_MESSAGE);
        }        
    }
    
    public void retroceder(int cantidadACaminar){
        if(cantidadACaminar>0){//bueno aunque con un diferente de 0 estaría bien xD, porque me encargo de que no se puedan ingresar vals negativos Y MENOS el 0, sino se povoca un bucle infinito, porque estará llamadno cada vez 
            ubicacionActual.quitarFicha(colorDeFicha);//al método de stablecer nodoActual, quien se encara de exe la acción de la ubicación nueva, pero como es la mism y lo que hace la acción de esta [En este caso retroceder, pero puede suceder lo mismo para avanzar y mover A]
            //es dirigirse a ella misma pues se provoca un StackOverFlow xD, por le hecho de no haber nada que lo pare, lo cual se arreglará con el if actual el cual evita que se exe cuando se refiera a ella misma, en este caso cuando sea 0 la cdad a retroceder
        
            while(cantidadACaminar>0){
                cantidadACaminar--;                
                nodoPosicionActual=nodoPosicionActual.obtenerAnterior();
            }            
            establecerNodoActual(nodoPosicionActual);//recuerda, este ya solicita la acción
        }else{
            JOptionPane.showMessageDialog(null, "Al parecer no retrocederás xD", "", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    
    /**
     * Encargado de crear el super listado, en este caso de lugares, donde 
     * se agrega un nuevo nodo si es que no existe uno con el listado enlazado
     * de casillas correspondiente, en el ligar donde se quedó [el no dejar la forma 
     * de este super listado semejante a la forma en que se fueron creando los grupos
     * facilita las cosas, puesto que solamente una vez tendrá que irse a buscar el tamaño
     * puesto que en este proceso al crear la lista de una vez se le asigna el tmanaño límite
     * que no es para decirle que ya no crezca sino para comparar su tamaño actual con el
     * límite, de tal manera que se conozca si se han completdo los grupos; esta variable no 
     * sería necesaria a no ser de lo ant, puesto que como los grupos ya se tienen establecidos
     * entonces no hay posibilidad de que se le asigne otro cuando ya no debería, además del 
     * hecho de que no se repiten
     * @param casilla
     */
    public void addPropiedades(Casilla casilla){//pues esto es lo que tendrá en el listado -> Estos métodos son los que vienen a sustituir a los tres de abajo, estos se basan en el hecho de que la súper lista se va generando conforme vayan agregando
        Nodo<ListaEnlazada<Casilla>> auxiliar;
        auxiliar= lugares.obtnerPrimerNodo();//aquí recibo la primer lista enlazada si es que no es null
        
        Lugar auxiliarCasilla;
         auxiliarCasilla=(Lugar)casilla;
        
        if(!lugares.estaVacia()){//lo puse aquí por el hecho de se encuentra un while que no requiero se exe al suceder esto, por lo cual requería tener un salto de tal manera que al llegar hasta el destino tenga la porción que necesito
            while(auxiliar!=null && !auxiliar.obtenerObjectcEnCasilla().obtenerNombre().equalsIgnoreCase(auxiliarCasilla.obtenerGrupo())){//Así porque yo quiero saber si ya llegué al final sin enconrar coincidencia
                auxiliar=auxiliar.obtenerSiguiente();
            }
            if(auxiliar==null){//Se crea el listado de subgrupos de los grupos generales, ya que puede ser null porque no tenía nada o porque llego hasta el final sin encontrar coincidencias   
                //Se me hace que el error es por este método, ya que se le asigna a un nodo nulo un contenido, lo cual no puede hacerse, entonces lo que deberás hacer, es crear un auxiliar pero de la lista de talmanera que cada vez vayas obteneiendo al nodo con accediendo antes a la lista, ooo mejor, lo que deberás hacer será usar de una vez a lugares en lugar del auxiliar y añadir al final, lo cual vendría a hacer lo que establecer contenido quería pero solo puede con un nodo existente
                //auxiliar.establecerContenido(new ListaEnlazada<Casilla>(auxiliarCasilla.obtenerGrupo()));//cree la lista interna de tipo ListaEnlazada<Casilla>, entonces solo hace falta asignarle su contenido... ya está agregado, es decir que antes de comentarla , en esta estructura te hacía falta add el contenido xD
                ListaEnlazada listaInterior=new ListaEnlazada<Casilla>(auxiliarCasilla.obtenerGrupo());
                listaInterior.anadirAlFinal(casilla);
                lugares.anadirAlFinal(listaInterior);//pues sabe exactamente cual es su final de tal manera que la lista se irá a crear directamente al espacio correspondiente
                /*lugares.anadirAlFinal(new ListaEnlazada<Casilla>(auxiliarCasilla.obtenerGrupo()));
                auxiliar.obtenerObjectcEnCasilla().anadirAlFinal(casilla);*/
                //se llama al método que busca el tamaño final que llegará a tener Ssi se almacenan todos sus elementos [en este caso las diferentes propiedades]
                listaInterior.establecerTamanioLimite(encontrarGrupoLugar(casilla));//este sería el valor con el que debería comparar para saber si tiene a todas las tarjetas para así permitirle comprar casas            
            }else if(auxiliar.obtenerObjectcEnCasilla().obtenerNombre().equalsIgnoreCase(auxiliarCasilla.obtenerGrupo())){//quiere decir que ya estabe el listado interno, por ello hay que corroborar si ya se completó o no el grupo, esto para cuando desee construir y para cobrar el costo por estancia en este caso
               auxiliar.obtenerObjectcEnCasilla().anadirAlFinal(casilla);                   
               if(auxiliar.obtenerObjectcEnCasilla().darTamanio()==auxiliar.obtenerObjectcEnCasilla().darTamanioLimite()){                   
                   Nodo<Casilla> auxiliarNodo;
                   auxiliarNodo=auxiliar.obtenerObjectcEnCasilla().obtnerPrimerNodo();
                   
                   for (int numeroElemento = 0; numeroElemento < auxiliar.obtenerObjectcEnCasilla().darTamanio(); numeroElemento++) {//se establece el hecho de estar completo el grupo para cada uno de los integrnates del grupo en cuestión
                       Propiedad casillaAuxilair;
                       casillaAuxilair= (Propiedad)auxiliarNodo.obtenerObjectcEnCasilla();
                       
                      casillaAuxilair.establecerGrupoCompleto();//esto para que cuando se esté por realizar el cobro, se obtenga de una manera más directa el valor de verdad de esta varibles, y así de esta manera cuando acceda acualquiera de
                      //las propiedades que a ese grupo pertenecen se sabrá que ya está completo                       
                      auxiliarNodo=auxiliarNodo.obtenerSiguiente();
                   }
               }
            }//termina el else para cuando ya existían elementos del mismo grupo     
            
        }else{//entonces a crera el nodo se ha dicho xD
            ListaEnlazada listaInterior=new ListaEnlazada<Casilla>(auxiliarCasilla.obtenerGrupo());//de primero hay que comenzar con lo más interior para llegar a lo exterior
            listaInterior.anadirAlFinal(casilla);
            lugares.anadirAlFinal(listaInterior);
            //auxiliar.obtenerObjectcEnCasilla().anadirAlFinal(casilla);
            listaInterior.establecerTamanioLimite(encontrarGrupoLugar(casilla));
        }
        
        if(propiedadesTotales == SolicitudDatos.registroDatos.obtenerTotalPropiedades()){
            DesarrolloPartida.encontrarGanador(this);//y de esta manera se manda el jugador que ha obtneido todas las propiedades :) chan chan xD
            menuFinal = new MenuDecisionFinal(new javax.swing.JFrame(), true);
            menuFinal.setLocationRelativeTo(null);
            menuFinal.setVisible(true);
            
        }//así de esta manera cuando se revise al finalizar la acción, se obtendrá al ganador
  }//de manera similar para las otras 2, esto irá en un switch, dependiendo del tipo de propiedad que sea, SE MANDA A LLAMAR CADA VEZ QUE ADQUIERA UNA PROPIEDAD 
    
    public void addServicios(Casilla casilla){//pues esto es lo que tendrá en el listado//estos dejarán de ser.. pues ya no tienen una forma de frupo como la de lugar ESTO SERÍA LO ÚNCIO QUE SE CONSERVARÍA, PARA SABER CUANDO ES QUE SE POSEE A TODOS LOS ELEMENTOS               
            servicios.establecerTamanioLimite(SolicitudDatos.registroDatos.obtenerListaElementosServicios().darTamanio());//deberá ser el tamaño puesto que es la lista de los elementos que conforman el grupo en general
            servicios.anadirAlFinal(casilla);
            
            Nodo<Casilla> auxiliarNodo;
            auxiliarNodo=servicios.obtnerPrimerNodo();
            
            if(servicios.darTamanio()==servicios.darTamanioLimite()){
                for (int servicio = 0; servicio < servicios.darTamanio(); servicio++) {//podría haber sido tb el límite...
                     Propiedad casillaAuxilair;
                       casillaAuxilair= (Propiedad)auxiliarNodo.obtenerObjectcEnCasilla();
                       
                       casillaAuxilair.establecerGrupoCompleto();
                       
                       auxiliarNodo=auxiliarNodo.obtenerSiguiente();
                }//fin del for que se encarga de recorrer la lista para así actualizar el valor de verdad dela varibale grupoCOmpleto
            }//fin del if en el que se entra cuando se tiene el tamño igual al número de elementos que en el grupo en cuestión se
            //Encuentra, en este caso, sería el tamaño de la lista de registro de servicios
           if(propiedadesTotales == SolicitudDatos.registroDatos.obtenerTotalPropiedades()){
               DesarrolloPartida.encontrarGanador(this);   
               menuFinal = new MenuDecisionFinal(new javax.swing.JFrame(), true);
               menuFinal.setLocationRelativeTo(null);
               menuFinal.setVisible(true);
               
            }
            
    }
    
    public void addEstaciones(Casilla casilla){//pues esto es lo que tendrá en el listado       
            estaciones.establecerTamanioLimite(SolicitudDatos.registroDatos.obtenerListaElementosEstaciones().darTamanio());//de tal manera que en este único momento sea en el cual se va a buscar a registro para así saber después si se ha completado o no el grupo       
            estaciones.anadirAlFinal(casilla);
            
             Nodo<Casilla> auxiliarNodo;
            auxiliarNodo=estaciones.obtnerPrimerNodo();
            
            if(estaciones.darTamanio()==estaciones.darTamanioLimite()){//debe ser darTamanio, pues se están trabajando con los elemntos como no hay subgrupos, entonces el tamaño de la lista es el número total que conforma el grupo general
                for (int estacion = 0; estacion < estaciones.darTamanio(); estacion++) {//podría haber sido tb el límite...
                     Propiedad casillaAuxilair;
                       casillaAuxilair= (Propiedad)auxiliarNodo.obtenerObjectcEnCasilla();
                       
                       casillaAuxilair.establecerGrupoCompleto();
                       
                       auxiliarNodo=auxiliarNodo.obtenerSiguiente();
                }
            }
            
            if(propiedadesTotales == SolicitudDatos.registroDatos.obtenerTotalPropiedades()){
                DesarrolloPartida.encontrarGanador(this);                                      
                menuFinal = new MenuDecisionFinal(new javax.swing.JFrame(), true);
                menuFinal.setLocationRelativeTo(null);
                menuFinal.setVisible(true);
                
            }
    }
    
     
    //encuentra el número de elementos que cada subgrupo posee, para así saber si completó ya o no el grupo el jugador, esto para construir casas
    private int encontrarGrupoLugar(Casilla casillaPropiedad){//se hace la búsqueda del respectivo grupo en los datos guardados del registro        
        Nodo<String> nodoAuxiliar;
        nodoAuxiliar = SolicitudDatos.registroDatos.obtenerListaGruposLugares().obtnerPrimerNodo();
        
        Lugar auxiiar;
        auxiiar =(Lugar) casillaPropiedad;
        
        for (int numeroGrupo = 0; numeroGrupo < SolicitudDatos.registroDatos.obtenerListaGruposLugares().darTamanio(); numeroGrupo++) {//al nodo será a quien se le asigne como CONTENIDO, la lista enlazada correspondiente al subgrupo del general correspondiente
            if(auxiiar.obtenerGrupo().equalsIgnoreCase(nodoAuxiliar.obtenerObjectcEnCasilla())){//si pues esta lista de registro, almacena strings
                return nodoAuxiliar.obtenerNumeroElementosNodo();//obtienes el numero de elementos[siendo estos como Retalhuleu] del nodo, que para este caso serían los lugares del subgrupo [como costa] del grupo general[lugares, p.ej]               
            }//este numero de elementos por ser de esa forma, se establece en el diálogo de la customización propiedades [la cual ahora solo es de lugar]
            
            nodoAuxiliar=nodoAuxiliar.obtenerSiguiente();
        }        
        return -1;//pongo el -1, porque arriba de una vez se retornará el valor[que antes se devolvía aquí], para quitar el break y no hacer trabajo de más buscando el sig nodo, de todos modos nunca llegará aquí
    } 
     
    /**
     *Método llamado en la tarjeta donde de una vez se coloca como parámentro
     * para que cobre al jugador si es que lo debe hacer [Es decir si no está hipotecada]
     * y muestre el msje debido si se da la situación
     * @param propiedad
     * @return
     */
    public int darDatoCobroPropiedad(Casilla propiedad){//caundo se emplea este método, ya se sabe que tiene a ese lugar el jugador en su listado, por el hecho de que se registró en la tjt además de tenerlo él en mente
         int pagoTotal;
            
            Propiedad auxiliar;
            auxiliar = (Propiedad)propiedad;
            if(!auxiliar.estaHIpotecada()){
                if(!auxiliar.estaCompletoELGrupo()){//pues media vez se han adquirido las propiedades no pueden ser vendidas, solo perdidas por completo, así que este atrib no se pierde...aunque una propiedad compañera esté equivocada        
                   pagoTotal=auxiliar.obtenerPagoPorEstancia();  
                   JOptionPane.showMessageDialog(null, " Te toca pagar $" + pagoTotal+ " a "+ obtnerNombre()," Pago por estancia", JOptionPane.INFORMATION_MESSAGE);//si pudiera ponerle ícono mucho mejor            
                    return pagoTotal;//esto con tal de mostrar el JOP solamente cuadno deba pagar algo y no cuando esté hipotecada
                }else{
                    Lugar lugarAuxiliar;
                    lugarAuxiliar = (Lugar)auxiliar;
                    
                    pagoTotal=(auxiliar.obtenerPagoPorEstancia()*2)+(lugarAuxiliar.obtenerPrecioPropiedadSImple()*lugarAuxiliar.obtenerCantidadSimplesPoseidas())+
                            (lugarAuxiliar.obtnerPrecioPropiedadSofisticada()*lugarAuxiliar.obtenerCantidadSoisticadasPoseidas());
                    JOptionPane.showMessageDialog(null, " Te toca pagar $" + pagoTotal+ " a "+ obtnerNombre()," Pago por estancia", JOptionPane.INFORMATION_MESSAGE);//si pudiera ponerle ícono mucho mejor                                 
                    return pagoTotal;//Se va a hacer así para no estar accediendo a la tarjeta y luego llamar a su método respectivo para establecer el pago total y hacer todo el mntón de conversiones... xD
                }       
            }//fin del if que verifica no estén hipotecadas            

             return 0;//si da 0 es porque está hipotecada
     }
//suma total= pago estancia;//si  grupoCOmpleto=false
//suma total = (pago estancia*2)+(precio edificació)*(cdad de construcciones)//puede ser el de las casas o el de los edificios dependiendo de que construccioón tenga actualmente [creo que debería poner una var -> construccionActual y ahí el nombr epara saber que cobrar, o simplemente hacer una línea completa donde prácticamente se obvie el cobro de una construcción por el hecho de que haré al númerode edificaciones [simples o sofisticadas] =0
      
                 
                //NO, YA NO HAGAS, ESTO PUES COMO RECIBES A LA CASILLA DE LA QUE SE QUEIRE EL COBRO Y COMO ES LA MISMA REFERENCIA, ENTONCES PUEDES HALLAR EL DATO DE FORMA DIRECTA,
                 //ACCEDIENDO A LAS VAR DE LA CASILLA ENVIADAD A ÉL PARA DETERMINAR LOS CÁLCULOS, luego, si tuviera que ser afecadaa la casilla, tb se afectaría la de la super lista xD, y con ello problema soluto xD
     
     public int darDetalleCostoServicios(Casilla propiedad, int sumaDados){//void porue empleará el método de tarjeta para enviarle directamemnte el valor final
         //detalle -> (costoPropiedad*númeroServicios)*SumaDados                
         Propiedad auxiliar;
         auxiliar = (Propiedad)propiedad;        
         
         if(!auxiliar.estaHIpotecada()){
             int pagoTotal =auxiliar.obtenerPagoPorEstancia()*servicios.darTamanio()*sumaDados;
             JOptionPane.showMessageDialog(null, " Pago total a " +obtnerNombre()+"$ " + pagoTotal ," Pago por uso de servicio", JOptionPane.INFORMATION_MESSAGE);//si pudiera ponerle ícono mucho mejor                                
             //auxiliar.establecerPagoTotal(servicios.darTamanio()*auxiliar.entregarPagoEstancia()*auxiliar.entregarSUmaDados());//esto era con tjt, pero sale muuuuchiiisisímo mejor con casilla xD
             return pagoTotal;
         }
         
         return 0;
     }
     
     public int darDetalleCobroEstaciones(Casilla propiedad){
         //detalle -> costoPropiedad*2*numeroEstaciones        
         Propiedad casillaPropiedad;
         casillaPropiedad= (Propiedad) propiedad;

         if(!casillaPropiedad.estaHIpotecada()){
            int pagoTotal=casillaPropiedad.obtenerPagoPorEstancia()*2*estaciones.darTamanio();//jamás dará 0, puesto que si entró aquí es porque revisó y corroboró que ya había sido comprada    
            JOptionPane.showMessageDialog(null, " Te toca pagar $" + pagoTotal+ " a "+ obtnerNombre()," Pago uso de la estación", JOptionPane.INFORMATION_MESSAGE);//si pudiera ponerle ícono mucho mejor            
            return pagoTotal;
         }
         return 0;
     }
     
     public void declararseEnBancarrota(){
         declararBancarrota();
         ubicacionActual.quitarFicha(colorDeFicha);//pues si porque si no se quedará ahí barada xD
         dinero=0;
         //recuerda, los métodos que se encargan de volvar a las casillas a su estado original deben ser llamados antes de hacer dicho listado =null         
         lugares=null;
         devolverLugaresxD();         
         servicios=null;
         devolverServicios();         
         estaciones=null;
         devolverEstaciones();
         
         nodoPosicionActual=null;
         ubicacionActual=null;
         encarcelado=false;
         condena=0;
         debe=0;
         turnosPerdidos=0;
         tarjetasSalgaCarcel=null;
     }
     
     /*
        Método encargado de reocrrer el listado del jugador para volver las propiedades y sus subordinados
        a los valores que originalmente se le concedieron
     */
     private void devolverLugaresxD(){
         if(!lugares.estaVacia()){
            Nodo<ListaEnlazada<Casilla>> auxiliarNodo;
            auxiliarNodo=lugares.obtnerPrimerNodo();
         
            Nodo<Casilla> auxiliarCasilla;
            auxiliarCasilla=auxiliarNodo.obtenerObjectcEnCasilla().obtnerPrimerNodo();
         
            for(int numeroNodo=0; numeroNodo<lugares.darTamanio(); numeroNodo++){//for que se encarga de cb de nodo [Es decir de grupo hasta que llegue al útlimo para reasignarle sus valores iniciales             
                for (int nodoInterno = 0; nodoInterno < auxiliarNodo.obtenerObjectcEnCasilla().darTamanio(); nodoInterno++) {
                    Lugar auxiliarLugar;
                     auxiliarLugar=(Lugar)auxiliarCasilla.obtenerObjectcEnCasilla();
                 
                     auxiliarLugar.reestablecerValoresOriginales();                 
                 
                     auxiliarCasilla=auxiliarCasilla.obtenerSiguiente();
                 }//fin del for que recorre nodos internos
             
                 auxiliarNodo=auxiliarNodo.obtenerSiguiente();
            }//fin del for que recorre nodos exteriores        
         }         
         
     }//listo, aqupi no habrá problemas con respecto a obtener uno nulo por el hehco de que como esos datos solamente servirán para
     //hacer los cb en el instante y no guardarlos depués de haber naveado por ellos...
    
     private void devolverEstaciones(){
         if(!estaciones.estaVacia()){
            Nodo<Casilla> auxiliarCasilla;//si te das cta, cada vez que recorres un nodo, debes obtener el primero antes del ciclo que se encargará de recorrer el auxiliar y dentro de él colocar la línea que emplea al método para obtener el siguiente
            auxiliarCasilla=estaciones.obtnerPrimerNodo();
         
            for (int estacion = 0; estacion < estaciones.darTamanio(); estacion++) {
                Estacion auxiliarEstacion;
                auxiliarEstacion = (Estacion) auxiliarCasilla.obtenerObjectcEnCasilla();
              
                 auxiliarEstacion.reestablecerValoresOriginales();
             
                 auxiliarCasilla=auxiliarCasilla.obtenerSiguiente();
            }
         }         
     }//fin del método que devuelve las estaciones
     
     private void devolverServicios(){
         if(!servicios.estaVacia()){
            Nodo<Casilla> auxiliarCasilla;//si te das cta, cada vez que recorres un nodo, debes obtener el primero antes del ciclo que se encargará de recorrer el auxiliar y dentro de él colocar la línea que emplea al método para obtener el siguiente
            auxiliarCasilla=servicios.obtnerPrimerNodo();
            
            for (int servicio = 0; servicio < servicios.darTamanio(); servicio++) {
                 Servicio auxiliarServicio;
                auxiliarServicio = (Servicio) auxiliarCasilla.obtenerObjectcEnCasilla();
              
                 auxiliarServicio.reestablecerValoresOriginales();
             
                 auxiliarCasilla=auxiliarCasilla.obtenerSiguiente();
             }
         }
         
     }//fin del método que devuelve las estaciones
     
    /**
     *Empleado para verificar la capacidad de pago, esto en las tjt's o situaciones
     * en las que se compre, (es decir que no se incluye las de pago en ellas si
     * o paga o paga, como los extorsionistas xD, luego deberá ver como pagar sus
     * deudas y si no puede pues declararse en bancarrota)
     */
    public int obtenerDinero(){
        return dinero;
    }
    
    public Casilla obtenerUBicacion(){//no recuerdo donde sería empleado o se emplea...
        return ubicacionActual;
    }           
    
    public boolean estaEnBancarrota(){
        return bancarrota;
    }
    
    public String obtnerNombre(){
        return nombre;
    }
    
    public int obtenerTurnosPerdidos(){
        return turnosPerdidos;
    }
    
    public int obtenerCondena(){
        return condena;
    }
    
    public Color obtnerColor(){
        return colorDeFicha;
    }
    
    public int darRiquezasActuales(){
        return riquezas;
    }
    
    public ListaEnlazada<ListaEnlazada<Casilla>> obtenerListadoLugares(){
        return lugares;
    }
    
    public ListaEnlazada<Casilla> obtenerListadoServicios(){
        return servicios;
    }
    
    public ListaEnlazada<Casilla> obtenerListadoEstaciones(){
        return estaciones;
    }    
    
    public ListaEnlazada<SalgaCarcel> obtenerListadoTarjetasSC(){
        return tarjetasSalgaCarcel;
    }//recuerda que con estas lo que habrá que hacer será crear un método que reciba la tarjeta y con ello la add a su respectivo listado
    //en el lo que habrá que hacer será emplera el método de las listas enlazadas, donde se retorna el primer elemento de la lista,... mejor el 
    //último, para luego hacer que el penúltimo haga su sig nodo=null  y así pierda la referencia a la tarjeta, este último método será llamado
    //luego de haber mandado la tjt al jugador para que no se haga un revoltijo xD
    
}
