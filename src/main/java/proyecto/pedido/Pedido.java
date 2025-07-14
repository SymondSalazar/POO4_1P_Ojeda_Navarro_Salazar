package proyecto.pedido;

import proyecto.EstadoPedido;
import proyecto.usuario.Repartidor;
import proyecto.usuario.Cliente;
import proyecto.producto.Producto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un pedido en el sistema de delivery.
 * Contiene información sobre el producto pedido, cliente, repartidor, estado y detalles del pedido.
 */
public class Pedido {
    private String codigoPedido;
    private LocalDateTime fechaPedido;
    private Producto producto;
    private int cantidadPedida;
    private double valorPagado;
    private EstadoPedido estado;
    private Repartidor repartidor;
    private Cliente cliente;

    /**
     * Constructor principal para crear un nuevo pedido.
     * 
     * @param codigoPedido Código único identificador del pedido
     * @param cliente Cliente que realiza el pedido
     * @param producto Producto que se está pidiendo
     * @param cantidadPedida Cantidad del producto pedido (mínimo 1)
     */
    public Pedido(String codigoPedido, Cliente cliente, Producto producto, int cantidadPedida) {
        this.codigoPedido = codigoPedido != null ? codigoPedido.trim() : "";
        this.cliente = cliente;
        this.producto = producto;
        this.cantidadPedida = Math.max(1, cantidadPedida);
        this.fechaPedido = LocalDateTime.now();
        this.valorPagado = producto != null ? producto.getPrecio() * this.cantidadPedida : 0.0;
        this.estado = EstadoPedido.EN_PREPARACION;
        this.repartidor = null;
    }

    /**
     * Constructor para cargar pedidos desde archivo.
     * 
     * @param codigoPedido Código único del pedido
     * @param fechaPedidoStr Fecha del pedido en formato string
     * @param cantPedido Cantidad pedida
     * @param valorPagado Valor total pagado por el pedido
     * @param estado Estado actual del pedido
     * @param repartidor Repartidor asignado al pedido
     */
    public Pedido(String codigoPedido, String fechaPedidoStr, int cantPedido, 
                  double valorPagado, EstadoPedido estado, Repartidor repartidor) {
        this.codigoPedido = codigoPedido != null ? codigoPedido.trim() : "";
        try {
            //Hora actual
            this.fechaPedido = LocalDateTime.parse(fechaPedidoStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            this.fechaPedido = LocalDateTime.now();
        }
        this.cantidadPedida = Math.max(1, cantPedido);
        this.valorPagado = Math.max(0, valorPagado);
        this.estado = estado != null ? estado : EstadoPedido.EN_PREPARACION;
        this.repartidor = repartidor;
    }

    /**
     * Obtiene el código único del pedido.
     * 
     * @return El código del pedido
     */
    public String getCodigoPedido() {
        return codigoPedido;
    }
    
    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }
    
    public String getFechaPedidoString() {
        return fechaPedido.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public int getCantidadPedida() {
        return cantidadPedida;
    }
    
    public double getValorPagado() {
        return valorPagado;
    }
    
    public EstadoPedido getEstado() {
        return estado;
    }
    
    public Repartidor getRepartidor() {
        return repartidor;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    // Setters con validaciones
    public void setCodigoPedido(String codigoPedido) {
        if (codigoPedido != null && !codigoPedido.trim().isEmpty()) {
            this.codigoPedido = codigoPedido.trim();
        }
    }
    
    public void setCantidadPedida(int cantidadPedida) {
        if (cantidadPedida > 0) {
            this.cantidadPedida = cantidadPedida;
            // Recalcular valor si hay producto
            if (this.producto != null) {
                this.valorPagado = this.producto.getPrecio() * cantidadPedida;
            }
        }
    }
    
    public void setValorPagado(double valorPagado) {
        if (valorPagado >= 0) {
            this.valorPagado = valorPagado;
        }
    }
    
    public void setEstado(EstadoPedido estado) {
        if (estado != null) {
            this.estado = estado;
        }
    }
    
    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }
    
    public void setProducto(Producto producto) {   
        this.producto = producto;
        // Recalcular valor pagado
        if (producto != null) {
            this.valorPagado = producto.getPrecio() * this.cantidadPedida;
        }
    }

    // Métodos de negocio
    public boolean estaEntregado() {
        return this.estado == EstadoPedido.ENTREGADO;
    }
    
    public boolean estaEnRuta() {
        return this.estado == EstadoPedido.EN_RUTA;
    }
    
    public boolean estaEnPreparacion() {
        return this.estado == EstadoPedido.EN_PREPARACION;
    }
    
    public boolean tieneRepartidorAsignado() {
        return this.repartidor != null;
    }
    
    public void marcarComoEntregado() {
        this.estado = EstadoPedido.ENTREGADO;
    }
    
    public void enviarADelivery() {
        if (this.estado == EstadoPedido.EN_PREPARACION && this.repartidor != null) {
            this.estado = EstadoPedido.EN_RUTA;
        }
    }
    
    public double getValorTotal() {
        return this.valorPagado;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%d|%.2f|%s|%s", 
                codigoPedido, 
                getFechaPedidoString(), 
                cantidadPedida, 
                valorPagado, 
                estado, 
                repartidor != null ? repartidor.getCodigoUnico() : "Sin asignar");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pedido pedido = (Pedido) obj;
        return codigoPedido != null ? codigoPedido.equals(pedido.codigoPedido) : pedido.codigoPedido == null;
    }
    

}
