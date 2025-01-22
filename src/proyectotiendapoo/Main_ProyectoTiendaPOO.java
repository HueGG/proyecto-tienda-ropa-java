/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotiendapoo;

import Controlador.Cliente.ControladorCliente;
import Controlador.Interfaz.ControladorInterfazLoginCliente;
import Controlador.Interfaz.ControladorInterfazRegistroCliente;
import Controlador.Producto.ControladorProducto;
import Controlador.Proveedor.ControladorProveedor;
import Modelo.Cliente.Cliente;
import Modelo.Cliente.MetodosCliente;
import Modelo.Producto.MetodosProducto;
import Modelo.Producto.Producto;
import Modelo.Proveedor.MetodosProveedor;
import Modelo.Proveedor.Proveedor;
import Vista.VentanaAdministrador;
import Vista.VentanaLoginCliente;
import Vista.VentanaRegistroCliente;

/**
 *
 * @author ASUS-TUF
 */
public class Main_ProyectoTiendaPOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Objetos de las Vistas
        VentanaAdministrador nuevaVistaAdmin = new VentanaAdministrador();
        VentanaRegistroCliente nuevaVistaRegistroCliente = new VentanaRegistroCliente();
        VentanaLoginCliente nuevaVistaLoginCliente = new VentanaLoginCliente();
        
        //Objetos de las clases
        Cliente nuevoCliente = new Cliente();
        Proveedor nuevoProveedor = new Proveedor();
        Producto nuevoProducto = new Producto();
        
        //Objetos de los modelos
        MetodosCliente modeloCliente = new MetodosCliente();
        MetodosProveedor modeloProveedor = new MetodosProveedor();
        MetodosProducto modeloProducto = new MetodosProducto();
        
        //Objetos de los controladores
        ControladorCliente newControlCliente = new ControladorCliente(nuevaVistaAdmin, nuevoCliente, modeloCliente);
        ControladorProveedor newControlProveedor = new ControladorProveedor(nuevaVistaAdmin, nuevoProveedor, modeloProveedor);
        ControladorProducto newControlProducto = new ControladorProducto(nuevaVistaAdmin, nuevoProducto, modeloProducto);
        
        //ControladorInterfazRegistroCliente newControlRegistroCliente= new ControladorInterfazRegistroCliente(nuevaVistaRegistroCliente, nuevoCliente, modeloCliente);
        //ControladorInterfazLoginCliente newControlLoginCliente = new ControladorInterfazLoginCliente(nuevaVistaLoginCliente, nuevoCliente, modeloCliente);
        
        //Se carga vista de administrador
        newControlCliente.iniciarFormularioCliente();
        //newControlProveedor.iniciarFormularioProveedor();
        nuevaVistaAdmin.setVisible(true);
        
        //Se carga vista de forulari de registro de cliente
        //newControlRegistroCliente.iniciarFormularioRegistroCliente();
        //nuevaVistaRegistroCliente.setVisible(true);
        
        //Se carga vista de forulari de LOGIN para CLIENTE
        //newControlLoginCliente.iniciarFormularioLoginCliente();
        //nuevaVistaLoginCliente.setVisible(true);
    }
    
}
