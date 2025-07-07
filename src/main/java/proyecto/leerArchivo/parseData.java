package proyecto.leerArchivo;

import java.util.ArrayList;

import proyecto.producto.Producto;
import proyecto.usuario.Cliente;
import proyecto.usuario.Usuario;
import proyecto.usuario.Repartidor;

public class ParseData {    


    public static ArrayList<Usuario> parseUsuarios() {
        ArrayList<Usuario> usuariosReturn = new ArrayList<>();
        
        try {
            ArrayList<String> usuariosFile = ManejoArchivos.LeeFichero("Data/Usuarios.txt");
            ArrayList<String> clientesFile = ManejoArchivos.LeeFichero("Data/Clientes.txt");
            ArrayList<String> repartidoresFile = ManejoArchivos.LeeFichero("Data/Repartidores.txt");

            for (String usuario : usuariosFile) {
                if (usuario.startsWith("Código")) continue; // Saltar header
                
                Usuario usuarioCreado = crearUsuario(usuario, clientesFile, repartidoresFile);
                if (usuarioCreado != null) {
                    usuariosReturn.add(usuarioCreado);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al parsear usuarios: " + e.getMessage());
        }

        return usuariosReturn;
    }
    
    private static Usuario crearUsuario(String lineaUsuario, ArrayList<String> clientesFile, ArrayList<String> repartidoresFile) {
        try {
            String[] datosPrimarios = lineaUsuario.split("\\|");
            if (datosPrimarios.length < 8) return null;
            
            String codigoUnico = datosPrimarios[0];
            String cedula = datosPrimarios[1];
            String nombres = datosPrimarios[2];
            String apellidos = datosPrimarios[3];
            String userName = datosPrimarios[4];
            String contrasena = datosPrimarios[5];
            String correo = datosPrimarios[6];
            String tipoUsuario = datosPrimarios[7];

            if ("C".equals(tipoUsuario)) {
                return crearCliente(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, clientesFile);
            } else {
                return crearRepartidor(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, repartidoresFile);
            }
        } catch (Exception e) {
            System.err.println("Error al crear usuario desde línea: " + lineaUsuario);
            return null;
        }
    }
        private static Cliente crearCliente(String codigoUnico, String cedula, String nombres, String apellidos, String userName, String contrasena, String correo, ArrayList<String> clientesFile) {
            for (String cliente : clientesFile) {
                if (cliente.startsWith("Código")) continue; 
                String[] datosSecundarios = cliente.split("\\|");
                if (datosSecundarios.length >= 6 && datosSecundarios[0].equals(codigoUnico)) {
                    String celular = datosSecundarios[4];
                    String direccion = datosSecundarios[5];
                    return new Cliente(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, celular, direccion);
                }
            }
            return null;
        }
    private static Repartidor crearRepartidor(String codigoUnico, String cedula, String nombres, String apellidos, String userName, String contrasena, String correo, ArrayList<String> repartidoresFile) {
        for (String repartidor : repartidoresFile) {
            if (repartidor.startsWith("Código")) continue; // Saltar header
            
            String[] datosSecundarios = repartidor.split("\\|");
            if (datosSecundarios.length >= 5 && datosSecundarios[0].equals(codigoUnico)) {
                String empresa = datosSecundarios[4];
                return new Repartidor(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, empresa);
            }
        }
        return null;
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
        
        try {
            ArrayList<String> productosFile = ManejoArchivos.LeeFichero("Data/Productos.txt");

            for (String producto : productosFile) {
                if (producto.startsWith("Código")) continue; // Saltar header
                
                Producto productoCreado = crearProducto(producto);
                if (productoCreado != null) {
                    productosReturn.add(productoCreado);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al parsear productos: " + e.getMessage());
        }
        
        return productosReturn;
    }
    
    private Producto crearProducto(String lineaProducto) {
        try {
            String[] datos = lineaProducto.split("\\|");
            if (datos.length < 5) return null;
            
            String codigo = datos[0];
            String categoria = datos[1];
            String nombre = datos[2];
            double precio = Double.parseDouble(datos[3]);
            int stock = Integer.parseInt(datos[4]);
            
            return new Producto(proyecto.producto.TipoProducto.valueOf(categoria), codigo, nombre, precio, stock);
        } catch (Exception e) {
            System.err.println("Error al crear producto desde línea: " + lineaProducto);
            return null;
        }
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