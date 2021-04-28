/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegistroYManejoTemporalDatos;

import BackendBancopoly.Nodo;
import BackendBancopoly.ListaEnlazada;
import Casillas.Casilla;
import Casillas.Propiedad;
import Casillas.Estacion;
import Casillas.Servicio;
import Casillas.Lugar;
import FrontendBancopoly.Facturita;
import FrontendBancopoly.MIsPoseciones;
import FrontendBancopoly.SolicitudDatos;
import Jugadores.Jugador;
import javax.swing.*;
import Partida.DesarrolloPartida;


/**
 *
 * @author phily
 */
public class ManejoPosesionesJugador {
    Jugador todosLosJugadores[];
    private String listadoSubgrupos[];//es un hecho que deberá ser local, por el hecho de que los demás métodos trabajan a base de los listados anteriores a los que se desean encontrar, y como no puede hacerse este trabajo de 
    //corrido por el hecho de que se depende de la selección, entonces no se l epuede mandar directamente el listado, porque no tendrá el "qué debe buscar", esto app para todos los listado en los cuales se requiere buscar
    private String listadoLugares[];//para guardar de forma temporal, por eso creo que debería de hacerse null luego de haber cerrado la ventana [es decir hacer esto antes de abrirla] para que una condición que por el momento no se donde está no se exe puesto que lo que hace es que revisa si el arr es = null y como no se está reestableciendo, entonces por ello es que lo termina mostrando
    private String construccionEnLugar;
    private Nodo<ListaEnlazada<Casilla>> nodoGrupoActual;//es decir donde se quiere buscar
     private Nodo<Casilla> nodoLugarActual;//por la forma de la búsqueda no es útil por el momento puesto que no hay más bracitos XD que hallar
     private ListaEnlazada<Casilla> listaServicios;
     private ListaEnlazada<Casilla> listaEstaciones;
     private boolean estaCompletoElGrupo;
        
    Facturita dialogoFacturita = new Facturita(new javax.swing.JFrame(), true);
    
    public ManejoPosesionesJugador(Jugador[] jugadores){
        todosLosJugadores=jugadores;
    }
    
    /**
     * Método base de los que realmente se necesita, puesto que este solo encuentra el listado completo
     * pero de un solo nodo, pensando de forma estática, pero se puede modificar a una dinámica, al simplemente
     * saber el número de nodo en cuestión al cual se debe dirigir la búsqueda, para dar con el resultado deseado
     *
     * @param jugadorEnCuestion
     * @return
     */
/*    public String[] darListadoLugares(int jugadorEnCuestion){//es decir los nombres, como Retalhuleu...
        String listadoNombresGrupos[];
        String listadoNombresLugares[];   

        if(!todosLosJugadores[jugadorEnCuestion].obtenerListadoLugares().estaVacia()){//si, pues media vez no está vacía tengo todo lo demás puesto que al llegar una casilla, debe existir un nodo que a su vez tenga un contenido que sea en este caso una listaEnlazada<Casilla> quien almacenará a esta casilla solicitante quien posee todos los datos que se requieren para trabajar
            listadoNombresGrupos = new String[todosLosJugadores[jugadorEnCuestion].obtenerListadoLugares().darTamanio()];
            
            Nodo<ListaEnlazada<Casilla>> nodoAuxiliarListadoLugares;//el nodo que contiene a los subgrupos 
            nodoAuxiliarListadoLugares=todosLosJugadores[jugadorEnCuestion].obtenerListadoLugares().obtnerPrimerNodo();//Aquí obtienes el nodo que tiene como contenido la ListaEnlazada<Casillas>
            
            ListaEnlazada<Casilla> listaAuxilair;
            listaAuxilair=nodoAuxiliarListadoLugares.obtenerObjectcEnCasilla();//Aquí obtienes la Lista
              
            listadoNombresLugares= new String[listaAuxilair.darTamanio()];//como lo que quieres son los nombres de los lugares, entonces debe tener el tamaño del listado de las casillas a quienes les pertencen los nombres
            
            Nodo<Casilla> nodoAuxiliarCasillas;//el nodo que contiene a las casillas
            nodoAuxiliarCasillas=listaAuxilair.obtnerPrimerNodo();//Aquí el nodo de tipo Casilla           
            
            
            
            for (int lugares = 0; lugares < listadoNombresLugares.length; lugares++) {              
              listadoNombresLugares[lugares]=nodoAuxiliarCasillas.obtenerObjectcEnCasilla().obtenerNombre();//aquí obtienes la casilla y por ello su nombre respectivo
                
              nodoAuxiliarCasillas=nodoAuxiliarCasillas.obtenerSiguiente();
            }//fin del for que recorre los nodos de la lista enlazada de casillas
            
            for (int subgrupos = 0; subgrupos < listadoNombresGrupos.length; subgrupos++) {
                listadoNombresGrupos[subgrupos]=nodoAuxiliarListadoLugares.obtenerObjectcEnCasilla().obtenerNombre();//puesto que el listado se nombro con el nombre xD de los subgrupos
                
                nodoAuxiliarListadoLugares=nodoAuxiliarListadoLugares.obtenerSiguiente();
            }
        }
        
        
        return null;
    }*/
    
    public void formarListadoServicios(int jugadorEnCuestion){
        MIsPoseciones.listado_servicios.removeAllItems();
        
        if(!DesarrolloPartida.jugadores[jugadorEnCuestion].obtenerListadoServicios().estaVacia()){//Esto evita en el caso de las hipotecas que pueda ser add una estación cuando no la hay, lo cual evita un null pointer y por ello que se pueda hipotecar, para evitar errores al  add al listado luego de la transacción lo que es útil es el tamaño del listado en cuestión, pues si está vaía, será 0
            Nodo<Casilla> nodoAuxiliarServicios;
            nodoAuxiliarServicios=DesarrolloPartida.jugadores[jugadorEnCuestion].obtenerListadoServicios().obtnerPrimerNodo();
        
             Propiedad auxiliarPropiedad;
            auxiliarPropiedad =(Propiedad) nodoAuxiliarServicios.obtenerObjectcEnCasilla();
            
            listaServicios=DesarrolloPartida.jugadores[jugadorEnCuestion].obtenerListadoServicios();
                                  
            for (int servicio = 0; servicio < DesarrolloPartida.jugadores[jugadorEnCuestion].obtenerListadoServicios().darTamanio(); servicio++) {                
                if(servicio>0){
                   nodoAuxiliarServicios=nodoAuxiliarServicios.obtenerSiguiente();
                }
                
                if(auxiliarPropiedad.estaHIpotecada()){
                    MIsPoseciones.listado_servicios.addItem("[H] "+nodoAuxiliarServicios.obtenerObjectcEnCasilla().obtenerNombre());                         
                }else{
                    MIsPoseciones.listado_servicios.addItem("[NH] "+nodoAuxiliarServicios.obtenerObjectcEnCasilla().obtenerNombre());                         
                }
            }//fin del for
            
            agregarServiciosAListadosHipotecas(null, null, nodoAuxiliarServicios, jugadorEnCuestion);
        }
    }
    
    public void formarListadoEstaciones(int jugadorEnCuestion){
        MIsPoseciones.listado_estaciones.removeAllItems();
        
        if(!DesarrolloPartida.jugadores[jugadorEnCuestion].obtenerListadoEstaciones().estaVacia()){
            Nodo<Casilla> nodoAuxiliarEstaciones;
            nodoAuxiliarEstaciones=DesarrolloPartida.jugadores[jugadorEnCuestion].obtenerListadoEstaciones().obtnerPrimerNodo();
            
            listaEstaciones=DesarrolloPartida.jugadores[jugadorEnCuestion].obtenerListadoEstaciones();
            
            Propiedad auxiliarPropiedad;
            auxiliarPropiedad =(Propiedad) nodoAuxiliarEstaciones.obtenerObjectcEnCasilla();
        
            for (int estaciones = 0; estaciones < DesarrolloPartida.jugadores[jugadorEnCuestion].obtenerListadoEstaciones().darTamanio(); estaciones++) {       
                if(estaciones>0){
                    nodoAuxiliarEstaciones=nodoAuxiliarEstaciones.obtenerSiguiente();                
                }
                if(auxiliarPropiedad.estaHIpotecada()){
                    MIsPoseciones.listado_estaciones.addItem("[H] "+nodoAuxiliarEstaciones.obtenerObjectcEnCasilla().obtenerNombre());
                }else{
                    MIsPoseciones.listado_estaciones.addItem("[NH] "+nodoAuxiliarEstaciones.obtenerObjectcEnCasilla().obtenerNombre());
                }                
            }//fin del for        
            
            agregarEstacionesAListadosHipotecas(null, null, nodoAuxiliarEstaciones, jugadorEnCuestion);
        }
    }
    
    /**
     * Método llamado antes de ser mostrado el diálogo, este se 
     * @param jugadorEnCuestion
     */
    public void formarListadoSubgrupos(int jugadorEnCuestion){
        if(!DesarrolloPartida.jugadores[jugadorEnCuestion].obtenerListadoLugares().estaVacia()){
            ListaEnlazada<ListaEnlazada<Casilla>> listaAuxiliarSubgrupos;//esto no era necesario xD
            listaAuxiliarSubgrupos=DesarrolloPartida.jugadores[jugadorEnCuestion].obtenerListadoLugares();
            listadoSubgrupos = new String[listaAuxiliarSubgrupos.darTamanio()];//solo para seguir un orden xD, porque antes estaba abajito del nodo de tipo LE<LE<C>>,pero como recibe el tamañode la lista, por eso xD
            
            Nodo<ListaEnlazada<Casilla>> nodoAuxiliarSubgrupos;
            nodoAuxiliarSubgrupos= listaAuxiliarSubgrupos.obtnerPrimerNodo();
        
            for(int nodoActual=0; nodoActual<listadoSubgrupos.length; nodoActual++){
                listadoSubgrupos[nodoActual]=nodoAuxiliarSubgrupos.obtenerObjectcEnCasilla().obtenerNombre();//pues la lista lleva el nombre del subgrupo correspondiente
            
                nodoAuxiliarSubgrupos=nodoAuxiliarSubgrupos.obtenerSiguiente();//no dará esto un nullPointer? No porque no se le llegará al for para que cuando se solicite el obj esta op apunte a la nada
            }
        }        
    }
    
    public void formarListadoDeLugaresDelSubgrupoSeleccionado( String item, int turnoActual){//este no necesita un if puesto que el botoncito se habi media vez halla al menos un grupo
     if(!DesarrolloPartida.jugadores[turnoActual].obtenerListadoLugares().estaVacia()){//este otro if es el que tampoco me parece, pues en un inicio no estaba aquí... y creo que el que esté aquí prte es por el hecho de que viene arrastrando un error  
     
            Nodo<ListaEnlazada<Casilla>> nodoAuxiliarLugares;
            nodoAuxiliarLugares= DesarrolloPartida.jugadores[turnoActual].obtenerListadoLugares().obtnerPrimerNodo();       
            System.out.println("itemGrupoSelected??? "+nodoAuxiliarLugares.obtenerObjectcEnCasilla().obtenerNombre());
         
            while(nodoAuxiliarLugares.obtenerSiguiente()!=null && !nodoAuxiliarLugares.obtenerObjectcEnCasilla().obtenerNombre().equalsIgnoreCase(item)){//mientras el nodo no tenga el nombre del item dado
                nodoAuxiliarLugares=nodoAuxiliarLugares.obtenerSiguiente();//de esta forma evito los errores implicados cuando lo busco por medio del índice    //pues así me ubico en el nodo correspondiente, donde se realizará la búsqueda
            }//de esta forma [es decir con el while, hago que el nodo grupal, tenga valor en correspondiente...aunque recuerda que este método no es el que posee el eroor en su ciclo
            
            nodoGrupoActual=nodoAuxiliarLugares;//esto será úlil para el momento en el cual se deseen ir a buscar las cnstrcc correspondientes, pue te ahorraría
            //la búsqueda del nodo que tieen a la lista de los luegares que deberá recorrer para encontrar la respectiva construcción
        
            ListaEnlazada<Casilla> listaAuxilair;
            listaAuxilair=nodoAuxiliarLugares.obtenerObjectcEnCasilla();//Aquí obtienes la Lista
         
            listadoLugares= new String [listaAuxilair.darTamanio()];
         
            Nodo<Casilla> nodoAuxiliarCasilla;
            nodoAuxiliarCasilla=listaAuxilair.obtnerPrimerNodo();
        
            for (int lugarActual = 0; lugarActual < nodoAuxiliarLugares.obtenerObjectcEnCasilla().darTamanio(); lugarActual++) {//recuerda que aquí estas obteneidndo el obj lista lugares, de tal manera que sí obtienes el tamaño correcto por el hecho de que estás posicionada en el nodo del grupo en cuestión
                listadoLugares[lugarActual]=nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre();//porque la casilla tiene el método para obtner el nombre de ella
            
                  Propiedad propiedadAuxiliar;
                propiedadAuxiliar=(Propiedad) nodoAuxiliarCasilla.obtenerObjectcEnCasilla();
            
                Lugar lugarAuxiliar;
                lugarAuxiliar = (Lugar) propiedadAuxiliar;//Deplano, porque al parecer no acepta una conversión hecha de una vez en el momento necesitado, es decir en el if
            
                if(lugarAuxiliar.estaCompletoELGrupo() && !propiedadAuxiliar.estaHIpotecada() && lugarAuxiliar.obtenerCantidadSimplesPoseidas()==0){             
                    MIsPoseciones.ListadoHipotecar.addItem(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre());//aqupi se hace la agregación,              
                }        
                if(propiedadAuxiliar.estaHIpotecada()){
                    //se manda a llamar al método que se encarga de add las casillas [o mejor dicho nombres] al listado de las que tienen oportunidad de ser sacadas de la hipoteca
                    MIsPoseciones.ListadoDeshipotecar.addItem(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre());
                }
            
                System.out.println("Nodo de tipo Casilla -> "+nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre());//es decir el que guarda a los lugares en cuestión
            
                nodoAuxiliarCasilla=nodoAuxiliarCasilla.obtenerSiguiente();            
            }//obtienes el listado de los lugares      
        
         }  
    }
    
   /* public void hallarListadoLugares(Nodo<ListaEnlazada<Casilla>> nodoDelGrupo){
        ListaEnlazada<Casilla> listaAuxilair;
         listaAuxilair=nodoDelGrupo.obtenerObjectcEnCasilla();//Aquí obtienes la Lista
         
        Nodo<Casilla> nodoAuxiliarCasilla;
        nodoAuxiliarCasilla=listaAuxilair.obtnerPrimerNodo();
        
        for (int lugarActual = 0; lugarActual < nodoDelGrupo.obtenerObjectcEnCasilla().darTamanio(); lugarActual++) {
            listadoLugares[lugarActual]=nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre();//porque la casilla tiene el método para obtner el nombre de ella
            
            nodoAuxiliarCasilla=nodoAuxiliarCasilla.obtenerSiguiente();            
        }//obtienes el listado de los lugares      
    }//a menos que repitas este mismo bloque, lo emplearás*/
    
    /*
        Este es mandado a llamar en el momento en el cual se selecciona un sitio en cuestión
        por ello, media vez no haya sitios que seleccionar, esto no se manda a exe y por ello
        no hay problema por el hecho de que sea null cuando no posee nada como etso aún,
        además para que esto suceda no tendría que tener grupos y por ello lugares en el listado físico...
        lo cual evita que un error como tal suceda
    */    
    public void hallarConstruccionCorrespondiente(String elLugar){//tampoco requiere un if, como el de grupos, puesto que media vez se llegó hasta lugares, entoces es un hecho que hay construcc
        ListaEnlazada<Casilla> listaAuxilair;
         listaAuxilair=nodoGrupoActual.obtenerObjectcEnCasilla();//Aquí obtienes la Lista
         
        Nodo<Casilla> nodoAuxiliarCasilla;
        nodoAuxiliarCasilla=listaAuxilair.obtnerPrimerNodo();
        
        //si acaso llegara a haber confusión con los lugares, entonces puede ser que pase por el -1
      
        while(nodoAuxiliarCasilla.obtenerSiguiente()!=null && !nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre().equalsIgnoreCase(elLugar)){//Creo que aquí habra un errorcito por el hehco de que se está revisando el actual y no el siguiente siendo el actual el que se va a pasar, por ello pienso que se debería evaluar el sig,para saber si cble o no lo qu eposee el nod en esa parte
            nodoAuxiliarCasilla=nodoAuxiliarCasilla.obtenerSiguiente();            
        }
        
        nodoLugarActual=nodoAuxiliarCasilla;//media vez tienes este nodo, tienes acceso directo a los datos que se necesitan para las transacciones [es decir compra/venta(deplano que tb para venta de tjt's)]
        
        Lugar casillaLugar;
        casillaLugar=(Lugar)nodoAuxiliarCasilla.obtenerObjectcEnCasilla();
        
        Propiedad casillaPropiedad;
        casillaPropiedad =(Propiedad)nodoAuxiliarCasilla.obtenerObjectcEnCasilla();
        
        //justamente aquí iba a proceder a add el bloque de condis ini para las compras, pero no será edicaz puesto que ese está pensado para la siutación en la que los btn no se encuentran inhanilitados, de tal manera que al selec y deselect, estos sena quines les app los cb, esto quiere decir que el método está pensado para los cb surgidos por el hecho de estar select o deselect
        //mas no habi o inhabi
        
        estaCompletoElGrupo=casillaLugar.estaElGrupoCOmpleto();//por esta acción, no es necesario reestablecer la variable en cuestión
        
        if(!casillaPropiedad.estaHIpotecada()){
            if(casillaLugar.obtenerCantidadSoisticadasPoseidas()==0){//quiere decir que no tiene y por lo tanto no ha alcanzado el lím, pues media vez lo haga se llenará la var corresp a las sofis xD
                construccionEnLugar = casillaLugar.obtenerCantidadSimplesPoseidas()+ " "+"Sencillas";
            }else{
                construccionEnLugar = casillaLugar.obtenerCantidadSoisticadasPoseidas()+ " "+"Sofisticadas";
            }        
        }else{
            construccionEnLugar="No hay debido a hipoteca";
        }
        
    }
    
    /**
     * Método llamado en la clase misPoseciones, donde se mandará a exe cuando el Rbtn correcpondiente
     * esté activado, este método se encarga de habi, deshabi y establecer los datos de los spinner según sea
     * la cada y tipo de ctrccs que posea el jugador
     */
    public void establecerCondicionesInicialesDeCompra(){//estas son las condiciones iniciales por cada compra, no las condis que provocan los comportamientos según las selecciones
        Casilla casillaDeTransaccion = nodoLugarActual.obtenerObjectcEnCasilla();//aquí lanzará un null pointer cuando el jugador en cuestión no posea nada, si es que lo llamas cuando no sabes si posee algo o no...
        
        Lugar casillaLugar;
        casillaLugar =(Lugar)casillaDeTransaccion;
        
        MIsPoseciones.spinnerTransacciones[1].setModel(new javax.swing.SpinnerNumberModel(0, 0, SolicitudDatos.registroDatos.obtenerLimiteSofisticadas()-casillaLugar.obtenerCantidadSoisticadasPoseidas(), 1));//Debe estar aquí y no en el if como para el caso de las simples, por el hecho de que puede que llegue al lím de una sola vez, entonce al querer adquirir las sofis no se le impondrá el lím pues no se encuentra en el lugar adecuado
        
        //Estos if dan las hailitaciones y límites iniciales, por cada compra [es decir selección y deselección del ckBxCompra
        if(casillaLugar.obtenerCantidadSimplesPoseidas()<SolicitudDatos.registroDatos.obtenerLimiteSimples()){//entonces se procede a habilitar el spnSimples y a delimitar su max con una fórmula que sea para cualquier sitaución
            MIsPoseciones.spinnerTransacciones[0].setModel(new javax.swing.SpinnerNumberModel(0, 0, SolicitudDatos.registroDatos.obtenerLimiteSimples()-casillaLugar.obtenerCantidadSimplesPoseidas(), 1));//pues de esta manera solo se le permitirá escoger entre
            //el rango de posibilidades que tiene, claro mientras no haya alcanzado el lím
            MIsPoseciones.spinnerTransacciones[0].setEnabled(true);
        }else{//quiere decir que ya agotó los lims, en la compra pasada
            if(casillaLugar.obtenerCantidadSoisticadasPoseidas()<SolicitudDatos.registroDatos.obtenerLimiteSofisticadas()){//creo que esto es lo mismo que un else if xD                
                MIsPoseciones.spinnerTransacciones[1].setEnabled(true);
            }        
        }
        if((Integer)MIsPoseciones.spinnerTransacciones[0].getValue()==0 && (Integer) MIsPoseciones.spinnerTransacciones[1].getValue()==0){
             MIsPoseciones.spinnerTransacciones[0].setEnabled(false);
            MIsPoseciones.spinnerTransacciones[1].setEnabled(false);
        }//vermos si es funcional, si lo es entonces entonces ya no tendrá que revisarse en el btn de transacciones el hecho que ambos sean 0 para no hacer nada
        
    }  
    
    public void establecerCOndicionesInicialesDeVenta(int turnoActual){//RECUERDA que estas condiciones son app cada vez que se seleccione o deseleccione el btn correspondiente
        Casilla casillaDeTransaccion = nodoLugarActual.obtenerObjectcEnCasilla();
        
        Lugar casillaLugar;
        casillaLugar =(Lugar)casillaDeTransaccion;
        if(casillaLugar.obtenerCantidadSimplesPoseidas()>0 && casillaLugar.obtenerCantidadSoisticadasPoseidas()==0){//esto para establecer la venta pero de simples
            MIsPoseciones.spinnerTransacciones[2].setModel(new javax.swing.SpinnerNumberModel(0, 0, casillaLugar.obtenerCantidadSimplesPoseidas(), 1));
            MIsPoseciones.spinnerTransacciones[2].setEnabled(true);
        }else if(casillaLugar.obtenerCantidadSoisticadasPoseidas()!=0){
            MIsPoseciones.spinnerTransacciones[2].setModel(new javax.swing.SpinnerNumberModel(0, 0, casillaLugar.obtenerCantidadSoisticadasPoseidas(), 1));
            MIsPoseciones.spinnerTransacciones[2].setEnabled(true);
        }else{//Esto es solo para las costrucciones, para tjt's es similar, solo que deberá acceder al tamño de la respectiva lista para saber el lím... esto lo hará luego de hacer la transaferencia... bueno puedes hacerlo ahorita xD
            MIsPoseciones.spinnerTransacciones[2].setEnabled(false);
        }//por si acaso no tiene nada de nada xD
        
        if(DesarrolloPartida.jugadores[turnoActual].obtenerListadoTarjetasSC().darTamanio()>0){//pues eso queire decir que tiene Duh! xD
            MIsPoseciones.spinnerTransacciones[2].setEnabled(true);
            MIsPoseciones.spinnerTransacciones[2].setModel(new javax.swing.SpinnerNumberModel(0, 0,DesarrolloPartida.jugadores[turnoActual].obtenerListadoTarjetasSC().darTamanio(), 1));            
        }else{//puesto que se llamará de forma directa en el diálogo entonces, se tendrá accceso al turno actual para referirse sorrectamente al jugador
            MIsPoseciones.spinnerTransacciones[2].setEnabled(false);
        }//con estas deshabilitaciones del else se puede decir que si ambos no son false entonces que se tramite la transacción claro, si es que está seleccionado el btn
        //de igual forma habría que revisar que ambos spinner [ooo asi que era revisar si estaban deshabi o habi xD] esten habi para saber si se tomará en cta la selección del btn para efectuar la transaccioón
        //por motivos de demostración [xD] se hará de la forma en la que se revisa de una vez allá en el bloque de las transacciones, para demostrar que funciona igual, sea que aquí se revise o allá, bueno aque el deshabi y habi 
        //no quiere decir que no se vaya a tomar en cta el dato en ellos en la transacción, así que de todos modos si hay que explicitarlo en el blique de la transacción
    }
    
    /**
     * Este es el método que se encarga de las rxn de los sppiner de compra
     * según las escogencias del jugador, en esta sitaución se darán cuando se
     * llegue al límite por parte del spn simples, lo cual permitirá la habilitación 
     * del spn de las sofisticadas, siempre y cuando desde el principio esté habi
     */
    public void establecerReacciónSegunAdquisicionDeSimples(){//Asumo yo que la dshabi y habi se encargará de que este método se exe o no, sino  es así pues entonces solo métele la condi de que esté habi [que sería la sitaución corecta en la que esto se debe exe, para que pueda leerse todo este cuerpaso xD
        Casilla casillaDeTransaccion = nodoLugarActual.obtenerObjectcEnCasilla();
        
        Lugar casillaLugar;
        casillaLugar =(Lugar)casillaDeTransaccion;
        
        if((Integer)MIsPoseciones.spinnerTransacciones[0].getValue()==SolicitudDatos.registroDatos.obtenerLimiteSimples()-casillaLugar.obtenerCantidadSimplesPoseidas()){//quiere decir que llegó al límite porqe ese cada quiere "comrar" [pues puede estar probando xD ó... xD]
            MIsPoseciones.spinnerTransacciones[1].setEnabled(true);
        }else{//pues eso es lo que le abre la puerta a la adquisición de las otras construcciones
            MIsPoseciones.spinnerTransacciones[1].setEnabled(false);
        }//por el hecho de que los estados son cbt's entonces lo debo explicitar, aunque sea razonable el hecho
        
    }
    
    public void establecerReaccionSegunAdquisicionDeSofisticadas(){//no me gusta el verbo xD... podría llamarse, establecer rxn según adquisisción de sofisticadas [pues afecta a las simples] y vice para el otro--- antes se llamaba [EstablecerPermisoParaAdquisicionDeSimples xD y vice para el otro
        Casilla casillaDeTransaccion = nodoLugarActual.obtenerObjectcEnCasilla();
        
        Lugar casillaLugar;
        casillaLugar =(Lugar)casillaDeTransaccion;
        
        if((Integer)MIsPoseciones.spinnerTransacciones[1].getValue()>0){
            MIsPoseciones.spinnerTransacciones[0].setEnabled(false);//pues para adquirir sofisticadas Si o Si debe tener el lím de simples, sino NO
        }else{
            MIsPoseciones.spinnerTransacciones[0].setEnabled(true);//ya que no ha escogido ninguna sofisticada y por ello tiene que estar habi, sino no podrá cb de ahí en ad el valor por si acaso llegara a arrepentirse
        }
    }
    
    public void establecerDetalleTransacciones(){//aquí convergen todos los método que impliquen transacciones: compras, ventas, transferencias, hipotecas...(eso último aún no lo sé)
        
        
    }//o mejor crea el switch allá, puesto que allá en el diálog tienes acceso directo a los estados de los btn
    
    public void establecerDetalleCompra(int cliente){
          Casilla casillaDeTransaccion = nodoLugarActual.obtenerObjectcEnCasilla();
        
        Lugar casillaLugar;
        casillaLugar =(Lugar)casillaDeTransaccion;
        
        //Se hacen los cálculos
        int pagoTotal;        
        pagoTotal=((Integer)MIsPoseciones.spinnerTransacciones[0].getValue()*casillaLugar.obtenerPrecioPropiedadSImple())+((Integer)MIsPoseciones.spinnerTransacciones[1].getValue()*casillaLugar.obtnerPrecioPropiedadSofisticada());
        //cha chan y aquí está el cobro total por venta xD, ahora solo hace falta el dialoguito uwu y el crear en él, el método para devolver la decisión y así proceder a cbiar los datos concernientes a las transacciones
        
        if(pagoTotal<= DesarrolloPartida.jugadores[cliente].obtenerDinero()){
            //Se manda a llamar al diálogo facturita uwu
            dialogoFacturita.limpiarParaNuevosDatos();
            dialogoFacturita.establecerDatosFactura((Integer)MIsPoseciones.spinnerTransacciones[0].getValue(), (Integer)MIsPoseciones.spinnerTransacciones[1].getValue(), 
            casillaLugar.obtenerPrecioPropiedadSImple(), casillaLugar.obtnerPrecioPropiedadSofisticada(), pagoTotal);
            dialogoFacturita.setVisible(true);
        
            //se manda a llamar al méodo del diálogo para usar o no el método que establece la acción del jugador
            if(dialogoFacturita.retornarDecision()){
                 DesarrolloPartida.jugadores[cliente].pagar(pagoTotal);//CHA CHAN XD ya está el pago
                casillaLugar.establecerCantidadSimples((Integer)MIsPoseciones.spinnerTransacciones[0].getValue());//como se van a limpiar los datos, no habrá problema, puesto que al adquirri todas, se le estará enviando cada vez 0...
                casillaLugar.establecerCantidadSofisticadas((Integer)MIsPoseciones.spinnerTransacciones[1].getValue());
                //manipularListadoHipotecaPorCompra(casillaLugar.obtenerNombre());//Aquí se hace la respectiva elimición del lugar actualmente seleccionado, puesto que ese fue quien recibió la compra y no los otros de la lista, de tal manera que deberá eliminarlo del listado, media vez se seleccione compra y add cuando se deseleccione
            }
        }else{
            JOptionPane.showMessageDialog(null, "No tienes el dinero suficiente para realizar la compra\nte hacen falta $ "+(pagoTotal-DesarrolloPartida.jugadores[cliente].obtenerDinero()),
                    "Incapacidad de pago", JOptionPane.WARNING_MESSAGE);
        }        
    }
    
    public void establecerDetalleVenta(int cliente){
        //Aqupi se implementa el código similar para venta, solo que con su propio diálogo o JOP, según sea mejor, esot para cada situación acontecida
        
        //Aquí luego de mostrar el diálogo de cerciorarse de si aceptó [esto si es un diálogo, sino entonces se muestra despuecito del JOP, en cuestión]
        //se verifica si la cantidad de simples del jugador! es 0, si es así, entonces se procede a hacer la agregación
          Casilla casillaDeTransaccion = nodoLugarActual.obtenerObjectcEnCasilla();
        
        Lugar casillaLugar;
        casillaLugar =(Lugar)casillaDeTransaccion;
        
        if(MIsPoseciones.spinnerTransacciones[2].isEnabled()){//obviamente si llegó hasta aquí es porque la opción está habilitada y por ello solo es de averiguar par quien está habilitada
            if(casillaLugar.obtenerCantidadSimplesPoseidas()>0 && casillaLugar.obtenerCantidadSoisticadasPoseidas()==0){
                DesarrolloPartida.jugadores[cliente].recibirDInero((Integer)MIsPoseciones.spinnerTransacciones[2].getValue()*casillaLugar.obtenerPrecioPropiedadSImple());
                JOptionPane.showMessageDialog(null, "Has recibido $ "+ (Integer)MIsPoseciones.spinnerTransacciones[2].getValue()*casillaLugar.obtenerPrecioPropiedadSImple()+ "por venta realizada", "", JOptionPane.INFORMATION_MESSAGE);
            }
            if(casillaLugar.obtenerCantidadSoisticadasPoseidas()>0){
                DesarrolloPartida.jugadores[cliente].recibirDInero((Integer)MIsPoseciones.spinnerTransacciones[2].getValue()*casillaLugar.obtnerPrecioPropiedadSofisticada());
                JOptionPane.showMessageDialog(null, "Has recibido $ "+ (Integer)MIsPoseciones.spinnerTransacciones[2].getValue()*casillaLugar.obtnerPrecioPropiedadSofisticada()+ "por venta realizada", "", JOptionPane.INFORMATION_MESSAGE);
            }        
        }        
        if(!DesarrolloPartida.jugadores[cliente].obtenerListadoTarjetasSC().estaVacia() && MIsPoseciones.spinnerTransacciones[3].isEnabled()){
            DesarrolloPartida.jugadores[cliente].recibirDInero((Integer)MIsPoseciones.spinnerTransacciones[3].getValue()*350);
            JOptionPane.showMessageDialog(null, "Has recibido $ "+ (Integer)MIsPoseciones.spinnerTransacciones[3].getValue()*350+ "por venta de tarjeta", "", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        //DesarrolloPartida.jugadores[MIsPoseciones.listadoJugadoresPosibles.getSelectedIndex()].incrementarRiquezas(turnoActual);//esto será para cuando venda a una tjt que por el momento no sé que valor tendrá
        //Aquí todo el proceso de venta... recuerda que este spn se activará Ssi tiene construcciones, eso se hará en las condicones inicales, de forma similar a las de compra
        
        //Aquí se hace el decremento de la cadd, enviadno el valor negativo
      /*  if(casillaLugar.obtenerCantidadSimplesPoseidas()==0){
            MIsPoseciones.ListadoHipotecar.addItem(casillaLugar.obtenerNombre());//aquí se add, por ello no se requiere de mayor proceso, puesto que no se encontraba en ninguno solo de los listados, debido a las condiciones dadas
        }*/// se eliminó por el hecho de que ahora se creo un solo método el cual se encarga de hacer las actualizaciones cada vez que hay una transacción, lo ucal es correcto porque solo en esos momentos los estados de las propiedades pueden cambiar
    }
    //estos dos de rxn que tiene que ver con jipotecas, ya no serán necesarios, pues se contemĺa esto en el único métoodo reclaiconado con esto luego de haber aceptado una transacción
    /*public void establecerRxnAlHipotecar(){//esto será cuando se seleccione el cbBx correspondiente de la hipoteca
        MIsPoseciones.ListadoHipotecar.removeItem((String)MIsPoseciones.ListadoHipotecar.getSelectedItem());//si quedan espacios en blanco, deplano tocará manipular el arr o listado de este cbBx, para que no se mire mal
        MIsPoseciones.ListadoDeshipotecar.addItem((String)MIsPoseciones.ListadoHipotecar.getSelectedItem());        
    }
    
    public void establecerRxnAlDeshipotecar(){//Este dato será recibido del elemento seleccionado del listado, lo cual me hace pensar entonces que no debería recibirlo sino explicitarlo
        MIsPoseciones.ListadoHipotecar.addItem((String)MIsPoseciones.ListadoHipotecar.getSelectedItem());//será así puesto que ese valor no cb luego de haber aceptado la transacción, poe ello puede ser de forma directa
        MIsPoseciones.ListadoDeshipotecar.removeItem((String)MIsPoseciones.ListadoHipotecar.getSelectedItem());
    }*///mpetodo llamado luego de haber aceptado tgransacción y haber sido seleccionado este ckBx... estos van a cb ues ahora se trabajará dierctamente con el btn de hipotecar...
    
    public void establecerListadoHIpotecas(int numeroJugador){//será llamado cuando se acepte una transacción, esto funciona para lugares, servicios y estaciones, recuerda que luego de haber aceptado la transacción, todas las opciones de cualquiera de ellas, se desacivan [inhabilitan/deseleccionan...]
        String lugarHipotecado=null;
        String lugarDeshipotecado=null;//xD
        
        
        if(MIsPoseciones.ListadoHipotecar.isEnabled()){//Estos if son tanto para lugares como para servicios y estaciones
            lugarHipotecado=(String) MIsPoseciones.ListadoHipotecar.getSelectedItem();
        }
        if(MIsPoseciones.ListadoDeshipotecar.isEnabled()){
            lugarDeshipotecado=(String) MIsPoseciones.ListadoDeshipotecar.getSelectedItem();//aunque si no está seleccionado tb habrá error,puesto que debolverá -1 lo cual no se encuentra en el listado, pero si se trabaja con un while, se estaría obteneiendo el primero elemento por lo cual hipotecaría o deship ese auqnue no lo haya querido así xD
        }//en estos if me encargo de recoger a los lugares que fueron hipotecados o dehip, si es que seleccionaron la opción        
        
        
        if(nodoGrupoActual!=null){
            ListaEnlazada<Casilla> listaAuxilairLugar;//Esto es lo de lugares
            listaAuxilairLugar=nodoGrupoActual.obtenerObjectcEnCasilla();//Aquí obtienes la Lista    NO tendría que haber error al ser este null, porque no se le permitiría interactuar con ninguna transacción... ufff bueno si se permitiría si tiene servicios/estaciones... entonces is habrá que poner un !=null porque
            //con cualquiera de estos últimos 2 tendrá acceso a hipotecar y con ello se exe todo esto aunque no xistan aun lugares
            Nodo<Casilla> nodoAuxiliarCasilla;
            nodoAuxiliarCasilla=listaAuxilairLugar.obtnerPrimerNodo();
            
            agregarLugaresAListadosDeHipotecas(lugarHipotecado, lugarDeshipotecado, listaAuxilairLugar, nodoAuxiliarCasilla, numeroJugador);
        }
        
        if(listaServicios!=null){
            Nodo<Casilla> nodoAuxiliarServicio;
            nodoAuxiliarServicio=listaServicios.obtnerPrimerNodo();
            agregarServiciosAListadosHipotecas(lugarHipotecado, lugarDeshipotecado,nodoAuxiliarServicio, numeroJugador);
        }
        
        if(listaEstaciones!=null){
            Nodo<Casilla> nodoAuxiliarEstacion;
            nodoAuxiliarEstacion=listaEstaciones.obtnerPrimerNodo();//recuerdate que la excepción que aquí se marcaba era por el hecho de que no se tomaba en cta en el for el hecho de que se estaba yendo un nodo más alla del que se requería
            
            agregarEstacionesAListadosHipotecas(lugarHipotecado, lugarDeshipotecado, nodoAuxiliarEstacion, numeroJugador);
        }   
    }//este método lo que hace es crear nuevamente el listado, según las situaciones que se encuentre al estar revisando
    
    public void agregarLugaresAListadosDeHipotecas(String lugarHipotecado, String lugarDeshipotecado,ListaEnlazada<Casilla> listaAuxilair,Nodo<Casilla> nodoAuxiliarCasilla, int numeroJugador){//tb puede ser llamado al crear el listado incicial de los lugares
    //if(nodoAuxiliarCasilla!=null){//este if si es neceaio puesto que se trbajaa por bloque y por lo tanto en otra parte no se anda corroborando el hecho de que no pueda tener nada, entonces por ello en esta parte deberá asegurarse de esto, porque las otras solametnte se encargan de enviale los datosm sea o no nulo, bueno auqnue tb lo que podría hacerse es el hecho de que se diga que no se llegue a la parte de la llamada al método si es nodo es null... pero daría casi lo mismo, en cb al hacerlo aquí el if está de cierta manera en su lugar correspondiente a diferencia de que estuviera en la parte más externa de la llamada
            for (int propiedad = 0; propiedad < listaAuxilair.darTamanio(); propiedad++) {//este es para lugares
                aplicarCambioALugarPorHipoteca(lugarHipotecado, lugarDeshipotecado, nodoAuxiliarCasilla, numeroJugador);
            
                Propiedad propiedadAuxiliar;
                propiedadAuxiliar=(Propiedad) nodoAuxiliarCasilla.obtenerObjectcEnCasilla();
            
                Lugar lugarAuxiliar;
                lugarAuxiliar = (Lugar) propiedadAuxiliar;
            
                if(lugarAuxiliar.estaCompletoELGrupo() && !propiedadAuxiliar.estaHIpotecada() && lugarAuxiliar.obtenerCantidadSimplesPoseidas()==0){
                    MIsPoseciones.ListadoHipotecar.addItem(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre());
                   MIsPoseciones.ListadoDeshipotecar.removeItem(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre());
                }
            
                if(propiedadAuxiliar.estaHIpotecada()){
                    MIsPoseciones.ListadoDeshipotecar.addItem(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre());
                    MIsPoseciones.ListadoHipotecar.removeItem(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre());
                }
            
                nodoAuxiliarCasilla=nodoAuxiliarCasilla.obtenerSiguiente();            
                //A este no le aplico cb por el hecho de que trabaja un poco diferente, lo cual se da a notar en que no requere de un auxiliar d etipo propiedad para trabajar como en el caso de servicios y estaciones
            }//para limpiar los datos, en el caso de caulquiera de los listados referentes a las hipotecas, bastará con limpiar a los cbBx directamente puesto que no reciben un obj predefinido, sino que reciben sus datos en el momento, eso sí antes de mostrar el listado
    
      //  }//fin del if que se encarga de que no hayan null pointers al momento de intentar formar cualquiera de los 2 listado referentes a las hipotecas
       
    }
      
    public void aplicarCambioALugarPorHipoteca(String lugarHipotecado, String lugarDeshipotecado, Nodo<Casilla> nodoAuxiliarCasilla, int numeroJugador){
            if(lugarHipotecado!=null && lugarHipotecado.equalsIgnoreCase(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre())){//pues aquí obtengo el nombre de la casilla en cuestión
                Propiedad lugarCambiado;
                lugarCambiado=(Propiedad) nodoAuxiliarCasilla.obtenerObjectcEnCasilla();    
                JOptionPane.showMessageDialog(null, "Has incrementado tu saldo a $ " + lugarCambiado.obtenerPrecioPropiedad(), lugarHipotecado, numeroJugador);
                DesarrolloPartida.jugadores[numeroJugador].recibirDInero(lugarCambiado.obtenerPrecioPropiedad());//aquí se le hace el debido aumento xD, recuerda que la actualización de la tabla de hace al finalizar este proceso que sería el último del btn acpetar transacción
                lugarCambiado.cambiarEstadoHIpoteca();
                Lugar propiedad;
                propiedad =(Lugar)lugarCambiado;
                propiedad.establecerFormatoHipotecada();
            }
            else if(lugarDeshipotecado!=null && lugarDeshipotecado.equalsIgnoreCase(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre())){//se obttiene el nombre de la casilla
                Propiedad lugarCambiado;
                lugarCambiado=(Propiedad) nodoAuxiliarCasilla.obtenerObjectcEnCasilla();   
                
                if(DesarrolloPartida.jugadores[numeroJugador].obtenerDinero()>= (lugarCambiado.obtenerPrecioPropiedad()+(lugarCambiado.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100)))){//pues tiene un interés xD, pobrecito xD [es un juego por eso molesto xD]
                   JOptionPane.showMessageDialog(null, "Se te descontarán $ "+ (lugarCambiado.obtenerPrecioPropiedad()+(lugarCambiado.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100)))
                        + "para pagar la hipoteca", "", JOptionPane.INFORMATION_MESSAGE);//así porque ya sé que si puede pagar
                   DesarrolloPartida.jugadores[numeroJugador].pagar(lugarCambiado.obtenerPrecioPropiedad()+(lugarCambiado.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100)));
                   DesarrolloPartida.jugadores[numeroJugador].decrementarRIquezas(lugarCambiado.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100));
                    lugarCambiado.cambiarEstadoHIpoteca();
                    Lugar propiedad;
                    propiedad =(Lugar)lugarCambiado;
                    propiedad.actualizarFormaFisica();
                }else{
                    JOptionPane.showMessageDialog(null, "Tu dinero no es suficiente para eliminar la hipoteca... \n te hacen falta $"+(((lugarCambiado.obtenerPrecioPropiedad()+(lugarCambiado.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100))))-DesarrolloPartida.jugadores[numeroJugador].obtenerDinero())
                            , "Deplano se quedará así un poco mas...", JOptionPane.ERROR_MESSAGE);//esto para que sepa cuanto le hace falta xD
                }
                
                
            }            
    }
    
    public void agregarServiciosAListadosHipotecas(String lugarHipotecado, String lugarDeshipotecado, Nodo<Casilla> nodoAuxiliarServicios, int  numeroJugador){        
       if(nodoAuxiliarServicios!=null){//bueno... creo que al final de ctas este if si será util,por el hecho de que pueden existir lugares mas no servicios por lo cual cuando intente formar el listado, intente trabajar con algo nulo y pum nullPointerException xD
           Propiedad auxiliarPropiedad;        
            
            for (int servicio = 0; servicio < listaServicios.darTamanio(); servicio++) {//si está vacía no dará problemas puesto que el tamaño sera 0 y además no podrá seleccionar un servicio ya que al inicio por estar vacía no se porcedió a add
               auxiliarPropiedad =(Propiedad) nodoAuxiliarServicios.obtenerObjectcEnCasilla(); 
               aplicarCambioAServicioPorHipoteca(lugarHipotecado, lugarDeshipotecado, auxiliarPropiedad, numeroJugador);//debido al salto que hace habrá que convertir el nodo pues este será el que ahora se le envíe... pero despupes de haber compilado las mejoras hechas hace unos momentos
                     if(auxiliarPropiedad.estaHIpotecada()){
                        MIsPoseciones.ListadoDeshipotecar.addItem(nodoAuxiliarServicios.obtenerObjectcEnCasilla().obtenerNombre());//debería funcionar tb con el auxiliar
                        MIsPoseciones.ListadoHipotecar.removeItem(nodoAuxiliarServicios.obtenerObjectcEnCasilla().obtenerNombre());
                    }else{
                        MIsPoseciones.ListadoHipotecar.addItem(nodoAuxiliarServicios.obtenerObjectcEnCasilla().obtenerNombre());//debería funcionar tb con el auxiliar
                        MIsPoseciones.ListadoDeshipotecar.removeItem(nodoAuxiliarServicios.obtenerObjectcEnCasilla().obtenerNombre());
                    }                
               nodoAuxiliarServicios=nodoAuxiliarServicios.obtenerSiguiente();//no entinedo por qué se lo salta la primera vez...
                     //auxiliarPropiedad =(Propiedad) nodoAuxiliarServicios.obtenerObjectcEnCasilla();//no  necesita un if para revisar cuando sea null porque va en sintonía con la cdad de nodos que debe ir revisando               
            }            
       }
            
    }
    
    public void aplicarCambioAServicioPorHipoteca(String lugarHipotecado, String lugarDeshipotecado, Propiedad servicio, int numeroJugador){//aquí hay un problema y es el hecho de que el servicio nunca cb de valor, es decir que es estático, por lo cual será funcioanla para cuando exista un solo elemento en el cbBx pero no cuando hayan más, por lo cual lo que se deberá hacer es que este auxiliarPropiedad adquiera un nuvo valor de la misma manera en que lo hace el nodo correcpondiente de donde salió este obj
       if(lugarHipotecado!=null && lugarHipotecado.equalsIgnoreCase(servicio.obtenerNombre())){//pues aquí obtengo el nombre de la casilla en cuestión                    
                JOptionPane.showMessageDialog(null, "Se ta han acreditado & "+servicio.obtenerPrecioPropiedad()+"por razón de hipoteca", "", JOptionPane.INFORMATION_MESSAGE);
                DesarrolloPartida.jugadores[numeroJugador].recibirDInero(servicio.obtenerPrecioPropiedad());
                servicio.cambiarEstadoHIpoteca();
                Servicio propiedad;
                propiedad=(Servicio)servicio;
                propiedad.establecerFormatoHipotecada();
                
                
        }else if(lugarDeshipotecado!=null && lugarDeshipotecado.equalsIgnoreCase(servicio.obtenerNombre())){                   
            if(DesarrolloPartida.jugadores[numeroJugador].obtenerDinero()>= (servicio.obtenerPrecioPropiedad()+(servicio.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100)))){
                JOptionPane.showMessageDialog(null, "Se te descontarán $ "+ (servicio.obtenerPrecioPropiedad()+(servicio.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100)))
                         +  "para pagar la hipoteca", " ", JOptionPane.INFORMATION_MESSAGE);
               DesarrolloPartida.jugadores[numeroJugador].pagar((servicio.obtenerPrecioPropiedad()+(servicio.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100))));
               DesarrolloPartida.jugadores[numeroJugador].decrementarRIquezas(servicio.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100));
                servicio.cambiarEstadoHIpoteca();
                Servicio propiedad;
                propiedad=(Servicio)servicio;
                propiedad.actualizarFormaFisica();
            }else{
                JOptionPane.showMessageDialog(null, "Te hacen falta $"+(((servicio.obtenerPrecioPropiedad()+(servicio.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100))))-DesarrolloPartida.jugadores[numeroJugador].obtenerDinero())
                            , "Cuando tengas el dinero vuelve", JOptionPane.ERROR_MESSAGE);
            }
            
                
        }           
    }
    
      public void agregarEstacionesAListadosHipotecas(String lugarHipotecado, String lugarDeshipotecado, Nodo<Casilla> nodoAuxiliarEstacion, int numeroJugador){        
       if(nodoAuxiliarEstacion!=null){
           Propiedad auxiliarPropiedad;            
  
            
            for (int estacion = 0; estacion < listaEstaciones.darTamanio(); estacion++) {//si está vacía no dará problemas puesto que el tamaño sera 0 y además no podrá seleccionar un servicio ya que al inicio por estar vacía no se porcedió a add
               auxiliarPropiedad =(Propiedad) nodoAuxiliarEstacion.obtenerObjectcEnCasilla();
               aplicarCambioAEstacionPorHipoteca(lugarHipotecado, lugarDeshipotecado, auxiliarPropiedad, numeroJugador);        
                
                     if(auxiliarPropiedad.estaHIpotecada()){//iba a agregar el hecho de que al hipotecar pero como no tienen una agrupación a parte de la general, entonces ya no xD, sino solo era de poner en el if && el listado de servicios/estaciones . dar tamanio = tamaño del listado de servicios/estaciones de registro... pues esto querría decir que ya completo'todas
                            MIsPoseciones.ListadoDeshipotecar.addItem(nodoAuxiliarEstacion.obtenerObjectcEnCasilla().obtenerNombre());//debería funcionar tb con el auxiliar
                            MIsPoseciones.ListadoHipotecar.removeItem(nodoAuxiliarEstacion.obtenerObjectcEnCasilla().obtenerNombre());
                     }else{
                            MIsPoseciones.ListadoHipotecar.addItem(nodoAuxiliarEstacion.obtenerObjectcEnCasilla().obtenerNombre());//debería funcionar tb con el auxiliar
                            MIsPoseciones.ListadoDeshipotecar.removeItem(nodoAuxiliarEstacion.obtenerObjectcEnCasilla().obtenerNombre());
                     }
                
                    nodoAuxiliarEstacion=nodoAuxiliarEstacion.obtenerSiguiente();
//                    auxiliarPropiedad =(Propiedad) nodoAuxiliarEstacion.obtenerObjectcEnCasilla();//Esto por el hecho de que no simpre habrá un solo nodo, así que hay que revisar los demás               
            }            
       }
          
          
    }
    
    public void aplicarCambioAEstacionPorHipoteca(String lugarHipotecado, String lugarDeshipotecado, Propiedad estacion, int numeroJugador){
       if(lugarHipotecado!=null && lugarHipotecado.equalsIgnoreCase(estacion.obtenerNombre())){//pues aquí obtengo el nombre de la casilla en cuestión                    
                JOptionPane.showMessageDialog(null, "Has recibido $ "+ estacion.obtenerPrecioPropiedad()+ " por la hipotecar","", JOptionPane.INFORMATION_MESSAGE);
                DesarrolloPartida.jugadores[numeroJugador].recibirDInero(estacion.obtenerPrecioPropiedad());
                estacion.cambiarEstadoHIpoteca();
                Estacion propiedad;
                propiedad=(Estacion)estacion;
                propiedad.establecerFormatoHipotecada();
                
            }
            else if(lugarDeshipotecado!=null && lugarDeshipotecado.equalsIgnoreCase(estacion.obtenerNombre())){                   
               if(DesarrolloPartida.jugadores[numeroJugador].obtenerDinero()>= (estacion.obtenerPrecioPropiedad()+(estacion.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100)))){
                 JOptionPane.showMessageDialog(null, "Se te descontarán $ "+ (estacion.obtenerPrecioPropiedad()+(estacion.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100)))+
                "para pagar la hipoteca", "", JOptionPane.INFORMATION_MESSAGE);
                   DesarrolloPartida.jugadores[numeroJugador].pagar((estacion.obtenerPrecioPropiedad()+(estacion.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100))));                
                   DesarrolloPartida.jugadores[numeroJugador].decrementarRIquezas(estacion.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100));
                    estacion.cambiarEstadoHIpoteca();
                    Estacion propiedad;
                    propiedad=(Estacion)estacion;
                    propiedad.actualizarFormaFisica();//aquí va llamada al métod que se encarga de colocarle nuevamente el dueño
                    
                }else{
                JOptionPane.showMessageDialog(null, "Tu tren se te va pues te faltan $"+(((estacion.obtenerPrecioPropiedad()+(estacion.obtenerPrecioPropiedad()*(SolicitudDatos.registroDatos.obtnerInteres()/100))))-DesarrolloPartida.jugadores[numeroJugador].obtenerDinero()) +
                        " para pagar la hipoteca", "", JOptionPane.ERROR_MESSAGE);
                }               
            }           
    }
    
    
    /*
    * Método llamado en las partes donde se requiere la actualización de los datos debido a la acción del jugador
       siendo los métodos que lo absorben los pertenecientes a cada una de las transacciones
     */
    /*public void estblecerPropiedadesConPermisoHipotecar(Nodo<Casilla> nodoAuxiliarCasilla){//como no se van a almacenar los datos en un listado aparte puesto que esto es un atributo de las propiedades en sí, que forman parte del listado, entonces se
        //deberpa manejar de forma directa a dichos listados para ir asignando los datos en el momento; para la add será sencillo puesto que es un cb box entonces solo habrá que add el objt, para el caso de la sacada, lo que habrá
        //que hacer es al igual como se haría al tenerlos almacenados en una lista, buscarlo hasta encontrar al nombre correcto para sacarlo de ahpi y pasarlo al otro en cuestión o eliminarlo del listado de forma correcta
          Propiedad propiedadAuxiliar;
            propiedadAuxiliar=(Propiedad) nodoAuxiliarCasilla.obtenerObjectcEnCasilla();
            
            Lugar lugarAuxiliar;
            lugarAuxiliar = (Lugar) propiedadAuxiliar;//Deplano, porque al parecer no acepta una conversión hecha de una vez en el momento necesitado, es decir en el if
            
            if(lugarAuxiliar.estaCompletoELGrupo() && !propiedadAuxiliar.estaHIpotecada() && lugarAuxiliar.obtenerCantidadSimplesPoseidas()==0){//pues debe ser así para que pueda procederse a hipotecar
                //aquí se mandaría a llamar al método que se encarga de crear el listado de las hipotecadas, para que cuando seleccione dicho listado, se puedan visualizar las propiedades poseibles de hipotecar
                //del grupo en cuestión
                MIsPoseciones.ListadoHipotecar.addItem(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre());//aqupi se hace la agregación,              
            }else if(propiedadAuxiliar.estaHIpotecada() || lugarAuxiliar.obtenerCantidadSimplesPoseidas()!=0){
                //se pensaba mandar a sacar del listado de deshipotecar,pero para hay que revisar, puesto que comprar hace que eso acierte, por una única vez y no más
                //ya que la primea vez en la que compre, se encontrará en el listado de hipotecar puesto que se asignó con el bloque donde se adignan los lugares, ya que cimplió con esta inciial, pero
                //cuando haga más compras, es un hecho que ya no se encontraba en el lsitado, por lo cual no debería entrarse a este if
                MIsPoseciones.ListadoHipotecar.removeItem(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre());//se encarga de sacar el ítem de su listado, puesto que cada uno se encarga de los suyo                
            }//ya no
    
    }*/
        
    /*
        Método empleado en el bloque que indica se ha hipotecado o deshipotecado de tal manera que se asigne y elimine la
       propiedad e cuestión de forma respectiva
    */
    /*public void establecerPropiedadesCOnPermisoADeshipotecar(Nodo<Casilla> nodoAuxiliarCasilla){
        Propiedad propiedadAuxiliar;
        propiedadAuxiliar=(Propiedad) nodoAuxiliarCasilla.obtenerObjectcEnCasilla();
        
        if(propiedadAuxiliar.estaHIpotecada()){
            MIsPoseciones.ListadoDeshipotecar.addItem(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre());
        }else{
            MIsPoseciones.ListadoDeshipotecar.removeItem(nodoAuxiliarCasilla.obtenerObjectcEnCasilla().obtenerNombre());
        }
        
    }//ya no*/
    
    public void manipularListadoHipotecaPorCompra(String nombreLugar){//se encarga de eliminar para el caso de la compra, al elemento en el que afectó dicha compra
        Nodo <Casilla> nodoCasillaAuxiliar;
        nodoCasillaAuxiliar=  nodoGrupoActual.obtenerObjectcEnCasilla().obtnerPrimerNodo();;
                        
        for (int elementoActual = 0; elementoActual < 10; elementoActual++) {
            if(nodoCasillaAuxiliar.obtenerObjectcEnCasilla().obtenerNombre().equalsIgnoreCase(nombreLugar)){
                      MIsPoseciones.ListadoHipotecar.removeItemAt(elementoActual);
                      //MIsPoseciones//no recuerdo que iba a poner...talvez el actualizados...
                break;
            }else{
                nodoCasillaAuxiliar=nodoCasillaAuxiliar.obtenerSiguiente();
            }
            
        }//fin del for que se encarga de ubicar el lugar de la compra en cuestión, esto para proceder a eliminar a ese obj 
    }//fin del método  este creo que ya no lo uso... mejor dicho ya no lo uso
    
    /*public void permitiHIpotecarPorVentaCOmpleta(){//método que se encarga de add al listado de las que se pueden hipotecar, el respectivo lugar
        if(){
        
        }
    }*/
    
    public void establecerCondicionesInicialesTransferenciaTarjetas(int jugadorTransactor){//llamado al ser presionado el btn de transferencia, por ello no es necesario deshabitlitar los btn porque estos cad vez que sean select, se actualizarán
        if(!DesarrolloPartida.jugadores[jugadorTransactor].obtenerListadoTarjetasSC().estaVacia()){
            MIsPoseciones.spinnerTransferencia.setEnabled(true);
            MIsPoseciones.listadoJugadoresPosibles.setEnabled(true);//solo para que siga con el patrón xD
            //MIsPoseciones.spinnerTransferencia.setModel(new javax.swing.SpinnerNumberModel(null, 0, DesarrolloPartida.jugadores[jugadorTransactor].obtenerListadoTarjetasSC().darTamanio(),1));
            
        }else{//lo de desseleccionar el btn tb serí bueno de app en las demas condis ini, así me evito en trasnsacción esar revisando si alestar select los valores son 0 para así no tomar en cta la transacción
            MIsPoseciones.spinnerTransferencia.setValue(0);//Haciendo esto me ahorro terner que estar revisando en el, método donde revisa que transacción emplear cada vez que uno se encuentre seleccionado
            MIsPoseciones.spinnerTransferencia.setEnabled(false);            
            MIsPoseciones.listadoJugadoresPosibles.setEnabled(false);
            MIsPoseciones.botonTransferencia.setSelected(false);//xD así se fastidia a un usuario xD
        }//Esto es para el caso del spinner, ahora para el caso de los jugadores, que no requiere condición porque simepre habrán [pues cuando no existan es porque hubo un ganador y por ello ya no tendrá que ingresar a este diálogo de MisPoseciones
        
        for (int jugador = 0; jugador < DesarrolloPartida.jugadores.length; jugador++) {
            if(!DesarrolloPartida.jugadores[jugador].obtnerNombre().equalsIgnoreCase(DesarrolloPartida.jugadores[jugadorTransactor].obtnerNombre())){//bueno, solo este if, porque claro que en el listado no debe aparecer él mismo
                MIsPoseciones.listadoJugadoresPosibles.addItem(DesarrolloPartida.jugadores[jugador].obtnerNombre());
            }
        }
        
    }
    
    /*
        Este método se emplea luego de haber primido el btn de aceptar transacción
        aquí no se pregunta solo se hace, o si se pregunta esto es lo que iría a la hora de acpetar
        así que no corrobora nada
    */
    public void realizarTransferenciaTarjeta(int turnoActual){//este número sería igual al índice del cbBx [vaya que es cbBx xD jajaja]
        DesarrolloPartida.jugadores[MIsPoseciones.listadoJugadoresPosibles.getSelectedIndex()].recibirTarjetaParaSalirDeCarcel(DesarrolloPartida.jugadores[turnoActual].obtenerListadoTarjetasSC().retornarUltimoELemento());        
        JOptionPane.showMessageDialog(null, "Se ha transferido la tarjeta al jugador ", "TRansferencia exitosa", JOptionPane.INFORMATION_MESSAGE);//y ahpi el nombre del jugador por medio de la obtneción del elemento del cbBx estático
        DesarrolloPartida.jugadores[turnoActual].reacomodarTarjetas();
        if(DesarrolloPartida.jugadores[turnoActual].obtenerListadoTarjetasSC().estaVacia()){
            MIsPoseciones.spinnerTransferencia.setValue(0);//Haciendo esto me ahorro terner que estar revisando en el, método donde revisa que transacción emplear cada vez que uno se encuentre seleccionado
            MIsPoseciones.spinnerTransferencia.setEnabled(false);            
            MIsPoseciones.listadoJugadoresPosibles.removeAllItems();
            MIsPoseciones.listadoJugadoresPosibles.setEnabled(false);
            MIsPoseciones.botonTransferencia.setSelected(false);            
        }
    }//ya solo falta que sea llamado en el método de transacciones del diálogo
    
    
    public String[] retornarListadosSubgruposHallados(){//pues el diálogo será quien se encargue de recibir y enviar la INFO respectiva al jugador
        return listadoSubgrupos;
    }
    
    public String[] retornarListadoLugaresHallados(){
        return listadoLugares;
    }
    
    public String retornarConstruccionesHalladas(){
        return construccionEnLugar;
    }
    
    public boolean estaCompletoElGrupo(){
        return estaCompletoElGrupo;
    }
    
    public void limpiarDatosTemporales(){
        listadoSubgrupos=null;
        listadoLugares=null;
        construccionEnLugar=null;
        listaServicios=null;
        listaEstaciones=null;
        nodoGrupoActual=null;
        nodoLugarActual=null;
        estaCompletoElGrupo=false;

    }
    
}
