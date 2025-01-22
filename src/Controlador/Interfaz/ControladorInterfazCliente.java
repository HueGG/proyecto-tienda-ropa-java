/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Interfaz;

import Modelo.Categoria.Categoria;
import Modelo.Categoria.MetodosCategoria;
import Modelo.Cliente.CifrarPassword;
import Modelo.Cliente.Cliente;
import Modelo.Cliente.MetodosCliente;
import Modelo.Producto.MetodosProducto;
import Modelo.Producto.Producto;
import Modelo.Proveedor.MetodosProveedor;
import Modelo.Proveedor.Proveedor;
import Modelo.Venta.MetodosVenta;
import Modelo.Venta.Venta;
import Vista.VentanaCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS-TUF
 */
public class ControladorInterfazCliente implements ActionListener{
    //Objetos atributos de la clase
    
    private VentanaCliente nuevaVistaCliente; //Objeto de la vista de interfaz cliente
    //Objetos para manipular los datos del cliente
    private Cliente nuevoCliente;
    private MetodosCliente modeloCliente;
    
    //Objetos para manipular los datos de los productos
    private Producto nuevoProducto;
    private MetodosProducto modeloProducto;
    
    //Objetos para manipular las ventas
    private Venta nuevaVenta;
    private MetodosVenta modeloVenta;
    
    //**********************************************************
    //************* Metodo constructor
    //**********************************************************

    public ControladorInterfazCliente(VentanaCliente nuevaVistaCliente, Cliente nuevoCliente, MetodosCliente modeloCliente, Producto nuevoProducto, MetodosProducto modeloProducto, Venta nuevaVenta, MetodosVenta modeloVenta) {
        this.nuevaVistaCliente = nuevaVistaCliente;
        this.nuevoCliente = nuevoCliente;
        this.modeloCliente = modeloCliente;
        
        this.nuevoProducto = nuevoProducto;
        this.modeloProducto = modeloProducto;
        
        this.nuevaVenta = nuevaVenta;
        this.modeloVenta = modeloVenta;
        
        //Metodos a llamar desde que se inicia la interfaz del cliente
        llenarTablaProductoCliente();  //Se llena la tabla de productos que el cliente puede comprar
        llenarTablaComprasCliente();
        nuevaVistaCliente.btnComprar.setEnabled(false);//Al no haber un producto seleccionado al principio, se deshabilita el boton de compra
        
        
        
        //Se indica donde se utilizar el ActionListener
        nuevaVistaCliente.btnActualizarTablaProductoCliente.addActionListener(this);
        nuevaVistaCliente.btnComprar.addActionListener(this);
        nuevaVistaCliente.btnActualizarCliente.addActionListener(this);
        
        /**
         * Codigo temporal de prueba
         * Llenado de formulario con datos del cliente
         */
        
        nuevaVistaCliente.txtIdCliente.setText(String.valueOf(nuevoCliente.getIdCliente()));
        nuevaVistaCliente.txtNombreCompleto.setText( nuevoCliente.getNombre()+" "+nuevoCliente.getApePaterno()+ " "+ nuevoCliente.getApeMaterno());
        nuevaVistaCliente.txtCorreo.setText( nuevoCliente.getEmail() );
        //nuevaVistaCliente.txtPassword.setText( nuevoCliente.getPassword() );
        nuevaVistaCliente.txtTelefono.setText( nuevoCliente.getTelefono() );
        nuevaVistaCliente.txtAreaDireccion.setText( nuevoCliente.getDireccion() );
    }
    
    
    
    //**********************************************************
    //Función que despliega la interfaz del cliente
    //**********************************************************
    public void iniciarInterfazCliente(){
        nuevaVistaCliente.setTitle("Interfaz del cliente");
        nuevaVistaCliente.setVisible(true);
        nuevaVistaCliente.setLocationRelativeTo(null);
        
    }
    
    
    
    
    
    /** =====================================================================================
     * INICIA LA IMPLEMENTACION DEL MÉTODO ABSTRATO << actionPerformed(ActionEvent ae)  >>
     *  =====================================================================================
     */
    @Override
    public void actionPerformed(ActionEvent objetoAccionEvento) {
        //Pendiente
        if( objetoAccionEvento.getSource() == nuevaVistaCliente.btnActualizarTablaProductoCliente ){
                                //********************************************************
                                //***************** BOTON de Actualizazion de tabla de productos ***********************
                                //********************************************************
            llenarTablaProductoCliente();
            limpiarFormularioProductoCompra();
            
        }else if ( objetoAccionEvento.getSource() == nuevaVistaCliente.btnComprar ){
                                //********************************************************
                                //***************** BOTON de COMPRA ***********************
                                //********************************************************
            double precioFinal = (double) nuevaVistaCliente.txtPrecioFinal.getValue();
            //Se inicializan los atributos de l objeto << nuevaVenta >>
            nuevaVenta.setIdCliente(Integer.parseInt(nuevaVistaCliente.txtIdCliente.getText()));
            nuevaVenta.setIdProducto(Integer.parseInt(nuevaVistaCliente.txtIdProducto.getText()));
            nuevaVenta.setCantidad(Integer.parseInt(nuevaVistaCliente.txtCantidadComprar.getText()));
            nuevaVenta.setPrecioTotal(precioFinal);
            
            if ( validarStockProducto() ){
                //STOCK DISPONIBLE
                if( validarActualizacionStock() ){
                    //Se actualiza el stock del producto
                    
                    //Ya se ha comprobado el stock y actualizado el stock, por lo que se procede con el registro de la venta
                    if( modeloVenta.registrarVenta(nuevaVenta) ){
                        //VENTA EXITOSA
                        JOptionPane.showMessageDialog(null, "VENTA EXITOSA", "COMPRA exitoso", JOptionPane.INFORMATION_MESSAGE);
                        llenarTablaProductoCliente();
                        llenarTablaComprasCliente();
                    }else{
                        //Si el valor de retorno es FALSE
                        //Mensaje de error si no se realiza el registro
                        JOptionPane.showMessageDialog(null, "ERROR AL REALIZAR LA COMPRA", "ERROR DE REGISTRO DE COMPRA", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    //Error al actualizar el stock del producto
                    JOptionPane.showMessageDialog(null, "ERROR de STOCK", "ERROR DE REGISTRO DE COMPRA", JOptionPane.ERROR_MESSAGE);
                }
                
                
            }else{
                //Stock no suficiente
                JOptionPane.showMessageDialog(null, "STOCK INSUFICIENTE", "Lo sentimos, pero no contamos con la cantidad que nos ha solicitado", JOptionPane.ERROR_MESSAGE);
            }
            
            
            
            limpiarFormularioProductoCompra();
            
            System.out.println("PRECIO FINAL: "+ precioFinal);
        }else if ( objetoAccionEvento.getSource() == nuevaVistaCliente.btnActualizarCliente ){
                                //********************************************************
                                //***************** BOTON de ACTUALIZAR DATOS DEL CLIENTE ***********************
                                //********************************************************
                                
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea actualizar su inforacion?", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
            if( respuesta == 0 ){
                //Si el formulario esta lleno, se continua con la actualizacion
                if (validarFormularioCliente()){
                    nuevoCliente.setPassword(cifrarPassword());
                    nuevoCliente.setTelefono(nuevaVistaCliente.txtTelefono.getText());
                    nuevoCliente.setDireccion(nuevaVistaCliente.txtAreaDireccion.getText());

                    if( modeloCliente.actualizarDatosCliente(nuevoCliente) ){
                        JOptionPane.showMessageDialog(null, "Actualizacion correcta", "ACTUALIZACION EXITOSA", JOptionPane.INFORMATION_MESSAGE);
                        
                        llenarFormularioCliente();
                    }else{
                        JOptionPane.showMessageDialog(null, "Actualizacion fallida", "ERROR AL ACTUALIZAR", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    //No se continua con la actalizaxon
                    JOptionPane.showMessageDialog(null, "Llene todos los campos", "Campos vacios", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                
            }               
            
                                
        }
    }
    
    
     /**************************************************
     * ************************************************
     * INICIAN MÉTODOS DE LA CLASE
     * ************************************************
     * ************************************************
     */
    
    
    //Llenado de tabla de productos en la interfaz del cliente
    //El llenado es con todos los productos existentes
    private void llenarTablaProductoCliente(){
        //Se almacenan los registros de la tabla productos para mostrar en la tabla del formulario
        ResultSet tuplaProducto = modeloProducto.consultarTablaProductosCliente();//Se realiza la consultaa de los productos existentes y se almacenan en un objeto ResultSet llamado << tuplaCompra >>
        //MetodosProveedor modeloProductoAuxiliar = new MetodosProveedor();//Objeto necesario para realizar consultas de la tabla Proveedor
        MetodosCategoria modeloCategoriaAuxiliar = new MetodosCategoria();//Objeto necesario para realizar consultas de la tabla Categoria
        
        //MODELO PARA LA TABLA
        //El modelo servirá para añadir las filas directamente a la tablas
        DefaultTableModel modeloTablaProducto = new DefaultTableModel();
        nuevaVistaCliente.tablaProductoCliente.setModel(modeloTablaProducto);
        
        //Agregar columnas a modelo tabla
        modeloTablaProducto.addColumn("ID");
        modeloTablaProducto.addColumn("Nombre");
        //modeloTablaProducto.addColumn("Proveedor");
        modeloTablaProducto.addColumn("Categoria");
        modeloTablaProducto.addColumn("Precio");
        modeloTablaProducto.addColumn("Marca");
        modeloTablaProducto.addColumn("Color");
        modeloTablaProducto.addColumn("Estatus");
        modeloTablaProducto.addColumn("Stock");
        
        
        try {
            //Mientras existan registros en el objeto tuplaCompra
            while(tuplaProducto.next()){
                //Se crea un arreglo de tipo objet para que acepte cualquier dato
                //Cada arreglo de fila[] tiene cuatro elementos (cuatro columnas)
                Object fila[] = new Object[8]; //En la instanciacion (new Object[8] se pone cuatro por los 8 elementos que son las 8 columnas
                //Se reccoren las  8 columnas del registro (fila o tupla)
                for(int i =0; i < 8 ; i++){
                    //Se almacenan los datos
                    /**En estos casos, el indice debe iniciar desde 1, 
                     * Si el indice inicia en 0, marcará el siguiente error: 
                     *                      java.sql.SQLException: Column Index out of range, 0 < 1  
                     */
                    //
                    //fila[i] = tuplaCompra.getObject(i+1); //se pone i+1 porque la primer que se quiere mostrar desde la BD es... 
                    /**
                     * PRUEBAS (BETA)
                     */
                    //Proveedor productoBuscado = new Proveedor();
                    Categoria categoriaBuscada = new Categoria();
                    
                    //Llenado de columnas
                    switch (i){
                        /*
                        case 2:
                            //Se busca el nombre del proveedor, y se coloca en la tabla dicho nombre en lugar del ID
                            productoBuscado.setIdProveedor((int) tuplaCompra.getObject(i+1));
                            if(modeloProductoAuxiliar.buscarNombreProveedor(productoBuscado)){
                                //Si se encuantra el proveedor, se almacena su nombre en lugar de su ID
                                fila[i] = productoBuscado.getNombreProveedor();
                            }else{
                                fila[i] = "NO TIENE";
                            }
                            
                        break;
                        */
                        
                        case 2:
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
    //Fin de llenado de tabla de productos
    
    //Validacion de stock
    private boolean validarStockProducto(){
        int stockActualizado;
        //Se consulta el producto que se comprará
        nuevoProducto.setIdProducto(Integer.parseInt(nuevaVistaCliente.txtIdProducto.getText())); //Se obtiene id del producto
        //Se busca producto y se inicializan los atributos dell producto buscado
        if ( modeloProducto.buscarProducto(nuevoProducto) ){
            //Si la busqueda es exitosa, si inicializan los atributos del producto
            //Se valida que haya stock disponiblle
            stockActualizado = nuevoProducto.getStock() - Integer.parseInt(nuevaVistaCliente.txtCantidadComprar.getText()); //Se resta al stock actual la cantidad que se desea comprar
            if ( stockActualizado >= 0 ){
                //La cantidad a comprar esta disponible en stock
                
                if(stockActualizado == 0){
                    nuevoProducto.setEstatus("No disponible"); // Si el stock queda en 0, se actualiza el estatus del producto (Cambia el estatus del producto)
                }else{
                    //Si el estock no es igual a 0, no se actualiza el estatus (se mantiene disponible)
                }
                //Se actualiza el stock
                nuevoProducto.setStock(stockActualizado);
                return true; // SI HAY STOCK DISPONIBLE Y LA COMPRA CONTINUA
            }else{
                return false; // NO HAY STOCK DISPONIBLE
            }
            
        }else{
            return false; //PRODUCTO NO ENCONTRADO
        }
    }
    
    private boolean validarActualizacionStock(){
        if (modeloProducto.actualizarStockProducto(nuevoProducto)){
            //Si la actualizacion es correcta, se puede continuar con la compra
            return true;
        }else{
            return false;
        }
    }
    
    
    //Llenar tabla de compras realizadas por un cliente
    private void llenarTablaComprasCliente(){
        
        //Se almacenan los registros de la tabla proveedores para mostrar en la tabla del formulario
        ResultSet tuplaCompra = modeloVenta.consultarTablaVentasPorCliente(nuevoCliente);
        MetodosProducto modeloProductoAuxiliar = new MetodosProducto();//Objeto necesario para realizar consultas de la tabla Producto
        
        //MODELO PARA LA TABLA
        //El modelo servirá para añadir las filas directamente a la tablas
        DefaultTableModel modeloTablaCompraCliente = new DefaultTableModel();
        nuevaVistaCliente.tablaCompraCliente.setModel(modeloTablaCompraCliente);
        
        //Agregar columnas a modelo tabla
        modeloTablaCompraCliente.addColumn("ID de compra");
        modeloTablaCompraCliente.addColumn("Producto");
        modeloTablaCompraCliente.addColumn("Cantidad");
        modeloTablaCompraCliente.addColumn("Precio total");
        modeloTablaCompraCliente.addColumn("Fecha de compra");
        modeloTablaCompraCliente.addColumn("Fecha de entrega");
        
        
        try {
            //Mientras existan registros 
            while(tuplaCompra.next()){
                //Se crea un arreglo de tipo objet para que acepte cualquier dato
                //Cada arreglo de fila[] tiene cuatro elementos (cuatro columnas)
                Object fila[] = new Object[6]; //En la instanciacion (new Object[6] se pone 6 por los 6 elementos que son las 6 columnas
                //Se reccoren las 6 columnas del registro (fila o tupla)
                for(int i =0; i < 6 ; i++){
                    //Se almacenan los datos
                    /**En estos casos, el indice debe iniciar desde 1, 
                     * Si el indice inicia en 0, marcará el siguiente error: 
                     *                      java.sql.SQLException: Column Index out of range, 0 < 1  
                     */
                    //
                    //fila[i] = tuplaCompra.getObject(i+1); //se pone i+1 porque la primer que se quiere mostrar desde la BD es... 
                    /**
                     * PRUEBAS (BETA)
                     */
                    Producto productoBuscado = new Producto();
                    
                    //Llenado de columnas
                    switch (i){
                        case 1:
                            //Se busca el nombre del proveedor, y se coloca en la tabla dicho nombre en lugar del ID
                            productoBuscado.setIdProducto((int) tuplaCompra.getObject(i+1));
                            if(modeloProductoAuxiliar.buscarNombreProducto(productoBuscado)){
                                //Si se encuantra el proveedor, se almacena su nombre en lugar de su ID
                                fila[i] = productoBuscado.getNombre();
                            }else{
                                fila[i] = "PRODUCTO DESCONOCIDO";
                            }
                            
                        break;
                        
                        
                        default:
                            fila[i] = tuplaCompra.getObject(i+1);
                        break;
                    }
                            
                }
                
                            
                modeloTablaCompraCliente.addRow(fila); //Se añaden filas mientras sigan existiendo registros
            }
        } catch (Exception e) {
            System.err.println("ERROR EN EL LA IMPRESION DE LA TABLA DE COPRAS DEL CLIENTE: "+ e);
        }
        
    }
    
    
    //METODO PARA LIMPIAR EL FORMULARIO
    private void limpiarFormularioProductoCompra(){
        nuevaVistaCliente.txtIdProducto.setText(null);
        nuevaVistaCliente.txtNombreProducto.setText(null);
        nuevaVistaCliente.txtMarcaProducto.setText(null);
        nuevaVistaCliente.txtColorProducto.setText(null);
        nuevaVistaCliente.txtPrecioUnitarioProducto.setText(null);
        nuevaVistaCliente.txtEstatusProducto.setText(null);
        nuevaVistaCliente.txtStockProducto.setText(null);
        nuevaVistaCliente.txtCantidadComprar.setText(null);
        nuevaVistaCliente.txtPrecioFinal.setValue(null);
        
        nuevaVistaCliente.btnComprar.setEnabled(false);
    }
    
    
    //Metodo para cifrar contraseña
    private String cifrarPassword(){
        String password = nuevaVistaCliente.txtPassword.getText();
        String nuevaContrasena = CifrarPassword.md5(password); //Se cifra la contraseña
        return nuevaContrasena; //Se retorna la contraseña cifrada
    }
    
    private boolean validarFormularioCliente(){
        //Los valores son verdaderos si se encuentran vacios
        boolean validarPassword = nuevaVistaCliente.txtPassword.getText().isEmpty();
        boolean validarTelefono = nuevaVistaCliente.txtTelefono.getText().isEmpty();
        boolean validarDireccion = nuevaVistaCliente.txtAreaDireccion.getText().isEmpty();
        
        //Si al menos un campo es verdader (esta vacio) se retorna un false
        if ( validarPassword || validarTelefono || validarDireccion ){
            return false;
        }else{
            return true; //Se retorna true si todos los campos estan llenos
        }
    }
    
    
    private void llenarFormularioCliente(){
        nuevaVistaCliente.txtIdCliente.setText(String.valueOf(nuevoCliente.getIdCliente()));
        nuevaVistaCliente.txtNombreCompleto.setText( nuevoCliente.getNombre()+" "+nuevoCliente.getApePaterno()+ " "+ nuevoCliente.getApeMaterno());
        nuevaVistaCliente.txtCorreo.setText( nuevoCliente.getEmail() );
        //nuevaVistaCliente.txtPassword.setText( nuevoCliente.getPassword() );
        nuevaVistaCliente.txtPassword.setText(null);
        nuevaVistaCliente.txtTelefono.setText( nuevoCliente.getTelefono() );
        nuevaVistaCliente.txtAreaDireccion.setText( nuevoCliente.getDireccion() );
    }
}
