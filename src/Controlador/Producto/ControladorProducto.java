/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Producto;

import Modelo.Categoria.Categoria;
import Modelo.Categoria.MetodosCategoria;
import Modelo.Producto.MetodosProducto;
import Modelo.Producto.Producto;
import Modelo.Proveedor.MetodosProveedor;
import Modelo.Proveedor.Proveedor;
import Vista.VentanaAdministrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * DEscripcion de la clase: Servir de intermediaro entre el modelo (clase MetodosProducto) y la vista (fomrulario)
 */
//
public class ControladorProducto implements ActionListener{
    //Objetos atributos de la clase
    private VentanaAdministrador nuevaVistaProductos; //Objeto de la Vista
    private Producto nuevoProducto; //Objeto de la clase
    private MetodosProducto modeloProducto; //Objeto del modelo
    
    //**********************************************************
    //************* Metodo constructor
    //**********************************************************
    public ControladorProducto(VentanaAdministrador nuevaVistaProductos, Producto nuevoProducto, MetodosProducto modeloProducto) { //Recibe como parametros 3 objetos
        //Se inicializan los objetos atributos de la clase
        this.nuevaVistaProductos = nuevaVistaProductos;
        this.nuevoProducto = nuevoProducto;
        this.modeloProducto = modeloProducto;
        //Se llenan los comboBox
        llenarComboProveedores();
        llenarComboCategorias();
        llenarTablaProductos();
        limpiarFormularioProducto();
        
        //Se indica donde se utilizara el ActionListener
        nuevaVistaProductos.btnRegistrarProducto.addActionListener(this);
        nuevaVistaProductos.btnBuscarProducto.addActionListener(this);
        nuevaVistaProductos.btnActualizarProducto.addActionListener(this);
        nuevaVistaProductos.btnEliminarProducto.addActionListener(this);
        nuevaVistaProductos.btnLimpiarFormProducto.addActionListener(this);
        
        
        
    }
    //Constructor 
    public ControladorProducto() {
        
    }
    
    //**********************************************************
    //Función que despliega el fomrulario para los productos
    //**********************************************************
    public void iniciarFormularioProductos(){
        nuevaVistaProductos.setTitle("PRODUCTOS");
        nuevaVistaProductos.setLocationRelativeTo(null);
        //nuevaVistaProveedor.txtIdPersona.setVisible(false);
    }

    
    /** =====================================================================================
     * INICIA LA IMPLEMENTACION DEL MÉTODO ABSTRATO << actionPerformed(ActionEvent ae)  >>
     *  =====================================================================================
     */
    @Override
    public void actionPerformed(ActionEvent objetoAccionEvento) {
                                
        if(objetoAccionEvento.getSource() == nuevaVistaProductos.btnRegistrarProducto){
                                //********************************************************
                                //***************** BOTON INSERTAR ***********************
                                //********************************************************
            //SE Inicializan los atributos del objeto miembro de esta clase
            //nuevoProducto.setIdProveedor( nuevaVistaProductos.comboProveedorProducto. );
            
            /**Rerdar que el combo box correspondiente a los proveedores se llena mediante un arreglo de objetos de tipo Proveedor
             * Por lo que se obtiene primero el item seleccionado (que es un objeto de tipo Proveedor)
             * Lo que se obtiene es un dato de tipo Object, por lo que se convierte a tipo Proveedor
             * Se almacena en el objeto auxiliar de tipo Proveedor
             */
            Proveedor proveedorAuxiliar = (Proveedor) nuevaVistaProductos.comboProveedorProducto.getSelectedItem();
            nuevoProducto.setIdProveedor(proveedorAuxiliar.getIdProveedor());
            Categoria categoriaAuxiliar = (Categoria) nuevaVistaProductos.comboCategoriaProducto.getSelectedItem();
            nuevoProducto.setIdCategoria(categoriaAuxiliar.getIdCategoria());
            
            nuevoProducto.setNombre(nuevaVistaProductos.txtNombreProducto.getText());
            nuevoProducto.setTipoPrenda(nuevaVistaProductos.comboTipoProducto.getSelectedItem().toString());
            nuevoProducto.setPrecio(Double.parseDouble(nuevaVistaProductos.txtPrecioProducto.getText()));
            nuevoProducto.setMarca(nuevaVistaProductos.comboMarcaProducto.getSelectedItem().toString());
            nuevoProducto.setTalla(nuevaVistaProductos.txtTallaProducto.getText());
            nuevoProducto.setGenero(nuevaVistaProductos.comboGeneroProducto.getSelectedItem().toString());
            nuevoProducto.setColor(nuevaVistaProductos.comboColorProducto.getSelectedItem().toString());
            nuevoProducto.setEstilo(nuevaVistaProductos.comboEstiloProducto.getSelectedItem().toString());
            nuevoProducto.setTemporada(nuevaVistaProductos.comboTemporadaProducto.getSelectedItem().toString());
            nuevoProducto.setDescripcion(nuevaVistaProductos.txtAreaDescripcionProducto.getText());
            nuevoProducto.setEstatus(nuevaVistaProductos.comboEstatusProducto.getSelectedItem().toString());
            nuevoProducto.setStock(Integer.parseInt(nuevaVistaProductos.txtStockProducto.getText()));
            
            //Se llama al metodo para registar al nuevo producto
            if(modeloProducto.registrarProducto(nuevoProducto)){
                //REGISTRO EXITOSO
                JOptionPane.showMessageDialog(null, "REGISTRO DE PRODUCTO EXITOSO", "REGISTRO EXITOSO", JOptionPane.INFORMATION_MESSAGE);
                llenarTablaProductos();
                limpiarFormularioProducto();
            }else{
                //ERROR DE REGISTRO
                JOptionPane.showMessageDialog(null, "REGISTRO DE PRODUCTO FALLIDO", "REGISTRO FALLIDO", JOptionPane.WARNING_MESSAGE);
            }
            
        }else if(objetoAccionEvento.getSource() == nuevaVistaProductos.btnBuscarProducto){
                                //********************************************************
                                //***************** BOTON BUSCAR PRODUCTO***********************
                                //********************************************************
            //Validar que el campo de id a buscar no este vacio
            if( !validarIdBuscar() ){
                //Se inicializa el atributo idProducto del objeto Producto
                nuevoProducto.setIdProducto(Integer.parseInt(nuevaVistaProductos.txtIdProductoBuscado.getText()));
                boolean resultadoBusqueda = modeloProducto.buscarProducto(nuevoProducto);
                if( resultadoBusqueda ){
                    //Si la busqueda es exitosa, se rellena el formulario con los atributos del objeto 
                    //nuevaVistaProductos.comboProveedorProducto.setSelectedItem(nuevoProducto.getNombre());

                    nuevaVistaProductos.txtIdProducto.setText(String.valueOf(nuevoProducto.getIdProducto()));

                            nuevaVistaProductos.comboProveedorProducto.setSelectedIndex(nuevoProducto.getIdProveedor());
                            nuevaVistaProductos.comboCategoriaProducto.setSelectedIndex(nuevoProducto.getIdCategoria());

                    nuevaVistaProductos.txtNombreProducto.setText(nuevoProducto.getNombre());
                    nuevaVistaProductos.comboTipoProducto.setSelectedItem(nuevoProducto.getTipoPrenda());
                    nuevaVistaProductos.txtPrecioProducto.setText(String.valueOf(nuevoProducto.getPrecio()));
                    nuevaVistaProductos.comboMarcaProducto.setSelectedItem(nuevoProducto.getMarca());
                    nuevaVistaProductos.txtTallaProducto.setText(nuevoProducto.getTalla());
                    nuevaVistaProductos.comboGeneroProducto.setSelectedItem(nuevoProducto.getGenero());
                    nuevaVistaProductos.comboColorProducto.setSelectedItem(nuevoProducto.getColor());
                    nuevaVistaProductos.comboEstiloProducto.setSelectedItem(nuevoProducto.getEstilo());
                    nuevaVistaProductos.comboTemporadaProducto.setSelectedItem(nuevoProducto.getTemporada());
                    nuevaVistaProductos.comboEstatusProducto.setSelectedItem(nuevoProducto.getEstatus());
                    nuevaVistaProductos.txtStockProducto.setText(String.valueOf(nuevoProducto.getStock()));
                    nuevaVistaProductos.txtAreaDescripcionProducto.setText(nuevoProducto.getDescripcion());

                    //Llenado de tabla
                    llenarTablaProductos(resultadoBusqueda);
                }else{
                    JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN PRODUCTO", "BUSQUEDA VACIA", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un id valido", "BUSQUEDA VACIA", JOptionPane.WARNING_MESSAGE);
            }
            
            
            
        }else if(objetoAccionEvento.getSource() == nuevaVistaProductos.btnActualizarProducto){
                                //********************************************************
                                //***************** BOTON ACTUALIZAR PRODUCTO***********************
                                //********************************************************
            //Se inicializan los atributos del objteto que se recibe como parametro
            
            nuevoProducto.setIdProducto(Integer.parseInt(nuevaVistaProductos.txtIdProductoBuscado.getText()));
            /**Rerdar que el combo box correspondiente a los proveedores se llena mediante un arreglo de objetos de tipo Proveedor
                * Por lo que se obtiene primero el item seleccionado (que es un objeto de tipo Proveedor)
                * Lo que se obtiene es un dato de tipo Object, por lo que se convierte a tipo Proveedor
                * Se almacena en el objeto auxiliar de tipo Proveedor
                */
            Proveedor proveAuxiliar = (Proveedor) nuevaVistaProductos.comboProveedorProducto.getSelectedItem();
            nuevoProducto.setIdProveedor(proveAuxiliar.getIdProveedor());
            Categoria categoriaAuxiliar = (Categoria) nuevaVistaProductos.comboCategoriaProducto.getSelectedItem();
            nuevoProducto.setIdCategoria(categoriaAuxiliar.getIdCategoria());

            nuevoProducto.setNombre(nuevaVistaProductos.txtNombreProducto.getText());
            nuevoProducto.setTipoPrenda(nuevaVistaProductos.comboTipoProducto.getSelectedItem().toString());
            nuevoProducto.setPrecio(Double.parseDouble(nuevaVistaProductos.txtPrecioProducto.getText()));
            nuevoProducto.setMarca(nuevaVistaProductos.comboMarcaProducto.getSelectedItem().toString());
            nuevoProducto.setTalla(nuevaVistaProductos.txtTallaProducto.getText());
            nuevoProducto.setGenero(nuevaVistaProductos.comboGeneroProducto.getSelectedItem().toString());
            nuevoProducto.setColor(nuevaVistaProductos.comboColorProducto.getSelectedItem().toString());
            nuevoProducto.setEstilo(nuevaVistaProductos.comboEstiloProducto.getSelectedItem().toString());
            nuevoProducto.setTemporada(nuevaVistaProductos.comboTemporadaProducto.getSelectedItem().toString());
            nuevoProducto.setDescripcion(nuevaVistaProductos.txtAreaDescripcionProducto.getText());
            nuevoProducto.setEstatus(nuevaVistaProductos.comboEstatusProducto.getSelectedItem().toString());
            nuevoProducto.setStock(Integer.parseInt(nuevaVistaProductos.txtStockProducto.getText()));
            
            if(modeloProducto.actualizarProducto(nuevoProducto)){
                //ACtualizacion correcta
                                   
               JOptionPane.showMessageDialog(null, "Actualizacion exitosa", "ACTUALIZACION EXITOSA", JOptionPane.INFORMATION_MESSAGE);
               llenarTablaProductos();
               limpiarFormularioProducto();
            }else{
                JOptionPane.showMessageDialog(null, "ACTUALIZACIÓN NO REALIZADA", "ACTUALIZACION FALLIDA", JOptionPane.WARNING_MESSAGE);
            }
             
            
            
        }else if(objetoAccionEvento.getSource() == nuevaVistaProductos.btnEliminarProducto){
                                //********************************************************
                                //***************** BOTON ELIMINAR PRODUCTO***********************
                                //********************************************************
            
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el producto?", "ELIMINAR PRODUCTO", JOptionPane.YES_NO_OPTION);
            
            //Si la respuesta es SI, el entero recibido es 0, caso contrario es 1
            if(respuesta == 0){
                nuevoProducto.setIdProducto(Integer.parseInt(nuevaVistaProductos.txtIdProducto.getText()));
                //SE PROCEDE A ELINIMNAR EL PRODUCTO
                if(modeloProducto.eliminarProducto(nuevoProducto)){
                    //ELIMINACION EXITOSA
                    JOptionPane.showMessageDialog(null, "Eliminacion exitosa", "ELIMINACION EXITOSA", JOptionPane.INFORMATION_MESSAGE);
                    llenarTablaProductos();
                    limpiarFormularioProducto();
                }else{
                    //Eliminacion fallida
                    JOptionPane.showMessageDialog(null, "ELIMINACION NO REALIZADA", "ELIMINACION FALLIDA", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                
            }
            
            
        }else if(objetoAccionEvento.getSource() == nuevaVistaProductos.btnLimpiarFormProducto){
            limpiarFormularioProducto();
            llenarTablaProductos();
        }
        
    } //Finaliza sobreescritura de métodos abstracto
    
    
    /**************************************************
     * ************************************************
     * INICIAN MÉTODOS DE LA CLASE
     * ************************************************
     * ************************************************
     */
    
    //Llenado de comboBox de proveedores
    private void llenarComboProveedores(){
        MetodosProveedor objModeloProveedor = new MetodosProveedor();
        //Modelo para comboBox de proveedores
        //el objeto << modeloComboProveedores >> recibirá un objeto de tipo vector con los proveedores existentes
        DefaultComboBoxModel modeloComboProveedores = new DefaultComboBoxModel(objModeloProveedor.mostrarComboProveedores()); //Requiere de import javax.swing.DefaultComboBoxModel;
        nuevaVistaProductos.comboProveedorProducto.setModel(modeloComboProveedores);
    }
    
            
    public void llenarComboProveedores(VentanaAdministrador nuevaVistaProductos){
        MetodosProveedor objModeloProveedor = new MetodosProveedor();
        //Modelo para comboBox de proveedores
        //el objeto << modeloComboProveedores >> recibirá un objeto de tipo vector con los proveedores existentes
        DefaultComboBoxModel modeloComboProveedores = new DefaultComboBoxModel(objModeloProveedor.mostrarComboProveedores()); //Requiere de import javax.swing.DefaultComboBoxModel;
        nuevaVistaProductos.comboProveedorProducto.setModel(modeloComboProveedores);
    }
    
    
    //Llenado de comboBox de CATEGORIAS
    private void llenarComboCategorias(){
        MetodosCategoria objModeloCategoria = new MetodosCategoria();
        //Creacion de modelo para combo BOX de categorias
        //El objeto << modeloComboCategorias >> recibirá un objeto de tipo vector con los proveedores existentes
        DefaultComboBoxModel modeloComboProveedores = new DefaultComboBoxModel(objModeloCategoria.mostrarComboCategoria());
        nuevaVistaProductos.comboCategoriaProducto.setModel(modeloComboProveedores);
    }
    
    
    //Llenado de tabla PRODUCTOS
    private void llenarTablaProductos(){
        //Se almacenan los registros de la tabla proveedores para mostrar en la tabla del formulario
        ResultSet tuplaProducto = modeloProducto.consultarTablaProductos();
        MetodosProveedor modeloProveedorAuxiliar = new MetodosProveedor();//Objeto necesario para realizar consultas de la tabla Proveedor
        MetodosCategoria modeloCategoriaAuxiliar = new MetodosCategoria();//Objeto necesario para realizar consultas de la tabla Categoria
        //MODELO PARA LA TABLA
        //El modelo servirá para añadir las filas directamente a la tablas
        DefaultTableModel modeloTablaProducto = new DefaultTableModel();
        nuevaVistaProductos.tablaProducto.setModel(modeloTablaProducto);
        
        //Agregar columnas a modelo tabla
        modeloTablaProducto.addColumn("ID");
        modeloTablaProducto.addColumn("Nombre");
        modeloTablaProducto.addColumn("Proveedor");
        modeloTablaProducto.addColumn("Categoria");
        modeloTablaProducto.addColumn("Precio");
        modeloTablaProducto.addColumn("Marca");
        modeloTablaProducto.addColumn("Color");
        modeloTablaProducto.addColumn("Estatus");
        modeloTablaProducto.addColumn("Stock");
        
        try {
            //Mientras existan registros 
            while(tuplaProducto.next()){
                //Se crea un arreglo de tipo objet para que acepte cualquier dato
                //Cada arreglo de fila[] tiene cuatro elementos (cuatro columnas)
                Object fila[] = new Object[9]; //En la instanciacion (new Object[9] se pone cuatro por los nueve elementos que son las 9 columnas
                //Se reccoren las 7 columnas del registro (fila o tupla)
                for(int i =0; i < 9 ; i++){
                    //Se almacenan los datos
                    /**En estos casos, el indice debe iniciar desde 1, 
                     * Si el indice inicia en 0, marcará el siguiente error: 
                     *                      java.sql.SQLException: Column Index out of range, 0 < 1  
                     */
                    //
                    //fila[i] = tuplaProducto.getObject(i+1); //se pone i+1 porque la primer que se quiere mostrar desde la BD es... 
                    /**
                     * PRUEBAS (BETA)
                     */
                    Proveedor proveedorBuscado = new Proveedor();
                    Categoria categoriaBuscada = new Categoria();
                    
                    //Llenado de columnas
                    switch (i){
                        case 2:
                            //Se busca el nombre del proveedor, y se coloca en la tabla dicho nombre en lugar del ID
                            proveedorBuscado.setIdProveedor((int) tuplaProducto.getObject(i+1));
                            if(modeloProveedorAuxiliar.buscarNombreProveedor(proveedorBuscado)){
                                //Si se encuantra el proveedor, se almacena su nombre en lugar de su ID
                                fila[i] = proveedorBuscado.getNombreProveedor();
                            }else{
                                fila[i] = "NO TIENE";
                            }
                            
                        break;
                        
                        case 3:
                            //Se busca el nombre de la categoria, y se coloca en la tabla dicho nombre en lugar del ID
                            
                            categoriaBuscada.setIdCategoria( (int) tuplaProducto.getObject(i+1) );
                            if( modeloCategoriaAuxiliar.buscarNombreCategoria(categoriaBuscada) ){
                                //Si se encuantra la categoria, se almacena su nombre en lugar de su ID
                                fila[i] = categoriaBuscada.getNombreCategoria();
                            }else{
                                fila[i] = "NO TIENE";
                            }
                            
                        break;
                        default:
                            fila[i] = tuplaProducto.getObject(i+1);
                        break;
                    }
                            
                }
                
                            
                modeloTablaProducto.addRow(fila); //Se añaden filas mientras sigan existiendo registros
            }
        } catch (Exception e) {
            System.err.println("ERROR EN EL LA IMPRESION DE LA TABLA PROVEEDORES: "+ e);
        }
        
    }
    
    ////Llenado de tabla PRODUCTOS Sobrecarga de funcion
    public void llenarTablaProductos(VentanaAdministrador nuevaVistaProductos){
        //Se almacenan los registros de la tabla proveedores para mostrar en la tabla del formulario
        MetodosProducto modeloProducto = new MetodosProducto();
        ResultSet tuplaProducto = modeloProducto.consultarTablaProductos();
        MetodosProveedor modeloProveedorAuxiliar = new MetodosProveedor();//Objeto necesario para realizar consultas de la tabla Proveedor
        MetodosCategoria modeloCategoriaAuxiliar = new MetodosCategoria();//Objeto necesario para realizar consultas de la tabla Categoria
        //MODELO PARA LA TABLA
        //El modelo servirá para añadir las filas directamente a la tablas
        DefaultTableModel modeloTablaProducto = new DefaultTableModel();
        nuevaVistaProductos.tablaProducto.setModel(modeloTablaProducto);
        
        //Agregar columnas a modelo tabla
        modeloTablaProducto.addColumn("ID");
        modeloTablaProducto.addColumn("Nombre");
        modeloTablaProducto.addColumn("Proveedor");
        modeloTablaProducto.addColumn("Categoria");
        modeloTablaProducto.addColumn("Precio");
        modeloTablaProducto.addColumn("Marca");
        modeloTablaProducto.addColumn("Color");
        modeloTablaProducto.addColumn("Estatus");
        modeloTablaProducto.addColumn("Stock");
        
        try {
            //Mientras existan registros 
            while(tuplaProducto.next()){
                //Se crea un arreglo de tipo objet para que acepte cualquier dato
                //Cada arreglo de fila[] tiene cuatro elementos (cuatro columnas)
                Object fila[] = new Object[9]; //En la instanciacion (new Object[9] se pone cuatro por los nueve elementos que son las 9 columnas
                //Se reccoren las 7 columnas del registro (fila o tupla)
                for(int i =0; i < 9 ; i++){
                    //Se almacenan los datos
                    /**En estos casos, el indice debe iniciar desde 1, 
                     * Si el indice inicia en 0, marcará el siguiente error: 
                     *                      java.sql.SQLException: Column Index out of range, 0 < 1  
                     */
                    //
                    //fila[i] = tuplaProducto.getObject(i+1); //se pone i+1 porque la primer que se quiere mostrar desde la BD es... 
                    /**
                     * PRUEBAS (BETA)
                     */
                    Proveedor proveedorBuscado = new Proveedor();
                    Categoria categoriaBuscada = new Categoria();
                    
                    //Llenado de columnas
                    switch (i){
                        case 2:
                            //Se busca el nombre del proveedor, y se coloca en la tabla dicho nombre en lugar del ID
                            proveedorBuscado.setIdProveedor((int) tuplaProducto.getObject(i+1));
                            if(modeloProveedorAuxiliar.buscarNombreProveedor(proveedorBuscado)){
                                //Si se encuantra el proveedor, se almacena su nombre en lugar de su ID
                                fila[i] = proveedorBuscado.getNombreProveedor();
                            }else{
                                fila[i] = "NO TIENE";
                            }
                            
                        break;
                        
                        case 3:
                            //Se busca el nombre de la categoria, y se coloca en la tabla dicho nombre en lugar del ID
                            
                            categoriaBuscada.setIdCategoria( (int) tuplaProducto.getObject(i+1) );
                            if( modeloCategoriaAuxiliar.buscarNombreCategoria(categoriaBuscada) ){
                                //Si se encuantra la categoria, se almacena su nombre en lugar de su ID
                                fila[i] = categoriaBuscada.getNombreCategoria();
                            }else{
                                fila[i] = "NO TIENE";
                            }
                            
                        break;
                        default:
                            fila[i] = tuplaProducto.getObject(i+1);
                        break;
                    }
                            
                }
                
                            
                modeloTablaProducto.addRow(fila); //Se añaden filas mientras sigan existiendo registros
            }
        } catch (Exception e) {
            System.err.println("ERROR EN EL LA IMPRESION DE LA TABLA PROVEEDORES: "+ e);
        }
        
    }
    
    // llenado de tabla con el producto ubscado
    private void llenarTablaProductos(boolean resultado){
        if(resultado){
                //Se almacenan los registros de la tabla proveedores para mostrar en la tabla del formulario
                        //ResultSet tuplaProducto = modeloProducto.consultarTablaProductos();
            MetodosProveedor modeloProveedorAuxiliar = new MetodosProveedor();//Objeto necesario para realizar consultas de la tabla Proveedor
            MetodosCategoria modeloCategoriaAuxiliar = new MetodosCategoria();//Objeto necesario para realizar consultas de la tabla Categoria
            //MODELO PARA LA TABLA
            //El modelo servirá para añadir las filas directamente a la tablas
            DefaultTableModel modeloTablaProducto = new DefaultTableModel();
            nuevaVistaProductos.tablaProducto.setModel(modeloTablaProducto);

            //Agregar columnas a modelo tabla
            modeloTablaProducto.addColumn("ID");
            modeloTablaProducto.addColumn("Nombre");
            modeloTablaProducto.addColumn("Proveedor");
            modeloTablaProducto.addColumn("Categoria");
            modeloTablaProducto.addColumn("Precio");
            modeloTablaProducto.addColumn("Marca");
            modeloTablaProducto.addColumn("Color");
            modeloTablaProducto.addColumn("Estatus");
            modeloTablaProducto.addColumn("Stock");

            Proveedor proveedorBuscado = new Proveedor();
            Categoria categoriaBuscada = new Categoria();

            proveedorBuscado.setIdProveedor(nuevoProducto.getIdProveedor()); //Se inicializa el atributo id del objeto proveedor
            modeloProveedorAuxiliar.buscarNombreProveedor(proveedorBuscado); //Se buscan los datos del proveedor y se inicializan 

            categoriaBuscada.setIdCategoria(nuevoProducto.getIdCategoria());
            modeloCategoriaAuxiliar.buscarNombreCategoria(categoriaBuscada);
            //Creacion de fila de tipo Object
            Object fila[] = {nuevoProducto.getIdProducto(), nuevoProducto.getNombre(), proveedorBuscado.getNombreProveedor(), categoriaBuscada.getNombreCategoria(), nuevoProducto.getPrecio(), nuevoProducto.getMarca(), nuevoProducto.getColor(), nuevoProducto.getEstatus(), nuevoProducto.getStock()};
            modeloTablaProducto.addRow(fila);
        }else{
            
        }
       
        
    }
    
    private void limpiarFormularioProducto(){
        nuevaVistaProductos.txtIdProducto.setText(null);
        nuevaVistaProductos.txtIdProductoBuscado.setText(null);
        nuevaVistaProductos.comboProveedorProducto.setSelectedItem(null);
        nuevaVistaProductos.comboCategoriaProducto.setSelectedItem(null);
        nuevaVistaProductos.txtNombreProducto.setText(null);
        nuevaVistaProductos.comboTipoProducto.setSelectedItem(null);
        nuevaVistaProductos.txtPrecioProducto.setText(null);
        nuevaVistaProductos.comboMarcaProducto.setSelectedItem(null);
        nuevaVistaProductos.txtTallaProducto.setText(null);
        nuevaVistaProductos.comboGeneroProducto.setSelectedItem(null);
        nuevaVistaProductos.comboColorProducto.setSelectedItem(null);
        nuevaVistaProductos.comboEstiloProducto.setSelectedItem(null);
        nuevaVistaProductos.comboTemporadaProducto.setSelectedItem(null);
        nuevaVistaProductos.comboEstatusProducto.setSelectedItem(null);
        nuevaVistaProductos.txtStockProducto.setText(null);
        nuevaVistaProductos.txtAreaDescripcionProducto.setText(null);
    }
    
    private boolean validarIdBuscar(){
        boolean validarId = nuevaVistaProductos.txtIdProductoBuscado.getText().isEmpty();
        return validarId;
    }
    
    
}//FINALIZA LA CLASE
