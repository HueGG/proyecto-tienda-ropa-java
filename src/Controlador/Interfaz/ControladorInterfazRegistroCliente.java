/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Interfaz;

import Modelo.Cliente.CifrarPassword;
import Modelo.Cliente.Cliente;
import Modelo.Cliente.MetodosCliente;
import Vista.VentanaLoginCliente;
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
public class ControladorInterfazRegistroCliente implements ActionListener{
    //Objetos atributos de la clase
    private VentanaRegistroCliente nuevaVistaRegistroCliente;
    private Cliente nuevoCliente;
    private MetodosCliente modeloCliente;
    
    //**********************************************************
    //************* Metodo constructor
    //**********************************************************

    public ControladorInterfazRegistroCliente(VentanaRegistroCliente nuevaVistaRegistroCliente, Cliente nuevoCliente, MetodosCliente modeloCliente) {
        //Se inicializan los objetos atributos de la clase
        this.nuevaVistaRegistroCliente = nuevaVistaRegistroCliente;
        this.nuevoCliente = nuevoCliente;
        this.modeloCliente = modeloCliente;
        
        //Se indica donde se utilizar el ActionListener
        nuevaVistaRegistroCliente.btnAutoRegistroCliente.addActionListener(this);
        nuevaVistaRegistroCliente.btnRegresar.addActionListener(this);
        
    }

    //**********************************************************
    //Función que despliega el fomrulario para autorregistro de cliente
    //**********************************************************
    public void iniciarFormularioRegistroCliente(){
        nuevaVistaRegistroCliente.setTitle("Registro Cliente MVC");
        nuevaVistaRegistroCliente.setVisible(true);
        nuevaVistaRegistroCliente.setLocationRelativeTo(null);
        //nuevaVistaProveedor.txtIdPersona.setVisible(false);
    }
    
    
    
    
    
    /** =====================================================================================
     * INICIA LA IMPLEMENTACION DEL MÉTODO ABSTRATO << actionPerformed(ActionEvent ae)  >>
     *  =====================================================================================
     */
    @Override
    public void actionPerformed(ActionEvent objetoAccionEvento) {
        
        //********************************************************
        //***************** BOTON REGISTRAR ***********************
        //********************************************************
        
        if( objetoAccionEvento.getSource() == nuevaVistaRegistroCliente.btnAutoRegistroCliente ){
            
            
            //Primero se valida que el fomrulario no este vacio
            if( validarFormularioRegistro() ){
                //Si el formulario esta lleno, se valida que la contraseña se confirmada
                if(confirmarPassword()){
                    
                    //Se valida que el campo para el correo cumpla con el formato o sintaxis de un correo electronico
                    if( validarEmail(nuevaVistaRegistroCliente.txtCorreoCliente.getText()) ){
                        //Si el correo umple con el PATRON DE un correo, se continuca
                        
                        //Se valida que el correo no este registrado en la BD para evitar correos repetidos
                        nuevoCliente.setEmail(nuevaVistaRegistroCliente.txtCorreoCliente.getText().toLowerCase());
                        if( !modeloCliente.buscarCorreoCliente(nuevoCliente) ){
                            //Si el correo no existe entonces se continua con la inicializacion de los atributos
                            //Se procede a inicializar los atributos del cliente
                            nuevoCliente.setNombre(nuevaVistaRegistroCliente.txtNombreCliente.getText());
                            nuevoCliente.setApePaterno(nuevaVistaRegistroCliente.txtApePaternoCliente.getText());
                            nuevoCliente.setApeMaterno(nuevaVistaRegistroCliente.txtApeMaternoCliente.getText());
                            nuevoCliente.setTelefono(nuevaVistaRegistroCliente.txtTelefonoCliente.getText());
                            nuevoCliente.setDireccion(nuevaVistaRegistroCliente.txtAreaDireccionCliente.getText());
                            nuevoCliente.setEmail(nuevaVistaRegistroCliente.txtCorreoCliente.getText().toLowerCase());
                            nuevoCliente.setPassword(cifrarPassword()); //Se inicializa la contraseña ya cifrada

                            if(modeloCliente.registrarCliente(nuevoCliente)){
                                JOptionPane.showMessageDialog(null, "Se ha registrado exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                                //Despues del registro exitoso, se retorna a la ventana de inicio de sesion
                                //La ventana de inicio de sesion se controla mediante la clase ControladorInterfazLoginCliente
                                //Asi que antes se crean los objetos necesarios 
                                VentanaLoginCliente nuevaVistaLoginCliente = new VentanaLoginCliente();
                                Cliente nuevoCliente = new Cliente();
                                MetodosCliente modeloCliente = new MetodosCliente();
                                
                                ControladorInterfazLoginCliente newControlLoginCliente = new ControladorInterfazLoginCliente(nuevaVistaLoginCliente, nuevoCliente, modeloCliente);
                                newControlLoginCliente.iniciarFormularioLoginCliente(); //Se regresa al formulario de inicio de sesion
                                nuevaVistaRegistroCliente.dispose(); //Se cierra la ventana actula
                                
                            }else{
                                JOptionPane.showMessageDialog(null, "No se logro registrar sus datos", "Error de registro", JOptionPane.WARNING_MESSAGE);
                            }

                        }else{
                            JOptionPane.showMessageDialog(null, "El correo ya existe, intente con otro", "CORREO NO VALIDO", JOptionPane.WARNING_MESSAGE);
                        }
                        
                    }else{
                        //El texto no cumple con el patron de un correo electronico usuario@dominio.com
                        JOptionPane.showMessageDialog(null, "Ingrese un formato de correo valido\n example@domain.com", "FORMATO DE CORREO NO VALIDO", JOptionPane.WARNING_MESSAGE);
                    }
                    
                }else{
                    //La contraseña no esta bien confirmada
                    JOptionPane.showMessageDialog(null, "LA CONTRASEÑA NO ES IGUAL", "CONTRASEÑAS DIFERENTES", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                //Formulario tiene campos vacios
                JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            }
            
        }else if( objetoAccionEvento.getSource() == nuevaVistaRegistroCliente.btnRegresar ){
                        //********************************************************
                        //***************** BOTON de REGRESAR ***********************
                        //********************************************************
            /**La ventana de inicio de sesion se controla mediante la clase ControladorInterfazLoginCliente
             * La cual requiere de 3 parametros que son objetos de la VISTA, CLASE y MODELO de la clase
             * Asi que antes se crean los objetos necesarios 
             */
            
            VentanaLoginCliente nuevaVistaLoginCliente = new VentanaLoginCliente(); //Objeto de la VISTA para la interfaz de LogIn
            Cliente nuevoCliente = new Cliente();   //Objeto de la Clase
            MetodosCliente modeloCliente = new MetodosCliente();    //Objeto del MODELO de la Clase, que permite la funcionalidad del CRUD

            ControladorInterfazLoginCliente newControlLoginCliente = new ControladorInterfazLoginCliente(nuevaVistaLoginCliente, nuevoCliente, modeloCliente);
            newControlLoginCliente.iniciarFormularioLoginCliente(); //Se regresa al formulario de inicio de sesion
            nuevaVistaRegistroCliente.dispose(); //Se cierra la ventana actual
            
        }
        
    } ///Finaliza metodo abstractp
    
    
    //Método para validar que el fomrulario no este vacio
    private boolean validarFormularioRegistro(){
        //Se valida que los campos del formulario no esten vacios
        boolean nombre = nuevaVistaRegistroCliente.txtNombreCliente.getText().isEmpty();
        boolean apePaterno = nuevaVistaRegistroCliente.txtApePaternoCliente.getText().isEmpty();
        //boolean apeMaterno = nuevaVistaRegistroCliente.txtApeMaternoCliente.getText().isEmpty(); // Basta con un apellido
        boolean telefono = nuevaVistaRegistroCliente.txtTelefonoCliente.getText().isEmpty();
        boolean direccion = nuevaVistaRegistroCliente.txtAreaDireccionCliente.getText().isEmpty();
        boolean correo = nuevaVistaRegistroCliente.txtCorreoCliente.getText().isEmpty();
        
        //LA contraseña se obtiene de un passwordfield que devuleve un ARREGLO DE CARACTERES, por lo que antes es necesario volverlo unn STRING
        String password = new String(nuevaVistaRegistroCliente.txtPasswordCliente.getPassword());
        String passwordConfirmar = new String(nuevaVistaRegistroCliente.txtPasswordConfirmarCliente.getPassword());
       //Validar que los campoos no esten vacios
        if ( nombre || apePaterno || telefono || direccion || correo || password.isEmpty() || passwordConfirmar.isEmpty() ){
            //Se retorna falso en caso de que algun campo dle formulario este vacio
            return false;
        }else{
            //Formulario lleno
            return true;
        }
    }
    
    //Método para confirmar que la contraseña es la misma
    private boolean confirmarPassword(){
        //LA contraseña se obtiene de un passwordfield que devuleve un ARREGLO DE CARACTERES, por lo que antes es necesario volverlo unn STRING
        String password = new String(nuevaVistaRegistroCliente.txtPasswordCliente.getPassword());
        String passwordConfirmar = new String(nuevaVistaRegistroCliente.txtPasswordConfirmarCliente.getPassword());
        
        //Antes de validar la contraseña, hay que validar que el formulario esta lleno
        
        if( password.equals(passwordConfirmar) ){
            //ambas contraseñas son iguales
            return true;
        }else{
            //Las contraseñas no son iguales
            return false;
        }
        
        
    } //Fin de confirmacion de contraseña
    
    
    //Metodo para cifrar contraseña
    private String cifrarPassword(){
        String password = new String(nuevaVistaRegistroCliente.txtPasswordCliente.getPassword());
        String nuevaContrasena = CifrarPassword.md5(password); //Se cifra la contraseña
        return nuevaContrasena; //Se retorna la contraseña cifrada
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
    
}
