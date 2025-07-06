package proyecto.leerArchivo;

import java.util.ArrayList;

import proyecto.producto.Producto;
import proyecto.usuario.Cliente;
import proyecto.usuario.Usuario;

public class ParseData {    


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


    public static void saveUserData(ArrayList<Usuario> usuarios, String tipo) {
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

    public ArrayList<Producto> parseProductos(){
        ArrayList<Producto> productosReturn = new ArrayList<>();
        ArrayList<String> productosFile = ManejoArchivos.LeeFichero("Data/Productos.txt");

        for (String producto : productosFile) {
            String[] datos = producto.split("\\|");
            String codigo = datos[0];
            if (codigo.equals("Código")) continue; // Skip header line
            String categoria = datos[1];
            String nombre = datos[2];
            double precio = Double.parseDouble(datos[3]);
            int stock = Integer.parseInt(datos[4]);
            productosReturn.add(new Producto(
                proyecto.producto.TipoProducto.valueOf(categoria), codigo, nombre, precio, stock
            ));
        }
        return productosReturn;
    }

    public static void saveProductoData(ArrayList<Producto> productos) {
        ArrayList<String> lineas = new ArrayList<>();
        lineas.add("Código|Categoría|Nombre|Precio|Stock");
        for (Producto producto : productos) {
            lineas.add(producto.toString());
        }
        ManejoArchivos.EscribirArchivo("Productos.txt", lineas);
    }

    public static void main(String[] args) {
        // Aquí puedes probar el método parseUsuarios() o parseProductos()
        ArrayList<Usuario> usuarios = parseUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }

        ParseData parser = new ParseData();
        ArrayList<Producto> productos = parser.parseProductos();
        for (Producto producto : productos) {
            System.out.println(producto);
        }

        // Puedes probar el método saveUserData() y saveProductData() aquí
        saveUserData(usuarios, "C");
        saveUserData(usuarios, "R");
        saveProductoData(productos);
        
    }
}
