/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Interfaz;

import Modelo.Cliente.CifrarPassword;
import Modelo.Cliente.Cliente;
import Modelo.Cliente.MetodosCliente;
import Modelo.Producto.MetodosProducto;
import Modelo.Producto.Producto;
import Modelo.Venta.MetodosVenta;
import Modelo.Venta.Venta;
import Vista.VentanaCliente;
import Vista.VentanaLoginCliente;
import Vista.VentanaPrincipal;
import Vista.VentanaRegistroCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * DESCRIPCION: Esta clase sirve de puente de comuinicacion entre el MOdelo de Cliente y la Vista o formulario del formulario de registro de cliente
 */
/**
 * El CONTROLADOR tiene que estar DIRECTAMENTE RELACIONADO con la VISTA de la aplicacion
 * por lo que se debe de implementar un ActionListener que requiere de la liberia java.awt.event.ActionListener;
 */
public class ControladorInterfazLoginCliente implements ActionListener{
    //Objetos atributos de la clase
    private VentanaLoginCliente nuevaVistaLoginCliente;
    private Cliente nuevoCliente;
    private MetodosCliente modeloCliente;
    
    
    //**********************************************************
    //************* Metodo constructor
    //**********************************************************

    public ControladorInterfazLoginCliente(VentanaLoginCliente nuevaVistaLoginCliente, Cliente nuevoCliente, MetodosCliente modeloCliente) {
        this.nuevaVistaLoginCliente = nuevaVistaLoginCliente;
        this.nuevoCliente = nuevoCliente;
        this.modeloCliente = modeloCliente;
        
        //Se indica donde se utilizar el ActionListener
        nuevaVistaLoginCliente.btnIngresarCliente.addActionListener(this);
        nuevaVistaLoginCliente.btnRegistrarseCliente.addActionListener(this);
        nuevaVistaLoginCliente.btnRegresarCliente.addActionListener(this);
        nuevaVistaLoginCliente.btnSalirCliente.addActionListener(this);
    }
    /*
    public ControladorInterfazLoginCliente(VentanaLoginCliente nuevaVistaLoginCliente) {
        this.nuevaVistaLoginCliente = nuevaVistaLoginCliente;
    }
    */
    
    
    
    //**********************************************************
    //Función que despliega el fomrulario para el inicio de sesion del cliente
    //**********************************************************
    public void iniciarFormularioLoginCliente(){
        nuevaVistaLoginCliente.setTitle("LogIn Cliente MVC");
        nuevaVistaLoginCliente.setVisible(true);
        nuevaVistaLoginCliente.setLocationRelativeTo(null);
        //nuevaVistaProveedor.txtIdPersona.setVisible(false);
    }

    
    /** =====================================================================================
     * INICIA LA IMPLEMENTACION DEL MÉTODO ABSTRATO << actionPerformed(ActionEvent ae)  >>
     *  =====================================================================================
     */
    
    @Override
    public void actionPerformed(ActionEvent objetoAccionEvento) {
        
        if(objetoAccionEvento.getSource() == nuevaVistaLoginCliente.btnIngresarCliente){
                                //********************************************************
                                //***************** BOTON de Inicio de seision ***********************
                                //********************************************************
            if( valdarFormularioLoginCliente() ){
                //Formulario lleno
                //Ahora se valida que el correo cumpla con el patron
                if (validarEmail(nuevaVistaLoginCliente.txtEmailLoginCliente.getText())){
                    //El correo es valido
                    
                    nuevoCliente.setEmail(nuevaVistaLoginCliente.txtEmailLoginCliente.getText().toLowerCase());
                    //String passwordCifrada = cifrarPassword();
                    nuevoCliente.setPassword( cifrarPassword() );
                    //Iniica validacion de login
                    if( modeloCliente.iniciarSesion(nuevoCliente) ){
                        //INICIO DE SESION EXITOSO
                        JOptionPane.showMessageDialog(null, "Bienvenido: "+nuevoCliente.getNombre()+" "+ nuevoCliente.getApePaterno()+" "+nuevoCliente.getApeMaterno()+"\nCorreo: "+nuevoCliente.getEmail()+"Telefono: "+nuevoCliente.getTelefono()+"\nDireccion: "+nuevoCliente.getDireccion(), "INICIO DE SESION EXITOSO", JOptionPane.INFORMATION_MESSAGE);
                        iniciarInterfaceCliente(nuevoCliente); //Se inicia la interfaz del cliente, enviando los datos del cliente
                        
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
                                
        }else if( objetoAccionEvento.getSource() == nuevaVistaLoginCliente.btnRegistrarseCliente ){
            
                                //********************************************************
                                //***************** BOTON de para formulario de REGISTRO ***********************
                                //********************************************************
            //Se crean los objetos que serviran de parametros para el constructor de la clase ControladorInterfazRegistroCliente
            VentanaRegistroCliente nuevaVistaRegistroCliente = new VentanaRegistroCliente();
            Cliente clienteRegistro = new Cliente();
            MetodosCliente modeloClienteRegistro = new MetodosCliente();
            
            //Se crea un obejeto y se inicializa con los objetos previamente creados
            ControladorInterfazRegistroCliente newControlRegistroCliente = new ControladorInterfazRegistroCliente(nuevaVistaRegistroCliente, clienteRegistro, modeloClienteRegistro);;
            //Mediante el objeto del controlador, se llama a la fución para mostrar el formulario
            newControlRegistroCliente.iniciarFormularioRegistroCliente();
            nuevaVistaLoginCliente.dispose();
            
        }else if( objetoAccionEvento.getSource() == nuevaVistaLoginCliente.btnRegresarCliente ){
            
                                //********************************************************
                                //***************** BOTON de para Regresar a la ventana principal ***********************
                                //********************************************************
            
            //Para abrir la ventana principal se requiere de crear primero un onjeto de esa clase
            VentanaPrincipal nuevaVistaPrincipal = new VentanaPrincipal();
            //La ventana principal se controla mediante una clase controlador, por lo que se crea un objeto de su clase controlador 
            ControladorInterfazPrincipal controlVentanaPrincipal = new ControladorInterfazPrincipal(nuevaVistaPrincipal);
            //Ahora que se cuenta con el objeto del controlador de la ventana principal, se llama al metodo que muestra la ventana principal
            controlVentanaPrincipal.iniciarVentanaPrincipal();
            nuevaVistaLoginCliente.dispose();   //Se cierra la ventana actual
            
        }else if( objetoAccionEvento.getSource() == nuevaVistaLoginCliente.btnSalirCliente ){
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
        
    }  //FINALIZA IMPLEMENTACION DE METODO ABSTRACTO
    
    
    
    //Funcion para validar que el foorumlario de inicio de seion no este vacio
    private boolean valdarFormularioLoginCliente(){
        boolean email = nuevaVistaLoginCliente.txtEmailLoginCliente.getText().isEmpty(); //true si esta vacio, false si contiene algo
        String contrasena = new String( nuevaVistaLoginCliente.txtPasswordLoginCliente.getPassword() ); //Se convierte a String el vector de caracteres del passwordField
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
    private String cifrarPassword(){
        String password = new String(nuevaVistaLoginCliente.txtPasswordLoginCliente.getPassword());
        String nuevaContrasena = CifrarPassword.md5(password); //Se cifra la contraseña 
        return nuevaContrasena; //Se retorna la contraseña cifrada commo cadena de caracteres
    }
    
    //Validar formato de correo electonico
    private boolean validarEmail(String correoCliente){
        /**
         * Requiere de la siguientes librerias
         * import java.util.regex.Matcher;
         * import java.util.regex.Pattern;
         */
        //Patron para validar email
        //Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$");
        
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        Matcher matcher = patron.matcher(correoCliente);
        return matcher.find(); //Retrna un true en caso de que el parametro cumpla con la sintaxis de un correo electronico
    }
    
    //Método para iniciar la interfaz del cliente
    private void iniciarInterfaceCliente( Cliente nuevoCliente ){
        
        VentanaCliente nuevaVistaCliente = new VentanaCliente(); //Objeto de la vista de interfaz cliente
        //Objetos para manipular los datos del cliente
        //Cliente nuevoCliente;
        MetodosCliente modeloCliente = new MetodosCliente();

        //Objetos para manipular los datos de los productos
        Producto nuevoProducto = new Producto();
        MetodosProducto modeloProducto = new MetodosProducto();

        //Objetos para manipular las ventas
        Venta nuevaVenta = new Venta();
        MetodosVenta modeloVenta = new MetodosVenta();
        
        //Se crea el objeto que controla la interfaz del cliente y se inicializan los atributos necesarios
        ControladorInterfazCliente controlDeInterfaceCliente = new ControladorInterfazCliente(nuevaVistaCliente, nuevoCliente, modeloCliente, nuevoProducto, modeloProducto, nuevaVenta, modeloVenta);
        controlDeInterfaceCliente.iniciarInterfazCliente(); //Se inicia interaz del cliente
        nuevaVistaLoginCliente.dispose(); //Se cierra la ventana actual
        
    }   //Finaliza método
    
    
} //FINALIZA CLASE
