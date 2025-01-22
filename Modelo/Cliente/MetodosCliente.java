/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Cliente;

import Modelo.ConexionTiendaBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Autor: Eduardo German Garcia
 * CLASE Modelo del Cliente
 * Descrpcion: Implementar los métodos del CRUD de la entidad Cliente de la BD
 * 02:03 a. m. 13/04/2022
 */

//Esta clase es hija de la clase ConexionTiendaBD
public class MetodosCliente extends ConexionTiendaBD{
    //Objetos de tipo PreparedStatement para ejecutar sentencias SQL que no devuelven registrsos
    PreparedStatement ps; //Requiere de libreria java.sql.PreparedStatement;
    //Objetos de tipo ResultSet para ejecutar sentencias SQL que devuelven registros de la BD
    ResultSet rs; //Requiere de liberia java.sql.ResultSet;
    
    //*****************************************************
    //***************  METODO para REGISTRAR CLIENTES
    //*****************************************************
    
    //EL metodo requere de un objeto de la clase Cliente para poder registar un cliente
    public boolean registrarCliente(Cliente nuevoCliente){
        // getConnection() es una funcion e¿heredada de la clase ConexionTiendaBD
        Connection nuevaConexion = getConnection(); //Requiere de libreria java.sql.Connection;
        
        try {
            //Se prepara la sentencia SQL para la insercion de un nuevo cliente
            ps = nuevaConexion.prepareStatement("INSERT INTO cliente (nombre, apePaterno, apeMaterno, email, password, telefono, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)");
            //Se pasan los atributos del objeto "nuevoCliente" que se recibió como parametro de la función registarCliente()
            ps.setString(1, nuevoCliente.getNombre());
            ps.setString(2, nuevoCliente.getApePaterno());
            ps.setString(3, nuevoCliente.getApeMaterno());
            ps.setString(4, nuevoCliente.getEmail());
            ps.setString(5, nuevoCliente.getPassword());
            ps.setString(6, nuevoCliente.getTelefono());
            ps.setString(7, nuevoCliente.getDireccion());
            //Como se va a realizar un registro, NO UNA CONSULTA se utilia << executeUpdate(); el cual siempre devuelve un entero
            int resultado = ps.executeUpdate();
            
            if(resultado > 0){
                //Se realiza el registro de manera exitosa
                return true;
            }else{
                //No se logra realizar la insercion
                return false;
            }
            
        } catch (Exception e) {
            
            System.err.println("Error: " + e);
            return false;
            
        }finally{ //ESTA PARTE SIEMPRE SE HABRA DE EJECUTAR
            //**********  SE DEBE DE CERRAR LA CONEXION DE LA BASE DE DATOS ***********
            /**
             * EL CIERRE DE LA CONEXION VA DESPUÉS DE finally ya que para entonces se deberá de haber ejcutado alguno de los casos anteriore.
             */
            try {
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
        }
    }
    
    //*****************************************************
    //***************  METODO para BUSCAR CLIENTE
    //*****************************************************
    public boolean buscarCliente (Cliente nuevoCliente){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection();
        
        try {
            ps = nuevaConexion.prepareStatement("SELECT * FROM cliente WHERE idCliente = ?");
            ps.setInt(1, nuevoCliente.getIdCliente());
            //Se ejecuta la consulta y se almacena en un objeo ResultSet
            rs = ps.executeQuery();
            
            if(rs.next()){
                //Se encuentra un registro
                //Se inicializan el resto de atributos del objeto que se recibio como parametro
                nuevoCliente.setNombre(rs.getString("nombre"));
                nuevoCliente.setApePaterno(rs.getString("apePaterno"));
                nuevoCliente.setApeMaterno(rs.getString("apeMaterno"));
                nuevoCliente.setEmail(rs.getString("email"));
                nuevoCliente.setTelefono(rs.getString("telefono"));
                nuevoCliente.setDireccion(rs.getString("direccion"));
                
                return true;
                
            }else{
                //Ningun registro encontrado
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("Error al tratar de buscar cliente: "+ e);
            return false;
        }finally{
            try {
                //se cierra la conexion con la BD
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("ERROR AL CERRAR LA CONEXION CON LA BD: " + e);
            }
        }
        
    }
    
    
    //*****************************************************
    //***************  METODO para ACTUALIZAR CLIENTE
    //*****************************************************
    public boolean actualizarCliente(Cliente nuevoCliente){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection();
        
        try {
            //Se prepara la sentencia sQL para actualizacion del registo
            ps = nuevaConexion.prepareStatement("UPDATE cliente SET nombre = ?, apePaterno = ?, apeMaterno = ?, email = ?, telefono = ?, direccion = ? WHERE idCliente = ?");
            ps.setString(1, nuevoCliente.getNombre());
            ps.setString(2, nuevoCliente.getApePaterno());
            ps.setString(3, nuevoCliente.getApeMaterno());
            ps.setString(4, nuevoCliente.getEmail());
            ps.setString(5, nuevoCliente.getTelefono());
            ps.setString(6, nuevoCliente.getDireccion());
            
            ps.setInt(7, nuevoCliente.getIdCliente());
            
            //Se ejecuta la sentencia SQL, que a sivez no devuelve ningun registro, solo un valor entero dependiendo de si se ejecuto con exito o no la sentencia SQL
            int resultado = ps.executeUpdate();
            
            if(resultado > 0){
                //Se actualiza el registro dle cliente
                return true;
            }else{
                //NO SE EJECUTA LA ACTUALIZACION
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("Error al actualizar el registro de cliente: "+ e);
            return false;
        }finally{
            try {
                //Se cierra conexion con la BD
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexion con la BD: "+ e);
            }
        }
    }
    
    
    //*****************************************************
    //***************  METODO para ACTUALIZAR Datos basicos del CLIENTE
    //*************** Solo actualiza contraseña, telefono y direccion
    //*****************************************************
    public boolean actualizarDatosCliente(Cliente nuevoCliente){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection();
        
        try {
            //Se prepara la sentencia sQL para actualizacion del registo
            ps = nuevaConexion.prepareStatement("UPDATE cliente SET password = ?, telefono = ?, direccion = ? WHERE idCliente = ?");
            ps.setString(1, nuevoCliente.getPassword());
            ps.setString(2, nuevoCliente.getTelefono());
            ps.setString(3, nuevoCliente.getDireccion());
            
            ps.setInt(4, nuevoCliente.getIdCliente());
            
            //Se ejecuta la sentencia SQL, que a sivez no devuelve ningun registro, solo un valor entero dependiendo de si se ejecuto con exito o no la sentencia SQL
            int resultado = ps.executeUpdate();
            
            if(resultado > 0){
                //Se actualiza el registro dle cliente
                return true;
            }else{
                //NO SE EJECUTA LA ACTUALIZACION
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("Error al actualizar el registro de cliente: "+ e);
            return false;
        }finally{
            try {
                //Se cierra conexion con la BD
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexion con la BD: "+ e);
            }
        }
    }
    
    //*****************************************************
    //***************  METODO para ELIMINAR CLIENTE
    //*****************************************************
    public boolean eliminarCliente(Cliente nuevoCliente){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection();
        
        try {
            ps = nuevaConexion.prepareStatement("DELETE FROM cliente WHERE idCliente = ?");
            ps.setInt(1, nuevoCliente.getIdCliente());
            
            //Ejecución de sentencia sQL
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                //Borrado exitoso
                return true;
            }else{
                //Erro en el borrado
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("ERROR AL TRTAR DE BORRAR AL CLIENTE: "+ e);
            return false;
            
        }finally{
            try {
                //Se cierra la conexion con la BD
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("ERROR AL CERRAR LA CONEXION CON LA BD: "+ e);
            }
        }
        
    }
    
    
    //*****************************************************
    //***************  METODO para BUSCAR CORREO REGISTRADO
    //*****************************************************
    public boolean buscarCorreoCliente(Cliente nuevoCliente){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection();
        
        try {
            ps = nuevaConexion.prepareStatement("SELECT email FROM cliente WHERE email = ?");
            ps.setString(1, nuevoCliente.getEmail());
            
            //Se almacena la consultas
            rs = ps.executeQuery();
            
            if(rs.next()){
                //Se devuelve un true si se encuentra un correo
                return true;
            }else{
                //No se encuentra el correo buscado
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("Error al buscar correo: "+ e);
            return false;
        }finally{
            try {
                //Se cierra la conexion con la BD
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar conexion con BD: " + e);
            }
        }
        
        
    }   //Finzaliza metodo para buscar correo
    
    
    //*****************************************************
    //***************  METODO para INICIAR SESION como CLIENTE
    //*****************************************************
    public boolean iniciarSesion(Cliente nuevoCliente){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection(); // getConnextion() es un metodo heredado
        
        try {
            //Sentencia para buscar usuario donde correo y contraseña coincidan
            ps = nuevaConexion.prepareStatement("SELECT * FROM cliente WHERE (email = ?) AND (password = ?) ");
            ps.setString(1, nuevoCliente.getEmail());
            ps.setString(2, nuevoCliente.getPassword());
            
            //Se pretende obtener un registro como resultado, y debe de almacenarse un un objeto de tipo ResultSet
            rs = ps.executeQuery(); //Se ejecuta la consulta
            
            if( rs.next() ){
                //Se encontro al usuario con los datos ingresados
                //se iniicilizan el resto de atributos
                //Ya no se inicializa el email y password porque ya vienen inicializadso desde que se llama la presente funcion
                nuevoCliente.setIdCliente( rs.getInt("idCliente") );
                nuevoCliente.setNombre( rs.getString("nombre") );
                nuevoCliente.setApePaterno( rs.getString("apePaterno") );
                nuevoCliente.setApeMaterno( rs.getString("apeMaterno") );
                
                nuevoCliente.setTelefono( rs.getString("telefono") );
                nuevoCliente.setDireccion( rs.getString("direccion") );
                
                return true;
            }else{
                //NO se localizao al usuario con los datos ingresados
                return false;
            }

        } catch (Exception e) {
            //ERROR AL REALIZAR BUSQIEDA
            System.err.println("Error al iniciar sesion\nCodigo: " + e);
            return false;
        }finally{
            try {
                //Se cierra la conexion con laBD
                nuevaConexion.close();
            } catch (Exception e) {
                //Error al cerrar la conexion con la BD
                System.err.println("Error al cerrar la conexion con la BD\nCodigo: " + e);
            }
        }
        
    }   //Finaliza método para iniciar sesion
    
    
    //*****************************************************
    //***************  METODO para CONSULTAR TABLA CLIENTE
    //*****************************************************
    public ResultSet consultarTablaClientes(){
        
        try {
            //Se abre conexion con la BD
            Connection nuevaConexion = getConnection();
            //Se prepara sentencia SQL
            ps = nuevaConexion.prepareStatement("SELECT idCliente, nombre, apePaterno, apeMaterno, email, telefono, direccion FROM cliente");
            //Se almacena el resultado de la consulta en un objeto ResultSet
            rs = ps.executeQuery();
                    
        } catch (Exception e) {
            System.err.println("Error al consultar la tabla de clientes: "+e);
        }
        return rs;
    }
    
    
}
