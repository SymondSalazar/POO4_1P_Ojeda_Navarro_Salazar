package proyecto;

/**
 * Enumeración que define los posibles estados de un pedido en el sistema de delivery.
 * Los pedidos siguen un flujo secuencial desde EN_PREPARACION hasta ENTREGADO.
 */
public enum EstadoPedido {
    /** Estado inicial cuando el pedido ha sido creado y está siendo preparado */
    EN_PREPARACION("En Preparación"),
    /** Estado cuando el pedido está siendo transportado por el repartidor */
    EN_RUTA("En Ruta"), 
    /** Estado final cuando el pedido ha sido entregado al cliente */
    ENTREGADO("Entregado");
    
    private final String descripcion;
    
    /**
     * Constructor del enum EstadoPedido.
     * 
     * @param descripcion Descripción legible del estado
     */
    EstadoPedido(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Obtiene la descripción del estado.
     * 
     * @return La descripción del estado
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Devuelve la descripción del estado.
     * 
     * @return La descripción del estado
     */
    @Override
    public String toString() {
        return descripcion;
    }
}
