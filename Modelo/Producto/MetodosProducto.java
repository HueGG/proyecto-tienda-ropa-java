/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Producto;

import Modelo.ConexionTiendaBD;
import Modelo.Proveedor.Proveedor;
import Vista.VentanaAdministrador;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS-TUF
 */
public class MetodosProducto extends ConexionTiendaBD{
                    
    PreparedStatement ps;
    ResultSet rs;
    
    //*****************************************************
    //***************  Método de REGISTRAR PRODUCTO
    //*****************************************************
    public boolean registrarProducto( Producto nuevoProducto ){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection(); //Recordando que getConnection es una funcion heredad de la clase ConexionTiendaBD
        
        try {
            //Sentencia SQL para inserción de un producto
            ps = nuevaConexion.prepareStatement("INSERT INTO producto (idProveedor, idCategoria, nombre, tipoPrenda, "
                + "precio, marca, talla, genero, color, estilo, temporada, descripcion, estatus, stock) VALUES "
                + "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            //ps.setString(1, String.valueOf(nuevoProducto.getIdProveedor()));
            //ps.setString(2, String.valueOf(nuevoProducto.getIdCategoria()));
            
            ps.setInt(1, nuevoProducto.getIdProveedor());
            ps.setInt(2, nuevoProducto.getIdCategoria());
            
            ps.setString(3, nuevoProducto.getNombre());
            ps.setString(4, nuevoProducto.getTipoPrenda());
            ps.setDouble(5, nuevoProducto.getPrecio());
            ps.setString(6, nuevoProducto.getMarca());
            ps.setString(7, nuevoProducto.getTalla());
            ps.setString(8, nuevoProducto.getGenero());
            ps.setString(9, nuevoProducto.getColor());
            ps.setString(10, nuevoProducto.getEstilo());
            ps.setString(11, nuevoProducto.getTemporada());
            ps.setString(12, nuevoProducto.getDescripcion());
            ps.setString(13, nuevoProducto.getEstatus());
            ps.setInt(14, nuevoProducto.getStock());
            //ps.setString(14, String.valueOf(nuevoProducto.getStock()));
            
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                //Registro exitoso
                return true;
            }else{
                //Registro fallido
                return false;
            }
        } catch (Exception e) {
            
            System.err.println("ERROR AL REGISTRAR EL PRODUCTO: "+ e);
            return false;
        }finally{
            try {
                //Se intenta cerrar lla conexion con la BD
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexion con la BD: "+ e);
            }
        }
        
    }
    
    
    //*****************************************************
    //***************  Método de BUSCAR PRODUCTO
    //*****************************************************
    public boolean buscarProducto(Producto nuevoProducto){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection();
        
        try {
            //Sentencia SQL para la busqueda de un  producto
            ps = nuevaConexion.prepareStatement("SELECT * FROM producto WHERE idProducto = ?");
            /**Si bien, el atributo idProducto es un entero, para la sentencia SQL se le debe de mandar como una cadena de caracteres
             * de  lo contrario no se ejecutara la sentencia o consulta
             */
            //ps.setString(1, String.valueOf(nuevoProducto.getIdProducto()));
            ps.setInt(1, nuevoProducto.getIdProducto());
            /**Al realizarse una busqueda, se pretende obtner un registro como resultado, por lo que
             * se debe de almacenar en un objeto de tipo ResultSet
             * para después inicializar los atributos del objeto que se recibe como parametro-
             * 
             * Como se obtiene un valor de regreso, se utilia la funcion executeQuery()
             */
            rs = ps.executeQuery();
            
            if(rs.next()){
                //Si la busqueda es exitosa
                //Inicializacion de los atributos del objeto que se recibe como parametro
                nuevoProducto.setIdProveedor(rs.getInt("idProveedor"));
                nuevoProducto.setIdCategoria(rs.getInt("idCategoria"));
                nuevoProducto.setNombre(rs.getString("nombre"));
                nuevoProducto.setTipoPrenda(rs.getString("tipoPrenda"));
                nuevoProducto.setPrecio(rs.getDouble("precio"));
                nuevoProducto.setMarca(rs.getString("marca"));
                nuevoProducto.setTalla(rs.getString("talla"));
                nuevoProducto.setGenero(rs.getString("genero"));
                nuevoProducto.setColor(rs.getString("color"));
                nuevoProducto.setEstilo(rs.getString("estilo"));
                nuevoProducto.setTemporada(rs.getString("temporada"));
                nuevoProducto.setDescripcion(rs.getString("descripcion"));
                nuevoProducto.setEstatus(rs.getString("estatus"));
                nuevoProducto.setStock(rs.getInt("stock"));
                
                return true;
            }else{
                //Si la consulta no devuelve nada
                return false;
            }            
            
            
        } catch (Exception e) {
            System.err.println("ERROR AL REALIZAR LA BUSQUEDA:  "+ e);
            return false;
        }finally{
            try {
                //se cierra la conexion con la BD
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("ERROR AL CERRAR LA CONEXION CON LA BD "+ e);
            }
        }
        
        
    }
    
    
    //*****************************************************
    //***************  Método de ACTUALIZAR PRODUCTO
    //*****************************************************
    
    public boolean actualizarProducto( Producto nuevoProducto ){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection(); //Recordando que getConnection es una funcion heredad de la clase ConexionTiendaBD
        
        try {
            //Sentencia SQL para ACTUALIZACION de un producto
            ps = nuevaConexion.prepareStatement("UPDATE producto SET idProveedor = ?, idCategoria = ?, nombre = ?, tipoPrenda = ?, "
                + "precio = ?, marca = ?, talla = ?, genero = ?, color = ?, estilo = ?, temporada = ?, descripcion = ?, estatus = ?, stock = ? "
                    + "WHERE idProducto = ? ");
            
            ps.setInt(1, nuevoProducto.getIdProveedor());
            ps.setInt(2, nuevoProducto.getIdCategoria());
                    //ps.setString(1, String.valueOf(nuevoProducto.getIdProveedor()));
                    //ps.setString(2, String.valueOf(nuevoProducto.getIdCategoria()));
            
            ps.setString(3, nuevoProducto.getNombre());
            ps.setString(4, nuevoProducto.getTipoPrenda());
            ps.setDouble(5, nuevoProducto.getPrecio());
            ps.setString(6, nuevoProducto.getMarca());
            ps.setString(7, nuevoProducto.getTalla());
            ps.setString(8, nuevoProducto.getGenero());
            ps.setString(9, nuevoProducto.getColor());
            ps.setString(10, nuevoProducto.getEstilo());
            ps.setString(11, nuevoProducto.getTemporada());
            ps.setString(12, nuevoProducto.getDescripcion());
            ps.setString(13, nuevoProducto.getEstatus());
            
            ps.setInt(14, nuevoProducto.getStock());
                    //ps.setString(14, String.valueOf(nuevoProducto.getStock()));
            
            ps.setInt(15, nuevoProducto.getIdProducto());
                    //ps.setString(15, String.valueOf(nuevoProducto.getIdProducto()));
            
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                //Registro exitoso
                return true;
            }else{
                //Registro fallido
                return false;
            }
        } catch (Exception e) {
            
            System.err.println("ERROR AL REGISTRAR EL PRODUCTO: "+ e);
            return false;
        }finally{
            try {
                //Se intenta cerrar lla conexion con la BD
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexion con la BD: "+ e);
            }
        }
        
    }
    
    
    //*****************************************************
    //***************  Método de ACTUALIZAR STOCK PRODUCTO
    //*****************************************************
    public boolean actualizarStockProducto( Producto nuevoProducto ){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection(); //Recordando que getConnection es una funcion heredad de la clase ConexionTiendaBD
        
        try {
            
            //Sentencia SQL para ACTUALIZACION de un producto
            ps = nuevaConexion.prepareStatement("UPDATE producto SET estatus = ?, stock = ? WHERE idProducto = ? ");
            ps.setString(1, nuevoProducto.getEstatus());
            ps.setInt(2, nuevoProducto.getStock());
                //ps.setString(14, String.valueOf(nuevoProducto.getStock()));
            ps.setInt(3, nuevoProducto.getIdProducto());
                    //ps.setString(15, String.valueOf(nuevoProducto.getIdProducto()));
            
            
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                //Registro exitoso
                return true;
            }else{
                //Registro fallido
                return false;
            }
        } catch (Exception e) {
            
            System.err.println("ERROR AL ACTUALIZAR STOCK DEL PRODUCTO: "+ e);
            return false;
        }finally{
            try {
                //Se intenta cerrar lla conexion con la BD
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexion con la BD: "+ e);
            }
        }
        
    }
    
    
    
    
    
    
    //*****************************************************
    //***************  Método de ELIMINAR PRODUCTO
    //*****************************************************
    public boolean eliminarProducto(Producto nuevoProducto){
        //Se abre una conexión con la BD
        Connection nuevaConexion = getConnection();
        
        try {
            //Se prepara la sentencia SQL para la eliminación de un producto de la BD
            ps = nuevaConexion.prepareStatement("DELETE FROM producto WHERE idProducto = ?");
            ps.setString(1, String.valueOf(nuevoProducto.getIdProducto()));
            /**La ejecucion de la sentencia para elimnar un producto no devuelve ningun registro, sino solo
             * un valor entero que indica se la sentencia se ejecuto correctamente o no
             */
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                //Borrado exitoso;
                return true;
            }else{
                //No se elimino ningun registro
                return false;
            }
            
            
        } catch (Exception e) {
            System.err.println("ERROR EN LA ELIMINACION DE PRODUCTO: "+ e);
            return false;
        }finally{
            try {
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("ERROR AL CERRAR LA CONEXION: " + e);
            }
        }
    }
    
    //*****************************************************
    //***************  Método para LLENAR TABLA PRODUCTOS
    //*****************************************************
    
    //Metodo para llenar tabla de productos en la interfaz del administrador
    public ResultSet consultarTablaProductos() {
        
        try {
            //Objeto de la clase Connection para ABRIR y CERRAR la conexion con la BD
            Connection nuevaConexion = getConnection(); //Requiere de libreria java.sql.Connection;
            ps = nuevaConexion.prepareStatement("SELECT idProducto, nombre, idProveedor, idCategoria,  precio, marca, color, estatus, stock FROM producto");
            rs = ps.executeQuery();

        } catch (Exception e) {
            System.err.println("Error al CONSULTAR la tabla de proveedores: " + e);
        }
        return rs; //Se retorna los registros de la tabla encontrados
    }
    
    //Metodo para llenar tablla de productos en la interfaz del cliente
    public ResultSet consultarTablaProductosCliente() {
        
        try {
            //Objeto de la clase Connection para ABRIR y CERRAR la conexion con la BD
            Connection nuevaConexion = getConnection(); //Requiere de libreria java.sql.Connection;
            ps = nuevaConexion.prepareStatement("SELECT idProducto, nombre, idCategoria,  precio, marca, color, estatus, stock FROM producto");
            rs = ps.executeQuery();

        } catch (Exception e) {
            System.err.println("Error al CONSULTAR la tabla de proveedores: " + e);
        }
        return rs; //Se retorna los registros de la tabla encontrados
    }
    
    
    //Buscar el nombre de un producto
    public boolean buscarNombreProducto(Producto buscarProducto){
        Connection nuevaConexion = getConnection(); //Se abre conexión con BD
        try {
            ps = nuevaConexion.prepareStatement("SELECT idProducto, nombre FROM producto WHERE idProducto = ?");
            ps.setInt(1, buscarProducto.getIdProducto()); //La busqueda del nombre del producto se realizara por su id
            
            rs = ps.executeQuery();
            if(rs.next()){
                //Se encuentra exitosamente un registro
                buscarProducto.setNombre(rs.getString("nombre")); //se iniicaliza el atributo nombre del objeto que se recibio como parametro
                return true;
            }else{
                //No se encuentra registro
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("ERROR AL buscar nombre del prodcuto: "+ e);
            return false;
        }finally{
            try {
                nuevaConexion.close();
            } catch (Exception e) {
                System.out.println("ERROR AL CERRAR LA CONEXION: "+ e);
            }
        }
    }
    
}
