/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBancopoly;

import Casillas.Casilla;
import Casillas.Inicio;
import FrontendBancopoly.CustomizacionCasillasEficiente;
import FrontendBancopoly.PrincipalCustomizacion;
import FrontendBancopoly.SolicitudDatos;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author phily
 */
public class BackendTablero {
    private int numeroColumnas;
    private int numeroFilas;
    private int numeroCasillasSeleccionadas;
    private JPanel cuadricula[][];
    
    private JPanel contenedor;
    
    final Color Beigh = new Color(219,213,203);
    final Color Gris= new Color(221,219,219);
    final Color Verde= new Color(187,221,84);
    final Color Anaranjado= new Color(245,200,21);
    final Color Morado= new Color(220,52,220);
    final Color Violeta = new Color(188,40,188);
    final Color Rosa= new Color(255,204,204);
    final Color Rosado = new Color(243,179,179);
    final Color AzVer = new Color(76,175,151);
    final Color AmAna = new Color(243,216,107);    
    final Color Azul = new Color(80,87,172);
    final Color Celeste = new Color(158,202,228);
    final Color Rojo = new Color(227,64,64);
    final Color[] colores ={Beigh,Gris, Verde, Anaranjado, Morado, Rosa, AzVer, AmAna };
    
    private MouseEvent casillaEvento;
    
//    JLabel etiquetaNombre = new JLabel();//tienen que ser locales sino solamente el último creado es captado        
//    JLabel etiquetaDefinicion = new JLabel();     
//    JLabel etiquetaIndiceRecorrido = new JLabel();
    
    ListaEnlazada<JPanel> listaCasillas;//Este es el listado que contiene las casillas físicas
    ListaCircular<Casilla> listaRecorrido = new ListaCircular();//Y este es el listado que contiene el recorrido lógico que se fue creando conforme se iba asignanado apariencia a las casillas físicas
    PrincipalCustomizacion principalCustomizacion;
    Verificacion procesoAsignacionCasillas;//le mandarás los datos que obtenga elregistro    
    
    private int separacionColumnas=-1;
    private int separacionFilas=-1;
    
    public BackendTablero(){
    
    }
    
    public BackendTablero(JPanel panelContenedor){
       contenedor=panelContenedor;
       principalCustomizacion = new PrincipalCustomizacion(new javax.swing.JFrame(), true);
       procesoAsignacionCasillas = new Verificacion();
    }        
    
    /**
     *genera las casilas útiles para ingresar los datos a mostrar, ya sea en edición o en creación, para
     * creación solo se omite el paso donde se obtienen los datos del arch, al igual que la redimensión
     * del tablero
     * @param numeroFil
     * @param numeroCol
     */
    public void generarCasillasConMatriz(int numeroFil,int numeroCol){//en creación podría aceptarse redimensionar el mapa, ya que se empleará la lista enlazada para guardarlo y crearlo, pero en ese caso tb habría que imple hilos
        numeroColumnas=numeroCol;
        numeroFilas=numeroFil;
        
        cuadricula= new JPanel[numeroFilas][numeroColumnas];//curiosidad: igual que la forma en que se busca en diagonal (como en el totito) esa es la que genera un mapa en diagonal duhhh! xD
        
        for (int numFilas = 0; numFilas < numeroFilas; numFilas++) {
               for (int numColumnas = 0; numColumnas < numeroColumnas; numColumnas++) {
                   
                   cuadricula[numFilas][numColumnas]= new JPanel(null);     
                  //casillaAMostrar.setBounds(SolicitudDatos.registroDatos.obtenerNumeroColumnas()*( contenedor.getWidth()/SolicitudDatos.registroDatos.obtenerNumeroColumnas()), 
                  //SolicitudDatos.registroDatos.obtnerNumeroFilas()*contenedor.getHeight()/SolicitudDatos.registroDatos.obtnerNumeroFilas(), 
                  //contenedor.getWidth()/SolicitudDatos.registroDatos.obtenerNumeroColumnas(),  contenedor.getHeight()/SolicitudDatos.registroDatos.obtnerNumeroFilas());
                   cuadricula[numFilas][numColumnas].setName(String.valueOf(numFilas)+","+ String.valueOf(numColumnas));
                   cuadricula[numFilas][numColumnas].setBackground(colores[0]);
                   cuadricula[numFilas][numColumnas].setOpaque(true);
                   cuadricula[numFilas][numColumnas].setBorder(BorderFactory.createBevelBorder(1));
                   agregarLabelsMatriz(numFilas, numColumnas);
                   
                   cuadricula[numFilas][numColumnas].addMouseListener(new java.awt.event.MouseAdapter() {
                   
                          public void mouseClicked(java.awt.event.MouseEvent evt) {//solo te hace falta add a la casilla en el for donde crear la cuadrícula UwU           
                                    
                              
                                    casillaEvento=evt;                                    
                                   /*CustomizacionCasillas dialogoCasillas = new CustomizacionCasillas(new javax.swing.JFrame(), true); 
                                    dialogoCasillas.setVisible(true);//Revisa que te hizo falta o que obviaste, creo que es por el hecho de estr encerradas las casillas en otra clase, talvez debas add un intermediario para hacer esta llamada y que la detecte*/               
                                  CustomizacionCasillasEficiente dialog = new CustomizacionCasillasEficiente(new javax.swing.JFrame(), true);                                  
                                  dialog.setVisible(true);
//                                  actualizarInfoCasillas(registroDatos.obtenerGrupoPropiedad(), registroDatos.obtenerNombrePropiedad(), registroDatos.obtenerCobroPorEstanciaUso());
                                  
                            }                                     
                   });                   
            }             
        }
        
        contenedor.updateUI();
    }        
    
    public void generarCasillasConListaEnlazada(int numFilas,int numColumnas){
        listaCasillas = new ListaEnlazada();
        numeroColumnas=numColumnas;
        numeroFilas=numFilas;        
                        
        System.out.println(contenedor.getSize());
        for (int numeroCasilla = 0; numeroCasilla <numFilas*numColumnas ; numeroCasilla++) {                         
             
           listaCasillas.anadirAlFinal(crearCasilla(numeroCasilla));//aaa, entonces no se deberá repetir esto, pues 
           //agregarCasillasAContenedorDeLista();                                                                       
        }//final del for
        
        contenedor.updateUI();
    }
    
    
    
    public void agregarCasillasDeMatrizAContenedor(){//esto por el hecho de la edición como en ese caso, el arreglo o la lista tomaría los valores del registro quien obtuvo los datos del archivo, mas no del frontend
        for (int numFilas = 0; numFilas < numeroFilas; numFilas++) {
            for (int numColumnas = 0; numColumnas < numeroColumnas; numColumnas++) {
                    agregarCasillasAContenedorDeMatriz(numFilas, numColumnas);                             
            }            
        }               
        contenedor.updateUI();
    }
    
    /**
     *Llamado luego de crear las casillas, en la lista enlazada, de tal forma que solo deba acceder al numero total
     * de elementos por medio del número de filas y columnas
     * @param listaDeCasillas
     */
    public void agregarCasillasDeListaAContenedor(ListaEnlazada<JPanel> listaDeCasillas){
        if(listaDeCasillas!=null){
            listaCasillas=listaDeCasillas;
        }
        
        //contenedor.setLayout(new GridLayout(SolicitudDatos.registroDatos.obtnerNumeroFilas(), SolicitudDatos.registroDatos.obtenerNumeroColumnas(), 0, 0));
        Nodo<JPanel> nodoActual=listaCasillas.obtnerPrimerNodo();
        
        for (int numeroCasillas = 0; numeroCasillas <(SolicitudDatos.registroDatos.obtnerNumeroFilas()*SolicitudDatos.registroDatos.obtenerNumeroColumnas()); numeroCasillas++) {            
                agregarCasillasAContenedorDeLista(nodoActual.contenido);//cabeza
                nodoActual=nodoActual.obtenerSiguiente();//Este al final simpre será null, pero como no mandaré a llamar de él ningún método, entonces no afecta                        
        }//fin del for              
        
            contenedor.updateUI();
    }//si hubieras puesto el grid layout solamente esto hubiera sido necesario, porque autoomáticamente este layout se hubiera encargado de colocarlas de tal manera qu equedara como una cuadrícula uniforme
     //Además no iba a ahaber problema con el número de columnas y filas porque eso se le pasasba como parámetro y al recibir el número total de elementos a add pues ya estabaa todo preparado para que pudiera hacer su trabajo        
    
    public void agregarLabelsMatriz(int x, int y){   
        JLabel etiquetaNombre = new JLabel();        
        JLabel etiquetaDefinicion = new JLabel();     
        
        etiquetaNombre.setBounds(0, 10, cuadricula[x][y].getWidth(), 10);        
        //etiquetaNombre.setBackground(Rosa);
        etiquetaNombre.setOpaque(true);
        etiquetaDefinicion.setBounds(0, cuadricula[x][y].getHeight()/2, cuadricula[x][y].getWidth(), 10);       
        //etiquetaDefinicion.setBackground(AmAna);
        etiquetaDefinicion.setOpaque(true);
        
        
        cuadricula[x][y].add(etiquetaNombre);
        cuadricula[x][y].add(etiquetaDefinicion);        
    }
    
    public Color establecerColorNombre(String colorEscogido){
        switch(colorEscogido){
            case "Azul":
                return Azul;
            case "Rojo":
                return Rojo;
            case "Anaranjado":
                return Anaranjado;
            case "Amarillo":
                return AmAna;
            case "Verde":
                return Verde;
            case "Rosado":
                return Rosado;
            case "Violeta":
                return Violeta;   
            case "Gris":
                return Gris;
        }
        
        return Celeste;
    }
    
     public void agregarLabelsListaEnlazada(JPanel casilla, int numero){//Esto lo tendría que volver a generar en la parte para reanudar el juego, puesto que el borde no es serializable
        JLabel etiquetaNombre = new JLabel();        
        JLabel etiquetaDefinicion = new JLabel(); //el nombre del dueño pordría colocarse de una vez aquí en lugar d emostrar el precio   
        JLabel etiquetaIndiceRecorrido = new JLabel();
        JLabel etiquetaFicha[]= new JLabel[SolicitudDatos.registroDatos.obtenerNumeroJugadores()];
                
         for (int numeroLbl = 0; numeroLbl < etiquetaFicha.length; numeroLbl++) {
            etiquetaFicha[numeroLbl]= new JLabel();
            /*etiquetaFicha[numeroLbl].setBounds(((casilla.getWidth()/etiquetaFicha.length)*numeroLbl), ((casilla.getHeight()/8)*7)-10,(casilla.getWidth()/etiquetaFicha.length)-1, 35);//x,y,W,H
            etiquetaFicha[numeroLbl].setBorder(javax.swing.BorderFactory.createRaisedSoftBevelBorder());
            etiquetaFicha[numeroLbl].setBackground(Beigh);
            etiquetaFicha[numeroLbl].setOpaque(true);*/

         }        
         
        etiquetaNombre.setBounds(0, 27, casilla.getWidth(), 25); 
        etiquetaNombre.setHorizontalTextPosition(JLabel.CENTER);
        //etiquetaNombre.setBackground(Rosa);
        //etiquetaNombre.setOpaque(true);
        etiquetaIndiceRecorrido.setBounds(casilla.getWidth()-20,5, 20, 25);
        etiquetaIndiceRecorrido.setName(String.valueOf(numero));
        etiquetaIndiceRecorrido.setOpaque(true);
        etiquetaIndiceRecorrido.setBackground(AmAna);
        etiquetaIndiceRecorrido.setText("-1");//Esto es para que el listener pueda solo rxn con los que posean este índice y así evitar que intente colocar en uno ya creado
        etiquetaIndiceRecorrido.setVisible(false);
        etiquetaDefinicion.setBounds(0, casilla.getHeight()/2, casilla.getWidth(), 25);       
        etiquetaDefinicion.setHorizontalTextPosition(JLabel.CENTER);
                
        casilla.add(etiquetaNombre);
        casilla.add(etiquetaIndiceRecorrido);//voy a ver si no genera problemas por el hecho de ya no llamarse así        
        casilla.add(etiquetaDefinicion);        
        for(int numeroLabel=0; numeroLabel<etiquetaFicha.length; numeroLabel++){
            casilla.add(etiquetaFicha[numeroLabel]);
        }
    }
     
     public void prepararCasillasParaJuego(){//debe ser genereal por lo tanto debe hacer que revise la var de estas casillas que será booleana de nombre enUso donde si no lo es entonces pasará a "blanquear" la casilla por el hecho de hacer set" " para eliminar todo incluyendo su color, esto en la casilla física, porque quedamos que la lógica media vez no presione PRESERVAR INTACTA, será eliminada
        Nodo<JPanel> nodoActual=listaCasillas.obtnerPrimerNodo();
        JLabel labelRecorrido;
        
        for (int numeroCasillas = 0; numeroCasillas <(numeroFilas*numeroColumnas); numeroCasillas++) {//ahorita pondré el -1 para corroborar si está en uso o no,pero depués se reisará la var enUso, donde si es false se procederá a blanquear, esta var deberá hacerse flase luego de abrir el arch para que pueda trabajarse de forma correcta                
            labelRecorrido= (JLabel)nodoActual.contenido.getComponent(1);
                if(Integer.parseInt(labelRecorrido.getText())==-1){
                    nodoActual.contenido.setBorder(null);
                    JLabel labelAuxiliar;
                    
                    for (int lblFichas = 3; lblFichas < nodoActual.contenido.getComponentCount(); lblFichas++) {
                        labelAuxiliar=(JLabel)nodoActual.contenido.getComponent(lblFichas);
                        labelAuxiliar.setBorder(null);
                    }//fin de for para los label de las fichas
                }else{
                        //nodoActual.contenido.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));
                          nodoActual.contenido.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                }
                /*nodoActual.contenido.setEnabled(false);
                nodoActual.contenido.updateUI();*/
                //le pondremos un nombre para que cuando esté en juego lleve ese estado en su nombre y no se pueda acceder al listener
                labelRecorrido.setName("enJuego");//recuerda que con anterioridad el nombre era el número de índice de creación, pero en el juego esto ya no es útil, por el hecho de que ya todas las referencias se tienen guardadas, entonces al hacer esto, no se afectará la funcionalidad del juego, y además creo que esto ayudaría a la parte de editar, porque así se podrían asignar de manera sencilla las columnas más que todo, sin tener confusión con los índices de creación
                nodoActual=nodoActual.obtenerSiguiente();//Este al final simpre será null, pero como no mandaré a llamar de él ningún método, entonces no afecta                        
        }//fin del for         
        
     }
    
    /**
     *Encargado de refrescar la info que el usuario ingrese a la casilla en cuestión
     * @param nombreGrupo
     * @param nombreCasilla
     * @param descripcionCasilla
     */
//    public void actualizarInfoCasillas(String nombreGrupo, String nombreCasilla, int valorPropiedad){//hace falta incorporar la clase de registro en el frontend para que reciba los datos,esto en el momento en que se presiona aceptar en cada panel sobrepuesto
//        etiquetaNombre.setName(nombreGrupo);
//        etiquetaNombre.setText(nombreCasilla);
//        etiquetaDefinicion.setText(String.valueOf(valorPropiedad));
//        
//        etiquetaNombre.setVisible(true);//si da error es por el hecho de haberle cambiado el nombre y no solo aqupi sino tambien cuando desee invocarla con el nombre antiguo
//        etiquetaDefinicion.setVisible(true);
//        
//    }//Creo que el problema del 0 está aquí pues no estoy obteniendo el componente del contenerdor como tal
    
    public void agregarCasillasAContenedorDeMatriz(int x, int y){        
        contenedor.add(cuadricula[x][y]);
        cuadricula[x][y].setVisible(true);
    }
    
    public void agregarCasillasAContenedorDeLista(JPanel casillaAMostrar){                         
        casillaAMostrar.setBorder(BorderFactory.createBevelBorder(1));//ya no será necesario emplear el método para establecer los bpunds y por ello tampoco los métodos creados para establecer las columnas y todo ello... pues se empleará el grid laout
        //casillaAMostrar.setBounds(crearColumnas()*( contenedor.getWidth()/numeroColumnas), crearFilas()*contenedor.getHeight()/numeroFilas, contenedor.getWidth()/numeroColumnas,  contenedor.getHeight()/numeroFilas);
        contenedor.add(casillaAMostrar);
        casillaAMostrar.setVisible(true);             
    }
    
    public JPanel crearCasilla(int numeroCasilla){
             JPanel casilla= new JPanel(null); 
             casilla.setBounds(crearColumnas()*( contenedor.getWidth()/SolicitudDatos.registroDatos.obtenerNumeroColumnas()), 
                    crearFilas()*contenedor.getHeight()/SolicitudDatos.registroDatos.obtnerNumeroFilas(), contenedor.getWidth()/SolicitudDatos.registroDatos.obtenerNumeroColumnas(),
                    contenedor.getHeight()/SolicitudDatos.registroDatos.obtnerNumeroFilas());
             casilla.setBackground(colores[0]);
             casilla.setOpaque(true);            
             agregarLabelsListaEnlazada(casilla, numeroCasilla);
             
             casilla.addMouseListener(new java.awt.event.MouseAdapter() {//no olvides que los listener son activados hasta que sea necesario, sin importar si el método está en acción actualemtne, pues lo que busca es si el elemento en "acción" tiene un escucha si es así entonces se diregie al método donde se define la rxn según la acción impuesta
                 public void mouseClicked(java.awt.event.MouseEvent evt) {//solo te hace falta add a la casilla en el for donde crear la cuadrícula UwU 
                  
                     System.out.println(evt.getSource());
                     JPanel casillaClickeada=(JPanel) evt.getSource();
                     JLabel labelNombre = (JLabel) casillaClickeada.getComponent(0);
                     JLabel labelRecorrido=(JLabel)casillaClickeada.getComponent(1);
                     JLabel labelDescriptivo =(JLabel)casillaClickeada.getComponent(2);
                     JLabel labelParaFichas[] = new JLabel[SolicitudDatos.registroDatos.obtenerNumeroJugadores()];
                     for (int numeroFichas = 0; numeroFichas < labelParaFichas.length; numeroFichas++) {
                         labelParaFichas[numeroFichas]= (JLabel)casillaClickeada.getComponent(3+numeroFichas);
                     }
                     System.out.println(labelRecorrido.getName());
                     
                    if(!labelRecorrido.getName().equalsIgnoreCase("enJuego")){    
                        //Aquí iría el método de ediar, donde se englobaría con las condis debidas: si es editar, la casilla !=-1 y si no ha sido preservada, pues lo que el bloque haría aquí sería preservar, recuerda, crear NO deberá ser colocada en un else
                        //se saltaría vario código de crear excepto, el método que corrobora si es MOv permitido [depedendiendo del modo del juego; la asignación de índices y la add al recorrido
                     
                       metodoParaCrearCasillas(labelRecorrido, labelDescriptivo, labelNombre, casillaClickeada, labelParaFichas);                 
                     
                              //casillaEvento=evt;                                            
                    }//fin del if que inhabilita a los paneles para que en el juego no puedan ser clickeados
                  }//fin del métod escucha
           });    
             
             return casilla;
    }
    
    
    
    public JPanel obtenerCasilla(int fila, int columna){//podrá ser útil en el momento en el que se requiera acceder a los métodos aquí prtes(presentes)                                                                                .            
        return cuadricula[fila][columna];
    }
    
    public PrincipalCustomizacion obtnerPrincipalCOstumizacion(){
        return principalCustomizacion;
    }
    
    public void metodoParaCrearCasillas(JLabel labelRecorrido, JLabel labelDescriptivo, JLabel labelNombre, JPanel casillaClickeada, JLabel[] labelParaFichas){
        creacionDelInicio(labelRecorrido,labelDescriptivo,labelNombre, casillaClickeada, labelParaFichas);//Recuerda iinico tendrá la posición 0 o si no da problemas no le pongas ínidice y si sí,add el método aquí
        creacionDemasCasillas(labelRecorrido, labelNombre, casillaClickeada, labelParaFichas);
    }
    
    public void creacionDelInicio(JLabel labelRecorrido, JLabel labelDescriptivo, JLabel labelNombre, JPanel casillaClickeada, JLabel[] labelParaFichas){
        if(procesoAsignacionCasillas.obtenerNumeroCasillasSeleccionadas()==-1 && !SolicitudDatos.registroDatos.obtenerModoDelJuego()){
            if(procesoAsignacionCasillas.ubicarInicio(Integer.parseInt(labelRecorrido.getName()), SolicitudDatos.registroDatos.obtnerNumeroFilas(), SolicitudDatos.registroDatos.obtenerNumeroColumnas())){//pues el nombre contine su numero de objeto
                bloqueCreacionInicio(labelRecorrido, labelDescriptivo, labelNombre, casillaClickeada,labelParaFichas );
            }
        }else if(procesoAsignacionCasillas.obtenerNumeroCasillasSeleccionadas()==-1 && SolicitudDatos.registroDatos.obtenerModoDelJuego()){
            bloqueCreacionInicio(labelRecorrido, labelDescriptivo, labelNombre, casillaClickeada, labelParaFichas);
        }
    }
    
    public void creacionDemasCasillas(JLabel labelRecorrido,JLabel labelNombre, JPanel casillaClickeada, JLabel[] labelParaFichas){
    //por el hecho de que el inicio puede que no se coloque bien entonces no debe entrar aquí, esto solo es para el resto de las casillas
    if(Integer.parseInt(labelRecorrido.getText())==-1 && !SolicitudDatos.registroDatos.obtenerModoDelJuego() && procesoAsignacionCasillas.obtenerNumeroCasillasSeleccionadas()!=-1) {//aqupi va lo del modo de juego){//pues en este momento le pertenece al panel genrado en la posición correspondiente, entoces al acceder al listener, tendré la referencia al lable correspondiente del compente por el hecho de que en este momento a él se lo asigné
    //en este punto solo necesito determinar el inicio y no esntrar al otro bloque puesto que aquí mismo genero las restricciones para la casilla siguiente
          procesoAsignacionCasillas.esPosicionPermitida(Integer.parseInt(labelRecorrido.getName()));//este punto no depende de si esté bien o no la posicón, pues aquí es donde se determina, por eso no está esa condi, caso
                                                                                                                                                                           //contrario con lo que sucede al buscar los nuevos límites, puesto que debe ser true para hallaros, sino hay que mantenerse con los antiguos, hasta que lo haga bien
         if(procesoAsignacionCasillas.esMovimientoPermitido()){
                procesoAsignacionCasillas.calcularPermisionesSegunBorde(Integer.parseInt(labelRecorrido.getName()));//Aqupi se hace el llamado del método que encuentra las 8 posibilidadessi es que no es borde, esa condición se encuentra dentro de su cuerpo                                        
                bloqueCreacionDemasCasillas(labelRecorrido, labelNombre, casillaClickeada, labelParaFichas);                                      
    
                 //actualizarInfoCasillas(registroDatos.obtenerGrupoPropiedad(), registroDatos.obtenerNombrePropiedad(), registroDatos.obtenerCobroPorEstanciaUso());//esto irá luego de invocar al daiálogo, es decir en la 2da fase de la creación                                        
                //Esto lo tengo que hacer de forma análoga como cuando abtuve el label del recorrido, solo que esta vez le mandaré los datos correspondientes a la forma física como a la forma lógica de la casilla 
                 //y la tjt si es que no es una casilla de tjt pues ahí recibo los datos del diálogo, pero la asignación a las tjts se hace en el cuerpo de la clase de la casilla lógica así que ellas ponen sus propias
                //restricciones par amandarle los datos
         }else{
                //Aquí se mostrará el JOption pane mostrando que la casilla y el numero de casilla no puede insertarse en esa posición, por las manecilas...
                    JOptionPane.showMessageDialog(contenedor, "La casilla "+ (procesoAsignacionCasillas.mostrarIndiceRecorridoActual()+1) + " no puede ser ingresada en esa posición","Posición incorrecta", JOptionPane.INFORMATION_MESSAGE);                                       
        }                                   
                                                                        
    }//quiere decir que no ha sido seleccionada la casilla(panel)
    else if(Integer.parseInt(labelRecorrido.getText())==-1 && SolicitudDatos.registroDatos.obtenerModoDelJuego() && procesoAsignacionCasillas.obtenerNumeroCasillasSeleccionadas()!=-1){
       bloqueCreacionDemasCasillas(labelRecorrido, labelNombre, casillaClickeada, labelParaFichas);
    }//ajjj, te la complicaste porque nada más tenías que emplear la variable estática de registro para obtener el modo del juego y listo, no que al hacer esto tuviste que darte cuenta que no estabas haciendo referencia al mismo objeto y que por ello no funcionaba el modo libre
}
    
//    /**
//     *método llamado por el btn de aceptar que se encuentra en el frontend solicitar datos, con este se
//     * add los lbl que contienen el nombre de la casilla que dpderá de su tipo de grupo y otro lbl, donde
//     * se encuentra su breve descripción.
//     * @param cellName
//     * @param nombreCasilla
//     * @param contenido
//     */
//    public void crearCasillaCompleta(String cellName, String nombreCasilla, String contenido){//es necesario obtener el nombre?? creo que el evento tiene una oción(me refiero a getSource) que se encarga de obtener el componente en cuestión, en ese caso solo será necesario usar la var referenciada al evt y usar su método para luego usar el método de la casilla de crear completametne la casilla
//        String dimensiones[]= new String [2];
//        
//        dimensiones=(cellName.split(","));
//        JLabel etiquetaNombre = new JLabel(nombreCasilla);        
//        JLabel etiquetaDefinicion = new JLabel(contenido);
//        etiquetaNombre.setBackground(Rosa);//aquí recibirá el color definido en la ventana
//        
//        cuadricula[Integer.parseInt(dimensiones[0])][Integer.parseInt(dimensiones[1])].add(etiquetaNombre);//aquí en el JLabel se va a colocar la info ingresada por medio del diálogo en el txtF
//        cuadricula[Integer.parseInt(dimensiones[0])][Integer.parseInt(dimensiones[1])].add(etiquetaDefinicion);
//        etiquetaNombre.setLocation(0,5);
//        etiquetaDefinicion.setLocation(0,10);                
//    }//no está siendo usuado, en su luegar está operando actualizarInfoCasillas
    
    public int crearColumnas(){//problema arreglado xD
        if(separacionColumnas<numeroColumnas-1){
            separacionColumnas++;
        }else{
            separacionColumnas=-1;
            separacionColumnas++;
        }        
        
        return separacionColumnas;
    }
    
    public int crearFilas(){
        if(separacionColumnas==0){
            separacionFilas++;
        }
        
        return separacionFilas;
    }
    
    public void bloqueCreacionInicio(JLabel labelRecorrido, JLabel labelDescriptivo, JLabel labelNombre, JPanel casillaClickeada, JLabel[] labelParaFichas){
        labelRecorrido.setText(String.valueOf(procesoAsignacionCasillas.indiceRecorridoCorrsepondiente()));
        labelRecorrido.setVisible(true);
        labelDescriptivo.setText("Inicio ;)");//en realidad debería ser una imagen xD
        labelNombre.setOpaque(false);//para que no muestre color, puesto que deberá tendrá una imagen 
        labelRecorrido.setVisible(true);
        crearEspacioParaFichas(labelParaFichas,labelNombre.getWidth(), casillaClickeada.getHeight());
        Casilla inicio= new Inicio(casillaClickeada, SolicitudDatos.registroDatos.obtenerDineroPorVuelta());//ya hiciste a registro stático, bueno a sus atributos, entonces con esto puedes seguir con la add de las tarjetas, sin pena alguna xD
        SolicitudDatos.registroDatos.reemplazarUltimoLugarDondeSeHACreado(Integer.parseInt(labelRecorrido.getName()));
        listaRecorrido.insertarSiguiente(inicio);
    }
    
    public void bloqueCreacionDemasCasillas(JLabel labelRecorrido,JLabel labelNombre, JPanel casillaClickeada, JLabel labelParaFichas[]){
        labelRecorrido.setText(String.valueOf(procesoAsignacionCasillas.indiceRecorridoCorrsepondiente()));//Talvez devería ir después de ser creada
        labelRecorrido.setVisible(true);
                                        
        principalCustomizacion.recibirCasillaSeleccionada(casillaClickeada);
        principalCustomizacion.setLocationRelativeTo(null);
        principalCustomizacion.setVisible(true);
        listaRecorrido.insertarSiguiente(principalCustomizacion.retornarCasillas());//Aquí se hace la debida add de la casilla correspondiente                                        
        SolicitudDatos.registroDatos.reemplazarUltimoLugarDondeSeHACreado(Integer.parseInt(labelRecorrido.getName()));//aquí guardo el índice de la última casilla creada        
        labelNombre.setBackground(establecerColorNombre(principalCustomizacion.mandarSeleccionColor()));
        labelNombre.setOpaque(true);
        crearEspacioParaFichas(labelParaFichas,labelNombre.getWidth(), casillaClickeada.getHeight());
        System.out.println("");
    }
    
    public void crearEspacioParaFichas(JLabel etiquetaFicha[], int ancho, int alto){
         for (int numeroLbl = 0; numeroLbl < etiquetaFicha.length; numeroLbl++) {            
            etiquetaFicha[numeroLbl].setBounds((((((ancho/etiquetaFicha.length)/2)-((ancho/6)/2))*(numeroLbl+1))), ((alto/8)*7)-10,(ancho/6)-1, 35);//x,y,W,H
            etiquetaFicha[numeroLbl].setBorder(javax.swing.BorderFactory.createRaisedSoftBevelBorder());
            etiquetaFicha[numeroLbl].setBackground(Beigh);
            etiquetaFicha[numeroLbl].setOpaque(true);

         }
        
    }
    
    public ListaEnlazada obtenerListaCasillasFIsicas(){
        return listaCasillas;
    }
    
    public ListaCircular obtnerListaCircularRecorrido(){
        return listaRecorrido;
    }    
    
    public void recibirRecorridoCIrcularCreado(ListaCircular<Casilla> recorridoCreado){
        listaRecorrido=recorridoCreado;
    }
    
    public void reactivarListadoParaEdicion(){
        Nodo<JPanel> nodoAuxiliar= listaCasillas.obtnerPrimerNodo();
        
        for (int numeroPanel = 0; numeroPanel < listaCasillas.darTamanio(); numeroPanel++) {
            nodoAuxiliar.contenido.getComponent(1).setName(String.valueOf(numeroPanel));
            
            
              nodoAuxiliar.contenido.addMouseListener(new java.awt.event.MouseAdapter() {//no olvides que los listener son activados hasta que sea necesario, sin importar si el método está en acción actualemtne, pues lo que busca es si el elemento en "acción" tiene un escucha si es así entonces se diregie al método donde se define la rxn según la acción impuesta
                 public void mouseClicked(java.awt.event.MouseEvent evt) {//solo te hace falta add a la casilla en el for donde crear la cuadrícula UwU 
                  
                     System.out.println(evt.getSource());
                     JPanel casillaClickeada=(JPanel) evt.getSource();
                     JLabel labelNombre = (JLabel) casillaClickeada.getComponent(0);
                     JLabel labelRecorrido=(JLabel)casillaClickeada.getComponent(1);
                     JLabel labelDescriptivo =(JLabel)casillaClickeada.getComponent(2);
                     JLabel labelParaFichas[] = new JLabel[SolicitudDatos.registroDatos.obtenerNumeroJugadores()];
                     for (int numeroFichas = 0; numeroFichas < labelParaFichas.length; numeroFichas++) {
                         labelParaFichas[numeroFichas]= (JLabel)casillaClickeada.getComponent(3+numeroFichas);
                     }
                     System.out.println(labelRecorrido.getName());
                     
                    if(!labelRecorrido.getName().equalsIgnoreCase("enJuego")){    
                        //Aquí iría el método de ediar, donde se englobaría con las condis debidas: si es editar, la casilla !=-1 y si no ha sido preservada, pues lo que el bloque haría aquí sería preservar, recuerda, crear NO deberá ser colocada en un else
                        //se saltaría vario código de crear excepto, el método que corrobora si es MOv permitido [depedendiendo del modo del juego; la asignación de índices y la add al recorrido
                     
                       metodoParaCrearCasillas(labelRecorrido, labelDescriptivo, labelNombre, casillaClickeada, labelParaFichas);                 
                     
                              //casillaEvento=evt;                                            
                    }//fin del if que inhabilita a los paneles para que en el juego no puedan ser clickeados
                  }//fin del métod escucha
           });   
              
           nodoAuxiliar=nodoAuxiliar.nodoSiguiente;
        }
    
    }
  
    
    //este ya no [fue susti por el de abajito xD]
    public void agregarParaColumnas(int columnaInicial){//debo recibir el número de índice de la casilla representante de su columna [todo se trabajará desde 0, en los métodos...]
        Nodo<JPanel> nodoAuxiliar;
        nodoAuxiliar=listaCasillas.obtnerPrimerNodo();
        int numeroPanelNuevo=columnaInicial+1;
        
        for (int repetir = 0; repetir < SolicitudDatos.registroDatos.obtnerNumeroFilas(); repetir++) {//hago el mismo proceso una cdad de veces = numero filas            
            
            if(repetir==0){                              
                nodoAuxiliar=listaCasillas.avanzarHastaEInsertar(nodoAuxiliar, columnaInicial-1, crearCasilla(numeroPanelNuevo));
                
            }else{
                numeroPanelNuevo=numeroPanelNuevo+SolicitudDatos.registroDatos.obtenerNumeroColumnas()+1;
                nodoAuxiliar=listaCasillas.avanzarHastaEInsertar(nodoAuxiliar, SolicitudDatos.registroDatos.obtenerNumeroColumnas(), crearCasilla(numeroPanelNuevo));
            }
            //si hubieras hecho el for que recorra toda la lista para solo emplear el método para añadir después de entonces hubieras podido hacer que antes de llegar al nodo al que se le add
            //cambiar los bounds (si es <= al numero de columna inicial, entonces sin índices sino con índices tb) para luego mandar la casilla que devuelve el método crear casilla y así
            //hasta que se asigne la última... y como ya sabes que el número de nodo es igual al numero de columnaINi + numeroColsNuevo para las demás entonces con ese for y dos if's se va...
            
            //colocaré los labeles
            //add el mouse listener [el cual convertiré e método para no andarlo repitiendo]
            
            //luego en otro método les cb los bounds y de una vez asigno los índices correctos a 
        }
        
    }//si hago que aquí sea donde se avance en los nodos y que solo se emplee el método para add despuésDe de la lista enlazada entonces fácilmente podría añadir mandar el índice al label de recorrido para crear
    //y modificar de tal manera que se procese bien para ver si el movimiento es restringido para el caso de modoRestringido
    
    /**
     * Este será el método que se empleará en los spinner y en los representantes de cada columna, recuerda que el número de filas y columnas debe ser cambiado de una vez en registro aunque no se guarde al final, sino no se trabajará bien
     * @param columnaDePartida
     */
    public void agregarColumnas(int columnaDePartida){//recuerda que se iniciará desde 1
        Nodo<JPanel> nodoAuxiliar;//como ya empleaste el método para agregar las casillas al contenedor entonces este listado ya tiene el contenido del arch guardado, lo cual sucederá siempre por el paso previo a mostrar sin permiso de editar
        nodoAuxiliar=listaCasillas.obtnerPrimerNodo();//este sería el numero 1
        int tamanioOriginal=listaCasillas.darTamanio();
        int numeroColumnaDeInsercion=columnaDePartida+1;
        int numeroFilasCompletas=0;
        
        for (int numeroNodo = 1; numeroNodo <= tamanioOriginal; numeroNodo++) {
            nodoAuxiliar.contenido.setBounds(crearColumnas()*( contenedor.getWidth()/SolicitudDatos.registroDatos.obtenerNumeroColumnas()), 
                    crearFilas()*contenedor.getHeight()/SolicitudDatos.registroDatos.obtnerNumeroFilas(), contenedor.getWidth()/SolicitudDatos.registroDatos.obtenerNumeroColumnas(),
                    contenedor.getHeight()/SolicitudDatos.registroDatos.obtnerNumeroFilas());//debe ir aquí pues en este punto tengo al panel que ya estaba, pues en el nuevo ya tiene sus bounds definidos
            
            if(numeroNodo<=columnaDePartida){                                
                if(numeroNodo==columnaDePartida){
                    listaCasillas.nuevoNodoDespuesDe(nodoAuxiliar, crearCasilla(numeroColumnaDeInsercion));
                    nodoAuxiliar=nodoAuxiliar.nodoSiguiente;
                    columnaDePartida=columnaDePartida+SolicitudDatos.registroDatos.obtenerNumeroColumnas();
                    numeroFilasCompletas++;
                }
                
            }else{                
                    
                JLabel labelRecorrido = (JLabel) nodoAuxiliar.contenido.getComponent(1);//aqrego el índice
                labelRecorrido.setName(String.valueOf(numeroNodo+numeroFilasCompletas));
            
                if(numeroNodo==columnaDePartida){                                        
                    listaCasillas.nuevoNodoDespuesDe(nodoAuxiliar, crearCasilla(numeroColumnaDeInsercion+((SolicitudDatos.registroDatos.obtenerNumeroColumnas()+1)*numeroFilasCompletas)));
                    nodoAuxiliar=nodoAuxiliar.nodoSiguiente;
                    columnaDePartida=columnaDePartida+SolicitudDatos.registroDatos.obtenerNumeroColumnas();
                    numeroFilasCompletas++;
                }
            }                                   
            
            encontrarUltimaCasillaCreada(nodoAuxiliar.contenido);
            nodoAuxiliar=nodoAuxiliar.nodoSiguiente;//cuando no se agrege al final este apuntará a un valor nulo, pero como no se empeará entoces no provocará un null pointer
        }
    }
    
    public void encontrarUltimaCasillaCreada(JPanel panel){
       JLabel recorrido = (JLabel) panel.getComponent(1);
        
        if(Integer.parseInt(recorrido.getText())==SolicitudDatos.registroDatos.obtnerNumeroCasillasAsignadas()){//es decir si el lbl tiene el último índice de recorrido entonces es la última casilla
            SolicitudDatos.registroDatos.reemplazarUltimoLugarDondeSeHACreado(Integer.parseInt(recorrido.getName()));
        }
    }
    
    //el método en sí está bien pues a casilla requiere de la descripción ya sea cdad o cadena, lo cual depende del tipo de casilla, al igual que el nombre.....pero esto me hace ver que no está bien construida la ventana que solicita por el
    //hecho de que resulta ineficiente ya que tengo que hacer muchas comparaciones par saber a que componente debo hacer referencia para obtener su datos, lo cual implicaría poner lógica extensa, la cual no debería ir en el frontend
   //pero por facilidad estaba pensando en ello(aunque ahora lo estoy reconsiderando xD [inefiencia más pto -.. no gracias xD]) por ello trata de mejorar ese aspecto de la ventana, pero eso sí
    //Debes recibir el nombre ya sea del tipo de grupo o del lugar
    //la descripción si la casilla lo admite
    //la cdad de pago, ya sea por uso o para los demás (al ser de tipo tgt)
    //el color 
    //a parte debes mandar al registro, eso pienso por el momento los grupos creados, pero puede que varía al modificar la apariencia o modalidad y mira si al final app el hecho de que todo en todas las clases que necesiten info del tablero
    //mas que todo la info de creación o edición, la vas a ir a traer al registro temporal, si es así pues ya no habrá necesidad de llamar a tanta clase en cada frontend sino solo al registro donde ya tendrá metida en su método a la clase
    //que requiera de dicha info.. pero eso es una idea primeriza
    
    
    
}//fin de la clase Backend Tablero


//en este caso como dijo el inge... sin el escucha es un atributo y por ello cuando se haga referencia a dicho componente se hará al atributo,
//de tal forma que solo requieres asignarle dicho escuha al componente respectivo sin iportar donde se encuentre, pues miestras se haga
//Referencia a él se hará referecnia a su atrib escuha si se da la ocasión xD.

//ENTONCES LUEGO DE CREAR LA LISTA ENLAZADA, lo que debes hacer es adpatar este for que se encarga de crear las casillas,
//en este solo será necesario mandarle al nodo el panel sin nada aderido, aparte, (como la cantidad de componentes a recibir por el nodo,
//Se especifica en la clase nodo, pero las agregaciones que el componente tenga se tienen que definir en la clase de dicho componente,
//Entonces debe tener un ctructor ya el nodo donde reciba un panel y luego para agregar componentes a los paneles que se encuentren 
//ya en la lista, recibiendo en el método donde se busca el elemento de la lista y luego mandandoselos al nodo correspondiente, para luego
//en el método de agregar componentes del nodo (si es que resulta eficiente) hacer las add ahí al componente ó de forma más directa en
//lugar de esto último obtner el componente del nodo y aplicarle los cambios respectivos
