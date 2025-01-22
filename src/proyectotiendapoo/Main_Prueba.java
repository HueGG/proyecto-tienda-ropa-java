
package proyectotiendapoo;

import Controlador.Interfaz.ControladorInterfazPrincipal;
//import Modelo.Empleado.CifrarPasswordEmpleado;  ///BORRAR CUANDO YA NO SE REQUEIERAN PRUEBAS EN EL CIFRADO DE CONTRASEÑA DE EMPLEADOS
import Vista.VentanaPrincipal;

/**
 *
 */
public class Main_Prueba {
    
    public static void main (String[] args) {
        VentanaPrincipal nuevaVistaPrincipal = new VentanaPrincipal(); // Objeto e instanciacion de la ventana principal
        ControladorInterfazPrincipal controlVentanaPrincipal = new ControladorInterfazPrincipal(nuevaVistaPrincipal);
        
        controlVentanaPrincipal.iniciarVentanaPrincipal();
        /**
         * LINEAS DE CODIGO PARA CIFRAR CONTRASEÑA DE EMPLEADO
         * BORRAR CUANDO YA NO SE REQUERAN HACER PRUEBAS
         */
        //String contra = "12345";
        //System.out.println("Contraseña del empleado:  "+contra+"\nCifrada: "+CifrarPasswordEmpleado.md5(contra));
    }
    
}
