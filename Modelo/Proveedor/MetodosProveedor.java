package Modelo.Proveedor;

import Modelo.ConexionTiendaBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * Descripcion de clase: Definir e implementar los metodos que permitirán la
 * funcionalidad del CRUD para proveedores
 */
//HEREDA DE LA CLASE ConexionTiendaBD
public class MetodosProveedor extends ConexionTiendaBD {

    //Atributos de calse
    PreparedStatement ps; //Requiere de libreria java.sql.PreparedStatement; objeto para preparar sentencia SQL
    ResultSet rs; //Requiere de liberia java.sql.ResultSet; Objeto necesario para almacenar los registros consultados

    //*****************************************************
    //***************  Método de inserción
    //*****************************************************
    public boolean registrarProveedor(Proveedor nuevoProveedor) {    //Recibe como aparametro un objeto de la clase Proveedor
        // getConnection() es una funcion e¿heredada de la clase ConexionBD
        //Objeto de la clase Connection para ABRIR y CERRAR la conexion con la BD
        Connection nuevaConexion = getConnection(); //Requiere de libreria java.sql.Connection;

        //Iniicia el intento de registro de proveedor con Try
        try {
            //Lineas para la insercion de un nuevo proveedor
            //Se prepara sentencia SQL para la incersion de un nuevo proveedor
            ps = nuevaConexion.prepareStatement("INSERT INTO proveedor (nombreProveedor, nif, personaContacto, estado, municipio, colonia, calle, "
                    + "numExt, numInt, entreCalle1, entreCalle2, cp, telefonoProveedor, emailProveedor) VALUES "
                    + "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, nuevoProveedor.getNombreProveedor());
            ps.setString(2, nuevoProveedor.getNif());
            ps.setString(3, nuevoProveedor.getPersonaContacto());
            ps.setString(4, nuevoProveedor.getEstado());
            ps.setString(5, nuevoProveedor.getMunicipio());
            ps.setString(6, nuevoProveedor.getColonia());
            ps.setString(7, nuevoProveedor.getCalle());
            ps.setString(8, nuevoProveedor.getNumExt());
            ps.setString(9, nuevoProveedor.getNumInt());
            ps.setString(10, nuevoProveedor.getEntreCalle1());
            ps.setString(11, nuevoProveedor.getEntreCalle2());
            ps.setString(12, nuevoProveedor.getCp());
            ps.setString(13, nuevoProveedor.getTelefonoProveedor());
            ps.setString(14, nuevoProveedor.getEmailProveedor());
            //Como se va a realizar un registro, NO UNA CONSULTA se utilia << executeUpdate(); el cual siempre devuelve un entero
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                //REGISTRO EXITOSO
                return true;
            } else {
                //Registro fallido
                return false;
            }
            //FINALIZA try
        } catch (Exception e) {
            System.err.println("ERROR con el registro de proveedor\n Excepción: " + e);
            return false;
        } finally {   //ESTA PARTE SIEMPRE SE HABRA DE EJECUTAR
            //**********  SE DEBE DE CERRAR LA CONEXION DE LA BASE DE DATOS ***********
            /**
             * EL CIERRE DE LA CONEXION VA DESPUÉS DE finally ya que para
             * entonces se deberá de haber ejcutado alguno de los casos
             * anteriore.
             */
            try {
                //Se intenta cerrar la conexion
                //En esta parte se espera un cierre de conexion con la BD de manera exitosa
                nuevaConexion.close();
            } catch (Exception e) {
                //EN CASO DE QUE FALLE EL CIERRE DE CONEXION CON LA BD
                System.err.println("Error al cerrar la conexion\n Excepción " + e);
            }

        }

    }//FINALIZA Método para Registrar un proveedor

    //*****************************************************
    //***************  Método de BUSCAR PROVEEDOR
    //*****************************************************
    public boolean buscarProveedor(Proveedor nuevoProveedor) {    //Recibe como aparametro un objeto de la clase Proveedor
        // getConnection() es una funcion e¿heredada de la clase ConexionBD
        //Objeto de la clase Connection para ABRIR y CERRAR la conexion con la BD
        Connection nuevaConexion = getConnection(); //Requiere de libreria java.sql.Connection;

        //Iniicia el intento de BUSQUEDA de proveedor con Try
        try {
            //Lineas para la insercion de un nuevo proveedor
            //Se prepara sentencia SQL para la BUSQUEDA de un  proveedor
            ps = nuevaConexion.prepareStatement("SELECT * FROM proveedor WHERE idProveedor = ?");
            // !!!! NOTA: para la sentencia SQL, los datos ingresados deeben ser caracteres si lo que se realiza es una consulta  ¡¡¡¡
            ps.setString(1, String.valueOf(nuevoProveedor.getIdProveedor()));
            /**
             * Como se va a realizar una consulta que devilverá un resultado se
             * utilia << executeQuery(); el cual devolverá un valor que se
             * almacenara en un objeto tipo ResutlSet
             */
            rs = ps.executeQuery();

            if (rs.next()) {
                //Si se encuentra un registro
                //Se inicializan el resto de atributos del objeto nuevoProveedor mdiante lo recibido en << rs >>
                nuevoProveedor.setNombreProveedor(rs.getString("nombreProveedor"));
                nuevoProveedor.setNif(rs.getString("nif"));
                nuevoProveedor.setPersonaContacto(rs.getString("personaContacto"));
                nuevoProveedor.setEstado(rs.getString("estado"));
                nuevoProveedor.setMunicipio(rs.getString("municipio"));
                nuevoProveedor.setColonia(rs.getString("colonia"));
                nuevoProveedor.setCalle(rs.getString("calle"));
                nuevoProveedor.setNumExt(rs.getString("numExt"));
                nuevoProveedor.setNumInt(rs.getString("numInt"));
                nuevoProveedor.setEntreCalle1(rs.getString("entreCalle1"));
                nuevoProveedor.setEntreCalle2(rs.getString("entreCalle2"));
                nuevoProveedor.setCp(rs.getString("cp"));
                nuevoProveedor.setTelefonoProveedor(rs.getString("telefonoProveedor"));
                nuevoProveedor.setEmailProveedor(rs.getString("emailProveedor"));
                return true;
            } else {
                //No se encuentra ingun registro
                return false;
            }
            //FINALIZA try
        } catch (Exception e) {
            System.err.println("ERROR con la BUSQUEDA de proveedor\n Excepción: " + e);
            return false;
        } finally {   //ESTA PARTE SIEMPRE SE HABRA DE EJECUTAR
            //**********  SE DEBE DE CERRAR LA CONEXION DE LA BASE DE DATOS ***********
            /**
             * EL CIERRE DE LA CONEXION VA DESPUÉS DE finally ya que para
             * entonces se deberá de haber ejcutado alguno de los casos
             * anteriore.
             */
            try {
                //Se intenta cerrar la conexion
                //En esta parte se espera un cierre de conexion con la BD de manera exitosa
                nuevaConexion.close();
            } catch (Exception e) {
                //EN CASO DE QUE FALLE EL CIERRE DE CONEXION CON LA BD
                System.err.println("Error al cerrar la conexion\n Excepción " + e);
            }

        }

    }//FINALIZA Método para BUSCAR un proveedor

    //*****************************************************
    //***************  Método de ACTUALIZAR PROVEEDOR
    //*****************************************************
    public boolean actualizarProveedor(Proveedor nuevoProveedor) {    //Recibe como aparametro un objeto de la clase Proveedor
        // getConnection() es una funcion e¿heredada de la clase ConexionBD
        //Objeto de la clase Connection para ABRIR y CERRAR la conexion con la BD
        Connection nuevaConexion = getConnection(); //Requiere de libreria java.sql.Connection;

        //Iniicia el intento de ACTUALIZACION de proveedor con Try
        try {
            //Lineas para la ACTUALIZACION de un nuevo proveedor
            //Se prepara sentencia SQL para la ACTUALIZACIÓN de un  proveedor
            ps = nuevaConexion.prepareStatement("UPDATE proveedor SET nombreProveedor = ?, nif = ?, personaContacto = ?, "
                    + " estado = ?, municipio = ?, colonia = ?, calle = ?, numExt = ?, numInt = ?, entreCalle1 = ?,"
                    + " entreCalle2 = ?, cp = ?, telefonoProveedor = ?, emailProveedor = ? WHERE idProveedor = ?");
            // !!!! NOTA: para la sentencia SQL, los datos ingresados deeben ser caracteres si lo que se realiza es una consulta  ¡¡¡¡
            ps.setString(1, nuevoProveedor.getNombreProveedor());
            ps.setString(2, nuevoProveedor.getNif());
            ps.setString(3, nuevoProveedor.getPersonaContacto());
            ps.setString(4, nuevoProveedor.getEstado());
            ps.setString(5, nuevoProveedor.getMunicipio());
            ps.setString(6, nuevoProveedor.getColonia());
            ps.setString(7, nuevoProveedor.getCalle());
            ps.setString(8, nuevoProveedor.getNumExt());
            ps.setString(9, nuevoProveedor.getNumInt());
            ps.setString(10, nuevoProveedor.getEntreCalle1());
            ps.setString(11, nuevoProveedor.getEntreCalle2());
            ps.setString(12, nuevoProveedor.getCp());
            ps.setString(13, nuevoProveedor.getTelefonoProveedor());
            ps.setString(14, nuevoProveedor.getEmailProveedor());
            ps.setString(15, String.valueOf(nuevoProveedor.getIdProveedor()));
            /**
             * Se ejecutara una sentencia SQL que no devuelve un registro de la
             * BD, por lo que se utilizará el método executeUpdate() que se
             * llama mediant el objeto << ps >> de la clase << PreparedStatement
             * >> el método devuelve un entero
             */
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                //Actualizacion correcta
                return true;
            } else {
                //No se actualizó nada
                return false;
            }
            //FINALIZA try
        } catch (Exception e) {
            System.err.println("ERROR con la ACTUALIZACION de proveedor\n Excepción: " + e);
            return false;
        } finally {   //ESTA PARTE SIEMPRE SE HABRA DE EJECUTAR
            //**********  SE DEBE DE CERRAR LA CONEXION DE LA BASE DE DATOS ***********
            /**
             * EL CIERRE DE LA CONEXION VA DESPUÉS DE finally ya que para
             * entonces se deberá de haber ejcutado alguno de los casos
             * anteriore.
             */
            try {
                //Se intenta cerrar la conexion
                //En esta parte se espera un cierre de conexion con la BD de manera exitosa
                nuevaConexion.close();
            } catch (Exception e) {
                //EN CASO DE QUE FALLE EL CIERRE DE CONEXION CON LA BD
                System.err.println("Error al cerrar la conexion\n Excepción " + e);
            }

        }

    }//FINALIZA Método para ACTUALIZAR un proveedor

    //*****************************************************
    //***************  Método de ELIMINAR PROVEEDOR
    //*****************************************************
    public boolean eliminarProveedor(Proveedor nuevoProveedor) {    //Recibe como aparametro un objeto de la clase Proveedor
        // getConnection() es una funcion e¿heredada de la clase ConexionBD
        //Objeto de la clase Connection para ABRIR y CERRAR la conexion con la BD
        Connection nuevaConexion = getConnection(); //Requiere de libreria java.sql.Connection;

        //Iniicia el intento de ACTUALIZACION de proveedor con Try
        try {
            //Lineas para la ELIMINAR de un nuevo proveedor
            //Se prepara sentencia SQL para la ELIMINACION de un  proveedor
            ps = nuevaConexion.prepareStatement("DELETE FROM proveedor WHERE idProveedor = ?");
            // !!!! NOTA: para la sentencia SQL, los datos ingresados deeben ser caracteres si lo que se realiza es una consulta  ¡¡¡¡
            ps.setString(1, String.valueOf(nuevoProveedor.getIdProveedor()));
            /**
             * Se ejecutara una sentencia SQL que no devuelve un registro de la
             * BD, por lo que se utilizará el método executeUpdate() que se
             * llama mediant el objeto << ps >> de la clase << PreparedStatement
             * >> el método devuelve un entero
             */
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                //ELIMINACION correcta
                return true;
            } else {
                //No se ELIMINÓ nada
                return false;
            }
            //FINALIZA try
        } catch (Exception e) {
            System.err.println("ERROR con la eliminacion de proveedor\n Excepción: " + e);
            return false;
        } finally {   //ESTA PARTE SIEMPRE SE HABRA DE EJECUTAR
            //**********  SE DEBE DE CERRAR LA CONEXION DE LA BASE DE DATOS ***********
            /**
             * EL CIERRE DE LA CONEXION VA DESPUÉS DE finally ya que para
             * entonces se deberá de haber ejcutado alguno de los casos
             * anteriore.
             */
            try {
                //Se intenta cerrar la conexion
                //En esta parte se espera un cierre de conexion con la BD de manera exitosa
                nuevaConexion.close();
            } catch (Exception e) {
                //EN CASO DE QUE FALLE EL CIERRE DE CONEXION CON LA BD
                System.err.println("Error al cerrar la conexion\n Excepción " + e);
            }

        }

    }//FINALIZA Método para ELIMINAR un proveedor

    //*****************************************************
    //***************  Método para LLENAR TABLA PROVEEDOR
    //*****************************************************
    public ResultSet consultarTablaProveedor() {
        try {
            //Objeto de la clase Connection para ABRIR y CERRAR la conexion con la BD
            Connection nuevaConexion = getConnection(); //Requiere de libreria java.sql.Connection;
            ps = nuevaConexion.prepareStatement("SELECT idProveedor, nombreProveedor, nif, personaContacto, cp, telefonoProveedor, emailProveedor FROM proveedor");
            rs = ps.executeQuery();

        } catch (Exception e) {
            System.err.println("Error al CONSULTAR la tabla de proveedores: " + e);
        }
        return rs; //Se retorna los registros de la tabla encontrados
    }

    /**
     * Vector de proveedores para llenar el combo box de proveedores en el
     * formulario de producto
     */
    //Requiere de import java.util.Vector;
    public Vector<Proveedor> mostrarComboProveedores() {
        //PreparedStatement ps = null;
        //ResultSet rs = null;

        Vector<Proveedor> vectorDeProveedores = new Vector<Proveedor>();
        Proveedor objetoProveedor = null;
        //Se abre la conexion con la BD
        Connection nuevaConeccion = getConnection();
        try {
            
            ps = nuevaConeccion.prepareStatement("SELECT idProveedor, nombreProveedor FROM proveedor"); //Se cconsultan todos los proveedores (solo su id y su nombre)
            rs = ps.executeQuery(); //Los registros se almacenan en un objeto del tipo ResulSet

            objetoProveedor = new Proveedor(); //instanciacion
            objetoProveedor.setIdProveedor(0);
            objetoProveedor.setNombreProveedor("Seleccione un Proveedor");
            //Las dos lineas anteriores son una primer tupla introducida manuallmente
            vectorDeProveedores.add(objetoProveedor); //Se agrega al objeto de tipo Vector<Proveedor> el primer elemento que también es un objeto

            //Mientras existan registros
            while (rs.next()) {
                /**Durante el ciclo se crearan objetos de la clase Proveedor, se inicializaran sus atributos y cada objeto se añadira
                 * al objeto de tipo Vector
                 */
                objetoProveedor = new Proveedor(); //instanciacion
                objetoProveedor.setIdProveedor(rs.getInt("idProveedor"));
                objetoProveedor.setNombreProveedor(rs.getString("nombreProveedor"));
                
                vectorDeProveedores.add(objetoProveedor); //Se agrega al objeto de tipo vecto el nuevo objeto de tipo Proveedor
            }
            rs.close();
        } catch (Exception e) {
            System.err.println("ERROR AL CONSULATAR COMBO DE PROVEEDORES: "+ e);
        }finally{
            try {
                nuevaConeccion.close();
            } catch (Exception e) {
                System.err.println("ERROR AL CONSULATAR COMBO DE PROVEEDORES: "+ e);
            }
        }
        return vectorDeProveedores; //Se retrna el vetor de los proveedores
    }
    
    public boolean buscarNombreProveedor(Proveedor buscarProveedor){
        Connection nuevaConexion = getConnection(); //Se abre conexión con BD
        try {
            ps = nuevaConexion.prepareStatement("SELECT idProveedor, nombreProveedor FROM proveedor WHERE idProveedor = ?");
            ps.setInt(1, buscarProveedor.getIdProveedor());
            
            rs = ps.executeQuery();
            if(rs.next()){
                //Se encuentra exitosamente un registro
                buscarProveedor.setNombreProveedor(rs.getString("nombreProveedor")); //se iniicaliza el atributo nombre del objeto que se recibio como parametro
                return true;
            }else{
                //No se encuentra registro
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("ERROR AL buscar nombre de proveedor: "+ e);
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
