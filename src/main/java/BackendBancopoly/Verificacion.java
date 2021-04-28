/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBancopoly;

import FrontendBancopoly.SolicitudDatos;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author phily
 */
public class Verificacion {
    
    private String ubicacionInicio;//no cambiará mientras la partida, el tablero sean los mismos y no se edite su lugar
    private int numeroCasillasAsignadas;//para ir dándole el número correspondiente a cada componente, el 0 es del INICIO
    private int filas;
    private int columnas;    
    private int indiceComponenteAnterior;//para saber que dirección agarró a partir de esta var, la cual contiene el índice del movimiento actual que al ser aprobado pasa a ser el anterior del cual dependen las siguientes permisiones
    //private int movimientosPermitidos[]= {-1,-1,-1,-1,-1,-1,-1,-1};//según la casilla asignada anteriormente de forma correcta. D, I, ARR, AB, ARRDE, ARRIZ, ABDE, ABIZ
    private int movimientosPermitidos[];//con esta lógica no me importa saber a que posición se refiere el número al cual se parece, solo me interesa saber si la casilla seleccionada
    private boolean esBorde;                                                      //Se encuentra en un borde para volver a calcular dichos valores y saber si se encuentra su índice en dicho arreglo
    private boolean esMovimientoPermitido;//para saber si hacer el incremento del índice o no y cambiar las permisiones
    public static boolean esModoLibre;//debe ser cambiado por los respectivos ckcBx
    
    
    public Verificacion(){//aún está en discusión de si lo recibirá del registro temporal o directamente del forntend
        numeroCasillasAsignadas=SolicitudDatos.registroDatos.obtnerNumeroCasillasAsignadas();
        
    }
    /**
     *Estos datos los recibe al principio de la customización, es decir luego de ingresarlos en el frontend
     * 
     * @param numeroFilas
     * @param numeroColumnas
     */    
    public void asignarEsenciales(int numeroFilas, int numeroColumnas){//aún está en discusión de si lo recibirá del registro temporal o directamente del forntend
        filas= numeroFilas;
        columnas = numeroColumnas;
    }//creo que los recibirá directamente del registro temporal, pues de todos modos ahí se va a guardar este y los demás datos que en el frontend se ingresen
              
    /**
     *Se mandará a llamar cuando asigne la primer casilla, de tal forma que se sepa que método emplear para
     * saber si la asignación deseada es permitida o no
     * @param indiceDelInicio
     */    
    public boolean ubicarInicio(int indiceDelInicio, int filas, int columnas){//hace falta ver si se harán especificaciones para las esquinas o sup e inf agarrarán sus esquinas y le quitarán espacio a los der e izq        
        asignarEsenciales(filas, columnas);//pues así tendré el numero de filas y columnas en la instancia correspondiente
        
        if(esBordeSuperior(indiceDelInicio)){//borde superior
            indiceComponenteAnterior=indiceDelInicio;//esto ya no lo empleo...pues al seleccionar la casilla, en ese mismo momento hallo las respectivas permisiones según sea su posición
            ubicacionInicio="Superior";
            garantizadorOrientacion(indiceDelInicio);                
        }
        else if (esBordeInferior(indiceDelInicio)){//borde inferior
            indiceComponenteAnterior=indiceDelInicio;
            ubicacionInicio="Inferior";
            garantizadorOrientacion(indiceDelInicio);                
        }
        else if(esBordeIzquierdo(indiceDelInicio)){
                indiceComponenteAnterior=indiceDelInicio;
                 ubicacionInicio="Izquierdo";
                 garantizadorOrientacion(indiceDelInicio);                
        }        
        else if(esBordeDerecho(indiceDelInicio)){
              indiceComponenteAnterior=indiceDelInicio;
              ubicacionInicio="Derecho";
              garantizadorOrientacion(indiceDelInicio); //debe ir aquí y no al final de todos estos if, porque si entra a alguno de estos está asegurado que cumple con las condiciones, pues si no entra acá, por ningún motivo debería
              //invocarlo, por 2 razones, puede que sea modoLibre o no haya asignado bien el inicio, estando en el restringido
        }      
        else{//pues es el caso contrario en general sin importar que este se subdivida
            if(ubicacionInicio==null && esModoLibre==true){//en ningún borde, pero esto solo debe suceder para cuando esté en el modo libre, sino está mal y hay que evitar que se cree la casilla, además de mostrar el msje
                JOptionPane.showMessageDialog(null, "NO TE PREOCUPER POR LAS UBICACIONES, TÚ SOLO COLOCA :)", "GRANDIOSA LIBERTAD", JOptionPane.PLAIN_MESSAGE);               
            }else if(ubicacionInicio==null && esModoLibre==false){
                JOptionPane.showMessageDialog(null, "NO PUEDES COLOCAR EL INICIO FUERA DE LOS BORDES", "INICIO FUERA DE LUGAR", JOptionPane.ERROR_MESSAGE);
                return false;
            }                        
        }
        
        
        return true;//puesto que si no infringió lo de modo libre entonces se dirigirá directamente aquí ya sea porque es modo libre o porque fue alguno de los bordes casos en los cuales debe asignarse el inicio
    }//fin del método para ubicar el inicio           
    
    /**
     *Se basa en la siguiente casilla a la primera o a la última, dependiendo de que comportamiento haya decidido escoger 
     * en la primera, si ninguna de las dos es correcta entonces se procederá ha pedirle que dinuje nuevamente el trayecto, 
     *porque en no dio una forma que fuera conforme a las manecillas del reloj, el resultado se devolverá a una variable que se exe dos veces si la primera es false
     */
    private void garantizadorOrientacion(int indiceCasillaInicio){//ya sea que la vaya guardando conforme la vaya asigando o la busque en el mapa
           movimientosPermitidos= new int[2];           
        
           switch(ubicacionInicio){
               case "Superior" ://creo que esta es la parte que debería cambiar relamente en lugar de los bordes                   
                       movimientosPermitidos[0]= indiceCasillaInicio+1;//Derecha  --- es borde
                       movimientosPermitidos[1]= indiceCasillaInicio+columnas+1;//AbDer                             
                   
               break;
               
               case "Inferior"://bueno no veo nada malo con que vaya arriba a la zquierda
                   movimientosPermitidos[0]= indiceCasillaInicio-1;//Izquierda  --- es borde
                   movimientosPermitidos[1]= indiceCasillaInicio-(columnas+1);//ArrIzq
                   
                   break;    
                   
               case "Derecho":
                   movimientosPermitidos[0]= indiceCasillaInicio+columnas;//Abajo --- es borde
                   movimientosPermitidos[1]= indiceCasillaInicio+(columnas-1);//AbIzq
               break;     
               
               case "Izquierdo":
                   if(indiceCasillaInicio!=(filas-1)*columnas){//Como ya está garantizado que entrará aquí por ser izq, se puede colocar esto, sin confusiones
                       garantizarContinuidad(indiceCasillaInicio);//Exceto si es la esquina izquierda
                   }else{
                       movimientosPermitidos= new int[1];                         
                       movimientosPermitidos[0]= indiceCasillaInicio-columnas;//Arriba  --- es borde                       
                   } 
                   
               break;                                  
            }//Fin del switch                         
           
           //esMovimientoPermitido=true;//porque media vez ya está aquí es porque pasó las condiciones de los bordes, pero no es necesario revisarlo en esta posición sino hasta después, por ello en ese después se tiene que decir si, lo es o no
    }//fin del método dedicado a la casilla segunda, esto para la garantización de la orientación      
    
     /**
     *Llamado luego de dfinir las permisiones, su valor permite o no 
     * proseguir con la inserción de casilas en la cuadrícula
     * @param posicion
     * @return
     */
    public void esPosicionPermitida(int posicion){       
        if(movimientosPermitidos==null){
            if(SolicitudDatos.registroDatos.obtnerNumeroCasillasAsignadas()==0){//es decir por alguna razón solo creo el inicio
                garantizadorOrientacion(SolicitudDatos.registroDatos.darUltimoLugarDeCasillaCreada());
            }else{
                calcularPermisionesSegunBorde(SolicitudDatos.registroDatos.darUltimoLugarDeCasillaCreada());//hay que revisar algo aquí fmmm xD
            }
        }
        
       for (int posicionARevisar = 0; posicionARevisar < movimientosPermitidos.length; posicionARevisar++) {//esto evitará errores con la redimensión del arreglo 
           if(posicion==movimientosPermitidos[posicionARevisar]){                                   
                esMovimientoPermitido=true;                      
                break;
           }            
       }//Fin del for        
        
       esBorde=false;//pues no lo sé y siempre de debe hacer, ya que de esto dependerá el permitir las 8 casillas
    }
    
      /**
     *Llamado antes de determinar los siguietnes espacios permitidos para moverse, para saber si debe
     * o no llamarse ha dicho método, dicho llamado se hace en el listener
     * @param indiceCasillaACrear
     * @return
     */
    public boolean esMovimientoPermitido(int indiceCasillaACrear){//este dato se obtiene en el bloque donde se escuha el click, en el componente en cuestión, el cual se obtiene por medio del método de obtener índice de JPanel o por el get que colocaré si la asignación de los índices (o si no se pudo obtnerlos a pesar de asignarse como esperaba) no era como esperaba
        for(int indiceMovimiento=0; indiceMovimiento<movimientosPermitidos.length; indiceMovimiento++){
            if(indiceCasillaACrear==movimientosPermitidos[indiceMovimiento] && movimientosPermitidos[indiceMovimiento]!=-1){
                indiceComponenteAnterior=indiceCasillaACrear;//hagoat BackendBancopoly.Verificacion.esPosicionPermitida(Verificacion.java:134) el paso del nuevo anterior, del cual dependen las nuevas permisiones               
                //hacer otra vez a movimientosPerimitdos -1 en todo, para volver a tener la misma lógica
               // reiniciarMovimientosPermitidos();
                numeroCasillasAsignadas++;//el inicio si llega a tener número será =0;
                SolicitudDatos.registroDatos.incrementarNumeroCasillasAsignadas();
                return true;
            }
        }//final del for        
        return false;
    }//este ya no lo empleo actualmente
    
    //por el momento pienso que las diagonales tienen el mismo comportamiento que sus respectivas direcciones en x, esto para las que van hacia arriba
    //RECUERDA MEDIA VEZ SEA VERDAD UNA DE ESAS CONDICIONES, NO PUEDE SER NADA DE LO QUE TENGA QUE VER CON IZQ                
    /**
     *Mandado a llamar luego de determinar el inicio para la casilla 2 solamente
     * de tal forma que no tenga que estar verificando que casilla tenga que ser la del inicio puesto que está
     * la restricción de los bordes y esta además, lo cual garantiza que vaya conforme a la orientación dicha
     */
  
    public void calcularPermisionesSegunBorde(int indiceMovimiento){
         if(esBordeIzquierdo(indiceMovimiento)){            
         }
         else{
             if(esBordeDerecho(indiceMovimiento)){             
             }
             else{
                 if(esBordeSuperior(indiceMovimiento)){                 
                 }
                 else{
                     esBordeInferior(indiceMovimiento);
                 }
             }
         }
         if(esBorde==false){//Aqupi se llamará ahora al método que calcula las 8 permisiones, para cuando esté en el modoRestringido
             //permitirTodasLasCasillas();
             garantizarContinuidad(indiceMovimiento);
         }
         
         esMovimientoPermitido=false;//porque no se si el siguiente la casilla que viene lo será, de esta manera se evita entrar a este bloque
    }
    
    private void garantizarContinuidad(int indiceMovimiento){//utilizado para cuando el inicio esté en el borde izquierdo, puesto que no tiene restricciones propias solo la de continuidad
        movimientosPermitidos= new int[8];
        
        movimientosPermitidos[0]=indiceMovimiento+1;//Derecha
        movimientosPermitidos[1]=indiceMovimiento-1;//Izquierda
        movimientosPermitidos[2]=indiceMovimiento-columnas;//Arriba
        movimientosPermitidos[3]=indiceMovimiento+columnas;//Abajo
        movimientosPermitidos[4]=movimientosPermitidos[2]+1;//ArrDer
        movimientosPermitidos[5]=movimientosPermitidos[2]-1;//ArrIzq
        movimientosPermitidos[6]=movimientosPermitidos[3]-1;//AbDer
        movimientosPermitidos[7]=movimientosPermitidos[3]+1;//AbIzq                       
        
    }
    
    private boolean esBordeIzquierdo(int indiceMovimiento){
        movimientosPermitidos= new int[3];//La esquina inferior izquierda le debe pertenecer por las restricciones de los bordes, pues si no, no se le permitiría ir hacia nungún solo lado
        
        for(int numeroFila=1; numeroFila<filas; numeroFila++){//abarca el borde izquierdo, a excepción de las esquinas                      
                if(indiceMovimiento==(numeroFila*columnas)){                    
                    movimientosPermitidos[0]=indiceMovimiento-columnas;//Arriba  --- es borde                  
                    movimientosPermitidos[1]=indiceMovimiento+1;//Derecha
                    movimientosPermitidos[2]=movimientosPermitidos[0]+1;//ArrDer
                    esBorde=true;
                    return true;//esto se mandará a guardar a una variable que permita entrar al bloque donde se compara si escogió uno de los lugares permitidos
                }
        }
         return false;
    }
    
    private boolean esBordeDerecho(int indiceMovimiento){//para verificar si la casilla cumple con estos requisitos se deberá colocar después de esto(por la condición de la 2da casilla), pues la var se inicializa en false, recuerda, como se van sumando entonces jamás será igual a -1 el índice y por ello solo se quedará con una posibilidad cuando sea la casilla 1(Después del inicio)
        movimientosPermitidos= new int[3];
        
            for(int numeroFila=0; numeroFila<filas-1; numeroFila++){//abarca la esquina sup derecha, mas no la inf der
                if(indiceMovimiento==(columnas*(numeroFila+1))-1){
                    movimientosPermitidos[0]=indiceMovimiento+columnas;//Abajo --- es borde
                    movimientosPermitidos[1]=indiceMovimiento-1;//Izq                    
                    movimientosPermitidos[2]=movimientosPermitidos[0]-1;//AbDer
                    esBorde=true;
                    return true;//se hará false la variable que reciba esto, hasta que coincida su valor, en ese bloque se hará el incremento del índice
                }
        }
         return false;
    }
    
    private boolean esBordeSuperior(int indiceMovimiento){//Abarca las esquinas superiores
        movimientosPermitidos= new int[3];
        
        if(indiceMovimiento>=0 && indiceMovimiento<columnas-1){//ya no le pertenece el borde sup der
            movimientosPermitidos[0]=indiceMovimiento+1;//Derecha --- es borde
            movimientosPermitidos[1]=indiceMovimiento+columnas;//Abajo
            movimientosPermitidos[2]=movimientosPermitidos[1]+1;//AbDer
            esBorde=true;
            return true;
        }        
            return false;    
    }
    
    private boolean esBordeInferior(int indiceMovimiento){
        movimientosPermitidos= new int[3];
        
        if(indiceMovimiento>=(((filas-1)*columnas)+1) && indiceMovimiento<((filas*columnas))){//pues no abarca la inf izq, por las restricciones de este borde
            movimientosPermitidos[0]=indiceMovimiento-1;//Izquierda  --- es borde
            movimientosPermitidos[1]=indiceMovimiento-columnas;//Arriba
            movimientosPermitidos[2]=movimientosPermitidos[1]-1;//ArrIzq
            esBorde=true;
            return true;
        }        
            return false;    
    }
    
    public int obtenerNumeroCasillasSeleccionadas(){
        return numeroCasillasAsignadas;
    }
    
    public int tamanioArregloPermisiones(){
        return movimientosPermitidos.length;
    }

    public boolean esMovimientoPermitido(){        
        return esMovimientoPermitido;        
    }
    
    public int indiceRecorridoCorrsepondiente(){
        numeroCasillasAsignadas++;
        SolicitudDatos.registroDatos.incrementarNumeroCasillasAsignadas();//con esto realemnte no sería necesario tener la variable para esto y menos tener estos métodos porque podríaacceder directamente a dichos valores de casillas asignadas
        
        return numeroCasillasAsignadas;
    }
    
    public int mostrarIndiceRecorridoActual(){
        return numeroCasillasAsignadas;
    }
    
    public void establecerModoJuego(boolean modo){
        esModoLibre=modo;
    }

    /**
     *método llamado luego de seleccionar los respectivos chkBx
     * @param modo
     */
    public boolean obtenerModoJuego(){
        return esModoLibre;//Recuerda que estos tambien pueden utilizarse para asignarle los valores a las variables
    }
            
    
    /**
     *Empleado al momento de borrar todo lo que está después de la casilla que rxn, ante el llamado por 
     * medio de su respectivo escucha(click derecho)
     * @param numeroCasillaPreservada
     */
    public void regresarHasta(int numeroCasillaPreservada){//te decidirpas por buscar el número anterior al indice que tienes del componente preservado?, si es así entonces sería bueno crear los métodos que se encarguen de hacer el cálculo de arriba, abajo, izq... para no repetir el código aquí y al momento de crera las permisiones
            
        indiceComponenteAnterior=numeroCasillaPreservada;
    }
    
    public void hallarOrientacion(int casillaPreservada){
        int casillaAncestro;
        
        //recorrer el mapa entero hasta encontrar el valor de la casilla -1, de tal forma que se pueda analizar las respectivas permisiones, esto, luefo de hacer encontrado su ancestro
        //método de la lista enlazada que busca el ancestro
       
        //casillaAncestro=método que al encontrar dicho ancestro debuelve el inidce asignado por su contenedor
                
          //      
        
        
    }//Esto es para borrar, pues a partir de la preservada se sabrá que permisiones deben haber
    
    
    
}

//entonces de primero lo de los bordes, para crear los respectivos índices permitidos, luego el método que solo se puede acceder si la variable es true, en el cual se hará el incremento y el cambio a false, creo que tendré que asignar no 
//en el bloue de los bordes sino en otro la única posibilidad para cuando sea el bloque dos, el cual se ejecutaría solo si se está en dicho bloque, luego se verificaría si cumple con los requisitos
//para hacer el cambio, luego si está en algún borde y así de forma repetida (es decir que la primera permisión será para el bloque 2, generado en el bloque que se exe solo si se está en la creación de él
//

//AHORA...toca formar el método que corrobora la orientación, además del método que verifica si está opcupado o no, para borrar o para asignar directamente el contenido correspondiente(esto si
//antes de asignar su indice de recorrido no había un -1 ahí) según lo que posea a la lista circular de recorrido
//luego de esto crear el recorrido(lista circular)
//lógica de casillas
//depués no se si configurar las partes del frontend para recibir o seguir con la demás lógica del backend o componer lo de la customización de casillas, o ver si es lo que dijo el inge

//el primer numeroDeObjetoEnContenedor que se obtendrá, será el del inicio, el cual se almacenará para saber 
//que dirección escogió al final de cuentas el usaurio, esto se hará con cada uno de los componentes siguientes,
//hasta n-1, para hacer las comparaciones debidas; esto se hará cada vez que se asigne correctamente una casilla
//a la cuadricula, comenando por la del inicio


//NO OLVIDES que si escoge "borrar todo lo posterior a esta" xD jas, jas debes calcular las permisiones nuevamente, al momento de aceptar, sino no podrás corroborar de forma correcta
//además se procederá a hacer "" para los números asignados y dejar el conteo en el valor de la casilla donde aceptó la opción