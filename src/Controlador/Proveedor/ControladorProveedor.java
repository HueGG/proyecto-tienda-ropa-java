
package Controlador.Proveedor;

import Controlador.Producto.ControladorProducto;
import Modelo.Proveedor.MetodosProveedor;
import Modelo.Proveedor.Proveedor;
import Vista.VentanaAdministrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 * DESCRIPCION: Esta clase sirve de puente de comuinicacion entre el MOdelo de Proveedor y la Vista o formulario del CRUD Proveedor
 */
/**
 * El CONTROLADOR tiene que estar DIRECTAMENTE RELACIONADO con la VISTA de la aplicacion
 * por lo que se debe de implementar un ActionListener que requiere de la liberia java.awt.event.ActionListener;
 */
public class ControladorProveedor implements ActionListener{
    //Objetos atributos de la clase
    private VentanaAdministrador nuevaVistaProveedor; //objeto de la vista
    private Proveedor nuevoProveedor; //objeto de la clase
    private MetodosProveedor modeloProveedor; //objeto del modelo para la clase
    
    //**********************************************************
    //************* Metodo constructor
    //**********************************************************
    public ControladorProveedor (VentanaAdministrador nuevaVistaProveedor, Proveedor nuevoProveedor, MetodosProveedor modeloProveedor){ //Recibe como parametros 3 objetos
        //Se inicializan los objetos atributos de la clase
        this.nuevaVistaProveedor = nuevaVistaProveedor;
        this.nuevoProveedor = nuevoProveedor;
        this.modeloProveedor = modeloProveedor;
        
        //Función paa llenar tabla de proveedores
        llenarTablaProveedores();
        //Se indica donde se utilizar el ActionListener
        nuevaVistaProveedor.btnRegistrarProveedor.addActionListener(this);
        nuevaVistaProveedor.btnBuscarProveedor.addActionListener(this);
        nuevaVistaProveedor.btnActualizarProveedor.addActionListener(this);
        nuevaVistaProveedor.btnEliminarProveedor.addActionListener(this);
        nuevaVistaProveedor.btnLimpiarFormProveedor.addActionListener(this);
        
    }
    
    //**********************************************************
    //Función que despliega el fomrulario para le proveedor
    //**********************************************************
    public void iniciarFormularioProveedor(){
        nuevaVistaProveedor.setTitle("CRUD Persona MVC");
        nuevaVistaProveedor.setLocationRelativeTo(null);
        //nuevaVistaProveedor.txtIdPersona.setVisible(false);
    }

    
    
    /** =====================================================================================
     * INICIA LA IMPLEMENTACION DEL MÉTODO ABSTRATO << actionPerformed(ActionEvent ae)  >>
     *  =====================================================================================
     */
    @Override
    public void actionPerformed(ActionEvent objetoAccionEvento) {
        //********************************************************
        //***************** BOTON INSERTAR ***********************
        //********************************************************
        if (objetoAccionEvento.getSource() == nuevaVistaProveedor.btnRegistrarProveedor){ //Si el boton presionado es el de registrar proveedor
            
            //Validar correo 
            if( validarEmail(nuevaVistaProveedor.txtEmailProveedor.getText()) ){
                //Se inicializan los atributos del objeto de la clase Proveedor
                //Se inicializan mediante metodoos de acceso
                nuevoProveedor.setNombreProveedor(nuevaVistaProveedor.txtNombreProveedor.getText());
                nuevoProveedor.setNif(nuevaVistaProveedor.txtNif.getText());
                nuevoProveedor.setPersonaContacto(nuevaVistaProveedor.txtContactoProveedor.getText());
                nuevoProveedor.setEstado(nuevaVistaProveedor.comboEdoProveedor.getSelectedItem().toString());
                nuevoProveedor.setMunicipio(nuevaVistaProveedor.txtMunicipioProveedor.getText());
                nuevoProveedor.setColonia(nuevaVistaProveedor.txtColoniaProveedor.getText());
                nuevoProveedor.setCalle(nuevaVistaProveedor.txtCalleProveedor.getText());
                nuevoProveedor.setNumExt(nuevaVistaProveedor.txtNumExtProveedor.getText());
                nuevoProveedor.setNumInt(nuevaVistaProveedor.txtNumIntProveedor.getText());
                nuevoProveedor.setEntreCalle1(nuevaVistaProveedor.txtCalleUnoProveedor.getText());
                nuevoProveedor.setEntreCalle2(nuevaVistaProveedor.txtCalleDosProveedor.getText());
                nuevoProveedor.setCp(nuevaVistaProveedor.txtCPProveedor.getText());
                nuevoProveedor.setTelefonoProveedor(nuevaVistaProveedor.txtTelefonoProveedor.getText());
                nuevoProveedor.setEmailProveedor(nuevaVistaProveedor.txtEmailProveedor.getText().toLowerCase());

                /**Ya que se han inicializado los atributos del objeto de la claee Proveedor, se llama al 
                 * método de insercion de un nuevo proveedor
                 * Se pasa como el paramtro el objeto de tipo Proveedor
                 * 
                 * Recordar que el metodo que realiza el registro devuelve un TRUE si el registro es exitoso, caso contrario, un FALSE
                 * el metodo se llama mediante el objeto de de la clase MetodosProveedor
                 */
                if(modeloProveedor.registrarProveedor(nuevoProveedor)){
                    //REGISTRO DE PROVEEDOR EXITOSO
                    JOptionPane.showMessageDialog(null, "Proveeddor regisrado exitosamente", "REGISTRO EXITOSO", JOptionPane.INFORMATION_MESSAGE);
                    //Actualización de tabla del formulario de proveedores
                    llenarTablaProveedores();
                    /**
                     * CODIGO DE PRUEBA
                     */
                    ControladorProducto controlProducto = new ControladorProducto();
                    controlProducto.llenarComboProveedores(nuevaVistaProveedor);
                    controlProducto.llenarTablaProductos(nuevaVistaProveedor);
                }else{
                    JOptionPane.showMessageDialog(null, "No se logro registra al proveedor", "ERROR DE REGISTRO", JOptionPane.WARNING_MESSAGE);
                }
                limpiarFormularioProveedor();

            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un correo de la forma example@domain.com", "Correo invalido", JOptionPane.WARNING_MESSAGE);
            }
            
        }else if( objetoAccionEvento.getSource() == nuevaVistaProveedor.btnBuscarProveedor ){
                                //********************************************************
                                //***************** BOTON BUSCAR PROVEEDOR***********************
                                //********************************************************
            //Se valida que el campo de id a buscar no este vacio
            if ( !validarIdBuscar() ){
                //SE Inicializa el id del objeto nuevoProveedor
                nuevoProveedor.setIdProveedor(Integer.parseInt(nuevaVistaProveedor.txtIdBuscadoProveedor.getText()));
                //Se llama al método de busqueda  enviando como parametro el objeto nuevoProveedor
                boolean resultadoBusqueda = modeloProveedor.buscarProveedor(nuevoProveedor);

                if( resultadoBusqueda ){
                    //Si se  logra hallar un registro
                    //Se llena el formulario

                    nuevaVistaProveedor.txtIdProveedor.setText(String.valueOf(nuevoProveedor.getIdProveedor()));

                    nuevaVistaProveedor.txtIdBuscadoProveedor.setText(String.valueOf(nuevoProveedor.getIdProveedor()));
                    nuevaVistaProveedor.txtNombreProveedor.setText(nuevoProveedor.getNombreProveedor());
                    nuevaVistaProveedor.txtNif.setText(nuevoProveedor.getNif());
                    nuevaVistaProveedor.txtContactoProveedor.setText(nuevoProveedor.getPersonaContacto());
                    nuevaVistaProveedor.comboEdoProveedor.setSelectedItem(nuevoProveedor.getEstado());
                    nuevaVistaProveedor.txtMunicipioProveedor.setText(nuevoProveedor.getMunicipio());
                    nuevaVistaProveedor.txtColoniaProveedor.setText(nuevoProveedor.getColonia());
                    nuevaVistaProveedor.txtCalleProveedor.setText(nuevoProveedor.getCalle());
                    nuevaVistaProveedor.txtNumExtProveedor.setText(nuevoProveedor.getNumExt());
                    nuevaVistaProveedor.txtNumIntProveedor.setText(nuevoProveedor.getNumInt());
                    nuevaVistaProveedor.txtCalleUnoProveedor.setText(nuevoProveedor.getEntreCalle1());
                    nuevaVistaProveedor.txtCalleDosProveedor.setText(nuevoProveedor.getEntreCalle2());
                    nuevaVistaProveedor.txtCPProveedor.setText(nuevoProveedor.getCp());
                    nuevaVistaProveedor.txtTelefonoProveedor.setText(nuevoProveedor.getTelefonoProveedor());
                    nuevaVistaProveedor.txtEmailProveedor.setText(nuevoProveedor.getEmailProveedor());

                    //Llenado de tabla
                    llenarTablaProveedores(resultadoBusqueda);
                }else{
                    JOptionPane.showMessageDialog(null, "No se encontro al proveedor", "BUSQUEDA FALLIDA", JOptionPane.WARNING_MESSAGE);
                    limpiarFormularioProveedor();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un id valido", "BUSQUEDA FALLIDA", JOptionPane.WARNING_MESSAGE);
            }
            
        }else if (objetoAccionEvento.getSource() == nuevaVistaProveedor.btnActualizarProveedor){
                                //********************************************************
                                //***************** BOTON ACTUALIZAR PROVEEDOR***********************
                                //********************************************************
            
            //Validar que el correo sea valido
            if( validarEmail(nuevaVistaProveedor.txtEmailProveedor.getText()) ){
                //Se inicializa el id del proveedor
                nuevoProveedor.setIdProveedor(Integer.parseInt(nuevaVistaProveedor.txtIdProveedor.getText()));
                /**
                 * SE inicializan el resto de los atributos del objeto nuevoProveedor con los valores ingresados en el formulario
                 */
                nuevoProveedor.setNombreProveedor(nuevaVistaProveedor.txtNombreProveedor.getText());
                nuevoProveedor.setNif(nuevaVistaProveedor.txtNif.getText());
                nuevoProveedor.setPersonaContacto(nuevaVistaProveedor.txtContactoProveedor.getText());
                nuevoProveedor.setEstado(nuevaVistaProveedor.comboEdoProveedor.getSelectedItem().toString());
                nuevoProveedor.setMunicipio(nuevaVistaProveedor.txtMunicipioProveedor.getText());
                nuevoProveedor.setColonia(nuevaVistaProveedor.txtColoniaProveedor.getText());
                nuevoProveedor.setCalle(nuevaVistaProveedor.txtCalleProveedor.getText());
                nuevoProveedor.setNumExt(nuevaVistaProveedor.txtNumExtProveedor.getText());
                nuevoProveedor.setNumInt(nuevaVistaProveedor.txtNumIntProveedor.getText());
                nuevoProveedor.setEntreCalle1(nuevaVistaProveedor.txtCalleUnoProveedor.getText());
                nuevoProveedor.setEntreCalle2(nuevaVistaProveedor.txtCalleDosProveedor.getText());
                nuevoProveedor.setCp(nuevaVistaProveedor.txtCPProveedor.getText());
                nuevoProveedor.setTelefonoProveedor(nuevaVistaProveedor.txtTelefonoProveedor.getText());
                nuevoProveedor.setEmailProveedor(nuevaVistaProveedor.txtEmailProveedor.getText());
                /** Una vez inicializados los atributos del objeto << nuevoProveedor, este objeto se envía como paramtro a la funcion
                 * actualizar 
                 */

                if ( modeloProveedor.actualizarProveedor(nuevoProveedor) ){
                    //Actualización correcta
                    JOptionPane.showMessageDialog(null, "Proveeddor actualizado exitosamente", "ACTUALIZACION EXITOSA", JOptionPane.INFORMATION_MESSAGE);
                    //Actualización de tabla del formulario de proveedores
                    llenarTablaProveedores();
                    /**
                     * CODIGO DE PRUEBA
                     */
                    ControladorProducto controlProducto = new ControladorProducto();
                    controlProducto.llenarComboProveedores(nuevaVistaProveedor);
                    controlProducto.llenarTablaProductos(nuevaVistaProveedor);
                }else{
                    JOptionPane.showMessageDialog(null, "No se logro ACTUALIZAR al proveedor", "ERROR DE ACTUALIZACION", JOptionPane.WARNING_MESSAGE);
                    limpiarFormularioProveedor();
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un correo de la forma example@domain.com", "Correo invalido", JOptionPane.WARNING_MESSAGE);
            }
            
        }else if( objetoAccionEvento.getSource() == nuevaVistaProveedor.btnEliminarProveedor ){
                                //********************************************************
                                //***************** BOTON ELIMINAR PROVEEDOR***********************
                                //********************************************************
            //Se inicializa el atributo idProveedor del objeto nuevoProveedor
            nuevoProveedor.setIdProveedor(Integer.parseInt(nuevaVistaProveedor.txtIdProveedor.getText()));
            
            if( modeloProveedor.eliminarProveedor(nuevoProveedor) ){
                JOptionPane.showMessageDialog(null, "Proveedor Eliminado exitosamente", "ELIMINACION EXITOSA", JOptionPane.INFORMATION_MESSAGE);
                //Actualización de tabla del formulario de proveedores
                llenarTablaProveedores();
            }else{
                JOptionPane.showMessageDialog(null, "No se logro ELIMINAR al proveedor", "ERROR DE ELIMINACION", JOptionPane.WARNING_MESSAGE);
                
            }
            limpiarFormularioProveedor();
        }else if ( objetoAccionEvento.getSource() == nuevaVistaProveedor.btnLimpiarFormProveedor ){
            
            limpiarFormularioProveedor();
            llenarTablaProveedores();
        }
    }
    
    
    
    
    
    
    
    /**^^ 
     * ^^ FINALIZA LA IMPLEMENTACION DEL MÉTODO ABSTRATO << actionPerformed(ActionEvent ae)  >>
     * ^^
     */
    
    //*******************************************************
    //******************* METODOS DE CLASE **********************
    //*******************************************************
    //Limpieza de formulario
    private void limpiarFormularioProveedor(){
        nuevaVistaProveedor.txtIdProveedor.setText(null);
        
        nuevaVistaProveedor.txtIdBuscadoProveedor.setText(null);
        nuevaVistaProveedor.txtNombreProveedor.setText(null);
        nuevaVistaProveedor.txtNif.setText(null);
        nuevaVistaProveedor.txtContactoProveedor.setText(null);
        nuevaVistaProveedor.comboEdoProveedor.setSelectedIndex(0);
        nuevaVistaProveedor.txtMunicipioProveedor.setText(null);
        nuevaVistaProveedor.txtColoniaProveedor.setText(null);
        nuevaVistaProveedor.txtCalleProveedor.setText(null);
        nuevaVistaProveedor.txtNumExtProveedor.setText(null);
        nuevaVistaProveedor.txtNumIntProveedor.setText(null);
        nuevaVistaProveedor.txtCalleUnoProveedor.setText(null);
        nuevaVistaProveedor.txtCalleDosProveedor.setText(null);
        nuevaVistaProveedor.txtCPProveedor.setText(null);
        nuevaVistaProveedor.txtTelefonoProveedor.setText(null);
        nuevaVistaProveedor.txtEmailProveedor.setText(null);
    }
    

    //Llenado de tabla proveedores
    private void llenarTablaProveedores(){
        //Se almacenan los registros de la tabla proveedores para mostrar en la tabla del formulario
        ResultSet tuplaProveedor = modeloProveedor.consultarTablaProveedor();
        
        //MODELO PARA LA TABLA
        //El modelo servirá para añadir las filas directamente a la tablas
        DefaultTableModel modeloTablaProveedor = new DefaultTableModel();
        nuevaVistaProveedor.tablaProveedor.setModel(modeloTablaProveedor);
        //Agregar columnas a modelo tabla
        modeloTablaProveedor.addColumn("ID");
        modeloTablaProveedor.addColumn("Proveedor");
        modeloTablaProveedor.addColumn("RFC");
        modeloTablaProveedor.addColumn("Contacto");
        modeloTablaProveedor.addColumn("C.P");
        modeloTablaProveedor.addColumn("Telefono");
        modeloTablaProveedor.addColumn("E-mail");
        
        try {
            //Mientras existan registros 
            while(tuplaProveedor.next()){
                //Se crea un arreglo de tipo objet para que acepte cualquier dato
                //Cada arreglo de fila[] tiene 7 lementos (7 olumnas)
                Object fila[] = new Object[7]; //En la instanciacion (new Object[6] se pone cuatro por los SEIS elementos que son las 6 columnas
                //Se reccoren las 7 columnas del registro (fila o tupla)
                for(int i =0; i < 7 ; i++){
                    //Se almacenan los datos
                    /**En estos casos, el indice debe iniciar desde 1, 
                     * Si el indice inicia en 0, marcará el siguiente error: 
                     *                      java.sql.SQLException: Column Index out of range, 0 < 1  
                     */
                    fila[i] = tuplaProveedor.getObject(i+1); //se pone i+1 porque la primer que se quiere mostrar desde la BD es... 
                }
                modeloTablaProveedor.addRow(fila); //Se añaden filas mientras sigan existiendo registros
            }
        } catch (Exception e) {
            System.err.println("ERROR EN EL LA IMPRESION DE LA TABLA PROVEEDORES: "+ e);
        }
        
    }
    
    //Llenado de tabla con registros del proveedor buscado
    private void llenarTablaProveedores(boolean resultado){
        if(resultado){
            //Si se encuentra el registro
            //MODELO PARA LA TABLA
            //El modelo servirá para añadir las filas directamente a la tablas
            DefaultTableModel modeloTablaProveedor = new DefaultTableModel();
            nuevaVistaProveedor.tablaProveedor.setModel(modeloTablaProveedor);
            //Agregar columnas a modelo tabla
            modeloTablaProveedor.addColumn("ID");
            modeloTablaProveedor.addColumn("Proveedor");
            modeloTablaProveedor.addColumn("RFC");
            modeloTablaProveedor.addColumn("Contacto");
            modeloTablaProveedor.addColumn("C.P");
            modeloTablaProveedor.addColumn("Telefono");
            modeloTablaProveedor.addColumn("E-mail");
            
            //Creacion de fila de tipo Object
            Object fila[] = {nuevoProveedor.getIdProveedor(), nuevoProveedor.getNombreProveedor(), nuevoProveedor.getNif(), nuevoProveedor.getPersonaContacto(), nuevoProveedor.getCp(), nuevoProveedor.getTelefonoProveedor(), nuevoProveedor.getEmailProveedor()};
            modeloTablaProveedor.addRow(fila);
            
        }else{
            
        }
    }
    
    
    //Validar formato de correo electonico
    private boolean validarEmail(String correoProveedor){
        /**
         * Requiere de la siguientes librerias
         * import java.util.regex.Matcher;
         * import java.util.regex.Pattern;
         */
        //Patron para validar email
        //Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$");
        
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        Matcher matcher = patron.matcher(correoProveedor);
        return matcher.find(); //Retrna un true en caso de que el parametro cumpla con la sintaxis de un correo electronico
    }
    
   
    private boolean validarIdBuscar(){
        boolean validarId = nuevaVistaProveedor.txtIdBuscadoProveedor.getText().isEmpty();
        return validarId;
    }
    
    
}
