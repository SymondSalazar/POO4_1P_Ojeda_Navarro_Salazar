package proyecto.leerArchivo;

import java.util.ArrayList;

import proyecto.usuario.Cliente;
import proyecto.usuario.Usuario;

public class parseData {    

    public static ArrayList<Usuario> parseUsuarios() {
    ArrayList<Usuario> usuariosReturn = new ArrayList<>();
    ArrayList<String> usuariosFile = ManejoArchivos.LeeFichero("Data/Usuarios.txt");
    ArrayList<String> clientesFile = ManejoArchivos.LeeFichero("Data/Clientes.txt");
    ArrayList<String> repartidoresFile = ManejoArchivos.LeeFichero("Data/Repartidores.txt");

    for (String usuario : usuariosFile) {
        String[] datosPrimarios = usuario.split("\\|");
        String codigoUnico = datosPrimarios[0];
        String cedula = datosPrimarios[1];
        String nombres = datosPrimarios[2];
        String apellidos = datosPrimarios[3];
        String userName = datosPrimarios[4];
        String contrasena = datosPrimarios[5];
        String correo = datosPrimarios[6];
        String tipoUsuario = datosPrimarios[7];

        if (tipoUsuario.equals("C")) {
            for (String cliente : clientesFile) {
                String[] datosSecundarios = cliente.split("\\|");
                if (datosSecundarios[0].equals(codigoUnico)) {
                    String celular = datosSecundarios[4];
                    String direccion = datosSecundarios[5];
                    usuariosReturn.add(new Cliente(
                        codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, celular, direccion
                    ));
                    break;
                }
            }
        } else {
            for (String repartidor : repartidoresFile) {
                String[] datosSecundarios = repartidor.split("\\|");
                if (datosSecundarios[0].equals(codigoUnico)) {
                    String empresa = datosSecundarios[4];
                    usuariosReturn.add(new proyecto.usuario.Repartidor(
                        codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, empresa
                    ));
                    break;
                }
            }
        }
    }

        return usuariosReturn;
    }


    public static void saveData(ArrayList<Usuario> usuarios, String tipo) {
        ArrayList<String> lineasCliente = new ArrayList<>();
        ArrayList<String> lineasRepartidor = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente) {
                lineasCliente.add(usuario.toString());
            } else {
                lineasRepartidor.add(usuario.toString());
            }
        }

        if (tipo.equals("C")) {
            lineasCliente.add(0, "CodigoUnico|Cedula|Nombres|Apellidos|UserName|Contrasena|Correo|Celular|Direccion");
            ManejoArchivos.EscribirArchivo("Clientes.txt", lineasCliente);
        } else {
            ManejoArchivos.EscribirArchivo("Repartidores.txt", lineasRepartidor);
        }
}
}
