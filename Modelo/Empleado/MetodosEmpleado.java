/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Empleado;

import Modelo.Cliente.Cliente;
import Modelo.ConexionTiendaBD;
import com.mysql.jdbc.Connection;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 */
//Esta clase es hija de la clase ConexionTiendaBD
public class MetodosEmpleado extends ConexionTiendaBD{
    
    //Objetos de tipo PreparedStatement para ejecutar sentencias SQL que no devuelven registrsos
    PreparedStatement ps; //Requiere de libreria java.sql.PreparedStatement;
    //Objetos de tipo ResultSet para ejecutar sentencias SQL que devuelven registros de la BD
    ResultSet rs; //Requiere de liberia java.sql.ResultSet;
    
    
    //*****************************************************
    //***************  METODO para REGISTRAR EMPLEADOS
    //*****************************************************
    
    
    //EL metodo requere de un objeto de la clase EMPLEADO para poder registar un EMPLEADO
    public boolean registrarEmpleado(Empleado nuevoEmpleado){
        // getConnection() es una funcion e¿heredada de la clase ConexionTiendaBD
        Connection nuevaConexion = getConnection(); //Requiere de libreria java.sql.Connection;
        
        try {
            //Se prepara la sentencia SQL para la insercion de un nuevo EMPLEADO
            ps = nuevaConexion.prepareStatement("INSERT INTO empleado (nombre, apePaterno, apeMaterno, email, password, telefono, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)");
            //Se pasan los atributos del objeto "nuevoEmpleado" que se recibió como parametro de la función registarCliente()
            ps.setString(1, nuevoEmpleado.getNombre());
            ps.setString(2, nuevoEmpleado.getApePaterno());
            ps.setString(3, nuevoEmpleado.getApeMaterno());
            ps.setString(4, nuevoEmpleado.getEmail());
            ps.setString(5, nuevoEmpleado.getPassword());
            ps.setString(6, nuevoEmpleado.getTelefono());
            ps.setString(7, nuevoEmpleado.getDireccion());
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
            
            System.err.println("Error DE REGISTRO de empleado: " + e);
            return false;
            
        }finally{ //ESTA PARTE SIEMPRE SE HABRA DE EJECUTAR
            //**********  SE DEBE DE CERRAR LA CONEXION DE LA BASE DE DATOS ***********
            /**
             * EL CIERRE DE LA CONEXION VA DESPUÉS DE finally ya que para entonces se deberá de haber ejcutado alguno de los casos anteriore.
             */
            try {
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar conexion con BD: " + e);
            }
        }
    }
    
    //*****************************************************
    //***************  METODO para BUSCAR EMPLEADO
    //*****************************************************
    public boolean buscarEmpleado (Empleado nuevoEmpleado){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection();
        
        try {
            ps = nuevaConexion.prepareStatement("SELECT * FROM empleado WHERE idEmpleado = ?");
            ps.setInt(1, nuevoEmpleado.getIdEmpleado());
            //Se ejecuta la consulta y se almacena en un objeo ResultSet
            rs = ps.executeQuery();
            
            if(rs.next()){
                //Se encuentra un registro
                //Se inicializan el resto de atributos del objeto que se recibio como parametro
                nuevoEmpleado.setNombre(rs.getString("nombre"));
                nuevoEmpleado.setApePaterno(rs.getString("apePaterno"));
                nuevoEmpleado.setApeMaterno(rs.getString("apeMaterno"));
                nuevoEmpleado.setEmail(rs.getString("email"));
                nuevoEmpleado.setTelefono(rs.getString("telefono"));
                nuevoEmpleado.setDireccion(rs.getString("direccion"));
                
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
    //***************  METODO para ACTUALIZAR EMPLEADO
    //*****************************************************
    public boolean actualizarEmpleado(Empleado nuevoEmpleado){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection();
        
        try {
            //Se prepara la sentencia sQL para actualizacion del registo
            ps = nuevaConexion.prepareStatement("UPDATE empleado SET nombre = ?, apePaterno = ?, apeMaterno = ?, email = ?, telefono = ?, direccion = ? WHERE idEmpleado = ?");
            ps.setString(1, nuevoEmpleado.getNombre());
            ps.setString(2, nuevoEmpleado.getApePaterno());
            ps.setString(3, nuevoEmpleado.getApeMaterno());
            ps.setString(4, nuevoEmpleado.getEmail());
            ps.setString(5, nuevoEmpleado.getTelefono());
            ps.setString(6, nuevoEmpleado.getDireccion());
            
            ps.setInt(7, nuevoEmpleado.getIdEmpleado());
            
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
            System.err.println("Error al actualizar el registro del empleado: "+ e);
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
    //***************  METODO para ELIMINAR EMPLEADO
    //*****************************************************
    public boolean eliminarEmpleado(Empleado nuevoEmpleado){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection();
        
        try {
            ps = nuevaConexion.prepareStatement("DELETE FROM empleado WHERE idEmpleado = ?");
            ps.setInt(1, nuevoEmpleado.getIdEmpleado());
            
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
            System.err.println("ERROR AL TRTAR DE BORRAR AL EMPLEADO: "+ e);
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
    //***************  METODO para BUSCAR CORREO REGISTRADO de EMPLEADO
    //*****************************************************
    public boolean buscarCorreoEmpleado(Empleado nuevoEmpleado){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection();
        
        try {
            ps = nuevaConexion.prepareStatement("SELECT email FROM empleado WHERE email = ?");
            ps.setString(1, nuevoEmpleado.getEmail());
            
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
            System.err.println("Error al buscar correo de empleado: "+ e);
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
    //***************  METODO para INICIAR SESION como EMPLEADO
    //*****************************************************
    public boolean iniciarSesion(Empleado nuevoEmpleado){
        //Se abre conexion con la BD
        Connection nuevaConexion = getConnection(); // getConnextion() es un metodo heredado
        
        try {
            //Sentencia para buscar usuario donde correo y contraseña coincidan
            ps = nuevaConexion.prepareStatement("SELECT * FROM empleado WHERE (email = ?) AND (password = ?) ");
            ps.setString(1, nuevoEmpleado.getEmail());
            ps.setString(2, nuevoEmpleado.getPassword());
            
            //Se pretende obtener un registro como resultado, y debe de almacenarse un un objeto de tipo ResultSet
            rs = ps.executeQuery(); //Se ejecuta la consulta
            
            if( rs.next() ){
                //Se encontro al usuario con los datos ingresados
                //se iniicilizan el resto de atributos
                //Ya no se inicializa el email y password porque ya vienen inicializadso desde que se llama la presente funcion
                nuevoEmpleado.setIdEmpleado(rs.getInt("idEmpleado") );
                nuevoEmpleado.setNombre( rs.getString("nombre") );
                nuevoEmpleado.setApePaterno( rs.getString("apePaterno") );
                nuevoEmpleado.setApeMaterno( rs.getString("apeMaterno") );
                
                nuevoEmpleado.setTelefono( rs.getString("telefono") );
                nuevoEmpleado.setDireccion( rs.getString("direccion") );
                
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
    
    
    
}   //FINALIZA CLASE
