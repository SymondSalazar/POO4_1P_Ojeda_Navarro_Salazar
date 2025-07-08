package proyecto;

import java.util.List;
import java.util.ArrayList;

import proyecto.leerArchivo.ParseData;
import proyecto.usuario.*;
import proyecto.producto.Producto;
import proyecto.pedido.Pedido;
import proyecto.servicio.*;
import proyecto.utils.InputUtils;

/**
 * Clase principal del sistema de delivery que coordina todas las operaciones del sistema.
 * Maneja la autenticación de usuarios, procesamiento de pedidos, y la interfaz de usuario.
 */
public class Sistema {
    private List<Usuario> usuarios;
    private List<Producto> productos;
    private ProductoService productoService;
    private PagoService pagoService;
    private PedidoService pedidoService;
    
    /**
     * Constructor que inicializa el sistema cargando datos y servicios.
     */
    public Sistema() {
        inicializarSistema();
    }
    
    /**
     * Inicializa los componentes del sistema cargando usuarios, productos y servicios.
     * Maneja errores de inicialización asignando valores por defecto si es necesario.
     */
    private void inicializarSistema() {
        try {
            this.usuarios = ParseData.parseUsuarios();
            
            ParseData parseData = new ParseData();
            this.productos = parseData.parseProductos();
            
            this.productoService = new ProductoService();
            this.pagoService = new PagoService();
            

            // Extraer repartidores para el servicio de pedidos
            List<Repartidor> repartidores = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                if (usuario instanceof Repartidor) {
                    repartidores.add((Repartidor) usuario);
                }
            }
            this.pedidoService = new PedidoService(repartidores);
            this.pedidoService.setPedidos(ParseData.parsePedidos(usuarios,this.productos));
            System.out.println("Sistema inicializado correctamente");
            System.out.println("Usuarios cargados: " + usuarios.size());
            System.out.println("Productos cargados: " + productos.size());
            System.out.println("Repartidores disponibles: " + repartidores.size());
            
        } catch (Exception e) {
            System.err.println("Error al inicializar el sistema: " + e.getMessage());
            this.usuarios = new ArrayList<>();
            this.productos = new ArrayList<>();
            this.productoService = new ProductoService();
            this.pagoService = new PagoService();
            this.pedidoService = new PedidoService();
        }
    }

    /**
     * Proceso de inicio de sesión del usuario.
     * Permite intentos ilimitados hasta que se ingresen credenciales correctas.
     * 
     * @return El usuario autenticado, o null si hay errores
     */
    public Usuario iniciarSesion() {
        System.out.println("\n============ INICIAR SESIÓN ============");
        
        while (true) {
            String usuario = InputUtils.leerTextoNoVacio("Usuario: ");
            String contrasenia = InputUtils.leerTextoNoVacio("Contraseña: ");

            for (Usuario user : this.usuarios) {
                if (user.getUserName().equals(usuario) && user.getContrasena().equals(contrasenia)) {
                    System.out.println("✓ Credenciales correctas");
                    return user;
                }
            }
            
            System.out.println("✗ Credenciales incorrectas");
            System.out.println("Intente nuevamente...");
        }
    }
    
    /**
     * Valida la identidad del usuario autenticado mediante verificación adicional.
     * Para clientes verifica el número de celular, para repartidores la empresa.
     * 
     * @param user El usuario a validar
     * @return true si la validación es exitosa, false en caso contrario
     */
    public boolean validarUsuario(Usuario user) {
        if (user == null) {
            System.out.println("✗ Error al autenticar usuario");
            return false;
        }

        System.out.println("✓ Usuario autenticado exitosamente");
        char tipoUsuario = user.getCodigoUnico().charAt(0);
        
        if (tipoUsuario == 'C') {
            return validarCliente((Cliente) user);
        } else {
            return validarRepartidor((Repartidor) user);
        }
    }
    
    /**
     * Validación específica para clientes.
     * Confirma que el número de celular registrado es correcto.
     * 
     * @param cliente El cliente a validar
     * @return true si la validación es exitosa, false en caso contrario
     */
    private boolean validarCliente(Cliente cliente) {
        System.out.println("Rol detectado: CLIENTE");
        System.out.println("Bienvenid@ " + cliente.getNombreCompleto());
        System.out.println("Celular registrado: " + cliente.getCelular());
        
        boolean confirmacion = InputUtils.leerConfirmacion("¿Este número celular es correcto?");
        
        if (confirmacion) {
            System.out.println("✓ Identidad confirmada");
            return true;
        } else {
            System.out.println("✗ Verificación fallida");
            System.out.println("Por motivos de seguridad se cerrará sesión");
            return false;
        }
    }
    
    /**
     * Validación específica para repartidores.
     * Confirma que la empresa asignada es correcta.
     * 
     * @param repartidor El repartidor a validar
     * @return true si la validación es exitosa, false en caso contrario
     */
    private boolean validarRepartidor(Repartidor repartidor) {
        System.out.println("Rol detectado: REPARTIDOR");
        System.out.println("Bienvenid@ " + repartidor.getNombres() + " " + repartidor.getApellidos());
        System.out.println("Empresa asignada: " + repartidor.getEmpresa());
        
        boolean confirmacion = InputUtils.leerConfirmacion("¿Esta empresa es correcta?");
        
        if (confirmacion) {
            System.out.println("✓ Identidad confirmada");
            return true;
        } else {
            System.out.println("✗ Verificación fallida");
            System.out.println("Por motivos de seguridad se cerrará sesión");
            return false;
        }
    }
    
    /**
     * Muestra el menú principal correspondiente al tipo de usuario autenticado.
     * Redirige a menús específicos para clientes o repartidores.
     * 
     * @param user El usuario autenticado
     */
    public void mostrarMenuPrincipal(Usuario user) {
        if (user instanceof Cliente) {
            mostrarMenuCliente((Cliente) user);
        } else if (user instanceof Repartidor) {
            mostrarMenuRepartidor((Repartidor) user);
        }
    }
    
    /**
     * Menú específico para clientes con opciones de compra y gestión de pedidos.
     * Incluye opciones para comprar productos, gestionar pedidos, ver historial,
     * cerrar sesión o salir del sistema.
     * 
     * @param cliente El cliente autenticado
     */
    private void mostrarMenuCliente(Cliente cliente) {
        String[] opciones = {
            "Comprar producto",
            "Gestionar pedidos",
            "Ver historial de pedidos",
            "Cerrar sesión",
            "Salir del sistema"
        };
        
        boolean continuar = true;
        while (continuar) {
            int opcion = InputUtils.leerOpcionMenu(opciones);
            
            switch (opcion) {
                case 1:
                    procesarCompra(cliente);
                    break;
                case 2:
                    gestionarPedidosCliente(cliente);
                    break;
                case 3:
                    mostrarHistorialPedidos(cliente);
                    break;
                case 4:
                    continuar = false;
                    System.out.println("Cerrando sesión...");
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    
                    System.exit(0);
                    break;
            }
        }
    }
    
    /**
     * Menú específico para repartidores con opciones de gestión de entregas.
     * Incluye opciones para consultar pedidos, actualizar estados, marcar entregas,
     * cerrar sesión o salir del sistema.
     * 
     * @param repartidor El repartidor autenticado
     */
    private void mostrarMenuRepartidor(Repartidor repartidor) {
        String[] opciones = {
            "Consultar pedidos asignados",
            "Actualizar estado de pedido",
            "Marcar pedido como entregado",
            "Cerrar sesión",
            "Salir del sistema"
        };
        
        boolean continuar = true;
        while (continuar) {
            int opcion = InputUtils.leerOpcionMenu(opciones);
            
            switch (opcion) {
                case 1:
                    consultarPedidosRepartidor(repartidor);
                    break;
                case 2:
                    actualizarEstadoPedido(repartidor);
                    break;
                case 3:
                    marcarPedidoEntregado(repartidor);
                    break;
                case 4:
                    continuar = false;
                    System.out.println("Cerrando sesión...");
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                    break;
            }
        }
    }
    
    /**
     * Procesa una compra completa desde la selección hasta el pago.
     * Incluye selección de categoría, producto, cantidad, confirmación de pago
     * y creación del pedido si todo es exitoso.
     * 
     * @param cliente El cliente que realiza la compra
     */
    private void procesarCompra(Cliente cliente) {
        try {
            // Seleccionar categoría
            TipoProducto categoria = productoService.seleccionarCategoria();
            
            // Mostrar productos de la categoría
            List<Producto> productosDisponibles = productoService.mostrarProductosPorCategoria(productos, categoria);
            
            if (productosDisponibles.isEmpty()) {
                System.out.println("No hay productos disponibles en esta categoría.");
                return;
            }
            
            // Seleccionar producto
            Producto productoSeleccionado = productoService.seleccionarProducto(productosDisponibles);
            if (productoSeleccionado == null) return;
            
            // Seleccionar cantidad
            int cantidad = productoService.solicitarCantidad(productoSeleccionado);
            
            // Mostrar resumen
            productoService.mostrarResumenCompra(productoSeleccionado, cantidad);
            
            // Confirmar compra
            double total = productoSeleccionado.getPrecio() * cantidad;
            if (!pagoService.confirmarPago(total)) {
                System.out.println("Compra cancelada");
                return;
            }
            
            // Procesar pago
            if (pagoService.procesarPago(total)) {
                // Actualizar stock
                if (productoService.procesarCompra(productoSeleccionado, cantidad)) {
                    // Crear pedido
                    pedidoService.crearPedido(cliente, productoSeleccionado, cantidad);
                    System.out.println("✓ Compra completada exitosamente");
                    //Actualizamos el archivo de productos
                    ParseData.saveProductoData(productos);
                } else {
                    System.out.println("✗ Error al procesar la compra");
                }
            } else {
                System.out.println("✗ Error en el procesamiento del pago");
            }
            
        } catch (Exception e) {
            System.err.println("Error durante la compra: " + e.getMessage());
        }
    }
    
    /**
     * Gestión de pedidos para clientes.
     * Permite ver la lista de pedidos y consultar detalles específicos.
     * 
     * @param cliente El cliente propietario de los pedidos
     */
    private void gestionarPedidosCliente(Cliente cliente) {
        List<Pedido> pedidosCliente = pedidoService.obtenerPedidosCliente(cliente);
        
        if (pedidosCliente.isEmpty()) {
            System.out.println("No tienes pedidos registrados.");
            return;
        }
        
        pedidoService.mostrarListaPedidos(pedidosCliente);
        
        String codigo = InputUtils.leerTexto("Ingrese el código del pedido para ver detalles (Enter para volver): ");
        if (!codigo.isEmpty()) {
            Pedido pedido = pedidoService.buscarPedido(codigo);
            if (pedido != null && pedidosCliente.contains(pedido)) {
                pedidoService.mostrarDetallesPedido(pedido);
            } else {
                System.out.println("Pedido no encontrado o no autorizado.");
            }
        }
    }
    
    /**
     * Muestra el historial completo de pedidos del cliente.
     * 
     * @param cliente El cliente del cual mostrar el historial
     */
    private void mostrarHistorialPedidos(Cliente cliente) {
        List<Pedido> pedidosCliente = pedidoService.obtenerPedidosCliente(cliente);
        pedidoService.mostrarListaPedidos(pedidosCliente);
        InputUtils.pausar();
    }
    
    /**
     * Consulta y muestra los pedidos asignados a un repartidor específico.
     * 
     * @param repartidor El repartidor del cual consultar los pedidos
     */
    private void consultarPedidosRepartidor(Repartidor repartidor) {
        List<Pedido> pedidosRepartidor = pedidoService.obtenerPedidosRepartidor(repartidor);
        pedidoService.mostrarListaPedidos(pedidosRepartidor);
        InputUtils.pausar();
    }
    
    /**
     * Permite al repartidor actualizar el estado de un pedido asignado.
     * Solo puede actualizar pedidos que le pertenecen.
     * 
     * @param repartidor El repartidor que actualiza el estado
     */
    private void actualizarEstadoPedido(Repartidor repartidor) {
        List<Pedido> pedidosRepartidor = pedidoService.obtenerPedidosRepartidor(repartidor);
        
        if (pedidosRepartidor.isEmpty()) {
            System.out.println("No tienes pedidos asignados.");
            return;
        }
        
        pedidoService.mostrarListaPedidos(pedidosRepartidor);
        
        String codigo = InputUtils.leerTextoNoVacio("Ingrese el código del pedido a actualizar: ");
        Pedido pedido = pedidoService.buscarPedido(codigo);
        
        if (pedido == null || !pedidosRepartidor.contains(pedido)) {
            System.out.println("Pedido no encontrado o no autorizado.");
            return;
        }
        
        String[] estadosDisponibles = {"EN_RUTA", "ENTREGADO"};
        int opcionEstado = InputUtils.leerOpcionMenu(estadosDisponibles);
        
        EstadoPedido nuevoEstado = opcionEstado == 1 ? EstadoPedido.EN_RUTA : EstadoPedido.ENTREGADO;
        pedidoService.actualizarEstadoPedido(codigo, nuevoEstado);
    }
    
    /**
     * Permite al repartidor marcar un pedido como entregado.
     * Solo puede marcar pedidos que le están asignados.
     * 
     * @param repartidor El repartidor que marca la entrega
     */
    private void marcarPedidoEntregado(Repartidor repartidor) {
        List<Pedido> pedidosRepartidor = pedidoService.obtenerPedidosRepartidor(repartidor);
        
        if (pedidosRepartidor.isEmpty()) {
            System.out.println("No tienes pedidos asignados.");
            return;
        }
        
        pedidoService.mostrarListaPedidos(pedidosRepartidor);
        
        String codigo = InputUtils.leerTextoNoVacio("Ingrese el código del pedido entregado: ");
        Pedido pedido = pedidoService.buscarPedido(codigo);
        
        if (pedido != null && pedidosRepartidor.contains(pedido)) {
            if (pedidoService.marcarComoEntregado(codigo)) {
                System.out.println("✓ Pedido marcado como entregado");
            }
        } else {
            System.out.println("Pedido no encontrado o no autorizado.");
        }
    }
    
    /**
     * Ejecuta el bucle principal del sistema de delivery.
     * Maneja la autenticación, validación y navegación del menú principal.
     * El sistema continúa ejecutándose hasta que el usuario decide salir.
     */
    public void ejecutar() {
        System.out.println("=== SISTEMA DE DELIVERY ===");
        
        boolean continuarSistema = true;
        
        while (continuarSistema) {
            try {
                Usuario usuarioAutenticado = iniciarSesion();
                
                if (usuarioAutenticado != null && validarUsuario(usuarioAutenticado)) {
                    mostrarMenuPrincipal(usuarioAutenticado);
                    // Después de salir del menú, regresa automáticamente al login
                    System.out.println("\nRegresando al inicio de sesión...");
                    System.out.println("=".repeat(50));
                } else {
                    System.out.println("Acceso denegado.");
                    continuarSistema = InputUtils.leerConfirmacion("¿Desea intentar nuevamente?");
                }
            } catch (Exception e) {
                System.err.println("Error en el sistema: " + e.getMessage());
                continuarSistema = InputUtils.leerConfirmacion("¿Desea continuar?");
            }
        }
        
        System.out.println("Gracias por usar el Sistema de Delivery. ¡Hasta pronto!");
        InputUtils.cerrar();
    }

    /**
     * Método principal que inicia la aplicación del sistema de delivery.
     * Crea una instancia del sistema y ejecuta el bucle principal.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.ejecutar();
    }
}