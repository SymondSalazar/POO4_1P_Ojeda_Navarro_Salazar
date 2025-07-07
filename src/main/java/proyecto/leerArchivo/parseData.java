package proyecto.leerArchivo;

import java.util.ArrayList;
import java.util.List;

import proyecto.producto.Producto;
import proyecto.TipoProducto;
import proyecto.usuario.Cliente;
import proyecto.usuario.Usuario;
import proyecto.usuario.Repartidor;

/**
 * Clase para parsear datos desde archivos
 */
public class ParseData {    

    /**
     * Parsea usuarios desde archivos de datos
     */
    public static List<Usuario> parseUsuarios() {
        List<Usuario> usuariosReturn = new ArrayList<>();
        
        try {
            ArrayList<String> usuariosFile = ManejoArchivos.LeeFichero("Data/Usuarios.txt");
            ArrayList<String> clientesFile = ManejoArchivos.LeeFichero("Data/Clientes.txt");
            ArrayList<String> repartidoresFile = ManejoArchivos.LeeFichero("Data/Repartidores.txt");

<<<<<<< HEAD
    public static ArrayList<Usuario> parseUsuarios() {
        ArrayList<Usuario> usuariosReturn = new ArrayList<>();
        
        try {
            ArrayList<String> usuariosFile = ManejoArchivos.LeeFichero("Data/Usuarios.txt");
            ArrayList<String> clientesFile = ManejoArchivos.LeeFichero("Data/Clientes.txt");
            ArrayList<String> repartidoresFile = ManejoArchivos.LeeFichero("Data/Repartidores.txt");

            for (String usuario : usuariosFile) {
                if (usuario.startsWith("Código")) continue; // Saltar header
=======
            for (String usuario : usuariosFile) {
                if (usuario.startsWith("Código") || usuario.trim().isEmpty()) continue; // Saltar header y líneas vacías
>>>>>>> 99659700cddf95e82a7b1217313e68afdafe0297
                
                Usuario usuarioCreado = crearUsuario(usuario, clientesFile, repartidoresFile);
                if (usuarioCreado != null) {
                    usuariosReturn.add(usuarioCreado);
                }
            }
<<<<<<< HEAD
=======
            
            System.out.println("Usuarios parseados exitosamente: " + usuariosReturn.size());
            
>>>>>>> 99659700cddf95e82a7b1217313e68afdafe0297
        } catch (Exception e) {
            System.err.println("Error al parsear usuarios: " + e.getMessage());
        }

        return usuariosReturn;
    }
    
<<<<<<< HEAD
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
<<<<<<< HEAD
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
=======
    
    private static Cliente crearCliente(String codigoUnico, String cedula, String nombres, String apellidos, 
                                       String userName, String contrasena, String correo, ArrayList<String> clientesFile) {
        for (String cliente : clientesFile) {
            if (cliente.startsWith("Código")) continue; // Saltar header
            
            String[] datosSecundarios = cliente.split("\\|");
            if (datosSecundarios.length >= 6 && datosSecundarios[0].equals(codigoUnico)) {
                String celular = datosSecundarios[4];
                String direccion = datosSecundarios[5];
                return new Cliente(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, celular, direccion);
            }
        }
        return null;
    }
    
    private static Repartidor crearRepartidor(String codigoUnico, String cedula, String nombres, String apellidos,
                                                              String userName, String contrasena, String correo, ArrayList<String> repartidoresFile) {
>>>>>>> cd9cfcea8cb4eccba535c2284f5086c105bd0576
        for (String repartidor : repartidoresFile) {
            if (repartidor.startsWith("Código")) continue; // Saltar header
            
            String[] datosSecundarios = repartidor.split("\\|");
            if (datosSecundarios.length >= 5 && datosSecundarios[0].equals(codigoUnico)) {
                String empresa = datosSecundarios[4];
                return new Repartidor(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, empresa);
            }
        }
=======
    /**
     * Crea un usuario específico basado en el tipo
     */
    private static Usuario crearUsuario(String lineaUsuario, ArrayList<String> clientesFile, ArrayList<String> repartidoresFile) {
        try {
            String[] datosPrimarios = lineaUsuario.split("\\|");
            if (datosPrimarios.length < 8) {
                System.err.println("Línea de usuario con formato incorrecto: " + lineaUsuario);
                return null;
            }
            
            String codigoUnico = datosPrimarios[0].trim();
            String cedula = datosPrimarios[1].trim();
            String nombres = datosPrimarios[2].trim();
            String apellidos = datosPrimarios[3].trim();
            String userName = datosPrimarios[4].trim();
            String contrasena = datosPrimarios[5].trim();
            String correo = datosPrimarios[6].trim();
            String tipoUsuario = datosPrimarios[7].trim();

            if ("C".equals(tipoUsuario)) {
                return crearCliente(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, clientesFile);
            } else if ("R".equals(tipoUsuario)) {
                return crearRepartidor(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, repartidoresFile);
            } else {
                System.err.println("Tipo de usuario desconocido: " + tipoUsuario);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error al crear usuario desde línea: " + lineaUsuario + " - " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Crea un cliente con sus datos específicos
     */
    private static Cliente crearCliente(String codigoUnico, String cedula, String nombres, String apellidos, 
                                       String userName, String contrasena, String correo, ArrayList<String> clientesFile) {
        for (String cliente : clientesFile) {
            if (cliente.startsWith("Código") || cliente.trim().isEmpty()) continue; // Saltar header y líneas vacías
            
            String[] datosSecundarios = cliente.split("\\|");
            if (datosSecundarios.length >= 6 && datosSecundarios[0].trim().equals(codigoUnico)) {
                try {
                    String celular = datosSecundarios[4].trim();
                    String direccion = datosSecundarios[5].trim();
                    return new Cliente(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, celular, direccion);
                } catch (Exception e) {
                    System.err.println("Error al crear cliente con código: " + codigoUnico + " - " + e.getMessage());
                    return null;
                }
            }
        }
        System.err.println("No se encontraron datos adicionales para el cliente: " + codigoUnico);
        return null;
    }
    
    /**
     * Crea un repartidor con sus datos específicos
     */
    private static Repartidor crearRepartidor(String codigoUnico, String cedula, String nombres, String apellidos,
                                             String userName, String contrasena, String correo, ArrayList<String> repartidoresFile) {
        for (String repartidor : repartidoresFile) {
            if (repartidor.startsWith("Código") || repartidor.trim().isEmpty()) continue; // Saltar header y líneas vacías
            
            String[] datosSecundarios = repartidor.split("\\|");
            if (datosSecundarios.length >= 5 && datosSecundarios[0].trim().equals(codigoUnico)) {
                try {
                    String empresa = datosSecundarios[4].trim();
                    return new Repartidor(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo, empresa);
                } catch (Exception e) {
                    System.err.println("Error al crear repartidor con código: " + codigoUnico + " - " + e.getMessage());
                    return null;
                }
            }
        }
        System.err.println("No se encontraron datos adicionales para el repartidor: " + codigoUnico);
>>>>>>> 99659700cddf95e82a7b1217313e68afdafe0297
        return null;
    }

    /**
     * Guarda datos de usuarios en archivos
     */
    public static void saveUserData(List<Usuario> usuarios, String tipo) {
        ArrayList<String> lineasCliente = new ArrayList<>();
        ArrayList<String> lineasRepartidor = new ArrayList<>();
        ArrayList<String> lineasUsuario = new ArrayList<>();

        // Agregar headers
        lineasUsuario.add("Código|Cédula|Nombres|Apellidos|UserName|Contraseña|Correo|Tipo");
        lineasCliente.add("Código|Cédula|Nombres|Apellidos|Celular|Dirección");
        lineasRepartidor.add("Código|Cédula|Nombres|Apellidos|Empresa");

        for (Usuario usuario : usuarios) {
            // Línea principal del usuario
            String lineaUsuario = String.format("%s|%s|%s|%s|%s|%s|%s|%s",
                usuario.getCodigoUnico(), usuario.getCedula(), usuario.getNombres(),
                usuario.getApellidos(), usuario.getUserName(), usuario.getContrasena(),
                usuario.getCorreo(), usuario instanceof Cliente ? "C" : "R");
            lineasUsuario.add(lineaUsuario);

            if (usuario instanceof Cliente) {
                Cliente cliente = (Cliente) usuario;
                String lineaCliente = String.format("%s|%s|%s|%s|%s|%s",
                    cliente.getCodigoUnico(), cliente.getCedula(), cliente.getNombres(),
                    cliente.getApellidos(), cliente.getCelular(), cliente.getDireccion());
                lineasCliente.add(lineaCliente);
            } else if (usuario instanceof Repartidor) {
                Repartidor repartidor = (Repartidor) usuario;
                String lineaRepartidor = String.format("%s|%s|%s|%s|%s",
                    repartidor.getCodigoUnico(), repartidor.getCedula(), repartidor.getNombres(),
                    repartidor.getApellidos(), repartidor.getEmpresa());
                lineasRepartidor.add(lineaRepartidor);
            }
        }

        try {
            ManejoArchivos.EscribirArchivo("Data/Usuarios.txt", lineasUsuario);
            ManejoArchivos.EscribirArchivo("Data/Clientes.txt", lineasCliente);
            ManejoArchivos.EscribirArchivo("Data/Repartidores.txt", lineasRepartidor);
            System.out.println("Datos de usuarios guardados exitosamente");
        } catch (Exception e) {
            System.err.println("Error al guardar datos de usuarios: " + e.getMessage());
        }
<<<<<<< HEAD
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
=======
>>>>>>> 99659700cddf95e82a7b1217313e68afdafe0297
    }

    /**
     * Parsea productos desde archivo
     */
    public List<Producto> parseProductos() {
        List<Producto> productosReturn = new ArrayList<>();
        
        try {
            ArrayList<String> productosFile = ManejoArchivos.LeeFichero("Data/Productos.txt");

            for (String producto : productosFile) {
                if (producto.startsWith("Código") || producto.trim().isEmpty()) continue; // Saltar header y líneas vacías
                
                Producto productoCreado = crearProducto(producto);
                if (productoCreado != null) {
                    productosReturn.add(productoCreado);
                }
            }
            
            System.out.println("Productos parseados exitosamente: " + productosReturn.size());
            
        } catch (Exception e) {
            System.err.println("Error al parsear productos: " + e.getMessage());
        }
        
        return productosReturn;
    }
    
    /**
     * Crea un producto desde una línea de datos
     */
    private Producto crearProducto(String lineaProducto) {
        try {
            String[] datos = lineaProducto.split("\\|");
            if (datos.length < 5) {
                System.err.println("Línea de producto con formato incorrecto: " + lineaProducto);
                return null;
            }
            
            String codigo = datos[0].trim();
            String categoriaStr = datos[1].trim();
            String nombre = datos[2].trim();
            double precio = Double.parseDouble(datos[3].trim());
            int stock = Integer.parseInt(datos[4].trim());
            
            // Convertir string de categoría a enum
            TipoProducto categoria;
            try {
                categoria = TipoProducto.valueOf(categoriaStr);
            } catch (IllegalArgumentException e) {
                System.err.println("Categoría de producto desconocida: " + categoriaStr + ". Usando ROPA por defecto.");
                categoria = TipoProducto.Ropa;
            }
            
            return new Producto(categoria, codigo, nombre, precio, stock);
        }  catch (Exception e) {
            System.err.println("Error al crear producto desde línea: " + lineaProducto + " - " + e.getMessage());
            return null;
        }
    }

    /**
     * Guarda datos de productos en archivo
     */
    public static void saveProductoData(List<Producto> productos) {
        ArrayList<String> lineas = new ArrayList<>();
        lineas.add("Código|Categoría|Nombre|Precio|Stock");
        
        for (Producto producto : productos) {
            lineas.add(producto.toString());
        }
        
        try {
            ManejoArchivos.EscribirArchivo("Data/Productos.txt", lineas);
            System.out.println("Datos de productos guardados exitosamente");
        } catch (Exception e) {
            System.err.println("Error al guardar datos de productos: " + e.getMessage());
        }
    }
<<<<<<< HEAD
=======

    /**
     * Método de prueba para verificar el funcionamiento
     */
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE PARSEO DE DATOS ===");
        
        try {
            // Probar parseo de usuarios
            List<Usuario> usuarios = parseUsuarios();
            System.out.println("\nUsuarios cargados:");
            for (Usuario usuario : usuarios) {
                System.out.println("- " + usuario.getClass().getSimpleName() + ": " + 
                                 usuario.getNombres() + " " + usuario.getApellidos());
            }

            // Probar parseo de productos
            ParseData parser = new ParseData();
            List<Producto> productos = parser.parseProductos();
            System.out.println("\nProductos cargados:");
            for (Producto producto : productos) {
                System.out.println("- " + producto.getNombre() + " (" + producto.getCategoria() + ")");
            }

            // Probar guardado de datos
            System.out.println("\nProbando guardado de datos...");
            saveUserData(usuarios, "ALL");
            saveProductoData(productos);
            
        } catch (Exception e) {
            System.err.println("Error en la prueba: " + e.getMessage());
        }
    }
>>>>>>> 99659700cddf95e82a7b1217313e68afdafe0297
}