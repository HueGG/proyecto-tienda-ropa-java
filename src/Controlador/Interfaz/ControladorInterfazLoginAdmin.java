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
import Modelo.Empleado.CifrarPasswordEmpleado;
import Modelo.Empleado.Empleado;
import Modelo.Empleado.MetodosEmpleado;
import Modelo.Producto.MetodosProducto;
import Modelo.Producto.Producto;
import Modelo.Proveedor.MetodosProveedor;
import Modelo.Proveedor.Proveedor;
import Modelo.Venta.MetodosVenta;
import Modelo.Venta.Venta;
import Vista.VentanaAdministrador;
import Vista.VentanaLoginAdministrador;
import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *Clase que controla la interfaz de login 
 */
public class ControladorInterfazLoginAdmin implements ActionListener {
    //Objetos atributos de la clase
    private VentanaLoginAdministrador nuevaVistaLoginAdmin;
    private Empleado nuevoEmpleado;
    private MetodosEmpleado modeloEmpleado;
    
    //**********************************************************
    //************* Metodo constructor
    //**********************************************************
    public ControladorInterfazLoginAdmin(VentanaLoginAdministrador nuevaVistaLoginAdmin, Empleado nuevoEmpleado, MetodosEmpleado modeloEmpleado) {
        this.nuevaVistaLoginAdmin = nuevaVistaLoginAdmin;
        this.nuevoEmpleado = nuevoEmpleado;
        this.modeloEmpleado = modeloEmpleado;
        
        
        nuevaVistaLoginAdmin.btnIngresarAdministrador.addActionListener(this);
        nuevaVistaLoginAdmin.btnRegresarAdministrador.addActionListener(this);
        nuevaVistaLoginAdmin.btnSalirAdministrador.addActionListener(this);
    }
    
    //**********************************************************
    //Función que despliega el fomrulario para el inicio de sesion del ADMINISTRADOR O EMPLEADO
    //**********************************************************
    public void iniciarFormularioLoginAdmin(){
        nuevaVistaLoginAdmin.setTitle("LogIn Administrador MVC");
        nuevaVistaLoginAdmin.setVisible(true);
        nuevaVistaLoginAdmin.setLocationRelativeTo(null);
    }

    
    /** =====================================================================================
     * INICIA LA IMPLEMENTACION DEL MÉTODO ABSTRATO << actionPerformed(ActionEvent ae)  >>
     *  =====================================================================================
     */
    @Override
    public void actionPerformed(ActionEvent objetoAccionEvento) {
        
        if(objetoAccionEvento.getSource() == nuevaVistaLoginAdmin.btnIngresarAdministrador){
                                //********************************************************  
                                //***************** BOTON de Inicio de SESION ***********************
                                //********************************************************
            //Se valida que el formulario no este vacio
            if (valdarFormularioLoginAdmin()){
                //Se recibe un True si el formulario esta lleno
                //Se valida que el correo cumpla con el patron correcto
                if( validarEmail(nuevaVistaLoginAdmin.txtEmailAdministrador.getText()) ){
                    //El correo es valido
                    
                    nuevoEmpleado.setEmail(nuevaVistaLoginAdmin.txtEmailAdministrador.getText().toLowerCase());
                    nuevoEmpleado.setPassword( cifrarPasswordEmpleado() ); //SE INICIALIZA EL ATRIBUTO CON LA CONTRASEÑA YA CIFRADA
                    
                    //Inicia validacion de login
                    if( modeloEmpleado.iniciarSesion(nuevoEmpleado) ){
                        //Inicio de sesion exitoso
                        
                        JOptionPane.showMessageDialog(null, "Bienvenido: "+nuevoEmpleado.getNombre()+" "+ nuevoEmpleado.getApePaterno()+" "+nuevoEmpleado.getApeMaterno()+"\nCorreo: "+nuevoEmpleado.getEmail()+"Telefono: "+nuevoEmpleado.getTelefono()+"\nDireccion: "+nuevoEmpleado.getDireccion(), "INICIO DE SESION EXITOSO", JOptionPane.INFORMATION_MESSAGE);
                        iniciarInterfaceAdministrador(nuevoEmpleado); //Se llama a la funcion que despliega la ventana del administrador, enviando los datos del EMPLEADO que acaba de iniciar sesion
                        nuevaVistaLoginAdmin.dispose(); //Se cierra la ventana actual
                    }else{
                        //El inicio de sesion no es exitoso
                        JOptionPane.showMessageDialog(null, "Datos incorrectos, usuario o contraseña no valida\nIntente nuevamente", "DATOS INCORRECTOS", JOptionPane.WARNING_MESSAGE);
                    }
                    
                    
                }else{
                    //El patro del correo no es valido
                    //El texto no cumple con el patron de un correo electronico usuario@dominio.com
                    JOptionPane.showMessageDialog(null, "Ingrese un formato de correo valido\n example@domain.com", "FORMATO DE CORREO NO VALIDO", JOptionPane.WARNING_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Debe de ingresar su correo y contraseña para ingresar", "Formulario vacio", JOptionPane.WARNING_MESSAGE);
            }
        }else if( objetoAccionEvento.getSource() == nuevaVistaLoginAdmin.btnRegresarAdministrador ){
                                //********************************************************
                                //***************** BOTON de para Regresar a la ventana principal ***********************
                                //********************************************************
                    //Para abrir la ventana principal se requiere de crear primero un onjeto de esa clase
                VentanaPrincipal nuevaVistaPrincipal = new VentanaPrincipal();
                //La ventana principal se controla mediante una clase controlador, por lo que se crea un objeto de su clase controlador 
                ControladorInterfazPrincipal controlVentanaPrincipal = new ControladorInterfazPrincipal(nuevaVistaPrincipal);
                //Ahora que se cuenta con el objeto del controlador de la ventana principal, se llama al metodo que muestra la ventana principal
                controlVentanaPrincipal.iniciarVentanaPrincipal();
                nuevaVistaLoginAdmin.dispose();   //Se cierra la ventana actual
            
        }else if( objetoAccionEvento.getSource() == nuevaVistaLoginAdmin.btnSalirAdministrador ){
                                //********************************************************
                                //***************** BOTON de para SALIR DEL PROGRAMA ***********************
                                //********************************************************
                                
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                //La respuesta será 0 si se desea salir
                if(respuesta == 0){
                    System.exit(respuesta); //Si es 0 la respuesta, entonces se saldra
                }else{
                    //se desea continuar, por lo que no se hace nada
                }
                                
            
        }
        
    }
    
    
    /*****************************************************
     * *********************  METODOS DE CLASE
     * **************************************************
     */
    //Funcion para validar que el foorumlario de inicio de seion no este vacio
    private boolean valdarFormularioLoginAdmin(){
        boolean email = nuevaVistaLoginAdmin.txtEmailAdministrador.getText().isEmpty(); //true si esta vacio, false si contiene algo
        String contrasena = new String( nuevaVistaLoginAdmin.txtPasswordAdministrador.getPassword() ); //Se convierte a String el vector de caracteres del passwordField
        boolean password = contrasena.isEmpty();
        
        if(email || password){
            //Se retorna falso su alguno de los campos estan vacios
            return false;
        }else{
            //True si el formulario esta lleno
            return true;
        }
        
    }
    
    
    //Metodo para cifrar contraseña
    private String cifrarPasswordEmpleado(){
        String password = new String(nuevaVistaLoginAdmin.txtPasswordAdministrador.getPassword());
        String nuevaContrasena = CifrarPasswordEmpleado.md5(password); //Se cifra la contraseña 
        return nuevaContrasena; //Se retorna la contraseña cifrada commo cadena de caracteres
    }
    
    //Validar formato de correo electonico
    private boolean validarEmail(String correoEmpleado){
        /**
         * Requiere de la siguientes librerias
         * import java.util.regex.Matcher;
         * import java.util.regex.Pattern;
         */
        //Patron para validar email
        //Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$");
        
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        Matcher matcher = patron.matcher(correoEmpleado);
        return matcher.find(); //Retrna un true en caso de que el parametro cumpla con la sintaxis de un correo electronico
    }
    
    
    //Método para iniciar la interfaz del administrador
    private void iniciarInterfaceAdministrador( Empleado nuevoEmpleado ){
        
        VentanaAdministrador nuevaVistaAdministrador = new VentanaAdministrador(); //Objeto de la vista de interfaz cliente
        
        //Objetos de las clases
        Cliente nuevoCliente = new Cliente();
        Proveedor nuevoProveedor = new Proveedor();
        Producto nuevoProducto = new Producto();
        
        //Objetos de los modelos
        MetodosCliente modeloCliente = new MetodosCliente();
        MetodosProveedor modeloProveedor = new MetodosProveedor();
        MetodosProducto modeloProducto = new MetodosProducto();
       
        //Objetos para manipular las ventas
        Venta nuevaVenta = new Venta();
        MetodosVenta modeloVenta = new MetodosVenta();
        
        
        //Se crea el objeto que controla la interfaz del cliente y se inicializan los atributos necesarios
        ControladorInterfazAdministrador controlAdmin = new ControladorInterfazAdministrador(nuevaVistaAdministrador, nuevoEmpleado, modeloEmpleado, nuevoCliente, modeloCliente, nuevoProveedor, modeloProveedor, nuevoProducto, modeloProducto, nuevaVenta, modeloVenta);
        controlAdmin.iniciarInterfazAdmin();
        nuevaVistaLoginAdmin.dispose();
        
        //ControladorInterfazCliente controlDeInterfaceCliente = new ControladorInterfazCliente(nuevaVistaAdministrador, nuevoEmpleado, modeloCliente, nuevoProducto, modeloProducto, nuevaVenta, modeloVenta);
        //controlDeInterfaceCliente.iniciarInterfazCliente(); //Se inicia interaz del cliente
        //nuevaVistaLoginCliente.dispose(); //Se cierra la ventana actual
        
    }   //Finaliza método
    
}       //Fin de la clase
