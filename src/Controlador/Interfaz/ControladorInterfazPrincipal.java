/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Interfaz;

import Modelo.Cliente.Cliente;
import Modelo.Cliente.MetodosCliente;
import Modelo.Empleado.Empleado;
import Modelo.Empleado.MetodosEmpleado;
import Modelo.Producto.MetodosProducto;
import Modelo.Producto.Producto;
import Modelo.Proveedor.MetodosProveedor;
import Modelo.Proveedor.Proveedor;
import Vista.VentanaAdministrador;
import Vista.VentanaLoginAdministrador;
import Vista.VentanaLoginCliente;
import Vista.VentanaPrincipal;
import Vista.VentanaRegistroCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ASUS-TUF
 */
public class ControladorInterfazPrincipal implements ActionListener{
    
    private VentanaPrincipal nuevaVistaPrincipal;
    
    
    //private ControladorInterfazLoginCliente newControlLoginCliente;
    //**********************************************************
    //************* Metodo constructor
    //**********************************************************

    public ControladorInterfazPrincipal(VentanaPrincipal nuevaVistaPrincipal) {
        this.nuevaVistaPrincipal = nuevaVistaPrincipal;
        
        nuevaVistaPrincipal.btnAccesoAdministrador.addActionListener(this);
        nuevaVistaPrincipal.btnAccesoCliente.addActionListener(this);
        nuevaVistaPrincipal.btnSalir.addActionListener(this);
    }
    
    //**********************************************************
    //Función que despliega la ventana principal
    //**********************************************************
    public void iniciarVentanaPrincipal(){
        nuevaVistaPrincipal.setTitle("Ventana principal");
        nuevaVistaPrincipal.setVisible(true);
        nuevaVistaPrincipal.setLocationRelativeTo(null);
    }
    
    /** =====================================================================================
     * INICIA LA IMPLEMENTACION DEL MÉTODO ABSTRATO << actionPerformed(ActionEvent ae)  >>
     *  =====================================================================================
     */
    @Override
    public void actionPerformed(ActionEvent objetoAccionEvento) {
        if(objetoAccionEvento.getSource() == nuevaVistaPrincipal.btnAccesoCliente){
                                    /** **************************************************************
                                     *  ************ ACCESO A FORMULARIO LOGIN CLIENTE ***************
                                     * ***************************************************************
                                     */ 
            VentanaLoginCliente nuevaVistaLoginCliente = new VentanaLoginCliente(); // Objeto de la vista de formulario para inicio de sesion
            Cliente nuevoCliente = new Cliente(); //Objeto de la clase
            MetodosCliente modeloCliente = new MetodosCliente();    //Objeto del modelo para el cliente
            
            //Creacion de objeto e inicializacion de sus atributos mediante el  metodo constructor
            ControladorInterfazLoginCliente newControlLoginCliente = new ControladorInterfazLoginCliente(nuevaVistaLoginCliente, nuevoCliente, modeloCliente); //Se instancia el objeto newControlLoginCliente
            
            //Llamada a la funcion que despliega la interfaz de inicio de sesion para el cliente
            newControlLoginCliente.iniciarFormularioLoginCliente();
            //System.out.println("Boton para acceder como cliente");
            nuevaVistaPrincipal.dispose(); //Se cierra la ventana actual
            
        }else if(objetoAccionEvento.getSource() == nuevaVistaPrincipal.btnAccesoAdministrador){
                                    /** **************************************************************
                                     *  ************ ACCESO A FORMULARIO LOGIN CLIENTE ***************
                                     * ***************************************************************
                                     */ 
            
            VentanaLoginAdministrador nuevaVistaLoginAdmin = new VentanaLoginAdministrador(); //Objeto de la vista de formulario de Login para ADMINISTRADOR
            Empleado nuevoEmpleado = new Empleado(); //Objeto de la clase
            MetodosEmpleado modeloEmpleado = new MetodosEmpleado();
            
            //Creacion de objeto e inicializacion de sus atributos mediante el  metodo constructor
            ControladorInterfazLoginAdmin newControlLoginAdmin = new ControladorInterfazLoginAdmin(nuevaVistaLoginAdmin, nuevoEmpleado, modeloEmpleado);
            
            //Llamada a la funcion que despliega la interfaz de inicio de sesion para el ADMINISTRADOR O EMPLEADO
            newControlLoginAdmin.iniciarFormularioLoginAdmin();
            nuevaVistaPrincipal.dispose(); // Se cierra la ventana actual
            
            
        }else if( objetoAccionEvento.getSource() == nuevaVistaPrincipal.btnSalir ){
            System.exit(0); //Se cierra la APLICACION POR CCOMPLETO
        }
    }
    
    
    
}
