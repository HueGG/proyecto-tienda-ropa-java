/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Venta;

import Modelo.Cliente.Cliente;
import java.time.LocalDate;
import Modelo.ConexionTiendaBD;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * METODOS DE VENTA
 */
public class MetodosVenta extends ConexionTiendaBD{
    //Objetos de tipo PreparedStatement para ejecutar sentencias SQL que no devuelven registrsos
    PreparedStatement ps; //Requiere de libreria java.sql.PreparedStatement;
    //Objetos de tipo ResultSet para ejecutar sentencias SQL que devuelven registros de la BD
    ResultSet rs; //Requiere de liberia java.sql.ResultSet;
    
    
    //*****************************************************
    //***************  METODO para REGISTRAR NUEVA VENTA / COMPRA
    //*****************************************************
    

    //EL metodo requere de un objeto de la clase Venta para poder registar una nueva venta o compra
    public boolean registrarVenta(Venta nuevoVenta){
        // getConnection() es una funcion e¿heredada de la clase ConexionTiendaBD
        Connection nuevaConexion = getConnection(); //Requiere de libreria java.sql.Connection;
        
        try {
            //Se prepara la sentencia SQL para la insercion de una nueva VENTA O COMPRA
            ps = nuevaConexion.prepareStatement("INSERT INTO venta (idCliente, idProducto, cantidad, precioTotal, fechaCompra, fechaEntrega) VALUES (?, ?, ?, ?, ?, ?)");
            //Creacion de un objeto para la fecha
            LocalDate fechaDeHoy = LocalDate.now();
            
            //Se pasan los atributos del objeto "nuevoVenta" que se recibió como parametro de la función registarVenta()
            ps.setInt(1, nuevoVenta.getIdCliente());
            ps.setInt(2, nuevoVenta.getIdProducto());
            ps.setInt(3, nuevoVenta.getCantidad());
            ps.setDouble(4, nuevoVenta.getPrecioTotal());
            ps.setString(5, String.valueOf(fechaDeHoy));
            ps.setString(6, String.valueOf(fechaDeHoy));
            
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
    //***************  Método para LLENAR TABLA de COMPRAS DE UN CLIENTE
    //*****************************************************
    
    //Metodo para llenar tabla de productos en la interfaz del administrador
    public ResultSet consultarTablaVentasPorCliente( Cliente nuevoCliente ) {
        
        try {
            //Objeto de la clase Connection para ABRIR y CERRAR la conexion con la BD
            Connection nuevaConexion = getConnection(); //Requiere de libreria java.sql.Connection;
            ps = nuevaConexion.prepareStatement("SELECT idVenta, idProducto, cantidad, precioTotal, fechaCompra, fechaEntrega FROM venta WHERE idCliente = ?");
            ps.setInt(1, nuevoCliente.getIdCliente()); //Se consulta la tabla de ventas mediante el id del cliente que realizo la compra
            rs = ps.executeQuery(); //Se almacenan los datos en un objeto de tipo ResultSet

        } catch (Exception e) {
            System.err.println("Error al CONSULTAR la tabla de proveedores: " + e);
        }
        return rs; //Se retorna los registros de la tabla encontrados
    }
    
    
    
    
}
//FIN DE CLASE
