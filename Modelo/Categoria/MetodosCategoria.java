
package Modelo.Categoria;

import Modelo.ConexionTiendaBD;
import Modelo.Proveedor.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 */
public class MetodosCategoria extends ConexionTiendaBD{
    
    //Atributos de calse
    private PreparedStatement ps; //Requiere de libreria java.sql.PreparedStatement; objeto para preparar sentencia SQL
    private ResultSet rs; //Requiere de liberia java.sql.ResultSet; Objeto necesario para almacenar los registros consultados
    
    /**
     * Vector de proveedores para llenar el combo box de proveedores en el
     * formulario de producto
     */
    //Requiere de import java.util.Vector;
    public Vector<Categoria> mostrarComboCategoria() {
        //PreparedStatement ps = null;
        //ResultSet rs = null;

        Vector<Categoria> vectorDeCategorias = new Vector<Categoria>();
        Categoria objetoCategoria = null;
        //Se abre la conexion con la BD
        Connection nuevaConexion = getConnection();
        try {
            
            ps = nuevaConexion.prepareStatement("SELECT idCategoria, nombreCategoria FROM categoria"); //Se cconsultan todas las categoria (solo su id y su nombre)
            rs = ps.executeQuery(); //Los registros se almacenan en un objeto del tipo ResulSet

            objetoCategoria = new Categoria(); //instanciacion
            objetoCategoria.setIdCategoria(0);
            objetoCategoria.setNombreCategoria("Seleccione una categoria");
            //Las dos lineas anteriores son una primer tupla introducida manuallmente
            vectorDeCategorias.add(objetoCategoria); //Se agrega al objeto de tipo Vector<Categoria> el primer elemento que también es un objeto

            //Mientras existan registros
            while (rs.next()) {
                /**Durante el ciclo se crearan objetos de la clase Categoria, se inicializaran sus atributos y cada objeto se añadira
                 * al objeto de tipo Vector
                 */
                objetoCategoria = new Categoria(); //instanciacion
                objetoCategoria.setIdCategoria(rs.getInt("idCategoria"));
                objetoCategoria.setNombreCategoria(rs.getString("nombreCategoria"));
                
                vectorDeCategorias.add(objetoCategoria); //Se agrega al objeto de tipo vecto el nuevo objeto de tipo Categoria
            }
            rs.close();
        } catch (Exception e) {
            System.err.println("ERROR AL CONSULTAR COMBO DE CATEGORIAS: "+ e);
        }finally{
            try {
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("ERROR AL CONSULTAR COMBO DE CATEGORIAS: "+ e);
            }
        }
        return vectorDeCategorias; //Se retrna el vetor de los proveedores
    }
    
    //Metodo de consula de los nombres de las categorias existentes
    public boolean buscarNombreCategoria(Categoria buscarCategoria){
        //Se aabre la conexion con la BD
        Connection nuevaConexion = getConnection(); //getConnection() es un método heredado
        
        try {
            ps = nuevaConexion.prepareStatement("SELECT idCategoria, nombreCategoria FROM CATEGORIA WHERE idCategoria = ?");
            ps.setInt(1, buscarCategoria.getIdCategoria());
            //Se alamcena el registro encontrado
            rs = ps.executeQuery();
            
            if(rs.next()){
                buscarCategoria.setNombreCategoria(rs.getString("nombreCategoria"));
                return true; //busqueda exitosa
            }else{
                return false; //busqueda fallida
            }
            
        } catch (Exception e) {
            System.err.println("Error en la consulta de nombres de categorias: "+e);
            return false; //Error en la ejecucion de la sentencia SQL
        }finally{
            try {
                //Se cierra la conexion
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("ERROR AL CERRAR LA CONEXION: "+ e);
            }
        }
            
        
    }
}
