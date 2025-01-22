/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Interfaz;

import Controlador.Cliente.ControladorCliente;
import Controlador.Producto.ControladorProducto;
import Controlador.Proveedor.ControladorProveedor;
import Modelo.Cliente.Cliente;
import Modelo.Cliente.MetodosCliente;
import Modelo.Empleado.Empleado;
import Modelo.Empleado.MetodosEmpleado;
import Modelo.Producto.MetodosProducto;
import Modelo.Producto.Producto;
import Modelo.Proveedor.MetodosProveedor;
import Modelo.Proveedor.Proveedor;
import Modelo.Venta.MetodosVenta;
import Modelo.Venta.Venta;
import Vista.VentanaAdministrador;

/**
 *
 */
public class ControladorInterfazAdministrador {
    //Objetos de las Vistas
    private VentanaAdministrador nuevaVistaAdmin;
    
    //Objetos de las clases
    private Empleado adminEmpleado;
    private Cliente nuevoCliente;
    private Proveedor nuevoProveedor;
    private Producto nuevoProducto;
    private Venta nuevaVenta;
        
    //Objetos de los modelos
    private MetodosEmpleado modeloEmpleado;
    private MetodosCliente modeloCliente;
    private MetodosProveedor modeloProveedor;
    private MetodosProducto modeloProducto;
    private MetodosVenta modeloVenta;

    public ControladorInterfazAdministrador(VentanaAdministrador nuevaVistaAdmin, Empleado adminEmpleado, MetodosEmpleado modeloEmpleado, Cliente nuevoCliente, MetodosCliente modeloCliente, Proveedor nuevoProveedor, MetodosProveedor modeloProveedor, Producto nuevoProducto, MetodosProducto modeloProducto, Venta nuevaVenta,  MetodosVenta modeloVenta) {
        this.nuevaVistaAdmin = nuevaVistaAdmin;
        
        this.adminEmpleado = adminEmpleado;
        this.nuevoCliente = nuevoCliente;
        this.nuevoProveedor = nuevoProveedor;
        this.nuevoProducto = nuevoProducto;
        this.nuevaVenta = nuevaVenta;
        
        this.modeloEmpleado = modeloEmpleado;
        this.modeloCliente = modeloCliente;
        this.modeloProveedor = modeloProveedor;
        this.modeloProducto = modeloProducto;
        this.modeloVenta = modeloVenta;
        
        
        ControladorCliente newControlCliente = new ControladorCliente(nuevaVistaAdmin, nuevoCliente, modeloCliente);
        ControladorProveedor newControlProveedor = new ControladorProveedor(nuevaVistaAdmin, nuevoProveedor, modeloProveedor);
        ControladorProducto newControlProducto = new ControladorProducto(nuevaVistaAdmin, nuevoProducto, modeloProducto);
    }
    
    
    
    
    
    
    //Constructor
    
    
    //Se carga vista de administrador
    //**********************************************************
    //Función que despliega la ventana del administrador
    //**********************************************************
    public void iniciarInterfazAdmin(){
        nuevaVistaAdmin.setTitle("LogIn Administrador MVC");
        nuevaVistaAdmin.setVisible(true);
        nuevaVistaAdmin.setLocationRelativeTo(null);
        
    }
    

    

    
}
