package proyecto.servicio;

import proyecto.pedido.Pedido;
import proyecto.EstadoPedido;
import proyecto.usuario.Cliente;
import proyecto.usuario.Repartidor;
import proyecto.producto.Producto;
import proyecto.leerArchivo.ManejoArchivos;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Servicio para manejo de pedidos
 */
public class PedidoService {
    private List<Pedido> pedidos;
    private List<Repartidor> repartidoresDisponibles;
    private Random random;
    public static int contador = 0;
    
    public PedidoService() {
        this.pedidos = new ArrayList<>();
        this.repartidoresDisponibles = new ArrayList<>();
        this.random = new Random();
    }
    
    public PedidoService(List<Repartidor> repartidores) {
        this();
        this.repartidoresDisponibles = repartidores != null ? repartidores : new ArrayList<>();
    }
    
    /**
     * Crea un nuevo pedido
     */
    public Pedido crearPedido(Cliente cliente, Producto producto, int cantidad) {

        //Actualizar el archivo de 
        String codigoPedido = generarCodigoPedido();
        Pedido nuevoPedido = new Pedido(codigoPedido, cliente, producto, cantidad);
        
        // Asignar repartidor automáticamente
        Repartidor repartidorAsignado = asignarRepartidorAutomatico();
        if (repartidorAsignado != null) {
            nuevoPedido.setRepartidor(repartidorAsignado);
        }
        
        pedidos.add(nuevoPedido);
        
        // Guardar pedido en archivo
        guardarPedidoEnArchivo(nuevoPedido);
        
        System.out.println("\n✓ Pedido creado exitosamente");
        System.out.println("Código de pedido: " + codigoPedido);
        System.out.println("Estado: " + nuevoPedido.getEstado());
        if (repartidorAsignado != null) {
            System.out.println("Repartidor asignado: " + repartidorAsignado.getNombres() + " " + repartidorAsignado.getApellidos());
            System.out.println("Empresa: " + repartidorAsignado.getEmpresa());
        }
        
        return nuevoPedido;
    }
    
    /**
     * Genera un código único para el pedido
     */
    private String generarCodigoPedido() {
        return "PED-" + contador++;
    }
    
    /**
     * Asigna un repartidor automáticamente
     */
    private Repartidor asignarRepartidorAutomatico() {
        if (repartidoresDisponibles.isEmpty()) {
            System.out.println("Advertencia: No hay repartidores disponibles");
            return null;
        }
        
        // Seleccionar un repartidor aleatoriamente
        int indice = random.nextInt(repartidoresDisponibles.size());
        return repartidoresDisponibles.get(indice);
    }
    
    /**
     * Busca un pedido por código
     */
    public Pedido buscarPedido(String codigoPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigoPedido().equals(codigoPedido)) {
                return pedido;
            }
        }
        return null;
    }
    
    /**
     * Obtiene todos los pedidos de un cliente
     */
    public List<Pedido> obtenerPedidosCliente(Cliente cliente) {
        List<Pedido> pedidosCliente = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getCliente() != null && 
                pedido.getCliente().getCodigoUnico().equals(cliente.getCodigoUnico())) {
                pedidosCliente.add(pedido);
            }
        }
        return pedidosCliente;
    }
    
    /**
     * Obtiene todos los pedidos asignados a un repartidor
     */
    public List<Pedido> obtenerPedidosRepartidor(Repartidor repartidor) {
        List<Pedido> pedidosRepartidor = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getRepartidor() != null && 
                pedido.getRepartidor().getCodigoUnico().equals(repartidor.getCodigoUnico())) {
                pedidosRepartidor.add(pedido);
            }
        }
        return pedidosRepartidor;
    }
    
    /**
     * Actualiza el estado de un pedido
     */
    public boolean actualizarEstadoPedido(String codigoPedido, EstadoPedido nuevoEstado) {
        Pedido pedido = buscarPedido(codigoPedido);
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
            
            // Actualizar archivo con todos los pedidos
            actualizarArchivoPedidos();
            
            System.out.println("Estado del pedido " + codigoPedido + " actualizado a: " + nuevoEstado);
            return true;
        }
        System.out.println("Pedido no encontrado: " + codigoPedido);
        return false;
    }
    
    /**
     * Marca un pedido como entregado
     */
    public boolean marcarComoEntregado(String codigoPedido) {
        return actualizarEstadoPedido(codigoPedido, EstadoPedido.ENTREGADO);
    }
    
    /**
     * Muestra los detalles de un pedido
     */
    public void mostrarDetallesPedido(Pedido pedido) {
        if (pedido == null) {
            System.out.println("Pedido no encontrado");
            return;
        }
        
        System.out.println("\n--- DETALLES DEL PEDIDO ---");
        System.out.println("Código: " + pedido.getCodigoPedido());
        System.out.println("Fecha: " + pedido.getFechaPedidoString());
        System.out.println("Cliente: " + (pedido.getCliente() != null ? pedido.getCliente().getNombreCompleto() : "No asignado"));
        System.out.println("Producto: " + (pedido.getProducto() != null ? pedido.getProducto().getNombre() : "No especificado"));
        System.out.println("Cantidad: " + pedido.getCantidadPedida());
        System.out.println("Valor total: $" + String.format("%.2f", pedido.getValorPagado()));
        System.out.println("Estado: " + pedido.getEstado());
        System.out.println("Repartidor: " + (pedido.getRepartidor() != null ? 
            pedido.getRepartidor().getNombres() + " " + pedido.getRepartidor().getApellidos() : "Sin asignar"));
        if (pedido.getRepartidor() != null) {
            System.out.println("Empresa: " + pedido.getRepartidor().getEmpresa());
        }
    }
    
    /**
     * Muestra una lista de pedidos
     */
    public void mostrarListaPedidos(List<Pedido> listaPedidos) {
        if (listaPedidos.isEmpty()) {
            System.out.println("No hay pedidos para mostrar");
            return;
        }
        
        System.out.println("\n--- LISTA DE PEDIDOS ---");
        System.out.printf("%-15s %-12s %-15s %-12s%n", "Código", "Estado", "Valor", "Repartidor");
        System.out.println("-".repeat(60));
        
        for (Pedido pedido : listaPedidos) {
            System.out.printf("%-15s %-12s $%-14.2f %-12s%n",
                pedido.getCodigoPedido(),
                pedido.getEstado(),
                pedido.getValorPagado(),
                pedido.getRepartidor() != null ? pedido.getRepartidor().getNombres() : "Sin asignar");
        }
    }
    
    /**
     * Guarda un pedido en el archivo de texto
     */
    private void guardarPedidoEnArchivo(Pedido pedido) {
        try {
            String lineaPedido = String.format("%s|%s|%s|%s|%d|%.2f|%s|%s",
                pedido.getCodigoPedido(),
                pedido.getFechaPedidoString(),
                pedido.getCliente() != null ? pedido.getCliente().getNombreCompleto() : "Sin cliente",
                pedido.getProducto() != null ? pedido.getProducto().getNombre() : "Sin producto",
                pedido.getCantidadPedida(),
                pedido.getValorPagado(),
                pedido.getEstado(),
                pedido.getRepartidor() != null ? pedido.getRepartidor().getNombreCompleto() : "Sin asignar"
            );
            
            ManejoArchivos.EscribirLinea("Data/Pedidos.txt", lineaPedido);
            
        } catch (Exception e) {
            System.err.println("Error al guardar pedido en archivo: " + e.getMessage());
        }
    }
    
    /**
     * Actualiza completamente el archivo de pedidos
     */
    private void actualizarArchivoPedidos() {
        try {
            List<String> lineas = new ArrayList<>();
            lineas.add("Código|Fecha|Cliente|Producto|Cantidad|ValorTotal|Estado|Repartidor");
            
            for (Pedido pedido : pedidos) {
                String lineaPedido = String.format("%s|%s|%s|%s|%d|%.2f|%s|%s",
                    pedido.getCodigoPedido(),
                    pedido.getFechaPedidoString(),
                    pedido.getCliente() != null ? pedido.getCliente().getNombreCompleto() : "Sin cliente",
                    pedido.getProducto() != null ? pedido.getProducto().getNombre() : "Sin producto",
                    pedido.getCantidadPedida(),
                    pedido.getValorPagado(),
                    pedido.getEstado(),
                    pedido.getRepartidor() != null ? pedido.getRepartidor().getNombreCompleto() : "Sin asignar"
                );
                lineas.add(lineaPedido);
            }
            
            ManejoArchivos.EscribirArchivo("Data/Pedidos.txt", (ArrayList<String>) lineas);
            
        } catch (Exception e) {
            System.err.println("Error al actualizar archivo de pedidos: " + e.getMessage());
        }
    }
    

    // Getters y setters
    public List<Pedido> getPedidos() {
        return new ArrayList<>(pedidos);
    }
    
    public void setRepartidoresDisponibles(List<Repartidor> repartidores) {
        this.repartidoresDisponibles = repartidores != null ? repartidores : new ArrayList<>();
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos != null ? pedidos : new ArrayList<>();
    }
}
