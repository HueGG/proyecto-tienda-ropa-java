
package Controlador.Cliente;

import Modelo.Cliente.Cliente;
import Modelo.Cliente.MetodosCliente;
import Vista.VentanaAdministrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * Descripción: Esta clase servirá de puente entre las VISTAS y los MODELOS  de cada clase
 */

/**
 * El CONTROLADOR tiene que estar DIRECTAMENTE RELACIONADO con la VISTA de la aplicacion
 * por lo que se debe de implementar un ActionListener que requiere de la liberia java.awt.event.ActionListener;
 */
public class ControladorCliente implements ActionListener{
    //Se requeren los siguinetes objetos
    VentanaAdministrador nuevaVistaCliente;   //Ojeto de la Clase VentanaAdministrador que es la parte visual
    Cliente nuevoCliente;   //Objeto de la clase Cliente
    MetodosCliente modeloCliente;   //Objeto de la clase MetodosCliente
    
    
    //**********************************************************
    //************* Metodo constructor
    //**********************************************************
    //EL CONSTRUCTOR reuiere como parametros los objetos de la VISTA, MODELO Y CLASE del Clinte
    public ControladorCliente(VentanaAdministrador nuevaVistaAdmin, Cliente nuevoCliente, MetodosCliente modeloCliente) {
        this.nuevaVistaCliente = nuevaVistaAdmin;
        this.nuevoCliente = nuevoCliente;
        this.modeloCliente = modeloCliente;
        //Llenado de tabla de clientes
        llenarTablaClientes();
        //Se indica donde se utilizar el ActionListener
        nuevaVistaAdmin.btnRegistrarCliente.addActionListener(this);
        nuevaVistaAdmin.btnBuscarCliente.addActionListener(this);
        nuevaVistaAdmin.btnActualizarCliente.addActionListener(this);
        nuevaVistaAdmin.btnEliminarCliente.addActionListener(this);
        nuevaVistaAdmin.btnResetFormCliente.addActionListener(this);
    }
    
    
    //**********************************************************
    //Función que despliega el fomrulario para la persona
    //**********************************************************
    public void iniciarFormularioCliente(){
        nuevaVistaCliente.setTitle("CRUD  MVC");
        nuevaVistaCliente.setLocationRelativeTo(null);
        //nuevaVistaAdmin.panelCliente.setEnabled(true);
        //nuevaVistaCliente.txtIdCliente.setVisible(false);
        
    }
    
    /** 
     * INICIA LA IMPLEMENTACION DEL MÉTODO ABSTRATO << actionPerformed(ActionEvent ae)  >>
     */
    
    @Override
    public void actionPerformed(ActionEvent objAccionEvento) {
                            //********************************************************
                            //***************** BOTON INSERTAR ***********************
                            //********************************************************
        if(objAccionEvento.getSource() == nuevaVistaCliente.btnRegistrarCliente){
            
            //Se valida que el correo cumpla con un formato valido
            if (validarEmail(nuevaVistaCliente.txtEmailCliente.getText())){
                    //SI EL BOTON PRESIONADO ES EL DE INSERTAR, se inicializan los atributos del objeto nuevoCliente
                //Se inicializan los atriutos del objeto nuevoCliente
                nuevoCliente.setNombre(nuevaVistaCliente.txtNombreCliente.getText());
                nuevoCliente.setApePaterno(nuevaVistaCliente.txtApePatCliente.getText());
                nuevoCliente.setApeMaterno(nuevaVistaCliente.txtApeMatCliente.getText());
                nuevoCliente.setEmail(nuevaVistaCliente.txtEmailCliente.getText().toLowerCase());
                nuevoCliente.setPassword(nuevaVistaCliente.txtEmailCliente.getText());
                nuevoCliente.setTelefono(nuevaVistaCliente.txtTelefonoCliente.getText());
                nuevoCliente.setDireccion(nuevaVistaCliente.txtAreaDireccionCliente.getText());

                /**Se procede a realizar la iserción en la BD mendiante el llamado a la función 
                 * boolean registrarCliente(Cliente nuevaCliente) que nos retornará un valor booleadno
                 * dependiendo de si se registra o no la persona
                 * La funcion registrarCliente(Cliente nuevoCliente) pertenece a la clase << MetodosCliente >>
                 * por lo que en la actual clase se ha creado un objeto de dicha clase, para poder llamar a el métod 
                 * registrarCliente(Cliente nuevoCliente)
                 */
                if (modeloCliente.registrarCliente(nuevoCliente)){
                    //SI el valor de retorno es TRUE y por tanto un registro exitos
                    JOptionPane.showMessageDialog(null, "Cliente registrado", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                    llenarTablaClientes();

                }else{
                    //Si el valor de retorno es FALSE
                    //Mensaje de error si no se realiza el registro
                    JOptionPane.showMessageDialog(null, "ERROR AL REALIZAR EL REGISTRO", "ERROR DE REGISTRO", JOptionPane.ERROR_MESSAGE);
                }
                limpiarFormularioClienteAdmin();

            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un correo de la forma example@domain.com", "Correo invalido",  JOptionPane.INFORMATION_MESSAGE);
            }
            
        }else if (objAccionEvento.getSource() == nuevaVistaCliente.btnBuscarCliente){
                            //********************************************************
                            //***************** BOTON BUSCAR ***********************
                            //********************************************************
            //Validar que el campo id no este vacio                
            if( !validarIdBuscar() ){
                nuevoCliente.setIdCliente(Integer.parseInt(nuevaVistaCliente.txtIdClienteBuscar.getText()));
                boolean resultado = modeloCliente.buscarCliente(nuevoCliente);

                if(resultado){
                    //BUSQUEDA EXITOSA
                    //Se rellena el formulario
                    nuevaVistaCliente.txtIdCliente.setText(String.valueOf(nuevoCliente.getIdCliente()));
                    nuevaVistaCliente.txtNombreCliente.setText(nuevoCliente.getNombre());
                    nuevaVistaCliente.txtApePatCliente.setText(nuevoCliente.getApePaterno());
                    nuevaVistaCliente.txtApeMatCliente.setText(nuevoCliente.getApeMaterno());
                    nuevaVistaCliente.txtEmailCliente.setText(nuevoCliente.getEmail());
                    nuevaVistaCliente.txtTelefonoCliente.setText(nuevoCliente.getTelefono());
                    nuevaVistaCliente.txtAreaDireccionCliente.setText(nuevoCliente.getDireccion());
                    //Se actualiza tablas
                    llenarTablaClientes(resultado);

                }else{
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado", "BUSQUEDA VACIA", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Campo vacio\nIngrese un id valido", "BUSQUEDA VACIA", JOptionPane.WARNING_MESSAGE);
            }
            
            
        }else if (objAccionEvento.getSource() == nuevaVistaCliente.btnActualizarCliente) {
                            //********************************************************
                            //***************** BOTON ACTUALIZAR ***********************
                            //********************************************************
                            
            if (validarEmail(nuevaVistaCliente.txtEmailCliente.getText())){
                //Se inicializan los atributos del objeto
            nuevoCliente.setIdCliente(Integer.parseInt(nuevaVistaCliente.txtIdCliente.getText()));
            nuevoCliente.setNombre(nuevaVistaCliente.txtNombreCliente.getText());
            nuevoCliente.setApePaterno(nuevaVistaCliente.txtApePatCliente.getText());
            nuevoCliente.setApeMaterno(nuevaVistaCliente.txtApeMatCliente.getText());
            nuevoCliente.setEmail(nuevaVistaCliente.txtEmailCliente.getText());
            nuevoCliente.setTelefono(nuevaVistaCliente.txtTelefonoCliente.getText());
            nuevoCliente.setDireccion(nuevaVistaCliente.txtAreaDireccionCliente.getText());
            
            if( modeloCliente.actualizarCliente(nuevoCliente) ){
                //Actuzalizacion correcta
                
                JOptionPane.showMessageDialog(null, "Actualizacion correcta", "ACTUALIZACION EXITOSA", JOptionPane.INFORMATION_MESSAGE);
                llenarTablaClientes();
                }else{
                    JOptionPane.showMessageDialog(null, "Actualizacion fallida", "ERROR AL ACTUALIZAR", JOptionPane.WARNING_MESSAGE);
                    llenarTablaClientes();
                    limpiarFormularioClienteAdmin();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un correo de la forma example@domain.com", "Correo invalido",  JOptionPane.INFORMATION_MESSAGE);
            }
                            
                
                            
                            
        }else if (objAccionEvento.getSource() == nuevaVistaCliente.btnEliminarCliente){
                            //********************************************************
                            //***************** BOTON ACTUALIZAR ***********************
                            //********************************************************
            
            nuevoCliente.setIdCliente(Integer.parseInt(nuevaVistaCliente.txtIdCliente.getText()));
            
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al usuario "+nuevoCliente.getNombre()+" "+nuevoCliente.getApePaterno()+" "+nuevoCliente.getApeMaterno()+"?", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
            
            if (respuesta == 0){
                //ELIMINACION cofirmada
                if(modeloCliente.eliminarCliente(nuevoCliente)) {
                    //Eliminacion exitosa
                    JOptionPane.showMessageDialog(null, "Eliminacion exitosa", "ELIMINACION COMPLETA", JOptionPane.WARNING_MESSAGE);
                    limpiarFormularioClienteAdmin();
                }else{
                    JOptionPane.showMessageDialog(null, "No se logro ELIMINAR al cliente", "ERROR DE ELIMINACION", JOptionPane.WARNING_MESSAGE);
                }
                
            }else{
                
            }
            llenarTablaClientes();
            
        }else if(objAccionEvento.getSource() == nuevaVistaCliente.btnResetFormCliente){
                            //********************************************************
                            //***************** BOTON ACTUALIZAR ***********************
                            //********************************************************
            limpiarFormularioClienteAdmin();
            llenarTablaClientes();
        }
        
        
        
    } //FINALIZA IMPLEMENTACION DE MÉTODO ABSTRACTO <<  actionPerformed(ActionEvent ae)  >>
    
    
    
    //*******************************************************
    //******************* METODOS DE CLASE **********************
    //*******************************************************
    //Lipieza de formulario
    private void limpiarFormularioClienteAdmin(){
        nuevaVistaCliente.txtIdCliente.setText(null);
        nuevaVistaCliente.txtIdClienteBuscar.setText(null);
        
        nuevaVistaCliente.txtNombreCliente.setText(null);
        nuevaVistaCliente.txtApePatCliente.setText(null);
        nuevaVistaCliente.txtApeMatCliente.setText(null);
        nuevaVistaCliente.txtEmailCliente.setText(null);
        nuevaVistaCliente.txtTelefonoCliente.setText(null);
        nuevaVistaCliente.txtAreaDireccionCliente.setText(null);
    }
    
    
    private void llenarTablaClientes( ){
        //eEl objeto de tipoResultSet almacenara los datos recibidos por la función consultarTablaClientes();
        ResultSet tuplaCliente = modeloCliente.consultarTablaClientes();
        //Se crea un objeto para el modelo de la tabla donde se listaran los regisros
        DefaultTableModel modeloTablaCliente = new DefaultTableModel();
        //El modelo servirá para añadir las filas directamente a la tablas
        nuevaVistaCliente.tablaCliente.setModel(modeloTablaCliente);
        
        
        //Agregar columnas a modelo tabla
        modeloTablaCliente.addColumn("ID");
        modeloTablaCliente.addColumn("Nombre");
        modeloTablaCliente.addColumn("Correo");
        modeloTablaCliente.addColumn("Telefono");
        modeloTablaCliente.addColumn("Direccion");
        
        //Llenado de tabla
        try {
            
            while(tuplaCliente.next()){
                //Se crea un arreglo de tipo objet para que acepte cualquier dato
                //Cada arreglo de fila[] tiene 5 lementos (5 olumnas)
                Object fila[] = new Object[5];  //En la instanciacion (new Object[5] se pone 5 por los 5 elementos que son las 5 columnas
                //Se reccoren las 5 columnas del registro (fila o tupla)
                
                //for (int i = 0; i < 3; i++) {
                    //Se almacenan los datos
                    /**En estos casos, el indice debe iniciar desde 1, 
                     * Si el indice inicia en 0, marcará el siguiente error: 
                     *                      java.sql.SQLException: Column Index out of range, 0 < 1  
                     */
                    //if ( i== 1 || i==2 || i==3){
                        //fila[2] = tuplaCliente.getObject(1)+" "+tuplaCliente.getObject(2)+" "+tuplaCliente.getObject(2);
                    //}
                    //fila[i] = tuplaCliente.getObject(i+1); //se pone i+1 porque la primer que se quiere mostrar desde la BD es... 
                //}
                
                fila[0] = tuplaCliente.getObject(1);
                fila[1] = tuplaCliente.getObject(2)+" "+tuplaCliente.getObject(3)+" "+tuplaCliente.getObject(4);
                fila[2] = tuplaCliente.getObject(5);
                fila[3] = tuplaCliente.getObject(6);
                fila[4] = tuplaCliente.getObject(7);
                modeloTablaCliente.addRow(fila);     //Se añaden filas mientras sigan existiendo registros
            }
            
        } catch (Exception e) {
            System.err.println("Error al cargar tabla de clientes: "+e);
        }
        
    }
    
    //Lllenado de tabla con una busqueda 
    private void llenarTablaClientes(boolean resultado){
        
        if(resultado){
            //Busqueda exitosa
            //Se crea modelo de tabla clientes
            DefaultTableModel modeloTablaCliente = new DefaultTableModel();
            nuevaVistaCliente.tablaCliente.setModel(modeloTablaCliente);
            
            //Agregar columnas a modelo tabla
            modeloTablaCliente.addColumn("ID");
            modeloTablaCliente.addColumn("Nombre");
            modeloTablaCliente.addColumn("Correo");
            modeloTablaCliente.addColumn("Telefono");
            modeloTablaCliente.addColumn("Direccion");
            
            //Creacion de fila de tipo Object
            Object fila[] = {nuevoCliente.getIdCliente(), (nuevoCliente.getNombre()+" "+nuevoCliente.getApePaterno()+" "+nuevoCliente.getApeMaterno()), nuevoCliente.getEmail(), nuevoCliente.getTelefono(), nuevoCliente.getDireccion()};
            modeloTablaCliente.addRow(fila);
        }
        
    }
    
    //Validar formato de correo electonico
    private boolean validarEmail(String correoCliente){
        /**
         * Requiere de la siguientes librerias
         * import java.util.regex.Matcher;
         * import java.util.regex.Pattern;
         */
        //Patron para validar email
        //Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$");
        
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        Matcher matcher = patron.matcher(correoCliente);
        return matcher.find(); //Retrna un true en caso de que el parametro cumpla con la sintaxis de un correo electronico
    }
    
    private boolean validarIdBuscar(){
        boolean validarId = nuevaVistaCliente.txtIdClienteBuscar.getText().isEmpty();
        return validarId;
    }
}
